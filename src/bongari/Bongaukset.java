package bongari;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongaukset {
    private final List<Bongaus> alkiot = new ArrayList<Bongaus>();
    private String tiedostonPerusNimi = "";
    
    /**
     * Lisää bongauksen tietorakenteeseen
     * @param bongaus lisättävä bongaus
     */
    public void lisaa(Bongaus bongaus) {
        this.alkiot.add(bongaus);
    }
    
    /**
     * Poistaa bongauksen tietorakenteesta
     * @param bongaus poistettava bongaus
     */
    public void poista(Bongaus bongaus) {
        alkiot.remove(bongaus);
    }
}
