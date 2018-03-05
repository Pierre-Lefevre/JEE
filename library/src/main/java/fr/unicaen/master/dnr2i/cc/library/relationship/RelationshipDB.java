package fr.unicaen.master.dnr2i.cc.library.relationship;

import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersistentPersonRelationship;
import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersonRelationship;

import java.util.ArrayList;

/**
 * Interface représentant toutes les opérations relatives à la gestion des PersistentRelationship en base de données.
 *
 * @author Pierre Lefèvre.
 */
public interface RelationshipDB {

    /**
     * Permet de recupérer une PersistentRelationship désignée par son id.
     *
     * @param id l'id de la PersistentRelationship à récupérer.
     * @return la PersistentRelationship recherchée.
     * @throws Exception
     */
    PersistentRelationship retrieve(int id) throws Exception;

    /**
     * Permet de recupérer l'ensemble des PersistentRelationship.
     *
     * @return une ArrayList contenant l'ensemble des PersistentRelationship.
     * @throws Exception
     */
    ArrayList<PersistentRelationship> retrieveAll() throws Exception;

    /**
     * Permet d'ajouter une nouvelle Relationship.
     *
     * @param relationship la Relationship à ajouter.
     * @return la PersistentRelationship correspondant à la Relationship précédemment ajoutée.
     * @throws Exception
     */
    PersistentRelationship create(Relationship relationship) throws Exception;

    /**
     * Permet de mettre à jour une PersistentRelationship en se basant sur un id et une Relationship.
     *
     * @param id           l'id de la PersistentRelationship à mettre à jour.
     * @param relationship la Relationship servant de base à la mise à jour d'une PeristentRelationship.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    boolean update(int id, Relationship relationship) throws Exception;

    /**
     * Permet de supprimer la PersistentRelationship ayant un certain id.
     *
     * @param id l'id de la PersistentRelationship à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    boolean delete(int id) throws Exception;

    /**
     * Permet d'ajouter une nouvelle PersonRelationship.
     *
     * @param personRelationship la PersonRelationship à ajouter.
     * @return la PersistentPersonRelationship correspondant à la PersonRelationship précédemment ajoutée.
     * @throws Exception
     */
    PersistentPersonRelationship createPersonRelationship(PersonRelationship personRelationship) throws Exception;

    /**
     * Permet de supprimer la PersistentPersonRelationship ayant un certain id.
     *
     * @param id l'id de la PersistentPersonRelationship à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    boolean deletePersonRelationship(int id) throws Exception;
}
