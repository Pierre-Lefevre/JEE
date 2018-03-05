package fr.unicaen.master.dnr2i.cc.service.controllers;

import fr.unicaen.master.dnr2i.cc.library.person.Person;
import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;
import fr.unicaen.master.dnr2i.cc.library.person.PersonDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Controleur Spring permettant de récupérer de informations et d'effectuer des actions relatives aux PersistentPerson.
 *
 * @author Pierre Lefèvre.
 */
@RestController
public class PersonController {

    /**
     * Bean relatif à la gestion des PersistentPerson par Hibernate.
     */
    @Autowired
    private PersonDB personDB;

    /**
     * (GET) Affiche le détail d'une PersistentPerson ainsi que toutes ses relations.
     *
     * @param personId l'id de la PersistentPerson.
     * @return la PersistentPerson recherchée.
     */
    @RequestMapping(value = "/person/{personId}", method = RequestMethod.GET)
    public PersistentPerson getPersonById(@PathVariable int personId) {
        try {
            return personDB.retrieve(personId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * (GET) Liste toutes les PersistentPerson avec leurs détails ainsi que toutes leurs relations.
     *
     * @return une ArrayList contenant l'ensemble des PersistentPerson.
     */
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ArrayList<PersistentPerson> getAllPersons() {
        try {
            return personDB.retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * (POST) Permet d'ajouter une nouvelle PersistentPerson.
     *
     * @param firstname le prénom de la PersistentPerson (obligatoire).
     * @param lastname  le nom de la PersistentPerson (obligatoire).
     * @param age       l'âge de la PersistentPerson (obligatoire).
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     */
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public boolean addPerson(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam int age
    ) {
        try {
            personDB.create(new Person(firstname, lastname, age));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * (POST) Permet de mettre à jour une PersistentPerson en se basant sur un id.
     *
     * @param personId  l'id de la PersistentPerson à mettre à jour.
     * @param firstname le prénom de la PersistentPerson (optionnel).
     * @param lastname  le nom de la PersistentPerson (optionnel).
     * @param age       l'âge de la PersistentPerson (optionnel).
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     */
    @RequestMapping(value = "/person/{personId}", method = RequestMethod.POST)
    public boolean updatePerson(
            @PathVariable int personId,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false, defaultValue = "-1") Integer age
    ) {
        try {
            return personDB.update(personId, new Person(firstname, lastname, age));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * (POST) Permet de supprimer la PersistentPerson ayant un certain id.
     *
     * @param personId l'id de la PersistentPerson à supprimer.
     * @return un booléen qui indique si l'opération s'est déroulée correctement.
     */
    @RequestMapping(value = "/person/{personId}/delete", method = RequestMethod.POST)
    public boolean deletePerson(@PathVariable int personId) {
        try {
            return personDB.delete(personId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
