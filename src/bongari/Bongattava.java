package bongari;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongattava {
    private int bongattavaId = 0;
    private String nimi = "";
    private String tieteellinen_nimi = "";
    private String heimo = "";
    private String laji = "";
      
    /**
     * Palauttaa bongattavan olion id-numeron
     * @return bongattavan olion id-numero
     */
    public int getBongattavaId() {
        return this.bongattavaId;
    }
    
    /**
     * Palauttaa olion nimen
     * @return bongattavan olion nimi
     */
    public String getNimi() {
        return this.nimi;
    }
    
    /**
     * Bongattavan olion id
     * @param id annettava id-numero
     */
    public void setId(int id) {
        this.bongattavaId = id;
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
     * Parsii tiedostosta rivi kerrallaan otuksia
     * @param rivi tuotava rivi luettavasta tiedostosta
     * @throws ExceptionHandler heitettävä kustomoitu exception
     */
    public void parse(String rivi) throws ExceptionHandler {
        try {
        StringBuffer sb = new StringBuffer(rivi);
        
        String olio_id = Mjonot.erota(sb,'|');
        this.setId(Integer.parseInt(olio_id));
        String olio_nimi = Mjonot.erota(sb,'|');
        this.setNimi(olio_nimi);
        String olio_tieteellinen_nimi = Mjonot.erota(sb, '|');
        this.setTieteellinenNimi(olio_tieteellinen_nimi);
        String olio_heimo = Mjonot.erota(sb, '|');
        this.setHeimo(olio_heimo);
        this.setLaji(sb.toString());
        } catch (Exception ex) {
            throw new ExceptionHandler("Tietojen hakemisessa meni joku rikki " + ex.getMessage());
        }       
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Bongattava olio = new Bongattava();
        olio.setId(1);
        olio.setNimi("Käki");
        olio.setTieteellinenNimi("KäkiLatinaksi");
        olio.setHeimo("Käet");
        olio.setLaji("Lintu");
        olio.tulosta(System.out);
    }

    
}
