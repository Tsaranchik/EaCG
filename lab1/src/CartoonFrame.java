import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CartoonFrame extends JFrame {

    CartoonFrame() {
        CartoonPanel panel = new CartoonPanel();
        add(panel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CartoonFrame();
    }
}
