package fr.unicaen.master.dnr2i.cc.library.personrelationship;

import fr.unicaen.master.dnr2i.cc.library.relationship.PersistentRelationship;
import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;

/**
 * Représente la classe métier PersistentPersonRelationship.
 *
 * @author Pierre Lefèvre.
 */
public class PersistentPersonRelationship extends PersonRelationship {

    /**
     * L'id de la PersistentPersonRelationship.
     */
    private int id;

    /**
     * Constructeur de PersistentPersonRelationship.
     *
     * @param id                     l'id de la PersistentPersonRelationship.
     * @param persistentPersonFrom   la PersistentPerson en amont de la PersonRelationship.
     * @param persistentPersonTo     la PersistentPerson en aval de la PersonRelationship.
     * @param persistentRelationship la PersistentRelationship qui caractérise cette PersonRelationship.
     */
    public PersistentPersonRelationship(int id, PersistentPerson persistentPersonFrom, PersistentPerson persistentPersonTo, PersistentRelationship persistentRelationship) {
        super(persistentPersonFrom, persistentPersonTo, persistentRelationship);
        this.id = id;
    }

    /**
     * Constructeur de PersistentPersonRelationship.
     */
    public PersistentPersonRelationship() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersistentPersonRelationship{id=" + id + ", idPersistentPersonFrom='" + persistentPersonFrom.getId() + "', idPersistentPersonFrom='" + persistentPersonTo.getId() + "', idPersistentRelationship=" + persistentRelationship.getId() + "}";
    }
}
