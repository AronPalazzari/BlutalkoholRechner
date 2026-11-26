package blutalkoholwertTester;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("berechnungsForm")
public class BerechnungsController {

    @ModelAttribute("berechnungsForm")
    public BerechnungsForm form() {
        return new BerechnungsForm();
    }

    @GetMapping("/start")
    public String start(Model model) {
        return "start";
    }

    @PostMapping("/start")
    public String startAbsenden(@Valid @ModelAttribute("berechnungsForm") BerechnungsForm berechnungsForm,
                            BindingResult bindingResult, Model  model) {
        if (bindingResult.hasErrors()) {
            return "start";
        }
        return "redirect:/alkohol";
    }

    @GetMapping("/alkohol")
    public String alkohol() {
        return "alkohol";
    }

    @PostMapping("/alkohol")
    public String alkoholAbsenden(@Valid @ModelAttribute("berechnungsForm") BerechnungsForm berechnungsForm,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "alkohol";
        }

        double alkoholGramm = berechnungsForm.getBierMenge()    * 0.05 * 0.8
                            + berechnungsForm.getWeinMenge()    * 0.12 * 0.8
                            + berechnungsForm.getKornMenge()    * 0.32 * 0.8
                            + berechnungsForm.getVodkaMenge()   * 0.40 * 0.8
                            + berechnungsForm.getWhiskeyMenge() * 0.43 * 0.8;

        double r = "m".equals(berechnungsForm.getGeschlecht()) ? 0.7 : 0.6;

        double promille = alkoholGramm / (berechnungsForm.getGewicht() * r);

        model.addAttribute("ergebnis", promille);
        return "alkohol";

    }

    @PostMapping("/neu")
    public String neu(SessionStatus status) {
        status.setComplete();   // Session-Objekt löschen
        return "redirect:/schritt1";
    }
}
