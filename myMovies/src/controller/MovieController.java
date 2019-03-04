/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package controller;

import entity.Movie;
import java.util.ArrayList;
import javax.persistence.Query;

/**
 *
 * @author dinob
 */
public class MovieController extends MainController{
    
    public MovieController(){
        super();
    } 
    
    public void storeMoviesToDataBase(ArrayList<Movie> movies){
        em.getTransaction().begin();
        for (Movie movie : movies) {
            em.persist(movie);
        }
        em.getTransaction().commit();
    }
    public Movie getMovie(int id){
        Query q = em.createNamedQuery("Movie.findById");
        q.setParameter("id", id);
        return (Movie)q.getSingleResult();
    }    
    
}
