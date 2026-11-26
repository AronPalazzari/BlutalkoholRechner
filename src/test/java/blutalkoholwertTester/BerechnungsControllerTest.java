package blutalkoholwertTester;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.closeTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BerechnungsController.class)
public class BerechnungsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Die Startseite ist unter /start erreichbar")
    void startseite_erreichbar() throws Exception {
        mockMvc.perform(get("/start"))
                .andExpect(status().isOk())
                .andExpect(view().name("start"))
                .andExpect(model().attributeExists("berechnungsForm"));
    }

    @Test
    @DisplayName("/start mit gültigen Daten leitet auf /alkohol weiter")
    void start_leitet_weiter() throws Exception {
        mockMvc.perform(post("/start")
                        .param("geschlecht", "m")
                        .param("gewicht", "80"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alkohol"));
    }

    @Test
    @DisplayName("Bei Eingabe m80 und 500ml Bier kommt das korrekte Ergebnis von 0,357")
    void bier_bei_Mann_berechnet_korrekt() throws Exception {
        // 500 * 0,05 * 0,8 = 20 g  ->  20 / (80 * 0,7) = 0,357
        mockMvc.perform(post("/alkohol")
                        .param("geschlecht", "m")
                        .param("gewicht", "80")
                        .param("bierMenge", "500"))
                .andExpect(status().isOk())
                .andExpect(view().name("alkohol"))
                .andExpect(model().attribute("ergebnis", closeTo(0.357, 0.001)));
    }

    @Test
    @DisplayName("leere Mengen werden als null gezählt und akzeptier")
    void leere_Menge_zaehlt_als_null()  throws  Exception {
        mockMvc.perform(post("/alkohol")
                        .param("geschlecht", "m")
                        .param("gewicht", "80")
                        .param("bierMenge", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("ergebnis", closeTo(0.0, 0.001)));

    }

    @Test
    @DisplayName("fehlende Geschlechtsangabe wird als Validierungsfehler abgefagen")
    void fehlende_geschlechtsangabe_erzeugt_fehler()  throws  Exception {
        mockMvc.perform(post("/start")
                        .param("geschlecht", "")
                        .param("gewicht", "80"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("berechnungsForm", "geschlecht"))
                .andExpect(model().attributeDoesNotExist("ergebnis"));
    }

    @Test
    @DisplayName("zu niedriges gewicht erzeugt einen Validierungsfehler")
    void zu_niedriges_gewicht_erzeugt_fehler()   throws  Exception {
        mockMvc.perform(post("/start")
                        .param("geschlecht", "m")
                        .param("gewicht", "5"))
                .andExpect(model().attributeHasFieldErrors("berechnungsForm", "gewicht"))
                .andExpect(model().attributeDoesNotExist("ergebnis"));
    }

    @Test
    @DisplayName("negative Weinmenge erzuegt einen Validierungsfehler")
    void negative_weinmenge_erzeugt_fehler() throws  Exception {
        mockMvc.perform(post("/start")
                        .param("geschlecht", "m")
                        .param("gewicht", "80")
                        .param("weinMenge", "-5"))
                .andExpect(model().attributeHasFieldErrors("berechnungsForm", "weinMenge"));
    }
}
