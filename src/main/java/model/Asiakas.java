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

/**
 *
 * @author vadimzubchenko
 */
@Entity
@Table(name = "ASIAKAS")
public class Asiakas {

    private int asiakasID;
    private char nimi;
    private char osoite;
    private char email;

    public Asiakas() {
    }

    public Asiakas(int asiakasID, char nimi, char osoite, char email) {
        this.asiakasID = asiakasID;
        this.nimi = nimi;
        this.osoite = osoite;
        this.email = email;
    }

    @Id
    @GeneratedValue
    @Column(name = "AsiakasID")
    public int getAsiakasID() {
        return asiakasID;
    }

    public void setAsiakasID(int asiakasID) {
        this.asiakasID = asiakasID;
    }

    @Column(name = "Nimi")
    public char getNimi() {
        return nimi;
    }

    public void setNimi(char nimi) {
        this.nimi = nimi;
    }

    @Column(name = "Osoite")
    public char getOsoite() {
        return osoite;
    }

    public void setOsoite(char osoite) {
        this.osoite = osoite;
    }

    @Column(name = "Email")
    public char getEmail() {
        return email;
    }

    public void setEmail(char email) {
        this.email = email;
    }

}
