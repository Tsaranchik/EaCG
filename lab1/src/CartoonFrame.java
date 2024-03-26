import javax.swing.*;

public class CartoonFrame extends JFrame {

    CartoonFrame() {
        CartoonPanel panel = new CartoonPanel();
        add(panel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Timer timer = new Timer(20, repaint -> panel.repaint());
        timer.start();
    }

    public static void main(String[] args) {
        new CartoonFrame();
    }
}
