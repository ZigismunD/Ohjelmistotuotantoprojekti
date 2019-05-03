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
        /**
         * luodaan parametrillisen konsruktorin 
         * @param Tpaketti luo tillatun paketin parametrit Paketti oliosta
         * @param Samount luo tilattu pakettien määrän
         */
        public Product(Paketti Tpaketti, int Samount) {
            this.paketti = Tpaketti;
            this.tuote1 = paketti.getPaketinNimi();
            this.amount = Samount;
            this.price = paketti.getPaketinHinta(); 
            this.tilausrivi = new Tilaus_rivi(paketti, Samount);
        }
        /**
         * 
         * @return tuote1 palauttaa paketin nimen  
         */
        public String getTuote1() {
            return tuote1;
        }
        /**
         * 
         * @param tuote1 asentaa paketin nimen 
         */
        public void setTuote1(String tuote1) {
            this.tuote1 = tuote1;
        }
        /**
         * 
         * @return amount palauttaa pakettien määrän
         */
        public int getAmount() {
            return amount;
        }
        /**
         * 
         * @param Samount asentaa pakettien määrän 
         */
        public void setAmount(int Samount) {
            this.amount = Samount;
        }
        /**
         * 
         * @return price palauttaa paketin hinnan 
         */
        public double getPrice() {
            return price;
        }
        /**
         * 
         * @param Sprice asentaa paketin hinnan 
         */
        public void setPrice(double Sprice) {
            this.price = Sprice;
        }
        /**
         * 
         * @return tilausrivi palauttaa tilauksen paramatrit Tilaus_rivin oliosta
         */
        public Tilaus_rivi getTilaus_rivi() {
            return tilausrivi;
        }
        
    }