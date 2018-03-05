package fr.unicaen.master.dnr2i.cc.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.InvalidMappingException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Singleton permettant d'assurer l'existance d'une unique instance de SessionFactory dans toute l'application.
 *
 * @author Pierre Lefèvre.
 */
public class SessionFactoryProvider {

    /**
     * Instance unique non préinitialisée de SessionFactory.
     */
    private static SessionFactory sessionFactory = null;

    /**
     * Permet de récupérer l'unique instance de SessionFactory grâce au patron Singleton.
     *
     * @return L'instance de SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Permet de récuper une SessionFactory.
     *
     * @return une instance de SessionFactory.
     * @throws InvalidMappingException
     */
    private static SessionFactory createSessionFactory() throws InvalidMappingException {
        ServiceRegistry serviceRegistry = null;
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            serviceRegistry = builder.build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            System.out.println("Erreur lors de l'initialisation de la session factory: " + e);
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
            throw e;
        }
    }

    /**
     * Permet de fermer la SessionFactory.
     *
     * @throws HibernateException
     */
    private static void closeSessionFactory() throws HibernateException {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
