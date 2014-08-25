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
package com.allcoware.actiontaximockup.resources;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class currently only holds the Cab ID number, but it can be modified as
 * necessary later on
 *
 * @author alfred
 */
@Entity
public class Cab implements Serializable {

    @Id
    private long cabID;
    
    public void setCabID(long cabID){
        this.cabID=cabID;
    }
    
    public long getCabID(){
        return cabID;
    }

    @Override
    public String toString() {
        return "Cab{" + "cabID=" + cabID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cab other = (Cab) obj;
        if (this.cabID != other.cabID) {
            return false;
        }
        return true;
    }
    
    
  }
