package fr.unicaen.master.dnr2i.cc.service_servlet;

import fr.unicaen.master.dnr2i.cc.hibernate.HibernatePersonDB;
import fr.unicaen.master.dnr2i.cc.library.person.Person;
import fr.unicaen.master.dnr2i.cc.library.person.PersonDB;
import fr.unicaen.master.dnr2i.cc.library.person.PersistentPerson;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet permettant recupérer une PersistentPerson.
 *
 * @author Pierre Lefèvre.
 */
public class ServletPerson extends HttpServlet {

    /**
     * Instance de PersonDB permettant, ici, la gestion des PersistentPerson par Hibernate.
     */
    private PersonDB personDBStub;

    /**
     * Constructeur de ServletPerson.
     */
    public ServletPerson() {
        personDBStub = new HibernatePersonDB();
    }

    /**
     * Intercepte une requête HTTP GET et affiche une PersistentPerson.
     *
     * @param req la requête.
     * @param res la réponse.
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String[] urlSplit = req.getPathInfo().split("/");
        int personId = Integer.parseInt(urlSplit[1]);
        PersistentPerson person = null;
        try {
            person = personDBStub.retrieve(personId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.setContentType("text/html");
        ServletOutputStream out = res.getOutputStream();
        out.println(person.toString());
    }

    /**
     * Intercepte une requête HTTP POST et met à jour une PersistentPerson.
     *
     * @param req la requête.
     * @param res la réponse.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        String[] urlSplit = req.getPathInfo().split("/");
        int personId = Integer.parseInt(urlSplit[1]);
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        int age = Integer.parseInt(req.getParameter("age"));
        try {
            personDBStub.update(personId, new Person(firstname, lastname, age));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
