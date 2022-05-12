package imatmini;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Customer;

public class mypages extends AnchorPane {

    @FXML ImageView myinfoimage;
    @FXML ImageView profilepic;
    @FXML Button orderhistory;
    @FXML Button mypages;
    @FXML Button close;
    @FXML Button savechanges;
    @FXML Label myname;
    @FXML TextField firstname;
    @FXML TextField surname;
    @FXML TextField mail;
    @FXML TextField phone;
    @FXML TextField address;
    @FXML TextField postcode;
    @FXML TextField cardnumber;
    @FXML TextField validthru;
    @FXML TextField cvc;

    Customer customer;


    private Model model = Model.getInstance();


    private void initialize() {
        customer = model.getCustomer();

        firstname.setText(customer.getFirstName());
        //setName();

    }

    private void setName() {
        myname.setText(model.getCustomer().getFirstName() + " " + model.getCustomer().getLastName());
    }
}
