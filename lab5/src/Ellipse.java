import java.util.List;
import java.util.ArrayList;

public class Ellipse {
    private double a;
    private double b;

    public Ellipse(double a, double b) {
        if (a <= 0 || b <= 0)
            throw new IllegalArgumentException("Invalid ellipse parameters");
        this.a = a;
        this.b = b;
    }

    public List<Point> calculatePoints() {
        List<Point> points = new ArrayList<>();
        for (double x = -a; x <= a; x += 0.01) {
            double y = b * Math.sqrt(1 - (x * x) / (a * a));
            points.add(new Point(x, y));
            points.add(new Point(x, -y));
        }
        return points;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double setA(double a) {
        this.a = a;
        return a;
    }

    public double setB(double b) {
        this.b = b;
        return b;
    }
}
