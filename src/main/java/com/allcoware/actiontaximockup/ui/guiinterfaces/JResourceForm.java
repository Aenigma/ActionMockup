package com.allcoware.actiontaximockup.ui.guiinterfaces;

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
 * This class is responsible for a form which represents a resource. It may or
 * may not be coupled with the resource and may or may not be capable of
 * managing more than one different type of resource but should only manage one
 * resource at any given time.
 *
 * @author Kevin Raoofi
 * @param <R> the resource handled
 */
public abstract class JResourceForm<R> extends JComponent implements ResourceBuilder<R> {
}
