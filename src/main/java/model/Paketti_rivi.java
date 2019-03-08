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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import model.Osa;
import model.Paketti;


/**
 *
 * @author vadimzubchenko
 */
@Entity
@Table(name = "PAKETTI_RIVI")
public class Paketti_rivi {
    private int Id;
    private Paketti paketti;
    private Osa osa;

    public Paketti_rivi() {
        
    }

    public Paketti_rivi(Paketti paketti, Osa osa) {
        this.paketti = paketti;
        this.osa = osa;
    }
    
    @Id
    @GeneratedValue
    @Column(name = "Id")
    public int getPakettiId() {
        return Id;
    }

    public void setPakettiId(int Id) {
        this.Id = Id;
    }
    
    @ManyToOne
    @JoinColumn(name = "Osa")
    public Osa getOsa() {
        return osa;
    }

    public void setOsa(Osa osa) {
        this.osa = osa;
    }
    @ManyToOne
    @JoinColumn(name = "Paketti")
    public Paketti getPaketti() {
        return paketti;
    }

    public void setPaketti(Paketti paketti) {
        this.paketti = paketti;
    }
    
    
}
