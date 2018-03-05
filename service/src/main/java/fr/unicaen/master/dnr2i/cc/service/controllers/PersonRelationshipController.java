package fr.unicaen.master.dnr2i.cc.service.controllers;

import fr.unicaen.master.dnr2i.cc.library.person.PersonDB;
import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersonRelationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.Relationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.RelationshipDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controleur Spring permettant de récupérer de informations et d'effectuer des actions relatives aux PersistentPersonRelationship.
 *
 * @author Pierre Lefèvre.
 */
@RestController
public class PersonRelationshipController {

    /**
     * Bean relatif à la gestion des PersistentPerson par Hibernate.
     */
    @Autowired
    private PersonDB personDB;
    /**
     * Bean relatif à la gestion des PersistentRelationship par Hibernate.
     */
    @Autowired
    private RelationshipDB relationshipDB;

    /**
     * (POST) Permet d'ajouter une nouvelle PersistentPersonRelationship.
     *
     * @param idPersonFrom l'id de la PersistentPerson en amont de la PersistentPersonRelationship (obligatoire).
     * @param idPersonTo   l'id de la PersistentPerson en aval de la PersistentPersonRelationship (obligatoire).
     * @param idRelation   l'id de la PersistentRelationship qui caractérise cette PersistentPersonRelationship (obligatoire).
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     */
    @RequestMapping(value = "/person-relationship/{idPersonFrom}/{idPersonTo}/{idRelation}", method = RequestMethod.POST)
    public boolean addPersonRelationship(
            @PathVariable int idPersonFrom,
            @PathVariable int idPersonTo,
            @PathVariable int idRelation
    ) {
        try {
            relationshipDB.createPersonRelationship(new PersonRelationship(personDB.retrieve(idPersonFrom), personDB.retrieve(idPersonTo), relationshipDB.retrieve(idRelation)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * (POST) Permet de mettre à jour une PersistentPersonRelationship en se basant sur un id.
     *
     * @param idPersonFrom l'id de la PersistentPerson en amont de la PersistentPersonRelationship (obligatoire).
     * @param idPersonTo   l'id de la PersistentPerson en aval de la PersistentPersonRelationship (obligatoire).
     * @param relation     Le label de la PersistentRelationship (obligatoire).
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     */
    @RequestMapping(value = "/person-relationship/{idPersonFrom}/{idPersonTo}", method = RequestMethod.POST)
    public boolean addPersonRelationship(
            @PathVariable int idPersonFrom,
            @PathVariable int idPersonTo,
            @RequestParam String relation
    ) {
        try {
            relationshipDB.createPersonRelationship(new PersonRelationship(personDB.retrieve(idPersonFrom), personDB.retrieve(idPersonTo), relationshipDB.create(new Relationship(relation))));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * (POST) Permet de supprimer la PersistentPersonRelationship ayant un certain id.
     *
     * @param id l'id de la PersistentPersonRelationship à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     */
    @RequestMapping(value = "/person-relationship/{id}/delete", method = RequestMethod.POST)
    public boolean deletePersonRelationship(@PathVariable int id) {
        try {
            return relationshipDB.deletePersonRelationship(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
