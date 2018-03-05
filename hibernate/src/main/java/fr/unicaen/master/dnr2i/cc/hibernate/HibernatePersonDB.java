package fr.unicaen.master.dnr2i.cc.hibernate;

import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;
import fr.unicaen.master.dnr2i.cc.library.person.Person;
import fr.unicaen.master.dnr2i.cc.library.person.PersonDB;
import org.hibernate.*;

import java.util.ArrayList;

/**
 * Permet de gérer les enregistrements relatifs aux PeristentPerson en base de données, en utilisant l'ORM Hibernate.
 *
 * @author Pierre Lefèvre.
 */
public class HibernatePersonDB extends HibernateDB implements PersonDB {

    /**
     * Constructeur de HibernatePersonDB.
     */
    public HibernatePersonDB() {
        super();
    }

    /**
     * Permet de recupérer une PersistentPerson correspondant à une Person.
     *
     * @param id     l'id de la PersistentPerson.
     * @param person la Person à transformer.
     * @return la PersistentPerson correspondant à la Person.
     */
    public PersistentPerson personToPersistentPerson(int id, Person person) {
        return new PersistentPerson(id, person.getFirstname(), person.getLastname(), person.getAge());
    }

    /**
     * Permet de recupérer une PersistentPerson désignée par son id.
     *
     * @param id l'id de la PersistentPerson à récupérer.
     * @return la PersistentPerson recherchée.
     * @throws Exception
     */
    @Override
    public PersistentPerson retrieve(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            PersistentPerson persistentPerson = (PersistentPerson) session.get(PersistentPerson.class, id);
            transaction.commit();
            return persistentPerson;
        } finally {
            session.close();
        }
    }

    /**
     * Permet de recupérer l'ensemble des PersistentPerson.
     *
     * @return une ArrayList contenant l'ensemble des PersistentPerson.
     * @throws Exception
     */
    @Override
    public ArrayList<PersistentPerson> retrieveAll() throws Exception {
        Session session = this.sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            ArrayList<PersistentPerson> persistentPersons = new ArrayList<PersistentPerson>(session.createQuery("from PersistentPerson").list());
            transaction.commit();
            return persistentPersons;
        } finally {
            session.close();
        }
    }

    /**
     * Permet d'ajouter une nouvelle Person.
     *
     * @param person la Person à ajouter.
     * @return la PersistentPerson correspondant à la Person précédemment ajoutée.
     * @throws Exception
     */
    @Override
    public PersistentPerson create(Person person) throws Exception {
        PersistentPerson persistentPerson = personToPersistentPerson(0, person);
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(persistentPerson);
            transaction.commit();
            return persistentPerson;
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
     * Permet de mettre à jour une PersistentPerson en se basant sur un id et une Person.
     *
     * @param id     l'id de la PersistentPerson à mettre à jour.
     * @param person la Person servant de base à la mise à jour d'une PeristentPerson.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean update(int id, Person person) throws Exception {
        PersistentPerson persistentPerson = retrieve(id);
        if (person.getFirstname() != null) {
            persistentPerson.setFirstname(person.getFirstname());
        }
        if (person.getLastname() != null) {
            persistentPerson.setLastname(person.getLastname());
        }
        if (person.getAge() != -1) {
            persistentPerson.setAge(person.getAge());
        }
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(persistentPerson);
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
     * Permet de supprimer la PersistentPerson ayant un certain id.
     *
     * @param id l'id de la PersistentPerson à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean delete(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            PersistentPerson persistentPerson = (PersistentPerson) session.get(PersistentPerson.class, id);
            if (persistentPerson != null) {
                session.delete(persistentPerson);
            }
            transaction.commit();
            return persistentPerson != null;
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
