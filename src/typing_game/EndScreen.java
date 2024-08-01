package typing_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndScreen extends JFrame{

    int SCREEN_HEIGHT;
    int SCREEN_WIDTH;
    private RoundedButton retryButton;

    public EndScreen(long time) {
        initializeUI(time);
    }

    private void initializeUI(long wpm) {
        setTitle("Typing Game");
        SCREEN_HEIGHT = 700;
        SCREEN_WIDTH = 800;
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        JPanel buttonpanel = new JPanel(new BorderLayout());
        buttonpanel.setBackground(Color.BLACK);

        JPanel textpanel = new JPanel(new BorderLayout());
        textpanel.setBackground(Color.BLACK);

        JLabel textlabel = new JLabel("<html><div style='text-align: center;'>Your Typing Speed is:<br>" + wpm + " Words Per Minute</div></html>");
        textlabel.setForeground(Color.WHITE);
        textlabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font("Arial", Font.BOLD, 38);
        textlabel.setFont(font);


        textpanel.add(textlabel);

        //retryButton.setPreferredSize(new Dimension(150, 80)); // Adjusting start button size
        buttonpanel.setPreferredSize(new Dimension(100, 100)); // Adjust height as needed
        //textpanel.setPreferredSize(new Dimension(100, 100)); // Adjust height as needed

        retryButton = new RoundedButton("Try Again");
        buttonpanel.add(retryButton, BorderLayout.CENTER);

        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        panel.add(textpanel, BorderLayout.CENTER); // Add spacer to offset Y-axis
        panel.add(buttonpanel, BorderLayout.SOUTH); // Add spacer to offset Y-axis
        add(panel);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private void startGame() {
        new WordNumberSelection();
    }

}
