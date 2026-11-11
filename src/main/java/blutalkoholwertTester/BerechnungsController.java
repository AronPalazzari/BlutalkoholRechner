package blutalkoholwertTester;

import org.springframework.web.bind.annotation.GetMapping;

public class BerechnungsController {

    @GetMapping("/rechner")
    public String rechner(){
        return "rechner";
    }
}
