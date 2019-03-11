package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import  javax.persistence.*;
/**
 *
 * @author vadimzubchenko
 * 
 * 
 * TÄMÄ ON PEKKA TESTI DEVELOPMENT HAARAAN
 */
@Entity
@Table (name="HENKILOSTO")
public class Henkilosto {
    private int id;
    private String nimi;
    private String rooli;
    private String salasana;
    private String kirjautumistunnus;
    
    public Henkilosto(String nimi, String rooli, String salasana, String kirjautumistunnus) {
        
        this.nimi = nimi;
        this.rooli = rooli;
        this.salasana = salasana;
        this.kirjautumistunnus = kirjautumistunnus;
    }
    public Henkilosto(){
    }
    
    public Henkilosto(String nimi, String rooli) {
        
    }
    
    @Id 
    @GeneratedValue
    @Column(name="Id")
    public int getHenkiloId() {
        return id;
    } 
    public void setHenkiloId(int id) {
        this.id = id;
    }
    
    @Column(name="Nimi")
    public String getHenkiloNimi() {
        return nimi;
    }
    public void setHenkiloNimi(String nimi) {
        this.nimi = nimi;
    }
    
    @Column(name="Rooli")
    public String getRooli() {
        return rooli;
    }
    public void setRooli(String rooli) {
        this.rooli = rooli;
    }
    
    @Column(name="Salasana")
    public String getSalasana() {
        return salasana;
    }
    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }
    
    @Column(name="Kirjautumistunnus")
    public String getKirjoitumistunnus() {
        return kirjautumistunnus;
    }
    public void setKirjoitumistunnus(String kirjautumistunnus) {
        this.kirjautumistunnus = kirjautumistunnus;
    }
    
    
}
