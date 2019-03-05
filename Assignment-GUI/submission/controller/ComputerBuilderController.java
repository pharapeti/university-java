package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.ComputerBuilder;

public class ComputerBuilderController extends Controller<ComputerBuilder>{
    public final ComputerBuilder getComputerBuilder(){ return model; }
    
    @FXML private Button openBuildMenuBtn;
    @FXML private Button openCatalogueMenuBtn;
    @FXML private Button quitBtn;
    
    @FXML
    private void handleOpenBuild(ActionEvent event) throws IOException {
        ViewLoader.showStage(getComputerBuilder().getBuild(), "/view/build.fxml", "Build Menu", new Stage(), 500);
    }
    
    @FXML
    private void handleOpenCatalogue(ActionEvent event) throws IOException {
        ViewLoader.showStage(getComputerBuilder().getCatalogue(), "/view/catalogue.fxml", "Catalogue", new Stage(), 500);
    }
    
    @FXML
    private void handleQuit(ActionEvent event) throws IOException {
        System.exit(0);
    }
}