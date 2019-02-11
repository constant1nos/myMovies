/*
 * Created by Batzonis Constantinos - Klianis Christos - Servozlidis Giwrgos
 */
package API_Communication;
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


// Άντληση δεδομένων από το API της themoviedb.org
public class JsonManager {
    
    //  Το κλειδί για την επικοινωνία με το API
    private static final String API_KEY = "api_key=bf92a1466e3a994ab59eb0886780f564";
    // Εντολή για ανάκτηση των id των ταινιών
    private static final String GENRE_URL_COMMAND = "https://api.themoviedb.org/3/genre/movie/list?";
    // Εύρεση των ταινιών με συγκεκριμένο id και κυκλοφορία μετά την 01/01/2000
    private static final String MOVIE_URL_COMMAND = "https://api.themoviedb.org/3/discover/movie?with_genres=28|878|10749&primary_release_date.gte=2000-01-01T00:00:00&sort_by=popularity.desc&";
    
    public JsonManager(){

        try
        {
         /*κατασκευή ενός URL για άντληση των διαθέσιμων ειδών ταινιών*/
            URL url = new URL(GENRE_URL_COMMAND+API_KEY);
            
            /*Ξεκινάει τη σύνδεση με τον server και αποθηκεύει τα δεδομένα στη ροή δεδομένων "is".*/          
            InputStream is = url.openStream(); 
            
            /*Διαβάζει τη ροή και μετατρέπει τα εισερχόμενα bytes σε χαρακτήρες.*/
            InputStreamReader isr = new InputStreamReader(is);                              
            
            /*Αναλύει την αρχική δομή του json που βρίσκεται στο isr, και επιστρέφει ένα JsonElement το οποίο μπορεί να είναι
            ένα  JsonObject, JsonArray, JsonPrimitive ή ένα JsonNull.*/
            JsonElement jElement = new JsonParser().parse(isr);
            
            /*εμείς γνωρίζουμε οτι είναι ένα JsonObject οπότε το αποθηκεύουμε σε μια αναφορά mainJsonObject*/   
            JsonObject mainJsonObject = jElement.getAsJsonObject(); 
            
            /*To mainJsonObject περιέχει ένα Array με τα διαθέσιμα είδη ταινιών*/
            JsonArray genres = mainJsonObject.get("genres").getAsJsonArray();
            
            /*Κρατάμε μόνο τους κωδικούς που είναι Action, Romance ή Science Fiction*/
            for(int i = 0; i < genres.size()-1; i++){
                if(genres.get(i).getAsJsonObject().get("name").getAsString().equals("Action")){
                    //TODO: Προσθήκη στη βάση δεδομένων στον πίνακα GENRE. Πρέπει
                    //πρώτα να έχει δημιουργηθεί η entity class
                }
                if(genres.get(i).getAsJsonObject().get("name").getAsString().equals("Romance")){
                    //TODO: Προσθήκη στη βάση δεδομένων στον πίνακα GENRE. Πρέπει
                    //πρώτα να έχει δημιουργηθεί η entity class                    
                }
                if(genres.get(i).getAsJsonObject().get("name").getAsString().equals("Science Fiction")){
                    //TODO: Προσθήκη στη βάση δεδομένων στον πίνακα GENRE. Πρέπει
                    //πρώτα να έχει δημιουργηθεί η entity class                      
                }
            }
            System.out.println("Η ανάκτηση των ειδών ήταν επιτυχής");
            //Ανάκτηση δεδομένων ταινιών. Επιτρέπονται max 40 κλήσεις του API
            //Η κάθε σελίδα επιστρέφει 20 ταινίες. Διαβάζω τις 40 πρώτες σελίδες
            //Συνολικά έχουμε 800 ταινίες για τη βάση δεδομένων
            for(int i=1; i < 40; i++){
                url = new URL(MOVIE_URL_COMMAND+"page="+i+"&"+API_KEY);
                is = url.openStream(); 
                isr = new InputStreamReader(is);
                jElement = new JsonParser().parse(isr);
                mainJsonObject = jElement.getAsJsonObject(); 
                JsonArray movies = mainJsonObject.get("results").getAsJsonArray();
                for(int j = 0; j < movies.size(); j++){
                    //TODO: Αποθήκευση δεδομένων της κάθε ταινίας στη βάση
                    //Πρέπει πρώτα να έχει δημιουργηθεί η entity class
                }
            }
            System.out.println("Η ανάκτηση των ταινιών ήταν επιτυχής");
            JOptionPane.showMessageDialog(null, "Η ανάκτηση των ταινιών ήταν επιτυχής", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, "προέκυψε σφάλμα MalformedURLException!", ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, "προέκυψε σφάλμα IOException!", ex);
        }
    }
}
