package blutalkoholwertTester;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BerechnungsController {

    @GetMapping("/rechner")
    public String rechner(Model model) {

        model.addAttribute("berechnungsForm", new BerechnungsForm());
        return "rechner";
    }

    @PostMapping("/rechner")
    public String berechnen(@Valid @ModelAttribute("berechnungsForm") BerechnungsForm berechnungsForm,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rechner";
        }
        System.out.println("Rechnung mit Gewicht: " + berechnungsForm.getGewicht());
        return "rechner";
    }
}
