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
    private static int seuraavaNro = 1;
    private String nimi = "";
    private String tieteellinen_nimi = "";
    private String heimo = "";
    private String laji = "";
      
    /**
     * Palauttaa bongattavan olion id-numeron
     * @return bongattavan olion id-numero
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setBongattavaId(1);
     * bongattava.getBongattavaId() === 1;
     * </pre>
     */
    public int getBongattavaId() {
        return this.bongattavaId;
    }
    
    /**
     * Palauttaa olion nimen
     * @return bongattavan olion nimi
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setNimi("Kalle");
     * bongattava.getNimi() === "Kalle";
     * </pre>
     */
    public String getNimi() {
        return this.nimi;
    }
    
    /**
     * Bongattavan olion id
     * Samalla kasvattaa attribuuttia seuraavaNro rekisteröintiä varten
     * @param id annettava id-numero
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setBongattavaId(1);
     * bongattava.getBongattavaId() === 1;
     * </pre>
     */
    public void setBongattavaId(int id) {
        this.bongattavaId = id;
        if (this.bongattavaId >= seuraavaNro) seuraavaNro = this.bongattavaId + 1;
    }
    
    /**
     * Rekisteröi uuden bongattavan-otuksen tietorakenteeseen.
     * @return palauttaa luodun bongattava-olion id-numeron
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.rekisteroi();
     * bongattava.getBongattavaId() === 1;
     * Bongattava bongattava2 = new Bongattava();
     * bongattava2.rekisteroi();
     * bongattava2.getBongattavaId() === 2;
     * </pre>
     */
    public int rekisteroi() {
        this.bongattavaId = seuraavaNro;
        seuraavaNro++;
        return this.bongattavaId;
    }
    
    /**
     * Bongattavan olion nimi
     * @param nimi asetettava nimi
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setNimi("Kissa");
     * bongattava.getNimi() === "Kissa";
     * </pre>
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;     
    }
    
    /**
     * Bongattavan olion tieteellinen nimi
     * @param tieteellinen_nimi asetettava tieteellinen nimi
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setTieteellinenNimi("Latinaksinimi");
     * bongattava.getTieteellinenNimi() === "Latinaksinimi";
     * </pre>
     */
    public void setTieteellinenNimi(String tieteellinen_nimi) {
        this.tieteellinen_nimi = tieteellinen_nimi;
        
    }
    
    /**
     * Palauttaa bongattavan olion tieteellisen nimen
     * @return bongattavan olion tieteellinen nimi
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setTieteellinenNimi("Latinaksinimi");
     * bongattava.getTieteellinenNimi() === "Latinaksinimi";
     * </pre>
     */
    public String getTieteellinenNimi() {
        return this.tieteellinen_nimi;
    }
    
    /**
     * Bongattavan olion heimon nimi
     * @param heimo asetettava heimon nimi
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setHeimo("Kissakalat");
     * bongattava.getHeimo() === "Kissakalat";
     * </pre>
     */
    public void setHeimo(String heimo) {
        this.heimo = heimo;       
    }
    
    /**
     * Palauttaa bongattavan olion heimon nimen
     * @return palauttaa bongattavan olion heimon nimen
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setHeimo("Kissakalat");
     * bongattava.getHeimo() === "Kissakalat";
     * </pre>
     */
    public String getHeimo() {
        return this.heimo;
    }
    
    /**
     * Bongattavan olion lajin nimi
     * @param laji asetettava lajin nimi
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setLaji("Kala");
     * bongattava.getLaji() === "Kala";
     * </pre>
     */
    public void setLaji(String laji) {
        this.laji = laji;      
    }
    
    /**
     * Palauttaa bongattavan olion lajin nimen
     * @return bongattavan olion laji
     * @example
     * <pre name="test">
     * Bongattava bongattava = new Bongattava();
     * bongattava.setLaji("Kala");
     * bongattava.getLaji() === "Kala";
     * </pre>
     */
    public String getLaji() {
        return this.laji;
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
     * @example
     * <pre name="test">
     * #THROWS ExceptionHandler
     * Bongattava bongattava = new Bongattava();
     * bongattava.parse("2|Harmaahaikara|Ardea cinerea|Haikarat, Ardeidae|Lintu");
     * bongattava.getBongattavaId() === 2;
     * bongattava.getNimi() === "Harmaahaikara";
     * bongattava.getTieteellinenNimi() === "Ardea cinerea";
     * bongattava.getHeimo() === "Haikarat, Ardeidae";
     * bongattava.getLaji() === "Lintu";
     * </pre>
     */
    public void parse(String rivi) throws ExceptionHandler {
        try {
        StringBuffer sb = new StringBuffer(rivi);
        
        String olio_id = Mjonot.erota(sb,'|');
        this.setBongattavaId(Integer.parseInt(olio_id));
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
        olio.setBongattavaId(1);
        olio.setNimi("Käki");
        olio.setTieteellinenNimi("KäkiLatinaksi");
        olio.setHeimo("Käet");
        olio.setLaji("Lintu");
        olio.tulosta(System.out);
    } 
}
