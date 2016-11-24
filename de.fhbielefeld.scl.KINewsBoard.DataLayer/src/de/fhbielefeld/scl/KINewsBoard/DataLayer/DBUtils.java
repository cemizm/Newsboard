package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by azad- on 24.11.2016.
 */
public class DBUtils {

    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("NewsBoardPU").createEntityManager();
    }
}