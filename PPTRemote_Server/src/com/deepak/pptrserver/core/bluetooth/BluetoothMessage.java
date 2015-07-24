/*
This file is part of PPTRemote_Server v0.1

PPTRemote_Server is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PPTRemote_Server is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with PPTRemote_Server.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.deepak.pptrserver.core.bluetooth;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author deepak
 */
/*
 * this class provides a bluetooth message implimentation
 * used for storing the recieved message
 */
public class BluetoothMessage {

    // the the message string
    private int message;

    /*
     * the default constructor
     */
    public BluetoothMessage() {
    }

    /*
     * parameteerised constructor with the string message as parameter
     */
    public BluetoothMessage(int message) {
        // set the message
        this.message = message;
    }

    /*
     * getter for the message
     */
    public int getMessage() {
        return message;
    }

    /*
     * setter for the message
     */
    public void setMessage(int message) {
        // set the message
        this.message = message;
        // firing the property change
        // in-order to be listened by the property change listner components
        this.propertyChangeSupport.firePropertyChange(messageProperty, 0, 1);
    }
    // --------------------- Property Change Listner ---------------------------
    // message property name
    private String messageProperty = "MESSAGE_CHANGED";
    // property change support object
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /*
     * addPropertyChangeListener Method
     * used by listners for this message for adding themself as a listner for this message
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /*
     * removePropertyChangeListener Method
     * used by listners for this message for removing themself as a listner for this message
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    // ------------------- MessegeProperty Getter and Setter -------------------
    public String getMessegeProperty() {
        return messageProperty;
    }

    public void setMessegeProperty(String messageProperty) {
        this.messageProperty = messageProperty;
    }
}
