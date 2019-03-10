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
@Table(name = "PAKETTI")
public class Paketti {

    private int Id;
    private String paketinNimi;
    private double paketinHinta;

    public Paketti() {

    }

    public Paketti(String paketinNimi, double pakettiHinta) {

        this.paketinNimi = paketinNimi;
        this.paketinHinta = pakettiHinta;

    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getPakettiId() {
        return Id;
    }

    public void setPakettiId(int Id) {
        this.Id = Id;
    }

    @Column(name = "nimi")
    public String getPaketinNimi() {
        return paketinNimi;
    }

    public void setPaketinNimi(String paketinNimi) {
        this.paketinNimi = paketinNimi;
    }

    @Column(name = "hinta")
    public Double getPaketinHinta() {
        return paketinHinta;
    }

    public void setPaketinHinta(Double paketinHinta) {
        this.paketinHinta = paketinHinta;
    }

}
