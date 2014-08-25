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

/**
 * A class implementing this interface should build an instance of the resource
 * and set it so that {@link #isReady()} returns true. A class using this purely
 * using this interface would only be able to fetch the resource and know when
 * it can by polling {@link #isReady()}.
 *
 * @author Kevin Raoofi
 * @param <R> the resource handled
 */
public interface ResourceBuilder<R> {

    /**
     * Signifies when the resource is ready to be taken with
     * {@link #getResource()}
     *
     * @return true if the form is completed and the resource being built is
     * ready to fetched
     */
    public boolean isReady();

    /**
     * If {@link #isReady()} is false, this method should return null.
     *
     * @return resource that this form is responsible for
     */
    public R getResource();
}
