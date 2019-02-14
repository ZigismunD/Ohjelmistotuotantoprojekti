/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietokonekauppa;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author RJulin
 */
public class loginView extends Application {
	public void start(Stage primaryStage) {
		// Käyttöliittymän rakentaminen
		try {
			primaryStage.setTitle("Kirjaudu järjestelmään");

                    Button loginBtn = new Button();
	        loginBtn.setText("Kirjaudu");
                loginBtn.setOnAction(new EventHandler<ActionEvent>(){
                     public void handle(ActionEvent ae){
                     Stage Viewclass = new Stage();
                     new View().start(Viewclass);
                     

                     }
                });

                    TextField user = new TextField("Käyttäjätili");
	        user.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                    TextField password = new TextField("Salasana");
	        password.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));



	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setVgap(20);
	        grid.setHgap(10);

	        grid.add(user, 0, 0);            // sarake, rivi
	        grid.add(password, 1, 0);
	        grid.add(loginBtn,0, 1);            // sarake, rivi

	        Scene scene = new Scene(grid, 500, 400);
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
