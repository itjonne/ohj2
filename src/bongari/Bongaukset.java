package bongari;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kanta.TiedostostaLuettava;

/**
 * Pitää kirjaa bongauksista
 * Tehty hieman eri tavalla, kokeilin vähän tuota perimistä,
 * myös koodista ei tullut niin sekavaa, kun tuon tiedostosta
 * lukemisen voi jemmata tuonne taustalle. 
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongaukset extends TiedostostaLuettava {
    private final List<Bongaus> alkiot = new ArrayList<Bongaus>();
    private boolean muutettu = false;
    /**
     * Lisää bongauksen tietorakenteeseen
     * @param bongaus lisättävä bongaus
     * @example
     * <pre name="test">
     * Bongaukset bongaukset = new Bongaukset();
     * Bongaus bongaus = new Bongaus();
     * bongaukset.getLkm() === 0;
     * bongaukset.lisaa(bongaus);
     * bongaukset.getLkm() === 1;
     * </pre>
     */
    public void lisaa(Bongaus bongaus) {
        this.alkiot.add(bongaus);
        muutettu = true;
    }
    
    /**
     * Palauttaa tietorakenteessa olevien bongausten lukumäärän
     * @return alkioiden lukumäärä
     * @example
     * <pre name="test">
     * Bongaukset bongaukset = new Bongaukset();
     * Bongaus bongaus = new Bongaus();
     * bongaus.setBongausId(1);
     * bongaukset.getLkm() === 0;
     * bongaukset.lisaa(bongaus);
     * bongaukset.getLkm() === 1;
     * bongaukset.poista(bongaus.getBongausId());
     * bongaukset.getLkm() === 0;
     * </pre>
     */
    public int getLkm() {
        return this.alkiot.size();
    }
    
    /**
     * TODO: Voiko iteraattori hajota?!?!
     * Poistaa bongauksen id-numeron perusteella
     * @param bongausId poistettavan bongauksen id
     * @return true jos poisto onnistui, muuten false
     * @example
     * <pre name="test">
     * Bongaukset bongaukset = new Bongaukset();
     * Bongaus bongaus = new Bongaus();
     * bongaus.setBongausId(1);
     * bongaukset.getLkm() === 0;
     * bongaukset.lisaa(bongaus);
     * bongaukset.getLkm() === 1;
     * bongaukset.poista(0);
     * bongaukset.getLkm() === 1;
     * bongaukset.poista(1);
     * bongaukset.getLkm() === 0;
     * </pre>
     */
    public boolean poista(int bongausId) {
        Iterator<Bongaus> itr = alkiot.iterator();
        while (itr.hasNext()) {
            Bongaus bongaus = itr.next();
            if ( bongaus.getBongausId() == bongausId) {
                itr.remove();
                muutettu = true;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Tallentaa tiedostoon haluttuun paikkaan
     * @throws ExceptionHandler Jos joku menee rikki
     */
    public void tallenna() throws ExceptionHandler {
        if (!muutettu) return;
        
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            fo.println(";Luontobongaajien kerho ry");
            fo.println(";Kenttien jÃ¤rjestys tiedostossa on seuraava:");
            fo.println(";id|etunimi|sukunimi");
            for (Bongaus bongaus : alkiot) {
                if (bongaus != null) {                    
                    fo.println(bongaus.toString());
                }
            }
            //} catch ( IOException e ) { // ei heitä poikkeusta
            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage());
        } catch ( FileNotFoundException ex ) {
            throw new ExceptionHandler("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new ExceptionHandler("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    /**
     * Muokkaa tietorakenteesta löytyvää bongaus-tapahtumaa
     * @param bongaus bongaus, jonka tietoja muokataan
     * @example
     * <pre name="test">
     * Bongaukset bongaukset = new Bongaukset();
     * Bongaus bongaus = new Bongaus();
     * bongaus.setBongausId(1);
     * bongaus.setJasenId(1);
     * Bongaus muokattava = new Bongaus();
     * muokattava.setBongausId(1);
     * muokattava.setJasenId(2);
     * bongaukset.lisaa(bongaus);
     * bongaukset.haeJasenenBongaukset(1).size() === 1;
     * bongaukset.muokkaa(muokattava);
     * bongaukset.haeJasenenBongaukset(1).size() === 0;
     * </pre>
     */
    public void muokkaa(Bongaus bongaus) {
        for (int i = 0; i < alkiot.size(); i++) {
            if (alkiot.get(i).getBongausId() == bongaus.getBongausId()) {
                alkiot.set(i, bongaus);
            }
        }
        muutettu = true;
    }
    
    /**
     * Hakee jäsenen bongaukset jäsenen id:llä
     * TODO: SAAKO VERRATA int == int?!
     * @param jasenId jäsenen id, jonka bongauksia haetaan
     * @return jäsenen löytämät bongaukset
     * @example
     * <pre name="test">
     * Bongaukset bongaukset = new Bongaukset();
     * Bongaus bongaus = new Bongaus();
     * bongaus.setBongausId(1);
     * bongaus.setJasenId(1);
     * Bongaus bongaus2 = new Bongaus();
     * bongaus2.setBongausId(2);
     * bongaus2.setJasenId(1);
     * bongaukset.lisaa(bongaus);
     * bongaukset.haeJasenenBongaukset(1).size() === 1;
     * bongaukset.lisaa(bongaus2);
     * bongaukset.haeJasenenBongaukset(1).size() === 2;
     * </pre>
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
     * @example
     * <pre name="test">
     * Bongaukset bongaukset = new Bongaukset();
     * Bongaus bongaus = new Bongaus();
     * bongaus.setBongausId(1);
     * bongaus.setBongattavaId(1);
     * Bongaus bongaus2 = new Bongaus();
     * bongaus2.setBongausId(2);
     * bongaus2.setBongattavaId(1);
     * bongaukset.lisaa(bongaus);
     * bongaukset.haeBongattavanBongaukset(1).size() === 1;
     * bongaukset.lisaa(bongaus2);
     * bongaukset.haeBongattavanBongaukset(1).size() === 2;
     * </pre>
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
     * @example
     * <pre name="test">
     * #THROWS ExceptionHandler
     * #import java.util.*;
     * List<String> rivit = new ArrayList<String>();
     * String rivi = "1|1|1|Jyväskylä|01/01/2020|Helppo löytö";
     * rivit.add(rivi);
     * Bongaukset bongaukset = new Bongaukset();
     * bongaukset.getLkm() === 0;
     * bongaukset.lisaaTietorakenteeseen(rivit);
     * bongaukset.getLkm() === 1;
     * </pre>
     */
    public void lisaaTietorakenteeseen(List<String> rivit) throws ExceptionHandler {
        for (String rivi : rivit) {
            Bongaus bongaus = new Bongaus();
            bongaus.parse(rivi);
            lisaa(bongaus);
        }
        muutettu = true;
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
