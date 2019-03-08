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
import model.Tilaus;

/**
 *
 * @author vadimzubchenko
 */
@Entity
@Table(name = "TILAUS_RIVI")
public class Tilaus_rivi {

    private int id;
    private Paketti paketti;
    private Osa osa;
    private int maara;
    private Tilaus tilaus;

    public Tilaus_rivi(Tilaus tilaus, Paketti paketti, Osa osa, int maara) {
        
        this.paketti = paketti;
        this.osa = osa;
        this.maara = maara;
    }
    
    public Tilaus_rivi() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getTilaus_riviId() {
        return id;
    }

    public void setTilaus_riviId(int id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "Paketti")
    public Paketti getPaketti() {
        return paketti;
    }

    public void setPaketti(Paketti paketti) {
        this.paketti = paketti;
    }
    @ManyToOne
    @JoinColumn(name = "Osa")
    public Osa getOsa() {
        return osa;
    }

    public void setOsa(Osa osa) {
        this.osa = osa;
    }
    @Column(name = "Maara")
    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }
    @ManyToOne
    @JoinColumn(name = "tilaus")
    public Tilaus getTilaus() {
        return tilaus;
    }

    public void setTilaus(Tilaus tilaus) {
        this.tilaus = tilaus;
    }

   

}
