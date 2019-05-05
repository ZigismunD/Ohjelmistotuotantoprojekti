/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
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
     
        //btnSalesPurchases.setOnAction((event) -> {
        //    showPurchasesChart();
        //});
        grid4.add(btnSalesPurchases, 0, 1);

        btnSummary.setPrefSize(200, 100);
        grid4.add(btnSummary, 0, 2);

        this.setContent(grid4);
        
        localizationSetText();
    }

    private void showSalesChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        final BarChart<String, Number> lineChart = new BarChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("Myyntitiedot");

        XYChart.Series series = new XYChart.Series();
        series.setName("2018");

        XYChart.Series series2019 = new XYChart.Series();
        series2019.setName("2019");

        double[] year2019 = controller.getSalesOfYear(2019);

        series.getData().add(new XYChart.Data("Jan", 23004));
        series.getData().add(new XYChart.Data("Feb", 14002));
        series.getData().add(new XYChart.Data("Mar", 15228));
        series.getData().add(new XYChart.Data("Apr", 24225));
        series.getData().add(new XYChart.Data("May", 34557));
        series.getData().add(new XYChart.Data("Jun", 36754));
        series.getData().add(new XYChart.Data("Jul", 22454));
        series.getData().add(new XYChart.Data("Aug", 45544));
        series.getData().add(new XYChart.Data("Sep", 43454));
        series.getData().add(new XYChart.Data("Oct", 17454));
        series.getData().add(new XYChart.Data("Nov", 29454));
        series.getData().add(new XYChart.Data("Dec", 25197));

        series2019.getData().add(new XYChart.Data("Jan", year2019[0]));
        series2019.getData().add(new XYChart.Data("Feb", year2019[1]));
        series2019.getData().add(new XYChart.Data("Mar", year2019[2]));
        series2019.getData().add(new XYChart.Data("Apr", year2019[3]));
        series2019.getData().add(new XYChart.Data("May", year2019[4]));
        series2019.getData().add(new XYChart.Data("Jun", year2019[5]));
        series2019.getData().add(new XYChart.Data("Jul", year2019[6]));
        series2019.getData().add(new XYChart.Data("Aug", year2019[7]));
        series2019.getData().add(new XYChart.Data("Sep", year2019[8]));
        series2019.getData().add(new XYChart.Data("Oct", year2019[9]));
        series2019.getData().add(new XYChart.Data("Nov", year2019[10]));
        series2019.getData().add(new XYChart.Data("Dec", year2019[11]));

        lineChart.setPrefHeight(700);
        lineChart.setPrefWidth(1600);
        lineChart.getData().addAll(series, series2019);
        lineChart.setPadding(new Insets(20, 20, 20, 20));
        grid4.add(lineChart, 1, 1, 7, 7);
        setContent(grid4);
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
}
