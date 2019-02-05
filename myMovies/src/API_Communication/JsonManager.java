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
    
    private static final String URL_COMMAND = "https://api.themoviedb.org/3/genre/movie/list?api_key=bf92a1466e3a994ab59eb0886780f564";
    
    public JsonManager(){

        try
        {
         /*κατασκευή ενός URL για το ερώτημα JSON weather now*/
            URL url = new URL(URL_COMMAND);
            
            /*Ξεκινάει τη σύνδεση με τον server και αποθηκεύει τα δεδομένα στη ροή δεδομένων "is".*/          
            InputStream is = url.openStream(); 
            
            /*Διαβάζει τη ροή και μετατρέπει τα εισερχόμενα bytes σε χαρακτήρες.*/
            InputStreamReader isr = new InputStreamReader(is);                              
            
            /*Αναλύει την αρχική δομή του json που βρίσκεται στο isr, και επιστρέφει ένα JsonElement το οποίο μπορεί να είναι
            ένα  JsonObject, JsonArray, JsonPrimitive ή ένα JsonNull.*/
            JsonElement jElement = new JsonParser().parse(isr);
            
            /*εμείς γνωρίζουμε οτι είναι ένα JsonObject οπότε το αποθηκεύουμε σε μια αναφορά mainJsonObject*/   
            if(jElement.isJsonArray()){
                System.out.println("Είναι jsonArray");
            }
            else if(jElement.isJsonObject()) {
                System.out.println("Είναι jsonObject");
            }
            else System.out.println("Είναι κάτι άλλο...");
            
            /*εμείς γνωρίζουμε οτι είναι ένα JsonObject οπότε το αποθηκεύουμε σε μια αναφορά mainJsonObject*/   
            JsonObject mainJsonObject = jElement.getAsJsonObject(); 
            
            JsonArray genres = mainJsonObject.get("genres").getAsJsonArray();
            System.out.println("Έγινε κλήση του API...");
            
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
