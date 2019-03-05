package controller;

import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Build;

public class CheckBuildController extends Controller<Build>{
    public final Build getBuild(){ return model; }
    
    @FXML private Label validLbl;
    @FXML private Label cpuLbl;
    @FXML private Label mboardLbl;
    @FXML private Label ramLbl;
    @FXML private Label caseLbl;
    @FXML private Label storageLbl;
    @FXML private Button closeBtn;
    
    @FXML
    public void initialize() {
        //Copy the build and search through it
        Build searchBuild = getBuild();
        
        if(searchBuild.isValid()){
            //Show sucess label
            validLbl.visibleProperty().setValue(Boolean.TRUE);
            validLbl.setText("This build is functional.");
            
            //Hide all error labels
            cpuLbl.visibleProperty().setValue(Boolean.FALSE);
            cpuLbl.managedProperty().setValue(Boolean.FALSE);
            mboardLbl.visibleProperty().setValue(Boolean.FALSE);
            mboardLbl.managedProperty().setValue(Boolean.FALSE);
            ramLbl.visibleProperty().setValue(Boolean.FALSE);
            ramLbl.managedProperty().setValue(Boolean.FALSE);
            caseLbl.visibleProperty().setValue(Boolean.FALSE);
            caseLbl.managedProperty().setValue(Boolean.FALSE);
            storageLbl.visibleProperty().setValue(Boolean.FALSE);
            storageLbl.managedProperty().setValue(Boolean.FALSE);
            
        } else {
            //Show all error labels and hide valid label
            validLbl.visibleProperty().setValue(Boolean.FALSE);
            cpuLbl.visibleProperty().setValue(Boolean.TRUE);
            cpuLbl.managedProperty().setValue(Boolean.TRUE);
            mboardLbl.visibleProperty().setValue(Boolean.TRUE);
            mboardLbl.managedProperty().setValue(Boolean.TRUE);
            ramLbl.visibleProperty().setValue(Boolean.TRUE);
            ramLbl.managedProperty().setValue(Boolean.TRUE);
            caseLbl.visibleProperty().setValue(Boolean.TRUE);
            caseLbl.managedProperty().setValue(Boolean.TRUE);
            storageLbl.visibleProperty().setValue(Boolean.TRUE);
            storageLbl.managedProperty().setValue(Boolean.TRUE);
            
            //Hide the error labels that exist
            if(searchBuild.hasPartOfType("cpu")){ 
                cpuLbl.visibleProperty().setValue(Boolean.FALSE);
                cpuLbl.managedProperty().setValue(Boolean.FALSE);
                
            }
            if(searchBuild.hasPartOfType("motherboard")){ 
                mboardLbl.visibleProperty().setValue(Boolean.FALSE); 
                mboardLbl.managedProperty().setValue(Boolean.FALSE);
            }
            
            if(searchBuild.hasPartOfType("memory")){ 
                ramLbl.visibleProperty().setValue(Boolean.FALSE); 
                ramLbl.managedProperty().setValue(Boolean.FALSE); 
            }
            
            if(searchBuild.hasPartOfType("case")){ 
                caseLbl.visibleProperty().setValue(Boolean.FALSE); 
                caseLbl.managedProperty().setValue(Boolean.FALSE); 
            }
            
            if(searchBuild.hasPartOfType("storage")){ 
                storageLbl.visibleProperty().setValue(Boolean.FALSE); 
                storageLbl.managedProperty().setValue(Boolean.FALSE); 
            }
        }
    }

    @FXML
    private void handleClose(ActionEvent event) throws IOException {
        stage.close();
    }
}
