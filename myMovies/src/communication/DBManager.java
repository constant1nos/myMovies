/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package communication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */

/* Υλοποίηση κλάσης Singleton για δημιουργία EntityManagerFactory 
 * και διαχείριση EntityManager σε Multi-Thread περιβάλλον 
 * Αρχικοποίηση στην αρχή της main για να είναι Thread-Safe 
 */
public class DBManager {
    
    private static final String PERSISTENCE_UNIT = "myMoviesPU";
    // Η κλάση EntityManagerFactory είναι Thread-Safe
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    private EntityManager em;
    private final String ERROR_MSG = "Αποτυχία σύνδεσης με τη Βάση Δεδομένων!";
    
    private static DBManager dbManager;
    
    private DBManager(){
        try 
        {
            if(em == null){
                // δημιουργία Entity Manager
                em = EMF.createEntityManager();
            } 
        }
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(null, ERROR_MSG, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   public static DBManager getInstance(){
       if(dbManager == null){
           dbManager = new DBManager();
       }
       return dbManager;
   }
   
   public EntityManager getEm(){
       return em;
   }
    
}
