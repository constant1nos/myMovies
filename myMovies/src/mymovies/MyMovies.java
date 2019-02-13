/*
 * Created by Batzonis Constantinos
 */
package mymovies;
import communication.DBManager;
import design.MainMenu;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author dinob
 */
public class MyMovies {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DBManager dbManager = DBManager.getInstance();
        EntityManager em = dbManager.getEm();
        
        //μέθοδος διαγραφής πίνακα μέσω ενός έτοιμου namedQuery.
        try 
        { 
            em.getTransaction().begin();
            Query query1 = em.createNamedQuery("Genre.deleteAll");
            query1.executeUpdate();
            em.getTransaction().commit();
        } 
        catch (Exception e) 
        { 
            em.getTransaction().rollback();
        }  
        
        new MainMenu().setVisible(true);
    }    
}
