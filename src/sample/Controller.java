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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller implements Message{



    @FXML
    private TextField startLogin;

    @FXML
    private PasswordField startPassword;

    @FXML
    private Button startEntry;

    @FXML
    private Button startReg;


    @FXML
    void initialize(){
        App app;
        MessageFactory factory;
        factory = new ErFab();
        app = new App(factory);
        startEntry.setOnAction(event -> {//Вход
            String loginLog = startLogin.getText().trim();
            String loginPassword = startPassword.getText().trim();
            if(!loginLog.equals("")&& !loginPassword.equals("")){
                try {
                    loginUser(loginLog, loginPassword);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else{
                Alert loginOrPassIsEmpty = new Alert(Alert.AlertType.ERROR);
                loginOrPassIsEmpty.setHeaderText("Ошибка!");
                loginOrPassIsEmpty.setContentText("Пароль или логин не заполнены!");
                loginOrPassIsEmpty.showAndWait();
                System.out.println("Пароль или логин не заполнены!");



            }

        });


        startReg.setOnAction(event -> {//Переход на страницу регистрации. Нажатие на кнопку РЕГЕСТРАЦИЯ
            startReg.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("signUp.fxml"));
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
            stage.show();
        });

    }

    private void loginUser(String loginLog, String loginPassword) throws SQLException, ClassNotFoundException {//Логин юзира
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(loginLog);
        user.setPassword(loginPassword);
        ResultSet res = dbHandler.getUser(user);

        int cont = 0;
        while (res.next()){
            cont++;
        }
        if(cont>=1) {
            System.out.println("Нашел");
            Alert successfulLogin = new Alert(Alert.AlertType.INFORMATION);
            successfulLogin.setHeaderText("Все хорошо");
            successfulLogin.setContentText("Вы вошли в систему");
            successfulLogin.showAndWait();
            ///////////////////////Открытие магазина при успешной авторизации
            startEntry.getScene().getWindow().hide();
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("../views/market.fxml"));
            try {
                loader1.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FXMLLoader fXMLLoader;
            Parent root1 = loader1.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            //stage.setResizable(false);
            stage.show();
        }else {
            Alert failedLogin = new Alert(Alert.AlertType.ERROR);
            failedLogin.setHeaderText("Ошибка!");
            failedLogin.setContentText("Не верный логин или пароль");
            failedLogin.showAndWait();
        }
    }

    @Override
    public void Message() {

    }
}
