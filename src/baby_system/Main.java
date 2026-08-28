package baby_system;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {}
            
            WelcomeFrame frame = new WelcomeFrame();
            frame.setVisible(true);
        });
    }
}