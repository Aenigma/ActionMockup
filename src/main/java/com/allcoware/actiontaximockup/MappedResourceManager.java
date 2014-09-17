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
package com.allcoware.actiontaximockup;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MappedResourceManager<R> implements ResourceManager<Long, R> {

    private long count;

    private final Map<Long, R> formMap;

    protected final BiConsumer<R, Long> resourceIDSetter;
    protected final Function<R, Long> resourceIDGetter;

    public MappedResourceManager(
            Supplier<R> resourceFactory, Function<R, Long> resourceIDGetter) {
        this.formMap = new TreeMap<>();
        this.resourceIDGetter = resourceIDGetter;

        this.resourceIDSetter = (x, y) -> {
        };
    }

    public MappedResourceManager(
            Supplier<R> resourceFactory,
            BiConsumer<R, Long> resourceIDSetter,
            Function<R, Long> resourceIDGetter) {
        this.formMap = new TreeMap<>();
        this.resourceIDSetter = resourceIDSetter;
        this.resourceIDGetter = resourceIDGetter;
    }

    @Override
    public void create(R r) {
        long id = count++;
        create(id, r);
        Long result = resourceIDGetter.apply(r);

        //return result != null ? result : id;
    }

    @Override
    public void create(Long key, R value) {
        resourceIDSetter.accept(value, key);
        formMap.put(key, value);
    }

    @Override
    public R read(Long key) {
        return formMap.get(key);
    }

    @Override
    public void update(R resource) {
        update(resourceIDGetter.apply(resource), resource);
    }

    @Override
    public void update(Long key, R value) {
        formMap.put(key, value);
    }

    @Override
    public void delete(R resource) {
        formMap.remove(resourceIDGetter.apply(resource));
    }

    /**
     * Returns a copy of the map which is used internally
     * @return 
     */
    public Map<Long, R> getMap() {
        return new TreeMap<>(formMap);
    }
}
