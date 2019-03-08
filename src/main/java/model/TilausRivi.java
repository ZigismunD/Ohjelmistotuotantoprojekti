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
 * @author zigis
 */
@Entity
@Table(name="TILAUS_RIVI")
public class TilausRivi {
    
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    
    @Column(name="paketti")
    private Paketti tilattuPaketti;
    
    @Column(name="maara")
    private int pakettienMaara;
    
    @Column(name="hinta")
    private int tilausRivinHinta;
    
    public TilausRivi() {
        
    }
    
    public TilausRivi(Paketti paketti, int maara, int hinta) {
        this.tilattuPaketti = paketti;
        this.pakettienMaara = maara;
        this.tilausRivinHinta = hinta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paketti getTilattuPaketti() {
        return tilattuPaketti;
    }

    public void setTilattuPaketti(Paketti tilattuPaketti) {
        this.tilattuPaketti = tilattuPaketti;
    }

    public int getPakettienMaara() {
        return pakettienMaara;
    }

    public void setPakettienMaara(int pakettienMaara) {
        this.pakettienMaara = pakettienMaara;
    }

    public int getTilausRivinHinta() {
        return tilausRivinHinta;
    }

    public void setTilausRivinHinta(int tilausRivinHinta) {
        this.tilausRivinHinta = tilausRivinHinta;
    }
    
    
    
}
