import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EllipsePanel extends JPanel {
    private List<Point> points;
    private Ellipse ellipse;
    private JTextField aField;
    private JTextField bField;
    private final int SCALE = 40;

    public EllipsePanel(Ellipse ellipse) {
        this.ellipse = ellipse;
        this.points = ellipse.calculatePoints();

        setLayout(null);

        JLabel aLabel = new JLabel("a:");
        aLabel.setBounds(10, 10, 20, 25);
        add(aLabel);

        aField = new JTextField(String.valueOf(ellipse.getA()));
        aField.setBounds(40, 10, 50, 25);
        add(aField);

        JLabel bLabel = new JLabel("b:");
        bLabel.setBounds(10, 40, 20, 25);
        add(bLabel);

        bField = new JTextField(String.valueOf(ellipse.getB()));
        bField.setBounds(40, 40, 50, 25);
        add(bField);

        aField.addActionListener(e -> {
            double a = Double.parseDouble(aField.getText());
            if (a <= 0)
                JOptionPane.showMessageDialog(EllipsePanel.this, "Parameter 'a' must be greater than 0", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            else {
                ellipse.setA(a);
                points = ellipse.calculatePoints();
                repaint();
            }
        });

        bField.addActionListener(e -> {
            double b = Double.parseDouble(bField.getText());
            if (b <= 0)
                JOptionPane.showMessageDialog(EllipsePanel.this, "Parameter 'b' must be greater than 0", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            else {
                ellipse.setB(b);
                points = ellipse.calculatePoints();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        g2d.drawLine(0, centerY, width, centerY);
        g2d.drawLine(centerX, 0, centerX, height);

        g2d.setColor(Color.RED);


        for (Point point : points) {
            int x = (int) (centerX + point.getX() * SCALE);
            int y = (int) (centerY - point.getY() * SCALE);
            g2d.fillOval(x, y, 10, 10);
        }

        int cmInPixels = cmToPixels(1);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.setColor(Color.BLACK);

        for (int i = cmInPixels; i < centerX; i += cmInPixels) {
            g2d.drawLine(centerX + i, centerY - 5, centerX + i, centerY + 5);
            g2d.drawString(String.valueOf(i / cmInPixels), centerX + i - 10, centerY + 20);

            g2d.drawLine(centerX - i, centerY - 5, centerX - i, centerY + 5);
            g2d.drawString(String.valueOf(-i / cmInPixels), centerX - i - 10, centerY + 20);
        }

        for (int i = cmInPixels; i < centerY; i += cmInPixels) {
            g2d.drawLine(centerX - 5, centerY - i, centerX + 5, centerY - i);
            g2d.drawString(String.valueOf(i / cmInPixels), centerX + 10, centerY - i + 5);

            g2d.drawLine(centerX - 5, centerY + i, centerX + 5, centerY + i);
            g2d.drawString(String.valueOf(-i / cmInPixels), centerX + 10, centerY + i + 5);
        }
    }

    private int cmToPixels(int cm) {
        return cm * SCALE;
    }
}
