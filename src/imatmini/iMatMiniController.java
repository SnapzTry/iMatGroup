/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;


/**
 *
 * @author oloft
 */
public class iMatMiniController implements Initializable, ShoppingCartListener {

    @FXML
    private Label erbjudanden;



    // Shopping Pane
    @FXML
    private AnchorPane shopPane;
    @FXML
    private TextField searchField;
    @FXML
    private Label itemsLabel;
    @FXML
    private Label costLabel;
    @FXML
    private FlowPane productsFlowPane;
    
    // Account Pane
    @FXML
    private AnchorPane accountPane;
    @FXML 
    ComboBox cardTypeCombo;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField nameTextField;
    @FXML 
    private ComboBox monthCombo;
    @FXML
    private ComboBox yearCombo;
    @FXML
    private TextField cvcField;
    @FXML
    private Label purchasesLabel;
    
    // Other variables
    private final Model model = Model.getInstance();

    // Shop pane actions
    @FXML
    private void handleShowAccountAction(ActionEvent event) {
        openAccountView();
    }
    
    @FXML
    private void handleSearchAction(ActionEvent event) {
        
        List<Product> matches = model.findProducts(searchField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }
    
    @FXML
    private void handleClearCartAction(ActionEvent event) {
        model.clearShoppingCart();
    }
    
    @FXML
    private void handleBuyItemsAction(ActionEvent event) {
        model.placeOrder();
        costLabel.setText("Köpet klart!");
    }
    
    // Account pane actions
     @FXML
    private void handleDoneAction(ActionEvent event) {
        closeAccountView();
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model.getShoppingCart().addShoppingCartListener(this);

        updateProductList(model.getProducts());
        updateBottomPanel();
        
        setupAccountPane();
        
    }    
    
    // Navigation
    public void openAccountView() {
        updateAccountPanel();
        accountPane.toFront();
    }

    public void closeAccountView() {
        updateCreditCard();
        shopPane.toFront();
    }
    
    // Shope pane methods
    @Override
     public void shoppingCartChanged(CartEvent evt) {
        updateBottomPanel();
    }
   
    
    private void updateProductList(List<Product> products) {

        productsFlowPane.getChildren().clear();

        for (Product product : products) {

            productsFlowPane.getChildren().add(new ProductPanel(product));
        }

    }
    
    private void updateBottomPanel() {
        
        ShoppingCart shoppingCart = model.getShoppingCart();
        
        itemsLabel.setText(String.valueOf(shoppingCart.getItems().size()));
        costLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));
        
    }
    
    private void updateAccountPanel() {
        
        CreditCard card = model.getCreditCard();
        
        numberTextField.setText(card.getCardNumber());
        nameTextField.setText(card.getHoldersName());
        
        cardTypeCombo.getSelectionModel().select(card.getCardType());
        monthCombo.getSelectionModel().select(""+card.getValidMonth());
        yearCombo.getSelectionModel().select(""+card.getValidYear());

        cvcField.setText(""+card.getVerificationCode());
        
        purchasesLabel.setText(model.getNumberOfOrders()+ " tidigare inköp hos iMat");
        
    }
    
    private void updateCreditCard() {
        
        CreditCard card = model.getCreditCard();
        
        card.setCardNumber(numberTextField.getText());
        card.setHoldersName(nameTextField.getText());
        
        String selectedValue = (String) cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);
        
        selectedValue = (String) monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));
        
        selectedValue = (String) yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));
        
        card.setVerificationCode(Integer.parseInt(cvcField.getText()));

    }
    
    private void setupAccountPane() {
                
        cardTypeCombo.getItems().addAll(model.getCardTypes());
        
        monthCombo.getItems().addAll(model.getMonths());
        
        yearCombo.getItems().addAll(model.getYears());
        
    }


    public void home_view() throws IOException {
        Stage primaryStage = (Stage) erbjudanden.getScene().getWindow();
        primaryStage.close();
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("iMatMini.fxml"));
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void erbjud_view() throws IOException {
        Stage primaryStage = (Stage) erbjudanden.getScene().getWindow();
        primaryStage.close();
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("erbjudanden.fxml"));
        primaryStage.setTitle("Erbjudanden");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void favorites_view() throws IOException {
        Stage primaryStage = (Stage) erbjudanden.getScene().getWindow();
        primaryStage.close();
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("favoriter.fxml"));
        primaryStage.setTitle("Favoriter");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void myLists_view() throws IOException {
        Stage primaryStage = (Stage) erbjudanden.getScene().getWindow();
        primaryStage.close();
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("inkoplistor.fxml"));
        primaryStage.setTitle("Inköpslistor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
