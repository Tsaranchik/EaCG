import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

public class EllipsePanel extends JPanel {
    private List<Point> points;
    private Ellipse ellipse;
    private JTextField aField;
    private JTextField bField;

    public EllipsePanel(Ellipse ellipse) {
        this.ellipse = ellipse;
        this.points = ellipse.calculatePoints();

        aField = new JTextField(String.valueOf(ellipse.getA()));
        aField.setPreferredSize(new Dimension(100, 20));

        bField = new JTextField(String.valueOf(ellipse.getB()));
        bField.setPreferredSize(new Dimension(100, 20));

        aField.addActionListener(e -> {
            double a = Double.parseDouble(aField.getText());
            if (a <= 0)
                JOptionPane.showMessageDialog(this, "Parameter 'a' must be greater than 0", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            else {
                ellipse.setA(a);
                points = ellipse.calculatePoints();
                repaint();
            }

        });

        bField.addActionListener(e -> {
            double b = Double.parseDouble(bField.getText());
            if (b <= 0)
                JOptionPane.showMessageDialog(this, "Parameter 'b' must be greater than 0", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            else {
                ellipse.setB(b);
                points = ellipse.calculatePoints();
                repaint();
            }
        });

        add(new JLabel("a:"));
        add(aField);
        add(new JLabel("b:"));
        add(bField);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        g.setColor(Color.RED);

        for (Point point : points) {
            int x = (int) (getWidth() / 2 + point.getX());
            int y = (int) (getHeight() / 2 - point.getY());
            g.fillOval(x, y, 2, 2);
        }
    }
}
