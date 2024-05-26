package weekendcompanies.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import weekendcompanies.example.demo.model.Bill;
import weekendcompanies.example.demo.model.Person;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"bills", "people"})
public class BillController {

    private List<Bill> bills = new ArrayList<>();
    private List<Person> people = new ArrayList<>();

    @GetMapping("/")
    public String showForm(Model model) {
        if (!model.containsAttribute("bills")) {
            model.addAttribute("bills", new ArrayList<Bill>());
        }
        if (!model.containsAttribute("people")) {
            model.addAttribute("people", new ArrayList<Person>());
        }
        return "billForm";
    }

    @PostMapping("/split")
    public String splitBill(@RequestParam double amount, @RequestParam int people, @RequestParam String description, Model model) {
        List<Bill> bills = (List<Bill>) model.getAttribute("bills");
        Bill bill = new Bill(amount, people, description);
        bills.add(bill);
        model.addAttribute("bills", bills);
        return "billForm";
    }

    @PostMapping("/addPerson")
    public String addPerson(@RequestParam String name, @RequestParam("descriptions") List<String> descriptions, Model model) {
        List<Bill> bills = (List<Bill>) model.getAttribute("bills");
        double totalAmount = bills.stream()
                .filter(bill -> descriptions.contains(bill.getDescription()))
                .mapToDouble(Bill::getSplitAmount)
                .sum();
        Person person = new Person(name, String.join(", ", descriptions), totalAmount);
        List<Person> people = (List<Person>) model.getAttribute("people");
        people.add(person);
        model.addAttribute("people", people);
        return "billForm";
    }

    @GetMapping("/clear")
    public String clearBills(Model model, SessionStatus status) {
        model.addAttribute("bills", new ArrayList<Bill>());
        model.addAttribute("people", new ArrayList<Person>());
        status.setComplete();
        return "redirect:/";
    }
}
