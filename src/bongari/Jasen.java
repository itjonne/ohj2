package bongari;

/**
 * @author Jonne
 * @version 2 Sep 2020
 *
 */
public class Jasen {
    private int jasenId;
    private String etunimi = "";
    private String sukunimi = "";
    
    /**
     * @return jäsenen etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }
    
    /**
     * @param etunimi annettava etunimi
     */
    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }
    
    /**
     * @param sukunimi annettava sukunimi
     */
    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }
    
    /**
     * @return jäsenen sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }
    
    /**
     * @return jäsenen koko nimi eroteltuna välimerkillä
     */
    public String getKokonimi() {
        return etunimi + " " + sukunimi;
    }
    
    /**
     * @return jäsenen id-numero
     */
    public int getjasenId() {
        return jasenId;
    }
    
}
