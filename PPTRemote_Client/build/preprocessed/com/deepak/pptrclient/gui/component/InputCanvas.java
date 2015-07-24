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
package com.deepak.pptrclient.gui.component;

import com.deepak.pptrclient.core.bluetooth.BluetoothCommunicator;
import com.deepak.pptrclient.core.command.PPTCommand;
import java.io.IOException;
import javax.microedition.lcdui.*;

/**
 * @author deepak
 */
public class InputCanvas extends Canvas implements CommandListener {

    private BluetoothCommunicator bluetoothCommunicator = null;

    /**
     * constructor
     */
    public InputCanvas(BluetoothCommunicator bluetoothCommunicator) {
        this.bluetoothCommunicator = bluetoothCommunicator;
    }

    /**
     * paint
     */
    public void paint(Graphics g) {
        int i, color = 0;
        for (i = 0; i < this.getHeight() / 2; i++) {
            g.setColor(color, color, color);
            color++;
            g.drawLine(0, i, this.getWidth(), i);
        }
        for (; i < this.getHeight(); i++) {
            g.setColor(color, color, color);
            color--;
            g.drawLine(0, i, this.getWidth(), i);
        }
        try {
            Image logo = Image.createImage("/com/deepak/pptrclient/resources/logo_64x64.png");
            g.drawImage(logo, (this.getWidth() / 2) - (logo.getWidth() / 2), (this.getHeight() / 2) - (logo.getHeight() / 2), Graphics.HCENTER & Graphics.VCENTER);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Called when a key is pressed.
     */
    protected void keyPressed(int keyCode) {
        if (keyCode == KEY_NUM2) {
            try {
                bluetoothCommunicator.SendData(PPTCommand.NEXT_SLIDE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (keyCode == KEY_NUM5) {
            try {
                bluetoothCommunicator.SendData(PPTCommand.PREVIOUS_SLIDE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Called when a key is released.
     */
    protected void keyReleased(int keyCode) {
    }

    /**
     * Called when a key is repeated (held down).
     */
    protected void keyRepeated(int keyCode) {
    }

    /**
     * Called when the pointer is dragged.
     */
    protected void pointerDragged(int x, int y) {
    }

    /**
     * Called when the pointer is pressed.
     */
    protected void pointerPressed(int x, int y) {
    }

    /**
     * Called when the pointer is released.
     */
    protected void pointerReleased(int x, int y) {
    }

    /**
     * Called when action should be handled
     */
    public void commandAction(Command command, Displayable displayable) {
    }
}
