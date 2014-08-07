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

import com.allcoware.actiontaximockup.utils.MappedFormFactory;
import com.allcoware.actiontaximockup.resources.Cab;
import com.allcoware.actiontaximockup.ui.resources.CabForm;
import com.allcoware.actiontaximockup.ui.JResourceForm;

/**
 *
 * @author alfred
 * @author Kevin Raoofi
 */
public class CabFormFactory extends MappedFormFactory<Cab, JResourceForm<Cab>> {
    public CabFormFactory() {
        super(CabForm::new, Cab::new);
    }
}
