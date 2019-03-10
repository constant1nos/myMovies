/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σεβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package controller;

import entity.FavoriteList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σεβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
public class FavoriteListController extends MainController{
    
    public FavoriteListController(){
        super();
    } 
    
    /* Αποθήκευση αγαπημένεης λίστας στη βάση δεδομένων */
    public void storeFavoriteToDataBase(FavoriteList favorite){
        em.getTransaction().begin();
        em.persist(favorite);
        em.getTransaction().commit();
    }
 
    /* Διαγραφή αγαπημένεης λίστας από τη βάση δεδομένων */
    public void deleteFavoriteFromDataBase(String listName){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("FavoriteList.deleteList");
        query.setParameter("name", listName);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    /* Αντικατάσταση ονόματος αγαπημένης λίστας */
    public void updateFavoriteListName(FavoriteList favoriteList, String name){
        em.getTransaction().begin();
        favoriteList.setName(name);
        em.getTransaction().commit();
    }
   
    /* Εύρεση όλων των αγαπημένων λιστών */    
    public List getFavoriteList(){
        Query query = em.createNamedQuery("FavoriteList.findAll");
        return query.getResultList();
    }
  
    /* Εύρεση αγαπημένης λίστας με βάση το όνομα */      
    public FavoriteList getFavoriteListByName(String name){
        Query q = em.createNamedQuery("FavoriteList.findByName");
        q.setParameter("name", name);
        return (FavoriteList)q.getSingleResult(); 
    }
}
