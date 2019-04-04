/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
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
 *
 * Luodaan olion TILAUS
 * ja uuden tietokantataulun olio-relaatiomuunnoksen annotaatiolla.
 */
@Entity
@Table(name = "TILAUS")
public class Tilaus {
    /**
     * luodaan olion muuttujat 
     */
    private int id;
    private Asiakas asiakas;
    private Henkilosto henkilosto;
    private Date tilausPvm;
    /**
     * luodaan konstruktorin 3-parametrilla
     * @param asiakas luo asiakkaan parametrit Asiakas-oliosta
     * @param henkilosto luo työntekijän parametrit Henkilosto-oliosta
     * @param tilausPvm luo tilauksen päivämäärän 
     */
    public Tilaus(Asiakas asiakas, Henkilosto henkilosto, Date tilausPvm) {
        
        this.asiakas = asiakas;
        this.henkilosto = henkilosto;
        this.tilausPvm = tilausPvm;
    }
    
    /**
     * luodaan parametriton konstruktorin
     */
    public Tilaus() {
    }
    /**
     * luodaan tauluun perusavaimen ja sen kenttä id
     * @return id palauttaa taulun tietokannan generoiman avainarvon
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getTilausId() {
        return id;
    }
    /**
     * 
     * @param Id asentaa tietokannan generoiman id olioon
     */
    public void setTilausId(int Id) {
        this.id = Id;
    }
    /**
     * luodaan monen suhde yhteen-yhteyden, joka 
     * tuottaa viiteavaimen Asiakas-olioon
     * @return asikkaan parametrit Asiakas-oliosta
     */
    @ManyToOne
    @JoinColumn(name = "Asiakas")
    public Asiakas getAsiakas() {
        return asiakas;
    }
    /**
     * 
     * @param asiakas asentaa asiakkaan parametrit Asiakas-oliosta
     * Tilaus-olioon
     */
    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }
    /**
     * luodaan monen suhde yhteen-yhteyden, joka 
     * tuottaa viiteavaimen Henkilosto-olioon
     * @return työntekijan parametrit Henkilosto-oliosta
     */
    @ManyToOne
    @JoinColumn(name = "henkilosto")
    public Henkilosto getHenkilosto() {
        return henkilosto;
    }
    /**
     * 
     * @param henkilosto asentaa työntekijan parametrit Henkilosto-oliosta
     * Tilaus-olioon
     */
    public void setHenkilosto(Henkilosto henkilosto) {
        this.henkilosto = henkilosto;
    }
    /**
     * luodaan tauluun kentän "TilausPvm"
     * @return tilausPvm palauttaa tulauksen päivämäärän 
     */
    @Column(name = "TilausPvm")
    public Date getTilausPvm() {
        return tilausPvm;
    }
    /**
     * 
     * @param tilausPvm asentaa tilauksen päivämäärän Tilaus-olioon
     */
    public void setTilausPvm(Date tilausPvm) {
        this.tilausPvm = tilausPvm;
    }

}
