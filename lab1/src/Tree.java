import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Tree {
    private int x, y;

    Tree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void draw(Graphics g) {
        g.setColor(new Color(139, 69, 19));
        g.fillRect(x, y, 40, 120);
        g.setColor(new Color(0, 128, 0));
        g.fillOval(x - 40, y - 50, 120, 120);
    }

    void drawShadow(Graphics g) {
        g.setColor(new Color(0, 0, 0, 64));
        g.fillOval(x - 45, y + 75, 100, 100);
    }

    public boolean isInside(int x, int y) {
        return x >= this.x && x <= this.x + 50 && y >= this.y && y <= this.y + 130;
    }
}
