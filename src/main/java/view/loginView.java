/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.Locale;
import java.util.ResourceBundle;
import view.View;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Localization;

/**
 *
 * @author RJulin
 */
public class loginView extends Application {
    
    //HK 29.3.2019 tietokantayhteyden luominen jo kirjautumisessa
    Controller controller = Controller.getInstance();
    loginView loginscreen = this;
    public Button loginBtn = new Button();
    Button reconnectBtn = new Button();
    Label lblMessage = new Label();
    Label userlabel = new Label();
    Label passlabel = new Label();
    public TextField user = new TextField();
    public PasswordField password = new PasswordField();
    
    //Localisation
    Localization localization = Localization.getInstance();
    ComboBox localeList = new ComboBox(); 
    
    Stage loginPrimaryStage;
    
    public void start(Stage primaryStage) {
        // Käyttöliittymän rakentaminen
        try {
            loginPrimaryStage = primaryStage;
            
            user.setId("user");
            user.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            
            password.setId("password");
            password.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            
            lblMessage.setId("fail");
            lblMessage.setTextFill(Color.RED);
            
            ComboBox<String> localeList = new ComboBox();
            localeList.getItems().addAll(
                localization.getLocaleList()
            );
            localeList.getSelectionModel().selectFirst();
            localeList.setOnAction(e-> {
                localization.changeLocale(localeList.getValue());
                localizationSetText();
            });
            
            loginBtn.setId("login");
            loginBtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    controller.loginUser(loginscreen, primaryStage, user.getText().toString(), password.getText().toString());
                }}
            );
            
            reconnectBtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    controller = Controller.getInstance();
                }}
            );
                    
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(20);
            grid.setHgap(10);

            grid.add(userlabel, 1, 0);
            grid.add(passlabel, 2, 0);
            grid.add(user, 1, 1);
            grid.add(password, 2, 1);
            grid.add(loginBtn,1, 2);
            grid.add(lblMessage, 2, 2);
            grid.add(reconnectBtn, 3, 0);
            grid.add(localeList, 3, 1);
            
            Scene scene = new Scene(grid, 550, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            localizationSetText();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void localizationSetText() {
        //Aseta tekstikenttien teksti uudelleen
        loginPrimaryStage.setTitle(localization.getBundle().getString("view_header"));
        userlabel.setText(localization.getBundle().getString("lbl_username"));
        passlabel.setText(localization.getBundle().getString("lbl_password"));
        loginBtn.setText(localization.getBundle().getString("btn_login"));
        reconnectBtn.setText(localization.getBundle().getString("btn_reconnect"));
    }
    
    public void setErrorMessage(String message) {
        lblMessage.setText(localization.getBundle().getString("lbl_incorrect_password"));
        
        user.setText("");
        password.setText("");
    }
        
    public static void main(String[] args) {
        launch(args);
    }
}
