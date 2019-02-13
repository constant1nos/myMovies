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
 * @author dinob
 */
public class DBManager {
    
    private static final String PERSISTENCE_UNIT = "myMoviesPU";
    private EntityManagerFactory emf;
    private EntityManager em;
    private final String ERROR_MSG = "Αποτυχία σύνδεσης με τη Βάση Δεδομένων!";
    
    private static DBManager dbManager;
    
    private DBManager(){
        try 
            {
                //δημιουργία Entity Manager που θα χρησιμοποιηθεί καθ όλη τη διάρκεια εκτέλεσης της εφαρμογής.
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
                em = emf.createEntityManager();
                System.out.println("Έγινε σύνδεση με τη βάση");
            } 
            catch(Exception e) 
            {
                //System.out.println(e); 
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