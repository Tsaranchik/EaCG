import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel {
    private Parallelogram parallelogram;

    public PaintPanel(Parallelogram parallelogram) {
        this.parallelogram = parallelogram;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        g.setColor(Color.RED);

        Point[] vertices = parallelogram.getVertices();
        for (int i = 0; i < vertices.length; i++) {
            Point start = vertices[i];
            Point end = vertices[(i + 1) % vertices.length];
            g.drawLine(start.x, start.y, end.x, end.y);
        }
    }
}
