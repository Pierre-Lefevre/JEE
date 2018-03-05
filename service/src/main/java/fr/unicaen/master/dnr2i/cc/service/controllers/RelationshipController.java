package fr.unicaen.master.dnr2i.cc.service.controllers;

import fr.unicaen.master.dnr2i.cc.library.relationship.PersistentRelationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.Relationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.RelationshipDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Controleur Spring permettant de récupérer de informations et d'effectuer des actions relatives aux PersistentRelationship.
 *
 * @author Pierre Lefèvre.
 */
@RestController
public class RelationshipController {

    /**
     * Bean relatif à la gestion des PersistentRelationship par Hibernate.
     */
    @Autowired
    private RelationshipDB relationshipDB;

    /**
     * (GET) Affiche le détail d'une PersistentRelationship.
     *
     * @param id l'id de la PersistentRelationship.
     * @return la PersistentRelationship recherchée.
     */
    @RequestMapping(value = "/relationship/{id}", method = RequestMethod.GET)
    public Relationship getPersonById(@PathVariable int id) {
        try {
            return relationshipDB.retrieve(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * (GET) Liste toutes les PersistentRelationship.
     *
     * @return une ArrayList contenant l'ensemble des PersistentRelationship.
     */
    @RequestMapping(value = "/relationships", method = RequestMethod.GET)
    public ArrayList<PersistentRelationship> getAllRelationships() {
        try {
            return relationshipDB.retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * (POST) Permet d'ajouter une nouvelle PersistentRelationship.
     *
     * @param relation le label de la PersistentRelationship (obligatoire).
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     */
    @RequestMapping(value = "/relationship", method = RequestMethod.POST)
    public boolean addRelationship(@RequestParam String relation) {
        try {
            relationshipDB.create(new Relationship(relation));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * (POST) Permet de mettre à jour une PersistentRelationship en se basant sur un id.
     *
     * @param id       l'id de la PersistentRelationship à mettre à jour.
     * @param relation le label de la PersistentRelationship (optionnel).
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     */
    @RequestMapping(value = "/relationship/{id}", method = RequestMethod.POST)
    public boolean updateRelationship(
            @PathVariable int id,
            @RequestParam(required = false) String relation
    ) {
        try {
            return relationshipDB.update(id, new Relationship(relation));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * (POST) Permet de supprimer la PersistentRelationship ayant un certain id.
     *
     * @param id l'id de la PersistentRelationship à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     */
    @RequestMapping(value = "/relationship/{id}/delete", method = RequestMethod.POST)
    public boolean deleteRelationship(@PathVariable int id) {
        try {
            return relationshipDB.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
