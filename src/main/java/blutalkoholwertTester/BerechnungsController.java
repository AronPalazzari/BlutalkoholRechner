package blutalkoholwertTester;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BerechnungsController {

    @GetMapping("/rechner")
    public String rechner(Model model) {

        model.addAttribute("berechnungsForm", new BerechnungsForm());
        return "rechner";
    }
}
