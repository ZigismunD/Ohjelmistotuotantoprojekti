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
@Entity
@Table(name = "OSA")
public class Osa {
   private int id;
   private String osaNimi;
   private double osaHinta;
   private int varastoMaara;
   private String tyyppi;

    public Osa() {
    }

    public Osa(String nimi, double hinta, int varastoMaara, String tyyppi) {
        
        this.osaNimi = nimi;
        this.osaHinta = hinta;
        this.varastoMaara = varastoMaara;
        this.tyyppi = tyyppi;
    }
    @Id
    @GeneratedValue
    @Column(name = "Id")
    public int getOsaId() {
        return id;
    }
    public void setOsaId(int id) {
        this.id = id;
    }
    
    @Column(name = "Nimi")
    public String getOsaNimi() {
        return osaNimi;
    }
    public void setOsaNimi(String nimi) {
        this.osaNimi = nimi;
    }
    
    @Column(name = "Hinta")
    public double getOsaHinta() {
        return osaHinta;
    }
    public void setOsaHinta(double hinta) {
        this.osaHinta = hinta;
    }
    
    @Column(name = "VarastoMaara")
    public int getVarastoMaara() {
        return varastoMaara;
    }
    public void setVarastoMaara(int varastoMaara) {
        this.varastoMaara = varastoMaara;
    }
    
    @Column(name = "Tyyppi")
    public String getTyyppi() {
        return tyyppi;
    }
    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }
    
    
   
   
}
