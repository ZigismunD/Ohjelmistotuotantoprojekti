/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author hannu.korhonen
 */
public class Localization {
    private static Localization INSTANCE = null;
    
    ResourceBundle mybundle;
    Locale locale;
    
    private Localization() {
        //Suomi oletuskieli
        Locale.setDefault(new Locale("fi", "FI"));
        mybundle = ResourceBundle.getBundle("MyLabels");
    }
    
    public static synchronized Localization getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Localization();
        }
        return INSTANCE;
    }
    
    public void changeLocale(String language) {
        switch(language) { 
            case "FI": 
                Locale.setDefault(new Locale("fi", "FI"));
                break; 
            case "US": 
                Locale.setDefault(new Locale("en", "US"));
                break; 
            case "RUS": 
                Locale.setDefault(new Locale("en", "US"));
                break;
        }
        mybundle = ResourceBundle.getBundle("MyLabels");
    }
    
    public String[] getLocaleList() {
        return new String[] {"FI", "US", "RUS"};
    }
    
    public ResourceBundle getBundle() {
        return mybundle;
    }
}
