/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Tab;
import view.Tab1;
import view.Tab2;
import view.Tab3;
import view.Tab4;

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
                Locale.setDefault(new Locale("ru", "RU"));
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
    public void translateAll(Tab1 tab1, Tab2 tab2, Tab3 tab3, Tab4 tab4) {
        tab1.localizationSetText();
        tab2.localizationSetText();
        tab3.localizationSetText();
        //tab4.localizationSetText();
        
    }

    public void translateAll(Tab tab1, Tab tab2, Tab tab3, Tab tab4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
}
