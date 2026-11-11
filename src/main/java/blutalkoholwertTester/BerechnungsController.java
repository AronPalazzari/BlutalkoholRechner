package blutalkoholwertTester;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BerechnungsController {

    @GetMapping("/rechner")
    public String rechner(){
        return "rechner";
    }
}
