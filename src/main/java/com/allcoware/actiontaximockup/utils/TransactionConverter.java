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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;

/**
 *
 * @author Kevin Raoofi
 */
public class TransactionConverter implements Converter {

    static final Pattern transactionPattern;
    private static final Logger LOG = Logger.getLogger(
            TransactionConverter.class.getName());

    static {
        String[] transactionPatterns = new String[]{
            "Transaction\\{",
            ".*?(?=transactionInstant=(?<transactionInstant>.+?)[,}])",
            ".*?(?=amount=(?<amount>.+)[,}])",
            ".*?}"
        };
        transactionPattern = Pattern.compile(Arrays.stream(transactionPatterns)
                .collect(Collectors.joining()));
    }

    private final boolean throwExceptions;
    private final boolean defaultNull;
    private final ConvertUtilsBean cub;

    public TransactionConverter() {
        this(false, false);
    }

    public TransactionConverter(boolean throwExceptions,
            boolean defaultNull) {
        this(throwExceptions, defaultNull, AllcowareConverterHelpers
                .getDefaultConvertUtilsBeanInstance(throwExceptions, defaultNull));
    }

    public TransactionConverter(boolean throwExceptions, boolean defaultNull,
            ConvertUtilsBean cub) {
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
    static Collection<Transaction> parseTransactions(String s,
            ConvertUtilsBean cub) {
        return AllcowareConverterHelpers.parseObjCollection(s,
                transactionPattern,
                (objString) -> {
                    return parseTransaction(objString, cub);
                });
    }

    static Transaction parseTransaction(String objString, ConvertUtilsBean cub) {
        Matcher transactionMatcher = transactionPattern
                .matcher(objString);

        if (!transactionMatcher.matches()) {
            LOG.log(Level.WARNING,
                    "Didn't match! Got String: {0}",
                    objString);
        }

        Transaction transaction = new Transaction();
        transaction.setInstant(
                (Instant) cub.convert(transactionMatcher
                        .group("transactionInstant"),
                        Instant.class));

        transaction.setAmount(
                (CustomMoney) cub.convert(transactionMatcher
                        .group("amount"), CustomMoney.class));
        return transaction;

    }

    @Override
    public <T> T convert(Class<T> type, Object value) {
        try {
//            if (type.isAssignableFrom(Collection.class)) {
//                return (T) parseTransactions(value.toString(), this.cub);
//            } 
            if (!type.equals(Transaction.class)) {
                throw new ConversionException("Can only convert " + type
                        .getSimpleName() + " classes!");
            }
            return (T) parseTransaction(value.toString(), this.cub);
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

    public static class TransactionsConverter extends ArrayConverter {

        private final ConvertUtilsBean cub;
        
        public TransactionsConverter(boolean throwExceptions,
                boolean defaultNull, ConvertUtilsBean cub) {
            super(Transaction[].class, new TransactionConverter(throwExceptions,
                    defaultNull, cub));
            this.cub = cub;
        }

        @Override
        protected Collection<?> convertToCollection(
                Class<?> type, Object value) {
            
            if (value instanceof Collection || value instanceof Number
                    || value instanceof Boolean
                    || value instanceof Date) {
                return super.convertToCollection(type, value);
            }
            return parseTransactions(value.toString(), cub);
        }

    }
}
