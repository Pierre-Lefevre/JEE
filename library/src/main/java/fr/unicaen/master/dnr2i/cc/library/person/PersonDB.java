package fr.unicaen.master.dnr2i.cc.library.person;

import java.util.ArrayList;

/**
 * Interface représentant toutes les opérations relatives à la gestion des PersistentPerson en base de données.
 *
 * @author Pierre Lefèvre.
 */
public interface PersonDB {

    /**
     * Permet de recupérer une PersistentPerson désignée par son id.
     *
     * @param id l'id de la PersistentPerson à récupérer.
     * @return la PersistentPerson recherchée.
     * @throws Exception
     */
    PersistentPerson retrieve(int id) throws Exception;

    /**
     * Permet de recupérer l'ensemble des PersistentPerson.
     *
     * @return une ArrayList contenant l'ensemble des PersistentPerson.
     * @throws Exception
     */
    ArrayList<PersistentPerson> retrieveAll() throws Exception;

    /**
     * Permet d'ajouter une nouvelle Person.
     *
     * @param person la Person à ajouter.
     * @return la PersistentPerson correspondant à la Person précédemment ajoutée.
     * @throws Exception
     */
    PersistentPerson create(Person person) throws Exception;

    /**
     * Permet de mettre à jour une PersistentPerson en se basant sur un id et une Person.
     *
     * @param id     l'id de la PersistentPerson à mettre à jour.
     * @param person la Person servant de base à la mise à jour d'une PeristentPerson.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    boolean update(int id, Person person) throws Exception;

    /**
     * Permet de supprimer la PersistentPerson ayant un certain id.
     *
     * @param id l'id de la PersistentPerson à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     * @throws Exception
     */
    boolean delete(int id) throws Exception;
}
