import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartoonPanel extends JPanel {
    private final List<Drawable> firstLayer = new ArrayList<>();
    private final List<Drawable> secondLayer = new ArrayList<>();
    private final List<Drawable> thirdLayer = new ArrayList<>();

    private final BufferedImage buffer;
    private final JSlider speedSlider;

    CartoonPanel() {
        firstLayer.add(new StaticObject.Sky());
        firstLayer.add(new StaticObject.Grass());
        firstLayer.add(new StaticObject.Sun());

        for (int i = 0; i < 100; ++i) {
            if (i < 50) {
                thirdLayer.add(new StaticObject.Tree(150 + i, 300 + i));
                continue;
            }
            firstLayer.add(new StaticObject.Tree(350 + i, 200 + i));
        }

        Random random = new Random();
        for (int i = 0; i < 20; ++i) {
            int x, y;

            do {
                x = random.nextInt(760);
                y = 330 + random.nextInt(200);
            } while (isInsideTree(x, y) || isInsideFlower(x, y));

            if (i % 2 == 0) {
                firstLayer.add(new StaticObject.Flower(x, y));
                continue;
            }
            thirdLayer.add(new StaticObject.Flower(x, y));
        }

        secondLayer.add(new DynamicObject.Kolobok(150, 350 + random.nextInt(150), 2));

        for (int i = 0; i < 5; ++i) {
            secondLayer.add(new DynamicObject.Cloud(random.nextInt(500), 10 + random.nextInt(150), 1 + random.nextInt(5)));
        }

        buffer = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int speed = speedSlider.getValue();
                for (Drawable drawable : secondLayer) {
                    if (drawable instanceof DynamicObject.Cloud) {
                        ((DynamicObject.Cloud) drawable).setSpeed(speed);
                        continue;
                    }
                    ((DynamicObject.Kolobok) drawable).setSpeed(speed);
                }
            }
        });
        add(speedSlider);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = buffer.createGraphics();

        for (Drawable drawable : firstLayer)
            drawable.draw(g2d);

        for (Drawable drawable : secondLayer) {
            drawable.draw(g2d);
            if (drawable instanceof DynamicObject.Kolobok) {
                ((DynamicObject.Kolobok) drawable).update();
                continue;
            }
            ((DynamicObject.Cloud) drawable).update();
        }

        for (Drawable drawable : thirdLayer)
            drawable.draw(g2d);

        g.drawImage(buffer, 0, 0, null);
    }


    private boolean isInsideTreeInLayer(int x, int y, List<Drawable> layer) {
        for (Drawable drawable : layer) {
            if (drawable instanceof StaticObject.Tree
                && ((StaticObject.Tree) drawable).isInside(x, y)) {
                return true;
            }
        }
        return false;
    }


    private boolean isInsideFlowerInLayer(int x, int y, List<Drawable> layer) {
        for (Drawable drawable : layer) {
            if (drawable instanceof StaticObject.Flower &&
                (((StaticObject.Flower) drawable).isInside(x, y)
                || ((StaticObject.Flower) drawable).isInside(x + 20, y)
                || ((StaticObject.Flower) drawable).isInside(x, y + 20)
                || ((StaticObject.Flower) drawable).isInside(x + 20, y + 20))) {
                return true;
            }
        }
        return false;
    }


    private boolean isInsideTree(int x, int y) {
        return isInsideTreeInLayer(x, y, firstLayer) || isInsideTreeInLayer(x, y, thirdLayer);
    }


    private boolean isInsideFlower(int x, int y) {
        return isInsideFlowerInLayer(x, y, firstLayer) || isInsideFlowerInLayer(x, y, thirdLayer);
    }
}
