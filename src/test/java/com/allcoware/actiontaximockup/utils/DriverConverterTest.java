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

import com.allcoware.actiontaximockup.resources.Cab;
import com.allcoware.actiontaximockup.resources.CustomMoney;
import com.allcoware.actiontaximockup.resources.Driver;
import com.allcoware.actiontaximockup.resources.RecurringTransaction;
import com.allcoware.actiontaximockup.resources.Transaction;
import static com.allcoware.actiontaximockup.utils.DriverConverter.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kevin Raoofi
 */
public class DriverConverterTest {

    Transaction t;
    RecurringTransaction r;
    Driver d;
    static DriverConverter dc;
    static DriverConverter dc2;

    @BeforeClass
    public static void setUpBeforeClass() {
        dc = new DriverConverter();
        dc2 = new DriverConverter(false, true);
    }

    @AfterClass
    public static void cleanUpAfterClass() {
        dc = dc2 = null;
    }

    @Before
    public void setUp() {
        t = new Transaction();
        t.setAmount(new CustomMoney(BigDecimal.ZERO));
        t.setInstant(Instant.MIN);

        r = new RecurringTransaction(Instant.MIN,
                Duration.ZERO,
                new CustomMoney(BigDecimal.ZERO),
                new CustomMoney(
                        BigDecimal.ZERO));

        d = new Driver();
        d.setCab(new Cab());
        d.setFirstName("");
        d.setMiddleName("");
        d.setLastName("");
        d.setMoney(new CustomMoney(BigDecimal.ZERO));
        d.setNumber(0L);
        d.setPhone("");
        d.setRecurringTransactions(Arrays.asList(r));
        d.setTransactions(Arrays.asList(t));
    }

    /**
     * Test of convert method, of class DriverConverter.
     */
    @Test
    public void testConvert() {
        System.out.println("testConvert");
        Driver d2 = dc.convert(Driver.class, d.toString());
        System.out.println(d);
        System.out.println(d2);
        assertEquals(d, d2);
    }

    @Test
    public void testJSON() throws Exception {
        System.out.println("testJSON");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        Driver d2 = mapper.readValue(mapper.writeValueAsBytes(d), Driver.class);
        System.out.println(d);
        System.out.println(d2);
        JsonNode jn = mapper.valueToTree(d);
        printJSON(jn);
        assertEquals(d, d2);
    }

    public void printJSON(JsonNode rn) {

        if (rn.isObject()) {
            Iterator<Entry<String, JsonNode>> jniter = rn.fields();
            while (jniter.hasNext()) {
                Entry<String, JsonNode> en = jniter.next();
                System.out.print(en.getKey() + ":");
                printJSON(en.getValue());
            }
        } else {
            System.out.println(rn);
        }
    }

    @Test
    public void testTransactionPattern() {
        System.out.println(TransactionConverter.transactionPattern);
        System.out.println(new Transaction().toString());
        Matcher m = TransactionConverter.transactionPattern.matcher(
                new Transaction().toString());
        assertEquals("transactionPattern does not match a blank transaction",
                true, m.matches());

        m = TransactionConverter.transactionPattern.matcher(t.toString());
        assertEquals("transactionPattern does not match sample transaction",
                true, m.matches());

    }

    @Test
    public void testRecurringTransactionPattern() {
        System.out.println(
                RecurringTransactionConverter.recurringTransactionPattern);
        System.out.println(new RecurringTransaction().toString());
        Matcher m = RecurringTransactionConverter.recurringTransactionPattern
                .matcher(
                        new RecurringTransaction().toString());
        assertEquals(
                "recurringtransactionpattern does not match a blank transaction",
                true, m.matches());

        m = RecurringTransactionConverter.recurringTransactionPattern.matcher(
                r.toString());

        assertEquals(
                "recurringtransactionpattern does not match sample transaction",
                true, m.matches());
    }

    @Test
    public void testDriverPattern() {
        System.out.println(driverPattern);
        System.out.println(d);
        System.out.println(new Driver());

        Matcher m = driverPattern.matcher(
                d.toString());

        assertEquals(
                "driverPatterndoes not match a sample Driver",
                true, m.matches());

        m = driverPattern.matcher(
                new Driver().toString());

        assertEquals(
                "driverPattern does not match empty Driver",
                true, m.matches());
    }

    @Test
    public void testParseTransactions() {
        Matcher m = driverPattern.matcher(
                d.toString());
        m.matches();

        String result = m.group("transactions");
        System.out.println(result);

        assertArrayEquals(
                "Transactions collection from sample driver fails to match",
                d.getTransactions().toArray(),
                dc.parseTransactions(result).toArray());

        Collection<Transaction> transactions = new ArrayList<>();
        Transaction t2 = new Transaction();
        t2.setAmount(new CustomMoney("200"));
        t2.setInstant(Instant.now());
        transactions.add(t);
        transactions.add(t2);

        assertArrayEquals("Non-null values fail to create equivalent copies",
                transactions.toArray(),
                dc.parseTransactions(transactions.toString()).toArray());

        transactions = new ArrayList<>();
        transactions.add(t);
        transactions.add(t2);
        transactions.add(new Transaction());

        assertArrayEquals(
                "Transaction with null values fail to create equivalent copies",
                transactions.toArray(),
                dc2.parseTransactions(transactions.toString()).toArray());
    }

    @Test
    public void testParseRecurringTransactions() {
        Matcher m = driverPattern.matcher(
                d.toString());

        m.matches();

        String result = m.group("recurringTransactions");
        System.out.println(result);

        assertArrayEquals(
                "RecurringTransactions collection from sample driver fails to match",
                d.getRecurringTransactions().toArray(),
                dc.parseRecurringTransactions(result).toArray());

        Collection<RecurringTransaction> transactions = new ArrayList<>();
        RecurringTransaction r2 = new RecurringTransaction();
        r2.setAmount(new CustomMoney("456"));
        r2.setPeriodicAmount(new CustomMoney("6546"));
        r2.setStartingInstant(Instant.MIN);
        r2.setTimeToPay(Duration.ZERO);
        transactions.add(r);
        transactions.add(r2);

        assertArrayEquals("Non-null values fail to create equivalent copies",
                transactions.toArray(),
                dc.parseRecurringTransactions(transactions.toString()).toArray());

        transactions = new ArrayList<>();
        transactions.add(r);
        transactions.add(r2);
        transactions.add(new RecurringTransaction());

        assertArrayEquals(
                "RecurringTransaction with null values fail to create "
                + "equivalent copies",
                transactions.toArray(),
                dc2.parseRecurringTransactions(transactions.toString())
                .toArray());
    }

}
