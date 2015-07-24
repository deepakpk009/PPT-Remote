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
package com.deepak.pptrserver.core.controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author deepak
 */
/*
 * this class provides methods for pressing the next and previous keys
 * for controlling the presentation
 */
public class Remote {

    // the robot object
    private Robot robot = null;

    public Remote() {
        try {
            // initialise the robot object
            this.robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Remote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // the next slide command
    public void next() {
        robot.keyPress(KeyEvent.VK_N);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_N);
    }

    // the previous slide command
    public void previous() {
        robot.keyPress(KeyEvent.VK_P);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_P);
    }
}
