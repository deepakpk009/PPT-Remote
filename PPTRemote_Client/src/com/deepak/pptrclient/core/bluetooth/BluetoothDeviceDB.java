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

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 *
 * @author deepak
 */
/*
 * this class implements a bluetooth device database
 * provides method to save and retrive the last connected server url
 */
public class BluetoothDeviceDB {

    // the record store object
    private RecordStore store;
    // the record store name
    private final String storeName = "pptrdb";

    /*
     * method to get the stored device url
     */
    public String getStoredDeviceURL() throws RecordStoreException {
        // open the record store
        store = RecordStore.openRecordStore(storeName, true);
        // device url string
        String deviceURL = null;
        // if there is any record in the record store
        if (store.getNumRecords() > 0) {
            // get the device url
            deviceURL = new String(store.getRecord(1));
        }
        // else if the record store is empty
        else {
            // create a default record
            store.addRecord(new byte[1], 0, 1);
            // set the device url as null
            deviceURL = null;
        }
        // close the record store
        store.closeRecordStore();
        // return the device url
        return deviceURL;
    }

    /*
     * method to save the device url into the record store
     */
    public void saveDeviceURL(String URL) throws RecordStoreException {
        // open the record store
        store = RecordStore.openRecordStore(storeName, true);
        // if record already present
        // then replace it
        if (store.getNumRecords() > 0) {
            store.setRecord(1, URL.getBytes(), 0, URL.getBytes().length);
        }
        // else if no record are present then add the record
        else {
            store.addRecord(URL.getBytes(), 0, URL.getBytes().length);
        }
        // close the record store
        store.closeRecordStore();
    }
}
