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

import com.allcoware.actiontaximockup.resources.CustomMoney;
import com.allcoware.actiontaximockup.resources.Transaction;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import org.apache.commons.beanutils.ConvertUtilsBean;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Kevin Raoofi
 */
public class TransactionConverterTest {

    ConvertUtilsBean cub;

    public TransactionConverterTest() {
        cub = new ConvertUtilsBean();
        AllcowareConverterHelpers.registerDefaultConverters(cub, false, false);
        cub.register(new TransactionConverter.TransactionsConverter(true, true,
                cub), Transaction[].class);
    }

    /**
     * Test of convert method, of class TransactionConverter.
     */
    @Test
    public void testConvert() {

        Transaction t = new Transaction();
        t.setAmount(new CustomMoney(BigDecimal.ZERO));
        t.setInstant(Instant.MIN);
        Object[] o = (Object[]) cub.convert(Arrays.asList(t).toString(), Transaction[].class);
        System.out.println(Arrays.toString(o));
    }

}
