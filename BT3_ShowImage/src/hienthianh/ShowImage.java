package hienthianh;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class ShowImage extends MIDlet implements CommandListener {

    private Command cmdExit; // The exit command
    private Command cmdDisplay;
    private Display display;     // The display for this MIDlet
    private TextField txtImgPath;
    private Form form;

    public ShowImage() {
        // khoi tao gia tri
        display = Display.getDisplay(this);
        cmdExit = new Command("Exit", Command.EXIT, 0);
        cmdDisplay = new Command("Hien thi anh", Command.SCREEN, 0);
        txtImgPath = new TextField("Duong dan", "http://localhost/tenfile.jpg", 250, 0);
        form = new Form("Image");
    }

    public void startApp() {
        // gan cac thanh phan
        form.append(txtImgPath);

        form.addCommand(cmdExit);
        form.addCommand(cmdDisplay);

        form.setCommandListener(this);

        display.setCurrent(form);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable s) {
        if (c == cmdExit) {
            destroyApp(false);
            notifyDestroyed();
        } else if (c == cmdDisplay) {
            Image img = getImg(txtImgPath.getString());
            if (img != null) {
                form.append(img);
//                ImageItem imgItem = new ImageItem("Hinh anh", img, ImageItem.LAYOUT_DEFAULT, "Image");
//                form.append(imgItem);
            } else {
                System.out.println("Anh khong ton tai!");
            }
        }
    }

    private Image getImg(String url) {
        Image img = null;
        InputStream input = null;
        try {
            input = Connector.openInputStream(url);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            int c;
            while ((c = input.read()) != -1) {
                bytes.write(c);
            }
            img = Image.createImage(bytes.toByteArray(), 0, bytes.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return img;

    }
}
