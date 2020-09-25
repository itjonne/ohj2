package fxBongari;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import bongari.Bongattava;
import bongari.Bongattavat;
import bongari.Bongaus;
import bongari.BongausTietueet;
import bongari.Jasen;
import bongari.Kerho;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
    
    public void alusta() {
        List<Bongattava> bongattavat = uusiBongaus.getBongattavat();
        for (Bongattava bongattava : bongattavat) {
            lajiListChooser.add(bongattava.getNimi(), bongattava);
        }
        lajiListChooser.setSelectedIndex(0);
    }

    
    // ======== Tästä eteenpäin ei käyttöliittymään liittyvää koodia =======
    BongausTietueet uusiBongaus;
    
    private void uusiBongaus() {
        //
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

}
