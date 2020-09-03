package bongari;

public class Bongattavat {
    private final int MAX_BONGATTAVAT = 5;
    private Bongattava[] alkiot = new Bongattava[MAX_BONGATTAVAT];
    private String tiedostonPerusNimi = "";
    private int lkm = 0;
    
    /**
     * Palauttaa alkioiden lukumäärän
     * @return alkioiden lukumäärä
     */
    public int getLkm() {
        return this.lkm;
    }
    
    /**
     * Lisätään bongattava-olio tietorakenteeseen
     * @param bongattava lisättävä bongattava-olio
     */
    public void lisaa(Bongattava bongattava) {
        alkiot[lkm] = bongattava;
        lkm++;
    }
    
    /**
     * @param i haettavan olion indeksi
     * @return paikassa i olevan Bongattava-olion
     */
    public Bongattava anna(int i) {
        return alkiot[i];
    }
    
    /**
     * Haetaan tietorakenteesta annettavalla id-numerolla oleva bongattava-olio
     * @param id haettavan bongattava-olion id-numero
     * @return Jos tuollainen id löytyy, palauttaa löydetyn Bongattavan, muuten null
     */
    public Bongattava annaId(int id) {
        for(Bongattava bongattava : alkiot) {
            if (bongattava.getBongattavaId() == id) return bongattava;        
        }
        return null;
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     * @throws ExceptionHandler Jos jotain menee rikki, heittää kustomoidun exceptionhandlerin
     */
    public static void main(String args[]) throws ExceptionHandler  {
        Bongattavat bongattavat = new Bongattavat();
        Bongattava lintu = new Bongattava(1);
        lintu.setNimi("Lintu");
        Bongattava kissa = new Bongattava(2);
        kissa.setNimi("Kissa");
        
        try {
            bongattavat.lisaa(lintu);
            bongattavat.lisaa(kissa);
            // TODO: Kuinka iteroidaan ns. virallisesti? Saako tehdä aliohjelman
            // getAlkiot() ja rullata sitä? Miksi pitää tehdä iteraattoreita?!
            for(int i = 0; i < bongattavat.getLkm(); i++) {
                bongattavat.alkiot[i].tulosta(System.out);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
  
}
