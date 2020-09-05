package kanta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bongari.ExceptionHandler;

/**
 * @author Jonne
 * @version 4 Sep 2020
 *
 */
public class TiedostostaLuettava {
    private String tiedostonPerusNimi = "";

    /**
     * Perus constructori, en ole ihan 100% varma täytyykö tämä olla?
     */
    public TiedostostaLuettava() {
        // Pelkkää tyhjää
    }
    
    
    
    /**
     * @param tied tiedoston nimi
     * @return lista, jossa tiedoston tiedot
     * @throws ExceptionHandler jos joku menee pieleen, heitetään oma exceptioni
     */
    public List<String> lueTiedostosta(String tied) throws ExceptionHandler {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            List<String> lista = new ArrayList<String>();
            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                lista.add(rivi);
            }
            return lista;
        } catch ( FileNotFoundException e ) {
            throw new ExceptionHandler("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new ExceptionHandler("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @return Listan riveistä
     * @throws ExceptionHandler jos tulee poikkeus
     */
    public List<String> lueTiedostosta() throws ExceptionHandler {
        return lueTiedostosta(getTiedostonPerusNimi());        
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
}
