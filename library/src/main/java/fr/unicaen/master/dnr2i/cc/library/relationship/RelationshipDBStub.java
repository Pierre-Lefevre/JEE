package fr.unicaen.master.dnr2i.cc.library.relationship;

import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersistentPersonRelationship;
import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersonRelationship;

import java.util.ArrayList;

/**
 * Permet de gérer les enregistrements relatifs aux PersistentRelationship au travers d'un stub base de données.
 *
 * @author Pierre Lefèvre.
 */
public class RelationshipDBStub implements RelationshipDB {

    /**
     * L'id de la dernière PersistentRelationship dans le stub.
     */
    public static int currentId = 2;
    /**
     * Le stub de base de données.
     */
    public static ArrayList<PersistentRelationship> persistentRelationships = new ArrayList<PersistentRelationship>() {{
        add(new PersistentRelationship(0, "ami"));
        add(new PersistentRelationship(1, "père"));
        add(new PersistentRelationship(2, "frère"));
    }};

    /**
     * Permet de recupérer une PersistentRelationship correspondant à une Relationship.
     *
     * @param id           l'id de la PersistentRelationship.
     * @param relationship la Relationship à transformer.
     * @return la PersistentRelationship correspondant à la Relationship.
     */
    public PersistentRelationship relationshipToPersistentRelationship(int id, Relationship relationship) {
        return new PersistentRelationship(id, relationship.getRelation());
    }

    /**
     * Permet de recupérer une PersistentRelationship désignée par son id.
     *
     * @param id l'id de la PersistentRelationship à récupérer.
     * @return la PersistentRelationship recherchée.
     * @throws Exception
     */
    @Override
    public PersistentRelationship retrieve(int id) throws Exception {
        return persistentRelationships.get(id);
    }

    /**
     * Permet de recupérer l'ensemble des PersistentRelationship.
     *
     * @return une ArrayList contenant l'ensemble des PersistentRelationship.
     * @throws Exception
     */
    @Override
    public ArrayList<PersistentRelationship> retrieveAll() throws Exception {
        return persistentRelationships;
    }

    /**
     * Permet d'ajouter une nouvelle Relationship.
     *
     * @param relationship la Relationship à ajouter.
     * @return la PersistentRelationship correspondant à la Relationship précédemment ajoutée.
     * @throws Exception
     */
    @Override
    public PersistentRelationship create(Relationship relationship) throws Exception {
        PersistentRelationship persistentRelationship = relationshipToPersistentRelationship(++currentId, relationship);
        persistentRelationships.add(persistentRelationship);
        return persistentRelationship;
    }

    /**
     * Permet de mettre à jour une PersistentRelationship en se basant sur un id et une Relationship.
     *
     * @param id           l'id de la PersistentRelationship à mettre à jour.
     * @param relationship la Relationship servant de base à la mise à jour d'une PeristentRelationship.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean update(int id, Relationship relationship) throws Exception {
        PersistentRelationship persistentRelationship = retrieve(id);
        if (relationship.getRelation() != null) {
            persistentRelationship.setRelation(relationship.getRelation());
        }
        return true;
    }

    /**
     * Permet de supprimer la PersistentRelationship ayant un certain id.
     *
     * @param id l'id de la PersistentRelationship à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean delete(int id) throws Exception {
        persistentRelationships.remove(id);
        return false;
    }

    /**
     * Permet d'ajouter une nouvelle PersonRelationship.
     *
     * @param personRelationship la PersonRelationship à ajouter.
     * @return la PersistentPersonRelationship correspondant à la PersonRelationship précédemment ajoutée.
     * @throws Exception
     */
    @Override
    public PersistentPersonRelationship createPersonRelationship(PersonRelationship personRelationship) throws Exception {
        return null;
    }

    /**
     * Permet de supprimer la PersistentPersonRelationship ayant un certain id.
     *
     * @param id l'id de la PersistentPersonRelationship à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    @Override
    public boolean deletePersonRelationship(int id) throws Exception {
        return false;
    }
}
