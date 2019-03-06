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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author vadimzubchenko
 */
@Entity
@Table(name = "PAKETTI_RIVI")
public class Paketti_rivi {
    public int paketti_riviID;
    public Paketti pakettiID;
    public Osa osaID;

    public Paketti_rivi() {
        
    }
    @Id
    @GeneratedValue
    @Column(name = "Paketti_riviID")
    public int getPaketti_riviID() {
        return paketti_riviID;
    }

    public void setPaketti_riviID(int paketti_riviID) {
        this.paketti_riviID = paketti_riviID;
    }
    
    @ManyToOne
    @JoinColumn(name = "OsaID")
    public Osa getOsaID() {
        return osaID;
    }

    public void setOsaID(Osa osaID) {
        this.osaID = osaID;
    }
    @ManyToOne
    @JoinColumn(name = "Paketti")
    public Paketti getPakettiID() {
        return pakettiID;
    }

    public void setPakettiID(Paketti pakettiID) {
        this.pakettiID = pakettiID;
    }
    
    
}
