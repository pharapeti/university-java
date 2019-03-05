package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import java.text.NumberFormat;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Build;
import model.Part;

public class BuildController extends Controller<Build>{
    public final Build getBuild() { return model; }
    
    @FXML private TableView buildTableView;
    @FXML private TableColumn typeColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn priceColumn;
    @FXML private Text priceTxt;
    @FXML private Button checkBuildBtn;
    @FXML private Button removeSelectedBtn;
    @FXML private Button closeBtn;
    
    @FXML
    public void initialize() {
        buildTableView.setPlaceholder(new Label("No parts in current build."));
        buildTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        buildTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
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
        priceColumn.setCellFactory(tableCell -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        
        buildTableView.getColumns().addAll(typeColumn, nameColumn, priceColumn);
        buildTableView.setItems(getBuild().getParts());
        
        //Calculate Total Cost of Current Build
        checkPrice();
        
        //Create the listeners
        createListeners();
    }
    
    @FXML
    private void handleCheckBuild(ActionEvent event) throws IOException {
        ViewLoader.showStage(getBuild(), "/view/buildcheck.fxml", "Build Validity Status", new Stage(), 300);
    }
    
    @FXML
    private void handleRemoveSelected(ActionEvent event) throws IOException {
            for(Object o : buildTableView.getSelectionModel().getSelectedItems()){
                buildTableView.getItems().remove(o);
        }
        //Deselect the deleted parts
        buildTableView.getSelectionModel().clearSelection();
    }
    
    @FXML
    private void handleClose(ActionEvent event) throws IOException {
        stage.close();
    }    
    
    //Assign the text of priceTxt to be a prefix + a string formatted double to 2 dec place
    private void checkPrice(){
        priceTxt.setText("Total: $" + String.format("%.2f", getBuild().totalPrice()));
    }

    private void createListeners() {
        //Bind the cells of the TableView to a Boolean Binding and with an override to run an isEmpty() method 
        BooleanBinding selectedBool = new BooleanBinding() {
            {
                super.bind(buildTableView.getSelectionModel().getSelectedCells());
            }
          
            @Override
            protected boolean computeValue() {
                return (buildTableView.getSelectionModel().getSelectedCells().isEmpty());
            }
        };
        
        //Bind the removeSelected button to the 'Selected' BooleanBinding
        removeSelectedBtn.disableProperty().bind(selectedBool);
                
        //If table changes, calculate the price
        buildTableView.getItems().addListener(new ListChangeListener<Part>(){
                @Override
                public void onChanged(javafx.collections.ListChangeListener.Change<? extends Part> pChange) {
                    while(pChange.next()) { checkPrice(); }
                }
        });
    }
    
}
