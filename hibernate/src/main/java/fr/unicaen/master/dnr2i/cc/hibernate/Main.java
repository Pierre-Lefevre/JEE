package fr.unicaen.master.dnr2i.cc.hibernate;

import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;
import fr.unicaen.master.dnr2i.cc.library.person.Person;
import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersonRelationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.PersistentRelationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.Relationship;

import java.util.logging.Level;

/**
 * Classe permettant d'insérer des données dans la base de données.
 *
 * @author Pierre Lefèvre.
 */
public class Main {

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        try {
            Person pierre = new Person("Pierre", "Lefèvre", 22);
            Person maxime = new Person("Maxime", "Lefèvre", 25);
            Person philippe = new Person("Philippe", "Lefèvre", 50);
            Person marieNoelle = new Person("Marie-Noëlle", "Lefèvre", 50);
            Person corentin = new Person("Corentin", "Bellanger", 22);

            Relationship friend = new Relationship("est l'ami de");
            Relationship father = new Relationship("est le père de");
            Relationship brother = new Relationship("est le frère de");
            Relationship mother = new Relationship("est la mère de");
            Relationship son = new Relationship("est le fils de");

            HibernatePersonDB hibernatePersonDB = new HibernatePersonDB();
            HibernateRelationshipDB hibernateRelationshipDB = new HibernateRelationshipDB();

            PersistentPerson persistentPierre = hibernatePersonDB.create(pierre);
            PersistentPerson persistentMaxime = hibernatePersonDB.create(maxime);
            PersistentPerson persistentPhilippe = hibernatePersonDB.create(philippe);
            PersistentPerson persistentMarieNoelle = hibernatePersonDB.create(marieNoelle);
            PersistentPerson persistentCorentin = hibernatePersonDB.create(corentin);

            PersistentRelationship persistentFriend = hibernateRelationshipDB.create(friend);
            PersistentRelationship persistentFather = hibernateRelationshipDB.create(father);
            PersistentRelationship persistentBrother = hibernateRelationshipDB.create(brother);
            PersistentRelationship persistentMother = hibernateRelationshipDB.create(mother);
            PersistentRelationship persistentSon = hibernateRelationshipDB.create(son);

            hibernateRelationshipDB.createPersonRelationship(new PersonRelationship(persistentPierre, persistentMaxime, persistentBrother));
            hibernateRelationshipDB.createPersonRelationship(new PersonRelationship(persistentPhilippe, persistentPierre, persistentFather));
            hibernateRelationshipDB.createPersonRelationship(new PersonRelationship(persistentPhilippe, persistentMaxime, persistentFather));
            hibernateRelationshipDB.createPersonRelationship(new PersonRelationship(persistentMarieNoelle, persistentPierre, persistentMother));
            hibernateRelationshipDB.createPersonRelationship(new PersonRelationship(persistentMarieNoelle, persistentMaxime, persistentMother));
            hibernateRelationshipDB.createPersonRelationship(new PersonRelationship(persistentPierre, persistentCorentin, persistentFriend));
            hibernateRelationshipDB.createPersonRelationship(new PersonRelationship(persistentPierre, persistentPhilippe, persistentSon));
            hibernateRelationshipDB.createPersonRelationship(new PersonRelationship(persistentMaxime, persistentPhilippe, persistentSon));

            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
