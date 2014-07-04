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

import java.util.Map;

/**
 *
 * @author alfred
 */
public class RecurringTransactionFormFactory implements ResourceFormFactory {

    private Map<Long, com.allcoware.actiontaximockup.RecurringTransaction> formMap;

    //this method should use "Long" not object but it won't except it... 
    public JResourceForm<RecurringTransactionForm> getForm(Object key) {
        return null;
    }

    public JResourceForm<RecurringTransactionForm> makeNewForm() {
        return null;
    }
}
