package tn.insat.moteur_d_inference;

import tn.insat.structure.Fait;
import tn.insat.structure.Proposition;
import tn.insat.structure.Regle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Devcartha on 11/23/2015.
 */
public class MoteurDI0 {
    public final static int SELECTION_PREMIERE_REGLE;
    public final static int SELECTION_REGLE_AYANT_LE_PLUS_DE_PREMISSES;
    public final static int SATURATION_DE_LA_BASE_DE_FAITS;
    public final static int ARRET_SI_UN_BUT_EST_PRECISE;

    static {
        SELECTION_REGLE_AYANT_LE_PLUS_DE_PREMISSES = 1;
        SELECTION_PREMIERE_REGLE = 0;
        SATURATION_DE_LA_BASE_DE_FAITS = 0;
        ARRET_SI_UN_BUT_EST_PRECISE = 1;
    }

    public String chainageAvant(ArrayList<Fait> baseFaits, ArrayList<Regle> baseRegles, int modeChainage, int modeResolutioConflit, Proposition but) throws Exception {
        ArrayList<Regle> ensembleConflit;
        if (modeChainage == MoteurDI0.ARRET_SI_UN_BUT_EST_PRECISE) {
            boolean trouve = (appartient(but, baseFaits) == 0);
            do {
                if (trouve) {
                    return "But trouvé : " + but;
                }
                ensembleConflit = this.getEnsembleConflit(baseFaits, baseRegles);
                if (modeResolutioConflit == MoteurDI0.SELECTION_REGLE_AYANT_LE_PLUS_DE_PREMISSES)
                    Collections.sort(ensembleConflit);
                for (int i = 0; i < ensembleConflit.size(); i++) {
                    Regle regle = ensembleConflit.get(i);
                    System.out.println(declencherRegle(regle, baseRegles, baseFaits));
                    trouve = (appartient(but, baseFaits) == 0);
                }
            } while (!ensembleConflit.isEmpty() && !trouve);
            if (trouve)
                return "But trouvé : " + but;
            else
                return "But non trouvé";
        } else if (modeChainage == MoteurDI0.SATURATION_DE_LA_BASE_DE_FAITS) {
            do {
                ensembleConflit = this.getEnsembleConflit(baseFaits, baseRegles);
                if (modeResolutioConflit == MoteurDI0.SELECTION_REGLE_AYANT_LE_PLUS_DE_PREMISSES)
                    Collections.sort(ensembleConflit);
                for (int i = 0; i < ensembleConflit.size(); i++) {
                    Regle regle = ensembleConflit.get(i);
                    System.out.println(declencherRegle(regle, baseRegles, baseFaits));
                }
            } while (!ensembleConflit.isEmpty());
        }
        return "Base saturée";
    }

    public ArrayList<Regle> getEnsembleConflit(ArrayList<Fait> baseFaits, ArrayList<Regle> baseRegles) {
        ArrayList<Regle> ensembleConflit = new ArrayList<Regle>();
        for (Regle regle : baseRegles) {
            int i = 0;
            while (i < regle.getPremisses().size()) {
                if (this.appartient(regle.getPremisses().get(i), baseFaits) == 0)
                    i++;
                else
                    break;
            }
            if (i == regle.getPremisses().size())
                ensembleConflit.add(regle);
        }
        return ensembleConflit;
    }

    public int appartient(Proposition proposition, ArrayList<Fait> baseFaits) {
        int i = 0;
        while (i < baseFaits.size()) {
            if (baseFaits.contains(proposition)) {
                return 0;
            } else if (baseFaits.contains(proposition.negation())) {
                return -1;
            }
            i++;
        }
        return 1;
    }

    private String declencherRegle(Regle regle, ArrayList<Regle> baseRegles, ArrayList<Fait> baseFaits) {
        baseRegles.remove(regle);
        String traceDeclenchement = "Declenchement de la Regle " + regle.getNumero() + " : Conclusion(s) : ";
        for (Proposition conclusion : regle.getConclusions()) {
            if (appartient(conclusion, baseFaits) == 1) {
                baseFaits.add(
                        new Fait(conclusion.getNom(), conclusion.getValeur(), regle.getNumero() + ""));
                traceDeclenchement += conclusion + " ET ";
            } else if (this.appartient(conclusion, baseFaits) == -1)
                return "Erreur : L'ajout de " + conclusion + " met la base de faits dans un etat non coherent";
        }
        return traceDeclenchement.substring(0, traceDeclenchement.lastIndexOf("ET"));
    }

    public boolean terminal(Proposition proposition, ArrayList<Regle> baseRegles) {
        for (Regle regle : baseRegles) {
            for (Proposition premisse : regle.getPremisses()) {
                if (premisse.equals(proposition))
                    return false;
            }
        }
        return true;
    }

    public boolean observable(Proposition p, ArrayList<Regle> baseRegles) {
        for (Regle regle : baseRegles) {
            for (Proposition conclusion : regle.getConclusions()) {
                if (conclusion.equals(p))
                    return false;
            }
        }
        return true;
    }

    public Regle getReglePresqueDeclenchable(ArrayList<Regle> baseRegles, ArrayList<Fait> baseFaits, ArrayList<String> questions) {
        for (Regle regle : baseRegles) {
            int i = 0, j = 0, k = 0;
            for (Proposition proposition : regle.getPremisses()) {
                if (appartient(proposition, baseFaits) == 0) {
                    i++;
                } else if (appartient(proposition, baseFaits) == 1) {
                    j++;
                }
                if (i > 1) {
                    break;
                }
                k++;
            }
            if ((i == regle.getPremisses().size() - 1) && (j == 1) && (observable(regle.getPremisses().get(k - 1), baseRegles)) && !questions.contains(regle.getPremisses().get(k - 1).getNom())) {
                return regle;
            }
        }
        return null;
    }

    public Proposition getPremisseInconnue(Regle regle, ArrayList<Fait> baseFaits) {
        for (Proposition proposition : regle.getPremisses()) {
            if (appartient(proposition, baseFaits) == 1)
                return proposition;
        }
        return null;
    }

    public void chainageMixte(ArrayList<Fait> baseFaits, ArrayList<Regle> baseRegles, int modeResolutioConflit, Proposition but) throws Exception {
        ArrayList<String> questions = new ArrayList<String>();
        while (true) {
            String resultat = chainageAvant(baseFaits, baseRegles, MoteurDI0.ARRET_SI_UN_BUT_EST_PRECISE, modeResolutioConflit, but);
            if (resultat.equals("But non trouvé") && terminal(but,baseRegles)) {
                Regle regle = getReglePresqueDeclenchable(baseRegles, baseFaits, questions);
                if (regle != null) {

                    Fait faitDemandable = null;
                    Proposition proposition = getPremisseInconnue(regle, baseFaits);
                    questions.add(proposition.getNom());
                    String reponse = poserQuestion(proposition);

                    if (reponse.toUpperCase().equals("OUI")) {
                        faitDemandable = new Fait(proposition.getNom(), "true", "-1");
                    } else if (reponse.toUpperCase().equals("NON")) {
                        faitDemandable = new Fait(proposition.getNom(), "false", "-1");
                    } else {
                        faitDemandable = new Fait(proposition.getNom(), "inconnu", "-1");
                    }
                    if (!faitDemandable.getValeur().equals("iconnu")) {
                        baseFaits.add(faitDemandable);
                        if (proposition.equals(faitDemandable))
                            System.out.println(declencherRegle(regle, baseRegles, baseFaits));
                    }

                } else {
                    System.out.println("But non trouvé");
                    break;
                }
            }else if (!terminal(but,baseRegles)){
                System.out.println("But non trouvé");
                break;
            }
            else if (resultat.contains("But trouvé")) {
                System.out.println(resultat);
                break;
            }
        }
    }

    private String poserQuestion(Proposition proposition) {
        Scanner sc = new Scanner(System.in);
        String reponse;
        do {
            System.out.println(proposition.getNom() + " ? : OUI/NON/INCONNU");
            reponse = sc.next();
        }
        while (!reponse.toUpperCase().equals("OUI") && !reponse.toUpperCase().equals("NON") && !reponse.toUpperCase().equals("INCONNU"));
        return reponse;
    }
}
