package bongari;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongaus implements Cloneable {
    private int bongausId;
    private int jasenId;
    private int bongattavaId;
    private static int seuraavaNro = 1;
    private String kaupunki = "";
    private String pvm = ""; // Ehkä tulevaisuudessa date
    private String tietoja = "";
    
    
    /**
     * Palauttaa bongauksen id:n
     * @return bongauksen id
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setBongausId(1);
     * bongaus.getBongausId() === 1;
     * </pre>
     */
    public int getBongausId() {
        return this.bongausId;
    }
    
    /**
     * Asettaa bongaukselle id:n, samalla kasvattaa seuraavaNroa rekisteröintiä varten.
     * @param bongausId bongauksen id
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setBongausId(1);
     * bongaus.getBongausId() === 1;
     * </pre>
     */
    public void setBongausId(int bongausId) {
        this.bongausId = bongausId;
        if (seuraavaNro <= this.bongausId) seuraavaNro = this.bongausId + 1;
    }
    
    /**
     * Rekisteröi ja lisää bongauksen tietorakenteeseen
     * @return rekisteröidyn bongauksen id:n
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.rekisteroi();
     * bongaus.getBongausId() === 1;
     * Bongaus bongaus2 = new Bongaus();
     * bongaus2.rekisteroi();
     * bongaus2.getBongausId() === 2;
     * </pre>
     */
    public int rekisteroi() {
        this.bongausId = seuraavaNro;
        seuraavaNro++;
        return this.bongausId;
    }
     /**
     * Palauttaa bongauksen tehneen jäsenen id-numeron
     * @return bongauksen tehneen jäsenen id
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setJasenId(1);
     * bongaus.getJasenId() === 1;
     * </pre>
     */
    public int getJasenId() {
        return this.jasenId;
    }
    
    /**
     * Asettaa bongauksen löytäjän id-numeron
     * @param jasenId bongaajan id-numero
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setJasenId(1);
     * bongaus.getJasenId() === 1;
     * </pre>
     */
    public void setJasenId(int jasenId) {
        this.jasenId = jasenId;
    }
    
    /**
     * Palauttaa bongauksen kohteen id-numeron
     * @return bongattavan kohteen id
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setBongattavaId(1);
     * bongaus.getBongattavaId() === 1;
     * </pre>
     */
    public int getBongattavaId() {
        return this.bongattavaId;
    }
    
    /**
     * Asettaa bongattavan olion id-numeron
     * @param bongattavaId bongattavan olion id-numero
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setBongattavaId(1);
     * bongaus.getBongattavaId() === 1;
     * </pre>
     */
    public void setBongattavaId(int bongattavaId) {
        this.bongattavaId = bongattavaId;
    }
    
    /**
     * Asetetaan bongauksen kaupunki
     * @param kaupunki kaupunki, jossa bongaus tapahtui
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setKaupunki("Helsinki");
     * bongaus.getKaupunki() === "Helsinki";
     * </pre>
     */
    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }
    
    /**
     * Palauttaa bongauksen kaupungin nimen
     * @return kaupungin nimi, jossa löytö tapahtui
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setKaupunki("Helsinki");
     * bongaus.getKaupunki() === "Helsinki";
     * </pre>
     */
    public String getKaupunki() {
        return this.kaupunki;
    }
    
    /**
     * Asetetaan bongauksen päivämäärä
     * @param pvm päivämäärä, jona bongaus tapahtui (TODO: muotoa dd/mm/yyyy)
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setPvm("12/01/1999");
     * bongaus.getPvm() === "12/01/1999";
     * </pre>
     */
    public void setPvm(String pvm) {
        this.pvm = pvm;
    }
    
    /**
     * Palauttaa bongauksen päivämäärän
     * @return löydön päivämäärä
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setPvm("12/01/1999");
     * bongaus.getPvm() === "12/01/1999";
     * </pre>
     */
    public String getPvm() {
        return this.pvm;
    }
    
    /**
     * Bongauksen lisätiedot, mitä käyttäjä haluaa ilmoittaa
     * @param tietoja lisätietoja bongauksesta
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setTietoja("tietoja");
     * bongaus.getTietoja() === "tietoja";
     * </pre>
     */
    public void setTietoja(String tietoja) {
        this.tietoja = tietoja;
    }
    
    /**
     * Palauttaa lisätietoja bongauksesta
     * @return bongauksen lisätiedot
     * @example
     * <pre name="test">
     * Bongaus bongaus = new Bongaus();
     * bongaus.setTietoja("tietoja");
     * bongaus.getTietoja() === "tietoja";
     * </pre>
     */
    public String getTietoja() {
        return this.tietoja;
    }
      
    /**
     * Parsii tiedostosta rivi kerrallaan otuksia
     * @param rivi tuotava rivi luettavasta tiedostosta
     * @throws ExceptionHandler heitettävä kustomoitu exception
     * @example
     * <pre name="test">
     * #THROWS ExceptionHandler
     * Bongaus bongaus = new Bongaus();
     * bongaus.parse("1|1|2|Helsinki|12/01/2009|Lisätietoja");
     * bongaus.getBongausId() === 1;
     * bongaus.getJasenId() === 1;
     * bongaus.getBongattavaId() === 2;
     * bongaus.getPvm() === "12/01/2009";
     * bongaus.getTietoja() === "Lisätietoja";
     * </pre>
     */
    public void parse(String rivi) throws ExceptionHandler {
        try {
        StringBuffer sb = new StringBuffer(rivi);
        String olio_bongausId = Mjonot.erota(sb, '|');
        this.setBongausId(Integer.parseInt(olio_bongausId));
        String olio_jasenId = Mjonot.erota(sb,'|');
        this.setJasenId(Integer.parseInt(olio_jasenId));
        String olio_bongattavaId = Mjonot.erota(sb,'|');
        this.setBongattavaId(Integer.parseInt(olio_bongattavaId));
        String olio_kaupunki = Mjonot.erota(sb, '|');
        this.setKaupunki(olio_kaupunki);
        String olio_pvm = Mjonot.erota(sb, '|');
        this.setPvm(olio_pvm);
        this.setTietoja(sb.toString());
        } catch (Exception ex) {
            throw new ExceptionHandler("Tietojen hakemisessa meni joku rikki " + ex.getMessage());
        }       
    }
    
    /**
     * Tulostetaan bongattavan olion tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(this.bongausId + ": " + this.jasenId + " " + this.bongattavaId + " " + this.kaupunki + " " + this.pvm + " " + this.tietoja);
    }


    /**
     * Tulostetaan bongattavan olion tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    @Override
    public Bongaus clone() throws CloneNotSupportedException {
        Bongaus uusi;
        uusi = (Bongaus) super.clone();
        return uusi;
    }

}
