public class Point {
    private double x;
    private double y;
    private final int PIXELS_PER_CM = 40;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getXInCm() {
        return x / PIXELS_PER_CM;
    }

    public double getYInCm() {
        return -y / PIXELS_PER_CM;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
