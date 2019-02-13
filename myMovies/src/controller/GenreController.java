/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package controller;

import entity.Genre;
import java.util.ArrayList;

/**
 *
 * @author dinob
 */
public class GenreController extends MainController{
    
    public GenreController(){
        super();
    } 
    
    public void storeGenresToDataBase(ArrayList<Genre> genres){
        em.getTransaction().begin();
        for (Genre genre : genres) {
            em.persist(genre);
        }
        em.getTransaction().commit();
    }
}
