package baby_system;

public class Baby {
    private String name;
    private int age;
    private double weight;
    
    public Baby(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
}