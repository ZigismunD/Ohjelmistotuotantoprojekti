/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class TietokonekauppaDAO {
    
    SessionFactory istuntotehdas = null;
    final StandardServiceRegistry registry;
    
    /**
    * Konstruktori
    */
    public TietokonekauppaDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC-ajurin lataaminen epäonnistui");
            System.exit(-1);
        }

        registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Istuntotehtaan luonti epäonnistui");
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @return result palauttaa listan kaikista Paketti riveistä
     */
    public List<Paketti> readPaketit() {
        // TODO Auto-generated method stub
        ArrayList<Paketti> paketit = new ArrayList<>();
        Session istunto = istuntotehdas.openSession();
        try {
            Transaction transaction = istunto.beginTransaction();
            //@SuppressWarnings("unchecked")
            List<Paketti> result = istunto.createQuery("from Paketti").list();
            for (Paketti v : result) {
                paketit.add(new Paketti(v.getPaketinNimi(), v.getPaketinHinta()));
            }
            transaction.commit();

            for (Paketti paketti : result) {
                System.out.println(paketti.getPaketinNimi());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        } finally {
            istunto.close();
        }

    }
    
    /**
     * 
     * @return result palauttaa listan kaikista Tilaus riveistä
     */
    public List<Tilaus> readTilaukset() {
        // TODO Auto-generated method stub
        ArrayList<Tilaus> tilaukset = new ArrayList<>();
        Session istunto = istuntotehdas.openSession();
        try {
            Transaction transaction = istunto.beginTransaction();
            //@SuppressWarnings("unchecked")
            List<Tilaus> result = istunto.createQuery("from Tilaus").list();
            
            transaction.commit();
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        } finally {
            istunto.close();
        }

    }
    
    /**
     * 
     * @param id - olion haetun id
     * @return result palauttaa paketti olion haetun id:n perusteella
     */
    public Paketti readPaketti(int id) {
        try (Session istunto = istuntotehdas.openSession()) {
            istunto.beginTransaction();
            Paketti pak = new Paketti();
            istunto.load(pak, id);
            return new Paketti(pak.getPaketinNimi(), pak.getPaketinHinta());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 
     * @param id 
     * @return result palauttaa paketin hinnan haetun id:n perusteella
     */
    public Double haePaketinHinta(int id) {
        try (Session istunto = istuntotehdas.openSession()) {
            istunto.beginTransaction();
            Paketti pak = new Paketti();
            istunto.load(pak, id);
            return pak.getPaketinHinta();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 
     * @return henkilosto palauttaa listan Henkilosto taulun riveistä
     */
    public List<Henkilosto> haeHenkilosto() {
        // TODO Auto-generated method stub
        ArrayList<Henkilosto> henkilosto = new ArrayList<>();
        Session istunto = istuntotehdas.openSession();
        try {
            Transaction transaction = istunto.beginTransaction();
            //@SuppressWarnings("unchecked")
            List<Henkilosto> result = istunto.createQuery("from Henkilosto").list();
            for (Henkilosto v : result) {
                henkilosto.add(new Henkilosto(v.getHenkiloNimi(), v.getRooli()));
            }
            transaction.commit();
            return henkilosto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            istunto.close();
        }
    }
    
    /**
     * 
     * @param nimi - työntekijän nimi
     * @return henkilo palauttaa Henkilosto rivin haetun nimen perusteella
     */
    public Henkilosto haeKayttaja(String nimi) {
        Henkilosto henkilo = new Henkilosto();
        Session istunto = istuntotehdas.openSession();
        try {
            Transaction transaction = istunto.beginTransaction();
            //@SuppressWarnings("unchecked")
            List<Henkilosto> result = istunto.createQuery("from Henkilosto where nimi = '" + nimi + "'").list();
            henkilo = result.get(0);
            transaction.commit();
            
            return henkilo;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        } finally {
            istunto.close();
        }
    }
    
    /**
     * Luo Henkilosto rivin tietokantaan annetusta Henkilosto-oliosta
     * @param henkilo työntekijän nimi
     * @return id palauttaaa työntekijän parametrit Henkilosto-oliosta
     */
    public int luoHenkilo(Henkilosto henkilo) {
        int id = 0;
        
        try (Session istunto = istuntotehdas.openSession()) {
            Transaction transaktio = istunto.beginTransaction();
            //Henkilosto henkilo = new Henkilosto(username, role);
            istunto.save(henkilo);
            //Ota id talteen
            id = henkilo.getHenkiloId();
            
            transaktio.commit();    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    /**
     * Poistaa rivin Henkilosto taulusta annetun Henkilosto olion perusteella
     * @param henkilo työnkekijän nimi
     */
    public void poistaHenkilo(Henkilosto henkilo) {
        try (Session istunto = istuntotehdas.openSession()) {
            Transaction transaktio = istunto.beginTransaction();
            //henkilo = istunto.find(Henkilosto.class, henkilo.getHenkiloId());
            //assertThat(henkilo, notNullValue());
            istunto.delete(henkilo);
            
            transaktio.commit();  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Luo Tilaus rivin, ja asettaa annetun Tilaus_rivi listan oliot viittaamaan siihen
     * @param tilaukset - asikkaiden valmiit tilaukset
     */
    public void luoTilaus(List<Tilaus_rivi> tilaukset) {
        try (Session istunto = istuntotehdas.openSession()) {
            istunto.beginTransaction();
            
            //Luo Tilaus olio
            Tilaus tilaus = new Tilaus(null, null, new Date());
            istunto.saveOrUpdate(tilaus);
            
            //Looppaa tilaus rivejä
            for (Tilaus_rivi tilaus_rivi : tilaukset) {
                //Aseta viiteavain
                tilaus_rivi.setTilaus(tilaus);
                //Tallenna olio
                istunto.save(tilaus_rivi);
            }
            
            istunto.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    /**
     * Palauttaa listan Osa taulun riveistä joiden tyyppi on sama kuin annettulla String muuttujalla
     * @param tyyppi tilauksen tyypi osa tai paketti
     * @return result palauttaa listan Osa taulun riveistä 
     */
    public List<Osa> getOsat(String tyyppi) {
        try (Session istunto = istuntotehdas.openSession()) {
            Transaction transaktio = istunto.beginTransaction();
            List<Osa> result = istunto.createQuery("from Osa where Tyyppi='" + tyyppi + "'").list();
            transaktio.commit();
            return result;
            
        }
    }

}
