/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package controller;

import entity.FavoriteList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author dinob
 */
public class FavoriteListController extends MainController{
    
    public FavoriteListController(){
        super();
    } 
    
    public void storeFavoriteToDataBase(FavoriteList favorite){
        em.getTransaction().begin();
        em.persist(favorite);
        em.getTransaction().commit();
    }
 
    public void deleteFavoriteFromDataBase(String listName){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("FavoriteList.deleteList");
        query.setParameter("name", listName);
        query.executeUpdate();
        em.getTransaction().commit();
    }
    
    public List getFavoriteList(){
        Query query = em.createNamedQuery("FavoriteList.findAll");
        return query.getResultList();
    }
    
    public FavoriteList getFavoriteListByName(String name){
        Query q = em.createNamedQuery("FavoriteList.findByName");
        q.setParameter("name", name);
        return (FavoriteList)q.getSingleResult(); 
    }
}
