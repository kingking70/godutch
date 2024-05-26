package weekendcompanies.example.demo.model;

import lombok.Data;

@Data
public class Person {
    private String name;
    private String description;
    private double totalAmount;
    public Person(String name, String description, double totalAmount) {
        this.name = name;
        this.description = description;
        this.totalAmount = totalAmount;
    }
}
