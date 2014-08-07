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

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author Kevin Raoofi
 */
public class InstantConverter implements Converter {

    public static final DateTimeFormatter defaultDateTimeStampFormat
            = DateTimeFormatter.ISO_INSTANT;

    private final DateTimeFormatter timeStampFormat;

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

    public InstantConverter() {
        this(defaultDateTimeStampFormat);
    }

    public InstantConverter(boolean throwExceptions, boolean defaultNull) {
        this(defaultDateTimeStampFormat, throwExceptions, defaultNull);
    }

    public InstantConverter(DateTimeFormatter timeStampFormatter) {
        this(timeStampFormatter, false, false);
    }

    /**
     * Creates instance with specified flags set and a custom date time
     * formatter.
     *
     * If {@code defaultNull} is set to false, the default value used here is
     * the current date and time, truncated to the day.
     *
     * @param timeStampFormatter format used to convert date time to and from a
     *                           String
     *
     * @param throwExceptions    true if the converters should throw an
     *                           exception when a conversion error occurs,
     *                           otherwise false if a default value should be
     *                           used.
     * @param defaultNull        true if a default value of null should be used,
     *                           otherwise false. N.B. This values is ignored if
     *                           throwException is true
     */
    public InstantConverter(DateTimeFormatter timeStampFormatter,
            boolean throwExceptions, boolean defaultNull) {
        this.timeStampFormat = timeStampFormatter;
        this.throwExceptions = throwExceptions;
        this.defaultNull = defaultNull;
    }

    @Override
    public <T> T convert(Class<T> type, Object value) {
        try {
            if (!type.equals(Instant.class)) {
                throw new ConversionException(
                        "Can only convert java.time.Instant objects");
            }

            Instant result = Instant.from(defaultDateTimeStampFormat.parse(value
                    .toString()));
            return (T) result;
        } catch (Exception e) {
            if (throwExceptions) {
                throw new ConversionException(e);
            } else if (defaultNull) {
                return null;
            } else {
                return (T) Instant.now().truncatedTo(
                        ChronoUnit.DAYS);
            }
        }
    }

}
