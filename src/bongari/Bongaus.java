package bongari;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongaus {
    private int jasenId;
    private int bongattavaId;
    private String kaupunki = "";
    private String pvm = ""; // Ehkä tulevaisuudessa date
    private String tietoja = "";
    
    /**
     * Alustetaan bongaus-olio
     * @param jasenId jäsen joka bongauksen teki
     * @param bongattavaId bongattavan otuksen id
     */
    public Bongaus(int jasenId, int bongattavaId) {
        this.jasenId = jasenId;
        this.bongattavaId = bongattavaId;
    }
    
    /**
     * Palauttaa bongauksen tehneen jäsenen id-numeron
     * @return bongauksen tehneen jäsenen id
     */
    public int getJasenId() {
        return this.jasenId;
    }
    
    /**
     * Palauttaa bongauksen kohteen id-numeron
     * @return bongattavan kohteen id
     */
    public int getBongattavaId() {
        return this.bongattavaId;
    }
    
    /**
     * Asetetaan bongauksen kaupunki
     * @param kaupunki kaupunki, jossa bongaus tapahtui
     */
    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }
    
    /**
     * Asetetaan bongauksen päivämäärä
     * @param pvm päivämäärä, jona bongaus tapahtui (TODO: muotoa dd/mm/yyyy)
     */
    public void setPvm(String pvm) {
        this.pvm = pvm;
    }
    
    /**
     * Bongauksen lisätiedot, mitä käyttäjä haluaa ilmoittaa
     * @param tietoja lisätietoja bongauksesta
     */
    public void setTietoja(String tietoja) {
        this.tietoja = tietoja;
    }
}
