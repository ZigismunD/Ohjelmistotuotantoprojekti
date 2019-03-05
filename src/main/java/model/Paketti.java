/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javax.persistence.*;

/**
 *
 * @author zigis
 */
@Entity
@Table(name="PAKETTI")
public class Paketti {
    
    @Id
    @GeneratedValue
    @Column(name="id")
    private int paketinId;
    
    @Column(name="nimi")
    private String paketinNimi;
    
    @Column(name="hinta")
    private Double paketinHinta;
    
    @Column(name="viite_osa")
    private Integer paketinViite;
    
    public Paketti() {
        
    }
    
    public Paketti(String merkkijono, double numero, int viitenro) {
        this.paketinNimi = merkkijono;
        this.paketinHinta = numero;
        this.paketinViite = viitenro;
    }
    
    public Paketti(String merkkijono, double numero) {
        this.paketinNimi = merkkijono;
        this.paketinHinta = numero;
    }
   

    public int getPaketinId() {
        return paketinId;
    }

    public void setPaketinId(int paketinId) {
        this.paketinId = paketinId;
    }

    public String getPaketinNimi() {
        return paketinNimi;
    }

    public void setPaketinNimi(String paketinNimi) {
        this.paketinNimi = paketinNimi;
    }

    public double getPaketinHinta() {
        return paketinHinta;
    }

    public void setPaketinHinta(double paketinHinta) {
        this.paketinHinta = paketinHinta;
    }

    public int getPaketinViite() {
        return paketinViite;
    }

    public void setPaketinViite(int paketinViite) {
        this.paketinViite = paketinViite;
    }
    
}
