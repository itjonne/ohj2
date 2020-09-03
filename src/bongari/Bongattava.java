package bongari;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongattava {
    private int bongattavaId;
    private String nimi = "";
    private String tieteellinen_nimi = "";
    private String heimo = "";
    private String laji = "";
    
    /**
     * 
     * @param bongattavaId bongattavan olion id-numero
     */
    public Bongattava(int bongattavaId) {
        this.bongattavaId = bongattavaId;
    }
    
    /**
     * Palauttaa bongattavan olion id-numeron
     * @return bongattavan olion id-numero
     */
    public int getBongattavaId() {
        return this.bongattavaId;
    }
    
    /**
     * Bongattavan olion nimi
     * @param nimi asetettava nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;     
    }
    
    /**
     * Bongattavan olion tieteellinen nimi
     * @param tieteellinen_nimi asetettava tieteellinen nimi
     */
    public void setTieteellinenNimi(String tieteellinen_nimi) {
        this.tieteellinen_nimi = tieteellinen_nimi;
        
    }
    
    /**
     * Bongattavan olion heimon nimi
     * @param heimo asetettava heimon nimi
     */
    public void setHeimo(String heimo) {
        this.heimo = heimo;
        
    }
    
    /**
     * Bongattavan olion lajin nimi
     * @param laji asetettava lajin nimi
     */
    public void setLaji(String laji) {
        this.laji = laji;      
    }
    
    /**
     * Tulostetaan bongattavan olion tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(this.bongattavaId + ": " + this.nimi + " " + this.tieteellinen_nimi + " " + this.heimo + " " + this.laji);
    }


    /**
     * Tulostetaan bongattavan olion tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Bongattava olio = new Bongattava(2003);
        olio.setNimi("Käki");
        olio.setTieteellinenNimi("KäkiLatinaksi");
        olio.setHeimo("Käet");
        olio.setLaji("Lintu");
        olio.tulosta(System.out);
    }

    
}
