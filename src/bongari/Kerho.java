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
    // Bongattavat tehty omalla luokkarakenteella.
    private Bongattavat bongattavat = new Bongattavat();
    private Jasenet jasenet = new Jasenet();
    private Bongaukset bongaukset = new Bongaukset();
    
    public void lisaa(Jasen jasen) {
        jasenet.lisaa(jasen);
    }
    
    public void lisaa(Bongaus bongaus) {
        bongaukset.lisaa(bongaus);
    }
    
    public void muokkaa(Jasen jasen) {
        jasenet.muokkaa(jasen);
    }
    
    public void muokkaa(Bongaus bongaus) {
        bongaukset.muokkaa(bongaus);
    }
    
    /**
     * @param hakuehto hakuehto, jolla haetaan
     * @return hakuehtoa vastaavan jäsenistön
     */
    public List<Jasen> etsiJasenet(String hakuehto) {
        return jasenet.etsi(hakuehto);
    }
    
    /**
     * @param hakuehto hakuehto, jolla haetaan
     * @return hakuehtoa vastaavat bongattavat
     */
    public List<Bongattava> etsiBongattavat(String hakuehto) {
        return bongattavat.etsi(hakuehto);
    }
    
    /**
     * @param id bongattavan otuksen id, jota etsitään
     * @return bongattava otus
     */
    public Bongattava etsiBongattavatId(int id) {
        return bongattavat.etsiId(id);
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
     * Tallentaa bongarikerhon tiedot tiedostoon.  
     * @throws ExceptionHandler jos tallettamisessa ongelmia
     */
    public void tallenna() throws ExceptionHandler {
        String virhe = "";
        try {
            jasenet.tallenna();
        } catch ( ExceptionHandler ex ) {
            virhe = ex.getMessage();
        }

        try {
            bongaukset.tallenna();
        } catch ( ExceptionHandler ex ) {
            virhe += ex.getMessage();
        }
        if ( !"".equals(virhe) ) throw new ExceptionHandler(virhe);
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
    
    public void poistaJasenenBongaukset(int jasenId) {
        List<Bongaus> jasenenBongaukset = haeJasenenBongaukset(jasenId);
        for (Bongaus bongaus : jasenenBongaukset) {
            poista(bongaus);
        }
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
     * Poistaa jäsenen tietorakenteesta.
     * @param jasen poistettava jäsen
     * @return true jos poistaminen onnistui, false jos ei
     */
    public boolean poista(Jasen jasen) {
        boolean onnistuko = jasenet.poista(jasen.getJasenId());
        return onnistuko;
    }
    
    /**
     * Poistaa valitun bongauksen tietorakenteesta
     * @param bongaus poistettava bongaus
     * @return true jos poistaminen onnistui, false jos ei
     */
    public boolean poista(Bongaus bongaus) {
        boolean onnistuko = bongaukset.poista(bongaus.getBongausId());
        return onnistuko;   
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
            List<Jasen> jasenet = kerho.jasenet.annaJasenet();
            Jasen jasen = jasenet.get(0);
            kerho.poista(jasen);
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
