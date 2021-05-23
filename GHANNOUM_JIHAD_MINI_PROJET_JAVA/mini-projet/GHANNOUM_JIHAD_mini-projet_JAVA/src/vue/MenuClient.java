package vue;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import modele.Vehicule;
import modele.VehiculeList;

/************************************************************************************************************************/
/* CLASSE HERITANT DE LA CLASSE MenuCollection               									                        */
/* PERMET AUX CLIENTS D'ACHETER DES VEHICULES (AUTO, MOTO, CAMION)                                                      */
/* POUR ELEMINER LE DROIT D'AJOUTER DES VEHICULE A LA COLLECTION, J'AI REMPLACE LE BOUTON AJOUTER PAR ACHETER           */
/************************************************************************************************************************/


@SuppressWarnings("serial")
public class MenuClient extends MenuCollectionnaire {
	private VehiculeList<Vehicule> vehicules = new VehiculeList<Vehicule>();
	public MenuClient() {
		super();
		effacerContenu();
		changerLabelEnVente();
		afficherBoutonAjouter();
	}
	
	/***********************************************************************************************************/
	/** EFFACER LE PANEL COLLECTION : LES CLIENTS N'ONT PAS LE DROIT DE VOIR TOUTE LA COLLECTION DE VEHICULES **/
	/***********************************************************************************************************/
	public void effacerPanelCollection() {
		panelPrincipal.remove(panelCollection);
	}
	
	/****************************/
	/** EFFACER LE PANEL VENDU **/
	/****************************/
	public void effacerPanelVendu() {
		panelPrincipal.remove(panelVendu);
	}
	
	
	/*******************************/
	/** EFFACER BOUTON COLLECTION **/
	/*******************************/
	public void effacerBoutonCollection() {
		boutonCollection.setVisible(false);
	}
	
	/******************************************************************************/
	/** EFFACER BOUTON AJOUTER: C'EST LE BOUTON QUI PERMET D'AJOUTER UN VEHICULE **/
	/******************************************************************************/
	public void effacerBoutonAjouter() {
		boutonAjouter.setVisible(false);
	}
	
	/***************************/
	/** EFFACER BOUTON VENDU  **/
	/***************************/
	public void effacerBoutonVendu() {
		boutonVendu.setVisible(false);
	}
	
	/******************************************************************************************/
	/** CHANGER LE TEXT DU LABEL "En Vente" --> "Nos Véhicules", ET LES COORDONNEES DU LABEL **/
	/******************************************************************************************/
	public void changerLabelEnVente() {
		boutonEnVente.setText("Nos Véhicules");
		boutonEnVente.setBounds(27, 103, 182, 90);
		boutonEnVente.setIcon(new ImageIcon("images/icons8-car-37.png"));
	}
	
	/*********************************************/
	/** FONCTION QUI REGROUPE LES SUPPRESSIONS **/
	/********************************************/
	public void effacerContenu() {
		effacerPanelCollection();
		effacerPanelVendu();
		effacerBoutonCollection();
		effacerBoutonVendu();
	}
	
	/*======================================  AFFICHAGE DES BOUTONS ====================================== */
	
	/******************************************************************************/
	/** AFFICHAGE DU BOUTON ACHETER : PERMET AUX CLIENTS DE VALIDER LEURS ACHATS **/
	/* J'AI UTILISE LE MEME BOUTON QUE DANS LE MENU COLLECTIONAIRE "boutonAjouter */
	/* DEPLUS J'AI CHANGE SES CARACTERISTIQUES                                    */
	/******************************************************************************/
	public void afficherBoutonAjouter() {		
		boutonAjouter = new JButton("");
		boutonAjouter.setBackground(new Color(214, 229, 235));
		boutonAjouter.setBounds(11, 22, 37, 37);
		boutonAjouter.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				vehicules.removeAll(vehicules);
				vehicules.initialiserVehiculeList(vehicules, "files/collection_en_vente.txt");
				if(vehicules.size() != 0) {
					MenuAcheterVehicule frame = new MenuAcheterVehicule();
					frame.show();
				}
				else {
					afficherWarning();
				}
			
			}
		});
		boutonAjouter.setBorder(null);
		boutonAjouter.setIcon(new ImageIcon("images/icons8-acheter-37.png"));
		panelAccueil.add(boutonAjouter);
	}
	
	
	/*======================================  MESSAGE WARNING ====================================== */
	
	/***************************************************************/
	/** AFFICHER UN MESSAGE, S'IL Y A PLUS DES VEHICULES EN VENTE **/
	/***************************************************************/
	public void afficherWarning() {
		/** AFFICHAGE DANS LA CONSOLE **/
		System.out.println("Information : Nous n'avons plus de véhicules, veuillez revenir plus tard\n");
		
		
		JOptionPane.showMessageDialog(panelAccueil, "Nous n'avons plus de véhicules, veuillez revenir plus tard", "Attention", JOptionPane.WARNING_MESSAGE);	
	}

}
