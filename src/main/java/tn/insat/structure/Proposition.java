package tn.insat.structure;



/**
 * Created by Devcartha on 11/23/2015.
 */
public class Proposition {
    protected String nom;
    protected String valeur;

    public Proposition() {
        super();
    }

    public Proposition(String nom) {
        this.nom = nom;
    }

    public Proposition(String nom, String valeur) {
        super();
        this.nom = nom.trim();
        this.valeur = valeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Proposition negation() {
        Proposition negation = new Proposition(this.nom);
        if (this.valeur.equals("true"))
            negation.valeur = "false";
        else if (this.valeur.equals("false"))
            negation.valeur = "true";
        return negation;
    }

    @Override
    public String toString() {
        if (this.valeur.equals("true"))
            return this.nom;
        else if (this.valeur.equals("false"))
            return "Non " + this.nom;
        return this.nom + "?";
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (valeur != null ? valeur.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Proposition that = (Proposition) obj;
        return (this.nom.equals(that.nom) && this.valeur.equals(that.valeur));
    }

}
