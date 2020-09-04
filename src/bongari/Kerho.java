package bongari;

import java.io.File;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Kerho {
    private Bongattavat bongattavat = new Bongattavat();
    private Jasenet jasenet = new Jasenet();
    private Bongaukset bongaukset = new Bongaukset();
    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        bongattavat.setTiedostonPerusNimi(hakemistonNimi + "linnut");
    }
    
    /**
     * @param nimi luettavan tiedoston nimi
     * @throws ExceptionHandler heitettävä exception
     */
    public void lueTiedostosta(String nimi) throws ExceptionHandler {
        setTiedosto(nimi);
        bongattavat.lueTiedostosta();
    }
    
    /**
     * Tulostaa bongattavat.otukset
     */
    public void tulostaBongattavat() {
        bongattavat.tulostaBongattavat();
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     * @throws ExceptionHandler heitettävä exception
     */
    public static void main(String args[]) throws ExceptionHandler {
        Kerho kerho = new Kerho();
        try {
            kerho.lueTiedostosta("data");
            kerho.tulostaBongattavat();
        } catch (Exception ex) {
            throw new ExceptionHandler(ex.getMessage());
        }
    }
}
