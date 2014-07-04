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

/**
 *
 * @author alfred
 */
public class RecurringTransactionForm implements ResourceBuilder {

    private com.allcoware.actiontaximockup.RecurringTransaction recurringTransaction;

    public boolean isReady() {
        return false;
    }

    public com.allcoware.actiontaximockup.Driver getResource() {
        return null;
    }

    public void updateFields() {

    }
}
