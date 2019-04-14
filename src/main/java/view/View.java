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
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.event.EventHandler;
import model.Localization;
import model.Osa;
import model.Paketti;
import model.Product;
import model.TietokonekauppaDAO;
import model.Tilaus;
import model.Tilaus_rivi;

public class View extends Application {
    private int tulos;
    private Controller controller;
    private ComboBox<Integer> orderAmount;
    private ComboBox<Paketti> productsdrop;
    private TextField UnitPriceTxt;
    
    // yleiset
    Scene scene;
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
    //tokasivu
    private final GridPane grid2 = new GridPane();
    private final Tab tab2 = new Tab();
    private final TableView tableVarasto = new TableView();
    private final Button btnMotherboard = new Button();
    private final Button btnProcessors = new Button();
    private final Button btnGraphics = new Button();
    private final Button btnRam = new Button();
    private final Button btnPower = new Button();
    private final Button btnSsd = new Button();
    private final Button btnHdd = new Button();
    private final Button btnCase = new Button();
    private final Button btnAddProduct = new Button();
    private final Button btnDeleteProduct = new Button();
    private final Button btnAlterProduct = new Button();
        
    // kolmassivu
    private final GridPane grid3 = new GridPane();
    private final Tab tab3 = new Tab();
    private final TableView tableOrders = new TableView();
    private final Button btnOrders = new Button();
    private final Button btnPurchases = new Button();
    private final Button btnAllEvents = new Button();
    private final Button btnAlterOrder = new Button();
    private final Button btnRemoveOrder = new Button();
    
    //Taloustietosivu
    private final GridPane grid4 = new GridPane();
    private final Tab tab4 = new Tab();
    private final Button btnSales = new Button();
    private final Button btnSalesPurchases = new Button();
    private final Button btnSummary = new Button();
    
    //nappuloita
    private final Button btnAddproduct = new Button();
    private final Button btnSend = new Button();
    
    //Lokalisointi
    Localization localization = Localization.getInstance();
    
    public View() {
    }
    
    public View(Controller tmpController) {
        controller = tmpController;
    }
    
    /**
     * Luo käyttöliittymä näkymä.<br>
     * Käyttöliittymässä on sivut:<br>
     * Varasto: Pakettien ja Osien käsittely<br>
     * Myynti: Tilauksien käsittely<br>
     * Taloustiedot: Tilauksien ja myyntien tarkastelu<br>
     * 
     * @param primaryStage 
     */
    public void start(Stage primaryStage) {
        //Controller luodaan jo kirjautumisessa
        //controller =  new Controller(this);
        
        // Käyttöliittymän rakentaminen
        try {
            tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

            BorderPane borderPane = new BorderPane();

            // tabit
            createTab1();
            createTab2();
            createTab3();
            createTab4();
            
            //Tabit tabpanee , tää ehkä pois 
            tabPane.getTabs().add(tab1);
            tabPane.getTabs().add(tab2);
            tabPane.getTabs().add(tab3);
            tabPane.getTabs().add(tab4);
            
            // KIRJAUTUMISTA
            /*
            if (checkUser.equals(user1) && checkPw.equals(pw1)){
                tabPane.getTabs().add(tab1);
            }           
            else if(role.equals("varastomies")){
                tabPane.getTabs().add(tab2);
            }
            else if (role.equals("staff")){
                tabPane.getTabs().add(tab3);
            }
            */
            
            scene = new Scene(tabPane, 1900, 1000);
            scene.getStylesheets().add(this.getClass().getResource("/styles/stylesheet.css").toExternalForm());

            borderPane.prefHeightProperty().bind(scene.heightProperty());
            borderPane.prefWidthProperty().bind(scene.widthProperty());
            
            //Lokalisointi
            localizationSetText();
            
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    
    /**
     * Rakentaa käyttöliittymän Myynti sivun
     * Sivulla luodaan asiakkaan tilauksia.
     * 1. Valitse paketti/osa
     * 2. Valitse kappalemäärä
     * 3. Paina Lisää painiketta, Tilaukset listaan syntyy rivi
     * 4. Paina Lähetä painiketta, Tilaukset listan riveistä luodaan uusi tilaus tietokantaan
     */
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
        controller.getAllComputerNames(productsdrop);
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
        controller.getPrice(UnitPriceTxt);
        
        orderAmount.setOnAction(e-> {
            controller.getPrice(UnitPriceTxt);
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
                tilausrivit.add(new Product(getValittuPaketti() , getOrderAmount()));
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
    }

    /**
     * Rakentaa käyttöliittymän Varasto sivun
     * Sivulla tarkastellaan varaston paketteja ja osia.
     * Painiketta painamalla voi näyttää tietokannasta paketit tai halutun tyyppisen osan
     */
    private void createTab2() {
        grid2.setId("Varasto");
        grid2.setHgap(0); // Horizontal gap
        grid2.setVgap(0); // Vertical gap
        //grid2.setStyle("-fx-background-image: url('https://effiasoft.com/wp-content/uploads/app-background.png')");
        
        // Nappula, josta saa prosessorit näkyviin
        btnProcessors.setPrefSize(200, 100);
        btnProcessors.setOnAction(e-> {
            osaLista = controller.getOsat("Prosessori");
            osaData = FXCollections.observableArrayList(osaLista);
            tableVarasto.setItems(osaData);
        });
        grid2.add(btnProcessors, 0, 0);

        // Nappula, josta saa emolevyt näkyviin
        btnMotherboard.setPrefSize(200, 100);
        grid2.add(btnMotherboard, 0, 1);
        btnMotherboard.setOnAction(e-> {
            osaLista = controller.getOsat("Emolevy");
            osaData = FXCollections.observableArrayList(osaLista);
            tableVarasto.setItems(osaData);
        });

        // Nappula, josta saa näytönohjaimet näkyviin
        btnGraphics.setPrefSize(200, 100);
        grid2.add(btnGraphics, 0, 2);

        // Nappula, josta saa muistit näkyviin
        btnRam.setPrefSize(200, 100);
        grid2.add(btnRam, 0, 3);

        // Nappula, josta saa virtalähteet näkyviin
        btnPower.setPrefSize(200, 100);
        grid2.add(btnPower, 0, 4);

        // Nappula, josta saa SSD:t näkyviin
        btnSsd.setPrefSize(200, 100);
        grid2.add(btnSsd, 0, 5);

        // Nappula, josta saa HDD:t näkyviin
        btnHdd.setPrefSize(200, 100);
        grid2.add(btnHdd, 0, 6);

        // Nappula, josta saa HDD:t näkyviin
        btnCase.setPrefSize(200, 100);
        grid2.add(btnCase, 0, 7);
        
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

        btnAddProduct.setPrefSize(200, 100);
        btnDeleteProduct.setPrefSize(200, 100);
        btnAlterProduct.setPrefSize(200, 100);
        
        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(30);
        buttonsBox.setPadding(new Insets(20, 20, 20, 20));

        buttonsBox.getChildren().addAll(btnAddProduct, btnDeleteProduct, btnAlterProduct);
        
        grid2.add(vboxVarasto, 1, 0, 7, 7);
        grid2.add(buttonsBox, 7, 8, 7, 10);

        tab2.setContent(grid2);

    }
    
    /**
     * Rakentaa käyttöliittymän Tilaukset sivun
     * Sivulla tarkastellaan luotuja tilauksia ja niiden tilaus rivejä.
     * Tilaukset näkyvät listassa. Listaa painamalla kaikki tilauksen tilaus rivit näytetään toisessa listassa.
     * Luotuja tilauksia ja niiden tilaus rivejä voi muuttaa tai poistaa.
     */
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

        TableColumn orderDate = new TableColumn("Tilauspvm");
        orderDate.setStyle("-fx-font-size: 14pt;");
        orderDate.setMinWidth(200);
        orderDate.setCellValueFactory(new PropertyValueFactory<Tilaus, Date>("tilausPvm"));
        
        TableColumn amount = new TableColumn("Summa (€)");
        amount.setStyle("-fx-font-size: 14pt;");
        amount.setMinWidth(200);

        TableColumn additionalInfo = new TableColumn("HUOM");
        additionalInfo.setStyle("-fx-font-size: 14pt;");
        additionalInfo.setMinWidth(500);

        tableOrders.getColumns().addAll(brand, client, orderDate, amount, additionalInfo);
        tableOrders.setPrefHeight(700);
        tableOrders.setPrefWidth(1600);

        final VBox vboxOrders = new VBox();
        vboxOrders.setSpacing(5);
        vboxOrders.setPadding(new Insets(0, 0, 0, 20));
        vboxOrders.getChildren().addAll(tableOrders);

        grid3.add(vboxOrders, 1, 0, 7, 7);

        btnAlterOrder.setId("alterOrder");
        btnAlterOrder.setPrefSize(200, 100);

        btnRemoveOrder.setPrefSize(200, 100);
        
        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(30);
        buttonsBox.setPadding(new Insets(20, 20, 20, 20));

        buttonsBox.getChildren().addAll(btnAlterOrder, btnRemoveOrder);
        grid3.add(buttonsBox, 7, 8, 7, 10);
        
        tab3.setContent(grid3);

    }
    
    /**
     * Rakentaa käyttöliittymän Taloustiedot sivun
     * Sivulla tarkastellaan myyntien ja tilauksien tietoja.
     */
    private void createTab4() {
        grid4.setHgap(20); // Horizontal gap
        grid4.setVgap(0); // Vertical gap

        btnSales.setPrefSize(200, 100);
        btnSales.setOnAction((event) -> {
            showSalesChart();
        });
        grid4.add(btnSales, 0, 0);
        
        btnSalesPurchases.setPrefSize(200, 100);
     
        btnSalesPurchases.setOnAction((event) -> {
            showPurchasesChart();
        });
        grid4.add(btnSalesPurchases, 0, 1);

        btnSummary.setPrefSize(200, 100);
        grid4.add(btnSummary, 0, 2);

        tab4.setContent(grid4);
    }
    
    private void showSalesChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("Myyntitiedot");

        XYChart.Series series = new XYChart.Series();
        series.setName("Myynnit");

        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jul", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));

        lineChart.setPrefHeight(700);
        lineChart.setPrefWidth(1600);
        lineChart.getData().add(series);
        lineChart.setPadding(new Insets(20, 20, 20, 20));
        grid4.add(lineChart, 1, 1, 7, 7);
        tab4.setContent(grid4);
    }
    
    private void showPurchasesChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("Ostotiedot");

        XYChart.Series series = new XYChart.Series();
        series.setName("Ostot");

        series.getData().add(new XYChart.Data("Jan", 2));
        series.getData().add(new XYChart.Data("Feb", 3));
        series.getData().add(new XYChart.Data("Mar", 4));
        series.getData().add(new XYChart.Data("Apr", 5));
        series.getData().add(new XYChart.Data("May", 6));
        series.getData().add(new XYChart.Data("Jun", 7));
        series.getData().add(new XYChart.Data("Jul", 8));
        series.getData().add(new XYChart.Data("Aug", 9));
        series.getData().add(new XYChart.Data("Sep", 10));
        series.getData().add(new XYChart.Data("Oct", 11));
        series.getData().add(new XYChart.Data("Nov", 12));
        series.getData().add(new XYChart.Data("Dec", 13));

        lineChart.setPrefHeight(700);
        lineChart.setPrefWidth(1600);
        lineChart.getData().add(series);
        lineChart.setPadding(new Insets(20, 20, 20, 20));
        grid4.add(lineChart, 1, 1, 7, 7);
        tab4.setContent(grid4);
    }
    
    public void init() {
        tulos = 1;
    }
    
    public void nollaaTulos() {
        tulos = 0;
    }
    
    public int getTulos() {
        return this.tulos;
    }
    
    public int getOrderAmount() {
        return orderAmount.getSelectionModel().getSelectedItem();
    }
    
    /**
     * Palauttaa pudotusvalikosta valitun paketin
     * @return 
     */
    public Paketti getValittuPaketti() {
        return productsdrop.getSelectionModel().getSelectedItem();
    }
    
    public int getValitunPaketinIndex() {
        return productsdrop.getSelectionModel().getSelectedIndex();
    }
    
    /**
     * Palauttaa taulukosta valitut paketit ja osat
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
    
    public void localizationSetText() {
        //Aseta tekstikenttien teksti uudelleen
        
        //Yleiset
        tab1.setText(localization.getBundle().getString("tab_sales"));
        tab2.setText(localization.getBundle().getString("tab_warehouse"));
        tab3.setText(localization.getBundle().getString("tab_orders"));
        tab4.setText(localization.getBundle().getString("tab_finance"));
        
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
        
        //Varastosivu
        btnProcessors.setText(localization.getBundle().getString("btn_processor"));  // = .setText("Prosessorit");
        btnMotherboard.setText(localization.getBundle().getString("btn_motherboard"));  // = .setText("Emolevyt");
        btnGraphics.setText(localization.getBundle().getString("btn_graphics_card"));  // = .setText("Näytönohjaimet");
        btnRam.setText(localization.getBundle().getString("btn_ram"));  // = .setText("RAM");
        btnPower.setText(localization.getBundle().getString("btn_power_source"));  // = .setText("Virtalähteet");
        btnSsd.setText(localization.getBundle().getString("btn_ssd"));  // = .setText("SSD");
        btnHdd.setText(localization.getBundle().getString("btn_hhd"));  // = .setText("HHD");
        btnCase.setText(localization.getBundle().getString("btn_casing"));  // = .setText("Kotelo");
        btnAddProduct.setText(localization.getBundle().getString("btn_create_product"));  // = .setText("Lisää Tuote");
        btnDeleteProduct.setText(localization.getBundle().getString("btn_delete_product"));  // = .setText("Poista Tuote");
        //tbl_col_type
        //tbl_col_name
        //tbl_col_unit_price
        //tbl_col_quantity
        //tbl_extra
        
        //Tilaukset
        btnOrders.setText(localization.getBundle().getString("btn_orders"));  // = .setText("Tilaukset");
        btnPurchases.setText(localization.getBundle().getString("btn_purchases"));  // = .setText("Ostot");
        btnAllEvents.setText(localization.getBundle().getString("btn_all_transactions"));  // = .setText("Kaikki Tapahtumat");
        btnAlterOrder.setText(localization.getBundle().getString("btn_add_product"));  // = .setText("Lisää Tuote");
        btnRemoveOrder.setText(localization.getBundle().getString("btn_delete_product"));  // = .setText("Lisää Tuote");
        
        //Myynti
        btnSales.setText(localization.getBundle().getString("btn_sales"));  // = .setText("Myynti");
        btnSalesPurchases.setText(localization.getBundle().getString("btn_sales_purchases"));  // = .setText("Osto");
        btnSummary.setText(localization.getBundle().getString("btn_summary"));  // = .setText("Yhteenveto ja budjetti");
        
        //lineChart.setTitle("Myyntitiedot");
        //series.setName("Myynnit");
        
        //series.setName("Ostot");
        //lineChart.setTitle("Ostotiedot");
        
    }
    
}
