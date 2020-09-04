package bongari;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Jonne
 * @version 2 Sep 2020
 *
 */
public class Jasen {
    private int jasenId;
    private String etunimi = "";
    private String sukunimi = "";
    private static int seuraavaNro = 1;
    
    /**
     * @return jäsenen etunimi
     * @example
     * <pre name="test">
     *  Jasen testi = new Jasen();
     *  Jasen tyhja = new Jasen();
     *  testi.setEtunimi("Testaaja");
     *  testi.getEtunimi() === "Testaaja";
     *  tyhja.getEtunimi() === "";
     *  </pre>
     */
    public String getEtunimi() {
        return etunimi;
    }
    
    /**
     * @param etunimi annettava etunimi
     * @example
     * <pre name="test">
     * Jasen testi = new Jasen();
     * testi.setEtunimi("Veijo");
     * testi.getEtunimi() === "Veijo";
     * </pre>
     */
    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }
    
    /**
     * @param sukunimi annettava sukunimi
     * @example
     * <pre name="test">
     * Jasen testi = new Jasen();
     * testi.setSukunimi("Testaaja");
     * testi.getSukunimi() === "Testaaja";
     * </pre>
     */
    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }
    
    /**
     * @return jäsenen sukunimi
     * @example
     * <pre name="test">
     * Jasen testi = new Jasen();
     * testi.setSukunimi("Testaaja");
     * testi.getSukunimi() === "Testaaja";
     * </pre>
     */
    public String getSukunimi() {
        return sukunimi;
    }
    
    /**
     * @return jäsenen koko nimi eroteltuna välimerkillä
     * @example
     * <pre name="test">
     * Jasen testi = new Jasen();
     * testi.setEtunimi("Testi");
     * testi.setSukunimi("Testaaja");
     * testi.getKokonimi() === "Testi Testaaja";
     * </pre>
     */
    public String getKokonimi() {
        return etunimi + " " + sukunimi;
    }
    
    /**
     * Rekisteröi uuden jäsenen
     * @return jäsenen jäsenId
     * @example
     * <pre name="test">
     * Jasen testi = new Jasen();
     * testi.rekisteroi();
     * Jasen testi2 = new Jasen();
     * testi2.rekisteroi();
     * testi.getJasenId() === 1;
     * testi2.getJasenId() === 2;
     * </pre>
     */
    public int rekisteroi() {
        this.jasenId = seuraavaNro;
        seuraavaNro++;
        return this.jasenId;
    }
    
    /**
     * Jäsen parsii omat tietonsa luettavasta rivistä
     * @param rivi rivi josta jäsen lukee tietonsa
     * @throws ExceptionHandler jos jotain menee pieleen, heitetään tämä
     */
    public void parse(String rivi) throws ExceptionHandler {
        try {
            StringBuffer sb = new StringBuffer(rivi);
            
            String olio_id = Mjonot.erota(sb,'|');
            this.setJasenId(Integer.parseInt(olio_id));
            String olio_etunimi = Mjonot.erota(sb,'|');
            this.setEtunimi(olio_etunimi);
            this.setSukunimi(sb.toString());
            } catch (Exception ex) {
                throw new ExceptionHandler("Tietojen hakemisessa meni joku rikki " + ex.getMessage());
            }
    }
    
    /**
     * Asettaa jäsenelle id:n ilman erillistä rekisteröintiä.
     * Samalla varmistaa, että seuraavaNro on tarpeeksi suuri seuraavaa rekisteröintiä varten.
     * @param id annettava id-numero
     * @example
     * <pre name="test">
     * Jasen jasen = new Jasen();
     * jasen.setJasenId(3);
     * jasen.getJasenId() === 3;
     * </pre>
     */
    public void setJasenId(int id) {
        this.jasenId = id;
        if (this.jasenId >= seuraavaNro) seuraavaNro = this.jasenId + 1;
    }
    
    /**
     * @return jäsenen id-numero
     * @example
     * <pre name="test">
     * Jasen testi = new Jasen();
     * testi.setJasenId(1);
     * testi.getJasenId() === 1;
     * </pre>
     */
    public int getJasenId() {
        return this.jasenId;
    }
    
}
