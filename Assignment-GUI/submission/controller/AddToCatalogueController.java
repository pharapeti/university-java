package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.*;
import model.Catalogue;

public class AddToCatalogueController extends Controller<Catalogue>{
    public final Catalogue getCatalogue() { return model; }
    
    @FXML private TextField typeTxt;
    @FXML private TextField nameTxt;
    @FXML private TextField priceTxt;
    @FXML private Button addBtn;
    
    @FXML
    private void handleAdd(ActionEvent event) throws IOException {      
        //Get values from the textfields
        String name = nameTxt.getText();
        String type = typeTxt.getText();
        
        //Check if the price is 
        if(priceTxt.getText().length()!=0){
            
            //try catch this
            try { 
                Double price = Double.parseDouble(priceTxt.getText()); 
                
                //If it is valid, add it to the catalogue.
                if(nameValid(name) && typeValid(type) && priceValid(price)){
                    getCatalogue().addPart(name, type, price);
                    stage.close();
                } else {
                    //Else open the error window.
                    ViewLoader.showStage(new ErrorController(), "/view/error.fxml", "Incorrect Input", new Stage(), 300);
                } 
            }
            
            catch(NumberFormatException e){
                //If NaN, Invalid price entered
                ViewLoader.showStage(new ErrorController(), "/view/error.fxml", "Incorrect Input", new Stage(), 300);
            }

        } else {
            //ASK TUTOR IF I NEED THIS
            ViewLoader.showStage(new ErrorController(), "/view/error.fxml", "Incorrect Input", new Stage(), 300);
        }
    }
    
    //Make sure that the type is not null
    private boolean typeValid(String type){
        return type.length()!=0;
    }
    
    //Make sure that the name is not null
    private boolean nameValid(String name){
        return name.length()!=0;
    }
    
    //Make sure the price is a number and also greater than 0.0; can't be negative
    private boolean priceValid(Double price){
        return !price.isNaN() && price >= 0.0;
    } 

}
