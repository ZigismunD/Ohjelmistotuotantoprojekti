/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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

    String user1 = "Admin";
    String pw1 = "salis";
    String checkUser, checkPw;

    public void start(Stage primaryStage) {
        // Käyttöliittymän rakentaminen
        try {
            primaryStage.setTitle("Kirjaudu järjestelmään");
            
            Label userlabel = new Label("Käyttäjänimi");
            TextField user = new TextField();
            user.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

            Label passlabel = new Label("Salasana");
            PasswordField password = new PasswordField();
            password.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

            Label lblMessage = new Label();
            
            Button loginBtn = new Button();
            loginBtn.setText("Kirjaudu");
            loginBtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    checkUser = user.getText().toString();
                    checkPw = password.getText().toString();
                    if (checkUser.equals(user1) && checkPw.equals(pw1)) { //equals(get.userhibernatesta)
                        Stage Viewclass = new Stage();
                        new View().start(Viewclass);
                    } else {
                        lblMessage.setText("Salasana väärin.");
                        lblMessage.setTextFill(Color.RED);
                    }
                    user.setText("");
                    password.setText("");
                }
                     
                });

        
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
        
	public static void main(String[] args) {
		launch(args);
	}
        

}