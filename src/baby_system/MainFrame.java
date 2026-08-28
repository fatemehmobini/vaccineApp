package baby_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        
    	setTitle("برنامه واکسیناسیون نوزاد");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(100, 60, 150));
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(120, 80, 170));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(60, 0, 60, 0));
        JLabel titleLabel = new JLabel("برنامه واکسیناسیون نوزاد", SwingConstants.CENTER);
        titleLabel.setFont(new Font("B Nazanin", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 20, 20));
        inputPanel.setBackground(new Color(240, 230, 255));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground(new Color(240, 230, 255));
        JLabel nameLabel = new JLabel("نام نوزاد:");
        nameLabel.setFont(new Font("B Nazanin", Font.BOLD, 22));
        nameLabel.setForeground(new Color(80, 60, 100));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("B Nazanin", Font.PLAIN, 18));
        nameField.setPreferredSize(new Dimension(300, 40));
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(nameField, BorderLayout.CENTER);
        JPanel agePanel = new JPanel(new BorderLayout());
        agePanel.setBackground(new Color(240, 230, 255));
        JLabel ageLabel = new JLabel("سن نوزاد (ماه - 0 تا 18):");
        ageLabel.setFont(new Font("B Nazanin", Font.BOLD, 22));
        ageLabel.setForeground(new Color(80, 60, 100));
        JTextField ageField = new JTextField();
        ageField.setFont(new Font("B Nazanin", Font.PLAIN, 18));
        ageField.setPreferredSize(new Dimension(300, 40));
        agePanel.add(ageLabel, BorderLayout.WEST);
        agePanel.add(ageField, BorderLayout.CENTER);
        JPanel weightPanel = new JPanel(new BorderLayout());
        weightPanel.setBackground(new Color(240, 230, 255));
        JLabel weightLabel = new JLabel("وزن نوزاد (کیلوگرم):");
        weightLabel.setFont(new Font("B Nazanin", Font.BOLD, 22));
        weightLabel.setForeground(new Color(80, 60, 100));
        JTextField weightField = new JTextField();
        weightField.setFont(new Font("B Nazanin", Font.PLAIN, 18));
        weightField.setPreferredSize(new Dimension(300, 40));
        weightPanel.add(weightLabel, BorderLayout.WEST);
        weightPanel.add(weightField, BorderLayout.CENTER);
        inputPanel.add(namePanel);
        inputPanel.add(agePanel);
        inputPanel.add(weightPanel);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBackground(new Color(240, 230, 255));
        JButton submitButton = new JButton("نمایش گزارش");
        submitButton.setFont(new Font("B Nazanin", Font.BOLD, 22));
        submitButton.setBackground(new Color(150, 100, 200));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(200, 50));
        JButton exitButton = new JButton("خروج");
        exitButton.setFont(new Font("B Nazanin", Font.BOLD, 22));
        exitButton.setBackground(new Color(200, 100, 120));
        exitButton.setForeground(Color.WHITE);
        exitButton.setPreferredSize(new Dimension(150, 50));   
        buttonPanel.add(submitButton);
        buttonPanel.add(exitButton);
 
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText().trim();
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(MainFrame.this, "لطفاً نام نوزاد را وارد کنید");
                        return;
                    }
                    
                    int age = Integer.parseInt(ageField.getText().trim());
                    if (age < 0 || age > 18) {
                        JOptionPane.showMessageDialog(MainFrame.this, "سن باید بین 0 تا 18 ماه باشد");
                        return;
                    }
                    
                    double weight = Double.parseDouble(weightField.getText().trim());
                    if (weight <= 0 || weight > 20) {
                        JOptionPane.showMessageDialog(MainFrame.this, "وزن باید بین 0.1 تا 20 کیلوگرم باشد");
                        return;
                    }
                    
                    Baby baby = new Baby(name, age, weight);
                    ReportFrame reportFrame = new ReportFrame(baby);
                    reportFrame.setVisible(true);
                    dispose();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "لطفاً اعداد صحیح وارد کنید");
                }
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        inputPanel.add(buttonPanel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER); 
        add(mainPanel);
    }
}