/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Asiakas;
import model.Henkilosto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vadimzubchenko
 */
@Entity
@Table(name = "TILAUS")
public class Tilaus {

    private int Id;
    private Asiakas asiakas;
    private Henkilosto henkilosto;
    private double tilausPvm;

    public Tilaus(Asiakas asiakas, Henkilosto henkilosto, double tilausPvm) {
        
        this.asiakas = asiakas;
        this.henkilosto = henkilosto;
        this.tilausPvm = tilausPvm;
    }

    public Tilaus() {
    }

    @Id
    @GeneratedValue
    @Column(name = "Id")
    public int getTilausId() {
        return Id;
    }

    public void setTilausId(int Id) {
        this.Id = Id;
    }

    @ManyToOne
    @JoinColumn(name = "Asiakas")
    public Asiakas getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    @ManyToOne
    @JoinColumn(name = "henkilosto")
    public Henkilosto getHenkilosto() {
        return henkilosto;
    }

    public void setHenkilosto(Henkilosto henkilosto) {
        this.henkilosto = henkilosto;
    }

    @Column(name = "TilausPvm")
    public double getTilausPvm() {
        return tilausPvm;
    }

    public void setTilausPvm(double tilausPvm) {
        this.tilausPvm = tilausPvm;
    }

}
