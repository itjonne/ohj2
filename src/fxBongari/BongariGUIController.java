package fxBongari;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import bongari.Bongattava;
import bongari.Bongattavat;
import bongari.Bongaus;
import bongari.BongausTietueet;
import bongari.ExceptionHandler;
import bongari.Jasen;
import bongari.Kerho;
import fxBongari.JasenMuokkaaDialogController;

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
    @FXML private TextField bongauksenTiedotPvm;
    @FXML private TextField bongauksenTiedotKaupunki;
    @FXML private TextArea bongauksenTiedotLisatietoja;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }
    /**
     * Käsitellään uuden jäsenen lisääminen
     */
    @FXML private void handleUusiJasen() {
        uusiJasen();
    }
    
    @FXML private void handleMuokkaajasen() {
        muokkaaJasen();
        // ModalController.showModal(BongariGUIController.class.getResource("JasenMuokkaaDialogView.fxml"), "Muokkaa", null, new Jasen());
    }
    
    @FXML private void handlePoistaJasen() {
        Jasen jasenKohdalla = jasenLista.getSelectedObject();
        if (jasenKohdalla == null) return;
        Boolean vastaus = Dialogs.showQuestionDialog("Poista jäsen", "Haluatko varmasti poistaa valitun jäsenen?", "Kyllä", "Ei");
        if (vastaus == true) {
        kerho.poista(jasenKohdalla);
        paivita();
        } else {
            return;
        }
    }
    
    /**
     * Käsitellään uuden bongauksen lisääminen
     */
    @FXML private void handleUusiBongaus() {
        uusiBongaus();
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
    @FXML private void handleMuokkaaBongaus() {
        muokkaaBongaus();
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
    
    private void uusiJasen() {
        Jasen uusiJasen = new Jasen();
        uusiJasen = JasenLisaaDialogController.kysyJasen(null, uusiJasen);
        if (uusiJasen == null) return;
        uusiJasen.rekisteroi();
        kerho.lisaa(uusiJasen);
        paivita();
    }
    
    private void naytaJasen() {
        Jasen jasenKohdalla = jasenLista.getSelectedObject();
        if (jasenKohdalla == null) return;
        naytaJasenenBongaukset(jasenKohdalla);      
    }
    
    private void muokkaaJasen() {
        Jasen jasenKohdalla = jasenLista.getSelectedObject();       
        if (jasenKohdalla == null) return;
        
        Jasen muokattuJasen;
        try {
        muokattuJasen = JasenMuokkaaDialogController.kysyJasen(null, jasenKohdalla.clone()); // Palauttaa null, jos jotain meni pieleen
        if (muokattuJasen == null) return;
        // Jos kaikki kunnossa, muokataan.
        kerho.muokkaa(muokattuJasen);
        paivita();
        } catch (CloneNotSupportedException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }       
    }
    
    private void paivita() {
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
    
    /*
     * Tässä piti käyttää aputietuetta, jotta saatiin bongattavat otukset
     * listattua näkymässä. En tiedä voisiko tehdä järkevämmin, yritin muutamalla
     * eri tavalla, mutta tämä oli ensimmäinen toimiva.
     */
    private void uusiBongaus() {
        BongausTietueet uusiBongausTietue = new BongausTietueet();
        List<Bongattava> bongattavat = kerho.etsiBongattavat("");
        uusiBongausTietue.setBongattavat(bongattavat);
        uusiBongausTietue = BongausLisaaDialogController.kysyBongaus(null, uusiBongausTietue);
        
        Bongaus uusiBongaus = new Bongaus();
        uusiBongaus.setBongattavaId(uusiBongausTietue.getBongattavaId());
        uusiBongaus.setKaupunki(uusiBongausTietue.getKaupunki());
        uusiBongaus.setPvm(uusiBongausTietue.getPvm());
        uusiBongaus.setTietoja(uusiBongausTietue.getTietoja());
        uusiBongaus.setJasenId(jasenLista.getSelectedObject().getJasenId());
        uusiBongaus.rekisteroi();
        kerho.lisaa(uusiBongaus);
        paivita();
    }
    
    private void naytaBongaus() {
        Bongaus bongausKohdalla = bongauksetLista.getSelectedObject();
        if (bongausKohdalla == null) return;
        naytaBongauksenTiedot(bongausKohdalla);
    }
    
    private void muokkaaBongaus() {
        Bongaus bongausKohdalla = bongauksetLista.getSelectedObject();
        if (bongausKohdalla == null) return;
        // Alkukantainen virheidenhallinta
        if (bongauksenTiedotKaupunki.getText().trim().contentEquals("") || bongauksenTiedotPvm.getText().trim().contentEquals("")) {
            Dialogs.showMessageDialog("Bongauksen tiedot eivät saa olla tyhjiä");
        }
        Bongaus muokattuBongaus;
        try {
            muokattuBongaus = bongausKohdalla.clone();
            muokattuBongaus.setKaupunki(bongauksenTiedotKaupunki.getText());
            muokattuBongaus.setPvm(bongauksenTiedotPvm.getText());
            muokattuBongaus.setTietoja(bongauksenTiedotLisatietoja.getText());
            kerho.muokkaa(muokattuBongaus);
            paivita();
        } catch (CloneNotSupportedException e) {   
            Dialogs.showMessageDialog(e.getMessage());
        }    
    }
    
    private void naytaBongauksenTiedot(Bongaus bongaus) {
        if (bongaus.getTietoja() == "tyhja") {
            bongauksenTiedotNimi.setText("");
            bongauksenTiedotTieteellinenNimi.setText("");
            bongauksenTiedotHeimo.setText("");
            bongauksenTiedotLaji.setText("");
            bongauksenTiedotPvm.setText("");
            bongauksenTiedotKaupunki.setText("");
            bongauksenTiedotLisatietoja.setText("");
            return;
        }
        Bongattava bongattava = kerho.etsiBongattavatId(bongaus.getBongattavaId());
        bongauksenTiedotNimi.setText(bongattava.getNimi());
        bongauksenTiedotTieteellinenNimi.setText(bongattava.getTieteellinenNimi());
        bongauksenTiedotHeimo.setText(bongattava.getHeimo());
        bongauksenTiedotLaji.setText(bongattava.getLaji());
        // Bongauksen tiedot
        bongauksenTiedotPvm.setText(bongaus.getPvm());
        bongauksenTiedotKaupunki.setText(bongaus.getKaupunki());
        bongauksenTiedotLisatietoja.setText(bongaus.getTietoja());
    }
}
