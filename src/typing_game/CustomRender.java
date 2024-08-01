package typing_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class CustomRender {

    private BufferedImage buffer;
    public long startTime;
    public boolean hasError = false;
    public boolean deletePress = false;
    public boolean finish = false;
    private Font font;
    private int xPos;
    private int yPos;

    public CustomRender(int width, int height) {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void draw(Graphics g) {
        g.drawImage(buffer, 0, 0, null);
    }

    public void refreshBuffer(String passage, String inputString, String errString) { //TODO: Fix the fitting on the screen
        Graphics2D g = buffer.createGraphics();
        g.clearRect(0, 0, buffer.getWidth(), buffer.getHeight()); //clears the surface

        int fontSize = buffer.getWidth() / 25;
        font = new Font("Monospaced", Font.PLAIN, fontSize);
        g.setFont(font);

        FontMetrics fontMetrics = g.getFontMetrics();
        int charWidth = fontMetrics.charWidth('_');

        if(inputString.isEmpty()) { //sets the initial position
            xPos = buffer.getWidth() / 2;
            yPos = buffer.getHeight() / 2;
        } else {
            if (deletePress) {
                xPos += charWidth; //if x=1 do not move the text //deletion - text is movig right
                deletePress = false;
            } else xPos -= charWidth / 2;
            }

        if (errString.length() == passage.length()) {
            System.out.println("Finish");
            printEndScreen(g, errString.length());
        }
        else {
            g.setColor(Color.GRAY); //sets color of the underlay
            g.drawString(passage, xPos, yPos); //shows the underlay

            if (!errString.isEmpty()) {
                Rectangle2D textBounds = fontMetrics.getStringBounds(inputString, g);

                int errHeight = yPos + (int) textBounds.getHeight();

                g.setColor(Color.RED); //sets color of the overlay
                g.drawString(errString, xPos, errHeight);
            }

            g.setColor(Color.GREEN); //sets color of the overlay
            g.drawString(inputString, xPos, yPos); //shows the overlay
            g.dispose(); //releases resources
        }
    }

    public void printEndScreen(Graphics2D g, int strlen) {
        finish = true;
        long timeEllapsed = System.currentTimeMillis() - startTime;
        long wpm = calcWPM(timeEllapsed, strlen);
        System.out.println("Finished");
        new EndScreen(wpm);
    }

    private long calcWPM(long timeEllapsed, int strlen) {
        long timeSec = timeEllapsed / 1000;
        long wpm = strlen / 5 * 60 / timeSec;
        return wpm;
    }

    public void resizeBuffer(int width, int height) { //TODO: fix the resizing
        BufferedImage newBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = newBuffer.createGraphics();
        g.drawImage(buffer, 0, 0, null);
        g.dispose();
        buffer = newBuffer;
    }
}
