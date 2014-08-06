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
package com.allcoware.actiontaximockup.helpers;

import java.util.function.BiFunction;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author Kevin Raoofi
 */
public class BeanTableModelFactory {

    private static final Logger LOG = Logger.getLogger(
            BeanTableModelFactory.class
            .getName());

    private static BiFunction<Class<?>, BeanUtilsBean, BeanTableModel<?>> defaultFactory
            = (clazz, bub) -> new BeanTableModelImpl<>(clazz, bub);

    private final BeanUtilsBean bub;
    private final BiFunction<Class<?>, BeanUtilsBean, BeanTableModel<?>> factory;

    public BeanTableModelFactory(BeanUtilsBean bub) {
        this(bub, defaultFactory);
    }
    
    public BeanTableModelFactory() {
        this(BeanUtilsBean.getInstance(), defaultFactory);
    }

    public BeanTableModelFactory(BeanUtilsBean bub,
            BiFunction<Class<?>, BeanUtilsBean, BeanTableModel<?>> factory) {
        this.bub = bub;
        this.factory = factory;
    }

    public void registerConverter(Converter con, Class<?> clazz) {
        this.bub.getConvertUtils().register(con, clazz);
    }

    public <R> BeanTableModel<R> get(Class<R> resourceClass) {
        return (BeanTableModel<R>) this.factory.apply(resourceClass, bub);
    }
}
