/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

/**
 *
 * @author zigis
 */
/**
 * Luodaan olion PAKETTI
 * ja uuden tietokantataulu olio-relaatiomuunnoksen annotaatiolla.
 */
@Entity
@Table(name = "PAKETTI")
public class Paketti {
    /**
     * luodaan olion muuttujat
     */
    private int id;
    private String paketinNimi;
    private double paketinHinta;
    /**
     * luodaan tyhjän parametriton konstruktori
     */
    public Paketti() {

    }
    /**
     * luodaan konstruktorin 2-lla parametrilla
     * @param pakettiNimi luo paketin nimen 
     * @param pakettiHinta luo paketin hinnan 
     */
    public Paketti(String pakettiNimi, double pakettiHinta) {

        this.paketinNimi = pakettiNimi;
        this.paketinHinta = pakettiHinta;

    }
    /**
     * luodaan tauluun perusavaimen ja sen kentän Id
     * @return id palauttaa taulun tietokannan generoiman avainarvon
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getPakettiId() {
        return id;
    }
    /**
     * 
     * @param Id asentaa tietokannan generoiman id olioon
     */
    public void setPakettiId(int Id) {
        this.id = Id;
    }
    /**
     * luodaan tauluun kentän "Nimi"
     * @return nimi palauttaa tuotteen nimen
     */
    @Column(name = "nimi")
    public String getPaketinNimi() {
        return paketinNimi;
    }
    /**
     * 
     * @param paketinNimi  asentaa tietokannan generoiman id olioon
     */
    public void setPaketinNimi(String paketinNimi) {
        this.paketinNimi = paketinNimi;
    }
     /**
     * luodaan tauluun kentän "hinta"
     * @return paketinHinta palauttaa paketin hinnan
     */
    @Column(name = "hinta")
    public double getPaketinHinta() {
        return paketinHinta;
    }
    /**
     * 
     * @param paketinHinta  asentaa tietokannan generoiman id olioon
     */
    public void setPaketinHinta(double paketinHinta) {
        this.paketinHinta = paketinHinta;
    }
    /**
     * 
     * @return paketin nimen
     */
    public String toString(){
        return this.paketinNimi;
    }
}
