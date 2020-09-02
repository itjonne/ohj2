package fxBongari;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Jonne
 * @version 2 Sep 2020
 *
 */
public class BongariMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    final FXMLLoader ldr = new FXMLLoader(getClass().getResource("BongariGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
            final BongariGUIController bongariCtrl = (BongariGUIController)ldr.getController();
            
            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("bongari.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bongari");
            
            primaryStage.setOnCloseRequest((event) -> {
                // Kutsutaan voikoSulkea-metodia
                if ( !bongariCtrl.voikoSulkea() ) event.consume();
            });
            
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
