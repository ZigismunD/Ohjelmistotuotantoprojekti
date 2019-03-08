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
   private int Id;
   private String nimi;
   private double hinta;
   private int varastoMaara;

    public Osa() {
    }

    public Osa(String nimi, double hinta, int varastoMaara) {
        
        this.nimi = nimi;
        this.hinta = hinta;
        this.varastoMaara = varastoMaara;
    }
    @Id
    @GeneratedValue
    @Column(name = "Id")
    public int getOsaId() {
        return Id;
    }

    public void setOsaId(int Id) {
        this.Id = Id;
    }
    @Column(name = "Nimi")
    public String getOsaNimi() {
        return nimi;
    }

    public void setOsaNimi(String nimi) {
        this.nimi = nimi;
    }
    
    @Column(name = "Hinta")
    public double getOsaHinta() {
        return hinta;
    }

    public void setOsaHinta(double hinta) {
        this.hinta = hinta;
    }
    @Column(name = "VarastoMaara")
    public int getVarastoMaara() {
        return varastoMaara;
    }

    public void setVarastoMaara(int varastoMaara) {
        this.varastoMaara = varastoMaara;
    }
    
    
   
   
}
