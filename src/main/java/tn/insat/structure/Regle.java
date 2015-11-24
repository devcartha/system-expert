package tn.insat.structure;

import java.util.ArrayList;

/**
 * Created by Devcartha on 11/23/2015.
 */
public class Regle implements Comparable<Regle> {
    private int numero;
    private ArrayList<Proposition> premisses;
    private ArrayList<Proposition> conclusions;

    public Regle() {
    }

    public Regle(int numero, ArrayList<Proposition> premisses, ArrayList<Proposition> conclusions) {
        this.numero = numero;
        this.premisses = premisses;
        this.conclusions = conclusions;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Proposition> getConclusions() {
        return conclusions;
    }

    public void setConclusions(ArrayList<Proposition> conclusions) {
        this.conclusions = conclusions;
    }

    public ArrayList<Proposition> getPremisses() {
        return premisses;
    }

    public void setPremisses(ArrayList<Proposition> premisses) {
        this.premisses = premisses;
    }

    public int compareTo(Regle that) {
        if (this.getPremisses().size() == that.getPremisses().size())
            return 0;
        if (this.getPremisses().size() < that.getPremisses().size())
            return 1;
        return -1;
    }

    @Override
    public String toString() {
        String regle;

        regle = "Regle " + this.numero + " " + this.getPremisses().get(0);
        for (int i = 1; i < this.getPremisses().size(); i++)
            regle += " ET " + this.getPremisses().get(i);
        regle += " -> " + this.getConclusions().get(0);
        for (int i = 1; i < this.getConclusions().size(); i++)
            regle += " ET " + this.getConclusions().get(i);

        return regle;
    }
}
