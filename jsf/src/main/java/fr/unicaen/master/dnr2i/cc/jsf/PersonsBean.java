package fr.unicaen.master.dnr2i.cc.jsf;

import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;

/**
 * Bean JSF permettant de récupérer la liste des PersistentPerson.
 *
 * @author Pierre Lefèvre.
 */
@ManagedBean(name = "personsBean")
@ViewScoped
public class PersonsBean {

    /**
     * Instance d'HibernateBean permettant de dialoguer avec la base données.
     */
    @ManagedProperty(value = "#{hibernateBean}")
    private HibernateBean hibernateBean;

    /**
     * Permet de recupérer l'ensemble des PersistentPerson.
     *
     * @return une ArrayList contenant l'ensemble des PersistentPerson.
     */
    public ArrayList<PersistentPerson> getPersons() {
        try {
            return hibernateBean.getHibernatePersonDB().retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setHibernateBean(HibernateBean hibernateBean) {
        this.hibernateBean = hibernateBean;
    }
}
