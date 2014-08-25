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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author Kevin Raoofi
 */
public class CabConverter implements Converter {

    private static final Pattern ptnCab = Pattern.compile("cabID=(?<cabid>\\d+)");
    private final boolean throwExceptions;
    private final boolean defaultNull;

    public CabConverter() {
        throwExceptions = false;
        defaultNull = false;
    }

    public CabConverter(boolean throwExceptions, boolean defaultNull) {
        this.throwExceptions = throwExceptions;
        this.defaultNull = defaultNull;
    }

    @Override
    public <T> T convert(Class<T> type, Object value) {
        try {
            if (!type.equals(Cab.class)) {
                throw new ConversionException("Can only convert " + type
                        .getSimpleName() + " classes!");
            }

            Cab c = new Cab();
            String cabStr = value.toString();
            Matcher cabMatcher = ptnCab.matcher(cabStr);
            c.setCabID(Long.parseLong(cabMatcher.group("cabid")));
            return (T) c;
        } catch (Exception e) {
            if (throwExceptions) {
                throw new ConversionException(e);
            } else if (defaultNull) {
                return null;
            } else {
                return (T) new Cab();
            }
        }
    }

}
