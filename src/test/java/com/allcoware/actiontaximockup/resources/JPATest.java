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
package com.allcoware.actiontaximockup.resources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kevin Raoofi
 */
public class JPATest {

    static EntityManagerFactory factory = null;
    static final String persistenceUnitName = "MEMORY";

    @BeforeClass
    public static void setUpEntityManagerFactory() {
        factory = Persistence
                .createEntityManagerFactory(
                        persistenceUnitName);
    }

    @AfterClass
    public static void cleanUpEntityManagerFactory() {
        factory.close();
        factory = null;
    }

    EntityManager em;
    EntityTransaction et;

    @Before
    public void setUpEntityManager() {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        et = em.getTransaction();
    }

    @After
    public void cleanUpEntityManager() {
        if (et.isActive()) {
            et.commit();
        }
        em.close();
        em = null;
    }

    /**
     * Test is successful if setup and cleanup methods are successful
     */
    @Test
    public void testBasicFunctionality() {
    }

}
