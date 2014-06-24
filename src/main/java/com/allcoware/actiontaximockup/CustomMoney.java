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
package com.allcoware.actiontaximockup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

/**
 * <p>
 * The best way to get started with a project is to introduce legacy code. This
 * class is not good code. It is a wrapper around an instance of
 * {@code BigDecimal} and provides little value on top of it.
 * </p>
 * <p>
 * I expect this class to be considered unstable and eventually thrown out for a
 * better implementation using a standard library of sorts. It was either this
 * or using one of several third-party library that I couldn't choose between.
 * </p>
 * <p>
 * However, because of that intention, using {@code BigDecimal} directly may
 * create complications later whereas using this wrapper, which provides very
 * little functionality, may make it slightly easier to port later.
 * </p>
 * <p>
 * This class is {@code final} which means that it should not be extended.
 * Additionally, it is immutable as is the {@code BigDecimal} that is being
 * used.
 * </p>
 *
 * @author Kevin Raoofi
 */
public final class CustomMoney implements Comparable<CustomMoney> {

    /**
     * We only care about US Dollars
     */
    private static final Currency usd = Currency.getInstance(Locale.US);

    private static final int scale = 2;
    private final BigDecimal amount;

    /**
     * Creates an instance of {@link CustomMoney}
     *
     * @param val String representation of the money value.
     */
    public CustomMoney(String val) {
        amount = new BigDecimal(val).setScale(scale, RoundingMode.HALF_EVEN);
    }

    /**
     * Creates an instance of {@link CustomMoney}.
     *
     * @param amount the BigDecimal amount to use internally
     */
    public CustomMoney(BigDecimal amount) {
        /*
         * Uses the standardized scale to be compatible with other instances of
         * CustomMoney. This may mean, however, that the instance's internal
         * BigDecimal does not equal the original BigDecimal.
         */
        this.amount = amount.setScale(scale, RoundingMode.HALF_EVEN);
    }

    /**
     * Adds two {@link CustomMoney} instances and returns another instance with
     * the result.
     *
     * @param o Other instance of {@link CustomMoney}
     * @return new instance of {@link CustomMoney} with results
     */
    public CustomMoney add(CustomMoney o) {
        return add(o.amount);
    }

    /**
     * Adds the instance using a {@code BigDecimal} and returns another instance
     * with the result.
     *
     * @param o The BigDecimal to add with
     * @return new instance of {@link CustomMoney} with results
     */
    public CustomMoney add(BigDecimal o) {
        return new CustomMoney(this.amount.add(o));
    }

    /**
     * Subtracts two {@link CustomMoney} instances and returns another instance
     * with the result.
     *
     * @param o Other instance of {@link CustomMoney}
     * @return new instance of {@link CustomMoney} with results
     */
    public CustomMoney subtract(CustomMoney o) {
        return this.subtract(o.amount);
    }

    /**
     * Subtracts the instance using a {@code BigDecimal} and returns another
     * instance with the result.
     *
     * @param o The BigDecimal to subtract by
     * @return new instance of {@link CustomMoney} with results
     */
    public CustomMoney subtract(BigDecimal o) {
        return new CustomMoney(this.amount.subtract(o));
    }

    /**
     * Multiplies the instance using a {@code BigDecimal} and returns another
     * instance with the result.
     *
     * @param o The BigDecimal to multiply with
     * @return new instance of {@link CustomMoney} with results
     */
    public CustomMoney multiply(BigDecimal o) {
        return new CustomMoney(this.amount.multiply(o));
    }

    /**
     * Divides the instance using a {@code BigDecimal} and returns another
     * instance with the result.
     *
     * @param o The BigDecimal to divide by
     * @return new instance of {@link CustomMoney} with results
     */
    public CustomMoney divide(BigDecimal o) {
        return new CustomMoney(this.amount.divide(o, RoundingMode.HALF_EVEN));
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomMoney other = (CustomMoney) obj;
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder rst = new StringBuilder();
        rst.append(usd.getSymbol());
        rst.append(amount.toString());
        return rst.toString();
    }

    @Override
    public int compareTo(CustomMoney o) {
        return this.amount.compareTo(o.amount);
    }
}
