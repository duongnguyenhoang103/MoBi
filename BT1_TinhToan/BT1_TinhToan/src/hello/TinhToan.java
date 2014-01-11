package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class TinhToan extends MIDlet implements CommandListener {

    private TextField txtA;
    private TextField txtphepToan;
    private TextField txtB;
    private TextField txtKQ;
    private Command cmdOK;
    private Command cmdEX;
    private Command cmdRefresh;
    private Display display;
    private Form mainForm;

    public TinhToan() {
        display = Display.getDisplay(this);
        cmdOK = new Command("OK", Command.OK, 0);
        cmdRefresh = new Command("Refresh", Command.BACK, 1);
        cmdEX = new Command("Exit", Command.EXIT, 2);

        txtA = new TextField("Nhap so A", "", 20,TextField.NUMERIC);
        txtphepToan = new TextField("Phep tinh", "+", 1,TextField.ANY);
        txtB = new TextField("nhap so b", "", 20,TextField.ANY);
        txtKQ = new TextField("Ket Qua", "", 20, 2);
    }

    public void startApp() {
        mainForm = new Form("Bài tâp tính toán");
        mainForm.append(txtA);
        mainForm.append(txtphepToan);
        mainForm.append(txtB);
        mainForm.append(txtKQ);


        mainForm.addCommand(cmdOK);
        mainForm.addCommand(cmdRefresh);
        mainForm.addCommand(cmdEX);
        mainForm.setCommandListener(this);
        display.setCurrent(mainForm);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
    int kq = 0;
    int a, b, c;

    public void commandAction(Command c, Displayable s) {
        String ChonPhepToan = null;
        if (c == cmdEX) {
            destroyApp(false);
            notifyDestroyed();
        } else if (c == cmdOK) {
            //PhepChon = new String();
            ChonPhepToan = txtA.getString();
            int a = Integer.parseInt(ChonPhepToan);

            ChonPhepToan = txtB.getString();
            int b = Integer.parseInt(ChonPhepToan);

            int kq = 0;
            ChonPhepToan = txtKQ.getString();
            ChonPhepToan = txtphepToan.getString();
            switch (ChonPhepToan.charAt(0)) {
                case '+':
                    kq = a + b;
                    break;
                case '-':
                    kq = a - b;
                    break;
                case '*':
                    kq = a * b;
                    break;
                case '/':
                    kq = a / b;
                    break;

                default:
                    txtKQ.setString("error");
            }
            txtKQ.setString(Integer.toString(kq));
        } else if (c == cmdRefresh) {
            txtA.setString("");
            txtB.setString("");
            txtphepToan.setString("");
            txtKQ.setString("");
        }


    }
}
