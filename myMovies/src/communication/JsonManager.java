/*
 * Created by Batzonis Constantinos - Klianis Christos - Servozlidis Giwrgos
 */
package communication;
import javax.swing.JOptionPane;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Genre;
import entity.Movie;
import static entity.Movie_.genreId;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;


// Άντληση δεδομένων από το API της themoviedb.org
public class JsonManager {
    
    //  Το κλειδί για την επικοινωνία με το API
    private static final String API_KEY = "api_key=bf92a1466e3a994ab59eb0886780f564";
    // Εντολή για ανάκτηση των id των ταινιών
    private static final String GENRE_URL_COMMAND = "https://api.themoviedb.org/3/genre/movie/list?";
    // Εύρεση των ταινιών με συγκεκριμένο id και κυκλοφορία μετά την 01/01/2000
    private static final String MOVIE_URL_COMMAND = "https://api.themoviedb.org/3/discover/movie?with_genres=28|878|10749&primary_release_date.gte=2000-01-01T00:00:00&sort_by=popularity.desc&";
    
    public JsonManager(){    
    }

    public ArrayList<Genre> getGenres(){
    String genreName;
    ArrayList<Genre> genres = new ArrayList<Genre>(3);
        try
        {
            /*κατασκευή ενός URL για άντληση των διαθέσιμων ειδών ταινιών*/
            /*Ξεκινάει τη σύνδεση με τον server και αποθηκεύει τα δεδομένα στη ροή δεδομένων "is".*/          
            InputStream is = constructURL(GENRE_URL_COMMAND+API_KEY).openStream(); 
            
            /*Διαβάζει τη ροή και μετατρέπει τα εισερχόμενα bytes σε χαρακτήρες.*/
            InputStreamReader isr = new InputStreamReader(is);                              
            
            /*Αναλύει την αρχική δομή του json που βρίσκεται στο isr, και επιστρέφει ένα JsonElement το οποίο μπορεί να είναι
            ένα  JsonObject, JsonArray, JsonPrimitive ή ένα JsonNull.*/
            JsonElement jElement = new JsonParser().parse(isr);
            
            /*εμείς γνωρίζουμε οτι είναι ένα JsonObject οπότε το αποθηκεύουμε σε μια αναφορά mainJsonObject*/   
            JsonObject mainJsonObject = jElement.getAsJsonObject(); 
            
            /*To mainJsonObject περιέχει ένα Array με τα διαθέσιμα είδη ταινιών*/
            JsonArray genresAPI = mainJsonObject.get("genres").getAsJsonArray();
            
            //DBManager dbManager = DBManager.getInstance();
            /*Κρατάμε μόνο τους κωδικούς που είναι Action, Romance ή Science Fiction*/
            for(int i = 0; i < genresAPI.size()-1; i++){
                genreName = genresAPI.get(i).getAsJsonObject().get("name").getAsString();
                if(genreName.equals("Action") || genreName.equals("Romance") || genreName.equals("Science Fiction")){
                    Genre genre = new Genre();
                    genre.setId(genresAPI.get(i).getAsJsonObject().get("id").getAsInt());
                    genre.setName(genresAPI.get(i).getAsJsonObject().get("name").getAsString());
                    genres.add(genre);
                    Genre g = new Genre();
                    //dbManager.getEm().getTransaction().begin();
                    //g.setId(genresAPI.get(i).getAsJsonObject().get("id").getAsInt());
                    //g.setName(genresAPI.get(i).getAsJsonObject().get("name").getAsString());
                    //dbManager.getEm().persist(g);
                    //System.out.println(genresAPI.get(i).getAsJsonObject().get("id").getAsInt());
                    //dbManager.getEm().getTransaction().commit();
                }
            }
            System.out.println("Η ανάκτηση των ειδών ήταν επιτυχής");
        }
        catch (IOException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, "προέκυψε σφάλμα IOException!", ex);
            JOptionPane.showMessageDialog(null, "Η μεταφορά δεδομένων διακόπηκε απρόσμενα", "Σφάλμα σύνδεσης", JOptionPane.ERROR_MESSAGE);
        }
        return genres;
    }
             
    public ArrayList<Movie> getMovies() throws ParseException{        
        ArrayList<Movie> movies = new ArrayList<Movie>(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try 
        {   
            //Ανάκτηση δεδομένων ταινιών. Επιτρέπονται max 40 κλήσεις του API
            //Η κάθε σελίδα επιστρέφει 20 ταινίες. Διαβάζω τις 40 πρώτες σελίδες
            //Συνολικά έχουμε 800 ταινίες για τη βάση δεδομένων
            for(int i=1; i < 40; i++){
                /* Κατασκευή URL για κάθε σελίδα */
                InputStream is = constructURL(MOVIE_URL_COMMAND+"page="+i+"&"+API_KEY).openStream(); 
                InputStreamReader isr = new InputStreamReader(is);
                JsonElement jElement = new JsonParser().parse(isr);
                JsonObject mainJsonObject = jElement.getAsJsonObject(); 
                JsonArray moviesAPI = mainJsonObject.get("results").getAsJsonArray();                
                DBManager dbm = DBManager.getInstance();
                EntityManager em = dbm.getEm();
                Genre genre = em.find(Genre.class, 28);
                /* Από κάθε σελίδα αποθηκεύουμε τα δεδομένα που χρειαζόμαστε*/
                for(int j = 0; j < moviesAPI.size(); j++){
                    Movie movie = new Movie();
                    movie.setId(moviesAPI.get(j).getAsJsonObject().get("id").getAsInt());
                    movie.setTitle(moviesAPI.get(j).getAsJsonObject().get("title").getAsString());
                    movie.setOverview(moviesAPI.get(j).getAsJsonObject().get("overview").getAsString());
                    movie.setRating(moviesAPI.get(j).getAsJsonObject().get("vote_average").getAsDouble());
                    Date date = sdf.parse(moviesAPI.get(j).getAsJsonObject().get("release_date").getAsString());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
                    movie.setReleaseDate(sqlDate);
                    movie.setGenreId(genre);
                    movies.add(movie);
                }
            }
            System.out.println("Η ανάκτηση των ταινιών ήταν επιτυχής");
            JOptionPane.showMessageDialog(null, "Η ανάκτηση των ταινιών ήταν επιτυχής", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, "προέκυψε σφάλμα IOException!", ex);
            JOptionPane.showMessageDialog(null, "Η μεταφορά δεδομένων διακόπηκε απρόσμενα", "Σφάλμα σύνδεσης", JOptionPane.ERROR_MESSAGE);
        }
        return movies;
    }
    
    /* κατασκευή ενός URL και έλεγχος */
    private URL constructURL(String text){
        try
        {
            URL url = new URL(text);
            return url;
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, "προέκυψε σφάλμα MalformedURLException!", ex);
            JOptionPane.showMessageDialog(null, "Η σύνδεση για ανάκτηση δεδομένων απέτυχε", "Σφάλμα σύνδεσης", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
