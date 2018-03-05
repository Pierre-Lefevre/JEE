package fr.unicaen.master.dnr2i.cc.library.personrelationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.unicaen.master.dnr2i.cc.library.relationship.PersistentRelationship;
import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;

/**
 * Représente la classe métier PersonRelationship.
 *
 * @author Pierre Lefèvre.
 */
public class PersonRelationship {

    /**
     * La PersistentPerson en amont de la PersonRelationship.
     */
    @JsonIgnoreProperties({"persistentPersonRelationshipsTo", "persistentPersonRelationshipsFrom"})
    protected PersistentPerson persistentPersonFrom;
    /**
     * La PersistentPerson en aval de la PersonRelationship.
     */
    @JsonIgnoreProperties({"persistentPersonRelationshipsTo", "persistentPersonRelationshipsFrom"})
    protected PersistentPerson persistentPersonTo;
    /**
     * La PersistentRelationship qui caractérise cette PersonRelationship.
     */
    protected PersistentRelationship persistentRelationship;

    /**
     * Constructeur de PersonRelationship.
     *
     * @param persistentPersonFrom   la PersistentPerson en amont de la PersonRelationship.
     * @param persistentPersonTo     la PersistentPerson en aval de la PersonRelationship.
     * @param persistentRelationship la PersistentRelationship qui caractérise cette PersonRelationship.
     */
    public PersonRelationship(PersistentPerson persistentPersonFrom, PersistentPerson persistentPersonTo, PersistentRelationship persistentRelationship) {
        this.persistentPersonFrom = persistentPersonFrom;
        this.persistentPersonTo = persistentPersonTo;
        this.persistentRelationship = persistentRelationship;
    }

    /**
     * Constructeur de PersonRelationship.
     */
    public PersonRelationship() {
        persistentPersonFrom = null;
        persistentPersonTo = null;
        persistentRelationship = null;
    }

    public PersistentPerson getPersistentPersonFrom() {
        return persistentPersonFrom;
    }

    public void setPersistentPersonFrom(PersistentPerson persistentPersonFrom) {
        this.persistentPersonFrom = persistentPersonFrom;
    }

    public PersistentPerson getPersistentPersonTo() {
        return persistentPersonTo;
    }

    public void setPersistentPersonTo(PersistentPerson persistentPersonTo) {
        this.persistentPersonTo = persistentPersonTo;
    }

    public PersistentRelationship getPersistentRelationship() {
        return persistentRelationship;
    }

    public void setPersistentRelationship(PersistentRelationship persistentRelationship) {
        this.persistentRelationship = persistentRelationship;
    }
}
