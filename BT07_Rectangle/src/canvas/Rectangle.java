/*
 * Sinh viên: Nguyễn Hoàng Dương
 * Lớp D7LT-CNTT15
 * Bài thực hành số 7
 */
package canvas;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Rectangle extends MIDlet implements CommandListener {

    private Command exitCommand; // The exit command
    private Display display;     // The display for this MIDlet

    public Rectangle() {
        display = Display.getDisplay(this);
        exitCommand = new Command("Exit", Command.EXIT, 0);
    }

    public void startApp() {
        HinhChuNhat hcn = new HinhChuNhat();
        hcn.addCommand(exitCommand);
        hcn.setCommandListener(this);
        display.setCurrent(hcn);
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
}

class HinhChuNhat extends Canvas {

    // Thông tin hình chữ nhật: tọa độ x, y, chiều rộng, chiều cao
    private int X;
    private int Y;
    private int W;
    private int H;

    public HinhChuNhat() {
        X = Y = 0;
        H = 20;
        W = 60;
    }

    protected void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0, 0, 0);
        g.fillRect(X, Y, W, H);
    }

    protected void keyPressed(int keyCode) {
        keyCode = getGameAction(keyCode);
        if (keyCode == UP && Y > 0) {
            Y--;
        } else if (keyCode == DOWN && Y + H < getHeight()) {
            Y++;
        } else if (keyCode == LEFT && X > 0) {
            X--;
        } else if (keyCode == RIGHT && X + W < getWidth()) {
            X++;
        }
        repaint();
    }
}