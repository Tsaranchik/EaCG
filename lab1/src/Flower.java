import java.awt.*;

public class Flower {
    private int x, y;

    Flower(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x + 10, y + 10, 5, 20);
        g.setColor(Color.RED);
        g.fillOval(x + 2, y, 20, 20);
        g.setColor(Color.YELLOW);
        g.fillOval(x + 9, y + 8, 5, 5);
    }

    public boolean isInside(int x, int y) {
        return x >= this.x && x <= this.x + 80 && y >= this.y && y <= this.y + 80;
    }

    void drawShadow(Graphics g) {
        g.setColor(new Color(0, 0, 0, 64));
        g.fillOval(x - 5, y + 25, 30, 10);
    }
}
