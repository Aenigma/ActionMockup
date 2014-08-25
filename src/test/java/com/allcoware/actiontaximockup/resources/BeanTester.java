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

import com.allcoware.actiontaximockup.utils.CabConverter;
import com.allcoware.actiontaximockup.utils.CustomMoneyConverter;
import com.allcoware.actiontaximockup.utils.DriverConverter;
import com.allcoware.actiontaximockup.utils.InstantConverter;
import com.allcoware.actiontaximockup.utils.RecurringTransactionConverter;
import com.allcoware.actiontaximockup.utils.TransactionConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class BeanTester<T> {

    static BeanUtilsBean bub;
    static ObjectMapper om;

    @BeforeClass
    public static void initializeClass() {
        bub = new BeanUtilsBean();
        ConvertUtilsBean cub = bub.getConvertUtils();
        cub.register(false, false, -1);
        cub.register(new CustomMoneyConverter(), CustomMoney.class);
        cub.register(new InstantConverter(), Instant.class);
        cub.register(new CabConverter(), Cab.class);
        cub.register(new DriverConverter(), Driver.class);
        cub.register(new TransactionConverter(), Transaction.class);
        cub.register(new RecurringTransactionConverter(),
                RecurringTransaction.class);

        om = new ObjectMapper();
        om.registerModule(new JSR310Module());
    }

    @AfterClass
    public static void cleanUpClass() {
        bub = null;
    }

    @Parameters
    public static Collection<Object[]> constructorParameters() {
        Collection<Object[]> params = new ArrayList<>();

        Transaction t = new Transaction();
        t.setAmount(new CustomMoney(BigDecimal.ZERO));
        t.setInstant(Instant.MIN);

        RecurringTransaction r = new RecurringTransaction(Instant.MIN,
                Duration.ZERO,
                new CustomMoney(BigDecimal.ZERO),
                new CustomMoney(
                        BigDecimal.ZERO));

        Driver d = new Driver();
        d.setCab(new Cab());
        d.setFirstName("");
        d.setMiddleName("");
        d.setLastName("");
        d.setMoney(new CustomMoney(BigDecimal.ZERO));
        d.setNumber(0L);
        d.setPhone("");
        d.setRecurringTransactions(Arrays.asList(r));
        d.setTransactions(Arrays.asList(t));

        //params.add(new Object[]{CustomMoney.class, new CustomMoney("10000")});
        params.add(new Object[]{Cab.class, new Cab()});
        params.add(new Object[]{Driver.class, d});
        params.add(new Object[]{
            RecurringTransaction.class, r});
        params.add(new Object[]{Transaction.class, t});

        return params;
    }

    Class<T> clazz;
    T defaultObj;
    PropertyDescriptor[] pds;

    public BeanTester(Class<T> clazz, T defaultObj) {
        this.pds = PropertyUtils.getPropertyDescriptors(clazz);
        this.clazz = clazz;
        this.defaultObj = defaultObj;
    }

//    @Test
//    @Ignore
//    public void testStringCloneableNormal() throws Exception {
//        System.out.println("Testing " + clazz.toString());
//        Map<String, String> pmap = new HashMap<>();
//        for (PropertyDescriptor pd : pds) {
//            pmap.put(pd.getName(), bub.getSimpleProperty(defaultObj, pd
//                    .getName()));
//        }
//
//        T actual = clazz.newInstance();
//        bub.populate(actual, pmap);
//
//        assertEquals(defaultObj, actual);
//    }
    @Test
    public void testConvertable() throws Exception {
        System.out.println("Testing " + clazz.toString());
        T clone = (T) bub.getConvertUtils()
                .convert(defaultObj.toString(), clazz);
        assertEquals(defaultObj, clone);
    }

    @Test
    public void testJSON() throws JsonProcessingException, IOException {
        System.out.println("Testing " + clazz.toString());

        String s = om.writeValueAsString(defaultObj);
        T obj = om.readValue(s, (Class<T>) defaultObj.getClass());
        System.out.println(s);
        System.out.println(obj);

        assertEquals(defaultObj, obj);

    }
}
