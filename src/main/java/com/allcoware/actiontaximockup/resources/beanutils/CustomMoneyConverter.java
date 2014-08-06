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
package com.allcoware.actiontaximockup.resources.beanutils;

import com.allcoware.actiontaximockup.resources.CustomMoney;
import java.math.BigDecimal;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author Kevin Raoofi
 */
public class CustomMoneyConverter implements Converter {

    /**
     * Default to use if {@link #defaultNull} is false
     */
    public static final CustomMoney defaultValue = new CustomMoney(
            BigDecimal.ZERO);

    /**
     * true if the converters should throw an exception when a conversion error
     * occurs, otherwise false if a default value should be used.
     */
    private final boolean throwExceptions;
    /**
     * true if a default value of null should be used, otherwise false. N.B.
     * This values is ignored if throwException is true
     */
    private final boolean defaultNull;

    /**
     * Creates instance with both {@link #throwExceptions} and
     * {@link #defaultNull} set to {@code false}.
     *
     * @see #CustomMoneyConverter(boolean, boolean)
     */
    public CustomMoneyConverter() {
        throwExceptions = false;
        defaultNull = false;
    }

    /**
     * Creates instance with specified flags set.
     *
     * @param throwExceptions true if the converters should throw an exception
     *                        when a conversion error occurs, otherwise false if
     *                        a default value should be used.
     * @param defaultNull     true if a default value of null should be used,
     *                        otherwise false. N.B. This values is ignored if
     *                        throwException is true
     */
    public CustomMoneyConverter(boolean throwExceptions, boolean defaultNull) {
        this.throwExceptions = throwExceptions;
        this.defaultNull = defaultNull;
    }

    @Override
    public <T> T convert(Class<T> type, Object value) {
        try {
            if (!type.equals(CustomMoney.class)) {
                throw new ConversionException(
                        "Can only convert CustomMoney classes!");
            }
            return (T) new CustomMoney(value.toString());
        } catch (Exception e) {
            if (throwExceptions) {
                throw new ConversionException(e);
            } else if (defaultNull) {
                return null;
            } else {
                return (T) defaultValue;
            }
        }
    }
}
