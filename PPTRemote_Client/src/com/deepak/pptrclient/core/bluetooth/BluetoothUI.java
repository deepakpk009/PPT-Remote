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

/**
 *
 * @author deepak
 */
/*
 * this interface must be implimented by all UI class which uses the
 * bluetooth communicator class to initiate the server service.
 * this interface provides mechanism to inform the UI class about the
 * successful connection.
 */
public interface BluetoothUI {
    /*
     * the connection established method prototype.
     * to be implimented by the UI class.
     * called by the bluetooth communicator class on successful connection
     */

    public void connectionEstablished();
}
