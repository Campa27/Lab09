
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	private List<Country> nazioni;
	
	@FXML
    private ComboBox<Country> cmbNazione;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	int anno = Integer.parseInt(txtAnno.getText());
    	this.model.createGraph(anno);
    	txtResult.appendText("numero di stati: " + this.model.grafo.vertexSet().size() + "\n" + "numero di confini: " + this.model.grafo.edgeSet().size() + "\n" + "numero di componenti connesse: " + this.model.getNumberOfConnectedComponents() + "\n");
    	for(Country n : this.model.grafo.vertexSet()) {
    		txtResult.appendText(n.getStateNme() + " grado: " + this.model.grafo.edgesOf(n).size() + "\n");
    	}
    }
    
    @FXML
    void handleCerca(ActionEvent event) {
    	txtResult.clear();
    	if(this.model.grafo == null) {
    		txtResult.setText("utilizza prima la funzione \"calcola confini\" per far creare al programma una mappa virtuale");
    	} else {
    		if(cmbNazione.getValue() != null) {
    			txtResult.appendText("nazioni raggiungibili via terra da: " + cmbNazione.getValue() + "\n");
    			Set<Country> lista = this.model.getNazioniRaggiungibili(cmbNazione.getValue());
    			if(lista.size() != 0) {
    				for(Country c : lista) {
    					txtResult.appendText(c.toString() + "\n");
    				}
    			}else {
    				txtResult.appendText("nessuna");
    			}
    		}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	nazioni = this.model.getCountries();
    	for(Country c : nazioni) {
        	cmbNazione.getItems().add(c);
        }
    }
}
