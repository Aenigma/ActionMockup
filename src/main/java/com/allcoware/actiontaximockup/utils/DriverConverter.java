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
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author Kevin Raoofi
 */
public class DriverConverter implements Converter {

    static final Pattern driverPattern;

    private static final Logger LOG = Logger.getLogger(DriverConverter.class
            .getName());

    static {
        String[] driverPatterns = new String[]{
            "Driver\\{",
            ".*?(?=number=(?<number>\\d+?)[,}])",
            ".*?(?=cab=(?<cab>.+?[,}]))",
            ".*?(?=money=(?<money>.+?)[,}])",
            ".*?(?=firstName=(?<firstName>.*?)[,}])",
            ".*?(?=middleName=(?<middleName>.*?)[,}])",
            ".*?(?=lastName=(?<lastName>.*?)[,}])",
            ".*?(?=phone=(?<phone>.*?)[,}])",
            ".*?(?=transactions=(?<transactions>\\[.*?][,}]))",
            ".*?(?=recurringTransactions=(?<recurringTransactions>\\[.*])[,}])",
            ".*?]}"
        };

        driverPattern = Pattern.compile(Arrays.stream(driverPatterns)
                .collect(Collectors.joining()));
    }

    private final boolean throwExceptions;
    private final boolean defaultNull;
    private final ConvertUtilsBean cub;

    public DriverConverter() {
        this(false, false);
    }

    public DriverConverter(boolean throwExceptions,
            boolean defaultNull) {
        this(throwExceptions, defaultNull, AllcowareConverterHelpers
                .getDefaultConvertUtilsBeanInstance(throwExceptions, defaultNull));
    }

    public DriverConverter(boolean throwExceptions, boolean defaultNull,
            ConvertUtilsBean cub) {
        this.throwExceptions = throwExceptions;
        this.defaultNull = defaultNull;
        this.cub = cub;
    }

    @Override
    public <T> T convert(Class<T> type, Object value) {
        try {
            if (!type.equals(Driver.class)) {
                throw new ConversionException("Can only convert " + type
                        .getSimpleName() + " classes!");
            }

            return (T) stringToDriver(value.toString());
        } catch (Exception e) {
            if (throwExceptions) {
                throw new ConversionException(e);
            } else if (defaultNull) {
                return null;
            } else {
                return (T) new Driver();
            }
        }
    }

    Driver stringToDriver(String driverStr) {
        Driver d = new Driver();
        Matcher m = driverPattern.matcher(driverStr);
        m.matches();
        d.setNumber((long) cub.convert(m.group("number"), Long.TYPE));
        d.setCab((Cab) cub.convert(m.group("cab"), Cab.class));
        d.setMoney((CustomMoney) cub
                .convert(m.group("money"), CustomMoney.class));
        d.setFirstName(m.group("firstName"));
        d.setMiddleName(m.group("middleName"));
        d.setLastName(m.group("lastName"));
        d.setPhone(m.group("phone"));
        d.setTransactions(parseTransactions(m.group("transactions")));
        d.setRecurringTransactions(parseRecurringTransactions(m.group(
                "recurringTransactions")));
        return d;
    }

    /**
     * Parses Transactions from a String
     *
     * @param s String representing a collection of {@link Transaction}s
     * @return {@link Collection} of {@link Transaction} objects represented in
     *         the string
     */
    Collection<Transaction> parseTransactions(String s) {
        return TransactionConverter.parseTransactions(s, this.cub);
    }

    /**
     * Parses Transactions from a String
     *
     * @param s String representing a collection of {@link Transaction}s
     * @return {@link Collection} of {@link Transaction} objects represented in
     *         the string
     */
    Collection<RecurringTransaction> parseRecurringTransactions(String s) {
        return RecurringTransactionConverter.parseRecurringTransactions(s,
                this.cub);
    }

}
