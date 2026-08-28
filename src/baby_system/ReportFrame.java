package baby_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReportFrame extends JFrame {
    private Baby baby;
    
    public ReportFrame(Baby baby) {
        this.baby = baby;
        setTitle("گزارش واکسیناسیون - " + baby.getName());
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon icon = new ImageIcon("baby_icon.png");
        setIconImage(icon.getImage());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0xFDF6FB));
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0xEA4BC2));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        JLabel title = new JLabel("گزارش واکسیناسیون");
        title.setFont(new Font("B Nazanin", Font.BOLD, 44));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel infoPanel = new JPanel(new GridLayout(1, 3, 30, 0));
        infoPanel.setBackground(new Color(0xF6B7E6));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        JLabel nameLabel = new JLabel("نام کودک: " + baby.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("B Nazanin", Font.BOLD, 30));
        nameLabel.setForeground(new Color(0x8A2C72));
        JLabel ageLabel = new JLabel("سن کودک: " + baby.getAge() + " ماه", SwingConstants.CENTER);
        ageLabel.setFont(new Font("B Nazanin", Font.BOLD, 30));
        ageLabel.setForeground(new Color(0x8A2C72));
        JLabel weightLabel = new JLabel("وزن کودک: " + baby.getWeight() + " کیلوگرم", SwingConstants.CENTER);
        weightLabel.setFont(new Font("B Nazanin", Font.BOLD, 30));
        weightLabel.setForeground(new Color(0x8A2C72));
        infoPanel.add(nameLabel);
        infoPanel.add(ageLabel);
        infoPanel.add(weightLabel);
        headerPanel.add(title, BorderLayout.NORTH);
        headerPanel.add(infoPanel, BorderLayout.SOUTH);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        VaccineSchedule schedule = new VaccineSchedule();
        List<Vaccine> currentVaccines = schedule.getVaccinesForAge(baby.getAge());
        List<Vaccine> nextVaccines = schedule.getNextVaccines(baby.getAge());
        JLabel currentLabel = new JLabel("واکسن‌های مورد نیاز این ماه:", SwingConstants.CENTER);
        currentLabel.setFont(new Font("B Nazanin", Font.BOLD, 32));
        currentLabel.setForeground(new Color(0xE51EB3));
        currentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        String[] columns = {"ماه", "نام واکسن", "توضیحات"};
        DefaultTableModel currentModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if (currentVaccines.isEmpty()) {
            currentModel.addRow(new Object[]{"-", "واکسن در این ماه موجود نیست", "در این ماه واکسنی ندارد"});
        } else {
            for (Vaccine v : currentVaccines) {
                currentModel.addRow(new Object[]{v.getMonth(), v.getName(), v.getDescription()});
            }
        }
        JTable currentTable = new JTable(currentModel);
        currentTable.setRowHeight(70);
        currentTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
        currentTable.getTableHeader().setFont(new Font("B Nazanin", Font.BOLD, 24));
        currentTable.getTableHeader().setBackground(new Color(0xF6B7E6));
        currentTable.setGridColor(new Color(0xEA4BC2));
        currentTable.setPreferredScrollableViewportSize(new Dimension(1200, 250));
        JScrollPane currentScroll = new JScrollPane(currentTable);
        currentScroll.setPreferredSize(new Dimension(1200, 280));
        currentScroll.setMaximumSize(new Dimension(1200, 280));
        JLabel nextLabel = new JLabel("واکسن‌های ماه‌های آینده:", SwingConstants.CENTER);
        nextLabel.setFont(new Font("B Nazanin", Font.BOLD, 32));
        nextLabel.setForeground(new Color(0xE51EB3));
        nextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        DefaultTableModel nextModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if (nextVaccines.isEmpty()) {
            nextModel.addRow(new Object[]{"-", "واکسن بعدی موجود نیست", "-"});
        } else {
            for (Vaccine v : nextVaccines) {
                nextModel.addRow(new Object[]{v.getMonth(), v.getName(), v.getDescription()});
            }
        }
        
        JTable nextTable = new JTable(nextModel);
        nextTable.setRowHeight(70);
        nextTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
        nextTable.getTableHeader().setFont(new Font("B Nazanin", Font.BOLD, 24));
        nextTable.getTableHeader().setBackground(new Color(0xF6B7E6));
        nextTable.setGridColor(new Color(0xEA4BC2));
        nextTable.setPreferredScrollableViewportSize(new Dimension(1200, 250));
        JScrollPane nextScroll = new JScrollPane(nextTable);
        nextScroll.setPreferredSize(new Dimension(1200, 280));
        nextScroll.setMaximumSize(new Dimension(1200, 280));
        JLabel adviceLabel = new JLabel("توصیه‌های مراقبتی:", SwingConstants.CENTER);
        adviceLabel.setFont(new Font("B Nazanin", Font.BOLD, 32));
        adviceLabel.setForeground(new Color(0xE51EB3));
        adviceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea adviceArea = new JTextArea(schedule.getCareAdvice(baby.getAge(), baby.getWeight()));
        adviceArea.setFont(new Font("B Nazanin", Font.PLAIN, 26));
        adviceArea.setBackground(new Color(0xFDF6FB));
        adviceArea.setEditable(false);
        adviceArea.setLineWrap(true);
        adviceArea.setWrapStyleWord(true);
        adviceArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane adviceScroll = new JScrollPane(adviceArea);
        adviceScroll.setPreferredSize(new Dimension(1200, 300));
        adviceScroll.setMaximumSize(new Dimension(1200, 250));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        buttonPanel.setBackground(Color.WHITE);
        JButton saveButton = new JButton("ذخیره در فایل");
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 28));
        saveButton.setBackground(new Color(0xF6B7E6));
        saveButton.setForeground(new Color(0x8A2C72));
        saveButton.setPreferredSize(new Dimension(260, 70));
        saveButton.setBorder(BorderFactory.createLineBorder(new Color(0xEA4BC2), 2));
        JButton exitButton = new JButton("خروج");
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 28));
        exitButton.setBackground(new Color(0xFFC7DD));
        exitButton.setForeground(new Color(0x8A2C72));
        exitButton.setPreferredSize(new Dimension(220, 70));
        exitButton.setBorder(BorderFactory.createLineBorder(new Color(0xE51EB3), 2));
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        saveButton.addActionListener(e -> {
            saveToFile(baby, schedule.getCareAdvice(baby.getAge(), baby.getWeight()), currentVaccines, nextVaccines);
        });
        
        
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        
        contentPanel.add(currentLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(currentScroll);
        contentPanel.add(Box.createVerticalStrut(35));
        contentPanel.add(nextLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(nextScroll);
        contentPanel.add(Box.createVerticalStrut(35));
        contentPanel.add(adviceLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(adviceScroll);
        contentPanel.add(Box.createVerticalStrut(35));
        contentPanel.add(buttonPanel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    
    private void saveToFile(Baby baby, String advice, List<Vaccine> currentVaccines, List<Vaccine> nextVaccines) {
        try {
            File file = new File("reporter.txt");
             FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println("========================================");
            out.println("گزارش واکسیناسیون نوزاد");
            out.println("نام کودک: " + baby.getName());
            out.println("سن کودک: " + baby.getAge() + " ماه");
            out.println("وزن کودک: " + baby.getWeight() + " کیلوگرم");
            out.println();
            
            // واکسن‌های این ماه
            out.println("واکسن‌های مورد نیاز این ماه:");
            if (currentVaccines.isEmpty()) {
                out.println("   واکسنی در این ماه موجود نیست");
            } else {
                for (Vaccine v : currentVaccines) {
                    out.println("   - " + v.getName() + " (" + v.getDescription() + ")");
                }
            }
            out.println();
            
            // واکسن‌های ماه‌های آینده
            out.println("واکسن‌های ماه‌ آینده:");
            if (nextVaccines.isEmpty()) {
                out.println("   واکسن بعدی موجود نیست");
            } else {
                for (Vaccine v : nextVaccines) {
                    out.println("   - " + v.getName() + " (" + v.getDescription() + ") - " + v.getMonth());
                }
            }
            out.println();
            
            out.println("توصیه های مراقبتی:");
            out.println(advice);
            out.println("========================================");
            out.println();
            
            out.close();
            bw.close();
            fw.close();
            
            JOptionPane.showMessageDialog(this, "اطلاعات در فایل reporter.txt ذخیره شد");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "خطا در ذخیره فایل: " + e.getMessage());
        }
    }
}