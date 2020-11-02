package bongari;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bongarikerhon jäsenistö
 * TODO: Yli viiden jäsenen lisääminen ei vielä toimi
 * 
 * @author Jonne
 * @version 2 Sep 2020
 */
public class Jasenet {
    private final int MAX_JASENET = 5;
    private Jasen alkiot[] = new Jasen[MAX_JASENET];
    private int lkm = 0;
    private String tiedostonPerusNimi = "jasenet";
    private boolean muutettu = false;
    
    
    /**
     * Lisää jäsenen jäsenten listaan, jos ei mahdu, heittää errorin.
     * @param jasen lisättävä jäsen
     * @example
     * <pre name="test">
     * Jasenet jasenet = new Jasenet();
     * Jasen testi = new Jasen();
     * jasenet.lisaa(testi);
     * jasenet.getLkm() === 1;
     * Jasen testi2 = new Jasen();
     * Jasen testi3 = new Jasen();
     * Jasen testi4 = new Jasen();
     * Jasen testi5 = new Jasen();
     * Jasen testi6 = new Jasen();
     * jasenet.lisaa(testi2); jasenet.lisaa(testi3); jasenet.lisaa(testi4); jasenet.lisaa(testi5);
     * jasenet.lisaa(testi6); #THROWS Error
     * </pre>
     */
    public void lisaa(Jasen jasen) {
        if (lkm >= alkiot.length) {
            alkiot = Arrays.copyOf(alkiot, lkm + 10);
            alkiot[lkm] = jasen;
            lkm++;
            muutettu = true;
        }
        alkiot[lkm] = jasen;
        lkm++;
        muutettu = true;
    }
    
    /**
     * Poistaa jäsenen id-numeron perusteella.
     * @param jasenId poistettavan jäsenen id
     * @return palauttaa true jos onnistui, muuten false
     * @example
     * <pre name="test">
     * #THROWS ExceptionHandler
     * Jasenet jasenet = new Jasenet();
     * Jasen jasen = new Jasen();
     * jasen.parse("1|Roope|Ankka");
     * jasenet.lisaa(jasen);
     * jasenet.getLkm() === 1;
     * Jasen jasen2 = new Jasen();
     * jasen2.parse("2|Aku|Ankka");
     * jasenet.lisaa(jasen2);
     * jasenet.getLkm() === 2;
     * jasenet.poista(1);
     * jasenet.getLkm() === 1;
     * </pre>
     */
    public boolean poista(int jasenId) {
        int loydetty = etsiJasenenIndeksi(jasenId);
        if (loydetty < 0) return false;
        lkm--;
        for (int i = loydetty; i < lkm; i++) {
            alkiot[i] = alkiot[i + 1];
        }
        alkiot[lkm] = null;
        muutettu = true;
        return true;
    }
    
    /**
     * Muokkaa tietorakenteessa olevan jäsenen tietoja.
     * TODO: Jos yrittää muokata olemattoman tyypin tietoja
     * @param jasen Jäsen, jonka tietoja muokataan
     * @example
     * <pre name="test">
     * #THROWS ExceptionHandler
     * Jasenet jasenet = new Jasenet();
     * Jasen jasen = new Jasen();
     * jasen.parse("1|Repe|Sorsa");
     * jasenet.lisaa(jasen);
     * jasenet.annaId(1).getSukunimi() === "Sorsa";
     * Jasen muokattuJasen = new Jasen();
     * muokattuJasen.parse("1|Repe|Ankka");
     * jasenet.muokkaa(muokattuJasen);
     * jasenet.annaId(1).getSukunimi() === "Ankka";
     * </pre>
     */
    public void muokkaa(Jasen jasen) {
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i].getJasenId() == jasen.getJasenId()) {
                alkiot[i] = jasen;
                muutettu = true;
                return;
            }
        }
    }
    
    /**
     * Hakee tietorakenteesta hakuehdon täyttävät jäsenet
     * @param hakuehto merkkijono, jolla haetaan
     * @return loydetyt jäsenet, jonka etunimi tai sukunimi täsmää hakuehtoon
     * @example
     * <pre name="test">
     * #THROWS ExceptionHandler
     * #import java.util.*;
     * Jasenet jasenet = new Jasenet();
     * Jasen jasen = new Jasen();
     * jasen.parse("1|Aku|Ankka");
     * Jasen jasen2 = new Jasen();
     * jasen2.parse("2|Roope|Ankka");
     * jasenet.lisaa(jasen); jasenet.lisaa(jasen2);
     * List<Jasen> loydetyt = jasenet.etsi("Ankka");
     * loydetyt.size() === 2;
     * List<Jasen> loydetyt2 = jasenet.etsi("Ank");
     * loydetyt2.size() === 2;
     * List<Jasen> loydetyt3 = jasenet.etsi("Pekka");
     * loydetyt3.size() === 0;
     * </pre>
     */
    public List<Jasen> etsi(String hakuehto) {
        List<Jasen> loydetyt = new ArrayList<Jasen>();
        // Tyhjällä haulla voi hakea kaikki jäsenet
        if (hakuehto == "") {
            for (Jasen jasen : alkiot) {
                if (jasen != null) loydetyt.add(jasen);               
            }
            return loydetyt;
        }
        for (Jasen jasen : alkiot) {
            if (jasen != null && (jasen.getEtunimi().toLowerCase().contains(hakuehto.toLowerCase()) || jasen.getSukunimi().toLowerCase().contains(hakuehto.toLowerCase()))) {
                loydetyt.add(jasen);
            }
        }
        return loydetyt;
    }
    
    /**
     * Etsii jäsenen indeksin tietorakenteesta
     * @param jasenId jäsenen id jonka indeksiä etsitään
     * @return löydetyn jäsenen indeksi tietorakenteessa, -1 jos ei löytynyt
     * @example
     * <pre name="test">
     * #THROWS ExceptionHandler
     * Jasenet jasenet = new Jasenet();
     * Jasen jasen = new Jasen();
     * jasen.parse("1|Repe|Sorsa");
     * Jasen jasen2 = new Jasen();
     * jasen2.parse("2|Aku|Ankka");
     * jasenet.lisaa(jasen); jasenet.lisaa(jasen2);
     * int indeksi = jasenet.etsiJasenenIndeksi(1);
     * indeksi === 0;
     * int indeksi2 = jasenet.etsiJasenenIndeksi(2);
     * indeksi2 === 1;
     * int indeksiEiLoydy = jasenet.etsiJasenenIndeksi(3);
     * indeksiEiLoydy === -1;
     * </pre>
     */
    public int etsiJasenenIndeksi(int jasenId) {
        for (int i = 0; i <= lkm; i++) {
            if ( alkiot[i] != null && alkiot[i].getJasenId() == jasenId ) return i;
        }
        return -1;
    }
    
    /**
     * Palauttaa jäsenten lukumäärän
     * @return jäsenten lukumäärä
     * @example
     * <pre name="test">
     * Jasenet jasenet = new Jasenet();
     * Jasen jasen = new Jasen();
     * jasenet.lisaa(jasen);
     * jasenet.getLkm() === 1;
     * </pre>
     */
    public int getLkm() {
        return this.lkm;
    }
    
    /**
     * @param i taulukon alkion indeksi
     * @return paikassa i oleva jäsen
     * @throws IndexOutOfBoundsException jos i ei ole sallittu
     * @example
     * <pre name="test">
     * Jasenet jasenet = new Jasenet();
     * Jasen jasen1 = new Jasen();
     * Jasen jasen2 = new Jasen();
     * jasenet.lisaa(jasen1); jasenet.lisaa(jasen2);
     * jasenet.anna(0) === jasen1;
     * jasenet.anna(1) === jasen2;
     * jasenet.anna(3); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public Jasen anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= this.lkm) throw new IndexOutOfBoundsException("Liian suuri indeksi");
        return alkiot[i];
    }
    
    /**
     * Hakee tietorakenteesta haettavalla id:llä varustetun jäsenen.
     * @param jasenId haettavan jäsenen id
     * @return palauttaa löydetin jäsenen, jos ei löydy niin null.
     * @example
     * <pre name="test">
     * #THROWS ExceptionHandler
     * Jasenet jasenet = new Jasenet();
     * Jasen jasen = new Jasen();
     * jasen.parse("1|Roope|Ankka");
     * Jasen jasen2 = new Jasen();
     * jasen2.parse("2|Repe|Sorsa");
     * jasenet.lisaa(jasen); jasenet.lisaa(jasen2);
     * Jasen loydetty = jasenet.annaId(1);
     * loydetty.getEtunimi() === "Roope";
     * Jasen loydetty2 = jasenet.annaId(4);
     * loydetty2 === null;
     * </pre>
     */
    public Jasen annaId(int jasenId) {
        for (Jasen jasen : alkiot) {
            if (jasen != null && jasen.getJasenId() == jasenId) return jasen;
        }
        return null;
    }
    
    /**
     * @param tied tiedoston nimi
     * @throws ExceptionHandler jos joku menee pieleen, heitetään oma exceptioni
     */
    public void lueTiedostosta(String tied) throws ExceptionHandler {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Jasen jasen = new Jasen();
                jasen.parse(rivi); // voisi olla virhekäsittely
                lisaa(jasen);
            }
            muutettu = false;

        } catch ( FileNotFoundException e ) {
            throw new ExceptionHandler("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new ExceptionHandler("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws ExceptionHandler jos tulee poikkeus
     */
    public void lueTiedostosta() throws ExceptionHandler {
        lueTiedostosta(getTiedostonPerusNimi());
    }

    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param tied tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String tied) {
        tiedostonPerusNimi = tied;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonPerusNimi + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }
    
    /**
     * Tallentaa tiedostoon haluttuun paikkaan
     * @throws ExceptionHandler Jos joku menee rikki
     */
    public void tallenna() throws ExceptionHandler {
        if (!muutettu) return;
        
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            fo.println(";Luontobongaajien kerho ry");
            fo.println(";Kenttien jÃ¤rjestys tiedostossa on seuraava:");
            fo.println(";id|etunimi|sukunimi");
            for (Jasen jasen : alkiot) {
                if (jasen != null) {                    
                    fo.println(jasen.toString());
                }
            }
            //} catch ( IOException e ) { // ei heitä poikkeusta
            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage());
        } catch ( FileNotFoundException ex ) {
            throw new ExceptionHandler("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new ExceptionHandler("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    /**
     * Tulostaa jäsenistön consoleen
     */
    public void tulostaJasenet() {
        for (Jasen jasen : this.alkiot) {
            if ( jasen != null ) {                
                System.out.println(jasen.getEtunimi() + " " + jasen.getSukunimi());
            }
        }
    }
    
    /**
     * Antaa kaikki tietorakenteesta löytyvät jäsenet
     * @return kaikki tietorakenteesta löytyvät jäsenet
     * @example
     * <pre name="test">
     * #THROWS ExceptionHandler
     * Jasenet jasenet = new Jasenet();
     * Jasen jasen = new Jasen();
     * jasen.parse("1|Aku|Ankka");
     * Jasen jasen2 = new Jasen();
     * jasen2.parse("2|Roope|Ankka");
     * jasenet.lisaa(jasen); jasenet.lisaa(jasen2);
     * jasenet.annaJasenet().size() === 2;
     * </pre>
     */
    public List<Jasen> annaJasenet() {
        List<Jasen> jasenet = new ArrayList<Jasen>();
        for (Jasen jasen : this.alkiot) {
            if (jasen != null) {
                jasenet.add(jasen);
            }
        }
        return jasenet;
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     * @throws ExceptionHandler Virheiden hallinta
     */
    public static void main(String args[]) throws ExceptionHandler  {
        Jasenet jasenet = new Jasenet();
        Jasen aku = new Jasen(), aku2 = new Jasen();
        aku.setEtunimi("Aku");
        aku.setSukunimi("Ankka");
        aku2.setEtunimi("Roope");
        aku2.setSukunimi("Ankka");
        jasenet.lisaa(aku);
        jasenet.lisaa(aku2);
        
        try {            
            System.out.println(jasenet.alkiot[0].getKokonimi());
            System.out.println(jasenet.alkiot[1].getKokonimi());
            System.out.println(jasenet.alkiot[2].getKokonimi());
        } catch (Exception e) {
            throw new ExceptionHandler(e.getMessage());
        }
    }
}
