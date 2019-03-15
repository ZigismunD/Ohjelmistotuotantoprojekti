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
 
        Paketti paketti;
        private String tuote1;
        private int amount;
        private double price;
        private Tilaus_rivi tilausrivi;
 
        public Product(Paketti Tpaketti, int Samount) {
            this.paketti = Tpaketti;
            this.tuote1 = paketti.getPaketinNimi();
            this.amount = Samount;
            this.price = paketti.getPaketinHinta(); 
            this.tilausrivi = new Tilaus_rivi(paketti, Samount);
        }
 
        public String getTuote1() {
            return tuote1;
        }
 
        public void setTuote1(String tuote1) {
            this.tuote1 = tuote1;
        }
 
        public int getAmount() {
            return amount;
        }
 
        public void setAmount(int Samount) {
            this.amount = Samount;
        }
 
        public double getPrice() {
            return price;
        }
 
        public void setPrice(double Sprice) {
            this.price = Sprice;
        }
        
        public Tilaus_rivi getTilaus_rivi() {
            return tilausrivi;
        }
        
    }