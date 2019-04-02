/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearch;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author RJulin
 */
public class Binarysearch {

    ArrayList<Integer> taulukko;
    int taulukkoKoko;
    
    public Binarysearch() {
        taulukko = new ArrayList();
        taulukkoKoko = 0;
    }
    
    //Lisäys 
    public void insert(int luku) {
        taulukko.add(luku);
        taulukkoKoko++;
        Collections.sort(taulukko);
    }
    
    public boolean searchMiddle(int avain) {
     
        int vasen = 0;
        int oikea = taulukkoKoko - 1;
        //Looppaa niin kauan et vasen ja oikea ei oo samat
        while (vasen <= oikea) {
            //Tarkista vasemman ja oikean välinen positio
            int m = (vasen + oikea) / 2;
            //Onko keskikohdan arvo pienempi avain
            if (taulukko.get(m) < avain) {
                //Etsi oikealta
                vasen = m + 1;
            //Onko keskikohdan arvo suurempi avain
            } else if (taulukko.get(m) > avain) {
                //Etsi vasemmalta
                oikea = m - 1;
            } else {
                //Arvo löytyy
                return true;
            }
        }
        //Arvoa ei löydy
        return false;
    }
    
    public void print() {
        System.out.println(taulukko);
    }
}