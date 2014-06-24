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
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kevin Raoofi
 */
public class CustomMoneyTest {
    
    CustomMoney main;
    
    public CustomMoneyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         main = new CustomMoney(BigDecimal.TEN);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class CustomMoney.
     */
    @Test
    public void testAdd_CustomMoney() {
        System.out.println("add");
        CustomMoney o = new CustomMoney("100");
        CustomMoney expResult = new CustomMoney("110");
        CustomMoney result = main.add(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class CustomMoney.
     */
    @Test
    public void testAdd_BigDecimal() {
        System.out.println("add");
        BigDecimal o = new BigDecimal("10000");
        CustomMoney expResult = new CustomMoney("10010");
        CustomMoney result = main.add(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of subtract method, of class CustomMoney.
     */
    @Test
    public void testSubtract_CustomMoney() {
        System.out.println("subtract");
        CustomMoney o = new CustomMoney("100");
        CustomMoney expResult = new CustomMoney("-90");
        CustomMoney result = main.subtract(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of subtract method, of class CustomMoney.
     */
    @Test
    public void testSubtract_BigDecimal() {
        System.out.println("subtract");
        BigDecimal o = new BigDecimal(-90);
        CustomMoney expResult = new CustomMoney("100");
        CustomMoney result = main.subtract(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class CustomMoney.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        BigDecimal o = new BigDecimal("2.556789");
        CustomMoney expResult = new CustomMoney("25.57");
        CustomMoney result = main.multiply(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of divide method, of class CustomMoney.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        BigDecimal o = new BigDecimal("2.556789");
        CustomMoney expResult = new CustomMoney("3.911155750435409");
        CustomMoney result = main.divide(o);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class CustomMoney.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj1 = new CustomMoney("33.55");
        CustomMoney instance1 = new CustomMoney("33.60");
        boolean expResult1 = false;
        boolean result1 = instance1.equals(obj1);
        assertEquals(expResult1, result1);
        
        Object obj2 = new CustomMoney("00019.55");
        CustomMoney instance2 = new CustomMoney("019.550");
        boolean expResult2 = true;
        boolean result2 = instance2.equals(obj2);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of toString method, of class CustomMoney.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "$10.00";
        String result = main.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class CustomMoney.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        CustomMoney o = new CustomMoney("20");
        Integer result = main.compareTo(o);
        assertThat(result, org.hamcrest.CoreMatchers.is(new BaseMatcher<Integer>(){
            @Override
            public boolean matches(Object item) {
                if (!(item instanceof Integer))
                    return false;
                Integer i = (Integer) item;
                return i.compareTo(0) < 0;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Must be less than");
            }
        }));
    }
    
}
