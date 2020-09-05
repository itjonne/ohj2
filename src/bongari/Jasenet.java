package bongari;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Jonne
 * @version 2 Sep 2020
 *
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
        if (lkm >= MAX_JASENET) {
            throw new Error("Meni rikki");
        }
        alkiot[lkm] = jasen;
        lkm++;
        muutettu = true;
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
     */
    public Jasen annaId(int jasenId) {
        for (Jasen jasen : alkiot) {
            if (jasen.getJasenId() == jasenId) return jasen;
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
