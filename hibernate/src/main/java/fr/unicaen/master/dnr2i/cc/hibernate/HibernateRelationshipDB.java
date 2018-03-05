package fr.unicaen.master.dnr2i.cc.hibernate;

import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersistentPersonRelationship;
import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersonRelationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.PersistentRelationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.Relationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.RelationshipDB;
import org.hibernate.*;

import java.util.ArrayList;

/**
 * Permet de gérer les enregistrements relatifs aux PeristentRelationship en base de données, en utilisant l'ORM Hibernate.
 *
 * @author Pierre Lefèvre.
 */
public class HibernateRelationshipDB extends HibernateDB implements RelationshipDB {

    /**
     * Constructeur de HibernateRelationshipDB.
     */
    public HibernateRelationshipDB() {
        super();
    }

    /**
     * Permet de recupérer une PersistentRelationship correspondant à une Relationship.
     *
     * @param id           l'id de la PersistentRelationship.
     * @param relationship la Relationship à transformer.
     * @return la PersistentRelationship correspondant à la Relationship.
     */
    public PersistentRelationship relationshipToPersistentRelationship(int id, Relationship relationship) {
        return new PersistentRelationship(id, relationship.getRelation());
    }

    /**
     * Permet de recupérer une PersistentPersonRelationship correspondant à une PersonRelationship.
     *
     * @param id                 l'id de la PersistentPersonRelationship.
     * @param personRelationship la PersonRelationship à transformer.
     * @return la PersistentPersonRelationship correspondant à la PersonRelationship.
     */
    public PersistentPersonRelationship personRelationshipToPersistentPersonRelationship(int id, PersonRelationship personRelationship) {
        return new PersistentPersonRelationship(id, personRelationship.getPersistentPersonFrom(), personRelationship.getPersistentPersonTo(), personRelationship.getPersistentRelationship());
    }

    /**
     * Permet de recupérer une PersistentRelationship désignée par son id.
     *
     * @param id l'id de la PersistentRelationship à récupérer.
     * @return la PersistentRelationship recherchée.
     * @throws Exception
     */
    @Override
    public PersistentRelationship retrieve(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            PersistentRelationship persistentRelationship = (PersistentRelationship) session.get(PersistentRelationship.class, id);
            transaction.commit();
            return persistentRelationship;
        } finally {
            session.close();
        }
    }

    /**
     * Permet de recupérer l'ensemble des PersistentRelationship.
     *
     * @return une ArrayList contenant l'ensemble des PersistentRelationship.
     * @throws Exception
     */
    @Override
    public ArrayList<PersistentRelationship> retrieveAll() throws Exception {
        Session session = this.sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            ArrayList<PersistentRelationship> persistentRelationships = new ArrayList<PersistentRelationship>(session.createQuery("from PersistentRelationship").list());
            transaction.commit();
            return persistentRelationships;
        } finally {
            session.close();
        }
    }

    /**
     * Permet d'ajouter une nouvelle Relationship.
     *
     * @param relationship la Relationship à ajouter.
     * @return la PersistentRelationship correspondant à la Relationship précédemment ajoutée.
     * @throws Exception
     */
    @Override
    public PersistentRelationship create(Relationship relationship) throws Exception {
        PersistentRelationship persistentRelationship = relationshipToPersistentRelationship(0, relationship);
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(persistentRelationship);
            transaction.commit();
            return persistentRelationship;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Permet de mettre à jour une PersistentRelationship en se basant sur un id et une Relationship.
     *
     * @param id           l'id de la PersistentRelationship à mettre à jour.
     * @param relationship la Relationship servant de base à la mise à jour d'une PeristentRelationship.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean update(int id, Relationship relationship) throws Exception {
        PersistentRelationship persistentRelationship = relationshipToPersistentRelationship(id, relationship);
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(persistentRelationship);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Permet de supprimer la PersistentRelationship ayant un certain id.
     *
     * @param id l'id de la PersistentRelationship à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean delete(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            PersistentRelationship persistentRelationship = (PersistentRelationship) session.get(PersistentRelationship.class, id);
            if (persistentRelationship != null) {
                session.delete(persistentRelationship);
            }
            transaction.commit();
            return persistentRelationship != null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Permet d'ajouter une nouvelle PersonRelationship.
     *
     * @param personRelationship la PersonRelationship à ajouter.
     * @return la PersistentPersonRelationship correspondant à la PersonRelationship précédemment ajoutée.
     * @throws Exception
     */
    @Override
    public PersistentPersonRelationship createPersonRelationship(PersonRelationship personRelationship) throws Exception {
        PersistentPersonRelationship persistentPersonRelationship = personRelationshipToPersistentPersonRelationship(0, personRelationship);
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(persistentPersonRelationship);
            transaction.commit();
            return persistentPersonRelationship;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Permet de supprimer la PersistentPersonRelationship ayant un certain id.
     *
     * @param id l'id de la PersistentPersonRelationship à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean deletePersonRelationship(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            PersistentPersonRelationship persistentPersonRelationship = (PersistentPersonRelationship) session.get(PersistentPersonRelationship.class, id);
            if (persistentPersonRelationship != null) {
                session.delete(persistentPersonRelationship);
            }
            transaction.commit();
            return persistentPersonRelationship != null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
