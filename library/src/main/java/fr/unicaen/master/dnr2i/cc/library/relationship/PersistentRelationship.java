package fr.unicaen.master.dnr2i.cc.library.relationship;

/**
 * Représente la classe métier PersistentRelationship.
 *
 * @author Pierre Lefèvre.
 */
public class PersistentRelationship extends Relationship {

    /**
     * L'id de la PersistentRelationship.
     */
    private int id;

    /**
     * Constructeur de PersistentRelationship.
     *
     * @param id       l'id de la PersistentRelationship.
     * @param relation le label de la Relationship.
     */
    public PersistentRelationship(int id, String relation) {
        super(relation);
        this.id = id;
    }

    /**
     * Constructeur de PersistentRelationship.
     */
    public PersistentRelationship() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersistentRelationship{id=" + id + ", relation='" + relation + "'}'";
    }
}
