package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.BookBo;
import lk.ijse.dto.BookDto;

import java.io.IOException;
import java.sql.SQLException;

public class UserBookController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Label txtName;

    @FXML
    private TextField txtSearchBook;


    BookBo bookBo = (BookBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.BOOK);


    @FXML
    void btnSignOutOnAction(ActionEvent event) throws IOException {

    }

    private void clearFields() {
        txtSearchBook.setText("");
    }



    @FXML
    void btnBooksOnAction(MouseEvent event) {

    }

    @FXML
    void btnBorrowedOnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/borrowed_books.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");


    }

    @FXML
    void btnDashboardOnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/user_dashboard.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");

    }

    @FXML
    void btnPasswordOnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/change_password.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String title = txtSearchBook.getText();

        try{
            BookDto bookDto = bookBo.searchBook(title);

            if(bookDto != null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/search_form.fxml"));
                Parent rootNode = loader.load();

                SearchFormController detailsController = loader.getController();

                detailsController.searchBookDetails(bookDto);

                Scene scene = new Scene(rootNode);

                Stage stage = new Stage();
                stage.setTitle("Book Worm");
                stage.setScene(scene);
                stage.show();

                clearFields();
            }else {
                new Alert(Alert.AlertType.ERROR,"Book Not Found").show();
            }
        }catch(IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }

    @FXML
    void btnSignOutOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_login.fxml"));

        Scene scene = new Scene(rootNode);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");

    }

}
