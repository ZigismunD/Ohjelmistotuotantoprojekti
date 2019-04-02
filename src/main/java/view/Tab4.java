/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
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
    
    private GridPane grid4;
   // private Tab tab4;
    Scene scene;
    TabPane tabPane;
    ObservableList<Product> data;
    List<Product> tilausrivit;
    List<Osa> osaLista;
    ObservableList<Osa> osaData;
    List<Tilaus> tilausLista;
    ObservableList<Tilaus> tilausData;
    
    public Tab4(){
       createTab4();
    }
    
    private void createTab4() {
        //tab4 = new Tab();
        this.setText("Taloustiedot");

        grid4 = new GridPane();
        grid4.setHgap(20); // Horizontal gap
        grid4.setVgap(0); // Vertical gap

        Button btnSales = new Button();
        btnSales.setText("Myynti");
        btnSales.setPrefSize(200, 100);
     //   btnSales.setOnAction((event) -> {
     //       showSalesChart();
     //  });
        grid4.add(btnSales, 0, 0);
        
        Button btnPurchases = new Button();
        btnPurchases.setText("Osto");
        btnPurchases.setPrefSize(200, 100);
     
      //  btnPurchases.setOnAction((event) -> {
      //      showPurchasesChart();
      //  });
        grid4.add(btnPurchases, 0, 1);

        Button btnSummary = new Button();
        btnSummary.setText("Yhteenveto ja budjetti");
        btnSummary.setPrefSize(200, 100);
        grid4.add(btnSummary, 0, 2);

        

        this.setContent(grid4);
    }
    
}
