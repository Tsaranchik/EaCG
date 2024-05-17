import javax.swing.*;
import java.awt.*;

public class Parallelogram extends JPanel {
    private double leftTopX = 0;
    private double leftTopY = 0;
    private final double baseLength = 3;
    private final double baseHeight = 3;
    private final double angle = 20;
    private double scale = 1.0;
    private double rotationAngle = 0;

    public Parallelogram() {
        setLayout(null);
        createInputFields();
    }

    private void createInputFields() {
        JLabel xLabel =  new JLabel("X:");
        xLabel.setBounds(10, 10, 50, 25);
        add(xLabel);

        JTextField xField = new JTextField(String.valueOf(leftTopX));
        xField.setBounds(70, 10, 50, 25);
        add(xField);

        JLabel yLabel =  new JLabel("Y:");
        yLabel.setBounds(10, 40, 50, 25);
        add(yLabel);

        JTextField yField = new JTextField(String.valueOf(leftTopY));
        yField.setBounds(70, 40, 50, 25);
        add(yField);

        JLabel scaleLabel =  new JLabel("Scale:");
        scaleLabel.setBounds(10, 70, 50, 25);
        add(scaleLabel);

        JLabel rotationLabel =  new JLabel("Rotation (in degrees):");
        rotationLabel.setBounds(10, 100, 150, 25);
        add(rotationLabel);

        JTextField rotationField = new JTextField(String.valueOf(rotationAngle));
        rotationField.setBounds(160, 100, 50, 25);
        add(rotationField);

        JTextField scaleField = new JTextField(String.valueOf(scale));
        scaleField.setBounds(70, 70, 50, 25);
        add(scaleField);

        JButton drawButton = new JButton("Draw");
        drawButton.setBounds(10, 130, 110, 25);
        add(drawButton);

        JButton scaleButton = new JButton("Scale");
        scaleButton.setBounds(10, 160, 110, 25);
        add(scaleButton);

        JButton rotateButton = new JButton("Rotate");
        rotateButton.setBounds(10, 190, 130, 25);
        add(rotateButton);

        drawButton.addActionListener(e -> {
            try {
                leftTopX = Double.parseDouble(xField.getText());
                leftTopY = Double.parseDouble(yField.getText());
                repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers");

            }
        });

        scaleButton.addActionListener(e -> {
            try {
                scale = Double.parseDouble(scaleField.getText());
                repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid scale factor");

            }
        });

        rotateButton.addActionListener(e -> {
            try {
                rotationAngle = Double.parseDouble(rotationField.getText());
                repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid rotation angle");

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        int centerX = width / 2;
        int centerY = height / 2;

        g2d.drawLine(centerX, 0, centerX, height);
        g2d.drawLine(0, centerY, width, centerY);

        drawAxisLabels(g2d, centerX, centerY, width, height);

        int x1 = centerX + cmToPixels(leftTopX);
        int y1 = centerY - cmToPixels(leftTopY);
        int x2 = x1 + cmToPixels(baseLength);
        int y2 = y1;
        int x3 = (int) (x2 + cmToPixels(baseHeight) * Math.tan(Math.toRadians(angle)));
        int y3 = y2 + cmToPixels(baseHeight);
        int x4 = (int) (x1 + cmToPixels(baseHeight) * Math.tan(Math.toRadians(angle)));
        int y4 = y1 + cmToPixels(baseHeight);

        int centerXParallelogram = (x1 + x3) / 2;
        int centerYParallelogram = (y1 + y3) / 2;

        x1 = scaleCoordinate(x1, centerXParallelogram);
        y1 = scaleCoordinate(y1, centerYParallelogram);
        x2 = scaleCoordinate(x2, centerXParallelogram);
        y2 = scaleCoordinate(y2, centerYParallelogram);
        x3 = scaleCoordinate(x3, centerXParallelogram);
        y3 = scaleCoordinate(y3, centerYParallelogram);
        x4 = scaleCoordinate(x4, centerXParallelogram);
        y4 = scaleCoordinate(y4, centerYParallelogram);

        int[] xPoints = {x1, x2, x3, x4};
        int[] yPoints = {y1, y2, y3, y4};
        rotateParallelogram(xPoints, yPoints, rotationAngle, centerX, centerY);

        g2d.setColor(Color.RED);
        g2d.drawPolygon(xPoints, yPoints, 4);
    }

    private int scaleCoordinate(int coord, int centerCoord) {
        return (int) ((coord - centerCoord) * scale + centerCoord);
    }

    private void rotateParallelogram(int[] xPoints, int[] yPoints, double angleDegrees, int centerX, int centerY) {
        double angleRadians = Math.toRadians(angleDegrees);
        double cosTheta = Math.cos(angleRadians);
        double sinTheta = Math.sin(angleRadians);

        for (int i = 0; i < xPoints.length; ++i) {
            int x = xPoints[i] - centerX;
            int y = yPoints[i] - centerY;

            xPoints[i] = (int) (x * cosTheta - y * sinTheta + centerX);
            yPoints[i] = (int) (x * sinTheta + y * cosTheta + centerY);
        }
    }

    private void drawAxisLabels(Graphics2D g2d, int centerX, int centerY, int width, int height) {
        int cmInPixels = cmToPixels(1);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));

        for (int i = cmInPixels; i < width / 2; i += cmInPixels) {
            g2d.drawLine(centerX + i, centerY - 5, centerX + i, centerY + 5);
            g2d.drawString(String.valueOf(i / cmInPixels), centerX + i - 10, centerY + 20);

            g2d.drawLine(centerX - i, centerY - 5, centerX - i, centerY + 5);
            g2d.drawString(String.valueOf(-i / cmInPixels), centerX - i - 10, centerY + 20);
        }

        for (int i = cmInPixels; i < height / 2; i += cmInPixels) {
            g2d.drawLine(centerX - 5, centerY - i, centerX + 5, centerY - i);
            g2d.drawString(String.valueOf(i / cmInPixels), centerX + 10, centerY - i + 5);

            g2d.drawLine(centerX - 5, centerY + i, centerX + 5, centerY + i);
            g2d.drawString(String.valueOf(-i / cmInPixels), centerX + 10, centerY + i + 5);
        }
    }

    public double pixelsToCm(int pixels) {
        double cmPerPixel = 1.0 / 40;
        return pixels * cmPerPixel;
    }

    public int cmToPixels(double cm) {
        double pixelsPerCm = 40;
        return (int) (cm * pixelsPerCm);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Parallelogram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.add(new Parallelogram());
        frame.setVisible(true);
    }
}