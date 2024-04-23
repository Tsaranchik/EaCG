import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class DynamicObject {
    public static class Cloud {
        private int x, y;
        private int speed;
        private Integer userSetSpeed = null;
        private final Random random = new Random();

        Cloud(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        void draw(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, 100, 50);
        }

        void update() {
            x += speed;
            if (x > 800) {
                x = -100;
                y = 50 + random.nextInt(250);
                if (userSetSpeed == null) {
                    speed = 1 + random.nextInt(5);
                }
            }
        }

        public void setSpeed(int speed) {
            this.speed = speed;
            this.userSetSpeed = speed;
        }
    }

    public static class Kolobok {
        private int x, y;
        private int speed;
        private final int size = 50;
        private double rotationAngle = 0.0;

        Kolobok(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(rotationAngle), x + size / 2.0, y + size / 2.0);
            g2d.setColor(Color.ORANGE);
            g2d.fillOval(x, y, size, size);


            g2d.setColor(Color.BLACK);
            int eyeRadius = size / 10;
            int eyeX = x + size / 3 - eyeRadius / 2;
            int eyeY = y + size / 3 - eyeRadius / 2;
            g2d.fillOval(eyeX, eyeY, eyeRadius, eyeRadius);
            g2d.fillOval(eyeX + size / 3, eyeY, eyeRadius, eyeRadius);

            g2d.drawArc(x + size / 5, y + size / 2, size / 2, size / 4, 0, -180);

            g2d.setTransform(old);

        }

        void drawShadow(Graphics g) {
            g.setColor(new Color(0, 0, 0, 64));
            g.fillOval(x - 15, y + size, size + 10, 20);
        }

        void update() {
            x += speed;
            rotationAngle += speed * 10;
            if (x > 800) {
                x = -100;
            }
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

    }
}
