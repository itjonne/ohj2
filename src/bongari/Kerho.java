package bongari;

import java.io.File;
import java.util.List;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Kerho {
    private Bongattavat bongattavat = new Bongattavat();
    private Jasenet jasenet = new Jasenet();
    private Bongaukset bongaukset = new Bongaukset();
    
    public void lisaa(Jasen jasen) {
        jasenet.lisaa(jasen);
    }
    
    public void lisaa(Bongaus bongaus) {
        bongaukset.lisaa(bongaus);
    }
    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedostot(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        bongattavat.setTiedostonPerusNimi(hakemistonNimi + "linnut");
        jasenet.setTiedostonPerusNimi(hakemistonNimi + "jasenet");
        bongaukset.setTiedostonPerusNimi(hakemistonNimi + "bongaukset");
    }
    
    /**
     * @param nimi luettavan tiedoston nimi
     * @throws ExceptionHandler heitettävä exception
     */
    public void lueKansiosta(String nimi) throws ExceptionHandler {
        setTiedostot(nimi);
        bongattavat.lueTiedostosta();
        jasenet.lueTiedostosta();
        List<String> rivit = bongaukset.lueTiedostosta();
        bongaukset.lisaaTietorakenteeseen(rivit);
    }
    
    /**
     * Hakee jäsenen bongaamat otukset
     * @param jasenId jäsenen id, jonka tietoja haetaan
     * @return lista jäsenen bongatuista bongattava-olioista
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Kerho kerho = new Kerho();
     * Jasen jasen = new Jasen();
     * jasen.rekisteroi();
     * Bongattava bongattava = new Bongattava();
     * bongattava.rekisteroi();
     * Bongaus bongaus = new Bongaus();
     * bongaus.setJasenId(jasen.getJasenId());
     * bongaus.setBongattavaId(bongattava.getBongattavaId()); 
     * kerho.lisaa(bongaus);
     * kerho.lisaa(jasen);
     * List<Bongattava> loytyneet;
     * loytyneet = kerho.haeJasenenBongaukset(jasen.getJasenId());
     * loytyneet.size() === 1;
     * </pre>
     */
    public List<Bongattava> haeJasenenBongaukset(int jasenId) {
        return null;
        //
    }
    
    /**
     * @param bongattavaId bongattavan id, jonka löytäneitä jäseniä haetaan
     * @return lista jäsenistä, jotka ovat löytäneet bongattavan olion
     */
    public List<Jasen> haeBongattavanLoytaneetJasenet(int bongattavaId) {
        return null;
        //
    }
    
    /**
     * Tulostaa bongattavat otukset
     */
    public void tulostaBongattavat() {
        bongattavat.tulostaBongattavat();
    }
    
    /**
     * Tulostaa jäsenet
     */
    public void tulostaJasenet() {
        jasenet.tulostaJasenet();
    }
    
    /**
     * Tulostaa bongaukset
     */
    public void tulostaBongaukset() {
        bongaukset.tulostaBongaukset();
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     * @throws ExceptionHandler heitettävä exception
     */
    public static void main(String args[]) throws ExceptionHandler {
        Kerho kerho = new Kerho();
        try {
            kerho.lueKansiosta("data");
            kerho.tulostaBongattavat();
            System.out.println("===== JÄSENET =====");
            kerho.tulostaJasenet();
            System.out.println("===== BONGAUKSET ====");
            kerho.tulostaBongaukset();
        } catch (Exception ex) {
            throw new ExceptionHandler(ex.getMessage());
        }
    }
}
