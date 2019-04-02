/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Osa;
import model.Paketti;
import model.Product;
import model.TietokonekauppaDAO;
import model.Tilaus;
import model.Tilaus_rivi;

/**
 *
 * @author RJulin
 */
    /*
     * Rakentaa käyttöliittymän Myynti sivun
     * Sivulla luodaan asiakkaan tilauksia.
     * 1. Valitse paketti/osa
     * 2. Valitse kappalemäärä
     * 3. Paina Lisää painiketta, Tilaukset listaan syntyy rivi
     * 4. Paina Lähetä painiketta, Tilaukset listan riveistä luodaan uusi tilaus tietokantaan
     */


public class Tab1 extends Tab {
   private int tulos;
    private TietokonekauppaDAO dao;
    public Controller controller;
    public ComboBox<Integer> orderAmount;
    public ComboBox<Paketti> productsdrop;
    public TextField UnitPriceTxt;
    
    // yleiset
    Scene scene;
    TabPane tabPane;
    ObservableList<Product> data;
    List<Product> tilausrivit;
    List<Osa> osaLista;
    ObservableList<Osa> osaData;
    List<Tilaus> tilausLista;
    ObservableList<Tilaus> tilausData;

    // ekasivu
   // private Tab tab1;
    public GridPane grid1;
    public TableView tableTemp;

    public Tab1(){
        createTab1(); 
    }

    private void createTab1() {
      //    controller =  new Controller(this);
      //  this = new Tab();
     //   tab1.setText("Myynti");

        
        this.setText("Myynti");
        GridPane grid1 = new GridPane();
        grid1.setHgap(30); // Horizontal gap
        grid1.setVgap(30); // Vertical gap

        // SIVUSTON KOMPONENTIT
        Text lblSales = new Text("MYYNTISIVU");
        lblSales.setFont(Font.font(null, FontWeight.BOLD, 60));
        lblSales.setFill(Color.BLACK);

        Text lblOrder = new Text("TILAUS");
        lblOrder.setFont(Font.font(null, FontWeight.BOLD, 25));
        lblOrder.setFill(Color.BLACK);

        Text lblProduct = new Text("TUOTE:");
        lblProduct.setFont(Font.font(null, 15));
        lblProduct.setFill(Color.BLACK);

        Text lblOrderAmount = new Text("MÄÄRÄ:");
        lblOrderAmount.setFont(Font.font(null, 15));
        lblOrderAmount.setFill(Color.BLACK);

        Text lblUnitPrice = new Text("YKSIKKÖHINTA:");
        lblUnitPrice.setFont(Font.font(null, 15));
        lblUnitPrice.setFill(Color.BLACK);
        TextField UnitPriceTxt = new TextField();

        Text lblPrice = new Text("HINTA YHTEENSÄ:");
        lblUnitPrice.setFont(Font.font(null, 15));
        lblUnitPrice.setFill(Color.BLACK);

        TextField PriceTxt = new TextField();

        // ComboboXXX
        ComboBox productsdrop = new ComboBox();
      //  controller.getAllComputerNames(productsdrop);
       // productsdrop.setOnAction(e-> {
       //     controller.getPrice(UnitPriceTxt);
  //      });
   
        ComboBox orderAmount = new ComboBox();
        orderAmount.getItems().addAll(
                1,
                2,
                3,
                4,
                5
        );
        orderAmount.getSelectionModel().selectFirst();
//        controller.getPrice(UnitPriceTxt);
        
        orderAmount.setOnAction(e-> {
  //      controller.getPrice(UnitPriceTxt);
        });
        
        Text lblAddproduct = new Text("LISÄÄ TUOTE:");
        lblAddproduct.setFont(Font.font(null, 15));
        lblAddproduct.setFill(Color.BLACK);

        Button btnAddproduct = new Button();
        btnAddproduct.setText("Lisää");
        btnAddproduct.setPrefSize(100, 50);
        //btnAddproduct.setStyle("-fx-background-image: url('')");
        Button btnSend = new Button();
        btnSend.setText("Luo tilaus");
        btnSend.setId("btnSend");
        btnSend.setPrefSize(250, 200);

        Text lblCompany = new Text("Yritys:");
        TextField companyTxt = new TextField();
        Text lblCustomer = new Text("Yhteyshenkilö:");
        TextField customerTxt = new TextField();
        Text lblAddress = new Text("Postiosoite:");
        TextField addressTxt = new TextField();
        Text lblBilling = new Text("Laskutusosoite:");
        TextField billingTxt = new TextField();
        Text lblOther = new Text("Erityishuomiot:");
        TextField otherTxt = new TextField();

        // Tilaus taulukko
        ArrayList tilausrivit = new ArrayList<Product>();
        TableView tableTemp = new TableView();      
        
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        Text otsikko = new Text("TILAUKSESI:");
        otsikko.setFont(Font.font(null, FontWeight.BOLD, 30));
        otsikko.setFill(Color.rgb(255, 255, 255));

        tableTemp.setEditable(true);

        TableColumn productCol = new TableColumn("Tuote");
        productCol.setStyle("-fx-font-size: 14pt;");
        productCol.setMinWidth(200);
        productCol.setCellValueFactory(
            new PropertyValueFactory<Product, String>("tuote1"));


        TableColumn amountCol = new TableColumn("Määrä");
        amountCol.setStyle("-fx-font-size: 14pt;");
        amountCol.setMinWidth(200);
        amountCol.setCellValueFactory(
            new PropertyValueFactory<Product, Integer>("amount"));

        TableColumn priceCol = new TableColumn("Hinta(kpl)");
        priceCol.setStyle("-fx-font-size: 14pt;");
        priceCol.setMinWidth(200);
        priceCol.setCellValueFactory(
            new PropertyValueFactory<Product, Double>("price"));

        tableTemp.getColumns().addAll(productCol, amountCol, priceCol);
        tableTemp.setPrefHeight(250);

        //Taulikon Vbox
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(otsikko, tableTemp);
        
         btnAddproduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            //    Tilaus_rivi tilausrivi = new Tilaus_rivi(getValittuPaketti(), getOrderAmount());
               // tilausrivit.add(new Product(getValittuPaketti() , getOrderAmount()));
                //System.out.println(new Product(tilausrivi,getOrderAmount(),getValitunPaketinIndex()));
               // data = FXCollections.observableArrayList(tilausrivit);
               // tableTemp.setItems(data);
            }
        });   
        btnSend.setOnAction(e-> {
          //  controller.createOrder();
            //Product taulun tyhjennys ja ilmoitus että homma onnistui
        });

        // LISÄYKSET GRIDII
        grid1.add(lblSales, 2, 1, 4, 2);
        grid1.add(lblOrder, 2, 3);
        grid1.add(lblProduct, 2, 4);
        grid1.add(productsdrop, 2, 5, 4, 1);
        grid1.add(lblOrderAmount, 2, 6);
        grid1.add(orderAmount, 3, 6);
        grid1.add(lblUnitPrice, 2, 7);
        grid1.add(UnitPriceTxt, 3, 7);
        grid1.add(lblAddproduct, 2, 8);
        grid1.add(btnAddproduct, 3, 8);

        grid1.add(lblCompany, 10, 2);
        grid1.add(companyTxt, 10, 3);
        grid1.add(lblAddress, 10, 4);
        grid1.add(addressTxt, 10, 5);
        grid1.add(lblBilling, 10, 6);
        grid1.add(billingTxt, 10, 7);
        grid1.add(lblOther, 10, 8);
        grid1.add(otherTxt, 10, 9);

        this.setContent(grid1);
        //grid1.setStyle("-fx-background-image: url('https://effiasoft.com/wp-content/uploads/app-background.png')");

        grid1.add(vbox, 2, 10, 10, 1);
        grid1.add(lblPrice, 2, 11);
        grid1.add(PriceTxt, 3, 11);
        grid1.add(btnSend, 12,10 );
    }

}
