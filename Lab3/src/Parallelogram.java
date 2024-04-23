import java.awt.*;

public class Parallelogram {
    private Point[] originalVertices;
    private Point[] vertices;
    private double rotatingAngle = 0.0;
    private double scaleFactor = 1.0;

    public Parallelogram(Point topLeft) {
        this.originalVertices = new Point[4];
        this.vertices = new Point[4];
        this.originalVertices[0] = topLeft;
        this.originalVertices[1] = new Point(topLeft.x + 100, topLeft.y);
        this.originalVertices[2] = new Point(topLeft.x + 115, topLeft.y + 50);
        this.originalVertices[3] = new Point(topLeft.x + 15, topLeft.y + 50);
        resetVertices();
    }

    public Point[] getVertices() {
        return vertices;
    }

    public Point getCenter() {
        int x = 0;
        int y = 0;
        for (Point vertex : vertices) {
            x += vertex.x;
            y += vertex.y;
        }
        return new Point(x / vertices.length, y / vertices.length);
    }

    public void scale(double scaleFactor) {
        this.scaleFactor = scaleFactor;
        resetVertices();
        applyTransformations();
    }

    public void rotate(double angle) {
        this.rotatingAngle += angle;
        resetVertices();
        applyTransformations();
    }

    private void resetVertices() {
        for (int i = 0; i < vertices.length; ++i)
            vertices[i] = new Point(originalVertices[i]);
    }

    private void applyTransformations() {
        Point center = getCenter();
        double sin = Math.sin(rotatingAngle);
        double cos = Math.cos(rotatingAngle);

        for (Point vertex : vertices) {
            int dx = vertex.x - center.x;
            int dy = vertex.y - center.y;
            vertex.x = center.x + (int) ((dx * cos - dy * sin) * scaleFactor);
            vertex.y = center.y + (int) ((dx * sin + dy * cos) * scaleFactor);
        }
    }

    public Point getTopLeft() {
        return originalVertices[0];
    }

    public void setTopLeft(Point topLeft) {
        int dx = topLeft.x - this.originalVertices[0].x;
        int dy = topLeft.y - this.originalVertices[0].y;

        for (int i = 0; i < originalVertices.length; ++i) {
            originalVertices[i] = new Point(originalVertices[i].x + dx, originalVertices[i].y + dy);
        }

        resetVertices();
        applyTransformations();
    }
}
