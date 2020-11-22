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
import bongari.Bongattava;
import bongari.Bongaus;
import bongari.BongausTietueet;
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
    @FXML private TextField bongauksenTiedotPvm;
    @FXML private TextField bongauksenTiedotKaupunki;
    @FXML private TextArea bongauksenTiedotLisatietoja;
    @FXML private Button buttonMuokkaaBongaus;
    @FXML private Button buttonPoistaBongaus;
    @FXML private Button buttonPoistaJasen;
    @FXML private Button buttonUusiBongaus;
    @FXML private TextField haeJasenet;

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
    }
    
    /**
     * Käsitellään jäsenen poistamiskäsky
     */
    @FXML private void handlePoistaJasen() {
        poistaJasen();
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
     * Jäsenten hakuominaisuudet
     */
    @FXML private void handleHaeJasenet() {
       paivita(0, haeJasenet.getText());
    }
    
    /**
     * Näyttää tietoikkunan
     */
    @FXML private void handleTietoja() {
       tietoja();
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
            jasenLista.setSelectedIndex(0);
            buttonPoistaJasen.setDisable(true);
            buttonUusiBongaus.setDisable(true);
            }                    
    }
    
    /**
     * Yritetään lukea tietokanta kansiosta
     * @param nimi kansion nimi josta luetaan
     */
    public void lueKansio(String nimi) {
        try {
            kerho.lueKansiosta("data/" + nimi.trim());
            alusta();
        } catch (ExceptionHandler e) {
            Dialogs.showMessageDialog("Ei löytynyt kansiota nimellä: " + nimi);
            boolean onnistuiko = alustaKansio(nimi);
            if (!onnistuiko) Platform.exit();
        }
    }
    
    /**
     * Avataan tietokannan kansio
     * @return true jos avaaminen onnistui, muuten false
     */
    public boolean avaa() {
        String vastaus = Dialogs.showInputDialog("Avaa tietokannan kansio", "kerho");        
        if (vastaus != null) {
            if (vastaus.trim().length() == 0) {
                Dialogs.showMessageDialog("Kansion nimi ei saa olla tyhjä! Lopetetaan ohjelma.");
                Platform.exit();
            }
            else if (onkoValidiKansio(vastaus) == false) {
                Dialogs.showMessageDialog("Kansion nimessä laittomia merkkejä! Lopetetaan ohjelma.");
                Platform.exit();
            }
            else lueKansio(vastaus);
        }
        return vastaus != null ? true : false;
    }
    
    private boolean onkoValidiKansio(String vastaus) {
        Pattern pattern = Pattern.compile("^[^\\\\/?%*:|\"<>\\.]+$");
        Matcher matcher = pattern.matcher(vastaus);
        return matcher.find();
    }
    
    /**
     * Alustetaan kerho uuteen kansioon
     * @param nimi kansion nimi
     * @return true jos halutaan alustaa uusi kerho, false jos ei
     */
    private boolean alustaKansio(String nimi) {
        Boolean vastaus = Dialogs.showQuestionDialog("Kerho", "Alustetaanko kerho kansioon: " + nimi, "Kyllä", "Ei");
        if (vastaus == true) {           
                try {
                    kerho.alustaKansio("data/" + nimi);
                } catch (ExceptionHandler e) {
                    Dialogs.showMessageDialog(e.getMessage());
                }
                lueKansio(nimi);
        }
        return vastaus;
    }
    
    private void uusiJasen() {
        Jasen uusiJasen = new Jasen();
        uusiJasen = JasenLisaaDialogController.kysyJasen(null, uusiJasen);
        if (uusiJasen == null) return;
        if (uusiJasen.getEtunimi() == "") return;
        uusiJasen.rekisteroi();
        kerho.lisaa(uusiJasen);
        paivita(0, "");
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
        paivita(jasenLista.getSelectedIndex(), "");
        } catch (CloneNotSupportedException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }       
    }
    
    private void poistaJasen() {
        Jasen jasenKohdalla = jasenLista.getSelectedObject();
        if (jasenKohdalla == null) return;
        Boolean vastaus = Dialogs.showQuestionDialog("Poista jäsen", "Haluatko varmasti poistaa valitun jäsenen?", "Kyllä", "Ei");
        if (vastaus == true) {
        kerho.poistaJasenenBongaukset(jasenKohdalla.getJasenId());
        kerho.poista(jasenKohdalla);
        paivita(0, "");
        } else {
            return;
        }
    }
    
    private void paivita(int valittu, String hakuehto) {
        jasenLista.clear();
        bongauksetLista.clear();
        bongauksenTiedotTyhjenna();
        jasenLista.addSelectionListener(e -> naytaJasen());
        List<Jasen> jasenet = kerho.etsiJasenet(hakuehto);
        if (!jasenet.isEmpty()) {
            buttonPoistaJasen.setDisable(false);
            for (Jasen jasen : jasenet) {
                if (jasen != null) jasenLista.add(jasen.getKokonimi(), jasen);           
                }
            // Jos on jäsenistöä, valittuna on ensimmäinen.
            jasenLista.setSelectedIndex(valittu);
        } else {
            if (hakuehto == "") {
            jasenLista.add("Ei jäseniä", new Jasen());
            buttonPoistaJasen.setDisable(true);
            buttonUusiBongaus.setDisable(true);
                }
            else {
                buttonPoistaJasen.setDisable(true);
                buttonUusiBongaus.setDisable(true);
            }
            }
    }
    
    private void naytaJasenenBongaukset(Jasen jasen) {
        bongauksetLista.clear();
        bongauksenTiedotTyhjenna();
        bongauksetLista.addSelectionListener(e -> naytaBongaus());
        if (jasen == null) return;
        
        if (jasen.getJasenId() > 0) buttonUusiBongaus.setDisable(false);
        
        List<Bongaus> bongaukset = kerho.haeJasenenBongaukset(jasen.getJasenId());
        if (!bongaukset.isEmpty()) {
            bongauksenTiedotPvm.setDisable(false);
            bongauksenTiedotKaupunki.setDisable(false);
            bongauksenTiedotLisatietoja.setDisable(false);
            buttonMuokkaaBongaus.setDisable(false);
            buttonPoistaBongaus.setDisable(false);
            buttonUusiBongaus.setDisable(false);
            for (Bongaus bongaus : bongaukset) {
                Bongattava bongattava = kerho.etsiBongattavatId(bongaus.getBongattavaId());
                bongauksetLista.add(bongattava.getNimi(), bongaus);
            }
            bongauksetLista.setSelectedIndex(0);
        } else {
            Bongaus bongaus = new Bongaus();
            bongaus.setBongausId(-1);
            bongauksetLista.add("Käyttäjällä ei ole vielä bongauksia", bongaus);
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
        if (uusiBongausTietue.getBongattavaId() == 0) return;
        Bongaus uusiBongaus = new Bongaus();
        uusiBongaus.setBongattavaId(uusiBongausTietue.getBongattavaId());
        uusiBongaus.setKaupunki(uusiBongausTietue.getKaupunki());
        uusiBongaus.setPvm(uusiBongausTietue.getPvm());
        uusiBongaus.setTietoja(uusiBongausTietue.getTietoja());
        uusiBongaus.setJasenId(jasenLista.getSelectedObject().getJasenId());
        uusiBongaus.rekisteroi();
        kerho.lisaa(uusiBongaus);
        paivita(jasenLista.getSelectedIndex(), "");
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
            paivita(jasenLista.getSelectedIndex(), "");
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
        paivita(jasenLista.getSelectedIndex(), "");
        } else {
            return;
        }      
    }
    
    private void naytaBongauksenTiedot(Bongaus bongaus) {
        if (bongaus.getBongausId() == -1) {
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
    
    private void bongauksenTiedotTyhjenna() {
        bongauksenTiedotNimi.clear();
        bongauksenTiedotTieteellinenNimi.clear();
        bongauksenTiedotHeimo.clear();
        bongauksenTiedotLaji.clear();
        bongauksenTiedotPvm.clear();
        bongauksenTiedotKaupunki.clear();
        bongauksenTiedotLisatietoja.clear();
    }
    
    private void tietoja() {
        ModalController.showModal(BongariGUIController.class.getResource("AboutView.fxml"), "Kerho", null, "");
    }
    
    private boolean onkoValidiPvm(String pvm) {
        Pattern pattern = Pattern.compile("\\d\\d[/]\\d\\d[/]\\d\\d\\d\\d");
        Matcher matcher = pattern.matcher(pvm);
        return matcher.find();
    }
}
