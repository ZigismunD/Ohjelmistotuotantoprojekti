/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.Asiakas;
import model.Localization;
import model.Osa;
import model.Paketti;
import model.Product;
import model.Tilaus;
import model.Tilaus_rivi;

/**
 *
 * @author RJulin
 */

  /*
     * Rakentaa käyttöliittymän Tilaukset sivun
     * Sivulla tarkastellaan luotuja tilauksia ja niiden tilaus rivejä.
     * Tilaukset näkyvät listassa. Listaa painamalla kaikki tilauksen tilaus rivit näytetään toisessa listassa.
     * Luotuja tilauksia ja niiden tilaus rivejä voi muuttaa tai poistaa.
     */

public class Tab3 extends Tab {
    
    //Yleiset
    Controller controller = Controller.getInstance();
    Scene scene;
    TabPane tabPane;
    private final GridPane grid3 = new GridPane();
    
    List<Tilaus> tilausLista = new ArrayList<Tilaus>();
    ObservableList<Tilaus> tilausData;
    
    ObservableList<Tilaus> rividata; 
    List<Tilaus> tilausriviLista  = new ArrayList<Tilaus>();
    
    private final TableView tableOrders = new TableView();
    private final TableView tableDetails = new TableView();
    private final Button btnOrders = new Button();
    private final Button btnPurchases = new Button();
    private final Button btnAllEvents = new Button();
    private final Button btnAlterOrder = new Button();
    private final Button btnRemoveOrder = new Button();
    
    public Tab3(){
        createTab3();
    }
    
    private void createTab3() {
        grid3.setHgap(0); // Horizontal gap
        grid3.setVgap(0); // Vertical gap
        //grid3.setStyle("-fx-background-image: url('https://effiasoft.com/wp-content/uploads/app-background.png')");

        btnOrders.setPrefSize(200, 100);
        btnOrders.setOnAction(e-> {
            tilausLista = controller.getTilaukset();
            tilausData = FXCollections.observableArrayList(tilausLista);
            tableOrders.setItems(tilausData);
        });        
        grid3.add(btnOrders, 0, 0);

        btnPurchases.setPrefSize(200, 100);
        grid3.add(btnPurchases, 0, 1);

        btnAllEvents.setPrefSize(200, 100);
        grid3.add(btnAllEvents, 0, 2);

        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        tableOrders.setEditable(true);

        TableColumn brand = new TableColumn("Tilaus ID");
        brand.setStyle("-fx-font-size: 14pt;");
        brand.setMinWidth(200);
        brand.setCellValueFactory(new PropertyValueFactory<Tilaus, Integer>("id"));

        TableColumn client = new TableColumn("Asiakas");
        client.setStyle("-fx-font-size: 14pt;");
        client.setMinWidth(500);
        client.setCellValueFactory(new PropertyValueFactory<Tilaus, Asiakas >("asiakas"));

        
        TableColumn orderDate = new TableColumn("Tilauspvm");
        orderDate.setStyle("-fx-font-size: 14pt;");
        orderDate.setMinWidth(200);
        orderDate.setCellValueFactory(new PropertyValueFactory<Tilaus, Date>("tilausPvm"));
        
        TableColumn amount = new TableColumn("Summa (€)");
        amount.setStyle("-fx-font-size: 14pt;");
        amount.setMinWidth(200);
        amount.setCellValueFactory(new PropertyValueFactory<Tilaus, Double>("yhteishinta"));


        TableColumn additionalInfo = new TableColumn("HUOM");
        additionalInfo.setStyle("-fx-font-size: 14pt;");
        additionalInfo.setMinWidth(500);

        tableOrders.getColumns().addAll(brand, client, orderDate, amount, additionalInfo);
        tableOrders.setPrefHeight(500);
        tableOrders.setPrefWidth(1600);

        final VBox vboxOrders = new VBox();
        vboxOrders.setSpacing(5);
        vboxOrders.setPadding(new Insets(0, 0, 0, 20));
        vboxOrders.getChildren().addAll(tableOrders);
        
        tableDetails.setEditable(true);

        TableColumn products = new TableColumn("Tilaus ID");
        products.setStyle("-fx-font-size: 14pt;");
        products.setMinWidth(200);
        products.setCellValueFactory(new PropertyValueFactory<Tilaus_rivi, Osa>("osa"));

        TableColumn productamount = new TableColumn("Asiakas");
        productamount.setStyle("-fx-font-size: 14pt;");
        productamount.setMinWidth(500);
       productamount.setCellValueFactory(new PropertyValueFactory<Tilaus_rivi, Paketti >("paketti"));

        TableColumn ordersum = new TableColumn("Tilauspvm");
        ordersum.setStyle("-fx-font-size: 14pt;");
        ordersum.setMinWidth(200);
        ordersum.setCellValueFactory(new PropertyValueFactory<Tilaus_rivi, Integer>("maara"));


        tableDetails.getColumns().addAll(products, productamount, ordersum);
        tableDetails.setPrefHeight(300);
        tableDetails.setPrefWidth(1600);

        final VBox vboxDetails = new VBox();
        vboxDetails.setSpacing(5);
        vboxDetails.setPadding(new Insets(0, 0, 0, 20));
        vboxDetails.getChildren().addAll(tableDetails);

        btnAlterOrder.setId("alterOrder");
        btnAlterOrder.setPrefSize(200, 100);
        
        btnRemoveOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (tableOrders.getSelectionModel().getSelectedItem() != null) {
                    Tilaus removeItem =(Tilaus) tableOrders.getSelectionModel().getSelectedItem();
                    tableOrders.getItems().remove(removeItem);
                    controller.objectDelete(removeItem);
                } else {
                }
            }
        });
        
        
        btnRemoveOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (tableOrders.getSelectionModel().getSelectedItem() != null) {
                    Tilaus removeItem =(Tilaus) tableOrders.getSelectionModel().getSelectedItem();
                    tableOrders.getItems().remove(removeItem);
                    controller.objectDelete(removeItem);
                } else {
                }
            }
        });
        
        
        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(30);
        buttonsBox.setPadding(new Insets(20, 20, 20, 20));

        buttonsBox.getChildren().addAll(btnAlterOrder, btnRemoveOrder);
        
        grid3.add(vboxOrders, 1, 0, 7, 7);
        grid3.add(vboxDetails,1, 10, 7, 2);
        grid3.add(buttonsBox, 7, 12, 7, 10);

        this.setContent(grid3);
        
        localizationSetText();
        
        btnAllEvents.setOnAction(e -> {;
        showRows();
        });
    }
    
    public void showRows(){
        Tilaus valittuTilaus =(Tilaus)tableOrders.getSelectionModel().getSelectedItem(); 
        tilausriviLista = controller.getOrderRows(valittuTilaus);
        //tilausriviLista.add(controller.getOrderRows(valittuTilaus));
        rividata = FXCollections.observableArrayList(tilausriviLista);
        tableDetails.setItems(rividata);
        System.out.println(rividata);
        System.out.println(valittuTilaus.getAsiakas());
    }
    
     
    public void localizationSetText() {
        Localization localization = Localization.getInstance();
        
        btnOrders.setText(localization.getBundle().getString("btn_orders"));  // = .setText("Tilaukset");
        btnPurchases.setText(localization.getBundle().getString("btn_purchases"));  // = .setText("Ostot");
        btnAllEvents.setText(localization.getBundle().getString("btn_all_transactions"));  // = .setText("Kaikki Tapahtumat");
        btnAlterOrder.setText(localization.getBundle().getString("btn_add_product"));  // = .setText("Lisää Tuote");
        btnRemoveOrder.setText(localization.getBundle().getString("btn_delete_product"));  // = .setText("Lisää Tuote");
    }
}
