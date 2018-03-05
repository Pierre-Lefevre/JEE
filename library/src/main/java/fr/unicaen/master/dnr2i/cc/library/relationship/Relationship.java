package fr.unicaen.master.dnr2i.cc.library.relationship;

/**
 * Représente la classe métier Relationship.
 *
 * @author Pierre Lefèvre.
 */
public class Relationship {

    /**
     * Le label de la Relationship.
     */
    protected String relation;

    /**
     * Constructeur de Relationship.
     *
     * @param relation le label de la Relationship.
     */
    public Relationship(String relation) {
        this.relation = relation;
    }

    /**
     * Constructeur de Relationship.
     */
    public Relationship() {
        relation = null;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "Relationship{relation='" + relation + "'}'";
    }
}
