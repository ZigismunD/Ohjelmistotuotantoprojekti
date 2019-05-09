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
    /**
     * Singleton malli palauttaa luokan olion vain yhteenä ilmentymänä
     * @return INSTANCE parametrit Lokalization-oliosta
     */
    public static synchronized Localization getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Localization();
        }
        return INSTANCE;
    }
    /**
     * 
     * @param language luo kielen tilan ja asentaa sen mukaisesti käyttöliitymän kielen
     */
    public void changeLocale(String language) {
        switch (language) {
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
    
    public ResourceBundle getBundle() {
        return mybundle;
    }
    /**
     *  luodaan konstruktorin 4-lla parametrilla
     * @param tab1 luo taulu 1 ja toteuttaa sen lokalisaation View luokassa
     * @param tab2 luo taulu 1 ja toteuttaa sen lokalisaation View luokassa
     * @param tab3 luo taulu 1 ja toteuttaa sen lokalisaation View luokassa
     * @param tab4 luo taulu 1 ja toteuttaa sen lokalisaation View luokassa
     */
    public void translateAll(Tab1 tab1, Tab2 tab2, Tab3 tab3, Tab4 tab4) {
        tab1.localizationSetText();
        tab2.localizationSetText();
        tab3.localizationSetText();
        tab4.localizationSetText();

    }

}
