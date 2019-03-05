/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


/**
 *
 * @author zigis
 */
public class TietokonekauppaDAO {
    SessionFactory istuntotehdas = null;
    final StandardServiceRegistry registry;
    
    public TietokonekauppaDAO() {
        try  {
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
    
    
    
	public Paketti[] readPaketit() {
		// TODO Auto-generated method stub
		ArrayList<Paketti> paketit = new ArrayList<>();
		Session istunto = istuntotehdas.openSession();
		try  {
			Transaction transaction = istunto.beginTransaction();
			//@SuppressWarnings("unchecked")
			List<Paketti> result = istunto.createQuery("from Paketti").list();
			/*for (Paketti v : result) {
				paketit.add(new Paketti(v.getPaketinNimi(), v.getPaketinHinta()));
			}*/
			transaction.commit();

                        
                        for (Paketti paketti : result) {
                            System.out.println(paketti.getPaketinNimi());
                        
                    }
			return paketit.toArray(new Paketti[paketit.size()]);
		} catch (Exception e) {
                    e.printStackTrace();
			
			return null;
		} finally {
                    istunto.close();
                }
		
		
		
		
	}
    
   
    
    
}
