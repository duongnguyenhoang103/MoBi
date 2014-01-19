package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

public class ReadWriteRMS extends MIDlet implements CommandListener {

    private Command exitCommand; // The exit command
    private Display display;     // The display for this MIDlet
    private TextBox text;
    private RecordStore rs;
    static final String RMS_NAME = "DEMORMS";

    public ReadWriteRMS() {
        display = Display.getDisplay(this);
        exitCommand = new Command("Exit", Command.EXIT, 0);
        text = new TextBox("", "", 256, 0);
    }

    public void startApp() {

        text.addCommand(exitCommand);
        text.setCommandListener(this);
        display.setCurrent(text);

        openRMS();
        writeRMS("Họ tên: Nguyễn Hoàng Dương");
        writeRMS("MSSV: 1271021561");
        writeRMS("Mail: DuongNguyenHoang.103@gmail.com");
        readRMS();
        closeRMS();
        deleteRMS();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable s) {
        if (c == exitCommand) {
            destroyApp(false);
            notifyDestroyed();
        }
    }

    private void openRMS() {
        try {
            rs = RecordStore.openRecordStore(RMS_NAME, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeRMS(String s) {
        byte[] b = s.getBytes();
        try {
            rs.addRecord(b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readRMS() {
        try {
            String data = "", s;
            if (rs.getNumRecords() > 0) {
                byte[] b;
                int i = 1;
                RecordEnumeration recEnum = rs.enumerateRecords(null, null, true);
                while (recEnum.hasNextElement()) {
                    b = recEnum.nextRecord();
                    s = new String(b);
                    data += "Record #" + i + "\n";
                    data += "Text: " + s + "\n";
                    data += "------------------\n";
                    i++;
                }
            } else {
                data = "No record!";
            }
            text.setString(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void closeRMS() {
        try {
            rs.closeRecordStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteRMS() {
        try {
            RecordStore.deleteRecordStore(RMS_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
