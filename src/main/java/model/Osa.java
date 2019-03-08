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
   public int osaID;
   public char nimi;
   public double hinta;
   public int varastoMaara;

    public Osa() {
    }

    public Osa(int osaID, char nimi, double hinta, int varastoMaara) {
        this.osaID = osaID;
        this.nimi = nimi;
        this.hinta = hinta;
        this.varastoMaara = varastoMaara;
    }
    @Id
    @GeneratedValue
    @Column(name = "OsaID")
    public int getOsaID() {
        return osaID;
    }

    public void setOsaID(int osaID) {
        this.osaID = osaID;
    }
    @Column(name = "Nimi")
    public char getNimi() {
        return nimi;
    }

    public void setNimi(char nimi) {
        this.nimi = nimi;
    }
    @Column(name = "Hinta")
    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
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
