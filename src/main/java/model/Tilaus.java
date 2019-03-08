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

    private int tilausID;
    private Asiakas asiakasID;
    private Henkilosto henkilostoKasittelijaID;
    private double tilausPvm;

    public Tilaus(int tilausID, Asiakas asiakasID, Henkilosto henkilostoKasittelijaID, double tilausPvm) {
        this.tilausID = tilausID;
        this.asiakasID = asiakasID;
        this.henkilostoKasittelijaID = henkilostoKasittelijaID;
        this.tilausPvm = tilausPvm;
    }

    public Tilaus() {
    }

    @Id
    @GeneratedValue
    @Column(name = "TilausID")
    public int getTilausID() {
        return tilausID;
    }

    public void setTilausID(int tilausID) {
        this.tilausID = tilausID;
    }

    @ManyToOne
    @JoinColumn(name = "AsiakasID")
    public Asiakas getAsiakasID() {
        return asiakasID;
    }

    public void setAsiakasID(Asiakas asiakasID) {
        this.asiakasID = asiakasID;
    }

    @ManyToOne
    @JoinColumn(name = "henkilostoKasittelijaID")
    public Henkilosto getHenkilosto() {
        return henkilostoKasittelijaID;
    }

    public void setHenkilostoKasittelijaID(Henkilosto henkilostoKasittelijaID) {
        this.henkilostoKasittelijaID = henkilostoKasittelijaID;
    }

    @Column(name = "TilausPvm")
    public double getTilausPvm() {
        return tilausPvm;
    }

    public void setTilausPvm(double tilausPvm) {
        this.tilausPvm = tilausPvm;
    }

}
