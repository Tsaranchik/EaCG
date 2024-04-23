import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    static ArrayList<File> files = new ArrayList<>();
    static int currImageIndex = 0;

    public static void main(String[] args) throws IOException {
        File folder = new File("bmp_pics");
        File[] listOfFiles = folder.listFiles();

        for (File file : Objects.requireNonNull(listOfFiles)) {
            if (file.isFile() && file.getName().endsWith(".bmp"))
                files.add(file);
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        BufferedImage bufferedImage = ImageIO.read(files.get(currImageIndex));
        frame.setTitle(files.get(currImageIndex).getName());
        JLabel label = new JLabel(new ImageIcon(bufferedImage));
        frame.add(label, BorderLayout.CENTER);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            currImageIndex++;
            if (currImageIndex >= files.size())
                currImageIndex = 0;

            try {
                label.setIcon(new ImageIcon(ImageIO.read(files.get(currImageIndex))));
                frame.setTitle(files.get(currImageIndex).getName());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        JButton prevButton = new JButton("Previous");
        prevButton.addActionListener(e -> {
            currImageIndex--;
            if (currImageIndex < 0)
                currImageIndex = files.size() - 1;

            try {
                label.setIcon(new ImageIcon(ImageIO.read(files.get(currImageIndex))));
                frame.setTitle(files.get(currImageIndex).getName());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        frame.add(nextButton, BorderLayout.EAST);
        frame.add(prevButton, BorderLayout.WEST);

        frame.setVisible(true);
    }
}