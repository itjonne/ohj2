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
public class JasenMuokkaaDialogController implements ModalControllerInterface<Jasen> {
    @FXML private TextField textFieldEtunimi;
    @FXML private TextField textFieldSukunimi;
    
    @FXML void handleKumoa() {
        this.jasenKohdalla = null;
        ModalController.closeStage(textFieldEtunimi);
    }

    @FXML void handleTallenna() {
        if (jasenKohdalla != null && (jasenKohdalla.getEtunimi().trim().contentEquals("") || jasenKohdalla.getSukunimi().trim().contentEquals(""))) {
            Dialogs.showMessageDialog("Nimi ei saa olla tyhjä");
            return;
        }
        this.jasenKohdalla.setEtunimi(textFieldEtunimi.getText());
        this.jasenKohdalla.setSukunimi(textFieldSukunimi.getText());
        ModalController.closeStage(textFieldEtunimi);
    }

    // =======================================================
    private Jasen jasenKohdalla;
    
    /**
     * Näyttää jäsenen tiedot tekstikentissä
     * @param jasen näytettävä jäsen
     */
    public void naytaJasen(Jasen jasen) {
        textFieldEtunimi.setText(jasen.getEtunimi());
        textFieldSukunimi.setText(jasen.getSukunimi());
    }

    @Override
    public Jasen getResult() {
         return this.jasenKohdalla;
    }

    @Override
    public void handleShown() {
        textFieldEtunimi.requestFocus();
    }
    
    @Override
    public void setDefault(Jasen oletus) {
        this.jasenKohdalla = oletus;
        naytaJasen(jasenKohdalla);
        
    }
    
    /**
     * Luodaan jäsenen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null == sovellukselle
     * @param oletus mitä dataa näytetään oletuksena
     * @return täytetty tietue, null jos painetaan Cancel
     */
    public static Jasen kysyJasen(Stage modalityStage, Jasen oletus) {
        return ModalController.showModal(
                JasenMuokkaaDialogController.class.getResource("JasenMuokkaaDialogView.fxml"),
                "Kerho",
                modalityStage, oletus, null 
            );
    } 
}


