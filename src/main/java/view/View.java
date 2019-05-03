/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.awt.Image;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Localization;
import model.Osa;
import model.Paketti;
import model.Product;
import model.Tilaus;
import model.Tilaus_rivi;

public class View extends Application {
    private int tulos;
    private Controller controller = Controller.getInstance();
    //private ComboBox<Integer> orderAmount;
    //private ComboBox<Paketti> productsdrop;
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
    private Tab1 salesTab;
    private Tab tab1;

    //tokasivu
    private Tab tab2;

    // kolmassivu
    private Tab tab3;

    //Taloustietosivu
    private GridPane grid4;
    private Tab tab4;
    
    //Lokalisointi
    Localization localization = Localization.getInstance();
    
    public View() {
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

        // Käyttöliittymän rakentaminen
        try {
            tabPane = new TabPane();
            tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

            BorderPane borderPane = new BorderPane();

            // tabit
            tab1 = new Tab1();
            tab2 = new Tab2();
            tab3 = new Tab3();
            tab4 = new Tab4();
            
            //Tabit tabpanee , tää ehkä pois 
            tabPane.getTabs().add(tab1);
            tabPane.getTabs().add(tab2);
            tabPane.getTabs().add(tab3);
            tabPane.getTabs().add(tab4);
            
            // buttons ja text for GridPane
            //creating label email 
            Text text1 = new Text("Tervetuloa: ");       

            //creating label password 
            Text text2 = new Text("Kirjautumisaika: "); 

            //Creating Text Filed for email        
            TextField textField1 = new TextField();       

            //Creating Text Filed for password        
            TextField textField2 = new TextField();  

            //Creating Buttons 
            Button button1 = new Button("Suomi"); 
            Button button2 = new Button("Englanti");  
            Button button3 = new Button("Venäjä"); 
            Button button4 = new Button("Kirjaudu ulos");
           // Vadim Jatka tästä 
//            button1.setPrefSize(50, 50);
//            //Image imageOk = new Image(getClass().getResourceAsStream("yes.png"));
//            ImageView img = new ImageView();
//            img.setPreserveRatio(true);
//            img.fitWidthProperty().bind(button1.widthProperty());
//            img.fitHeightProperty().bind(button2.heightProperty());
//            button1.setGraphic(img);
//            
//            
//            
//            button1.setGraphic(text2);
//            button2.setGraphic(text2);
//            button3.setGraphic(text2);
            
            
            GridPane gridPane = new GridPane();
            //Setting size for the pane  
            gridPane.setMinSize(1900, 100);
            //Setting the vertical and horizontal gaps between the columns 
            gridPane.setVgap(10); 
            gridPane.setHgap(10);
            //Setting the padding  
            gridPane.setPadding(new Insets(10, 10, 10, 10)); 
            
            HBox hEmpty = new HBox();
            hEmpty.setMinSize(700, 50);
            
            //Arranging all the nodes in the grid 
            gridPane.add(text1, 0, 0); 
            gridPane.add(textField1, 1, 0); 
            gridPane.add(text2, 0, 1);       
            gridPane.add(textField2, 1, 1);
            gridPane.add(hEmpty, 2, 0);
            gridPane.add(button1, 3, 0); 
            gridPane.add(button2, 4, 0); 
            gridPane.add(button3, 5, 0); 
            gridPane.add(button4, 6, 0);
            
            VBox vBox = new VBox();
            vBox.getChildren().add(gridPane);
            vBox.getChildren().add(tabPane);
            
            
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
            scene = new Scene(vBox, 1900, 1000);
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
    
    public int getOrderAmount() {
        return salesTab.orderAmount.getSelectionModel().getSelectedItem();
    }
    
    /**
     * Palauttaa pudotusvalikosta valitun paketin
     * @return 
     */
    public Paketti getValittuPaketti() {
        return salesTab.productsdrop.getSelectionModel().getSelectedItem();
    }
    
    public int getValitunPaketinIndex() {
        return salesTab.productsdrop.getSelectionModel().getSelectedIndex();
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
        /*
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
        */
    }
    public static void main(String[] args) {
        launch(args);
    }
}
