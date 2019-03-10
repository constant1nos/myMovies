/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σεβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package controller;

import entity.Genre;
import java.util.ArrayList;

/**
 *
 * @author Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σεβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
public class GenreController extends MainController{
    
    public GenreController(){
        super();
    } 
    
    /* Αποθήκευση ειδών ταινιών στη βάση δεδομένων */
    public void storeGenresToDataBase(ArrayList<Genre> genres){
        em.getTransaction().begin();
        for (Genre genre : genres) {
            em.persist(genre);
        }
        em.getTransaction().commit();
    }
}
