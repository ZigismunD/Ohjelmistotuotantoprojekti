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
    private int Id;
    private String nimi;
    private String rooli;
    
    public Henkilosto(String nimi, String rooli) {
        
        this.nimi = nimi;
        this.rooli = rooli;
    }
    public Henkilosto(){
    }
    
    @Id 
    @GeneratedValue
    @Column(name="Id")
    public int getHenkiloId() {
        return Id;
    } 

    public void setHenkiloId(int Id) {
        this.Id = Id;
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
    
    
}
