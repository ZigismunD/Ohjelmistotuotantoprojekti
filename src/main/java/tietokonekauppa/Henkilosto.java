package tietokonekauppa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import  javax.persistence.*;
/**
 *
 * @author vadimzubchenko
 */
@ Entity
@ Table (name="HENKILOSTO")
public class Henkilosto {
    private int henkiloId;
    private String nimi;
    private String rooli;
    
    public Henkilosto( int henkoloId, String nimi, String rooli) {
        this.nimi = nimi;
        this.rooli = rooli;
    }
    public Henkilosto(){
        
    }
    @Id 
    @GeneratedValue
    @Column(name="HenkiloID")
    public int getHenkiloId() {
        return henkiloId;
    } 

    public void setHenkiloId(int henkiloId) {
        this.henkiloId = henkiloId;
    }
    
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getRooli() {
        return rooli;
    }

    public void setRooli(String rooli) {
        this.rooli = rooli;
    }
    
    
}
