/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietokonekauppa;

/**
 *
 * @author zigis
 */
public class Model {
    Controller cont;
    private String koneenNimi;
    private String osanNimi;
    private int hinta;
    
    
    //TESTIÄ VARTEN
    private int tulos = 1;
    
    public Model() {
    }
    
    public void init() {  // Nollaa tulosmuuttuja
        tulos = 0;
    }
        public int annatulos(){       
        return tulos;
    }
    
    public Model(Controller cont) {
        this.cont = cont;
    }
    
    public void luoTietokone() {
        
    }
   
}


