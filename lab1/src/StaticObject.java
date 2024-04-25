import java.awt.*;

public class StaticObject {
    public static class Tree implements Drawable {
        private final int x;
        private final int y;


        Tree(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public void draw(Graphics g) {
            g.setColor(new Color(139, 69, 19));
            g.fillRect(x, y, 40, 120);
            g.setColor(new Color(0, 128, 0));
            g.fillOval(x - 40, y - 50, 120, 120);
        }

        public boolean isInside(int x, int y) {
            return x >= this.x - 20 && x <= this.x + 150 && y >= this.y - 20 && y <= this.y + 150;
        }

    }


    public static class Sky implements Drawable {
        public void draw(Graphics g) {
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, 800, 350);
        }
    }


    public static class Grass implements Drawable {
        public void draw(Graphics g) {
            g.setColor(Color.GREEN.darker());
            g.fillRect(0, 300, 800, 300);
        }
    }


    public static class Flower implements Drawable {
        private final int x;
        private final int y;


        Flower(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public void draw(Graphics g) {
            g.setColor(Color.GREEN);
            g.fillRect(x + 10, y + 10, 5, 20);
            g.setColor(Color.RED);
            g.fillOval(x + 2, y, 20, 20);
            g.setColor(Color.YELLOW);
            g.fillOval(x + 9, y + 8, 5, 5);
        }


        public boolean isInside(int x, int y) {
            return x >= this.x - 20 && x <= this.x + 100 && y >= this.y -20 && y <= this.y + 100;
        }
    }


    public static class Sun implements Drawable {
        public void draw(Graphics g) {
            g.setColor(Color.YELLOW);
            g.fillOval(600, -200, 400, 400);
        }
    }
}
