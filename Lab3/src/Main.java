import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        Parallelogram parallelogram = new Parallelogram(new Point(900, 500));

        PaintPanel paintPanel = new PaintPanel(parallelogram);
        JFrame frame = new JFrame("Parallelogram");
        frame.setLayout(new BorderLayout());
        frame.add(paintPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        controlPanel.add(new JLabel("Top left (X,Y):"));
        JTextField topLeftField = new JTextField();
        controlPanel.add(topLeftField);

        controlPanel.add(new JLabel("Scale factor:"));
        JTextField scaleFactorField = new JTextField();
        controlPanel.add(scaleFactorField);

        controlPanel.add(new JLabel("Rotation angle (in degrees):"));
        JTextField rotationAngleField = new JTextField();
        controlPanel.add(rotationAngleField);

        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        topLeftField.addActionListener(e -> {
            String[] parts = topLeftField.getText().replaceAll(" ", "").split(",");
            if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()
                    || Integer.parseInt(parts[0]) <= 0 || Integer.parseInt(parts[1]) <= 0)
                JOptionPane.showMessageDialog(frame, "Please enter a valid top left point.");
            else {
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                parallelogram.setTopLeft(new Point(x, y));
                paintPanel.repaint();
            }

        });

        scaleFactorField.addActionListener(e -> {
            scaleFactorField.setText(scaleFactorField.getText()
                    .replaceAll(",",".").replaceAll(" ", ""));
            if (scaleFactorField.getText().isEmpty() || Double.parseDouble(scaleFactorField.getText()) <= 0)
                JOptionPane.showMessageDialog(frame, "Please enter a valid scale factor.");
            else {
                double scaleFactor = Double.parseDouble(scaleFactorField.getText());
                parallelogram.scale(scaleFactor);
                paintPanel.repaint();
            }
        });

        rotationAngleField.addActionListener(e -> {
            rotationAngleField.setText(rotationAngleField.getText()
                    .replaceAll(",",".").replaceAll(" ", ""));
            if (rotationAngleField.getText().isEmpty())
                JOptionPane.showMessageDialog(frame, "Please enter a valid rotation angle.");
            else {
                double rotationAngle = Math.toRadians(Double.parseDouble(rotationAngleField.getText()));
                parallelogram.rotate(rotationAngle);
                paintPanel.repaint();
            }
        });


    }
}