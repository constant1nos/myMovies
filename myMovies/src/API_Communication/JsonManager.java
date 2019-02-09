/*
 * Created by Batzonis Constantinos - Klianis Christos - Servozlidis Giwrgos
 */
package API_Communication;

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
    
    private static final String API_KEY = "bf92a1466e3a994ab59eb0886780f564";
    private static final String GENRE_URL_COMMAND = "https://api.themoviedb.org/3/genre/movie/list?api_key=";
    private static final String MOVIE_URL_COMMAND = "https://api.themoviedb.org/3/discover/movie?with_genres=28|878|10749&primary_release_date.gte=2000-01-01T00:00:00&sort_by=release_date.asc&api_key=";
    
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
                   
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
