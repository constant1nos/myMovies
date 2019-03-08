/*
 * Created by Batzonis Constantinos
 */
package mymovies;
import communication.DBManager;
import design.MainMenu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinob
 */
public class MyMovies {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Αρχικοποίηση EntityManagerFactory και EntityManager
        DBManager dbManager = DBManager.getInstance();
        
        /* Set Nimbus Look and Feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            //@Override
            public void run() {
                MainMenu frame = null;
                try {
                    frame = new MainMenu();
                } catch (IOException ex) {
                    Logger.getLogger(MyMovies.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }    
}
