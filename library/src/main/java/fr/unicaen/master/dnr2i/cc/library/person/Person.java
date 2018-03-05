package fr.unicaen.master.dnr2i.cc.library.person;

import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersistentPersonRelationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Représente la classe métier Person.
 *
 * @author Pierre Lefèvre.
 */
public class Person {

    /**
     * Le prénom de la Person.
     */
    protected String firstname;
    /**
     * Le nom de la Person.
     */
    protected String lastname;
    /**
     * L'âge de la Person.
     */
    protected int age;
    /**
     * L'ensemble des relations de Person, dans le sens une Person vers la Person courante.
     */
    protected Set<PersistentPersonRelationship> persistentPersonRelationshipsTo;
    /**
     * L'ensemble des relations de Person, dans le sens la Person courante vers une Person.
     */
    protected Set<PersistentPersonRelationship> persistentPersonRelationshipsFrom;

    /**
     * Constructeur de Person.
     *
     * @param firstname                         le prénom de la Person.
     * @param lastname                          le nom de la Person.
     * @param age                               l'âge de la Person.
     * @param persistentPersonRelationshipsTo   l'ensemble des relations de Person, dans le sens une Person vers la Person courante.
     * @param persistentPersonRelationshipsFrom l'ensemble des relations de Person, dans le sens la Person courante vers une Person.
     */
    public Person(String firstname, String lastname, int age, Set<PersistentPersonRelationship> persistentPersonRelationshipsTo, Set<PersistentPersonRelationship> persistentPersonRelationshipsFrom) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.persistentPersonRelationshipsTo = persistentPersonRelationshipsTo;
        this.persistentPersonRelationshipsFrom = persistentPersonRelationshipsFrom;
    }

    /**
     * Constructeur de Person.
     *
     * @param firstname le prénom de la Person.
     * @param lastname  le nom de la Person.
     * @param age       l'âge de la Person.
     */
    public Person(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    /**
     * Constructeur de Person.
     */
    public Person() {
        firstname = null;
        lastname = null;
        age = -1;
        persistentPersonRelationshipsTo = new HashSet<>();
        persistentPersonRelationshipsFrom = new HashSet<>();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<PersistentPersonRelationship> getPersistentPersonRelationshipsTo() {
        return persistentPersonRelationshipsTo;
    }

    public void setPersistentPersonRelationshipsTo(Set<PersistentPersonRelationship> persistentPersonRelationshipsTo) {
        this.persistentPersonRelationshipsTo = persistentPersonRelationshipsTo;
    }

    public Set<PersistentPersonRelationship> getPersistentPersonRelationshipsFrom() {
        return persistentPersonRelationshipsFrom;
    }

    public void setPersistentPersonRelationshipsFrom(Set<PersistentPersonRelationship> persistentPersonRelationshipsFrom) {
        this.persistentPersonRelationshipsFrom = persistentPersonRelationshipsFrom;
    }

    @Override
    public String toString() {
        return "Person{firstname='" + firstname + "', lastname='" + lastname + "', age=" + age + "}";
    }
}
