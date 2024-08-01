package typing_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordNumberSelection extends JFrame {
    private JLabel numberLabel;
    private JLabel numberLabel2;
    private RoundedButton incrementButton;
    private RoundedButton decrementButton;
    private RoundedButton incrementButton2;
    private RoundedButton decrementButton2;
    private RoundedButton startButton;

    private int firstnum = 1;
    private int secondnum = 0;
    private int wordNumber = 0;

    int SCREEN_HEIGHT;
    int SCREEN_WIDTH;

    public WordNumberSelection() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Typing Game");
        SCREEN_HEIGHT = 700;
        SCREEN_WIDTH = 800;
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        numberLabel = new JLabel(String.valueOf(wordNumber), SwingConstants.CENTER);
        numberLabel2 = new JLabel(String.valueOf(wordNumber), SwingConstants.CENTER);
        incrementButton = new RoundedButton("▲");
        decrementButton = new RoundedButton("▼");
        incrementButton2 = new RoundedButton("▲");
        decrementButton2 = new RoundedButton("▼");
        startButton = new RoundedButton("Start");

        // Set foreground color (font color) for labels
        numberLabel.setForeground(Color.WHITE);
        numberLabel2.setForeground(Color.WHITE);

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(100, 120);
        incrementButton.setPreferredSize(buttonSize);
        decrementButton.setPreferredSize(buttonSize);
        incrementButton2.setPreferredSize(buttonSize);
        decrementButton2.setPreferredSize(buttonSize);
        startButton.setPreferredSize(new Dimension(150, 80)); // Adjusting start button size

        // Set font size for labels
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 150);
        numberLabel.setFont(labelFont);
        numberLabel2.setFont(labelFont);
        numberLabel.setBackground(Color.BLACK);
        numberLabel2.setBackground(Color.BLACK);

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(Color.BLACK);
        northPanel.add(incrementButton);
        northPanel.add(incrementButton2);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(Color.BLACK);
        southPanel.add(decrementButton);
        southPanel.add(decrementButton2);

        //for the numlabels in the center panel
        JPanel spacerTop = new JPanel(); // Empty panel to offset Y-axis
        spacerTop.setPreferredSize(new Dimension(1, 400)); // Adjust height as needed
        spacerTop.setBackground(Color.BLACK);

        JPanel spacerBottom = new JPanel(); // Empty panel to offset Y-axis
        spacerBottom.setPreferredSize(new Dimension(1, 150)); // Adjust height as needed
        spacerBottom.setBackground(Color.BLACK);

        JPanel labelPanel = new JPanel(new GridLayout(1, 2));
        labelPanel.setBackground(Color.BLACK);
        labelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        labelPanel.add(numberLabel);
        labelPanel.add(numberLabel2);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(spacerTop, BorderLayout.NORTH); // Add spacer to offset Y-axis
        centerPanel.add(labelPanel, BorderLayout.CENTER);
        centerPanel.add(spacerBottom, BorderLayout.SOUTH); // Add spacer to offset Y-axis
        centerPanel.setBackground(Color.BLACK);

        JPanel westPanel = new JPanel(new BorderLayout()); // BorderLayout for text wrapping
        westPanel.setBackground(Color.BLACK);
        westPanel.setPreferredSize(new Dimension(135, 100)); // Set preferred size

        // Add text to the west panel with HTML formatting for text wrapping
        JLabel westLabel = new JLabel("<html><div style='text-align: center;'>Welcome to<br>Typing Game<br></div><div style='text-align: center;'><br>Choose the<br>lengt<br>of the passage</div></html>", SwingConstants.CENTER);
        westLabel.setForeground(Color.WHITE);
        westPanel.add(westLabel, BorderLayout.CENTER);

        JPanel startButtonPanel = new JPanel(new BorderLayout());
        JPanel emptyPanelAboveStartButton = new JPanel(); // Empty panel above start button
        JPanel emptyPanelUnderStartButton = new JPanel(); // Empty panel underneath start button
        emptyPanelAboveStartButton.setPreferredSize(new Dimension(150, 85)); // Set preferred size for the empty panel
        emptyPanelUnderStartButton.setPreferredSize(new Dimension(150, 85)); // Set preferred size for the empty panel
        emptyPanelAboveStartButton.setBackground(Color.BLACK);
        emptyPanelUnderStartButton.setBackground(Color.BLACK);
        startButtonPanel.add(emptyPanelAboveStartButton, BorderLayout.NORTH);
        startButtonPanel.add(startButton, BorderLayout.CENTER);
        startButtonPanel.setBackground(Color.BLACK);
        startButtonPanel.add(emptyPanelUnderStartButton, BorderLayout.SOUTH);

        numberLabel.setText(String.valueOf(firstnum)); //initial set
        numberLabel2.setText(String.valueOf(secondnum)); //initial set

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(southPanel, BorderLayout.SOUTH);
        panel.add(startButtonPanel, BorderLayout.EAST); // Add startButtonPanel to the east
        panel.add(westPanel, BorderLayout.WEST);

        incrementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (firstnum == 9) {
                    firstnum = 1;
                } else {
                    firstnum++;
                }
                numberLabel.setText(String.valueOf(firstnum));
            }
        });

        incrementButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (secondnum == 9) {
                    secondnum = 0;
                } else {
                    secondnum++;
                }
                numberLabel2.setText(String.valueOf(secondnum));
            }
        });

        decrementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (firstnum == 1) {
                    firstnum = 9;
                } else {
                    firstnum--;
                }
                numberLabel.setText(String.valueOf(firstnum));
            }
        });

        decrementButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (secondnum == 0) {
                    secondnum = 9;
                } else {
                    secondnum--;
                }
                numberLabel2.setText(String.valueOf(secondnum));
            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        add(panel);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private void startGame() {
        wordNumber = Integer.parseInt(String.valueOf(firstnum).concat(String.valueOf(secondnum)));
        Frame frame = new Frame(wordNumber);
        frame.setVisible(true); // Show the Frame
        dispose(); // Close the word number selection window
    }

    public void restartGame() {
        // Resetting game state
        firstnum = 1;
        secondnum = 0;
        numberLabel.setText(String.valueOf(firstnum));
        numberLabel2.setText(String.valueOf(secondnum));
        // Restarting the game
        initializeUI();
    }
}
