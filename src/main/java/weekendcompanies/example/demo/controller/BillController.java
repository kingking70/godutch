package weekendcompanies.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BillController {

    @GetMapping("/")
    public String showForm() {
        return "billForm";
    }

    @PostMapping("/split")
    public String splitBill(@RequestParam double amount, @RequestParam int people, Model model) {
        double splitAmount = amount / people;
        model.addAttribute("splitAmount", splitAmount);
        return "result";
    }
}
