/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package controller;

import entity.FavoriteList;
import entity.Genre;
import entity.Movie;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
    
    /* Αναζήτηση με 2 κριτήρια, ώστε να πάρουμε σίγουρα ένα μόνο αποτέλεσμα */
    public Movie getMovieByTtitleAndOverview(String title, String overview){
        Query q = em.createNamedQuery("Movie.findByTitleAndOverview");
        q.setParameter("title", title);
        q.setParameter("overview", overview);
        return (Movie)q.getSingleResult();               
    }
    
    public void updateMovie(int movieId, FavoriteList favoriteList){
        Movie movie = em.find(Movie.class, movieId);
        em.getTransaction().begin();
        movie.setFavoriteListId(favoriteList);
        em.getTransaction().commit();
    }
    
    public ArrayList<Movie> getSelectedMovies(int selectedYear, Genre genre, boolean sorted){
        Query q = em.createNamedQuery("Movie.findYearAndGenre");
        Calendar calendar = new GregorianCalendar(selectedYear,0,1);
        Date date1 = calendar.getTime();
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date date2 = calendar.getTime();
        q.setParameter("date1", date1);
        q.setParameter("date2", date2);
        q.setParameter("genreId", genre);
        // Μετατροπή List σε ArrayList
        ArrayList<Movie> movies = new ArrayList<>(q.getResultList());
        if(sorted){
            Collections.sort(movies, Collections.reverseOrder(Comparator.comparingDouble(Movie::getRating))); 
        }
        return movies;
    } 

    public ArrayList<Movie> getMoviesForList(FavoriteList favoriteList){
        Query q = em.createNamedQuery("Movie.findByFavoriteList");
        q.setParameter("favoriteListId", favoriteList);
        // Μετατροπή List σε ArrayList  
        ArrayList<Movie> movies = new ArrayList<>(q.getResultList());
        return movies;
    } 
 
    /* Μέθοδος ανάκτησης των 10 καλύτερων ταινιών */
    public ArrayList<Movie> getTopTenMovies(){
        Query q = em.createNamedQuery("Movie.findAll");
        List<Movie> movies = q.getResultList();
        // Ταξινόμηση ταινιών κατά φθίνουσα βαθμολογία
        Collections.sort(movies, Collections.reverseOrder(Comparator.comparingDouble(Movie::getRating)));
        ArrayList<Movie> topTenMovies = new ArrayList<>();  
        // Επιστροφή των 10 ταινιών με την υψηλότερη βαθμολογία
        for(int i = 0; i < 10; i++){
            topTenMovies.add(movies.get(i));
        }
        return topTenMovies;
    }
      
}
