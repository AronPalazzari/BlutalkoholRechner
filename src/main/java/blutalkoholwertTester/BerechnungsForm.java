package blutalkoholwertTester;

import jakarta.validation.constraints.*;

public class BerechnungsForm {

    @NotBlank(message = "Bitte Geschlecht auswählen")
    @Pattern(regexp = "[mw]", message = "Ungültiges Geschlecht")
    private String geschlecht;

    //Gewicht wird in kg gemessen
    @NotNull(message = "Bitte Gewicht eingeben")
    @DecimalMin(value = "6.5", message = "Bitte ein gültiges Gewicht angeben") //leichteste Person der Welt wiegt 6,5kg
    @DecimalMax(value = "635", message = "Bitte ein gültiges Gewicht angeben") //schwerste person der Welt wiegt 635kg
    private Double gewicht;

    //Mengen werden in ml gemessen
    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private Integer bierMenge;

    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private Integer weinMenge;

    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private Integer kornMenge;

    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private Integer vodkaMenge;

    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private Integer whiskeyMenge;

    public String getGeschlecht() {
        return geschlecht;
    }

    public Double getGewicht() {
        return gewicht;
    }

    public Integer getBierMenge() {
        return bierMenge;
    }

    public Integer getWeinMenge() {
        return weinMenge;
    }

    public Integer getKornMenge() {
        return kornMenge;
    }

    public Integer getVodkaMenge() {
        return vodkaMenge;
    }

    public Integer getWhiskeyMenge() {
        return whiskeyMenge;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public void setGewicht(Double gewicht) {
        this.gewicht = gewicht;
    }

    public void setBierMenge(Integer bierMenge) {
        this.bierMenge = bierMenge;
    }

    public void setWeinMenge(Integer weinMenge) {
        this.weinMenge = weinMenge;
    }

    public void setKornMenge(Integer kornMenge) {
        this.kornMenge = kornMenge;
    }

    public void setVodkaMenge(Integer vodkaMenge) {
        this.vodkaMenge = vodkaMenge;
    }

    public void setWhiskeyMenge(Integer whiskeyMenge) {
        this.whiskeyMenge = whiskeyMenge;
    }
}
