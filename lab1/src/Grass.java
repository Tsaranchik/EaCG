import java.awt.*;

public class Grass {
    void draw(Graphics g) {
        g.setColor(Color.GREEN.darker());
        g.fillRect(0, 300, 800, 300);
    }
}
