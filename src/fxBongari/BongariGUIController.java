package fxBongari;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import bongari.Bongattava;
import bongari.Bongaus;
import bongari.ExceptionHandler;
import bongari.Jasen;
import bongari.Kerho;

/**
 * @author Jonne
 * @version 2 Sep 2020
 *
 */
public class BongariGUIController implements Initializable {
    @FXML private ListChooser<Bongaus> bongauksetLista;
    @FXML private ListChooser<Jasen> jasenLista;
    @FXML private TextField bongauksenTiedotNimi;
    @FXML private TextField bongauksenTiedotTieteellinenNimi;
    @FXML private TextField bongauksenTiedotLaji;
    @FXML private TextField bongauksenTiedotHeimo;
    @FXML private TextArea bongauksenTiedotTietoja;

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
        //tallenna(); TODO: ei osata vielä
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
    
    private Kerho kerho = new Kerho();
    
    public void setKerho(Kerho kerho) {
        this.kerho = kerho;
        naytaJasen();
    }
    
    private void alusta() {
        try {
            kerho.lueKansiosta("data");
            jasenLista.clear();
            jasenLista.addSelectionListener(e -> naytaJasen());
            List<Jasen> jasenet = kerho.etsiJasenet("");
            if (!jasenet.isEmpty()) {
                for (Jasen jasen : jasenet) {
                    if (jasen != null) jasenLista.add(jasen.getEtunimi(), jasen);           
                    }
                // Jos on jäsenistöä, valittuna on ensimmäinen.
                jasenLista.setSelectedIndex(0);
            } else {
                jasenLista.add("Ei jäseniä", new Jasen());
                }
        } catch (ExceptionHandler e) {
            System.out.println(e);
        }
        
        
    }
    
    private void naytaJasen() {
        Jasen jasenKohdalla = jasenLista.getSelectedObject();
        if (jasenKohdalla == null) return;
        naytaJasenenBongaukset(jasenKohdalla);      
    }
    
    private void naytaJasenenBongaukset(Jasen jasen) {
        bongauksetLista.clear();
        bongauksetLista.addSelectionListener(e -> naytaBongaus());
        if (jasen == null) return;
        
        List<Bongaus> bongaukset = kerho.haeJasenenBongaukset(jasen.getJasenId());
        if (!bongaukset.isEmpty()) {
            for (Bongaus bongaus : bongaukset) {
                Bongattava bongattava = kerho.etsiBongattavatId(bongaus.getBongattavaId());
                bongauksetLista.add(bongattava.getNimi(), bongaus);
            }
            bongauksetLista.setSelectedIndex(0);
        } else {
            Bongaus bongaus = new Bongaus();
            bongaus.setTietoja("tyhja");
            bongauksetLista.add("Käyttäjällä ei ole vielä bongauksia", bongaus);
            bongauksetLista.setSelectedIndex(0);
        }
    }
    
    private void naytaBongaus() {
        Bongaus bongausKohdalla = bongauksetLista.getSelectedObject();
        if (bongausKohdalla == null) return;
        naytaBongauksenTiedot(bongausKohdalla);
    }
    
    private void naytaBongauksenTiedot(Bongaus bongaus) {
        if (bongaus.getTietoja() == "tyhja") {
            bongauksenTiedotNimi.setText("");
            bongauksenTiedotTieteellinenNimi.setText("");
            bongauksenTiedotHeimo.setText("");
            bongauksenTiedotLaji.setText("");
            bongauksenTiedotTietoja.setText("");
            return;
        }
        Bongattava bongattava = kerho.etsiBongattavatId(bongaus.getBongattavaId());
        bongauksenTiedotNimi.setText(bongattava.getNimi());
        bongauksenTiedotTieteellinenNimi.setText(bongattava.getTieteellinenNimi());
        bongauksenTiedotHeimo.setText(bongattava.getHeimo());
        bongauksenTiedotLaji.setText(bongattava.getLaji());
        bongauksenTiedotTietoja.setText(bongaus.getTietoja());
    }
}
