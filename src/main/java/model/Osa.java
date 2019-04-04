/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vadimzubchenko
 */
/**
 * Luodaan olion OSA
 * ja uuden tietokantataulun olio-relaatiomuunnoksen annotaatiolla.
 */
@Entity
@Table(name = "OSA")
public class Osa {
   /**
   * luodaan olion muuttujat
   */
   private int id;
   private String osaNimi;
   private double osaHinta;
   private int varastoMaara;
   private String tyyppi;
    /**
     * luodaan tyhjän parametriton konstruktorin
     */
    public Osa() {
    }
    /**
     * luodaan konstruktorin 4-lla parametrilla
     * @param nimi luo tuotteen nimem
     * @param hinta luo tuotteen hinnan 
     * @param varastoMaara luoo tuotteen määrän varastossa
     * @param tyyppi luo tuotteen tyypi (paketti tai osa)
     */
    public Osa(String nimi, double hinta, int varastoMaara, String tyyppi) {
        
        this.osaNimi = nimi;
        this.osaHinta = hinta;
        this.varastoMaara = varastoMaara;
        this.tyyppi = tyyppi;
    }
    /**
     * 
     * @return Id palauttaa taulun tietokannan generoiman avainarvon
     */
    @Id
    @GeneratedValue
    @Column(name = "Id")
    public int getOsaId() {
        return id;
    }
    /**
     * 
     * @param id asentaa tietokannan generoiman id olioon
     */
    public void setOsaId(int id) {
        this.id = id;
    }
    /**
     * luodaan tauluun kentän "Nimi"
     * @return nimi palauttaa tuotteen nimen
     */
    @Column(name = "Nimi")
    public String getOsaNimi() {
        return osaNimi;
    }
    /**
     * 
     * @param nimi asentaa tuotteen nimen olioon
     */
    public void setOsaNimi(String nimi) {
        this.osaNimi = nimi;
    }
    /**
     * luodaan tauluun kentän "Hinta"
     * @return osaHinta palauttaa tuotteen hinnan
     */
    @Column(name = "Hinta")
    public double getOsaHinta() {
        return osaHinta;
    }
    /**
     * 
     * @param hinta asentaa tuotteen hinnan olioon
     */
    public void setOsaHinta(double hinta) {
        this.osaHinta = hinta;
    }
    /**
     * luodaan tauluun kentän "Nimi"
     * @return nimi palauttaa asiakkaan antaman nimen
     */
    @Column(name = "VarastoMaara")
    public int getVarastoMaara() {
        return varastoMaara;
    }
    /**
     * 
     * @param varastoMaara asentaa tuotteen määrän varastossa olioon
     */
    public void setVarastoMaara(int varastoMaara) {
        this.varastoMaara = varastoMaara;
    }
    /**
     * luodaan tauluun kentän "Tyyppi"
     * @return tyyppi palauttaa tuotteen tyypin 
     */
    @Column(name = "Tyyppi")
    public String getTyyppi() {
        return tyyppi;
    }
    /**
     * 
     * @param tyyppi asentaa tuotteen tyyypin (paketti tai osa) olioon
     */
    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }
    
    
   
   
}
