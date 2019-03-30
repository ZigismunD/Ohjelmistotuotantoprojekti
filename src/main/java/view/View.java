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
import model.Osa;
import model.Paketti;
import model.Product;
import model.TietokonekauppaDAO;
import model.Tilaus;
import model.Tilaus_rivi;

public class View extends Application {
    private int tulos;
    private TietokonekauppaDAO dao;
    private Controller controller;
    private ComboBox<Integer> orderAmount;
    private ComboBox<Paketti> productsdrop;
    private TextField UnitPriceTxt;
    
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
    private Tab tab1;
    private GridPane grid1;
    private TableView tableTemp;
    //tokasivu
    private GridPane grid2;
    private Tab tab2;
    private TableView tableVarasto;

    // kolmassivu
    private GridPane grid3;
    private Tab tab3;
    private TableView tableOrders;

    //Taloustietosivu
    private GridPane grid4;
    private Tab tab4;
    
    //nappuloita
    private Button btnAddproduct;
    private Button btnSend;
    
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
        controller =  new Controller(this);
        
        // Käyttöliittymän rakentaminen
        try {
            tabPane = new TabPane();
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
        tab1 = new Tab();
        tab1.setText("Myynti");

        grid1 = new GridPane();
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
        
        Text lblAddproduct = new Text("LISÄÄ TUOTE:");
        lblAddproduct.setFont(Font.font(null, 15));
        lblAddproduct.setFill(Color.BLACK);

        btnAddproduct = new Button();
        btnAddproduct.setText("Lisää");
        btnAddproduct.setPrefSize(100, 50);
        //btnAddproduct.setStyle("-fx-background-image: url('')");
        btnSend = new Button();
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
        tilausrivit = new ArrayList<Product>();
        tableTemp = new TableView();      
        
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
        tab2 = new Tab();
        tab2.setText("Varasto");

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

        tab2.setContent(grid2);

    }
    
    /**
     * Rakentaa käyttöliittymän Tilaukset sivun
     * Sivulla tarkastellaan luotuja tilauksia ja niiden tilaus rivejä.
     * Tilaukset näkyvät listassa. Listaa painamalla kaikki tilauksen tilaus rivit näytetään toisessa listassa.
     * Luotuja tilauksia ja niiden tilaus rivejä voi muuttaa tai poistaa.
     */
    private void createTab3() {
        tab3 = new Tab();
        tab3.setText("Tilaukset");

        grid3 = new GridPane();
        grid3.setHgap(0); // Horizontal gap
        grid3.setVgap(0); // Vertical gap
        //grid3.setStyle("-fx-background-image: url('https://effiasoft.com/wp-content/uploads/app-background.png')");

        Button btnOrders = new Button();
        btnOrders.setText("Tilaukset");
        btnOrders.setPrefSize(200, 100);
        btnOrders.setOnAction(e-> {
            tilausLista = controller.getTilaukset();
            tilausData = FXCollections.observableArrayList(tilausLista);
            tableOrders.setItems(tilausData);
        });        
        grid3.add(btnOrders, 0, 0);

        Button btnPurchases = new Button();
        btnPurchases.setText("Ostot");
        btnPurchases.setPrefSize(200, 100);
        grid3.add(btnPurchases, 0, 1);

        Button btnAllEvents = new Button();
        btnAllEvents.setText("Kaikki Tapahtumat");
        btnAllEvents.setPrefSize(200, 100);
        grid3.add(btnAllEvents, 0, 2);

        tableOrders = new TableView();
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

        Button btnAlterOrder = new Button();
        btnAlterOrder.setId("alterOrder");
        btnAlterOrder.setText("Lisää Tuote");
        btnAlterOrder.setPrefSize(200, 100);

        Button btnRemoveOrder = new Button();
        btnRemoveOrder.setText("Lisää Tuote");
        btnRemoveOrder.setPrefSize(200, 100);
        //grid2.add(btnAddProduct, 2, 7);

        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(30);
        buttonsBox.setPadding(new Insets(20, 20, 20, 20));

        buttonsBox.getChildren().addAll(btnAlterOrder, btnRemoveOrder);
        grid3.add(buttonsBox, 7, 8, 7, 10);

        //grid3.add(lblWarehouse,15,11);
        tab3.setContent(grid3);

    }
    
    /**
     * Rakentaa käyttöliittymän Taloustiedot sivun
     * Sivulla tarkastellaan myyntien ja tilauksien tietoja.
     */
    private void createTab4() {
        tab4 = new Tab();
        tab4.setText("Taloustiedot");

        grid4 = new GridPane();
        grid4.setHgap(20); // Horizontal gap
        grid4.setVgap(0); // Vertical gap

        Button btnSales = new Button();
        btnSales.setText("Myynti");
        btnSales.setPrefSize(200, 100);
        btnSales.setOnAction((event) -> {
            showSalesChart();
        });
        grid4.add(btnSales, 0, 0);
        
        Button btnPurchases = new Button();
        btnPurchases.setText("Osto");
        btnPurchases.setPrefSize(200, 100);
     
        btnPurchases.setOnAction((event) -> {
            showPurchasesChart();
        });
        grid4.add(btnPurchases, 0, 1);

        Button btnSummary = new Button();
        btnSummary.setText("Yhteenveto ja budjetti");
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
    
}
