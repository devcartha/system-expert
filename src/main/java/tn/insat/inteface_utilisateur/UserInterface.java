/*
 * Created by JFormDesigner on Wed Nov 25 15:43:51 WAT 2015
 */

package tn.insat.inteface_utilisateur;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

import com.intellij.uiDesigner.core.*;
import tn.insat.base_de_connaissances.BFLoader;
import tn.insat.base_de_connaissances.BRLoader;
import tn.insat.moteur_d_inference.MoteurDI0;
import tn.insat.structure.Proposition;

/**
 * @author Souhail Chaouechi
 */
public class UserInterface extends JFrame {

    private BFLoader bfLoader;
    private BRLoader brLoader;
    private MoteurDI0 moteur;

    public UserInterface() {
        this.bfLoader = new BFLoader();
        this.brLoader = new BRLoader();
        this.moteur = new MoteurDI0();
        initComponents();
    }

    private void BaseFaitBtnMouseClicked(MouseEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        try {
            bfLoader.setBaseDeFaits(fileChooser.getSelectedFile());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void BaseRegleBtnMouseClicked(MouseEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        try {
            brLoader.setBaseDeRegles(fileChooser.getSelectedFile());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void AfficherBaseFaitBtnMouseClicked(MouseEvent e) {
        this.bfLoader.afficherBF(traceTA);
    }

    private void AfficherBaseRegleBtnMouseClicked(MouseEvent e) {
        this.brLoader.afficherBR(traceTA);
    }

    private void ChainageAvantBtnMouseClicked(MouseEvent e) {
        String but = butTF.getText();
        Proposition butRecherche;
        int modeChainage;
        int modeResolutionConflit;
        if(this.saturationRB.isSelected()){
            modeChainage=MoteurDI0.SATURATION_DE_LA_BASE_DE_FAITS;
        }else{
            modeChainage=MoteurDI0.ARRET_SI_UN_BUT_EST_PRECISE;
        }
        if(this.selectionPremiereRegleRB.isSelected()){
            modeResolutionConflit=MoteurDI0.SELECTION_PREMIERE_REGLE;
        }else{
            modeResolutionConflit=MoteurDI0.SELECTION_REGLE_AYANT_LE_PLUS_DE_PREMISSES;
        }
        if (but.toUpperCase().contains("NON")) {
            butRecherche = new Proposition(but.substring(but.indexOf(" ") + 1), "false");
            System.out.print(butRecherche);
        } else {
            butRecherche = new Proposition(but, "true");
            System.out.print(butRecherche);
        }
        try {
            this.moteur.chainageAvant(bfLoader.getBaseDeFaits(), brLoader.getBaseDeRegles(), modeChainage, modeResolutionConflit, butRecherche, traceTA);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void ChainageMixteBtnMouseClicked(MouseEvent e) {
        String but = butTF.getText();
        Proposition butRecherche;

        int modeResolutionConflit;
        if(this.selectionPremiereRegleRB.isSelected()){
            modeResolutionConflit=MoteurDI0.SELECTION_PREMIERE_REGLE;
        }else{
            modeResolutionConflit=MoteurDI0.SELECTION_REGLE_AYANT_LE_PLUS_DE_PREMISSES;
        }
        if (but.toUpperCase().contains("NON")) {
            butRecherche = new Proposition(but.substring(but.indexOf(" ") + 1), "false");
        } else {
            butRecherche = new Proposition(but, "true");
        }
        try {
            moteur.chainageMixte(bfLoader.getBaseDeFaits(), brLoader.getBaseDeRegles(), modeResolutionConflit, butRecherche, traceTA);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Souhail Chaouechi
        inputPnl = new JPanel();
        butLbl = new JLabel();
        butTF = new JTextField();
        filePnl = new JPanel();
        baseFaitBtn = new JButton();
        baseRegleBtn = new JButton();
        baseConnaissancePnl = new JPanel();
        afficherBaseFaitBtn = new JButton();
        afficherBaseRegleBtn = new JButton();
        modeChainagePnl = new JPanel();
        saturationRB = new JRadioButton();
        selectionPremiereRegleRB = new JRadioButton();
        arretSurButRB = new JRadioButton();
        selectionRegleAyantPlusPremisseRB = new JRadioButton();
        actionPnl = new JPanel();
        chainageAvantBtn = new JButton();
        chainageMixteBtn = new JButton();
        outputPnl = new JPanel();
        outputSPnl = new JScrollPane();
        traceTA = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));

        //======== inputPnl ========
        {

            // JFormDesigner evaluation mark
            inputPnl.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), inputPnl.getBorder())); inputPnl.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            inputPnl.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));

            //---- butLbl ----
            butLbl.setText("But : ");
            inputPnl.add(butLbl, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
            inputPnl.add(butTF, new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        contentPane.add(inputPnl, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //======== filePnl ========
        {
            filePnl.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));

            //---- baseFaitBtn ----
            baseFaitBtn.setText("Choisir la base de faits");
            baseFaitBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    BaseFaitBtnMouseClicked(e);
                }
            });
            filePnl.add(baseFaitBtn, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- baseRegleBtn ----
            baseRegleBtn.setText("Choisir la base de regles");
            baseRegleBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    BaseRegleBtnMouseClicked(e);
                }
            });
            filePnl.add(baseRegleBtn, new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        contentPane.add(filePnl, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //======== baseConnaissancePnl ========
        {
            baseConnaissancePnl.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));

            //---- afficherBaseFaitBtn ----
            afficherBaseFaitBtn.setText("Afficher la base de faits");
            afficherBaseFaitBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    AfficherBaseFaitBtnMouseClicked(e);
                }
            });
            baseConnaissancePnl.add(afficherBaseFaitBtn, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- afficherBaseRegleBtn ----
            afficherBaseRegleBtn.setText("Afficher la base de regles");
            afficherBaseRegleBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    AfficherBaseRegleBtnMouseClicked(e);
                }
            });
            baseConnaissancePnl.add(afficherBaseRegleBtn, new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        contentPane.add(baseConnaissancePnl, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //======== modeChainagePnl ========
        {
            modeChainagePnl.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));

            //---- saturationRB ----
            saturationRB.setText("Saturer la base de faits");
            modeChainagePnl.add(saturationRB, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- selectionPremiereRegleRB ----
            selectionPremiereRegleRB.setText("Selection de la premiere regle");
            modeChainagePnl.add(selectionPremiereRegleRB, new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- arretSurButRB ----
            arretSurButRB.setText("S'arreter si le but est atteint");
            modeChainagePnl.add(arretSurButRB, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- selectionRegleAyantPlusPremisseRB ----
            selectionRegleAyantPlusPremisseRB.setText("Selection de la regle ayant le plus de premisses");
            modeChainagePnl.add(selectionRegleAyantPlusPremisseRB, new GridConstraints(1, 1, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        contentPane.add(modeChainagePnl, new GridConstraints(4, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //======== actionPnl ========
        {
            actionPnl.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));

            //---- chainageAvantBtn ----
            chainageAvantBtn.setText("Chainage avant");
            chainageAvantBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ChainageAvantBtnMouseClicked(e);
                }
            });
            actionPnl.add(chainageAvantBtn, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- chainageMixteBtn ----
            chainageMixteBtn.setText("Chainage mixte");
            chainageMixteBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ChainageMixteBtnMouseClicked(e);
                }
            });
            actionPnl.add(chainageMixteBtn, new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        contentPane.add(actionPnl, new GridConstraints(5, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //======== outputPnl ========
        {
            outputPnl.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));

            //======== outputSPnl ========
            {
                outputSPnl.setViewportView(traceTA);
            }
            outputPnl.add(outputSPnl, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        contentPane.add(outputPnl, new GridConstraints(6, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        pack();
        setLocationRelativeTo(getOwner());

        //---- ModeChainageGrp ----
        ButtonGroup ModeChainageGrp = new ButtonGroup();
        ModeChainageGrp.add(saturationRB);
        ModeChainageGrp.add(arretSurButRB);

        //---- ModeResolutionConflitGrp ----
        ButtonGroup ModeResolutionConflitGrp = new ButtonGroup();
        ModeResolutionConflitGrp.add(selectionPremiereRegleRB);
        ModeResolutionConflitGrp.add(selectionRegleAyantPlusPremisseRB);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Souhail Chaouechi
    private JPanel inputPnl;
    private JLabel butLbl;
    private JTextField butTF;
    private JPanel filePnl;
    private JButton baseFaitBtn;
    private JButton baseRegleBtn;
    private JPanel baseConnaissancePnl;
    private JButton afficherBaseFaitBtn;
    private JButton afficherBaseRegleBtn;
    private JPanel modeChainagePnl;
    private JRadioButton saturationRB;
    private JRadioButton selectionPremiereRegleRB;
    private JRadioButton arretSurButRB;
    private JRadioButton selectionRegleAyantPlusPremisseRB;
    private JPanel actionPnl;
    private JButton chainageAvantBtn;
    private JButton chainageMixteBtn;
    private JPanel outputPnl;
    private JScrollPane outputSPnl;
    private JTextArea traceTA;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


}
