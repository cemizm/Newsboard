package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by azad- on 14.11.2016.
 */
public class ViewDAO {
    private EntityManager entityManager;

    ViewDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public View get(int id) {
        return entityManager.find(View.class, id);
    }

    public List<View> getAll() {
        return entityManager.createNamedQuery("View.findAll", View.class).getResultList();
    }

    public void create(View view) {
        if (view == null)
            throw new IllegalArgumentException("Parameter view darf nicht null oder leer sein");

        entityManager.persist(view);
    }

    public View update(View view) {
        if (view == null)
            throw new IllegalArgumentException("Parameter view darf nicht null oder leer sein");

        return entityManager.merge(view);
    }

    public void delete(int id) {
        View view = get(id);
        delete(view);
    }

    public void delete(View view) {
        if (view == null)
            throw new IllegalArgumentException("Parameter view darf nicht null oder leer sein");

        entityManager.remove(view);
    }
}
