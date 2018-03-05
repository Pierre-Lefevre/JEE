package fr.unicaen.master.dnr2i.cc.sql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe mère de toute les sous-classes permettant d'interagir avec la base de données.
 *
 * @author Pierre Lefèvre.
 */
public class SQLDB {

    /**
     * L'hôte de la base de données.
     */
    private static final String HOST = "localhost";
    /**
     * Le nom de la base de données.
     */
    private static final String DATABASE = "j2ee";
    /**
     * L'identifiant de connexion à la base de données.
     */
    private static final String USERNAME = "root";
    /**
     * Le mot de passe de connexion à la base de données.
     */
    private static final String PASSWORD = "";

    /**
     * Une unique instance de Connection.
     */
    private static Connection connection = null;

    /**
     * Permet de récupérer l'unique instance de Connection grâce au patron Singleton.
     *
     * @return L'instance de Connection.
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = createLink(HOST, DATABASE, USERNAME, PASSWORD);
        }
        return connection;
    }

    /**
     * Permet d'établir la connexion.
     *
     * @param host     l'hôte de la base de données.
     * @param database le nom de la base de données.
     * @param username l'identifiant de connexion à la base de données.
     * @param password le mot de passe de connexion à la base de données.
     * @return la connexion à la base données.
     * @throws SQLException
     */
    public static Connection createLink(String host, String database, String username, String password) throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(host);
        ds.setDatabaseName(database);
        Connection link = ds.getConnection(username, password);
        if (!link.isValid(0)) {
            throw new SQLException("Failed to initialize a valid connection to database.");
        }
        return link;
    }
}
