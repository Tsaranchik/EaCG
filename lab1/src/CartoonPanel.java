import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartoonPanel extends JPanel {
    private final List<StaticObject.Tree> trees = new ArrayList<>();
    private final List<StaticObject.Flower> flowers = new ArrayList<>();
    private final StaticObject.Sky sky = new StaticObject.Sky();
    private final StaticObject.Grass grass = new StaticObject.Grass();
    private final StaticObject.Sun sun = new StaticObject.Sun();
    private final List<DynamicObject.Cloud> clouds = new ArrayList<>();
    private final DynamicObject.Kolobok Kolobok;
    private final BufferedImage buffer;
    private final Random random = new Random();
    private final JSlider speedSlider;

    CartoonPanel() {
        Kolobok = new DynamicObject.Kolobok(150, 500, 2);
        for (int i = 0; i < 100; ++i) {
            trees.add(new StaticObject.Tree(150 + i, 300 + i));
        }
        for (int i = 0; i < 20; ++i) {
            int x, y;

            do {
                x = random.nextInt(760);
                y = 330 + random.nextInt(200);
            } while (isInsideTree(x, y) || isInsideFlower(x, y));
            flowers.add(new StaticObject.Flower(x, y));
        }

        buffer = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < 5; ++i) {
            clouds.add(new DynamicObject.Cloud(random.nextInt(700), 50 + random.nextInt(250), 1 + random.nextInt(5)));
        }

        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int speed = speedSlider.getValue();
                for (DynamicObject.Cloud cloud : clouds) {
                    cloud.setSpeed(speed);
                }
                Kolobok.setSpeed(speed);
            }
        });
        add(speedSlider);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = buffer.createGraphics();
        grass.draw(g2d);
        sky.draw(g2d);
        sun.draw(g2d);

        for (DynamicObject.Cloud cloud : clouds) {
            cloud.draw(g2d);
            cloud.update();
        }

        for (StaticObject.Flower flower : flowers) {
            flower.drawShadow(g2d);
        }

        for (StaticObject.Flower flower : flowers) {
            flower.draw(g2d);
        }

        for (StaticObject.Tree tree : trees) {
            tree.drawShadow(g2d);
            tree.draw(g2d);
        }

        Kolobok.drawShadow(g2d);
        Kolobok.draw(g2d);
        Kolobok.update();


        g.drawImage(buffer, 0, 0, null);
    }

    private boolean isInsideTree(int x, int y) {
        for (StaticObject.Tree tree : trees) {
            if (tree.isInside(x, y)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInsideFlower(int x, int y) {
        for (StaticObject.Flower flower : flowers) {
            if (flower.isInside(x, y) || flower.isInside(x + 20, y) ||
                    flower.isInside(x, y + 20) || flower.isInside(x + 20, y + 20)) {
                return true;
            }
        }
        return false;
    }
}
