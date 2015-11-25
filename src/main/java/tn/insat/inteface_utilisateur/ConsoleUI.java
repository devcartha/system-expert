package tn.insat.inteface_utilisateur;

import tn.insat.base_de_connaissances.BFLoader;
import tn.insat.base_de_connaissances.BRLoader;
import tn.insat.moteur_d_inference.MoteurDI0;
import tn.insat.structure.Proposition;

import javax.swing.*;

/**
 * Created by Devcartha on 11/23/2015.
 */
public class ConsoleUI {
    public static void main(String []args) throws Exception {
        UserInterface ui = new UserInterface();
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
