package bongari;

import java.util.List;

/**
 * @author Jonne
 * @version 25 Sep 2020
 * 
 * Apuluokka bongausten lisäämistä varten.
 * Yhdistää bongauksen+bongattavat.
 *
 */
public class BongausTietueet extends Bongaus {
    private List<Bongattava> bongattavat;
    
    /**
     * Asettaa etsittävät bongattavat otukset .
     * @param bongattavat lista bongattavista otuksista.
     */
    public void setBongattavat(List<Bongattava> bongattavat) {
        this.bongattavat = bongattavat;
    }
    
    /**
     * Palauttaa etsittävät bongattavat oliot listana.
     * @return Lista bongattavista olioista
     */
    public List<Bongattava> getBongattavat() {
        return this.bongattavat;
    }
}
