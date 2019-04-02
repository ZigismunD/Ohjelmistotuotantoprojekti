/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closedhash;

import java.util.Scanner;

/**
 *
 * @author RJulin
 */
public class Hashgui {
     public static void main(String[] args) {
        Hash haj = new Hash();
        Scanner lukija = new Scanner(System.in);
        int komento;
        do {
        System.out.println("1. Avaimen lisäys");
        System.out.println("2. Avaimella haku");
        System.out.println("3. Poistu");
        komento = Integer.parseInt(lukija.nextLine());
        
        switch (komento) {
            case 1:
                System.out.print("Anna uusi avain (merkkijono)");
                if (haj.avaimenLisays(Integer.parseInt(lukija.nextLine()))) {
                    System.out.println("Avaimen lisäys onnistui");
                } else {
                    System.out.println("Avainta ei saatu lisättyä");
                }
                
                break;
            case 2:
                System.out.println("Anna etsittävä avain (merkkijono)");
                Integer haku = haj.avaimellaHaku(Integer.parseInt(lukija.nextLine()));
                
                if (haku == null) {
                    System.out.println("Avainta ei löytynyt");
                } else {
                    System.out.println("Avain " + haku + " löytyi!");
                }
        }
        } while (komento != 3);
        
    }
    
}