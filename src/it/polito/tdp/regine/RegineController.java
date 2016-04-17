package it.polito.tdp.regine;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.regine.model.CalcolaRegine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RegineController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Spinner<Integer> spinLato;

    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Label lblTrovate ;

    @FXML
    void doCerca(ActionEvent event) {
    	
    	int lato = spinLato.getValue() ;
    	
    	// disegna la scacchiera, dato il lato
    	anchor.getChildren().clear();

    	int latoCasella = (int)(anchor.getWidth() / lato) ;
    	for(int i=0; i<lato; i++) {
    		for(int j=0; j<lato; j++) {
    			Rectangle r = new Rectangle(latoCasella, latoCasella) ;
    			r.setX(j*latoCasella);
    			r.setY(i*latoCasella);
    			if((i+j)%2==0)
    				r.setFill(Color.BEIGE);
    			else
    				r.setFill(Color.KHAKI);
    			anchor.getChildren().add(r) ;
    		}
    	}
    	
    	// trova le soluzioni
    	CalcolaRegine calc = new CalcolaRegine(lato) ;
    	List<List<Integer>> tutte = calc.trova() ;
    	lblTrovate.setText(String.format("%d",tutte.size()));
    	
    	// visualizzane una a caso
    	List<Integer> soluz = tutte.get((int)(Math.random()*tutte.size())) ;
    	for(int i=0; i<lato; i++) {
    		ImageView img = new ImageView(getClass().getResource("Regina_nero.png").toExternalForm()) ;
    		img.setY(i*latoCasella);
    		img.setX(soluz.get(i)*latoCasella);
    		img.setFitWidth(latoCasella);
    		img.setFitHeight(latoCasella);
    		anchor.getChildren().add(img) ;
    	}
    	
    }

    @FXML
    void initialize() {
        assert spinLato != null : "fx:id=\"spinLato\" was not injected: check your FXML file 'Regine.fxml'.";
        assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'Regine.fxml'.";
        
        spinLato.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 12)) ;
        spinLato.getValueFactory().setValue(8);
    }
}
