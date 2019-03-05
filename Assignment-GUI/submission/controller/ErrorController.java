package controller;

import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ErrorController extends Controller{
        
    @FXML private Button closeBtn;
    
    @FXML
    private void handleClose(ActionEvent event) throws IOException {
        stage.close();
    }
}
