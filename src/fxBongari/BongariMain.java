package fxBongari;
	
import bongari.Kerho;
import javafx.application.Application;
import javafx.application.Platform;
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

		    Kerho kerho = new Kerho();
		    bongariCtrl.setKerho(kerho);

		    final Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("bongari.css").toExternalForm());
		    primaryStage.setScene(scene);
		    primaryStage.setTitle("Lintubongareiden kerho");
            		    		    
            primaryStage.setOnCloseRequest((event) -> {
                // Kutsutaan voikoSulkea-metodia
                if ( !bongariCtrl.voikoSulkea() ) event.consume(); 
            });
            
            
			primaryStage.show();
			
            Application.Parameters params = getParameters(); 
            if ( params.getRaw().size() > 0 ) 
                bongariCtrl.lueKansio(params.getRaw().get(0));  
            else
                if ( !bongariCtrl.avaa() ) Platform.exit();
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
