/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Asiakas;
import model.Henkilosto;
import model.Osa;
import model.Paketti;
import model.TietokonekauppaDAO;
import model.Tilaus;
import model.Tilaus_rivi;
import view.Tab1;
import view.View;
import view.loginView;

/**
 * 
 * @author Sami Sikkilä
 */
public class Controller {
    private static Controller INSTANCE = null;
    /**
     * Käyttöliittymä
     */
    View gui;
    /**
     * Tietokannan kanssa asioiva DataAccesObject
     */
    TietokonekauppaDAO dao;
    
    private Controller() {
        this.dao = new TietokonekauppaDAO();
    }
    
    /**
     * Konstruktori
     * @param gui Ohjemiston käyttöliittymäluokka
     */
    /*
    public Controller(View gui) {
        this.gui = gui;
        this.dao = new TietokonekauppaDAO();
    }
    */
    
    public static synchronized Controller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }
    
    public void setGui(View gui) {
        this.gui = gui;
    }
    
    public void reconnectDAO() {
        this.dao = new TietokonekauppaDAO();
    }
    
    public void enableLoginScreen(loginView loginscreen) {
        Boolean bTemp = this.dao.isSessionFactoryConnected();
        //Disabloi painikkeet jos tietokantayhteyttä ei ole luotu
        //btnLogin.setEnabled(bTemp);
    }
    
    public void loginUser(loginView loginscreen, Stage primaryStage, String nimi, String salasana) {
        Henkilosto user = dao.haeKayttaja(nimi, salasana);

        //user.getRooli();
        
        //Kirjautuminen epäonnistui
        if (user == null) {
            //Ilmoita virheestä ja tyhjennä tekstikentät
            loginscreen.setErrorMessage("Salasana väärin.");
        } else {
            
            //Luo view
            Stage Viewclass = new Stage();
            View v = new View();
            setGui(v);
            v.start(Viewclass);
            primaryStage.close();
        }
    }
    
    /**
     * Hae tietokoneiden nimet ComboBoxiin
     * @param box ComboBox, johon nimet halutaan tuoda
     */
    

    
     public ArrayList<Paketti> getAllComputerNames() {
        ArrayList<Paketti> paketit = new ArrayList<>();
        
        for (Paketti paketti : dao.readPaketit()) {
            paketit.add(paketti);
        }
        return paketit;
    }
     
     public ArrayList<Osa> getAllOsat() {
        ArrayList<Osa> osat = new ArrayList<>();
        
        for (Osa osa : dao.readOsat()) {
            osat.add(osa);
        }
        return osat;
    }

    /**
     * Funktio hakee käyttöliittymässä olevista tietokentistä tarvittavat tiedot ja luo niiden perusteella tilauksen
     */
    
    public void createOrder(Double hinta) {
        Tab1 tab1 = Tab1.getInstance();

        //Luo Tilaus_rivi lista productista
        List<Tilaus_rivi> tilaukset = tab1.getTilausrivit();
        Asiakas asiakas = tab1.getCustomer();
        //Tarkista että listassa on ainakin yksi tilaus
        if (tilaukset.isEmpty() == true) {
            //Ilmoita viewille että tilausrivejä ei ole yhtään
            tab1.lblWarning3.setText("Tilaus lista on tyhjä!");
            tab1.lblWarning3.setFill(Color.rgb(255, 0, 0));
        } else {
            dao.luoTilaus(tilaukset,asiakas,hinta);
            tab1.companyTxt.clear();
            tab1.addressTxt.clear();
            tab1.emailTxt.clear();
            tab1.lblWarning3.setText("Tilaus luotu onnistuneesti!");
            tab1.lblWarning3.setFill(Color.rgb(50, 205,50));
            tab1.lblWarning2.setText("");
            tab1.lblWarning.setText("");
            tab1.tableTemp.getItems().clear();
            tilaukset.removeAll(tilaukset);

        }
    }
    
    /**
     * Luo ohjelmistoon käyttäjä
     * @param henkilo  Henkilö, joka halutaan luoda
     */
    public void createUser(Henkilosto henkilo) {
        dao.luoHenkilo(henkilo);
    }

    /**
     * Hakee tietokannasta kaikki tiettyä tyyppiä vastaavat osat
     * @param tyyppi Osat jotka tietokannasta halutaan hakea
     * @return Lista halutuista osista
     */
    public ArrayList<Osa> getOsat(String tyyppi) {
        ArrayList<Osa> osat = new ArrayList<>();
        
        for (Osa osa : dao.getOsat(tyyppi)) {
            osat.add(osa);
        }
        
        return osat;
    }
    
    /**
     * Hae tilaukset tietokannasta
     * @return Lista kaikista tietokannassa olevista tilauksista
     */
    public ArrayList<Tilaus> getTilaukset() {
        ArrayList<Tilaus> tilaukset = new ArrayList<>();
        
        for (Tilaus tilaus : dao.readTilaukset()) {
            tilaukset.add(tilaus);
        }
        return tilaukset;
    }
    
     public ArrayList<Tilaus> getOrderRows(Tilaus tilaus) {
         return dao.tilausGetTilausRivit(tilaus);
    }
    

    public void luoOsa(Osa osa) {
        dao.luoOsa(osa);
    }
    
    public ArrayList<Object> getObjectRows(Object obj) {
        return dao.getObjectRows(obj);
    }
    
    public void objectSaveOrUpdate(Object obj) {
        dao.objectSaveOrUpdate(obj, null);
    }
    
    public void objectAndRowsSaveOrUpdate(Object obj, ArrayList<Object> obj_rows) {
        //dao.objectSaveOrUpdate(obj, obj_rows);
        dao.objectSaveOrUpdate(obj);
        dao.objectListSaveOrUpdate(obj_rows);
    }
    
    public void objectDelete(Object obj) {
        dao.objectDelete(obj);
    }

    public double[] getSalesOfYear(Integer vuosi) {
        return dao.getSalesYear(vuosi);
    }
    
}
