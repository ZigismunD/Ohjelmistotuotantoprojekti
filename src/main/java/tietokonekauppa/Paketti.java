/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietokonekauppa;

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
@Table(name = "Paketti")
public class Paketti {
    public int pakettiID;
    public String pakettiNimi;
    public double pakettiHinta;

    public Paketti() {
    }
    
    @Id
    @GeneratedValue
    @Column(name = "PakettiID")
    public int getPakettiID() {
        return pakettiID;
    }

    public void setPakettiID(int pakettiID) {
        this.pakettiID = pakettiID;
    }
    @Column(name = "PakettiNimi")
    public String getPakettiNimi() {
        return pakettiNimi;
    }

    public void setPakettiNimi(String pakettiNimi) {
        this.pakettiNimi = pakettiNimi;
    }
    @Column(name = "PakettiHinta")
    public double getPakettiHinta() {
        return pakettiHinta;
    }

    public void setPakettiHinta(double pakettiHinta) {
        this.pakettiHinta = pakettiHinta;
    }
    
    
    
}
