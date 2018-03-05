package fr.unicaen.master.dnr2i.cc.sql;

import fr.unicaen.master.dnr2i.cc.library.person.Person;
import fr.unicaen.master.dnr2i.cc.library.relationship.Relationship;

/**
 * Classe permettant de tester des interactions avec la base de données.
 *
 * @author Pierre Lefèvre.
 */
public class Main {

    public static void main(String[] args) {
        SQLPersonDB sqlPersonDB = new SQLPersonDB();
        SQLRelationshipDB sqlRelationshipDB = new SQLRelationshipDB();

        Person person = new Person("Pierre", "Lefèvre", 22);
        Relationship relationship = new Relationship("est l'enseignant de");

        try {
            System.out.println(sqlPersonDB.retrieveAll());
            System.out.println(sqlPersonDB.retrieve(1));
            sqlPersonDB.create(person);
            System.out.println(sqlPersonDB.retrieveAll());
            person.setFirstname("Maxime");
            sqlPersonDB.update(1, person);
            System.out.println(sqlPersonDB.retrieveAll());
            System.out.println(sqlRelationshipDB.retrieveAll());
            sqlRelationshipDB.create(relationship);
            System.out.println(sqlRelationshipDB.retrieveAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
