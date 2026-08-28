package baby_system;

public class Vaccine {
    private String month;
    private String name;
    private String description;
    
    public Vaccine(String month, String name, String description) {
        this.month = month;
        this.name = name;
        this.description = description;
    }
    
    public String getMonth() { return month; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}
