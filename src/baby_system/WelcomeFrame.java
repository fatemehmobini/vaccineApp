package baby_system;

import javax.swing.*;
import java.awt.*;

public class WelcomeFrame extends JFrame {
    
    public WelcomeFrame() {
        setTitle("اپ واکسیناسیون نوزادان");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
         ImageIcon icon = new ImageIcon("baby_icon.png");
         setIconImage(icon.getImage());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0xFDF6FB));
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0xEA4BC2));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        JLabel titleLabel = new JLabel("برنامه واکسیناسیون نوزادان");
        titleLabel.setFont(new Font("B Nazanin", Font.BOLD, 42));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));  
        JLabel welcomeLabel = new JLabel("مراقب کودکت باش:)");
        welcomeLabel.setFont(new Font("B Nazanin", Font.BOLD, 38));
        welcomeLabel.setForeground(new Color(0xE51EB3));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  
        JLabel description = new JLabel("این برنامه برای برنامه‌ریزی واکسیناسیون نوزادان طراحی شده است");
        description.setFont(new Font("B Nazanin", Font.PLAIN, 26));
        description.setForeground(new Color(0x666666));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel featuresPanel = new JPanel();
        featuresPanel.setLayout(new GridLayout(2, 2, 30, 30));
        featuresPanel.setBackground(Color.WHITE);
        featuresPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        JPanel feature1 = createFeaturePanel("واکسن‌های این ماه", "مشاهده واکسن‌های مورد نیاز در ماه جاری");
        JPanel feature2 = createFeaturePanel("واکسن‌های آینده", "برنامه واکسیناسیون ماه‌های آینده");
        JPanel feature3 = createFeaturePanel("توصیه‌های پزشکی", "دریافت توصیه‌های مراقبتی مناسب");
        JPanel feature4 = createFeaturePanel("گزارش کامل", "ذخیره اطلاعات در فایل گزارش");
        featuresPanel.add(feature1);
        featuresPanel.add(feature2);
        featuresPanel.add(feature3);
        featuresPanel.add(feature4);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        JButton startButton = new JButton("شروع برنامه");
        startButton.setFont(new Font("B Nazanin", Font.BOLD, 28));
        startButton.setBackground(new Color(0xF6B7E6));
        startButton.setForeground(new Color(0x8A2C72));
        startButton.setPreferredSize(new Dimension(250, 70));
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xEA4BC2), 2),
            BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));
        
        JButton exitButton = new JButton("خروج");
        exitButton.setFont(new Font("B Nazanin", Font.BOLD, 28));
        exitButton.setBackground(new Color(0xFFC7DD));
        exitButton.setForeground(new Color(0x8A2C72));
        exitButton.setPreferredSize(new Dimension(200, 70));
        exitButton.setFocusPainted(false);
        exitButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xE51EB3), 2),
            BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));
        
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        contentPanel.add(welcomeLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(description);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(featuresPanel);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(buttonPanel);
        startButton.addActionListener(e -> {
        	
            new InputFrame().setVisible(true);
            
            dispose();
        
        });
        
        exitButton.addActionListener(e -> {
           
        	System.exit(0);
        });
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JPanel createFeaturePanel(String title, String description) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0xFBE4F5));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xEA4BC2), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        )); 
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("B Nazanin", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0xE51EB3));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("B Nazanin", Font.PLAIN, 18));
        descLabel.setForeground(new Color(0x666666));
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(descLabel, BorderLayout.CENTER);
        return panel;
    }
}