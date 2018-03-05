package fr.unicaen.master.dnr2i.cc.service_servlet;

import fr.unicaen.master.dnr2i.cc.hibernate.HibernatePersonDB;
import fr.unicaen.master.dnr2i.cc.library.person.PersonDB;
import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet permettant de recupérer l'ensemble des PersistentPerson.
 *
 * @author Pierre Lefèvre.
 */
public class ServletPersons extends HttpServlet {

    /**
     * Instance de PersonDB permettant, ici, la gestion des PersistentPerson par Hibernate.
     */
    private PersonDB personDBStub;

    /**
     * Constructeur de ServletPersons.
     */
    public ServletPersons() {
        personDBStub = new HibernatePersonDB();
    }

    /**
     * Intercepte une requête HTTP GET et affiche l'ensemble des PersistentPerson.
     *
     * @param req la requête.
     * @param res la réponse.
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ArrayList<PersistentPerson> persons = null;
        try {
            persons = personDBStub.retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.setContentType("text/html");
        ServletOutputStream out = res.getOutputStream();
        for (PersistentPerson person : persons) {
            out.println(person.toString());
        }
    }
}
