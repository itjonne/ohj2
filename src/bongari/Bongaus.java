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
public class Bongaus {
    private int jasenId;
    private int bongattavaId;
    private String kaupunki = "";
    private String pvm = ""; // Ehkä tulevaisuudessa date
    private String tietoja = "";
    
    /**
     * Palauttaa bongauksen tehneen jäsenen id-numeron
     * @return bongauksen tehneen jäsenen id
     */
    public int getJasenId() {
        return this.jasenId;
    }
    
    /**
     * Asettaa bongauksen löytäjän id-numeron
     * @param jasenId bongaajan id-numero
     */
    public void setJasenId(int jasenId) {
        this.jasenId = jasenId;
    }
    
    /**
     * Palauttaa bongauksen kohteen id-numeron
     * @return bongattavan kohteen id
     */
    public int getBongattavaId() {
        return this.bongattavaId;
    }
    
    /**
     * Asettaa bongattavan olion id-numeron
     * @param bongattavaId bongattavan olion id-numero
     */
    public void setBongattavaId(int bongattavaId) {
        this.bongattavaId = bongattavaId;
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
      
    /**
     * Parsii tiedostosta rivi kerrallaan otuksia
     * @param rivi tuotava rivi luettavasta tiedostosta
     * @throws ExceptionHandler heitettävä kustomoitu exception
     */
    public void parse(String rivi) throws ExceptionHandler {
        try {
        StringBuffer sb = new StringBuffer(rivi);
        
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
        out.println(this.jasenId + " " + this.bongattavaId + " " + this.kaupunki + " " + this.pvm + " " + this.tietoja);
    }


    /**
     * Tulostetaan bongattavan olion tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

}
