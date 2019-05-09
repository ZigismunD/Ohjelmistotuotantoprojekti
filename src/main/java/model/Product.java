/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RJulin
 */
public class Product {
    
    //Paketti
    private String name;
    private int amount;
    private double price;

    
    //Tilausrivi
    private Tilaus_rivi tilausrivi;
    /**
     * luodaan Product konstruktorin 2-parametrilla
     *
     * @param objekti luo osan tai paketin objektina Osan tai Paketin luokeista
     * @param Samount luo osien tai pakettien määrän 
     */
    public Product(Object objekti, int Samount) {
        if (objekti instanceof Paketti) {
            this.name = ((Paketti) objekti).getPaketinNimi();
            this.price =((Paketti) objekti).getPaketinHinta();

        }
        if (objekti instanceof Osa) {
            this.name = ((Osa) objekti).getOsaNimi();
            this.price = ((Osa) objekti).getOsaHinta();
        }
        this.amount = Samount;
        this.tilausrivi = new Tilaus_rivi(objekti, Samount,this.price);
    }
    
    public String getName() {
        return name;
    }
    /**
     *
     * @param name asentaa tuotteen nimi Product olioon
     */
    public void setName(String Sname) {
        this.name = Sname;
    }
    /**
     * @return amount Product-oliosta
     */
    public int getAmount() {
        return amount;
    }
    /**
     *
     * @param amount asentaa tuotteen määrän Product olioon
     */
    public void setAmount(int Samount) {
        this.amount = Samount;
    }
    /**
     * @return price Product-oliosta
     */
    public double getPrice() {
        return price;
    }
    /**
     *
     * @param price asentaa tuotteen hinnan Product olioon
     */
    public void setPrice(double Sprice) {
        this.price = Sprice;
    }
    /**
     * @return tilausrivi Product-oliosta
     */
    public Tilaus_rivi getTilaus_rivi() {
        return tilausrivi;
    }

}
