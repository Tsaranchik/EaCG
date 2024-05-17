import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;


public class BMPViewer extends JPanel {
    private int width;
    private int height;
    private int[] pixels;
    private List<String> filePaths;
    private int currentImageIndex;

    public BMPViewer(List<String> filePaths) {
        this.filePaths = filePaths;
        this.currentImageIndex = 0;
        loadImage(filePaths.get(currentImageIndex));
    }

    private void loadImage(String filePath) {
        try {
            readBMP(filePath);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load the BMP file.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pixels != null) {
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    g.setColor(new Color(pixels[y * width + x]));
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
    }

    private void readBMP(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        byte[] header = new byte[54];
        fis.read(header);

        if (header[0] != 'B' || header[1] != 'M') {
            fis.close();
            throw new IOException("Invalid BMP file.");
        }

        width = ByteBuffer.wrap(header, 18, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        height = ByteBuffer.wrap(header, 22, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();

        int offset = ByteBuffer.wrap(header, 10, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        int rowSize = ((width * 3 + 3) / 4) * 4;

        fis.skip(offset - 54);
        byte[] pixelData = new byte[rowSize * height];
        fis.read(pixelData);
        fis.close();

        pixels = new int[width * height];
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int pixelIndex = y * rowSize + x * 3;
                int blue = pixelData[pixelIndex] & 0xFF;
                int green = pixelData[pixelIndex + 1] & 0xFF;
                int red = pixelData[pixelIndex + 2] & 0xFF;
                int rgb = (red << 16) | (green << 8) | blue;
                pixels[(height - y - 1) * width + x] = rgb;
            }
        }
    }

    public void nextImage() {
        if (filePaths.size() > 1) {
            currentImageIndex = (currentImageIndex + 1) % filePaths.size();
            loadImage(filePaths.get(currentImageIndex));
        }
    }

    public void previousImage() {
        if (filePaths.size() > 1) {
            currentImageIndex = (currentImageIndex - 1 + filePaths.size()) % filePaths.size();
            loadImage(filePaths.get(currentImageIndex));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BMP Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            String folderPath = "/home/tsaran/Desktop/Documents/University/SecondCourse/SecondTerm/EaCG/Lab4/bmp_pics";
            File folder = new File(folderPath);
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".bmp"));
            List<String> filePaths = new ArrayList<>();

            if (files != null) {
                for (File file : files) {
                    filePaths.add(file.getAbsolutePath());
                }
            }

            BMPViewer bmpViewer = new BMPViewer(filePaths);
            frame.add(bmpViewer, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            JButton previousButton = new JButton("Previous");
            JButton nextButton = new JButton("Next");

            previousButton.addActionListener(e -> bmpViewer.previousImage());
            nextButton.addActionListener(e -> bmpViewer.nextImage());

            buttonPanel.add(previousButton);
            buttonPanel.add(nextButton);

            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }

}