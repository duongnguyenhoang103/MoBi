/*
 * 
 * 
 *
 * and open the template in the editor.
 */
package DownloadShowFile;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 *
 * @author  Nguyen Hoang Duong
 */
public class Midlet extends MIDlet implements CommandListener {

    private Display display;
    private Command cmdOk;
    private Command cmdExit;
    private Form mainForm;
    private TextField link;
    private StringItem header;
    private StringItem data;

    public Midlet() {
        display = Display.getDisplay(this);
        cmdExit = new Command("Exit", Command.EXIT, 0);
        cmdOk = new Command("OK", Command.OK, 1);
        mainForm = new Form("Form");
        link = new TextField("Input URL", "", 200, 0);
        header = new StringItem("Header:", "");
        data = new StringItem("Content:", "");


    }

    public void startApp() {
        mainForm.append(link);
        mainForm.append(header);
        mainForm.append(data);
        
        mainForm.addCommand(cmdExit);
        mainForm.addCommand(cmdOk);
        mainForm.setCommandListener(this);
        display.setCurrent(mainForm);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdExit) {
            destroyApp(false);
            notifyDestroyed();
        } else if (c == cmdOk) {
            try {
                process();
            } catch (IOException e) {
                data.setText("Erro!");
            }
        }
    }

    private void process() throws IOException {
        HttpConnection http = null;
        InputStream is = null;
        String s = null;
        try {
            http = (HttpConnection) Connector.open(link.getString());
            http.setRequestMethod(HttpConnection.GET);
        } catch (Exception e) {
            data.setText("Not Connected server");
            return;
        }
        if (http.getResponseCode() == HttpConnection.HTTP_OK) {
            // header
            s = "";
            for (int i = 0;; i++) {
                String key = http.getHeaderFieldKey(i);
                String value = http.getHeaderField(i);
                if (key == null) {
                    break;
                }
                s += key + " = " + value;
            }
            header.setText(s);
            // content
            s = "";
            int ch;
            is = http.openInputStream();
            while ((ch = is.read()) != -1) {
                s += (char) ch;
            }
            data.setText(s);
        }
        // close connection
        if (http != null) {
            http.close();
        }
        if (is != null) {
            is.close();
        }

    }
}
