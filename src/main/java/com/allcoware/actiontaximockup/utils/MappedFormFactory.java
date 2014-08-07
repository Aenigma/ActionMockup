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

import com.allcoware.actiontaximockup.ui.resourceholders.ResourceFormFactory;
import com.allcoware.actiontaximockup.ui.resources.ResourceBuilder;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Implements common functionalities of resource form factories using an
 * internal Map.
 *
 * @author Kevin Raoofi
 * @param <R> the resource to be generated
 * @param <F> the form to be returned
 */
public class MappedFormFactory<R, F extends ResourceBuilder<R>> implements ResourceFormFactory<Long, R> {

    private final Map<Long, R> formMap;
    private transient long count;
    protected final Function<R, F> resourceFormFactory;
    protected final Supplier<R> resourceFactory;

    public MappedFormFactory(
            Function<R, F> resourceFormFactory,
            Supplier<R> resourceFactory) {
        this.formMap = new TreeMap<>();
        this.count = 0;
        this.resourceFormFactory = resourceFormFactory;
        this.resourceFactory = resourceFactory;
    }

    @Override
    public F getForm(Long key) {
        return resourceFormFactory.apply(formMap.get(key));
    }

    @Override
    public F makeNewForm() {
        this.formMap.put(count, resourceFactory.get());
        return resourceFormFactory.apply(this.formMap.get(count++));
    }

    public Map<Long, R> getMap() {
        return formMap;
    }
}
