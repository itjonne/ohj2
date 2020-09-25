package bongari;

import java.util.List;

/**
 * @author Jonne
 * @version 25 Sep 2020
 *
 */
public class BongausTietueet extends Bongaus {
    private List<Bongattava> bongattavat;
    
    public void setBongattavat(List<Bongattava> bongattavat) {
        this.bongattavat = bongattavat;
    }
    
    public List<Bongattava> getBongattavat() {
        return this.bongattavat;
    }
}
