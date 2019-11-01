/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmc.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class StartPageFXMLController implements Initializable {

    @FXML
    private TextField searchText;
    @FXML
    private TextField totalAttended;
    @FXML
    private TextArea resultText;
    
    BMCApplication bmc;
    @FXML
    private TextField spText;

    public void setBmc(BMCApplication bmc) {
        this.bmc = bmc;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void searchPressed(ActionEvent event) {
        Database database = new Database();
        resultComp r = database.search(searchText.getText());
        totalAttended.setText(r.getAttend());
        resultText.setText(r.getResultText());
        spText.setText("");
    }

    @FXML
    private void spSearchBtn(ActionEvent event) {
        Database database = new Database();
        resultComp r = database.spSearch(spText.getText());
        totalAttended.setText(r.getAttend());
        resultText.setText(r.getResultText());
        searchText.setText("");
    }

    @FXML
    private void savePressed(ActionEvent event) {
        Database database = new Database();
        if(searchText.getText().equals("")){
            database.save(spText.getText(),resultText.getText(), totalAttended.getText());
        }
        if(spText.getText().equals("")){
            database.save(searchText.getText(),resultText.getText(), totalAttended.getText());
        }
            
    }
    
}
