/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
     * Rakentaa käyttöliittymän Myynti sivun
     * Sivulla luodaan asiakkaan tilauksia.
     * 1. Valitse paketti/osa
     * 2. Valitse kappalemäärä
     * 3. Paina Lisää painiketta, Tilaukset listaan syntyy rivi
     * 4. Paina Lähetä painiketta, Tilaukset listan riveistä luodaan uusi tilaus tietokantaan
 */
public class Tab1 extends Tab {

    private static Tab1 INSTANCE = null;
    Controller controller = Controller.getInstance();
    public ComboBox<Integer> orderAmount;
    private final TableView productsTable = new TableView();
    public TextField UnitPriceTxt;
    View gui;

    // yleiset
    Scene scene;
    TabPane tabPane = new TabPane();
    ObservableList<Product> data;
    List<Product> tilausrivit;
    List<Paketti> pakettiLista;
    ObservableList<Paketti> pakettiData;
    List<Tilaus> tilausLista;
    ObservableList<Tilaus> tilausData;


    // ekasivu
    private final Text lblSales = new Text();
    final Tab tab1 = new Tab();
    private final GridPane grid1 = new GridPane();
    private final TableView tableTemp = new TableView();

    private final TextField searchField = new TextField();
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
    private final TextField addressTxt = new TextField();
    private final TextField billingTxt = new TextField();
    private final TextField otherTxt = new TextField();
    private final Button btnAddproduct = new Button();
    private final Button btnSend = new Button();

    private Tab1() {
        createTab1();
        haePaketit();
    }

    public static Tab1 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Tab1();
        }
        return INSTANCE;
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
        
        
        
        
        
        TableColumn productCol1 = new TableColumn("Tuote");
        productCol1.setStyle("-fx-font-size: 14pt;");
        productCol1.setMinWidth(200);
        productCol1.setCellValueFactory(
                new PropertyValueFactory<Paketti, String>("paketinNimi"));

        TableColumn amountCol1 = new TableColumn("ID");
        amountCol1.setStyle("-fx-font-size: 14pt;");
        amountCol1.setMinWidth(200);
        amountCol1.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("Id"));

        TableColumn priceCol1 = new TableColumn("Hinta(kpl)");
        priceCol1.setStyle("-fx-font-size: 14pt;");
        priceCol1.setMinWidth(200);
        priceCol1.setCellValueFactory(
                new PropertyValueFactory<Product, Double>("paketinHinta"));

        productsTable.getColumns().addAll(productCol1, amountCol1, priceCol1);
        productsTable.setPrefHeight(250);

        //Taulukon Vbox
        final VBox vbox1 = new VBox();
        vbox1.setSpacing(5);
        vbox1.setPadding(new Insets(10, 0, 0, 10));
        vbox1.getChildren().addAll(productsTable);
        
        
        
        
        // ComboboXXX
        //productsdrop = new ComboBox();
        //controller.getAllComputerNames(productsdrop);
        //productsdrop.setOnAction(e -> {
        //    getPrice(UnitPriceTxt);
        //});

        orderAmount = new ComboBox();
        orderAmount.getItems().addAll(
                1,
                2,
                3,
                4,
                5
        );
        orderAmount.getSelectionModel().selectFirst();
    //    getPrice(UnitPriceTxt);

        orderAmount.setOnAction(e -> {
      //      getPrice(UnitPriceTxt);
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
                tilausrivit.add(new Product(getValittuPaketti(), getOrderAmount()));
                //System.out.println(new Product(tilausrivi,getOrderAmount(),getValitunPaketinIndex()));
                data = FXCollections.observableArrayList(tilausrivit);
                tableTemp.setItems(data);
                for (int i = 0; i < tilausrivit.size(); i++) {
                    tilausrivit.forEach((prod) -> {
                        PriceTxt.setText("" + prod.getPrice());

                        /*  for(int i = 0; i < tilausrivit.size(); i++){
                PriceTxt.setText(""+(tilausrivit.get(i).getPrice()*tilausrivit.get(i).getAmount())*tilausrivit.size());
                }*/
                    });
                }
            }

        });
        btnSend.setOnAction(e -> {
            controller.createOrder();
                   System.out.println(getTilaukset());
            //Product taulun tyhjennys ja ilmoitus että homma onnistui
        });
        
       
       

        // LISÄYKSET GRIDII
        grid1.add(lblSales, 2, 1, 4, 2);
        grid1.add(lblOrder, 2, 3);
        grid1.add(lblProduct, 2, 4);
        grid1.add(searchField, 3, 4);
        grid1.add(productsTable, 2, 5, 4, 1);
        grid1.add(lblOrderAmount, 2, 6);
        grid1.add(orderAmount, 3, 6);
        grid1.add(lblUnitPrice, 2, 7);
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
        grid1.add(btnSend, 12, 10);

        this.setContent(grid1);

        localizationSetText();
        
        
        
       /* 
        FilteredList<Paketti> filteredData = new FilteredList<>(pakettiData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Paketti -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Paketti.getPaketinNimi()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                } else if (String.valueOf(Paketti.getPaketinNimi()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } 

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Paketti> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(productsTable.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        productsTable.setItems(sortedData);
    */
    }
    
        

    public int getOrderAmount() {
        return orderAmount.getSelectionModel().getSelectedItem();
    }

    /**
     * Palauttaa pudotusvalikosta valitun paketin
     *
     * @return
     */
    public Paketti getValittuPaketti() {
        return (Paketti) productsTable.getSelectionModel().getSelectedItem();
    }

    public int getValitunPaketinIndex() {
        return productsTable.getSelectionModel().getSelectedIndex();
    }

    /**
     * Palauttaa taulukosta valitut paketit ja osat
     *
     * @return
     */
    public ArrayList<Tilaus_rivi> getTilaukset() {
        ArrayList<Tilaus_rivi> prodTilaukset = new ArrayList<>();

        //Loop Product table
        tilausrivit.forEach((prod) -> {
            prodTilaukset.add(prod.getTilaus_rivi());
        });

        return prodTilaukset;
    }
    
     public ArrayList<Asiakas> getCustomer() {
        ArrayList asiakasrivit = new ArrayList<Asiakas>();
        asiakasrivit.add(new Asiakas(companyTxt.getText(), addressTxt.getText(), billingTxt.getText()));
        return asiakasrivit;
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
            public void haePaketit() {
            pakettiLista = controller.getAllComputerNames();
            pakettiData = FXCollections.observableArrayList(pakettiLista);
            productsTable.setItems(pakettiData);
                System.out.println(pakettiData);
        }
            

}
