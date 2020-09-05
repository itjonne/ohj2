package bongari;

import java.util.ArrayList;
import java.util.List;

import kanta.TiedostostaLuettava;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongaukset extends TiedostostaLuettava {
    private final List<Bongaus> alkiot = new ArrayList<Bongaus>();

    /**
     * Lisää bongauksen tietorakenteeseen
     * @param bongaus lisättävä bongaus
     */
    public void lisaa(Bongaus bongaus) {
        this.alkiot.add(bongaus);
    }
    
    /**
     * Hakee jäsenen bongaukset jäsenen id:llä
     * TODO: SAAKO VERRATA int == int?!
     * @param jasenId jäsenen id, jonka bongauksia haetaan
     * @return jäsenen löytämät bongaukset
     */
    public List<Bongaus> haeJasenenBongaukset(int jasenId) {
        List<Bongaus> jasenenBongaukset = new ArrayList<Bongaus>();
        for (Bongaus bongaus : alkiot) {
            if ( bongaus.getJasenId() == jasenId ) jasenenBongaukset.add(bongaus);
        }
        return jasenenBongaukset;
    }
    
    /**
     * Hakee bongaukset, joissa on löydetty tietty olio
     * @param bongattavaId bongattavan olion id jonka löytäjiä etsitään
     * @return bongaukset, joissa on löydetty haettavalla id:llä valittava olio
     */
    public List<Bongaus> haeBongattavanBongaukset(int bongattavaId) {
        List<Bongaus> bongauksenLoytaneetJasenet = new ArrayList<Bongaus>();
        for (Bongaus bongaus : alkiot) {
            if ( bongaus.getBongattavaId() == bongattavaId ) bongauksenLoytaneetJasenet.add(bongaus);
        }
        return bongauksenLoytaneetJasenet;
    }
    
    /**
     * Lisää tiedostosta luetut rivit tietorakenteeseen
     * @param rivit tiedostosta luetut rivit, joissa olioiden tiedot
     * @throws ExceptionHandler heittää kustomoidun errorin
     */
    public void lisaaTietorakenteeseen(List<String> rivit) throws ExceptionHandler {
        for (String rivi : rivit) {
            Bongaus bongaus = new Bongaus();
            bongaus.parse(rivi);
            lisaa(bongaus);
        }
    }
    
    /**
     * Poistaa bongauksen tietorakenteesta
     * @param bongaus poistettava bongaus
     */
    public void poista(Bongaus bongaus) {
        alkiot.remove(bongaus);
    }
     
    /**
     * Tulostaa bongaukset konsoliin
     */
    public void tulostaBongaukset() {
        for (Bongaus bongaus : alkiot) {
            bongaus.tulosta(System.out);
        }
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     * @throws ExceptionHandler Errorhandleri
     */
    public static void main(String[] args) throws ExceptionHandler {
        Bongaukset bongaukset = new Bongaukset();
        List<String> rivit = bongaukset.lueTiedostosta("data/bongaukset");
        for (String rivi : rivit) {
            System.out.println(rivi);
        }        
    }

}
