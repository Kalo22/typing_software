package typing_game;

import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        SwingUtilities.invokeLater(() -> { //making it threadsafe
          new WordNumberSelection(); //Creating an instance of the Main class - constructor will be called automatically
        });
    }
}