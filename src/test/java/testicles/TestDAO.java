/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testicles;

import model.Henkilosto;
import model.TietokonekauppaDAO;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import view.View;

/**
 *
 * @author hannu.korhonen
 */
public class TestDAO {
    
    public static void main(String[] args) {
        TietokonekauppaDAO dao = new TietokonekauppaDAO();
        
        /*
        //1.Luo henkilö
        Henkilosto henkilo1 = new Henkilosto();
        int henkiloId;
        henkilo1.setHenkiloNimi("pekka");
        henkilo1.setRooli("varasto");
        henkiloId = dao.luoHenkilo(henkilo1);
        
        //2.Tarkista että henkilö on luotu
        System.out.println("Henkilön id: " + henkiloId);  
        */
                
        //Hae henkilön rooli
        String nimi = "pekka";
        Henkilosto haeHenkilo = dao.haeKayttaja(nimi);
        
        if (haeHenkilo == null) {
            System.out.println("Henkilö " + nimi + " ei löytynyt");
        } else {
            System.out.println("Henkilön " + nimi + " rooli on " + haeHenkilo.getRooli());
        }
        
        
        //Poista henkilö
        dao.poistaHenkilo(haeHenkilo);
        
        if (haeHenkilo == null) {
            System.out.println("Henkilö " + nimi + " ei löytynyt");
        } else {
            System.out.println("Henkilön " + nimi + " rooli on " + haeHenkilo.getRooli());
        }
        
        haeHenkilo = dao.haeKayttaja(nimi);
        
        if (haeHenkilo == null) {
            System.out.println("Henkilö " + nimi + " ei löytynyt");
        } else {
            System.out.println("Henkilön " + nimi + " rooli on " + haeHenkilo.getRooli());
        }
        
    }
}
