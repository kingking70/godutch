package weekendcompanies.example.demo.model;


import lombok.Data;

@Data
public class Bill {
    private double amount;
    private int people;
    private String description;
    private double splitAmount;

    public Bill(double amount, int people, String description) {
        this.amount = amount;
        this.people = people;
        this.description = description;
        this.splitAmount = amount / people;
    }
}
