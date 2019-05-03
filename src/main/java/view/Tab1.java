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
import model.Localization;
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
    Controller controller = Controller.getInstance();
    public ComboBox<Integer> orderAmount;
    public ComboBox<Paketti> productsdrop;
    public TextField UnitPriceTxt;
    View gui;
    
    // yleiset
    Scene scene;
    
    GridPane grid = new GridPane();
    
    TabPane tabPane = new TabPane();
    ObservableList<Product> data;
    List<Product> tilausrivit;
    List<Osa> osaLista;
    ObservableList<Osa> osaData;
    List<Tilaus> tilausLista;
    ObservableList<Tilaus> tilausData;
    
    
    
    
    

    // ekasivu
    private final Text lblSales = new Text();
    private final Tab tab1 = new Tab();
    private final GridPane grid1 = new GridPane();
    private final TableView tableTemp = new TableView();
    private final TextField PriceTxt = new TextField();
    private final Text lblOrder = new Text();
    private final Text lblProduct = new Text();
    private final Text lblOrderAmount = new Text();
    private final Text lblUnitPrice = new Text();
    private final Text lblPrice = new Text();
    private final Text lblAddproduct = new Text();
    private final Text lblCompany = new Text();
    private final Text lblCustomer = new Text();
    private final Text lblAddress = new Text();
    private final Text lblBilling = new Text();
    private final Text lblOther = new Text();
    private final Text otsikko = new Text();
    private final TextField companyTxt = new TextField();
    private final TextField customerTxt = new TextField();
    private final TextField addressTxt = new TextField();
    private final TextField billingTxt = new TextField();
    private final TextField otherTxt = new TextField();
    
    private final Button btnAddproduct = new Button();
    private final Button btnSend = new Button();
    
    public Tab1() {
        createTab1(); 
    }

    private void createTab1() {
        grid1.setHgap(30); // Horizontal gap
        grid1.setVgap(30); // Vertical gap

        // SIVUSTON KOMPONENTIT
        lblSales.setFont(Font.font(null, FontWeight.BOLD, 60));
        lblSales.setFill(Color.BLACK);

        lblOrder.setFont(Font.font(null, FontWeight.BOLD, 25));
        lblOrder.setFill(Color.BLACK);

        lblProduct.setFont(Font.font(null, 15));
        lblProduct.setFill(Color.BLACK);

        lblOrderAmount.setFont(Font.font(null, 15));
        lblOrderAmount.setFill(Color.BLACK);

        lblUnitPrice.setFont(Font.font(null, 15));
        lblUnitPrice.setFill(Color.BLACK);
        TextField UnitPriceTxt = new TextField();

        lblUnitPrice.setFont(Font.font(null, 15));
        lblUnitPrice.setFill(Color.BLACK);

        TextField PriceTxt = new TextField();

        // ComboboXXX
        productsdrop = new ComboBox();
        //controller.getAllComputerNames(productsdrop);
        productsdrop.setOnAction(e-> {
            controller.getPrice(UnitPriceTxt);
        });
   
        orderAmount = new ComboBox();
        orderAmount.getItems().addAll(
                1,
                2,
                3,
                4,
                5
        );
        orderAmount.getSelectionModel().selectFirst();
        //controller.getPrice(UnitPriceTxt);
        
        orderAmount.setOnAction(e-> {
            //controller.getPrice(UnitPriceTxt);
        });
        
        lblAddproduct.setFont(Font.font(null, 15));
        lblAddproduct.setFill(Color.BLACK);

        btnAddproduct.setPrefSize(100, 50);
        //btnAddproduct.setStyle("-fx-background-image: url('')");
        btnSend.setId("btnSend");
        btnSend.setPrefSize(250, 200);

        // Tilaus taulukko
        tilausrivit = new ArrayList<Product>();
        
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

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

        //Taulukon Vbox
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(otsikko, tableTemp);
        
         btnAddproduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            //    Tilaus_rivi tilausrivi = new Tilaus_rivi(getValittuPaketti(), getOrderAmount());
                tilausrivit.add(new Product(gui.getValittuPaketti(), gui.getOrderAmount()));
                //System.out.println(new Product(tilausrivi,getOrderAmount(),getValitunPaketinIndex()));
                data = FXCollections.observableArrayList(tilausrivit);
                tableTemp.setItems(data);
            }
        });   
        btnSend.setOnAction(e-> {
            controller.createOrder();
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

        tab1.setContent(grid1);
        //grid1.setStyle("-fx-background-image: url('https://effiasoft.com/wp-content/uploads/app-background.png')");

        grid1.add(vbox, 2, 10, 10, 1);
        grid1.add(lblPrice, 2, 11);
        grid1.add(PriceTxt, 3, 11);
        grid1.add(btnSend, 12,10 );
        
        this.setContent(grid1);
        
        localizationSetText();
    }
    
    public void localizationSetText() {
        Localization localization = Localization.getInstance();
        
        //Myyntisivu
        lblSales.setText(localization.getBundle().getString("lbl_page_header"));  // ("MYYNTISIVU");
        lblOrder.setText(localization.getBundle().getString("lbl_page_order"));  // ("TILAUS");
        lblProduct.setText(localization.getBundle().getString("lbl_product"));  //  ("TUOTE:");
        lblOrderAmount.setText(localization.getBundle().getString("lbl_product_quantity"));  //  ("MÄÄRÄ:");
        lblUnitPrice.setText(localization.getBundle().getString("lbl_product_unit_price"));  //  ("YKSIKKÖHINTA:");
        lblPrice.setText(localization.getBundle().getString("lbl_order_total_price"));  //  ("HINTA YHTEENSÄ:");
        btnAddproduct.setText(localization.getBundle().getString("btn_add_product"));  // = ("Lisää");
        btnSend.setText(localization.getBundle().getString("btn_send_order"));  // = ("Luo tilaus");
        lblAddproduct.setText(localization.getBundle().getString("lbl_product_add"));  // = ("LISÄÄ TUOTE:");
        lblCompany.setText(localization.getBundle().getString("lbl_customer_company"));  //("Yritys:");
        lblCustomer.setText(localization.getBundle().getString("lbl_customer_contact_name"));  // = ("Yhteyshenkilö:");
        lblAddress.setText(localization.getBundle().getString("lbl_customer_address"));  // = ("Postiosoite:");
        lblBilling.setText(localization.getBundle().getString("lbl_customer_billing_address"));  // = ("Laskutusosoite:");
        lblOther.setText(localization.getBundle().getString("lbl_customer_extra"));  // = ("Erityishuomiot:");
        otsikko.setText(localization.getBundle().getString("lbl_order"));  // = ("TILAUKSESI:");
        //tbl_col_order_name
        //tbl_col_order_quantity
        //tbl_col_order_unit_price
    }

}
