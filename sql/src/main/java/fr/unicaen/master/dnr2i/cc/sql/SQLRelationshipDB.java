package fr.unicaen.master.dnr2i.cc.sql;

import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersistentPersonRelationship;
import fr.unicaen.master.dnr2i.cc.library.personrelationship.PersonRelationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.PersistentRelationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.Relationship;
import fr.unicaen.master.dnr2i.cc.library.relationship.RelationshipDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Permet de gérer les enregistrements relatifs aux PeristentRelationship en base de données.
 *
 * @author Pierre Lefèvre.
 */
public class SQLRelationshipDB extends SQLDB implements RelationshipDB {

    /**
     * Le nom de la table contenant les PeristentRelationship.
     */
    private final static String RELATIONSHIP_TABLE = "relationship";

    /**
     * Constructeur de SQLRelationshipDB.
     */
    public SQLRelationshipDB() {
        super();
    }

    /**
     * Permet de récupérer une PersistentRelationship en se basant sur un curseur.
     *
     * @param rs le cursor contenant les données.
     * @return le PersistentRelationship créée en se basant sur le curseur.
     * @throws SQLException
     */
    private PersistentRelationship createPersistentRelationshipFromCursor(ResultSet rs) throws SQLException {
        return new PersistentRelationship(
                rs.getInt("id"),
                rs.getString("relation")
        );
    }

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
     * Permet de créer une PreparedStatement permettant de recupérer une PersistentRelationship désignée par son id.
     *
     * @param id l'id de la PersistentRelationship à récupérer.
     * @return la PreparedStatement.
     * @throws SQLException
     */
    private PreparedStatement retrievePreparedStatement(int id) throws SQLException {
        String query = "SELECT * FROM " + RELATIONSHIP_TABLE + " WHERE id = ?";
        PreparedStatement st = getConnection().prepareStatement(query);
        st.setInt(1, id);
        return st;
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
        PreparedStatement st = retrievePreparedStatement(id);
        ResultSet rs = st.executeQuery();
        rs.next();
        PersistentRelationship persistentRelationship = createPersistentRelationshipFromCursor(rs);
        rs.close();
        st.close();
        return persistentRelationship;
    }

    /**
     * Permet de recupérer l'ensemble des PersistentRelationship.
     *
     * @return une ArrayList contenant l'ensemble des PersistentRelationship.
     * @throws Exception
     */
    @Override
    public ArrayList<PersistentRelationship> retrieveAll() throws Exception {
        ArrayList<PersistentRelationship> persistentRelationships = new ArrayList<>();
        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + RELATIONSHIP_TABLE);
        while (rs.next()) {
            persistentRelationships.add(createPersistentRelationshipFromCursor(rs));
        }
        rs.close();
        st.close();
        return persistentRelationships;
    }

    /**
     * Permet de créer une PreparedStatement permettant d'ajouter une PersistenttRelationship.
     *
     * @param relationship la PersistentRelationship à ajouter.
     * @return la PreparedStatement.
     * @throws SQLException
     */
    private PreparedStatement createPreparedStatement(PersistentRelationship relationship) throws SQLException {
        String query = "INSERT INTO " + RELATIONSHIP_TABLE + " (relation) VALUES (?)";
        PreparedStatement st = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, relationship.getRelation());
        return st;
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
        PersistentRelationship persistentRelationship = relationshipToPersistentRelationship(0, relationship);
        PreparedStatement st = createPreparedStatement(persistentRelationship);
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        persistentRelationship.setId(rs.getInt(1));
        rs.close();
        st.close();
        return persistentRelationship;
    }

    /**
     * Permet de créer une PreparedStatement permettant de mettre à jour une PersistentRelationship.
     *
     * @param relationship la PersistentRelationship à mettre à jour.
     * @return la PreparedStatement.
     * @throws SQLException
     */
    private PreparedStatement updatePreparedStatement(PersistentRelationship relationship) throws SQLException {
        String query = "UPDATE " + RELATIONSHIP_TABLE + " SET relation = ? WHERE id = ?";
        PreparedStatement st = getConnection().prepareStatement(query);
        st.setString(1, relationship.getRelation());
        st.setInt(2, relationship.getId());
        return st;
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
        PersistentRelationship persistentRelationship = relationshipToPersistentRelationship(id, relationship);
        PreparedStatement st = updatePreparedStatement(persistentRelationship);
        st.executeUpdate();
        st.close();
        return true;
    }

    /**
     * Permet de créer une PreparedStatement permettant de supprimer une PersistentRelationship ayant un certain id.
     *
     * @param id l'id de la Relationship à supprimer.
     * @return la PreparedStatement.
     * @throws SQLException
     */
    private PreparedStatement deletePreparedStatement(int id) throws SQLException {
        String query = "DELETE FROM " + RELATIONSHIP_TABLE + " WHERE id = ?";
        PreparedStatement st = getConnection().prepareStatement(query);
        st.setInt(1, id);
        return st;
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
        PreparedStatement st = deletePreparedStatement(id);
        st.executeUpdate();
        st.close();
        return true;
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
