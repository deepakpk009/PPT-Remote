/*
This file is part of PPTRemote_Client v0.1

PPTRemote_Client is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PPTRemote_Client is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with PPTRemote_Client.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.deepak.pptrclient.core.bluetooth;

import javax.microedition.io.*;
import javax.bluetooth.*;
import java.io.*;

/**
 * @author deepak
 */

/*
 * this class provides the methods for bluetooth communication between the current
 * mobile client and desktop server
 */
public class BluetoothCommunicator {

    // the discovery agent object
    private DiscoveryAgent discoveryAgent = null;
    // the stream connection object
    private StreamConnection streamConnection = null;
    // data input and output stream object
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    //The UUID of the server service
    private UUID serverUUID = null;
    // the server url
    private String serverURL = null;
    // the bluetooth UI refrence object
    private BluetoothUI bluetoothUI = null;
    // connection flag set to falseto indicate no connection
    private boolean connected = false;

    /**
     * It creates a new instance of the BluetoothCommunicator class.
     * with parameter a bluetooth UI refernce object
     */
    public BluetoothCommunicator(BluetoothUI bluetoothUI) {
        // set the bluetooth UI refernce object
        this.bluetoothUI = bluetoothUI;
    }

    /*
     * method to send the string data through the output stream
     */
    public void SendData(final int data) throws IOException {
        // using a thread to process the task
        new Thread() {
            // the over ridden run method of the thread class

            public void run() {
                try {
                    // write the data using the stream in the machine independent UTF format
                    dataOutputStream.write(data);
                    // flush the data out of the stream. to force write it.
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            // start the thread
        }.start();
    }

    /*
     * method to connect to server with the specified service uuid
     */
    public void connetToServerWithServiceID(final UUID uuid) throws IOException {
        // use a thread for processing
        new Thread() {
            // the overriden run method of the thread class

            public void run() {
                try {
                    // initially clear all connections if present
                    disconnect();
                    // set the uuid of the service
                    serverUUID = uuid;
                    // get the discovery agent from the local device
                    discoveryAgent = LocalDevice.getLocalDevice().getDiscoveryAgent();
                    // get the server url on selecting the server with the service
                    serverURL = discoveryAgent.selectService(serverUUID, ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                    //establish the connection
                    streamConnection = (StreamConnection) Connector.open(serverURL);
                    // get the output stream
                    dataOutputStream = new DataOutputStream(streamConnection.openOutputStream());
                    // get the input stream
                    dataInputStream = new DataInputStream(streamConnection.openInputStream());
                    // set the connected flag to true indicating successful connection
                    connected = true;
                    // notify the bluetoothUI about the succesful connection
                    bluetoothUI.connectionEstablished();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            // start the thread
        }.start();
    }

    /*
     * method to connect to a server with the specified url
     */
    public void connetToServerWithURL(final String url) throws IOException {
        // using a thread for processing
        new Thread() {
            // the overriden method of the thread class

            public void run() {
                try {
                    // intially disconnect all connections if present any
                    disconnect();
                    // set the server url
                    serverURL = url;
                    //establish the connection
                    streamConnection = (StreamConnection) Connector.open(url);
                    // get the output stream
                    dataOutputStream = new DataOutputStream(streamConnection.openOutputStream());
                    // get the input stream
                    dataInputStream = new DataInputStream(streamConnection.openInputStream());
                    // set the connected flag to true indication successful connection
                    connected = true;
                    // notify the bluetoothUI about the successful connection
                    bluetoothUI.connectionEstablished();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * method to disconnect from the server
     */
    public void disconnect() throws IOException {
        // if stream connection is present then
        if (streamConnection != null) {
            // close all streams
            dataOutputStream.close();
            dataInputStream.close();
            streamConnection.close();
        }
        // nullify all stream objects
        dataOutputStream = null;
        dataInputStream = null;
        streamConnection = null;
        // initiate garbage collector
        System.gc();
        // set the connected flag to false indicating no connection
        connected = false;
    }

    /*
     * getter for input stream
     */
    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    /*
     * getter for output stream
     */
    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    /*
     * getter for server url
     */
    public String getServerURL() {
        return serverURL;
    }

    /*
     * getter for stream connection
     */
    public StreamConnection getStreamConnection() {
        return streamConnection;
    }

    /*
     * getter for server uuid
     */
    public UUID getServerUUID() {
        return serverUUID;
    }

    /*
     * getter for connected status
     */
    public boolean isConnected() {
        return connected;
    }
}
