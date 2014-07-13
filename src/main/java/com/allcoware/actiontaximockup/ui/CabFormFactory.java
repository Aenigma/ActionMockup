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
package com.allcoware.actiontaximockup.ui;

import com.allcoware.actiontaximockup.Cab;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author alfred
 * @author Kevin Raoofi
 */
public class CabFormFactory implements ResourceFormFactory<Long, Cab> {

    private final Map<Long, Cab> formMap;
    private transient long count;

    public CabFormFactory() {
        this.formMap = new TreeMap<>();
        this.count = 0;
    }

    @Override
    public ResourceBuilder<Cab> getForm(Long key) {
        return new CabForm(formMap.get(key));
    }

    @Override
    public ResourceBuilder<Cab> makeNewForm() {
        this.formMap.put(count, new Cab());
        return new CabForm(this.formMap.get(count++));
    }
}
