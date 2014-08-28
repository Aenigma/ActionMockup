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

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Kevin Raoofi
 */
@RunWith(Parameterized.class)
public class JpaIT<K, T> {

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

    final Class<T> clazz;
    final T defaultObj;
    final K key;

    EntityManager em;
    EntityTransaction et;

    public JpaIT(Class<T> clazz, T defaultObj, K key) {
        this.clazz = clazz;
        this.defaultObj = defaultObj;
        this.key = key;
    }

    @Before
    public void setUpEntityManager() {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        et = em.getTransaction();
    }

    @After
    public void cleanUpEntityManager() {
        et.setRollbackOnly();
        if (et.getRollbackOnly()) {
            et.rollback();
        }
        if (et.isActive()) {
            et.commit();
        }
        em.close();
        em = null;
    }

    @Parameters
    public static Collection<Object[]> constructorParameters() {
        Collection<Object[]> params = new ArrayList<>();

        Cab c = new Cab();
        c.setCabID(100L);

        Transaction t = new Transaction();
        t.setAmount(new CustomMoney(BigDecimal.ZERO));
        t.setInstant(Instant.now());
        //t.setId(101L);

        RecurringTransaction r = new RecurringTransaction(Instant.now(),
                Duration.ZERO,
                new CustomMoney(BigDecimal.ZERO),
                new CustomMoney(
                        BigDecimal.ZERO));
        //r.setId(102L);

        Cab c2 = new Cab();
        c2.setCabID(101L);

        Driver d = new Driver();
        d.setNumber(103L);
        d.setCab(c2);
        d.setFirstName("");
        d.setMiddleName("");
        d.setLastName("");
        d.setMoney(new CustomMoney(BigDecimal.ZERO));
        d.setPhone("");
        d.setRecurringTransactions(Arrays.asList(r));
        d.setTransactions(Arrays.asList(t));

        params.add(new Object[]{Cab.class, new Cab(c), c.getCabID()});
        params.add(
                new Object[]{Transaction.class, new Transaction(t), t.getId()});
        params.add(new Object[]{RecurringTransaction.class,
            new RecurringTransaction(r), r.getId()});
        params.add(new Object[]{Driver.class, new Driver(d), d.getNumber()});

        return params;
    }

    /**
     * Test is successful if setup and cleanup methods are successful
     */
    public void testBasicFunctionality() {
    }

    public void testCab() {
        Cab c1 = em.find(Cab.class, 158L);
        assertEquals(null, c1);
        c1 = new Cab();
        c1.setCabID(158L);
        em.persist(c1);
        Cab c2 = em.find(Cab.class, 158L);

        assertEquals(c1, c2);
    }

    @Test
    public void test() {
        System.out.println(clazz + " " + key + " " + defaultObj);
        T o1 = em.find(clazz, key);
        assertEquals(null, o1);
        o1 = defaultObj;

        em.persist(o1);

        T o2 = em
                .find(clazz, factory.getPersistenceUnitUtil().getIdentifier(o1));

        assertEquals(o1, o2);

    }
}
