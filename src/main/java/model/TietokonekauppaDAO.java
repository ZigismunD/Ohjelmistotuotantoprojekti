/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;

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

    public boolean isSessionFactoryConnected() {
        if (istuntotehdas != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
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
                paketit.add(new Paketti(v.getPaketinNimi(), v.getVarastoMaara(), v.getPaketinHinta()));
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

    public List<Osa> readOsat() {
        // TODO Auto-generated method stub
        ArrayList<Osa> osat = new ArrayList<>();
        Session istunto = istuntotehdas.openSession();
        try {
            Transaction transaction = istunto.beginTransaction();
            //@SuppressWarnings("unchecked")
            List<Osa> result = istunto.createQuery("from Osa").list();
            for (Osa v : result) {
                osat.add(new Osa(v.getOsaNimi(), v.getOsaHinta()));
            }
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
     * @param id - olion haetun id
     * @return result palauttaa paketti olion haetun id:n perusteella
     */
    public Paketti readPaketti(int id) {
        try (Session istunto = istuntotehdas.openSession()) {
            istunto.beginTransaction();
            Paketti pak = new Paketti();
            istunto.load(pak, id);
            return new Paketti(pak.getPaketinNimi(), pak.getVarastoMaara(), pak.getPaketinHinta());
        } catch (Exception e) {
            return null;
        }
    }

    /**
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
     * @param nimi - työntekijän nimi
     * @return henkilo palauttaa Henkilosto rivin haetun nimen perusteella
     */
    public Henkilosto haeKayttaja(String nimi, String salasana) {
        Henkilosto henkilo = new Henkilosto();
        Session istunto = istuntotehdas.openSession();
        try {
            Transaction transaction = istunto.beginTransaction();
            //@SuppressWarnings("unchecked")
            List<Henkilosto> result = istunto.createQuery("from Henkilosto where Kirjautumistunnus = '" + nimi + "' AND Salasana = '" + salasana + "'").list();
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
     *
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

    public void luoOsa(Osa osa) {
        try (Session istunto = istuntotehdas.openSession()) {
            Transaction transaktio = istunto.beginTransaction();
            istunto.save(osa);
            transaktio.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Poistaa rivin Henkilosto taulusta annetun Henkilosto olion perusteella
     *
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
     * Luo Tilaus rivin, ja asettaa annetun Tilaus_rivi listan oliot viittaamaan
     * siihen
     *
     * @param tilaukset - asikkaiden valmiit tilaukset
     */
    public void luoTilaus(List<Tilaus_rivi> tilaukset,Asiakas asiakas, Double hinta) {
        try (Session istunto = istuntotehdas.openSession()) {
            istunto.beginTransaction();
            
         //  Tallenna asiakas
            istunto.save(asiakas);
                        
            //Luo Tilaus olio
            
            Tilaus tilaus = new Tilaus(asiakas, null, new Date(),hinta);
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
     * Palauttaa listan Osa taulun riveistä joiden tyyppi on sama kuin
     * annettulla String muuttujalla
     *
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
    
    public ArrayList tilausGetTilausRivit(Tilaus tilaus) {
        // TODO Auto-generated method stub
        ArrayList<Tilaus_rivi> rivit = new ArrayList<>();
        try (Session istunto = istuntotehdas.openSession()) {
            Transaction transaction = istunto.beginTransaction();
            //@SuppressWarnings("unchecked")
            List<Tilaus_rivi> result = istunto.createQuery("from Tilaus_rivi").list();
            for (Tilaus_rivi v : result) {
                if (v.getTilaus() == tilaus) {
                    rivit.add(v);
                }
            }
            transaction.commit();
            
            return rivit;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Ottaa vastaan id:n ja objektityypin, palauttaa objektin jolla sama id
    public Object getObjectById(int id, Object object) {
        try (Session istunto = istuntotehdas.openSession()) {
            Transaction transaction = istunto.beginTransaction();
            
            istunto.load(object, id);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }
    
    //Hakee ja palauttaa objektin alirivit
    public ArrayList<Object> getObjectRows(Object obj) {
        try (Session istunto = istuntotehdas.openSession()) {
            if (obj instanceof Tilaus) {
                //Hae ja poista tilauksen rivit
                List<Object> result = istunto.createQuery("from Tilaus_rivi where tilaus = " + ((Tilaus) obj).getTilausId()).list();
                ArrayList<Object> tilaus_rivit = new ArrayList<Object>();
                tilaus_rivit.addAll(result);
                return tilaus_rivit;
            } else if (obj instanceof Paketti) {
                //Hae ja poista paketin rivit
                List<Object> result = istunto.createQuery("from Paketti_rivi where id = " + ((Paketti) obj).getPakettiId()).list();
                ArrayList<Object> paketti_rivit = new ArrayList<Object>();
                paketti_rivit.addAll(result);
                return paketti_rivit;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }
    
    //Poistaa objektin tietokannasta. Jos objektilla on alirivejä se poistaa ne myös
    public void objectDelete(Object obj) {
        try (Session istunto = istuntotehdas.openSession()) {
            istunto.beginTransaction();
            
            //Delete main object
            istunto.delete(obj);
            
            //Jos poistettavalla objektilla on aliobjekteja niin poista ne ensin
            if (obj instanceof Tilaus) {
                //Hae ja poista tilauksen rivit
                List<Tilaus_rivi> result = istunto.createQuery("from Tilaus_rivi").list();
                for (Tilaus_rivi listobject : result) {
                    if (listobject.getTilaus().getTilausId() == ((Tilaus) obj).getTilausId()) {
                        istunto.delete(listobject);
                    }
                }
            } else if (obj instanceof Paketti) {
                //Hae ja poista paketin rivit
                List<Paketti_rivi> result = istunto.createQuery("from Paketti_rivi").list();
                for (Paketti_rivi listobject : result) {
                    if (listobject.getPaketti().getPakettiId() == ((Paketti) obj).getPakettiId()) {
                        istunto.delete(listobject);
                    }
                }
            }
            istunto.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Tallentaa objektin ja sen alirivit
    public void objectSaveOrUpdate(Object obj, ArrayList<Object> obj_rows) {
        try (Session istunto = istuntotehdas.openSession()) {
            istunto.beginTransaction();
            
            //Tallenna yksittäinen objekti
            if (obj != null) {
                istunto.saveOrUpdate(obj);
            }
            
            //Tallenna objekti lista jos niitä on annettu
            if (obj_rows != null) {
                for (Object row : obj_rows) {
                    istunto.saveOrUpdate(row);
                }
            }
            
            istunto.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Tallentaa yksittäisen objektin
    public void objectSaveOrUpdate(Object obj) {
        try (Session istunto = istuntotehdas.openSession()) {
            istunto.beginTransaction();
            istunto.saveOrUpdate(obj);
            istunto.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Tallentaa listan objekteja
    public void objectListSaveOrUpdate(ArrayList<Object> obj_list) {
        try (Session istunto = istuntotehdas.openSession()) {
            istunto.beginTransaction();
            for(Object obj : obj_list) {
                istunto.save(obj);
            }
            istunto.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public double[] getSalesYear(Integer year) {
        double[] sales = new double[12];
        try (Session istunto = istuntotehdas.openSession()) {
            Transaction ta = istunto.beginTransaction();
            List<Tilaus> tilausList = istunto.createQuery("from Tilaus").list();
            for (Tilaus dt :
                    tilausList) {

                switch (dt.getTilausPvm().getMonth()) {
                    case Calendar.JANUARY:
                        sales[0] += dt.getYhteishinta();
                        break;
                    case Calendar.FEBRUARY:
                        sales[1] += dt.getYhteishinta();
                        break;
                    case Calendar.MARCH:
                        sales[2] += dt.getYhteishinta();
                        break;
                    case Calendar.APRIL:
                        sales[3] += dt.getYhteishinta();
                        break;
                    case Calendar.MAY:
                        sales[4] += dt.getYhteishinta();
                        break;
                    case Calendar.JUNE:
                        sales[5] += dt.getYhteishinta();
                        break;
                    case Calendar.JULY:
                        sales[6] += dt.getYhteishinta();
                        break;
                    case Calendar.AUGUST:
                        sales[7] += dt.getYhteishinta();
                        break;
                    case Calendar.SEPTEMBER:
                        sales[8] += dt.getYhteishinta();
                        break;
                    case Calendar.OCTOBER:
                        sales[9] += dt.getYhteishinta();
                        break;
                    case Calendar.NOVEMBER:
                        sales[10] += dt.getYhteishinta();
                        break;
                    case Calendar.DECEMBER:
                        sales[11] += dt.getYhteishinta();

                }
            }
            ta.commit();
        }
        return sales;
    }
}
