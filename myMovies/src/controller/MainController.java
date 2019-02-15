/*
 * Created by Batzonis Constantinos - Klianis Christos - Servozlidis Giwrgos
 */
package controller;

import communication.DBManager;
import javax.persistence.EntityManager;

/**
 *
 * @author dinob
 */
public abstract class MainController {
    
    protected EntityManager em;
    
    public MainController(){
        DBManager dbManager = DBManager.getInstance();
        em = dbManager.getEm();
    }
    
}
