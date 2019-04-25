/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import model.Localization;
import model.Osa;
import model.Product;
import model.Tilaus;

/**
 *
 * @author RJulin
 */

/*
* Rakentaa käyttöliittymän Taloustiedot sivun
* Sivulla tarkastellaan myyntien ja tilauksien tietoja.
*/

public class Tab4 extends Tab {
    
    //Yleiset
    Controller controller = Controller.getInstance();
    Scene scene;
    TabPane tabPane;
    private final GridPane grid4 = new GridPane();
    
    private final Button btnSales = new Button();
    private final Button btnSalesPurchases = new Button();
    private final Button btnSummary = new Button();
    
    public Tab4(){
       createTab4();
    }
    
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

        this.setContent(grid4);
        
        localizationSetText();
    }
    
    public void localizationSetText() {
        Localization localization = Localization.getInstance();
        
        btnSales.setText(localization.getBundle().getString("btn_sales"));  // = .setText("Myynti");
        btnSalesPurchases.setText(localization.getBundle().getString("btn_sales_purchases"));  // = .setText("Osto");
        btnSummary.setText(localization.getBundle().getString("btn_summary"));  // = .setText("Yhteenveto ja budjetti");
        
        //lineChart.setTitle("Myyntitiedot");
        //series.setName("Myynnit");
        
        //series.setName("Ostot");
        //lineChart.setTitle("Ostotiedot");
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
        this.setContent(grid4);
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
        this.setContent(grid4);
    }
}
