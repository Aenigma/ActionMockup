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

import com.allcoware.actiontaximockup.utils.CustomMoneyConverter;
import com.allcoware.actiontaximockup.utils.InstantConverter;
import java.beans.PropertyDescriptor;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kevin Raoofi
 */
public class TransactionTest {

    public static ConvertUtilsBean cub;
    public static BeanUtilsBean bub;

    public TransactionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        cub = new ConvertUtilsBean();
        bub = new BeanUtilsBean(cub);
        cub.register(new CustomMoneyConverter(), CustomMoney.class);
        cub.register(new InstantConverter(), Instant.class);
    }

    @AfterClass
    public static void tearDownClass() {
        cub = null;
        bub = null;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBean() throws Exception {
        Transaction t = new Transaction();
        t.setAmount(new CustomMoney("20"));
        t.setInstant(Instant.ofEpochMilli(10000));
        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(t);
        Map<String, String> pmap = new HashMap<>();
        for (PropertyDescriptor pd : pds) {
            pmap.put(pd.getName(), bub.getSimpleProperty(t, pd.getName()));
        }

        Transaction o = Transaction.class.newInstance();
        bub.populate(o, pmap);

        assertEquals(t, o);
    }

}
