import java.awt.*;

public class StaticObject {
    public static class Tree {
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

    public static class Sky {
        void draw(Graphics g) {
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, 800, 350);
        }
    }

    public static class Grass {
        void draw(Graphics g) {
            g.setColor(Color.GREEN.darker());
            g.fillRect(0, 300, 800, 300);
        }
    }

    public static class Flower {
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
            return x >= this.x && x <= this.x + 100 && y >= this.y && y <= this.y + 100;
        }

        void drawShadow(Graphics g) {
            g.setColor(new Color(0, 0, 0, 64));
            g.fillOval(x - 5, y + 25, 30, 10);
        }
    }

    public static class Sun {
        void draw(Graphics g) {
            g.setColor(Color.YELLOW);
            g.fillOval(600, -200, 400, 400);
        }
    }
}
