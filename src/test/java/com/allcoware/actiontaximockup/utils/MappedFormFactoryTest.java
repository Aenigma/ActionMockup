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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import com.allcoware.actiontaximockup.ResourceBuilder;
import java.util.TreeMap;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author Kevin Raoofi
 */
public class MappedFormFactoryTest {

    MappedFormFactory<String, ? extends ResourceBuilder<String>> mff;

    @Before
    public void setup() {
        mff = new MappedFormFactory<>(StringResourceBuilderImpl::new,
                String::new);
    }

    @After
    public void cleanup() {
        mff = null;
    }

    /**
     * Test of getForm method, of class MappedFormFactory.
     */
    @Test
    public void testGetForm() {
        ResourceBuilder<String> rb = mff.getForm(0L);
        assertNull(rb);
    }

    /**
     * Test of makeNewForm method, of class MappedFormFactory.
     */
    @Test
    public void testMakeNewForm() {

        ResourceBuilder<String> rb = mff.makeNewForm();
        assertEquals("", rb.getResource());
    }

    /**
     * Test of getMap method, of class MappedFormFactory.
     */
    @Test
    public void testGetMap() {
        assertThat(mff.getMap(), is(new TreeMap<>()));
    }

    /**
     * Test of remove method, of class MappedFormFactory.
     */
    @Test
    public void testRemove() {
        mff.makeNewForm();
        mff.makeNewForm();
        mff.remove(0L);
        assertThat(mff.exists(0L), is(false));
        assertThat(mff.exists(1L), is(true));
    }

    /**
     * Test of exists method, of class MappedFormFactory.
     */
    @Test
    public void testExists() {
        assertThat(mff.exists(0L), is(false));
        mff.makeNewForm();
        assertThat(mff.exists(0L), is(true));
    }

    private static class StringResourceBuilderImpl implements ResourceBuilder<String> {

        private final String s;

        public StringResourceBuilderImpl(String s) {
            this.s = s;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public String getResource() {
            return s;
        }
    }

}
