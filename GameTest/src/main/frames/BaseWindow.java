package main.frames;

import javax.swing.*;
import java.awt.*;

public class BaseWindow extends JFrame {

    // Properties //

    // Constructor //
    public BaseWindow() {

        this.init();

    }

    // Methods //
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setUndecorated(true);
    }

    public void paint( Graphics g ) {
        System.out.println("update");
        super.paint(g);
        g.setColor( Color.BLACK );
        Dimension size = getSize();
        g.fillRect(0, 0, size.width, size.height );
    }

}
