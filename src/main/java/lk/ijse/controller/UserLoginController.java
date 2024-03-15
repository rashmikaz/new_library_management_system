package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.UserBo;
import lk.ijse.dto.UserDto;

import java.io.IOException;
import java.util.regex.Pattern;

public class UserLoginController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    MainUserController mainUserController = new MainUserController();
    UserBo userBo = (UserBo) BoFactory.getBOFactory().getBo(BoFactory.BoTypes.USER);





    public boolean validateUser(){
        String userName = txtUsername.getText();

        boolean isUserNameValidated = Pattern.matches("[A-Z][a-zA-Z\\s]+", userName);
        if (!isUserNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid UserName or Password!").show();
            return false;
        }

        String password = txtPassword.getText();

        boolean isPasswordValidated = Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", password);
        if (!isPasswordValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
            return false;
        }

        return true;
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public void openWindow() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_dashboard.fxml"));

        Scene scene = new Scene(rootNode);
        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");
    }


    @FXML
    void btnSignInOnAction(MouseEvent event) {
        if(validateUser()){
            String userName = txtUsername.getText();
            String password = txtPassword.getText();

            UserDto userDto = userBo.userSignIn(userName,password);

            if(userDto != null){
                try{
                    clearFields();
                    openWindow();
                    new Alert(Alert.AlertType.CONFIRMATION,"Welcome User").show();
                    mainUserController.setUserName(userDto.getUserName());
                }catch (Exception e){
                    clearFields();
                    new Alert(Alert.AlertType.ERROR,"Login Failed").show();
                }
            }
        }

    }

    @FXML
    void signUpChangeOnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/user_register.fxml"));

        Scene scene = new Scene(root);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");

    }
    @FXML
    void btnHomeOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_window.fxml"));

        Scene scene = new Scene(rootNode);

        Stage primaryStage =(Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Worm");

    }
}
