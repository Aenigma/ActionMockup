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
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;

/**
 * This class is to exclusively be used for Converters and to give them
 * Converters that are used in its own fields.
 *
 * N.B. You CANNOT add a {@link Converter} as a default {@link Converter} if the
 * {@link Converter} uses this class to initialize itself. If you do, it will
 * cause a {@link NullPointerException}.
 *
 * @author Kevin Raoofi
 */
class AllcowareConverterHelpers {

    private static final Logger LOG = Logger.getLogger(
            AllcowareConverterHelpers.class.getName());

    private static final Map<Class<?>, Set<Class<?>>> defaultConverters;
    private static final ThreadLocal<Map<Integer, ConvertUtilsBean>> cache;

    static {
        defaultConverters = new HashMap<>();
        defaultConverters.put(CustomMoney.class, new HashSet<>(Arrays.asList(
                CustomMoneyConverter.class)));
        defaultConverters.put(Instant.class, new HashSet<>(Arrays.asList(
                InstantConverter.class)));
        defaultConverters.put(Duration.class, new HashSet<>(Arrays.asList(
                DurationConverter.class)));
        defaultConverters.put(Cab.class, new HashSet<>(Arrays.asList(
                CabConverter.class)));

        cache = new ThreadLocalCUBCache();
    }

    /**
     * A somewhat flexible utility method responsible for finding patterns which
     * match the output of a pattern and delegates processing of every match to
     * a function to construct the object of the specified type. This method
     * collects the results and populates a Collection of the objects.
     *
     * @param <T>              type of object to create
     * @param collStr          string representing a collection of objects
     * @param objStringPattern pattern to find matches within string
     * @param objectGenerator  creates a {@code T} object using the String
     *                         represented in the pattern
     * @return collection of {@code T} objects found in {@code collStr}
     */
    public static <T> Collection<T> parseObjCollection(String collStr,
            Pattern objStringPattern, Function<String, T> objectGenerator) {
        Collection<T> objects = new ArrayList<>();

        Matcher m = objStringPattern.matcher(collStr);

        while (m.find()) {
            // String objString = collStr.substring(m.start(), m.end());
            String objString = m.group();
            objects.add(objectGenerator.apply(objString));
        }

        return objects;
    }

    public static void registerDefaultConverters(ConvertUtilsBean cub,
            boolean throwExceptions, boolean defaultNull) {

//        defaultConverters.forEach((k, v) -> {
//            v.forEach((converter) -> {
//                Object o;
//                try {
//                    Constructor c = converter.getConstructor(Boolean.class,
//                            Boolean.class);
//                    o = c.newInstance(throwExceptions, defaultNull);
//                } catch (ReflectiveOperationException ex1) {
//                    try {
//                        Constructor c = converter.getConstructor();
//                        o = c.newInstance();
//                    } catch (ReflectiveOperationException ex2) {
//                        Logger.getLogger(AllcowareConverterHelpers.class
//                                .getName())
//                                .log(Level.SEVERE,
//                                        "Caught exception processing " + k
//                                        + " with " + converter + "; skipping.",
//                                        ex2);
//                        return;
//                    }
//                }
//                defaultCub.register((Converter) o, k);
//            });
//        });
        cub.register(new CustomMoneyConverter(throwExceptions, defaultNull),
                CustomMoney.class);
        cub.register(new InstantConverter(throwExceptions, defaultNull),
                Instant.class);
        cub.register(new DurationConverter(throwExceptions, defaultNull),
                Duration.class);
        cub.register(new CabConverter(throwExceptions, defaultNull),
                Cab.class);
    }

    public static ConvertUtilsBean getDefaultConvertUtilsBeanInstance() {
        return cache.get().get(0);
    }

    public static ConvertUtilsBean getDefaultConvertUtilsBeanInstance(
            boolean throwExceptions, boolean defaultNull) {
        int key = 0;
        key += throwExceptions ? 1 : 0;
        key += defaultNull ? 2 : 0;
        ConvertUtilsBean cub = cache.get().get(key);

        if (cub == null) {
            cub = new ConvertUtilsBean();
            registerDefaultConverters(cub, throwExceptions, defaultNull);
        }
        return cub;
    }

    private static class ThreadLocalCUBCache extends ThreadLocal<Map<Integer, ConvertUtilsBean>> {

        ConvertUtilsBean defaultCub;

        {
            defaultCub = new ConvertUtilsBean();
            registerDefaultConverters(defaultCub, false, false);
        }

        public ThreadLocalCUBCache() {
        }

        @Override
        protected Map<Integer, ConvertUtilsBean> initialValue() {
            Map<Integer, ConvertUtilsBean> cacheMap = new WeakHashMap<>(4);
            cacheMap.put(0, defaultCub);
            return cacheMap;
        }
    }
}
