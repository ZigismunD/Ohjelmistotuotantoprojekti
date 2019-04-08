/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Osa;
import model.Product;
import model.Tilaus;



/**
 *
 * @author RJulin
 */

    /**
     * Rakentaa käyttöliittymän Varasto sivun
     * Sivulla tarkastellaan varaston paketteja ja osia.
     * Painiketta painamalla voi näyttää tietokannasta paketit tai halutun tyyppisen osan
     */


public class Tab2 extends Tab {
    
    
    private GridPane grid2;
    //private Tab tab2;
    private TableView tableVarasto;
    Scene scene;
    TabPane tabPane;
    ObservableList<Product> data;
    List<Product> tilausrivit;
    List<Osa> osaLista;
    ObservableList<Osa> osaData;
    List<Tilaus> tilausLista;
    ObservableList<Tilaus> tilausData;
   Controller controller = new Controller();
    public Tab2(){
    createTab2();
    }
    
    private void createTab2() {
        //tab2 = new Tab();
        this.setText("Varasto");

        grid2 = new GridPane();
        grid2.setId("Varasto");
        grid2.setHgap(0); // Horizontal gap
        grid2.setVgap(0); // Vertical gap
        //grid2.setStyle("-fx-background-image: url('https://effiasoft.com/wp-content/uploads/app-background.png')");
        
        //Text lblexample = new Text("esimerkki");
        // Nappula, josta saa prosessorit näkyviin
        Button btnProcessors = new Button();
        btnProcessors.setText("Prosessorit");
        btnProcessors.setPrefSize(200, 100);
        btnProcessors.setOnAction(e-> {
            osaLista = controller.getOsat("Prosessori");
            osaData = FXCollections.observableArrayList(osaLista);
            tableVarasto.setItems(osaData);
        });
        grid2.add(btnProcessors, 0, 0);

        // Nappula, josta saa emolevyt näkyviin
        Button btnMotherboard = new Button();
        btnMotherboard.setText("Emolevyt");
        btnMotherboard.setPrefSize(200, 100);
        grid2.add(btnMotherboard, 0, 1);
        btnMotherboard.setOnAction(e-> {
            osaLista = controller.getOsat("Emolevy");
            osaData = FXCollections.observableArrayList(osaLista);
            tableVarasto.setItems(osaData);
        });

        // Nappula, josta saa näytönohjaimet näkyviin
        Button btnGraphics = new Button();
        btnGraphics.setText("Näytönohjaimet");
        btnGraphics.setPrefSize(200, 100);
        grid2.add(btnGraphics, 0, 2);

        // Nappula, josta saa muistit näkyviin
        Button btnRam = new Button();
        btnRam.setText("RAM");
        btnRam.setPrefSize(200, 100);
        grid2.add(btnRam, 0, 3);

        // Nappula, josta saa virtalähteet näkyviin
        Button btnPower = new Button();
        btnPower.setText("Virtalähteet");
        btnPower.setPrefSize(200, 100);
        grid2.add(btnPower, 0, 4);

        // Nappula, josta saa SSD:t näkyviin
        Button btnSsd = new Button();
        btnSsd.setText("SSD");
        btnSsd.setPrefSize(200, 100);
        grid2.add(btnSsd, 0, 5);

        // Nappula, josta saa HDD:t näkyviin
        Button btnHdd = new Button();
        btnHdd.setText("HHD");
        btnHdd.setPrefSize(200, 100);
        grid2.add(btnHdd, 0, 6);

        // Nappula, josta saa HDD:t näkyviin
        Button btnCase = new Button();
        btnCase.setText("Kotelo");
        btnCase.setPrefSize(200, 100);
        grid2.add(btnCase, 0, 7);

        //grid2.add(lblexample,15,11);
        tableVarasto = new TableView();
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        tableVarasto.setEditable(true);

        TableColumn brand = new TableColumn("Merkki");
        brand.setStyle("-fx-font-size: 14pt;");
        brand.setMinWidth(200);

        TableColumn product = new TableColumn("Tuote");
        product.setStyle("-fx-font-size: 14pt;");
        product.setMinWidth(500);
        product.setCellValueFactory(new PropertyValueFactory<Osa, String>("osaNimi"));

        TableColumn arriveDate = new TableColumn("Hinta (€)");
        arriveDate.setStyle("-fx-font-size: 14pt;");
        arriveDate.setMinWidth(200);
        arriveDate.setCellValueFactory(new PropertyValueFactory<Osa, Double>("osaHinta"));

        TableColumn amount = new TableColumn("Määrä");
        amount.setStyle("-fx-font-size: 14pt;");
        amount.setMinWidth(200);
        amount.setCellValueFactory(new PropertyValueFactory<Osa, Integer>("varastoMaara"));

        TableColumn additionalInfo = new TableColumn("HUOM");
        additionalInfo.setStyle("-fx-font-size: 14pt;");
        additionalInfo.setMinWidth(500);
        additionalInfo.setCellValueFactory(new PropertyValueFactory<Osa, String>("tyyppi"));

        tableVarasto.getColumns().addAll(brand, product, arriveDate, amount, additionalInfo);
        tableVarasto.setPrefHeight(700);
        tableVarasto.setPrefWidth(1600);

        final VBox vboxVarasto = new VBox();
        vboxVarasto.setSpacing(5);
        vboxVarasto.setPadding(new Insets(0, 0, 0, 20));
        vboxVarasto.getChildren().addAll(tableVarasto);

        // gridin paikka
        grid2.add(vboxVarasto, 1, 0, 7, 7);

        Button btnAddProduct = new Button();
        btnAddProduct.setText("Lisää Tuote");
        btnAddProduct.setPrefSize(200, 100);

        Button btnDeleteProduct = new Button();
        btnDeleteProduct.setText("Poista Tuote");
        btnDeleteProduct.setPrefSize(200, 100);

        Button btnAlterProduct = new Button();
        btnAlterProduct.setText("Lisää Tuote");
        btnAlterProduct.setPrefSize(200, 100);
        //grid2.add(btnAddProduct, 2, 7);

        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(30);
        buttonsBox.setPadding(new Insets(20, 20, 20, 20));

        buttonsBox.getChildren().addAll(btnAddProduct, btnDeleteProduct, btnAlterProduct);
        grid2.add(buttonsBox, 7, 8, 7, 10);

        this.setContent(grid2);

    }
}
