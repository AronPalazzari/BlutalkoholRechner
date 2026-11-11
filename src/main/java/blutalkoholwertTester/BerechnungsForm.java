package blutalkoholwertTester;

import jakarta.validation.constraints.*;

public class BerechnungsForm {

    @NotNull(message = "Bitte Geschlecht auswählen")
    private String geschlecht;

    //Gewicht wird in kg gemessen
    @NotNull(message = "Bitte Gewicht eingeben")
    @DecimalMin(value = "6.5", message = "Bitte ein gültiges Gewicht angeben") //leichteste Person der Welt wiegt 6,5kg
    @DecimalMax(value = "635", message = "Bitte ein gültiges Gewicht angeben") //schwerste person der Welt wiegt 635kg
    private double gewicht;

    //Mengen werden in ml gemessen
    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private int bierMenge;

    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private int weinMenge;

    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private int kornMenge;

    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private int vodkaMenge;

    @Min(value = 0, message = "Die Menge kann nicht negativ sein")
    private int whiskeyMenge;

    public String getGeschlecht() {
        return geschlecht;
    }

    public double getGewicht() {
        return gewicht;
    }

    public int getBierMenge() {
        return bierMenge;
    }

    public int getWeinMenge() {
        return weinMenge;
    }

    public int getKornMenge() {
        return kornMenge;
    }

    public int getVodkaMenge() {
        return vodkaMenge;
    }

    public int getWhiskeyMenge() {
        return whiskeyMenge;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public void setBierMenge(int bierMenge) {
        this.bierMenge = bierMenge;
    }

    public void setWeinMenge(int weinMenge) {
        this.weinMenge = weinMenge;
    }

    public void setKornMenge(int kornMenge) {
        this.kornMenge = kornMenge;
    }

    public void setVodkaMenge(int vodkaMenge) {
        this.vodkaMenge = vodkaMenge;
    }

    public void setWhiskeyMenge(int whiskeyMenge) {
        this.whiskeyMenge = whiskeyMenge;
    }
}
