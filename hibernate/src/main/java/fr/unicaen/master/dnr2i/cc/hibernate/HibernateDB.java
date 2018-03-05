package fr.unicaen.master.dnr2i.cc.hibernate;

import org.hibernate.SessionFactory;

/**
 * Classe mère de toute les sous-classes Hibernate.
 *
 * @author Pierre Lefèvre.
 */
public abstract class HibernateDB {

    /**
     * Une unique instance de SessionFactory.
     */
    protected SessionFactory sessionFactory;

    /**
     * Constructeur de HibernateDB, récupère une instance unique de SessionFactory.
     */
    public HibernateDB() {
        this.sessionFactory = SessionFactoryProvider.getSessionFactory();
    }
}
