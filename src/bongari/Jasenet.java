package bongari;

/**
 * @author Jonne
 * @version 2 Sep 2020
 *
 */
public class Jasenet {
    private final int MAX_JASENET = 5;
    private Jasen alkiot[] = new Jasen[MAX_JASENET];
    private int lkm = 0;
    
    
    /**
     * Lisää jäsenen jäsenten listaan, jos ei mahdu, heittää errorin.
     * @param jasen lisättävä jäsen
     */
    public void lisaa(Jasen jasen) {
        if (lkm >= MAX_JASENET) {
            throw new Error("Meni rikki");
        }
        alkiot[lkm] = jasen;
        lkm++;
    }
    
    /**
     * @param i taulukon alkion indeksi
     * @return paikassa i oleva jäsen
     */
    public Jasen anna(int i) {
        // Puuttuu virhetarkistus
        return alkiot[i];
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Jasenet jasenet = new Jasenet();
        Jasen aku = new Jasen(), aku2 = new Jasen();
        aku.setEtunimi("Aku");
        aku.setSukunimi("Ankka");
        aku2.setEtunimi("Roope");
        aku2.setSukunimi("Ankka");
        jasenet.lisaa(aku);
        jasenet.lisaa(aku2);
        
        System.out.println(jasenet.alkiot[0].getKokonimi());
        System.out.println(jasenet.alkiot[1].getKokonimi());
    }
}
