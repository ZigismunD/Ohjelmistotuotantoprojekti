/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Paketti;
import model.TietokonekauppaDAO;
import view.View;

/**
 *
 * @author zigis
 */
public class Controller {
    View gui;
    TietokonekauppaDAO dao;
    
    public Controller(View gui) {
        this.gui = gui;
        this.dao = new TietokonekauppaDAO();
    }
    
    public void getAllComputerNames(ComboBox box) {
        for (Paketti paketti : dao.readPaketit()) {
            box.getItems().add(paketti);
        }
        box.getSelectionModel().selectFirst();
    }
    
    public void createComputer() {
        
    }
    
    public void createOrder() {
        
    }
    

    public void getPrice(TextField PriceTxt) {
        double hinta = dao.haePaketinHinta(gui.getValitunPaketinIndex() + 1);
        System.out.println(gui.getValitunPaketinIndex());
        
       PriceTxt.setText("" + hinta * gui.getOrderAmount());
    }
    
}
