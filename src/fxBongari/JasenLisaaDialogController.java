package fxBongari;

import java.net.URL;
import java.util.ResourceBundle;

import bongari.Jasen;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * TODO: Pitääkö olla Initializable, vai riittääkö vain tuo setDefault?
 * @author Jonne
 * @version 18 Sep 2020
 */
public class JasenLisaaDialogController implements ModalControllerInterface<Jasen> {
    @FXML private TextField textFieldEtunimi;
    @FXML private TextField textFieldSukunimi;
    
    @FXML void handleKumoa() {
        this.uusiJasen = null;
        ModalController.closeStage(textFieldEtunimi);
    }

    @FXML void handleLisaa() {
        if (uusiJasen != null && (textFieldEtunimi.getText().trim().contentEquals("") || textFieldSukunimi.getText().trim().contentEquals(""))) {
            Dialogs.showMessageDialog("Nimi ei saa olla tyhjä");
            return;
        }
        this.uusiJasen.setEtunimi(textFieldEtunimi.getText().trim());
        this.uusiJasen.setSukunimi(textFieldSukunimi.getText().trim());
        ModalController.closeStage(textFieldEtunimi);
    }

    // =======================================================
    private Jasen uusiJasen;

    @Override
    public Jasen getResult() {
         return this.uusiJasen;
    }

    @Override
    public void handleShown() {
        textFieldEtunimi.requestFocus();
    }
    
    @Override
    public void setDefault(Jasen oletus) {
        this.uusiJasen = oletus;
    }
    
    /**
     * Luodaan jäsenen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null == sovellukselle
     * @param oletus mitä dataa näytetään oletuksena
     * @return täytetty tietue, null jos painetaan Cancel
     */
    public static Jasen kysyJasen(Stage modalityStage, Jasen oletus) {
        return ModalController.showModal(
                JasenLisaaDialogController.class.getResource("JasenLisaaDialogView.fxml"),
                "Kerho",
                modalityStage, oletus, null 
            );
    } 
}


