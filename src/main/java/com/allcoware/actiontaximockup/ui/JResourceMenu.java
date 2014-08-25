package com.allcoware.actiontaximockup.ui;

import com.allcoware.actiontaximockup.ResourceFormFactory;
import javax.swing.JComponent;

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
 * This class is the main entry point for manipulating a resource. It should be
 * able to generate
 *
 * This class is incomplete. You should extend this class and add common
 * features. However, I do encourage the use of a {@link ResourceFormFactory}.
 *
 * @author Kevin Raoofi
 * @param <R> resource being managed
 */
public abstract class JResourceMenu<K, R> extends JComponent {

    /**
     * Creates forms to add/remove objects
     */
    protected final ResourceFormFactory<K, R> formFactory;

    /**
     * Constructor to initialize the form factory
     *
     * @param formFactory the factory to use for this instance
     */
    protected JResourceMenu(ResourceFormFactory<K, R> formFactory) {
        this.formFactory = formFactory;
        
        
        
    }

}
