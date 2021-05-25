package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerSignUp {

    @FXML
    private TextField regLogin;

    @FXML
    private PasswordField regPassword;

    @FXML
    private Button regEntry;

    @FXML
    private PasswordField regPasswordRepead;

    @FXML
    private Button regBack;

    @FXML
    void initialize(){
        regBack.setOnAction(event -> {//Вернуться на начальную страничку
            regBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sample.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FXMLLoader fXMLLoader;
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });
        DataBaseHandler dbHandler = new DataBaseHandler();
        regEntry.setOnAction(event -> {//Регистрация нового юзера при нажатии на кнопку ВХОД

            try {
                signUpNewUser();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        });
    }

    private void signUpNewUser() throws SQLException, ClassNotFoundException {//Регистрация нового юзера
        DataBaseHandler dbHandler = new DataBaseHandler();
        String login = regLogin.getText();
        String password = regPassword.getText();
        String password2 = regPasswordRepead.getText();
        if (!password.equals(password2)) {
            Alert errorPasswordsDontMatch = new Alert(Alert.AlertType.ERROR);
            errorPasswordsDontMatch.setHeaderText("Ошибка!");
            errorPasswordsDontMatch.setContentText("Пароли не совпадают");
            errorPasswordsDontMatch.showAndWait();
            return;
        }else if(password.equals("")||password2.equals("")||login.equals("")){
            Alert errorPasswordOrLoginNotFind = new Alert(Alert.AlertType.ERROR);
            errorPasswordOrLoginNotFind.setHeaderText("Ошибка!");
            errorPasswordOrLoginNotFind.setContentText("Одно из полей не заполнено!");
            errorPasswordOrLoginNotFind.showAndWait();
            return;
        }
        User user = new User(login, password);
        dbHandler.signUpUser(user);
        Alert successfulRegistration = new Alert(Alert.AlertType.INFORMATION);
        successfulRegistration.setHeaderText("Все хорошо");
        successfulRegistration.setContentText("Регистрация прошла успешно!");
        successfulRegistration.showAndWait();

        regEntry.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/market.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXMLLoader fXMLLoader;
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        //stage.setResizable(false);
        stage.showAndWait();
    }
}
