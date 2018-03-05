package fr.unicaen.master.dnr2i.cc.library.person;

import java.util.ArrayList;

/**
 * Permet de gérer les enregistrements relatifs aux PeristentPerson au travers d'un stub base de données.
 *
 * @author Pierre Lefèvre.
 */
public class PersonDBStub implements PersonDB {

    /**
     * L'id de la dernière PersistentPerson dans le stub.
     */
    public static int currentId = 4;
    /**
     * Le stub de base de données.
     */
    public static ArrayList<PersistentPerson> persistentPersons = new ArrayList<PersistentPerson>() {{
        add(new PersistentPerson(0, "Pierre", "Lefèvre", 22));
        add(new PersistentPerson(1, "Solène", "Dorey", 22));
        add(new PersistentPerson(2, "Paul", "Lemenager", 22));
        add(new PersistentPerson(3, "Maxime", "Lainé", 22));
        add(new PersistentPerson(4, "Jeremy", "Habit", 22));
    }};

    /**
     * Permet de recupérer une PersistentPerson correspondant à une Person.
     *
     * @param id     l'id de la PersistentPerson.
     * @param person la Person à transformer.
     * @return la PersistentPerson correspondant à la Person.
     */
    public PersistentPerson personToPersistentPerson(int id, Person person) {
        return new PersistentPerson(id, person.getFirstname(), person.getLastname(), person.getAge());
    }

    /**
     * Permet de recupérer une PersistentPerson désignée par son id.
     *
     * @param id l'id de la PersistentPerson à récupérer.
     * @return la PersistentPerson recherchée.
     * @throws Exception
     */
    @Override
    public PersistentPerson retrieve(int id) throws Exception {
        return persistentPersons.get(id);
    }

    /**
     * Permet de recupérer l'ensemble des PersistentPerson.
     *
     * @return une ArrayList contenant l'ensemble des PersistentPerson.
     * @throws Exception
     */
    @Override
    public ArrayList<PersistentPerson> retrieveAll() throws Exception {
        return persistentPersons;
    }

    /**
     * Permet d'ajouter une nouvelle Person.
     *
     * @param person la Person à ajouter.
     * @return la PersistentPerson correspondant à la Person précédemment ajoutée.
     * @throws Exception
     */
    @Override
    public PersistentPerson create(Person person) throws Exception {
        PersistentPerson persistentPerson = personToPersistentPerson(++currentId, person);
        persistentPersons.add(persistentPerson);
        return persistentPerson;
    }

    /**
     * Permet de mettre à jour une PersistentPerson en se basant sur un id et une Person.
     *
     * @param id     l'id de la PersistentPerson à mettre à jour.
     * @param person la Person servant de base à la mise à jour d'une PeristentPerson.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean update(int id, Person person) throws Exception {
        PersistentPerson persistentPerson = retrieve(id);
        if (person.getFirstname() != null) {
            persistentPerson.setFirstname(person.getFirstname());
        }
        if (person.getLastname() != null) {
            persistentPerson.setLastname(person.getLastname());
        }
        if (person.getAge() != -1) {
            persistentPerson.setAge(person.getAge());
        }
        return true;
    }

    /**
     * Permet de supprimer la PersistentPerson ayant un certain id.
     *
     * @param id l'id de la PersistentPerson à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean delete(int id) throws Exception {
        persistentPersons.remove(id);
        return true;
    }
}
