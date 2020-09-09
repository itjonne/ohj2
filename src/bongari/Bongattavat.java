package bongari;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongattavat {
    private HashMap<Integer, Bongattava> alkiot = new HashMap<Integer, Bongattava>();
    private String tiedostonPerusNimi = "linnut";
    private int lkm = 0;
    private boolean muutettu = false;
    
    /**
     * Palauttaa alkioiden lukumäärän
     * @return alkioiden lukumäärä
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * bongattavat.getLkm() === 0;
     * Bongattava bongattava = new Bongattava();
     * bongattavat.lisaa(bongattava);
     * bongattavat.getLkm() === 1;
     * </pre>
     */
    public int getLkm() {
        return this.lkm;
    }
    
    /**
     * Palauttaa kaikki tietorakenteesta löytyvät alkiot
     * TODO: Tarvitaanko?
     * @return kaikki alkiot
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * Bongattava bongattava = new Bongattava();
     * bongattavat.lisaa(bongattava);
     * bongattavat.getAlkiot().size() === 1;
     * </pre>
     */
    public HashMap<Integer, Bongattava> getAlkiot() {
        return this.alkiot;
    }
    
    /**
     * Lisätään bongattava-olio tietorakenteeseen
     * @param bongattava lisättävä bongattava-olio
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * Bongattava bongattava = new Bongattava();
     * Bongattava bongattava2 = new Bongattava();
     * bongattavat.lisaa(bongattava);
     * bongattavat.getLkm() === 1;
     * bongattavat.lisaa(bongattava2);
     * bongattavat.getLkm() === 2;
     * </pre>
     */
    public void lisaa(Bongattava bongattava) {      
        alkiot.put(bongattava.getBongattavaId(), bongattava);
        lkm++;
    }
    
    /**
     * Etsii tietorakenteesta merkkijonon nimi-ehdon täyttävät otukset
     * @param hakuehto merkkijono, jolla haetaan
     * @return palauttaa nimi-hakuehdon täyttävät bongattavat otukset
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * Bongattava bongattava = new Bongattava();
     * bongattava.setBongattavaId(1);
     * bongattava.setNimi("Kissa");
     * Bongattava bongattava2 = new Bongattava();
     * bongattava2.setBongattavaId(2);
     * bongattava2.setNimi("Kassa");
     * bongattavat.lisaa(bongattava); bongattavat.lisaa(bongattava2);
     * bongattavat.etsi("ssa").size() === 2;
     * bongattavat.etsi("kissa").size() === 1;
     * bongattavat.etsi("IsSa").size() === 1;
     * bongattavat.etsi("koira").size() === 0;
     * </pre>
     */
    public List<Bongattava> etsi(String hakuehto) {
        List<Bongattava> loydetyt = new ArrayList<Bongattava>();
        alkiot.forEach((key,value) -> {
            if (value.getNimi().toLowerCase().contains(hakuehto.toLowerCase())) loydetyt.add(value);
        });
        return loydetyt;
    }
    
    /**
     * @param id haettavan olion id
     * @return palauttaa id:llä haettavan olion, jos ei löydy niin null
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * Bongattava bongattava = new Bongattava();
     * bongattava.setBongattavaId(1);
     * bongattavat.lisaa(bongattava);
     * Bongattava loytynyt = bongattavat.anna(1);
     * Bongattava loytynyt2 = bongattavat.anna(0);
     * loytynyt.getBongattavaId() === 1;
     * loytynyt2 === null;
     * </pre>
     */
    public Bongattava anna(int id) {
        return alkiot.get(id);
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
                Bongattava bongattava = new Bongattava();
                bongattava.parse(rivi); // voisi olla virhekäsittely
                lisaa(bongattava);
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
     * Tulostaa kaikki tietorakenteessa olevat avaimet ( == nimet)
     */
    public void tulostaBongattavat() {
        for (Integer key : alkiot.keySet()) {
            System.out.println(alkiot.get(key).getNimi());
        }
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     * @throws ExceptionHandler Jos jotain menee rikki, heittää kustomoidun exceptionhandlerin
     */
    public static void main(String args[]) throws ExceptionHandler  {
        Bongattavat bongattavat = new Bongattavat();
        Bongattava lintu = new Bongattava();
        lintu.setBongattavaId(1);
        lintu.setNimi("Lintu");
        Bongattava kissa = new Bongattava();
        kissa.setBongattavaId(2);
        kissa.setNimi("Kissa");
        
        try {
            bongattavat.lisaa(lintu);
            bongattavat.lisaa(kissa);
            // TODO: Kuinka iteroidaan ns. virallisesti? Saako tehdä aliohjelman
            // getAlkiot() ja rullata sitä? Miksi pitää tehdä iteraattoreita?!
            for (Integer key : bongattavat.getAlkiot().keySet()) {
                System.out.println(key);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
  
}
