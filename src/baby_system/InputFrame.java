package baby_system;

import javax.swing.*;
import java.awt.*;

public class InputFrame extends JFrame {
    public InputFrame() {
        setTitle("ورود اطلاعات نوزاد");
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setResizable(false);   
        
            ImageIcon icon = new ImageIcon("baby_icon.png");
            setIconImage(icon.getImage());
         
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0xFDF6FB));
        JLabel title = new JLabel("لطفا اطلاعات نوزاد را وارد کنید");
        title.setFont(new Font("B Nazanin", Font.BOLD, 44));
        title.setForeground(new Color(0xE51EB3)); 
        title.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 40, 50));
        inputPanel.setBackground(new Color(0xFBE4F5)); 
        inputPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));  
        JLabel nameLabel = new JLabel("نام کودک:", SwingConstants.CENTER);
        nameLabel.setFont(new Font("B Nazanin", Font.BOLD, 30));
        nameLabel.setForeground(new Color(0x8A2C72));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("B Nazanin", Font.PLAIN, 26));
        nameField.setBorder(BorderFactory.createLineBorder(new Color(0xEA4BC2), 1)); 
        JLabel ageLabel = new JLabel("سن کودک (ماه):", SwingConstants.CENTER);
        ageLabel.setFont(new Font("B Nazanin", Font.BOLD, 30));
        ageLabel.setForeground(new Color(0x8A2C72));
        JTextField ageField = new JTextField();
        ageField.setFont(new Font("B Nazanin", Font.PLAIN, 26));
        ageField.setBorder(BorderFactory.createLineBorder(new Color(0xEA4BC2), 1));
        JLabel weightLabel = new JLabel("وزن کودک (کیلوگرم):", SwingConstants.CENTER);
        weightLabel.setFont(new Font("B Nazanin", Font.BOLD, 30));
        weightLabel.setForeground(new Color(0x8A2C72));
        JTextField weightField = new JTextField();
        weightField.setFont(new Font("B Nazanin", Font.PLAIN, 26));
        weightField.setBorder(BorderFactory.createLineBorder(new Color(0xEA4BC2), 1));
        JButton submitButton = new JButton("ثبت و ادامه");
        submitButton.setFont(new Font("B Nazanin", Font.BOLD, 28));
        submitButton.setBackground(new Color(0xF6B7E6)); 
        submitButton.setForeground(new Color(0x8A2C72));
        submitButton.setPreferredSize(new Dimension(220, 60));
        submitButton.setBorder(BorderFactory.createLineBorder(new Color(0xEA4BC2), 2));
        JButton backButton = new JButton("بازگشت");
        backButton.setFont(new Font("B Nazanin", Font.BOLD, 28));
        backButton.setBackground(new Color(0xFFC7DD)); 
        backButton.setForeground(new Color(0x8A2C72));
        backButton.setPreferredSize(new Dimension(200, 60));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0xE51EB3), 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(backButton);
        inputPanel.add(submitButton);
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());
                
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "لطفا نام کودک را وارد کنید");
                    return;
                }
                
                if (age < 0 || age > 18) {
                    JOptionPane.showMessageDialog(this, "سن باید بین 0 تا 18 ماه باشد");
                    return;
                }
                
                if (weight <= 0 || weight > 20) {
                    JOptionPane.showMessageDialog(this, "وزن باید بین 0.1 تا 20 کیلوگرم باشد");
                    return;
                }
                
                Baby baby = new Baby(name, age, weight);
                new ReportFrame(baby).setVisible(true);
                dispose();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "لطفا اعداد صحیح وارد کنید");
            }
        });
        
        backButton.addActionListener(e -> {
            new WelcomeFrame().setVisible(true);
            dispose();
        });
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}