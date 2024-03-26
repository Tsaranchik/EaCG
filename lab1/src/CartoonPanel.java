import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class CartoonPanel extends JPanel {
    private final List<Tree> trees = new ArrayList<>();
    private final List<Flower> flowers = new ArrayList<>();
    private final Sky sky = new Sky();
    private final Grass grass = new Grass();
    private final Sun sun = new Sun();
    private final BufferedImage buffer;

    CartoonPanel() {
        for (int i = 0; i < 100; ++i) {
            trees.add(new Tree(150 + i, 300 + i));
        }
        for (int i = 0; i < 20; ++i) {
            int x, y;
            Random random = new Random();
            do {
                x = random.nextInt(760);
                y = 330 + random.nextInt(200);
            } while (isInsideTree(x, y) || isInsideFlower(x, y));
            flowers.add(new Flower(x, y));
        }

        buffer = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = buffer.createGraphics();
        grass.draw(g2d);
        sky.draw(g2d);

        for (Flower flower : flowers) {
            flower.draw(g2d);
            flower.drawShadow(g2d);
        }

        for (Tree tree : trees) {
            tree.drawShadow(g2d);
            tree.draw(g2d);
        }

        sun.draw(g2d);
        g.drawImage(buffer, 0, 0, null);
    }

    private boolean isInsideTree(int x, int y) {
        for (Tree tree : trees) {
            if (tree.isInside(x, y)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInsideFlower(int x, int y) {
        for (Flower flower : flowers) {
            if (flower.isInside(x, y)) {
                return true;
            }
        }
        return false;
    }
}
