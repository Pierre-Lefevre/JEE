package fr.unicaen.master.dnr2i.cc.library.person;

import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersistentPersonRelationship;

import java.util.Set;

/**
 * Représente la classe métier PersistentPerson.
 *
 * @author Pierre Lefèvre.
 */
public class PersistentPerson extends Person {

    /**
     * L'id de la PersistentPerson.
     */
    private int id;

    /**
     * Constructeur de PersistentPerson.
     *
     * @param id                                l'id de la PersistentPerson.
     * @param firstname                         le prénom de la Person.
     * @param lastname                          le nom de la Person.
     * @param age                               l'âge de la Person.
     * @param persistentPersonRelationshipsTo   l'ensemble des relations de Person, dans le sens une Person vers la Person courante.
     * @param persistentPersonRelationshipsFrom l'ensemble des relations de Person, dans le sens une Person vers la Person courante.
     */
    public PersistentPerson(int id, String firstname, String lastname, int age, Set<PersistentPersonRelationship> persistentPersonRelationshipsTo, Set<PersistentPersonRelationship> persistentPersonRelationshipsFrom) {
        super(firstname, lastname, age, persistentPersonRelationshipsTo, persistentPersonRelationshipsFrom);
        this.id = id;
    }

    /**
     * Constructeur de PersistentPerson.
     *
     * @param id        l'id de la PersistentPerson.
     * @param firstname le prénom de la Person.
     * @param lastname  le nom de la Person.
     * @param age       l'âge de la Person.
     */
    public PersistentPerson(int id, String firstname, String lastname, int age) {
        super(firstname, lastname, age);
        this.id = id;
    }

    /**
     * Constructeur de PersistentPerson.
     */
    public PersistentPerson() {
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
        return "PersistentPerson{id=" + id + ", firstname='" + firstname + "', lastname='" + lastname + "', age=" + age + "}";
    }
}
