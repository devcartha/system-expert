package tn.insat.base_de_connaissances;

import tn.insat.structure.Fait;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Devcartha on 11/23/2015.
 */
public class BFLoader {
    private ArrayList<Fait> baseDeFaits;

    public ArrayList<Fait> getBaseDeFaits() {
        return baseDeFaits;
    }

    public void setBaseDeFaits(File f ) throws IOException {
        this.baseDeFaits = new ArrayList<Fait>();
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String ligne = br.readLine();
        while (ligne.length() > 0) {
            String fait ;
            if (ligne.contains(",")) {
                fait = ligne.substring(0, ligne.indexOf(","));
                ligne = ligne.substring(ligne.indexOf(",") + 1);
            } else {
                fait = ligne;
                ligne = "";
            }
            if (fait.contains("NON"))
                this.baseDeFaits.add(new Fait(fait.substring(ligne.indexOf(" ") + 1), "false", "-1"));
            else
                this.baseDeFaits.add(new Fait(fait, "true", "-1"));

        }
        br.close();
    }

    public void afficherBF(JTextArea output) {
        for (Fait fait : this.baseDeFaits)
            output.append(fait+"\n");
    }
}
