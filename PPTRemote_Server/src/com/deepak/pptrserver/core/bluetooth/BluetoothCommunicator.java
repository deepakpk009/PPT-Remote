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

import javax.bluetooth.*;
import javax.microedition.io.*;
import java.io.*;

/**
 * @author deepak
 */
/*
 * this class provides the bluetooth communicator implimentation
 * contains methods for starting, stoping the bluetooth server
 */
public class BluetoothCommunicator {

    //name of the service
    private String serviceName = null;
    // the device connection url
    private String url = null;
    // the local device object for finding the local object
    private LocalDevice localDevice = null;
    // the stream connection notifier object
    private StreamConnectionNotifier streamConnectionNotifier = null;
    // the stream connection object to get the stream on successful connection
    private StreamConnection streamConnection = null;
    // data input stream for reading from a stream
    private DataInputStream dataInputStream = null;
    // data output stream for writing to a stream
    private DataOutputStream dataOutputStream = null;
    // bluetooth user interface reference object
    private BluetoothUI bluetoothUI = null;
    // connection flag initialised to false
    private boolean connected = false;

    /*
     * the default constructor
     */
    public BluetoothCommunicator(BluetoothUI bluetoothUI) {
        // get the bluetooth ui refernce
        this.bluetoothUI = bluetoothUI;
    }

    /*
     * method to start the server service using the service UUID and service name
     */
    public void startService(final String serviceUUID, final String serviceName) {
        // process the connection task using a thread
        new Thread() {
            // the overriden run method of the thread class

            @Override
            public void run() {
                try {
                    // create the connection url using the service uuid and service name
                    url = "btspp://localhost:" + serviceUUID + ";name=" + serviceName + ";authenticate=false;encrypt=false;";
                    // get the local device
                    localDevice = LocalDevice.getLocalDevice();
                    // set the local device as discoverable
                    localDevice.setDiscoverable(DiscoveryAgent.GIAC);
                    // open the stream
                    streamConnectionNotifier = (StreamConnectionNotifier) Connector.open(url);
                    // using the stream connection notifier accept the connection and open it
                    streamConnection = streamConnectionNotifier.acceptAndOpen();
                    // get the data input stream for the connection for reading
                    dataInputStream = new DataInputStream(streamConnection.openInputStream());
                    // get the data output stream for the connection for writing
                    dataOutputStream = new DataOutputStream(streamConnection.openOutputStream());
                    // set the connection flag as true indicating successful connection
                    connected = true;
                    // notify the bluetooth user interface object about the successful connection
                    bluetoothUI.connectionEstablished();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            // calling start method to start the thread execution
        }.start();
    }

    /*
     * method to stop the server service
     */
    public void stopService() throws IOException {
        // if stream connection present
        if (streamConnection != null) {
            // close input output streams
            dataInputStream.close();
            dataOutputStream.close();
            // close stream connection
            streamConnection.close();
            // close stream connection notifier
            streamConnectionNotifier.close();
        }
        // set stream objects to null
        dataInputStream = null;
        dataOutputStream = null;
        streamConnection = null;
        streamConnectionNotifier = null;
        // set the local device object to null
        localDevice = null;
        // initiate garbage collector
        System.gc();
        // set the connection flag as false indicating connection closed
        connected = false;
    }

    /*
     * method to send a string data
     */
    public void SendData(final String data) {
        // using a thread to process the task
        new Thread() {
            // overriden method of the thread class

            @Override
            public void run() {
                try {
                    // write the data string into the output stream
                    // in the machine independent UTF format
                    dataOutputStream.writeUTF(data);
                    // flush the data in the stream to force write it
                    dataOutputStream.flush();
                } catch (Exception ex) {
                    System.out.println("Exception while sending data:\n" + ex.getMessage());
                }
            }
            // calling start method to start processing
        }.start();
    }

    /*
     * getter for input and output stream
     */
    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    /*
     * method to get the connection status
     */
    public boolean isConnected() {
        return connected;
    }
    /*
     * method to get the service name
     */

    public String getServiceName() {
        return serviceName;
    }
    /*
     * method to get the url
     */

    public String getUrl() {
        return url;
    }
}
