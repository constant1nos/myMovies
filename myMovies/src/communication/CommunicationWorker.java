/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package communication;

import design.*;
import controller.GenreController;
import controller.MovieController;
import design.MainMenu;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author dinob
 */
// Δημιουργία κλάσης SwingWorker, η οποία είναι ειδικά σχεδιασμένη για Swing
// και μας επιτρέπει να εκτελούμε χρονοβόρα tasks σε ένα καινούριο Thread.
// Με τον τρόπο αυτό αποφεύγουμε το "πάγωμα" της εφαρμογής
// Περισσότερες πληροφορίες εδώ https://docs.oracle.com/javase/7/docs/api/javax/swing/SwingWorker.html
public class CommunicationWorker extends SwingWorker<String, String>{
    
    private String msg;
    private final RetrieveProgress rp;
    private JLabel progLabel;
    private JProgressBar progBar;
    
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
    
    @Override
    protected String doInBackground() throws Exception {
        setProgress(1);
        publish("Αρχικοποίηση...");
        GenreController gc = new GenreController();
        MovieController mc = new MovieController();
        setProgress(10);
        publish("Διαγραφή δεδομένων από τη βάση...");
        JsonManager jm = new JsonManager();
        // Διαγραφή δεδομένων από τη βάση
        gc.deleteFromDataBase("Genre.deleteAll");
        mc.deleteFromDataBase("Movie.deleteAll");
        // Αποθήκευση δεδομένων στη βάση
        setProgress(60);
        publish("Ανάκτηση και αποθήκευση ειδών ταινιών...");
        gc.storeGenresToDataBase(jm.getGenres());
        try {
            setProgress(70);
            publish("Ανάκτηση και αποθήκευση ταινιών...");
            mc.storeMoviesToDataBase(jm.getMovies());
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        setProgress(100);
        msg="Η ανάκτηση και αποθήκευση δεδομένων ολοκληρώθηκε";
        return msg;
    }
    
    @Override
    protected void process(List<String> chunks){
        progLabel = rp.getProgressLabel();
        progLabel.setText(chunks.get(chunks.size()-1));
    }
    
    @Override
    protected void done(){
        rp.setVisible(false);
        rp.dispose();
    }
    
}
