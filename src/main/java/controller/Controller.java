/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Henkilosto;
import model.Osa;
import model.Paketti;
import model.TietokonekauppaDAO;
import model.Tilaus;
import model.Tilaus_rivi;
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
    
    public Controller() {
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
        //Luo Tilaus_rivi lista productista
        List<Tilaus_rivi> tilaukset = gui.getTilaukset();
        
        //Tarkista että listassa on ainakin yksi tilaus
        if (tilaukset.isEmpty() == true) {
            //Ilmoita viewille että tilausrivejä ei ole yhtään
            //gui.setMessagebox("Tilaus lista on tyhjä!");
        } else {
            dao.luoTilaus(tilaukset);
        }

    }
    
    public void createUser(Henkilosto henkilo) {
        dao.luoHenkilo(henkilo);
    }
    
    public void getPrice(TextField PriceTxt) {
        double hinta = dao.haePaketinHinta(gui.getValitunPaketinIndex() + 1);
        System.out.println(gui.getValitunPaketinIndex());
        
       PriceTxt.setText("" + hinta);
    }
    
    public ArrayList<Osa> getOsat(String tyyppi) {
        ArrayList<Osa> osat = new ArrayList<>();
        
        for (Osa osa : dao.getOsat(tyyppi)) {
            osat.add(new Osa(osa.getOsaNimi(), osa.getOsaHinta(), osa.getVarastoMaara(), osa.getTyyppi()));
        }
        return osat;
    }
    
    public ArrayList<Tilaus> getTilaukset() {
        ArrayList<Tilaus> tilaukset = new ArrayList<>();
        
        for (Tilaus tilaus : dao.readTilaukset()) {
            tilaukset.add(tilaus);
        }
        return tilaukset;
    }
    
}
