package fr.unicaen.master.dnr2i.cc.sql;

import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;
import fr.unicaen.master.dnr2i.cc.library.person.Person;
import fr.unicaen.master.dnr2i.cc.library.person.PersonDB;

import java.sql.*;

import java.util.ArrayList;

/**
 * Permet de gérer les enregistrements relatifs aux PeristentPerson en base de données.
 *
 * @author Pierre Lefèvre.
 */
public class SQLPersonDB extends SQLDB implements PersonDB {

    /**
     * Le nom de la table contenant les PeristentPerson.
     */
    private final static String PERSON_TABLE = "person";

    /**
     * Constructeur de SQLPersonDB.
     */
    public SQLPersonDB() {
        super();
    }

    /**
     * Permet de récupérer une PersistentPerson en se basant sur un curseur.
     *
     * @param rs le cursor contenant les données.
     * @return le PersistentPerson créée en se basant sur le curseur.
     * @throws Exception
     */
    private PersistentPerson createPersistentPersonFromCursor(ResultSet rs) throws Exception {
        return new PersistentPerson(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getInt("age")
        );
    }

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
     * Permet de créer une PreparedStatement permettant de recupérer une PersistentPerson désignée par son id.
     *
     * @param id l'id de la PersistentPerson à récupérer.
     * @return la PreparedStatement.
     * @throws Exception
     */
    private PreparedStatement retrievePreparedStatement(int id) throws Exception {
        String query = "SELECT * FROM " + PERSON_TABLE + " WHERE id = ?";
        PreparedStatement st = getConnection().prepareStatement(query);
        st.setInt(1, id);
        return st;
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
        PreparedStatement st = retrievePreparedStatement(id);
        ResultSet rs = st.executeQuery();
        rs.next();
        PersistentPerson persistentPerson = createPersistentPersonFromCursor(rs);
        rs.close();
        st.close();
        return persistentPerson;
    }

    /**
     * Permet de recupérer l'ensemble des PersistentPerson.
     *
     * @return une ArrayList contenant l'ensemble des PersistentPerson.
     * @throws Exception
     */
    @Override
    public ArrayList<PersistentPerson> retrieveAll() throws Exception {
        ArrayList<PersistentPerson> persistentPersons = new ArrayList<>();
        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + PERSON_TABLE);
        while (rs.next()) {
            persistentPersons.add(createPersistentPersonFromCursor(rs));
        }
        rs.close();
        st.close();
        return persistentPersons;
    }

    /**
     * Permet de créer une PreparedStatement permettant d'ajouter une PersistentPerson.
     *
     * @param person la PersistentPerson à ajouter.
     * @return la PreparedStatement.
     * @throws Exception
     */
    private PreparedStatement createPreparedStatement(PersistentPerson person) throws Exception {
        String query = "INSERT INTO " + PERSON_TABLE + " (firstname, lastname, age) VALUES (?, ?, ?)";
        PreparedStatement st = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, person.getFirstname());
        st.setString(2, person.getLastname());
        st.setInt(3, person.getAge());
        return st;
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
        PersistentPerson persistentPerson = personToPersistentPerson(0, person);
        PreparedStatement st = createPreparedStatement(persistentPerson);
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        persistentPerson.setId(rs.getInt(1));
        rs.close();
        st.close();
        return persistentPerson;
    }

    /**
     * Permet de créer une PreparedStatement permettant de mettre à jour une PersistentPerson.
     *
     * @param person la PersistentPerson à mettre à jour.
     * @return la PreparedStatement.
     * @throws Exception
     */
    private PreparedStatement updatePreparedStatement(PersistentPerson person) throws Exception {
        String query = "UPDATE " + PERSON_TABLE + " SET firstname = ?, lastname = ?, age = ? WHERE id = ?";
        PreparedStatement st = getConnection().prepareStatement(query);
        st.setString(1, person.getFirstname());
        st.setString(2, person.getLastname());
        st.setInt(3, person.getAge());
        st.setInt(4, person.getId());
        return st;
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
        PersistentPerson persistentPerson = personToPersistentPerson(id, person);
        PreparedStatement st = updatePreparedStatement(persistentPerson);
        st.executeUpdate();
        st.close();
        return true;
    }

    /**
     * Permet de créer une PreparedStatement permettant de supprimer une PersistentPerson ayant un certain id.
     *
     * @param id l'id de la PersistentPerson à supprimer.
     * @return la PreparedStatement.
     * @throws Exception
     */
    private PreparedStatement deletePreparedStatement(int id) throws Exception {
        String query = "DELETE FROM " + PERSON_TABLE + " WHERE id = ?";
        PreparedStatement st = getConnection().prepareStatement(query);
        st.setInt(1, id);
        return st;
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
        PreparedStatement st = deletePreparedStatement(id);
        st.executeUpdate();
        st.close();
        return true;
    }
}
