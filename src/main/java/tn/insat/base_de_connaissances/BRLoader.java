package tn.insat.base_de_connaissances;

import tn.insat.structure.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Devcartha on 11/23/2015.
 */
public class BRLoader {
    private ArrayList<Regle> baseDeRegles;

    public ArrayList<Regle> getBaseDeRegles() {
        return baseDeRegles;
    }

    public void setBaseDeRegles(String cheminBR) throws IOException {

        this.baseDeRegles = new ArrayList<Regle>();
        File f = new File(cheminBR);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String ligne;
        int i = 1;
        while ((ligne = br.readLine()) != null) {
            ArrayList<Proposition> premissesList = new ArrayList<Proposition>();
            ArrayList<Proposition> conclusionsList = new ArrayList<Proposition>();

            String premisses = ligne.substring(0, ligne.indexOf("->"));
            String conclusions = ligne.substring(ligne.indexOf("->") + 3);

            extrairePropositions(premissesList, premisses);
            extrairePropositions(conclusionsList, conclusions);

            this.baseDeRegles.add(new Regle(i, premissesList, conclusionsList));
            i++;
        }
        br.close();
    }

    private void extrairePropositions(ArrayList<Proposition> propositionsList, String propositions) {
        while (propositions.length() > 0) {
            String proposition;
            if (propositions.contains("ET")) {
                proposition = propositions.substring(0, propositions.indexOf(" ET"));
                propositions = propositions.substring(propositions.indexOf("ET") + 3);
            } else {
                proposition = propositions;
                propositions = "";
            }
            if (proposition.contains("NON"))
                propositionsList.add(new Proposition(proposition.substring(proposition.indexOf(" ") + 1), "false"));
            else
                propositionsList.add(new Proposition(proposition, "true"));
        }
    }

    public void afficherBR() {
        for (Regle regle : this.baseDeRegles)
            System.out.println(regle);
    }
}
