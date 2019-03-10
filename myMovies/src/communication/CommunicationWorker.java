/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package communication;

import design.*;
import controller.GenreController;
import controller.MovieController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controller.FavoriteListController;
import entity.Genre;
import entity.Movie;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author dinob
 */
/* Δημιουργία κλάσης SwingWorker, η οποία είναι ειδικά σχεδιασμένη για Swing
 * και μας επιτρέπει να εκτελούμε χρονοβόρα tasks σε ένα καινούριο Thread.
 * Με τον τρόπο αυτό αποφεύγουμε το "πάγωμα" της εφαρμογής
 * Περισσότερες πληροφορίες εδώ https://docs.oracle.com/javase/7/docs/api/javax/swing/SwingWorker.html
 */
public class CommunicationWorker extends SwingWorker<String, String>{
    
    private String msg;
    private final RetrieveProgress rp;
    private JLabel progLabel;
    private JProgressBar progBar;
    
    //  Το κλειδί για την επικοινωνία με το API
    private static final String API_KEY = "api_key=bf92a1466e3a994ab59eb0886780f564";
    // Εντολή για ανάκτηση των id των ταινιών
    private static final String GENRE_URL_COMMAND = "https://api.themoviedb.org/3/genre/movie/list?";
    // Εύρεση των ταινιών με συγκεκριμένο id και κυκλοφορία μετά την 01/01/2000
    private static final String MOVIE_URL_COMMAND = "https://api.themoviedb.org/3/discover/movie?with_genres=28|878|10749&primary_release_date.gte=2000-01-01T00:00:00&sort_by=popularity.desc&";
   
    // Αρχικοποίηση μεταβλητών με τον Constructor
    public CommunicationWorker(){
        rp = new RetrieveProgress();
        progBar = rp.getProgressBar();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
        this.addPropertyChangeListener(new PropertyChangeListener() {
        @Override
        public  void propertyChange(PropertyChangeEvent evt) {
            if ("progress".equals(evt.getPropertyName())) {
                 progBar.setValue((Integer)evt.getNewValue());
                }
            }
        });
    }
    
    /* Στην μέθοδο doInBackground γίνεται η εκτέλεση της χρονοβόρας διεργασίας */
    @Override
    protected String doInBackground() throws Exception {
        setProgress(1);         // Στέλνει μηνύματα σχετικά με την πρόοδο του Task (0-100) 
        publish("Αρχικοποίηση...");
        GenreController gc = new GenreController();
        MovieController mc = new MovieController();
        FavoriteListController flc = new FavoriteListController();
        setProgress(10);
        publish("Διαγραφή δεδομένων από τη βάση...");
        // Διαγραφή δεδομένων από τη βάση
        gc.deleteFromDataBase("Genre.deleteAll");
        mc.deleteFromDataBase("Movie.deleteAll");
        flc.deleteFromDataBase("FavoriteList.deleteAll");
        // Αποθήκευση δεδομένων στη βάση
        setProgress(30);
        publish("Ανάκτηση ειδών ταινιών...");
        ArrayList<Genre> genres = getGenres();
        setProgress(40);
        publish("Αποθήκευση ειδών στη βάση δεδομένων...");
        gc.storeGenresToDataBase(genres);
        try {
            setProgress(50);
            publish("Ανάκτηση ταινιών...");
            ArrayList<Movie> movies = getMovies();
            setProgress(90);
            publish("Αποθήκευση ταινιών στη βάση δεδομένων...");
            mc.storeMoviesToDataBase(movies);
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        setProgress(100);
        msg="Η ανάκτηση και αποθήκευση δεδομένων ολοκληρώθηκε";
        publish(msg);
        return msg;
    }
    
    /* Μέθοδος επικοινωνίας με το Thread. Εδώ φτάνουν τα μυνήματα από τη μέθοδο publish */
    @Override
    protected void process(List<String> chunks){
        progLabel = rp.getProgressLabel();
        // Εμφάνιση μηνυμάτων σε μορφή String, σχετικά με την πρόοδο της ανάκτησης και αποθήκευσης δεδομένων
        progLabel.setText(chunks.get(chunks.size()-1));
    }
    
    /* Μέθοδος εκτέλεσης εργασιών αμέσως μετά την ολοκλήρωση του Task στην doInBackground*/
    @Override
    protected void done(){
        rp.setVisible(false);
        rp.dispose();       // Κατάργηση του JFrame RetrieveProgress
        JOptionPane.showMessageDialog(null, msg, "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /* Ανάκτηση ειδών ταινιών από το API */
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
            
            /*Κρατάμε μόνο τους κωδικούς που είναι Action, Romance ή Science Fiction*/
            for(int i = 0; i < genresAPI.size()-1; i++){
                genreName = genresAPI.get(i).getAsJsonObject().get("name").getAsString();
                if(genreName.equals("Action") || genreName.equals("Romance") || genreName.equals("Science Fiction")){
                    Genre genre = new Genre();
                    genre.setId(genresAPI.get(i).getAsJsonObject().get("id").getAsInt());
                    genre.setName(genresAPI.get(i).getAsJsonObject().get("name").getAsString());
                    genres.add(genre);
                }
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(CommunicationWorker.class.getName()).log(Level.SEVERE, "προέκυψε σφάλμα IOException!", ex);
            JOptionPane.showMessageDialog(null, "Η μεταφορά δεδομένων διακόπηκε απρόσμενα", "Σφάλμα σύνδεσης", JOptionPane.ERROR_MESSAGE);
        }
        return genres;
    }
    
    /* Ανάκτηση ταινιών που ανήκουν στα επιθυμητά είδη από το API */
    public ArrayList<Movie> getMovies() throws ParseException{  
        ArrayList<Movie> movies = new ArrayList<Movie>(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JsonArray movieGenres;  // Κρατάει τα είδη στα οποία ανήκει μια ταινία
        int genreIdCounter;     // Χρησιμοποιείται σαν μετρητής σε βρόγχο
        int id;                 // Κρατάει το id της ταινίας
        try 
        {   
            /* 
             * Ανάκτηση δεδομένων ταινιών. Επιτρέπονται max 40 κλήσεις του API ανά 10 sec
             * Έχοντας κάνει ήδη μία κλήση για ανάκτηση των ειδών, μένουν άλλες 39
             * Η κάθε σελίδα περιέχει 20 ταινίες. Επομένως έχουμε συνολικά 780 ταινίες για τη βάση δεδομένων
             */
            for(int i=1; i < 40; i++){
                /* Κατασκευή URL για κάθε σελίδα */
                InputStream is = constructURL(MOVIE_URL_COMMAND+"page="+i+"&"+API_KEY).openStream(); 
                InputStreamReader isr = new InputStreamReader(is);
                JsonElement jElement = new JsonParser().parse(isr);
                JsonObject mainJsonObject = jElement.getAsJsonObject(); 
                JsonArray moviesAPI = mainJsonObject.get("results").getAsJsonArray();
                /* Χρήση EntityManager για ανάγνωση την ειδών των ταινιών */
                DBManager dbm = DBManager.getInstance();
                EntityManager em = dbm.getEm();
                /* Από κάθε σελίδα αποθηκεύουμε τα δεδομένα που χρειαζόμαστε */
                for(int j = 0; j < moviesAPI.size(); j++){
                    Movie movie = new Movie();
                    movie.setId(moviesAPI.get(j).getAsJsonObject().get("id").getAsInt());
                    movie.setTitle(moviesAPI.get(j).getAsJsonObject().get("title").getAsString());
                    movie.setOverview(moviesAPI.get(j).getAsJsonObject().get("overview").getAsString());
                    movie.setRating(moviesAPI.get(j).getAsJsonObject().get("vote_average").getAsDouble());
                    Date date = sdf.parse(moviesAPI.get(j).getAsJsonObject().get("release_date").getAsString());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
                    movie.setReleaseDate(sqlDate);
                    movieGenres = moviesAPI.get(j).getAsJsonObject().get("genre_ids").getAsJsonArray();
                    /* Βρόγχος εύρεσης του πρώτου επιθυμητού id από ένα JsonArray */
                    genreIdCounter = 0; // Αρχικοποίηση μετρητή
                    while(genreIdCounter < movieGenres.size()){
                        id = movieGenres.get(genreIdCounter).getAsInt();
                        if(id == 28 || id == 878 || id == 10749){
                            /* Αποθηκεύουμε το πρώτο id που θα βρούμε */
                            movie.setGenreId(em.find(Genre.class, id));
                            break; // Τερματισμός βρόγχου while
                        }
                        genreIdCounter++;   // επόμενο id
                    }
                    movies.add(movie);      // προσθήκη
                }
                setProgress(50+i);
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(CommunicationWorker.class.getName()).log(Level.SEVERE, "προέκυψε σφάλμα IOException!", ex);
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
            Logger.getLogger(CommunicationWorker.class.getName()).log(Level.SEVERE, "προέκυψε σφάλμα MalformedURLException!", ex);
            JOptionPane.showMessageDialog(null, "Η σύνδεση για ανάκτηση δεδομένων απέτυχε", "Σφάλμα σύνδεσης", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
}
