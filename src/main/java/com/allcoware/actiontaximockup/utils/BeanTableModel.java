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
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.apache.commons.beanutils.BeanUtilsBean;

/**
 * Classes implementing this should also consider extending
 * {@link AbstractTableModel} as well.
 *
 *
 * @author Kevin Raoofi
 * @param <R>
 */
public interface BeanTableModel<R> extends TableModel {

    static <R> List<String> beanToList(R bean, PropertyDescriptor[] pds)
            throws ReflectiveOperationException {
        List<String> beanList = new ArrayList<>();
        for (int i = 0; i < pds.length; i++) {
            Object property = pds[i].getReadMethod().invoke(bean);
            beanList.add(i, property == null ? "" : property.toString());
        }
        return beanList;
    }

    static <R> R initializeInstance(Class<R> clazz, BeanUtilsBean bub,
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

    boolean addRow(int i);

    boolean addRow(int i, R rsrc);

    R getResourceRow(int row) throws ReflectiveOperationException;

    default Collection<R> getResources() {
        int rowCount = getRowCount();
        List<R> resources = new ArrayList<>(rowCount);
        try {
            for (int row = 0; row < rowCount; row++) {
                resources.add(getResourceRow(row));
            }
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
        return resources;
    }

    default void removeRow(int row) {
        removeRows(row);
    }

    void removeRows(int... rows);

    void setResourceRow(int row, R rsrc) throws ReflectiveOperationException;

    void setResources(Collection<R> resources);
}
