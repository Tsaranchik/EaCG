import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String [] args) {
        Ellipse ellipse = new Ellipse(4, 3);

        JFrame frame = new JFrame("Ellipse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(screenSize.width, screenSize.height);
        frame.add(new EllipsePanel(ellipse));
        frame.setVisible(true);
    }
}