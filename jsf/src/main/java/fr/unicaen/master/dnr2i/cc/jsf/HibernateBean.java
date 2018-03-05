package fr.unicaen.master.dnr2i.cc.jsf;

import fr.unicaen.master.dnr2i.cc.hibernate.HibernatePersonDB;
import fr.unicaen.master.dnr2i.cc.hibernate.HibernateRelationshipDB;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Bean JSF permettant de dialoguer avec la base données.
 *
 * @author Pierre Lefèvre.
 */
@ManagedBean(name = "hibernateBean")
@ApplicationScoped
public class HibernateBean {

    /**
     * Instance d'HibernatePersonDB permettant de gérer les enregistrements relatifs aux PeristentPerson en base de données.
     */
    private HibernatePersonDB hibernatePersonDB;
    /**
     * Instance d'HibernateRelationshipDB permettant de gérer les enregistrements relatifs aux PeristentRelationship en base de données.
     */
    private HibernateRelationshipDB hibernateRelationshipDB;

    /**
     * Constructeur de HibernateBean.
     */
    public HibernateBean() {
        hibernatePersonDB = new HibernatePersonDB();
        hibernateRelationshipDB = new HibernateRelationshipDB();
    }

    public HibernatePersonDB getHibernatePersonDB() {
        return hibernatePersonDB;
    }

    public HibernateRelationshipDB getHibernateRelationshipDB() {
        return hibernateRelationshipDB;
    }
}
