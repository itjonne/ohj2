package bongari;

import java.io.File;
import java.util.ArrayList;
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
     * loytyneet = kerho.haeJasenenBongattavat(jasen.getJasenId());
     * loytyneet.size() === 1;
     * Jasen jasen2 = new Jasen();
     * jasen2.rekisteroi();
     * Bongaus bongaus2 = new Bongaus();
     * bongaus2.setJasenId(jasen.getJasenId());
     * bongaus2.setBongattavaId(bongattava.getBongattavaId());
     * kerho.lisaa(bongaus2);
     * List<Bongattava> loytyneet2;
     * loytyneet2 = kerho.haeJasenenBongattavat(jasen2.getJasenId());
     * loytyneet2.size() === 0;
     * loytyneet.size() === 1;
     * </pre>
     */
    public List<Bongattava> haeJasenenBongattavat(int jasenId) {
        List<Bongaus> loydetytBongaukset = this.bongaukset.haeJasenenBongaukset(jasenId);
        List<Bongattava> loydetytBongattavat = new ArrayList<Bongattava>();
        for (Bongaus bongaus : loydetytBongaukset) {
            Bongattava b = this.bongattavat.anna(bongaus.getBongattavaId());
            loydetytBongattavat.add(b);
        }
        return loydetytBongattavat;
    }
    
    /**
     * Hakee jäsenen bongaus-tapahtumat
     * @param jasenId jäsenen id-numero, jonka bongauksia etsitään
     * @return lista jäsenen bongaus-tapahtumista
     * * @example
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
     * List<Bongaus> loytyneet;
     * loytyneet = kerho.haeJasenenBongaukset(jasen.getJasenId());
     * loytyneet.size() === 1;
     * Jasen jasen2 = new Jasen();
     * jasen2.rekisteroi();
     * Bongaus bongaus2 = new Bongaus();
     * bongaus2.setJasenId(jasen.getJasenId());
     * bongaus2.setBongattavaId(bongattava.getBongattavaId());
     * kerho.lisaa(bongaus2);
     * List<Bongaus> loytyneet2;
     * loytyneet2 = kerho.haeJasenenBongaukset(jasen2.getJasenId());
     * loytyneet2.size() === 0;
     * loytyneet.size() === 1;
     * </pre>
     */
    public List<Bongaus> haeJasenenBongaukset(int jasenId) {
        List<Bongaus> loydetytBongaukset = this.bongaukset.haeJasenenBongaukset(jasenId);
        return loydetytBongaukset;
    }
    
    /**
     * Hakee bongattavan otuksen löytäneet jäsenet.
     * @param bongattavaId bongattavan id, jonka löytäneitä jäseniä haetaan
     * @return lista jäsenistä, jotka ovat löytäneet bongattavan olion
     */
    public List<Jasen> haeBongattavanLoytaneetJasenet(int bongattavaId) {
        List<Bongaus> loydetytBongaukset = this.bongaukset.haeBongattavanBongaukset(bongattavaId);
        List<Jasen> loydetytJasenet = new ArrayList<Jasen>();
        for (Bongaus bongaus : loydetytBongaukset) {
            Jasen j = this.jasenet.annaId(bongaus.getJasenId());
            loydetytJasenet.add(j);
        }
        return loydetytJasenet;
    }
    
    /**
     * Hakee bongattavan otuksen bongaus-tapahtumat
     * @param bongattavaId bongattavan otuksen id-numero
     * @return palauttaa bongattavan otuksen bongaus-tapahtumat
     */
    public List<Bongaus> haeBongattavanBongaukset(int bongattavaId) {
        List<Bongaus> loydetytBongaukset = this.bongaukset.haeBongattavanBongaukset(bongattavaId);
        return loydetytBongaukset;
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
