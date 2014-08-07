package com.allcoware.actiontaximockup.ui.resourceholders;

import com.allcoware.actiontaximockup.ui.resources.ResourceBuilder;

/*
 * Copyright (C) 2014 Jon Butler, Sam Morekas, Rushikesh Parekh, and Kevin
 * Raoofi
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * <p>
 * The class implementing this interface is <em>important</em>!</p>
 *
 * <p>
 * The implementing class is responsible for defining how to get a
 * {@link JResourceForm} instance paired with the appropriate resource. It is
 * thus responsible for creating new instances of the resource or managing that
 * with the implementation of the {@code JResourceForm}.</p>
 *
 * <p>
 * In fact, it is directly responsible for aggregating either or JResourceForm
 * instances and the resource instances</p>
 *
 * <p>
 * For a POJO implementation, this may necessitate this class to use some sort
 * of {@code Collection}. In an implementation using a database, this would be
 * responsible for translating DB to POJO.</p>
 *
 * <p>
 * It is perfectly reasonable to decouple this from the UI aspect and compose
 * another class to do all of the aggregation stuff. But, we have enough of
 * those already and kitchen sinks are much easier to code at this stage of
 * development. We can totally do the decoupling later, anyhow.</p>
 *
 * @author Kevin Raoofi
 * @param <R> the resource handled by the instance
 * @param <K> the key used to fetch the appropriate resource; should probably be
 * a {@code Long}, but can be whatever the unique ID for the resource should be
 */
public interface ResourceFormFactory<K, R> {

    /**
     * Looks up a pre-existing resource using the supplied key and returns a
     * form responsible for orchestrating its modification
     *
     * @param key used to look up a pre-existing resource
     * @return a form responsible for the resource instance. If the resource
     * does not exist, returns null
     */
    ResourceBuilder<R> getForm(K key);

    /**
     * A new resource is added and tracked by the class. The key for this new
     * resource must be obtainable by a call to
     * {@link #getForm(java.lang.Object)} after this method is executed. After
     * the resource is made, a form associated with it is returned.
     *
     * @return
     */
    ResourceBuilder<R> makeNewForm();
}
