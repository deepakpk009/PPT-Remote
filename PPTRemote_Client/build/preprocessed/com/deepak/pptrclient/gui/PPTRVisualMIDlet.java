/*-----------------------------------
PPT Remote v0.1
-------------------------------------
PPT Remote is a mobile based presentation controller/remote.
-------------------------------------
Developed By : deepak pk
Email : deepakpk009@yahoo.in
-------------------------------------
This Project is Licensed under LGPL
-------------------------------------

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.deepak.pptrclient.gui;

import com.deepak.pptrclient.core.bluetooth.BluetoothCommunicator;
import com.deepak.pptrclient.core.bluetooth.BluetoothDeviceDB;
import com.deepak.pptrclient.core.bluetooth.BluetoothUI;
import com.deepak.pptrclient.gui.component.InputCanvas;
import java.io.IOException;
import javax.bluetooth.UUID;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStoreException;
import org.netbeans.microedition.lcdui.SplashScreen;

/**
 * @author deepak
 */

/*
 * this midlet class provides the UI part for the mobile client
 * since this class uses the bluetooth communicator for conection
 * it impliments the bluetoothUI interface
 */
public class PPTRVisualMIDlet extends MIDlet implements CommandListener, BluetoothUI {
    // the bluetooth communicator object

    private BluetoothCommunicator bluetoothCommunicator = null;
    // the server service universal unique ID - used for searching for the server
    private UUID uuid = new UUID("27012f0c68af4fbf8dbe6bbaf7aa432a", false);
    private boolean midletPaused = false;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private SplashScreen splashScreen;
    private List serverList;
    private InputCanvas inputCanvas;
    private Alert Help;
    private Command exitCommand;
    private Command helpCommand;
    private Image splashScreenImage;
    private Image HelpLogo;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The PPTRVisualMIDlet constructor.
     */
    public PPTRVisualMIDlet() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        //----------------------------------------------------------------------
        // initialise the bluetooth communicator object
        // providing the current class refernce as the constructor argument
        bluetoothCommunicator = new BluetoothCommunicator(this);
        //----------------------------------------------------------------------
        switchDisplayable(null, getSplashScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == inputCanvas) {//GEN-BEGIN:|7-commandAction|1|54-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|54-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|54-postAction
                // write post-action user code here
            } else if (command == helpCommand) {//GEN-LINE:|7-commandAction|3|57-preAction
                // write pre-action user code here
                switchDisplayable(getHelp(), getInputCanvas());//GEN-LINE:|7-commandAction|4|57-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|36-preAction
        } else if (displayable == serverList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|5|36-preAction
                // write pre-action user code here
                serverListAction();//GEN-LINE:|7-commandAction|6|36-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|21-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|7|21-preAction
                // write pre-action user code here
                switchDisplayable(null, getServerList());//GEN-LINE:|7-commandAction|8|21-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|7-postCommandAction
        }//GEN-END:|7-commandAction|9|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|10|
    //</editor-fold>//GEN-END:|7-commandAction|10|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|20-getter|0|20-preInit
    /**
     * Returns an initiliazed instance of splashScreen component.
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {//GEN-END:|20-getter|0|20-preInit
            // write pre-init user code here
            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|20-getter|1|20-postInit
            splashScreen.setTitle("");
            splashScreen.setCommandListener(this);
            splashScreen.setFullScreenMode(true);
            splashScreen.setImage(getSplashScreenImage());
            splashScreen.setText("PPT Remote");
            splashScreen.setTimeout(3000);//GEN-END:|20-getter|1|20-postInit
            // write post-init user code here
        }//GEN-BEGIN:|20-getter|2|
        return splashScreen;
    }
    //</editor-fold>//GEN-END:|20-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: serverList ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initiliazed instance of serverList component.
     * @return the initialized component instance
     */
    public List getServerList() {
        if (serverList == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            serverList = new List("Server List", Choice.IMPLICIT);//GEN-BEGIN:|35-getter|1|35-postInit
            serverList.append("Search and Connect", null);
            serverList.append("Last Used", null);
            serverList.setCommandListener(this);
            serverList.setSelectedFlags(new boolean[] { false, false });//GEN-END:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return serverList;
    }
    //</editor-fold>//GEN-END:|35-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: serverListAction ">//GEN-BEGIN:|35-action|0|35-preAction
    /**
     * Performs an action assigned to the selected list element in the serverList component.
     */
    public void serverListAction() {//GEN-END:|35-action|0|35-preAction
        // enter pre-action user code here
        String __selectedString = getServerList().getString(getServerList().getSelectedIndex());//GEN-BEGIN:|35-action|1|38-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Search and Connect")) {//GEN-END:|35-action|1|38-preAction
                // if the user selects the search and connect option
                // use the bluetooth communicator to connect to the server
                // with service uuid as specified
                try {
                    bluetoothCommunicator.connetToServerWithServiceID(uuid);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                /*
//GEN-LINE:|35-action|2|38-postAction
                 */
                // write post-action user code here
            } else if (__selectedString.equals("Last Used")) {//GEN-LINE:|35-action|3|39-preAction
                // if the user selects the last used server for connection
                try {
                    // get the last used server url from the bluetooth device database
                    String url = new BluetoothDeviceDB().getStoredDeviceURL();
                    // if url is present
                    if (url != null) {
                        // connecto the the server with the url
                        bluetoothCommunicator.connetToServerWithURL(url);
                    } else {
                        // else if no url is present then display alert informing no last servers found
                        // and return to the server selection list
                        switchDisplayable(new Alert("No Last Used Server Found!!!"), getServerList());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // write pre-action user code here
                /*
//GEN-LINE:|35-action|4|39-postAction
                 */
                // write post-action user code here
            }//GEN-BEGIN:|35-action|5|35-postAction
        }//GEN-END:|35-action|5|35-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|35-action|6|
    //</editor-fold>//GEN-END:|35-action|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreenImage ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initiliazed instance of splashScreenImage component.
     * @return the initialized component instance
     */
    public Image getSplashScreenImage() {
        if (splashScreenImage == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|51-getter|1|51-@java.io.IOException
                splashScreenImage = Image.createImage("/com/deepak/pptrclient/resources/logo_64x64.png");
            } catch (java.io.IOException e) {//GEN-END:|51-getter|1|51-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|51-getter|2|51-postInit
            // write post-init user code here
        }//GEN-BEGIN:|51-getter|3|
        return splashScreenImage;
    }
    //</editor-fold>//GEN-END:|51-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: inputCanvas ">//GEN-BEGIN:|52-getter|0|52-preInit
    /**
     * Returns an initiliazed instance of inputCanvas component.
     * @return the initialized component instance
     */
    public InputCanvas getInputCanvas() {
        if (inputCanvas == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            inputCanvas = new InputCanvas(bluetoothCommunicator);//GEN-BEGIN:|52-getter|1|52-postInit
            inputCanvas.setTitle("");
            inputCanvas.addCommand(getExitCommand());
            inputCanvas.addCommand(getHelpCommand());
            inputCanvas.setCommandListener(this);
            inputCanvas.setFullScreenMode(true);//GEN-END:|52-getter|1|52-postInit
            // write post-init user code here
        }//GEN-BEGIN:|52-getter|2|
        return inputCanvas;
    }
    //</editor-fold>//GEN-END:|52-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Help ">//GEN-BEGIN:|58-getter|0|58-preInit
    /**
     * Returns an initiliazed instance of Help component.
     * @return the initialized component instance
     */
    public Alert getHelp() {
        if (Help == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
            Help = new Alert("Help", "Press 2 for NEXT slide and 5 for Previous slide", getHelpLogo(), null);//GEN-BEGIN:|58-getter|1|58-postInit
            Help.setTimeout(Alert.FOREVER);//GEN-END:|58-getter|1|58-postInit
            // write post-init user code here
        }//GEN-BEGIN:|58-getter|2|
        return Help;
    }
    //</editor-fold>//GEN-END:|58-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|53-getter|1|53-postInit
            // write post-init user code here
        }//GEN-BEGIN:|53-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|53-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: helpCommand ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initiliazed instance of helpCommand component.
     * @return the initialized component instance
     */
    public Command getHelpCommand() {
        if (helpCommand == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            helpCommand = new Command("Help", Command.HELP, 0);//GEN-LINE:|56-getter|1|56-postInit
            // write post-init user code here
        }//GEN-BEGIN:|56-getter|2|
        return helpCommand;
    }
    //</editor-fold>//GEN-END:|56-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: HelpLogo ">//GEN-BEGIN:|60-getter|0|60-preInit
    /**
     * Returns an initiliazed instance of HelpLogo component.
     * @return the initialized component instance
     */
    public Image getHelpLogo() {
        if (HelpLogo == null) {//GEN-END:|60-getter|0|60-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|60-getter|1|60-@java.io.IOException
                HelpLogo = Image.createImage("/com/deepak/pptrclient/resources/logo_32x32.png");
            } catch (java.io.IOException e) {//GEN-END:|60-getter|1|60-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|60-getter|2|60-postInit
            // write post-init user code here
        }//GEN-BEGIN:|60-getter|3|
        return HelpLogo;
    }
    //</editor-fold>//GEN-END:|60-getter|3|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
        // save the current url to the bluetooth database
        try {
            new BluetoothDeviceDB().saveDeviceURL(bluetoothCommunicator.getServerURL());
            // if bluetooth communicator is present then dissconnect it
            if (bluetoothCommunicator != null && bluetoothCommunicator.isConnected()) {
                bluetoothCommunicator.disconnect();
            }
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * the connection established method implimentation of the bluetoothUI interface
     */
    public void connectionEstablished() {
        // goto the input canvas screen
        switchDisplayable(null, getInputCanvas());
    }
}
