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
package com.deepak.pptrserver.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author deepak
 */
/*
 * this class provides methods to change the label icons as indicators
 * the labels are reset to their base icons after a delay
 */
public class LabelIconChanger {

    // the label object
    private JLabel label = null;
    // the indicator icon
    private ImageIcon indicatorIcon = null;
    // the base icon
    private ImageIcon baseIcon = null;
    // waiting time
    private long waitTime = 500;

    /*
     * method to change the label icon for a specified amount of time
     */
    public void change(JLabel label, ImageIcon indicatorIcon, ImageIcon baseIcon) {
        // set the objects
        this.label = label;
        this.indicatorIcon = indicatorIcon;
        this.baseIcon = baseIcon;
        // start engine to indicate
        new Engine().start();
    }

    /*
     * a thread class to provide a delay after the change
     */
    private class Engine extends Thread {

        @Override
        public void run() {
            // show indicator icon
            label.setIcon(indicatorIcon);
            try {
                // wait for specified time
                this.sleep(waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(LabelIconChanger.class.getName()).log(Level.SEVERE, null, ex);
            }
            // reset the base icon to the label
            label.setIcon(baseIcon);
        }
    }

    /*
     * getter and setters for the wait time
     */
    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        if(waitTime>10){
        this.waitTime = waitTime;
        }
    }
}
