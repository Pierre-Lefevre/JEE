package fr.unicaen.master.dnr2i.cc.jsf;

import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Bean JSF permettant de récupérer le détail d'une PersistentPerson.
 *
 * @author Pierre Lefèvre.
 */
@ManagedBean(name = "personBean")
@RequestScoped
public class PersonBean {

    /**
     * Instance d'HibernateBean permettant de dialoguer avec la base données.
     */
    @ManagedProperty(value = "#{hibernateBean}")
    private HibernateBean hibernateBean;
    /**
     * L'id de la PersistentPerson.
     */
    @ManagedProperty(value = "#{param.id}")
    private int id;
    /**
     * La PersistentPerson.
     */
    private PersistentPerson persistentPerson;

    /**
     * Permet de recupérer une PersistentPerson désignée par son id.
     *
     * @return la PersistentPerson recherchée.
     */
    public PersistentPerson getPerson() {
        try {
            return hibernateBean.getHibernatePersonDB().retrieve(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setHibernateBean(HibernateBean hibernateBean) {
        this.hibernateBean = hibernateBean;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersistentPerson getPersistentPerson() {
        if (persistentPerson == null) {
            persistentPerson = getPerson();
        }
        return persistentPerson;
    }
}
