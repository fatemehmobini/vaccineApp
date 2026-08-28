package baby_system;
import java.io.*;
import java.util.*;

public class VaccineSchedule {
    private List<Vaccine> vaccines;
    
    public VaccineSchedule() {
        vaccines = new ArrayList<>();
        loadVaccines();
    }
    
    private void loadVaccines() {
        try {
            File file = new File("IR_vaccine.csv");
           
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            br.readLine();
            String currentMonth = "";
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                
                if (parts.length >= 3) {
                    String month = parts[0].trim();
                    String name = parts[1].trim();
                    String desc = parts[2].trim();
                    
                    if (!month.isEmpty()) {
                        currentMonth = month;
                    }
                    
                    if (!name.isEmpty()) {
                        vaccines.add(new Vaccine(currentMonth, name, desc));
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("خطا در خواندن فایل واکسن");
        }
    }
    
    public List<Vaccine> getVaccinesForAge(int age) {
        List<Vaccine> result = new ArrayList<>();
        String ageText = getAgeText(age);
        
        for (Vaccine v : vaccines) {
           
            int vaccineMonth = extractMonthNumber(v.getMonth());
            if (vaccineMonth == age || v.getMonth().equals(ageText)) {
                result.add(v);
            }
        }
        return result;
    }
    
    public List<Vaccine> getNextVaccines(int currentAge) {
        List<Vaccine> result = new ArrayList<>();
        
     
        List<Integer> availableMonths = new ArrayList<>();
        for (Vaccine v : vaccines) {
            int month = extractMonthNumber(v.getMonth());
            if (!availableMonths.contains(month)) {
                availableMonths.add(month);
            }
        }
        
        
        Collections.sort(availableMonths);
        
      
        int nextMonth = -1;
        for (int month : availableMonths) {
            if (month > currentAge) {
                nextMonth = month;
                break;
            }
        }
        
        if (nextMonth != -1) {
            for (Vaccine v : vaccines) {
                int vaccineMonth = extractMonthNumber(v.getMonth());
                if (vaccineMonth == nextMonth) {
                    result.add(v);
                }
            }
        }
        
        return result;
    }
    
    private int extractMonthNumber(String month) {
        try {
            if (month.contains("بدو تولد")) return 0;
            if (month.contains("تولد")) return 0;
            
            
            String numStr = month.replaceAll("[^۰-۹0-9]", "");
            if (!numStr.isEmpty()) {
               
                return Integer.parseInt(numStr
                    .replace('۰', '0').replace('۱', '1')
                    .replace('۲', '2').replace('۳', '3')
                    .replace('۴', '4').replace('۵', '5')
                    .replace('۶', '6').replace('۷', '7')
                    .replace('۸', '8').replace('۹', '9'));
            }
        } catch (Exception e) {}
        return -1;
    }
    
    private String getAgeText(int age) {
        if (age == 0) return "بدو تولد";
        if (age == 1) return "۱ ماهگی";
        if (age == 2) return "۲ ماهگی";
        if (age == 3) return "۳ ماهگی";
        if (age == 4) return "۴ ماهگی";
        if (age == 5) return "۵ ماهگی";
        if (age == 6) return "۶ ماهگی";
        if (age == 7) return "۷ ماهگی";
        if (age == 8) return "۸ ماهگی";
        if (age == 9) return "۹ ماهگی";
        if (age == 10) return "۱۰ ماهگی";
        if (age == 11) return "۱۱ ماهگی";
        if (age == 12) return "۱۲ ماهگی";
        if (age == 18) return "۱۸ ماهگی";
        return age + " ماهگی";
    }
    
    public String getCareAdvice(int month, double weight) {
        StringBuilder advice = new StringBuilder();
        
        if (month == 0) {
            advice.append("تغذیه: شیر مادر هر 2-3 ساعت\n");
            advice.append("خواب: 16-18 ساعت در روز\n");
            advice.append("فعالیت: نگاه کردن به چهره\n");
            advice.append("ایمنی: دمای اتاق 24-26 درجه\n");
        } else if (month == 2) {
            advice.append("تغذیه: شیر مادر ادامه دارد\n");
            advice.append("خواب: 14-15 ساعت در روز\n");
            advice.append("فعالیت: تشویق به غلت زدن\n");
            advice.append("ایمنی: تمیز کردن لثه‌ها\n");
        } else if (month == 4) {
            advice.append("تغذیه: شیر مادر + قطره آهن\n");
            advice.append("خواب: 14-15 ساعت در روز\n");
            advice.append("فعالیت: بازی با اسباب‌بازی‌های نرم\n");
            advice.append("ایمنی: دوری از افراد بیمار\n");
        } else if (month == 6) {
            advice.append("تغذیه: شروع غذای کمکی\n");
            advice.append("خواب: 13-14 ساعت در روز\n");
            advice.append("فعالیت: نشستن با کمک\n");
            advice.append("ایمنی: مراقبت از دندان‌های در حال رشد\n");
        } else if (month == 12) {
            advice.append("تغذیه: غذای خانواده\n");
            advice.append("خواب: 12-14 ساعت در روز\n");
            advice.append("فعالیت: راه رفتن با کمک\n");
            advice.append("ایمنی: مراقبت از اشیاء کوچک\n");
        } else if (month == 18) {
            advice.append("تغذیه: غذای کامل خانواده\n");
            advice.append("خواب: 11-13 ساعت در روز\n");
            advice.append("فعالیت: بازی‌های فکری\n");
            advice.append("ایمنی: آموزش مفاهیم ساده\n");
        } else {
            advice.append("تغذیه: شیر مادر ادامه دارد\n");
            advice.append("خواب: 12-16 ساعت در روز\n");
            advice.append("فعالیت: بازی‌های ساده\n");
            advice.append("ایمنی: نظافت روزانه\n");
        }
        
        double idealWeight = 3.5 + (month * 0.6);
        advice.append("\nوزن فعلی: ").append(String.format("%.1f", weight)).append(" کیلوگرم\n");
        advice.append("وزن ایده‌آل: ").append(String.format("%.1f", idealWeight)).append(" کیلوگرم\n");
        
        if (weight < idealWeight * 0.9) {
            advice.append("وضعیت: نیاز به پیگیری پزشک\n");
        } else if (weight > idealWeight * 1.1) {
            advice.append("وضعیت: نیاز به تنظیم برنامه\n");
        } else {
            advice.append("وضعیت: وزن مناسب\n");
        }
        
        return advice.toString();
    }
}