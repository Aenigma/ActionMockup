/*
 * Copyright (C) 2014 Jon Butler, Sam Morekas,
 *     Rushikesh Parekh, and Kevin Raoofi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.allcoware.actiontaximockup.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 *
 * @author Kevin Raoofi
 * @param <R>
 */
class BeanTableModelImpl<R> extends AbstractTableModel implements BeanTableModel<R> {

    private static final Logger LOG = Logger.getLogger(BeanTableModelImpl.class
            .getName());

    private List<List<String>> table;
    private final Class<R> managedClass;
    private final PropertyDescriptor[] pds;
    private final BeanUtilsBean bub;

    protected static <R> List<String> beanToList(R bean,
            PropertyDescriptor[] pds)
            throws ReflectiveOperationException {
        List<String> beanList = new ArrayList<>();
        for (int i = 0; i < pds.length; i++) {
            Object property = pds[i].getReadMethod().invoke(bean);
            beanList.add(i, property == null ? "" : property.toString());
        }
        return beanList;
    }

    /**
     * This method instantiates an object but forces {@link BeanUtilsBean} to
     * populate it with empty fields. This is important as default values are
     * handled by various {@link Converter}s and this method allows the
     * converters to make the decision what default, uninitialized, values are.
     *
     * @param <R>   the resource class
     * @param clazz explicit passing of resource class
     * @param bub   used to invoke Converters
     * @param pds   properties to populate with bub
     * @return
     */
    protected static <R> R initializeInstance(Class<R> clazz, BeanUtilsBean bub,
            PropertyDescriptor[] pds) {
        try {
            R rsrc = clazz.newInstance();
            for (PropertyDescriptor pd : pds) {
                bub.setProperty(rsrc, pd.getName(),
                        bub.getProperty(rsrc, pd.getName()));
            }
            return rsrc;
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public BeanTableModelImpl(Class<R> managedClass, BeanUtilsBean bub) {
        this.bub = bub;
        this.managedClass = managedClass;
        /*
         * class property is useless -- let's ignore it
         */
        pds = Arrays.stream(
                PropertyUtils.getPropertyDescriptors(managedClass))
                .filter(p -> !p.getName().equals("class"))
                .toArray(PropertyDescriptor[]::new);
    }

    /**
     * Creates an instance using the default {@code BeanUtilsBean} returned by
     * {@link BeanUtilsBean#getInstance()}
     *
     * @param managedClass class of the managed bean, which must be same as the
     *                     type expressed by {@code R}
     */
    public BeanTableModelImpl(Class<R> managedClass) {
        this(managedClass, BeanUtilsBean.getInstance());
    }

    @Override
    public boolean addRow(int i) {
        R emptyObj;
        emptyObj = initializeInstance(managedClass, bub, pds);

        return addRow(i, emptyObj);
    }

    @Override
    public boolean addRow(int i, R rsrc) {
        try {
            table.add(i, beanToList(rsrc, pds));
        } catch (ReflectiveOperationException ex) {
            LOG.log(Level.WARNING, null, ex);
            return false;
        }
        fireTableRowsInserted(i, i);
        return true;
    }

    @Override
    public void removeRows(int... rows) {
        List<Integer> rowList = Arrays.stream(rows)
                .boxed()
                .collect(Collectors.toCollection(() -> new TreeSet<Integer>(
                                        Collections.reverseOrder())))
                .stream()
                .collect(Collectors.toList());

        int min = rowList.get(rowList.size() - 1);
        int max = rowList.get(0) - rowList.size();

        for (int i : rowList) {
            table.remove(i);
        }

        fireTableRowsDeleted(min, max);
    }

    @Override
    public R getResourceRow(int row) throws ReflectiveOperationException {
        R resource = initializeInstance(managedClass, bub, pds);
        Map<String, String> rsrcMap = new HashMap<>();
        for (int j = 0; j < pds.length; j++) {
            rsrcMap.put(
                    pds[j].getName(),
                    table.get(row).get(j));
        }
        bub.populate(resource, rsrcMap);

        return resource;
    }

    /**
     * Returns a copy of a {@link java.util.List<String>} representing a row
     * which is not guaranteed to be modifiable nor a representative of the
     * internally used row.
     *
     * @param row index of row in the table
     * @return a row with each column represented by a String
     */
    public List<String> getResourceListRow(int row) {
        return Collections.unmodifiableList(table.get(row));
    }

    @Override
    public void setResourceRow(int row, R rsrc) throws
            ReflectiveOperationException {
        setResourceListRow(row, beanToList(rsrc, pds));
    }

    public void setResourceListRow(int row, List<String> rsrc) {
        table.set(row, rsrc);
    }

    @Override
    public void setResources(Collection<R> resources) {
        int rowCount = resources.size();
        table = new ArrayList<>(rowCount);
        Iterator<R> iter = resources.iterator();
        for (int i = 0; i < rowCount && iter.hasNext(); i++) {
            assert i < rowCount == iter.hasNext();
            R resource = iter.next();
            addRow(i, resource);
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column < pds.length) {
            return pds[column].getDisplayName();
        } else {
            return super.getColumnName(column);
        }
    }

    @Override
    public int getRowCount() {
        return table.size();
    }

    @Override
    public int getColumnCount() {
        return pds.length;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        return table.get(rowIndex).get(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            R rsrc = getResourceRow(rowIndex);
            bub.setProperty(rsrc, pds[columnIndex].getName(), aValue.toString());
            setResourceRow(rowIndex, rsrc);
        } catch (Exception ex) {
            Logger.getLogger(BeanTableModelImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
