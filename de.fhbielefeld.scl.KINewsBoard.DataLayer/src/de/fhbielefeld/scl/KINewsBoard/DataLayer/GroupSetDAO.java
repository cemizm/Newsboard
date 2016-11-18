package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.GroupSet;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by azad- on 14.11.2016.
 */
public class GroupSetDAO {

    private EntityManager entityManager;

    GroupSetDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public GroupSet get(int id) {
        return entityManager.find(GroupSet.class, id);
    }

    public List<GroupSet> getAll() {
        return entityManager.createNamedQuery("GroupSet.findAll", GroupSet.class).getResultList();
    }

    public void create(GroupSet groupSet) {
        if (groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null oder leer sein");

        entityManager.persist(groupSet);
    }

    public GroupSet update(GroupSet groupSet) {
        if (groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null oder leer sein");

        return entityManager.merge(groupSet);
    }

    public void delete(GroupSet groupSet) {
        if (groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null oder leer sein");

        entityManager.remove(groupSet);
    }

    public void delete(int id) {
        GroupSet groupSet = get(id);
        delete(groupSet);
    }
}
