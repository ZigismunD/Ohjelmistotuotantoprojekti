/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closedhash;

/**
 *
 * @author RJulin
 */
public class Hash {

    private int taulunKoko = 7;
    Integer[] hajautustaulu = new Integer[taulunKoko];
    private int alkioidenMaara = 0;
   
    
    
    public boolean avaimenLisays(int avain) {
        boolean onnistui = false;
        int paikka = hajautus(avain);
        while (!onnistui && alkioidenMaara <= taulunKoko) {
            onnistui = hajautustaulu[paikka] == null;
            if (onnistui) {
                hajautustaulu[paikka] = avain;
                alkioidenMaara++;
            } else {
                paikka = uusiPaikka(paikka);
            }         
        }
        return onnistui;
    }
    
    
    public Integer avaimellaHaku(Integer avain) {
        boolean loytyi = false; 
        int paikka = hajautus(avain);
        Integer tulos = null;
        int ekaPaikka = paikka;      
        do {            
            loytyi = hajautustaulu[paikka] == avain;
            if (loytyi) {
                System.out.println("Avain paikassa: " + paikka);
               tulos = hajautustaulu[paikka]; 
            } else {
                paikka = uusiPaikka(paikka);
            } 
        } while (!loytyi && paikka != ekaPaikka);        
        return tulos;
    }
    
    
     int uusiPaikka(int paikka) {
        return ++paikka % taulunKoko;
    }
    
    private int hajautus(int avain) {
        return avain % taulunKoko;
    }
}

