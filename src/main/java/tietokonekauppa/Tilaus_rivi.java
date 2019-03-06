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
@Table(name = "TILAUS_RIVI")
public class Tilaus_rivi {

    public int tilaus_riviID;
    public Paketti pakettiID;
    public Osa osaID;
    public int maara;
    public Tilaus tilausID;

    public Tilaus_rivi(int tilaus_riviID, Tilaus tilausID, Paketti tilausPaketti, Osa tilausOsa, int maara) {
        this.tilaus_riviID = tilaus_riviID;
        this.pakettiID = tilausPaketti;
        this.osaID = tilausOsa;
        this.maara = maara;
    }
    
    public Tilaus_rivi() {
    }

    @Id
    @GeneratedValue
    @Column(name = "Tilaus_riviID")
    public int getTilaus_riviID() {
        return tilaus_riviID;
    }

    public void setTilaus_riviID(int tilaus_riviID) {
        this.tilaus_riviID = tilaus_riviID;
    }
    @ManyToOne
    @JoinColumn(name = "TilausPaketti")
    public Paketti getPakettiID() {
        return pakettiID;
    }

    public void setPakettiID(Paketti pakettiID) {
        this.pakettiID = pakettiID;
    }
    @ManyToOne
    @JoinColumn(name = "TilausOsa")
    public Osa getOsaID() {
        return osaID;
    }

    public void setOsaID(Osa osaID) {
        this.osaID = osaID;
    }
    @Column(name = "Maara")
    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }
    @ManyToOne
    @JoinColumn(name = "TilausID")
    public Tilaus getTilausID() {
        return tilausID;
    }

    public void setTilausID(Tilaus tilausID) {
        this.tilausID = tilausID;
    }

   

}
