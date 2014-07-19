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
package com.allcoware.actiontaximockup.ui.resourceholders;

import com.allcoware.actiontaximockup.ui.resourceholders.ResourceFormFactory;
import com.allcoware.actiontaximockup.resources.Driver;
import com.allcoware.actiontaximockup.ui.guiinterfaces.DriverForm;
import com.allcoware.actiontaximockup.ui.guiinterfaces.JResourceForm;
import java.util.Map;
import java.util.TreeMap;

/**
 * Generates DriverForm instances and keeps track of 
 *
 * @author alfred
 * @author Kevin Raoofi
 */
public class DriverFormFactory implements ResourceFormFactory<Long, Driver> {

    private final Map<Long, Driver> formMap;
    private transient long count;

    public DriverFormFactory() {
        this.formMap = new TreeMap<>();
        count = 0;
    }

    @Override
    public JResourceForm<Driver> getForm(Long key) {
        return new DriverForm(formMap.get(key));
    }

    @Override
    public JResourceForm<Driver> makeNewForm() {
        this.formMap.put(count, new Driver());
        return new DriverForm(this.formMap.get(count++));
    }
    
    public Map getMap(){
        return formMap;
    }
}