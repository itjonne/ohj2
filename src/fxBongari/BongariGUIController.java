package fxBongari;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import bongari.Bongattava;
import bongari.Kerho;

/**
 * @author Jonne
 * @version 2 Sep 2020
 *
 */
public class BongariGUIController implements Initializable {
    @FXML private ListChooser<Bongattava> bongauksetLista;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }
    /**
     * Käsitellään uuden jäsenen lisääminen
     */
    @FXML private void handleUusiJasen() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä");
    }
    
    /**
     * Käsitellään uuden bongauksen lisääminen
     */
    @FXML private void handleUusiBongaus() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä");
    }
    
    /**
     * Käsitellään bongauksen poistaminen
     */
    @FXML private void handlePoistaBongaus() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä");
    }
    
    /**
     * Käsitellään tallennuskäsky
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    /**
     * Käsitellään lopetuskäsky
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, muuten false
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    /**
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
    }
    
  //===========================================================================================    
  // Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Kerho kerho;
    
    private void alusta() {
        Bongattava kala = new Bongattava();
        kala.setBongattavaId(1);
        kala.setNimi("kala");
        Bongattava kissa = new Bongattava();
        kissa.setBongattavaId(2);
        kissa.setNimi("kissa");
        
        bongauksetLista.clear();
        bongauksetLista.add(kala.getNimi(), kala);
        bongauksetLista.add(kissa.getNimi(), kissa);
        
    }
}
