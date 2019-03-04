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
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.metamodel.MetadataSources;

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
        ArrayList<Paketti> paketit = new ArrayList<>();
        Session istunto = istuntotehdas.openSession();
        
        List<Paketti> result = istunto.createQuery("from Paketti").list();
        return null;
    }
    
   
    
    
}
