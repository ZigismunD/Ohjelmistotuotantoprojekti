/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import view.View;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author RJulin
 */
public class loginView extends Application {

    //HK 29.3.2019 tietokantayhteyden luominen jo kirjautumisessa
    Controller controller;
    loginView loginscreen = this;
    Button loginBtn;
    Label lblMessage = new Label();
    Label userlabel;
    Label passlabel;
    TextField user = new TextField();
    PasswordField password = new PasswordField();
    
    public void start(Stage primaryStage) {
        //Luo tietokantayhteys
        controller =  new Controller();
        // Käyttöliittymän rakentaminen
        try {
            primaryStage.setTitle("Kirjaudu järjestelmään");
            
            userlabel = new Label("Käyttäjänimi");
            
            user.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

            passlabel = new Label("Salasana");
            
            password.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            
            lblMessage.setTextFill(Color.RED);
                
            loginBtn = new Button();
            loginBtn.setText("Kirjaudu");
            loginBtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    controller.loginUser(loginscreen, primaryStage, user.getText().toString(), password.getText().toString());
                }}
            );

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(20);
            grid.setHgap(10);

            grid.add(userlabel, 1, 0);
            grid.add(passlabel, 2, 0);
            grid.add(user, 1, 1);            // sarake, rivi
            grid.add(password, 2, 1);
            grid.add(loginBtn,1, 2);            // sarake, rivi
            grid.add(lblMessage, 2, 2);

            Scene scene = new Scene(grid, 550, 400);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setErrorMessage(String message) {
        lblMessage.setText(message);
        
        user.setText("");
        password.setText("");
    }
        
    public static void main(String[] args) {
            launch(args);
    }
}