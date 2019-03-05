package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import java.text.NumberFormat;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

public class CatalogueController extends Controller<Catalogue>{
    public final Catalogue getCatalogue(){ return model; }
    
    @FXML private TableView catalogueTableView;
    @FXML private TableColumn typeColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn priceColumn;
    @FXML private TextField typeTfield;
    @FXML private TextField minPriceTfield;
    @FXML private TextField maxPriceTfield;
    @FXML private Button addSelectedToBuildBtn;
    @FXML private Button addPartBtn;
    @FXML private Button removeSelectedBtn;
    @FXML private Button closeBtn;
    
    @FXML
    public void initialize() {
        catalogueTableView.setEditable(true);
        catalogueTableView.setPlaceholder(new Label("No results found."));
        catalogueTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        catalogueTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        typeColumn.setText("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                
        nameColumn.setText("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        priceColumn.setText("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        priceColumn.setCellFactory(tableCell -> {
            return new TableCell<Part, Double>() {
                @Override
                protected void updateItem(Double price, boolean empty) {
                    super.updateItem(price, empty);
                    if (!empty) {
                        setText(currencyFormat.format(price));
                    } else {
                        setText(null);
                    }
                }
            };
        });
             
        catalogueTableView.getColumns().addAll(typeColumn, nameColumn, priceColumn);
        catalogueTableView.setItems(getCatalogue().getCurrentView());
        
        createListeners();
    }
    
    @FXML
    private void handleSelectedToBuild(ActionEvent event) throws IOException {
        for(Object o : catalogueTableView.getSelectionModel().getSelectedItems()){
            //Get column values and assign to local variables
            String name  = nameColumn.getCellObservableValue(o).getValue().toString();
            String type = typeColumn.getCellObservableValue(o).getValue().toString();
            Double price = Double.parseDouble(priceColumn.getCellObservableValue(o).getValue().toString());
            
            //Add part to Build.parts
            Part partToAdd = new Part(name, type, price);
            getCatalogue().addToBuild(partToAdd);
        }
    }
    
    //The AddToCatalogue window runs off the Catalogue which is called, but uses the AddToCatalogueController in fxml
    @FXML
    private void handleAddPart(ActionEvent event) throws IOException {
        ViewLoader.showStage(getCatalogue(), "/view/addtocatalogue.fxml", "Add New Part to Catalogue", new Stage(), 300);
    }
    
    @FXML
    private void handleRemoveSelected(ActionEvent event) throws IOException {        
        for(Object o : catalogueTableView.getSelectionModel().getSelectedItems()){
            catalogueTableView.getItems().remove(o);
        }
        //Deselect the parts that were just deleted
        catalogueTableView.getSelectionModel().clearSelection();
    }
    
    @FXML
    private void handleClose(ActionEvent event) throws IOException {
        stage.close();
    }
    
    //This method gathers the text from the 3 textfields and then passes them through filterList()
    private void filterList(){
        String type = typeTfield.getText().toUpperCase();
        String minPrice = minPriceTfield.getText().toUpperCase();
        String maxPrice = maxPriceTfield.getText().toUpperCase();
                
        getCatalogue().filterList(type, minPrice, maxPrice);
    }

    private void createListeners() {
        //Bind the cells of the TableView to a Boolean Binding and with an override to run an isEmpty() method 
        BooleanBinding selectedBool = new BooleanBinding() {
            {
                super.bind(catalogueTableView.getSelectionModel().getSelectedCells());
            }
          
            @Override
            protected boolean computeValue() {
                return (catalogueTableView.getSelectionModel().getSelectedCells().isEmpty());
            }
        };
        
        //Bind these two buttons to the 'Selected' BooleanBinding
        addSelectedToBuildBtn.disableProperty().bind(selectedBool);
        removeSelectedBtn.disableProperty().bind(selectedBool);
        
        //If typefield, minprice or maxprice changes, then run filterList()
        typeTfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList();
        });

        minPriceTfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList();
        });
        
        maxPriceTfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList();
        });
    }
}
