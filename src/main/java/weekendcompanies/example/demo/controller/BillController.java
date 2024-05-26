package weekendcompanies.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import weekendcompanies.example.demo.model.Bill;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("bills")
public class BillController {

    @GetMapping("/")
    public String showForm(Model model) {
        if (!model.containsAttribute("bills")) {
            model.addAttribute("bills", new ArrayList<Bill>());
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

    @GetMapping("/clear")
    public String clearBills(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }
}
