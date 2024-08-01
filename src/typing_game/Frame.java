package typing_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

// Inherits JFrame and Implements interfaces ActionListener and KeyListener
public class Frame extends JFrame implements ActionListener, KeyListener {

    String passage = "";
    int SCREEN_HEIGHT;
    int SCREEN_WIDTH;
    private JPanel panel;
    //private int wordNumber = 10;
    private int wordNum;
    private StringBuilder input = new StringBuilder();
    private StringBuilder errInput = new StringBuilder();
    CustomRender buffer;
    RandomWordSelector randSelector;

    // Create a start button panel
    JPanel startButtonPanel = new JPanel();

    JButton startButton = new JButton("Start");

    public Frame(int wordNumber) {
        setTitle("Typing Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate when X is pressed
        SCREEN_HEIGHT = 700;
        SCREEN_WIDTH = 800;
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.getContentPane().setBackground(Color.BLACK);

        wordNum = wordNumber;

        // Create a main panel to hold components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        // Add main panel to the frame
        this.add(mainPanel);

        this.setResizable(true);
        setFocusable(true); // container is focused

        getPassage();

        this.setLocationRelativeTo(null); // centers it on the screen

        this.addKeyListener(this); // adds keyListener to the current object
        buffer = new CustomRender(getWidth(), getHeight()); // creates the buffer
        repaintBuffered(); // initial paint to set the buffer

        this.addComponentListener(new java.awt.event.ComponentAdapter() { // component listener to handle resizing events
            public void componentResized(java.awt.event.ComponentEvent evt) {
                repaintBuffered();
                resizeBuffered(); //resizing the buffer does not work when in start screen
            }
        });

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buffer.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public void getPassage() { // TODO implement passage database
        randSelector = new RandomWordSelector();
        this.passage = randSelector.getRandomWords("resources/words.txt", wordNum);
        System.out.println(passage);
    }

    private void repaintBuffered() {
        String inputString = input.toString();
        String errString = errInput.toString();
        buffer.refreshBuffer(passage, inputString, errString);
        repaint();
    }

    private void resizeBuffered() {
        buffer.resizeBuffer(getWidth(), getHeight());
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if(!buffer.finish) {
            buffer.hasError = hasErr(e, input, errInput);
            if (buffer.hasError) typeDest(e, errInput, input);
            else typeDest(e, input, errInput);
        }
        repaintBuffered();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE && input.length() > 0 && !buffer.finish) {
            input.deleteCharAt(input.length() - 1);
            errInput.deleteCharAt(input.length());
            buffer.deletePress = true;
        }
        repaintBuffered();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void typeDest(KeyEvent e, StringBuilder dest, StringBuilder fillInBlank) {

        if(e.getKeyChar() != KeyEvent.VK_BACK_SPACE) { //handles backspace
            if(dest.length() == 0) {
                startTimer();
            }
            if (buffer.hasError && e.getKeyChar() == KeyEvent.VK_SPACE) dest.append("_");
            else dest.append(e.getKeyChar());
            fillInBlank.append(" ");
        }
    }

    private void startTimer() {
        buffer.startTime = System.currentTimeMillis();
        System.out.println("time tracker started!");
    }

    private boolean hasErr(KeyEvent e, StringBuilder input, StringBuilder errInput) {

        String inputStr = input.toString();
        String errStr = errInput.toString();

        if(e.getKeyChar() != KeyEvent.VK_BACK_SPACE && e.getKeyChar() != passage.charAt(inputStr.length())) return true;
        else if(e.getKeyChar() == passage.charAt(inputStr.length())) return false;
        return false;
    }
}
