/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.*;
import model.ComputerBuilder;
import javafx.scene.control.*;

/**
 *
 * @author student
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button openBuildMenuBtn;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void handleOpenCatalogue(ActionEvent event) {
//        ViewLoader.showStage(label, 'd', 'Catalogue Menu', stage);
    }
    
    @FXML
    private void handleOpenBuild(ActionEvent event) throws IOException {
//        ViewLoader.showStage(build, "/view/build.fxml", "Build Menu", new Stage());
        ViewLoader.showStage(new ComputerBuilder(), "/view/computerbuilder.fxml", "Build Menu", new Stage());
    }
    
    @FXML
    private void handleCloseCatalogue(ActionEvent event) {
//        stage.close();
    }
    
    @FXML
    private void handleCloseBuild(ActionEvent event) {
//        stage.close()
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
