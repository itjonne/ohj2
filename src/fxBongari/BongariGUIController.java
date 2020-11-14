package fxBongari;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML private Button buttonMuokkaaBongaus;
    @FXML private Button buttonPoistaBongaus;
    @FXML private Button buttonPoistaJasen;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //alusta();      
    }
    /**
     * Käsitellään uuden jäsenen lisääminen
     */
    @FXML private void handleUusiJasen() {
        uusiJasen();
    }
    
    /**
     * Käsitellään jäsenen muokkauskäsky
     */
    @FXML private void handleMuokkaajasen() {
        muokkaaJasen();
        // ModalController.showModal(BongariGUIController.class.getResource("JasenMuokkaaDialogView.fxml"), "Muokkaa", null, new Jasen());
    }
    
    /**
     * Käsitellään jäsenen poistamiskäsky
     */
    @FXML private void handlePoistaJasen() {
        Jasen jasenKohdalla = jasenLista.getSelectedObject();
        if (jasenKohdalla == null) return;
        Boolean vastaus = Dialogs.showQuestionDialog("Poista jäsen", "Haluatko varmasti poistaa valitun jäsenen?", "Kyllä", "Ei");
        if (vastaus == true) {
        kerho.poistaJasenenBongaukset(jasenKohdalla.getJasenId());
        kerho.poista(jasenKohdalla);
        paivita(0);
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
        poistaBongaus();
    }
    
    /**
     * Käsitellään bongauksen muokkauskäsky
     */
    @FXML private void handleMuokkaaBongaus() {
        muokkaaBongaus();
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
        try {
            kerho.tallenna();
        } catch (ExceptionHandler ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
        }
    }
    
  //===========================================================================================    
  // Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Kerho kerho;
    
    /**
     * @param kerho Kerho, jota käytetään
     */
    public void setKerho(Kerho kerho) {
        this.kerho = kerho;
    }
    
    private void alusta() {
        jasenLista.clear();       
        jasenLista.addSelectionListener(e -> naytaJasen());
        bongauksetLista.clear();
        List<Jasen> jasenet = kerho.etsiJasenet("");
        if (!jasenet.isEmpty()) {
            for (Jasen jasen : jasenet) {
                if (jasen != null) jasenLista.add(jasen.getKokonimi(), jasen);           
                }
            // Jos on jäsenistöä, valittuna on ensimmäinen.
            jasenLista.setSelectedIndex(0);
        } else {
            jasenLista.add("Ei jäseniä", new Jasen());
            }                    
    }
    
    /**
     * @param nimi kansion nimi josta luetaan
     */
    public void lueKansio(String nimi) {
        try {
            kerho.lueKansiosta("data/" + nimi);
            alusta();
        } catch (ExceptionHandler e) {
            Dialogs.showMessageDialog("Ei löytynyt kansiota nimellä: " + nimi);
            boolean onnistuiko = avaa();
            if (!onnistuiko) Platform.exit();
        }
    }
    
    /**
     * @return true jos avaaminen onnistui, muuten false
     */
    public boolean avaa() {
        String vastaus = Dialogs.showInputDialog("Avaa tietokannan kansio", "kerho");
        if (vastaus != null) lueKansio(vastaus);
        return vastaus != null ? true : false;
    }
    
    private void uusiJasen() {
        Jasen uusiJasen = new Jasen();
        uusiJasen = JasenLisaaDialogController.kysyJasen(null, uusiJasen);
        if (uusiJasen == null) return;
        uusiJasen.rekisteroi();
        kerho.lisaa(uusiJasen);
        paivita(0);
    }
    
    private void naytaJasen() {
        Jasen jasenKohdalla = jasenLista.getSelectedObject();
        if (jasenKohdalla == null) return;
        naytaJasenenBongaukset(jasenKohdalla);      
    }
    
    private void muokkaaJasen() {
        Jasen jasenKohdalla = jasenLista.getSelectedObject();    
        if (jasenKohdalla.getJasenId() == 0) {
            Dialogs.showMessageDialog("Lisää ensin jäseniä");
            return;
        }
        
        Jasen muokattuJasen;
        try {
        muokattuJasen = JasenMuokkaaDialogController.kysyJasen(null, jasenKohdalla.clone()); // Palauttaa null, jos jotain meni pieleen
        if (muokattuJasen == null) return;
        // Jos kaikki kunnossa, muokataan.
        kerho.muokkaa(muokattuJasen);
        paivita(jasenLista.getSelectedIndex());
        } catch (CloneNotSupportedException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }       
    }
    
    private void paivita(int valittu) {
        jasenLista.clear();
        jasenLista.addSelectionListener(e -> naytaJasen());
        List<Jasen> jasenet = kerho.etsiJasenet("");
        if (!jasenet.isEmpty()) {
            buttonPoistaJasen.setDisable(false);
            for (Jasen jasen : jasenet) {
                if (jasen != null) jasenLista.add(jasen.getKokonimi(), jasen);           
                }
            // Jos on jäsenistöä, valittuna on ensimmäinen.
            jasenLista.setSelectedIndex(valittu);
        } else {
            jasenLista.add("Ei jäseniä", new Jasen());
            buttonPoistaJasen.setDisable(true);         
            }
    }
    
    private void naytaJasenenBongaukset(Jasen jasen) {
        bongauksetLista.clear();
        bongauksetLista.addSelectionListener(e -> naytaBongaus());
        if (jasen == null) return;
        
        List<Bongaus> bongaukset = kerho.haeJasenenBongaukset(jasen.getJasenId());
        if (!bongaukset.isEmpty()) {
            bongauksenTiedotPvm.setDisable(false);
            bongauksenTiedotKaupunki.setDisable(false);
            bongauksenTiedotLisatietoja.setDisable(false);
            buttonMuokkaaBongaus.setDisable(false);
            buttonPoistaBongaus.setDisable(false);
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
            bongauksenTiedotPvm.setDisable(true);
            bongauksenTiedotKaupunki.setDisable(true);
            bongauksenTiedotLisatietoja.setDisable(true);
            buttonMuokkaaBongaus.setDisable(true);
            buttonPoistaBongaus.setDisable(true);
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
        
        if (uusiBongausTietue == null) return;
        
        Bongaus uusiBongaus = new Bongaus();
        uusiBongaus.setBongattavaId(uusiBongausTietue.getBongattavaId());
        uusiBongaus.setKaupunki(uusiBongausTietue.getKaupunki());
        uusiBongaus.setPvm(uusiBongausTietue.getPvm());
        uusiBongaus.setTietoja(uusiBongausTietue.getTietoja());
        uusiBongaus.setJasenId(jasenLista.getSelectedObject().getJasenId());
        uusiBongaus.rekisteroi();
        kerho.lisaa(uusiBongaus);
        paivita(jasenLista.getSelectedIndex());
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
        if (onkoValidiPvm(bongauksenTiedotPvm.getText()) == false) {
            Dialogs.showMessageDialog("Päivämäärän täytyy olla muotoa dd/mm/yyyy");
        }
        Bongaus muokattuBongaus;
        try {
            muokattuBongaus = bongausKohdalla.clone();
            muokattuBongaus.setKaupunki(bongauksenTiedotKaupunki.getText());
            muokattuBongaus.setPvm(bongauksenTiedotPvm.getText());
            muokattuBongaus.setTietoja(bongauksenTiedotLisatietoja.getText());
            kerho.muokkaa(muokattuBongaus);
            paivita(jasenLista.getSelectedIndex());
        } catch (CloneNotSupportedException e) {   
            Dialogs.showMessageDialog(e.getMessage());
        }    
    }
    
    private void poistaBongaus() {
        Bongaus bongausKohdalla = bongauksetLista.getSelectedObject();
        if (bongausKohdalla == null) return;
        Boolean vastaus = Dialogs.showQuestionDialog("Poista jäsen", "Haluatko varmasti poistaa valitun bongauksen?", "Kyllä", "Ei");
        if (vastaus == true) {
        kerho.poista(bongausKohdalla);
        paivita(jasenLista.getSelectedIndex());
        } else {
            return;
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
    
    private boolean onkoValidiPvm(String pvm) {
        Pattern pattern = Pattern.compile("\\d\\d[/]\\d\\d[/]\\d\\d\\d\\d");
        Matcher matcher = pattern.matcher(pvm);
        return matcher.find();
    }
}
