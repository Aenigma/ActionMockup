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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 * This class is experimental; avoid use for now.
 *
 * @author Kevin Raoofi
 */
class JacksonJsonTableModelImpl<R> extends AbstractTableModel implements BeanTableModel<R> {

    public static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
    }

    private final Class<R> managedClass;
    private final ArrayNode rootNode;
    private final int columnSize;
    private final Map<Integer, String> propertyKeyToNum;

    public JacksonJsonTableModelImpl(Class<R> managedClass) {
        this(managedClass, (JsonNode) mapper.valueToTree(new Object[]{}));
    }

    public JacksonJsonTableModelImpl(Class<R> managedClass,
            R... objects) {
        this(managedClass, (JsonNode) mapper.valueToTree(objects));
    }

    public JacksonJsonTableModelImpl(Class<R> managedClass,
            Collection<R> objects) {
        this(managedClass, (JsonNode) mapper.valueToTree(objects));
    }

    public JacksonJsonTableModelImpl(Class<R> managedClass, JsonNode rootNode) {
        this.managedClass = managedClass;
        if (!rootNode.isArray()) {
            throw new IllegalArgumentException(
                    "Root object must be collection or array!");
        }
        this.rootNode = (ArrayNode) rootNode;

        propertyKeyToNum = new HashMap<>();
        try {
            JsonNode tmp = mapper.valueToTree(managedClass.newInstance());
            Iterator<String> iter = tmp.fieldNames();

            for (int i = 0; iter.hasNext(); i++) {
                propertyKeyToNum.put(i, iter.next());
            }

            this.columnSize = propertyKeyToNum.size();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public boolean addRow(int i) {
        rootNode.insertObject(i);
        return true;
    }

    @Override
    public boolean addRow(int i, R rsrc) {
        rootNode.insertPOJO(i, rsrc);
        return true;
    }

    @Override
    public R getResourceRow(int row) throws ReflectiveOperationException {
        try {
            return mapper.treeToValue(rootNode.get(row), managedClass);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(JacksonJsonTableModelImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new ReflectiveOperationException(ex);
        }
    }

    @Override
    public void removeRows(int... rows) {
        for (int i = 0; i < rows.length; i++) {
            rootNode.remove(i);
        }
    }

    @Override
    public void setResourceRow(int row, R rsrc) throws
            ReflectiveOperationException {
        rootNode.set(row, mapper.valueToTree(rsrc));
    }

    @Override
    public void setResources(Collection<R> resources) {
        int rowCount = resources.size();
        rootNode.removeAll();

        Collection<JsonNode> nodes = resources.stream().map((rsrc) -> {
            return (JsonNode) mapper.valueToTree(rsrc);
        }).collect(Collectors.toList());

        rootNode.addAll(nodes);
    }

    @Override
    public int getRowCount() {
        return rootNode.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnSize;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return propertyKeyToNum.get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        JsonNode row = rootNode.get(rowIndex);
        Iterator<JsonNode> iter = row.iterator();
        for (int i = 0; i < columnIndex; i++) {
            iter.next();
        }
        R value = null;
        try {
            value = mapper.treeToValue(iter.next(), managedClass);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(JacksonJsonTableModelImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return String.valueOf(value);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ObjectNode on = (ObjectNode) rootNode.with(propertyKeyToNum
                .get(rowIndex));
        on.set(propertyKeyToNum.get(columnIndex), mapper.valueToTree(aValue));

    }

}
