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

    private int Id;
    private String nimi;
    private String osoite;
    private String email;

    public Asiakas() {
    }

    public Asiakas(String nimi, String osoite, String email) {
        
        this.nimi = nimi;
        this.osoite = osoite;
        this.email = email;
    }

    @Id
    @GeneratedValue
    @Column(name = "Id")
    public int getAsiakasID() {
        return Id;
    }

    public void setAsiakasID(int Id) {
        this.Id = Id;
    }

    @Column(name = "Nimi")
    public String getAsiakasNimi() {
        return nimi;
    }

    public void setAsiakasNimi(String nimi) {
        this.nimi = nimi;
    }

    @Column(name = "Osoite")
    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
