import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String [] args) {
        Ellipse ellipse = new Ellipse(400, 144);

        JFrame frame = new JFrame("Ellipse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.add(new EllipsePanel(ellipse));
        frame.setVisible(true);
    }
}