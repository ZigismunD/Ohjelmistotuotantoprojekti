/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.Date;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Localization;

public class View extends Application {

    //private int tulos;
    private Controller controller = Controller.getInstance();
    //private ComboBox<Integer> orderAmount;
    //private ComboBox<Paketti> productsdrop;
    //private TextField UnitPriceTxt;

    // yleiset
    Scene scene;
    TabPane tabPane;
    //ObservableList<Product> data;
    //List<Osa> osaLista;
    //ObservableList<Osa> osaData;
    //List<Tilaus> tilausLista;
    //ObservableList<Tilaus> tilausData;

    // ekasivu
    Tab1 salesTab;
    Tab tab1;

    //tokasivu
    private Tab tab2;

    // kolmassivu
    private Tab tab3;

    //Taloustietosivu
    private GridPane grid4;
    private Tab tab4;

    //Lokalisointi
    Localization localization = Localization.getInstance();

    //
    Text textWelcome = new Text();
    Text textTime = new Text();
    public Text textField1 = new Text();
    public Text textField2 = new Text();
    Label lblLanguageFI = new Label();
    Label lblLanguageRUS = new Label();
    Label lblLanguageEN = new Label();
    Label lblLogOut = new Label();
    HBox hEmpty = new HBox();
    GridPane gridPane = new GridPane();

    public View() {
    }

    public View(String username) {
        textField1.setText(username);
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
            tab1 = Tab1.getInstance();
            tab2 = new Tab2();
            tab3 = new Tab3();
            tab4 = new Tab4();

            //Tabit tabpanee , tää ehkä pois 
            tabPane.getTabs().add(tab1);
            tabPane.getTabs().add(tab2);
            tabPane.getTabs().add(tab3);
            tabPane.getTabs().add(tab4);

            //Creating Text Filed for Tervetuloa        
            //Creating Text Filed for Kirjautumisaika
            Date date = new Date();

            textField2.setText(date.toLocaleString());

            //Creating Buttons 
            Image image = new Image("Finland-icon.png");

            lblLanguageFI.setGraphic(new ImageView(image));
            lblLanguageFI.setOnMouseClicked(e -> {
                localization.changeLocale("FI");
                localizationSetText();
                Tab1.getInstance().localizationSetText();

            });

            Image image2 = new Image("Russia-icon.png");

            lblLanguageRUS.setGraphic(new ImageView(image2));
            lblLanguageRUS.setOnMouseClicked(e -> {
                localization.changeLocale("RUS");
                localizationSetText();
                Tab1.getInstance().localizationSetText();
            });

            Image image3 = new Image("United-Kingdom-icon.png");

            lblLanguageEN.setGraphic(new ImageView(image3));
            lblLanguageEN.setOnMouseClicked(e -> {
                localization.changeLocale("US");
                localizationSetText();
                Tab1.getInstance().localizationSetText();

            });
            Image image4 = new Image("logout-icon.png");

            lblLogOut.setGraphic(new ImageView(image4));
            lblLogOut.setOnMouseClicked(e -> {
                controller.logOut(primaryStage);
            });

            //Setting size for the pane  
            gridPane.setMinSize(1900, 80);
            //Setting the vertical and horizontal gaps between the columns 
            gridPane.setVgap(5);
            gridPane.setHgap(10);
            //Setting the padding  
            gridPane.setPadding(new Insets(10, 10, 10, 10));

            hEmpty.setMinSize(1400, 30);

            //Arranging all the nodes in the grid 
            gridPane.add(textWelcome, 0, 0);
            gridPane.add(textField1, 1, 0);
            gridPane.add(textTime, 0, 1);
            gridPane.add(textField2, 1, 1);
            gridPane.add(hEmpty, 2, 0);
            gridPane.add(lblLanguageFI, 3, 0,1,2);
            gridPane.add(lblLanguageRUS, 4, 0,1,2);
            gridPane.add(lblLanguageEN, 5, 0,1,2);
            gridPane.add(lblLogOut, 6, 0,1,2);
            gridPane.setId("ylapalkki");

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
    }

    ;
    
    public void localizationSetText() {
        //Aseta tekstikenttien teksti uudelleen

        //Yleiset
        tab1.setText(localization.getBundle().getString("tab_sales"));
        tab2.setText(localization.getBundle().getString("tab_warehouse"));
        tab3.setText(localization.getBundle().getString("tab_orders"));
        tab4.setText(localization.getBundle().getString("tab_finance"));

        textWelcome.setText(localization.getBundle().getString("welcome_text"));
        textTime.setText(localization.getBundle().getString("time_text"));

        //Myyntisivu
//        lblSales.setText(localization.getBundle().getString("lbl_page_header"));  // ("MYYNTISIVU");
//        lblOrder.setText(localization.getBundle().getString("lbl_page_order"));  // ("TILAUS");
//        lblProduct.setText(localization.getBundle().getString("lbl_product"));  //  ("TUOTE:");
//        lblOrderAmount.setText(localization.getBundle().getString("lbl_product_quantity"));  //  ("MÄÄRÄ:");
//        lblUnitPrice.setText(localization.getBundle().getString("lbl_product_unit_price"));  //  ("YKSIKKÖHINTA:");
//        lblPrice.setText(localization.getBundle().getString("lbl_order_total_price"));  //  ("HINTA YHTEENSÄ:");
//        btnAddproduct.setText(localization.getBundle().getString("btn_add_product"));  // = ("Lisää");
//        btnSend.setText(localization.getBundle().getString("btn_send_order"));  // = ("Luo tilaus");
//        lblAddproduct.setText(localization.getBundle().getString("lbl_product_add"));  // = ("LISÄÄ TUOTE:");
//        lblCompany.setText(localization.getBundle().getString("lbl_customer_company"));  //("Yritys:");
//        lblCustomer.setText(localization.getBundle().getString("lbl_customer_contact_name"));  // = ("Yhteyshenkilö:");
//        lblAddress.setText(localization.getBundle().getString("lbl_customer_address"));  // = ("Postiosoite:");
//        lblBilling.setText(localization.getBundle().getString("lbl_customer_billing_address"));  // = ("Laskutusosoite:");
//        lblOther.setText(localization.getBundle().getString("lbl_customer_extra"));  // = ("Erityishuomiot:");
//        otsikko.setText(localization.getBundle().getString("lbl_order"));  // = ("TILAUKSESI:");
//        //tbl_col_order_name
        //tbl_col_order_quantity
        //tbl_col_order_unit_price
//        
//        //Varastosivu
//        btnProcessors.setText(localization.getBundle().getString("btn_processor"));  // = .setText("Prosessorit");
//        btnMotherboard.setText(localization.getBundle().getString("btn_motherboard"));  // = .setText("Emolevyt");
//        btnGraphics.setText(localization.getBundle().getString("btn_graphics_card"));  // = .setText("Näytönohjaimet");
//        btnRam.setText(localization.getBundle().getString("btn_ram"));  // = .setText("RAM");
//        btnPower.setText(localization.getBundle().getString("btn_power_source"));  // = .setText("Virtalähteet");
//        btnSsd.setText(localization.getBundle().getString("btn_ssd"));  // = .setText("SSD");
//        btnHdd.setText(localization.getBundle().getString("btn_hhd"));  // = .setText("HHD");
//        btnCase.setText(localization.getBundle().getString("btn_casing"));  // = .setText("Kotelo");
//        btnAddProduct.setText(localization.getBundle().getString("btn_create_product"));  // = .setText("Lisää Tuote");
//        btnDeleteProduct.setText(localization.getBundle().getString("btn_delete_product"));  // = .setText("Poista Tuote");
//        //tbl_col_type
//        //tbl_col_name
//        //tbl_col_unit_price
//        //tbl_col_quantity
//        //tbl_extra
//        
//        //Tilaukset
//        btnOrders.setText(localization.getBundle().getString("btn_orders"));  // = .setText("Tilaukset");
//        btnPurchases.setText(localization.getBundle().getString("btn_purchases"));  // = .setText("Ostot");
//        btnAllEvents.setText(localization.getBundle().getString("btn_all_transactions"));  // = .setText("Kaikki Tapahtumat");
//        btnAlterOrder.setText(localization.getBundle().getString("btn_add_product"));  // = .setText("Lisää Tuote");
//        btnRemoveOrder.setText(localization.getBundle().getString("btn_delete_product"));  // = .setText("Lisää Tuote");
//        
//        //Myynti
//        btnSales.setText(localization.getBundle().getString("btn_sales"));  // = .setText("Myynti");
//        btnSalesPurchases.setText(localization.getBundle().getString("btn_sales_purchases"));  // = .setText("Osto");
//        btnSummary.setText(localization.getBundle().getString("btn_summary"));  // = .setText("Yhteenveto ja budjetti");
//        
        //lineChart.setTitle("Myyntitiedot");
        //series.setName("Myynnit");
        //series.setName("Ostot");
        //lineChart.setTitle("Ostotiedot");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
