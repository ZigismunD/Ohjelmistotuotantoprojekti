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
        //controller =  new Controller(this);
        
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
    
}
