package tn.insat.inteface_utilisateur;

import tn.insat.base_de_connaissances.BFLoader;
import tn.insat.base_de_connaissances.BRLoader;
import tn.insat.moteur_d_inference.MoteurDI0;
import tn.insat.structure.Proposition;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Devcartha on 11/23/2015.
 */
public class ConsoleUI {
    public static void main(String []args) throws Exception {
        BFLoader bfLoader = new BFLoader();
        bfLoader.setBaseDeFaits("c://bf.txt");
        bfLoader.afficherBF();
        BRLoader brLoader = new BRLoader();
        brLoader.setBaseDeRegles("c://br.txt");
        MoteurDI0 moteur = new MoteurDI0();
        moteur.chainageMixte(bfLoader.getBaseDeFaits(), brLoader.getBaseDeRegles(),MoteurDI0.SELECTION_REGLE_AYANT_LE_PLUS_DE_PREMISSES,new Proposition("sapin","true"));
        //System.out.println(moteur.terminal(new Proposition("cryptogame","true"),brLoader.getBaseDeRegles()));
        //System.out.println(moteur.observable(new Proposition("fleur","true"),brLoader.getBaseDeRegles()));
        //bfLoader.afficherBF();
    }
}
