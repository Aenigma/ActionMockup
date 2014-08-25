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
import com.allcoware.actiontaximockup.resources.RecurringTransaction;
import com.allcoware.actiontaximockup.resources.Transaction;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
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
public class RecurringTransactionConverter implements Converter {

    static final Pattern recurringTransactionPattern;
    private static final Logger LOG = Logger.getLogger(
            RecurringTransactionConverter.class.getName());

    static {
        String[] recurringTransactionPatterns = new String[]{
            "RecurringTransaction\\{",
            ".*?(?=startingInstant=(?<startingInstant>.+?)[,}])",
            ".*?(?=timeToPay=(?<timeToPay>.+?)[,}])",
            ".*?(?=capAmount=(?<capAmount>.+?)[,}])",
            ".*?(?=periodicAmount=(?<periodicAmount>.+?)[,}])",
            ".*?}"
        };

        recurringTransactionPattern = Pattern.compile(Arrays.stream(
                recurringTransactionPatterns)
                .collect(Collectors.joining()));
    }

    private final boolean throwExceptions;
    private final boolean defaultNull;
    private final ConvertUtilsBean cub;

    public RecurringTransactionConverter() {
        this(false, false);
    }

    public RecurringTransactionConverter(boolean throwExceptions,
            boolean defaultNull) {
        this(throwExceptions, defaultNull, AllcowareConverterHelpers
                .getDefaultConvertUtilsBeanInstance(throwExceptions, defaultNull));
    }

    public RecurringTransactionConverter(boolean throwExceptions,
            boolean defaultNull, ConvertUtilsBean cub) {
        this.throwExceptions = throwExceptions;
        this.defaultNull = defaultNull;
        this.cub = cub;
    }

    /**
     * Parses Transactions from a String
     *
     * @param s String representing a collection of {@link Transaction}s
     * @return {@link Collection} of {@link Transaction} objects represented in
     *         the string
     */
    static Collection<RecurringTransaction> parseRecurringTransactions(String s,
            ConvertUtilsBean cub) {
        return AllcowareConverterHelpers.parseObjCollection(s,
                recurringTransactionPattern,
                (objString) -> {
                    return parseRecurringTransaction(objString, cub);
                });
    }

    static RecurringTransaction parseRecurringTransaction(String objString,
            ConvertUtilsBean cub) {

        Matcher recurringTransactionMatcher
                = recurringTransactionPattern.matcher(objString);

        if (!recurringTransactionMatcher.matches()) {
            LOG.log(Level.WARNING,
                    "Didn't match! Got String: {0}",
                    objString);
        }

        RecurringTransaction transaction = new RecurringTransaction();

        transaction.setStartingInstant(
                (Instant) cub.convert(recurringTransactionMatcher
                        .group("startingInstant"),
                        Instant.class));

        transaction.setTimeToPay((Duration) cub.convert(
                recurringTransactionMatcher.group(
                        "timeToPay"), Duration.class));

        transaction.setPeriodicAmount(
                (CustomMoney) cub.convert(
                        recurringTransactionMatcher
                        .group("periodicAmount"), CustomMoney.class));

        transaction.setAmount(
                (CustomMoney) cub.convert(
                        recurringTransactionMatcher
                        .group("capAmount"), CustomMoney.class));
        return transaction;
    }

    @Override
    public <T> T convert(Class<T> type, Object value) {
        try {
            if (!type.equals(RecurringTransaction.class)) {
                throw new ConversionException("Can only convert " + type
                        .getSimpleName() + " classes!");
            }
            return (T) parseRecurringTransaction(value.toString(), this.cub);
        } catch (Exception e) {
            if (throwExceptions) {
                throw new ConversionException(e);
            } else if (defaultNull) {
                return null;
            } else {
                if (type.equals(Collection.class)) {
                    return (T) new ArrayList();
                } else {
                    return (T) new Transaction();
                }
            }
        }
    }

}
