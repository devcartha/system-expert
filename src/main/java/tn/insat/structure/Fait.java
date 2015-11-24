package tn.insat.structure;

/**
 * Created by Devcartha on 11/23/2015.
 */
public class Fait extends Proposition {

    private String meta;

    public Fait() {
    }

    public Fait(String meta) {
        this.meta = meta;
    }

    public Fait(String nom, String meta) {
        super(nom);
        this.meta = meta;
    }

    public Fait(String nom, String valeur, String meta) {
        super(nom, valeur);
        this.meta = meta;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        if (this.meta.equals("-1"))
            return "Fait : " + super.toString();
        else
            return "Fait : " + super.toString() + " obtenu par application de la regle " + this.meta;
    }
}
