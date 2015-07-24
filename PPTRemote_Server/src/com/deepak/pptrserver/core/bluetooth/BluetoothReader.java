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

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author deepak
 */
/*
 * this class impliments a reader clas which reads continiously from the stream
 * and set the read message to the bluetooth message object
 */
public class BluetoothReader implements Runnable {

    // the input stream object
    private DataInputStream dataInputStream = null;
    // the bluetooth message object
    private BluetoothMessage bluetoothMessage = null;
    // flag controller for repeated reading
    private boolean stop = false;

    /*
     * privatising the default constructor inorder to prevent usage
     */
    private BluetoothReader() {
    }

    /*
     * class constructor with input stream reffernce and message reffernce as parameter
     */
    public BluetoothReader(DataInputStream din, BluetoothMessage btmsg) {
        // set the stream refernce
        this.dataInputStream = din;
        // get the message reffernce
        bluetoothMessage = btmsg;
    }

    /*
     * method to start reading from the stream
     */
    public void startReading() {
        // set stop flag as false;
        stop = false;
        // create a new thread with the current class as the runnable parameter
        // and start it
        new Thread(this).start();
    }

    /*
     * method to stop reading from the stream
     */
    public void stopReading() {
        // set the stop flag to true
        stop = true;
    }

    /*
     * the run method implimentation for the runnable interface
     */
    @Override
    public void run() {
        try {
            // while the stream is not null and stop flag is false
            while (dataInputStream != null && !stop) {
                // read the message
                // set the message
                bluetoothMessage.setMessage(dataInputStream.read());
            }
        } catch (IOException ex) {
            Logger.getLogger(BluetoothReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
