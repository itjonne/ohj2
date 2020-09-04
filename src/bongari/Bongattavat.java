package bongari;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongattavat {
    private HashMap<String, Bongattava> alkiot = new HashMap<String, Bongattava>();
    private String tiedostonPerusNimi = "linnut";
    private int lkm = 0;
    private boolean muutettu = false;
    
    /**
     * Palauttaa alkioiden lukumäärän
     * @return alkioiden lukumäärä
     */
    public int getLkm() {
        return this.lkm;
    }
    
    /**
     * @return alkiot
     */
    public HashMap<String, Bongattava> getAlkiot() {
        return this.alkiot;
    }
    
    /**
     * Lisätään bongattava-olio tietorakenteeseen
     * @param bongattava lisättävä bongattava-olio
     */
    public void lisaa(Bongattava bongattava) {      
        alkiot.put(bongattava.getNimi(), bongattava);
        lkm++;
    }
    
    /**
     * @param nimi haettavan olion nimi
     * @return palauttaa nimellä haettavan olion
     */
    public Bongattava anna(String nimi) {
        return alkiot.get(nimi);
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
        for (String key : alkiot.keySet()) {
            System.out.println(key);
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
        lintu.setId(1);
        lintu.setNimi("Lintu");
        Bongattava kissa = new Bongattava();
        kissa.setId(2);
        kissa.setNimi("Kissa");
        
        try {
            bongattavat.lisaa(lintu);
            bongattavat.lisaa(kissa);
            // TODO: Kuinka iteroidaan ns. virallisesti? Saako tehdä aliohjelman
            // getAlkiot() ja rullata sitä? Miksi pitää tehdä iteraattoreita?!
            for (String key : bongattavat.getAlkiot().keySet()) {
                System.out.println(key);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
  
}
