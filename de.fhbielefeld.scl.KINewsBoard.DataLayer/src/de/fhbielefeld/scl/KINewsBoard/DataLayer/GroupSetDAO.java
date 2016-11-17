package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.GroupSet;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by azad- on 14.11.2016.
 */
public class GroupSetDAO {

    private EntityManager entityManager;

    GroupSetDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public GroupSet get(String id){
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Parameter id darf nicht null oder leer sein");

        return entityManager.find(GroupSet.class, id);
    }

    public List<GroupSet> getAll(){
        return entityManager.createNamedQuery("GroupSet.findAll", GroupSet.class).getResultList();
    }

    public void create(GroupSet groupSet){
        if(groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null oder leer sein");

        entityManager.getTransaction().begin();

        entityManager.persist(groupSet);

        entityManager.getTransaction().commit();
    }

    public GroupSet update(GroupSet groupSet){
        if(groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null oder leer sein");

        entityManager.getTransaction().begin();

        groupSet = entityManager.merge(groupSet);

        entityManager.getTransaction().commit();

        return groupSet;
    }

    public void delete(GroupSet groupSet){
        if(groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null oder leer sein");

        entityManager.getTransaction().begin();

        entityManager.remove(groupSet);

        entityManager.getTransaction().commit();
    }

    public void delete(String id){
        GroupSet groupSet = get(id);
        delete(groupSet);
    }
}
