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

import com.allcoware.actiontaximockup.ui.Cab;
import java.util.Collection;

/**
 *
 * @author alfred
 */


public class Driver {
    
    private static long number;
    Cab cab;
    CustomMoney money;
    private static String firstName;
    private static String middleName;
    private static String lastName;
    private static String phone;
    private static Collection<Transaction> transaction;
    private static Collection<RecurringTransaction> recurringTransactions;
    
    private long getNumber(){
        return number;
    }
        
    private void setNumber(long number){
        
    }
    
    private Cab getCab(){
        return cab;
    }
    
    private void setCab (Cab cab){
        
    }
    
    private CustomMoney getMoney(){
        return money;
    }
    
    private void setMoney(CustomMoney money){
        
    }
    
    private String getFirstName(){
        return firstName;
    }
    
    private void setFirstName(String firstName){
        
    }
    
    private String getMiddleName(){
        return middleName;
    }
    
    private void setMiddleName(String middleName){
        
    }
    
    private String getLastName(){
        return lastName;
    }
    
    private void setLastName(String lastName){
        
    }
    
    private String getPhone(){
        return phone;
    }
    
    private void setPhone(String phone){
        
    }
    
    private Collection<Transaction> getTransaction(){
        return transaction;
    }
    
    private void setTransaction(Collection<Transaction>  transaction){
        
    }
    
    private void addTransaction(Transaction t){
        
    }
    
    private void removeTransaction(Transaction t){
        
    } 
    
    private Collection<RecurringTransaction> getReurringTransactions(){
        return recurringTransactions;
    }
    
    private void setRecurringTransaction(Collection<RecurringTransaction> transactions){
        
    }
    
    private void addRecurringTransaction(RecurringTransaction t){
        
    }
    
    private void removeRecurringTransaction(RecurringTransaction t){
        
    }    
    }