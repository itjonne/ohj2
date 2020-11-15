package fxBongari;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import bongari.Bongattava;
import bongari.BongausTietueet;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Jonne
 * @version 25 Sep 2020
 *
 */
public class BongausLisaaDialogController implements ModalControllerInterface<BongausTietueet>, Initializable {

    @FXML
    private ListChooser<Bongattava> lajiListChooser;

    @FXML
    private TextField kaupunkiTextInput;

    @FXML
    private DatePicker pvmDatePicker;

    @FXML
    private TextArea lisatietojaTextArea;

    @FXML
    void handleKumoa() {
        uusiBongaus = null;
        ModalController.closeStage(lajiListChooser);
    }

    @FXML
    void handleLisaa() {
        uusiBongaus();
    }

    @Override
    public BongausTietueet getResult() {
        return uusiBongaus;
    }

    @Override
    public void handleShown() {
       // lajiChoiceBox.requestFocus();
        
    }

    @Override
    public void setDefault(BongausTietueet bongaus) {
        uusiBongaus = bongaus;
        alusta();
    }
    
    /**
     * Alustaa bongattavat otukset listaan
     */
    public void alusta() {
        List<Bongattava> bongattavat = uusiBongaus.getBongattavat();
        for (Bongattava bongattava : bongattavat) {
            String nimi = bongattava.getNimi();
            byte[] bytes = nimi.getBytes(StandardCharsets.UTF_8);
            lajiListChooser.add(new String(bytes, StandardCharsets.UTF_8), bongattava);
        }
        lajiListChooser.setSelectedIndex(0);
    }

    
    // ======== Tästä eteenpäin ei käyttöliittymään liittyvää koodia =======
    BongausTietueet uusiBongaus;
    
    private void uusiBongaus() {
        if (uusiBongaus == null) {
            ModalController.closeStage(lajiListChooser);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (kaupunkiTextInput.getText().trim().contentEquals("")) {
            Dialogs.showMessageDialog("Kaupunki-tietue ei saa olla tyhjä");           
            return;                 
        }
        if (pvmDatePicker.getValue() == null || onkoValidiPvm(pvmDatePicker.getValue().format(formatter)) == false) {
            Dialogs.showMessageDialog("Valitse päivämäärä valikosta"); 
            return;
        }

        this.uusiBongaus.setKaupunki(kaupunkiTextInput.getText().trim());
        this.uusiBongaus.setPvm(pvmDatePicker.getValue().format(formatter));
        this.uusiBongaus.setTietoja(lisatietojaTextArea.getText().trim());
        this.uusiBongaus.setBongattavaId(lajiListChooser.getSelectedObject().getBongattavaId());
        ModalController.closeStage(kaupunkiTextInput);
    }
    
    /**
     * Luodaan jäsenen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null == sovellukselle
     * @param oletus mitä dataa näytetään oletuksena
     * @return täytetty tietue, null jos painetaan Cancel
     */
    public static BongausTietueet kysyBongaus(Stage modalityStage, BongausTietueet oletus) {
        return ModalController.showModal(
                BongausLisaaDialogController.class.getResource("BongausLisaaDialogView.fxml"),
                "Kerho",
                modalityStage, oletus, null
            );
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // alusta();
        
    } 
    
    private Boolean onkoValidiPvm(String pvm) {
        Pattern pattern = Pattern.compile("\\d\\d[/]\\d\\d[/]\\d\\d\\d\\d");
        Matcher matcher = pattern.matcher(pvm);
        //System.out.println(pvm);
        //System.out.println(matcher.find());
        return matcher.find();
    }

}
