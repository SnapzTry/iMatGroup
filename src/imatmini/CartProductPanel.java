package imatmini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class CartProductPanel extends AnchorPane {
        //@FXML ImageView imageView;
        @FXML Label productName;


        private Model model = Model.getInstance();
        private Product product;

        private final static double kImageWidth = 100.0;
        private final static double kImageRatio = 0.75;

        public CartProductPanel(Product product) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("smallProductItem.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

            this.product = product;
            productName.setText(product.getName());

            //prizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
            //imageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
            /*if (!product.isEcological()) {
                ecoLabel.setText("");
            }

             */
        }

        @FXML
        private void handleAddAction(ActionEvent event) {
            System.out.println("Add " + product.getName());
            model.addToShoppingCart(product);
        }


}
