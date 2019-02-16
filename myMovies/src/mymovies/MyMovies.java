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
        
        // Αρχικοποίηση EntityManagerFactory και EntityManager
        DBManager dbManager = DBManager.getInstance();
        
        new MainMenu().setVisible(true);
    }    
}
