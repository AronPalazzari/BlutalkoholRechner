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
                            BindingResult bindingResult, Model  model) {
        if (bindingResult.hasErrors()) {
            return "rechner";
        }
        //System.out.println("Rechnung mit Gewicht: " + berechnungsForm.getGewicht());
        //Widmark Formel
        double alkoholGramm =
                berechnungsForm.getBierMenge()    * 0.05 * 0.8
                        + berechnungsForm.getWeinMenge()    * 0.12 * 0.8
                        + berechnungsForm.getKornMenge()    * 0.32 * 0.8
                        + berechnungsForm.getVodkaMenge()   * 0.40 * 0.8
                        + berechnungsForm.getWhiskeyMenge() * 0.43 * 0.8;

        double r = "m".equals(berechnungsForm.getGeschlecht()) ? 0.7 : 0.6;

        double promille = alkoholGramm / (berechnungsForm.getGewicht() * r);

        model.addAttribute("ergebnis", promille);
        return "rechner";
    }
}
