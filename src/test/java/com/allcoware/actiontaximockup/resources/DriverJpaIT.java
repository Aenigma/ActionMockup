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

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
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
 * Driver is the only "complex" class. It is tested it specially here.
 *
 * @author Kevin Raoofi
 */
public class DriverJpaIT {

    static EntityManagerFactory factory = null;
    static final String persistenceUnitName = "MEMORY";
    static Random rand;

    static Cab sampleCab;
    static Driver sampleDriver;
    static RecurringTransaction sampleRecurringTransaction;
    static Transaction sampleTransaction;

    @BeforeClass
    public static void setUpEntityManagerFactory() {
        factory = Persistence
                .createEntityManagerFactory(
                        persistenceUnitName);

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

        sampleCab = new Cab(c);
        sampleDriver = new Driver(d);
        sampleRecurringTransaction = new RecurringTransaction(r);
        sampleTransaction = new Transaction(t);

        rand = new Random();

    }

    @AfterClass
    public static void cleanUpEntityManagerFactory() {
        factory.close();
        factory = null;
        sampleDriver = null;
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

    @Test
    public void testDriverBasic() {
        long id = rand.nextLong();

        Driver o1 = em.find(Driver.class, id);
        assertEquals(null, o1);
        o1 = new Driver();
        o1.setNumber(id);
        em.persist(o1);
        Driver o2 = em.find(Driver.class, id);

        assertEquals(o1, o2);
    }

    @Test
    public void testDriverSample() {
        long id = 103L;

        Driver o1 = em.find(Driver.class, id);
        assertEquals(null, o1);
        o1 = sampleDriver;
        //em.persist(sampleRecurringTransaction);
        //em.persist(sampleTransaction);
        //em.persist(sampleTransaction);
        System.out.println(sampleTransaction.getId() + " " + sampleTransaction);
        //System.out.println(t.getId() + " " + t);
        em.persist(o1);
        
        Driver o2 = em.find(Driver.class, id);

        assertEquals(o1, o2);
    }

    public void customMoneySerialization() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(
                baos);
        CustomMoney c = new CustomMoney(BigDecimal.ZERO);

        oos.writeObject(c);
        oos.close();
        System.out.println(new String(Base64.getEncoder().encode(baos
                .toByteArray())));
        System.out.println(baos.toString().length());
    }
}
