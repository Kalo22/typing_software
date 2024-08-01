package typing_game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class RoundedButton extends JButton {
    private static final int ARC_WIDTH = 30; // Adjust the width of the arc for rounding
    private static final int ARC_HEIGHT = 30; // Adjust the height of the arc for rounding

    public RoundedButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setForeground(Color.WHITE); // Set font color to white
        setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Set border color to white
        setBackground(Color.BLACK);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT); // Use ARC_WIDTH and ARC_HEIGHT
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT); // Use ARC_WIDTH and ARC_HEIGHT
    }

    Shape shape;

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT); // Use ARC_WIDTH and ARC_HEIGHT
        }
        return shape.contains(x, y);
    }
}