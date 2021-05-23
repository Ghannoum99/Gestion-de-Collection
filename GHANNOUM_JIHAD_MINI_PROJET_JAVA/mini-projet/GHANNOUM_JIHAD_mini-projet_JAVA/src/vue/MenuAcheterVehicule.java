package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import modele.*;

@SuppressWarnings("serial")
public class MenuAcheterVehicule extends MenuAjouterAcheter {
	
	private JLabel labelInfo;
	private JTextField champText;
	private JSpinner numVehicule;
	private JButton boutonAcheter;
	private VehiculeList<Vehicule> vehiculesEnVente =  new VehiculeList<Vehicule>();
	private VehiculeList<Vehicule> vehicules = new VehiculeList<Vehicule>();
	private VehiculeList<Vehicule> vehiculesVendues =  new VehiculeList<Vehicule>();
	private ArrayList<JTextField> listeChampsText = new ArrayList<JTextField>();

	public MenuAcheterVehicule() {
		super();
		vehiculesEnVente.initialiserVehiculeList(vehiculesEnVente, "files/collection_en_vente.txt");
		afficherLabelInfoClient();
		afficherTextField();
		afficherSpinnerNumVehicule();
		afficherBoutonValider();
	}
	
	/*======================================  AFFICHAGE DES LABELS ====================================== */
	
	/*************************************************************************************************************/
	/** AFFICHAGE DES LABELS : CHAQUE LABEL EST ASSOCIE A UN CHAMP DU TEXT (POUR INSERER LES INFOS D'UN CLIENT) **/
	/*************************************************************************************************************/
	public void afficherLabelInfoClient() {
		int y = 35;
		String[] label = {"Num Véhciule :", "Nom :", "Prénom :", "Adresse :", "Ville :"};
		
		for(int i=0; i<5; i++) {
			labelInfo = new JLabel(label[i]);
			labelInfo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
			labelInfo.setBounds(49, y, 126, 22);
			panelPrincipal.add(labelInfo);
			y+=57;
		}

	}
	
	/*======================================  JSpinner ====================================== */
	
	/***********************************************************************************/
	/** AFFICHAGE D'UN SPINNER, QUI PERMET A RECUPERER LE NUMERO DU VEHICULE COMMANDE **/
	/***********************************************************************************/
	public void afficherSpinnerNumVehicule() {
		int min = 0;
		int max = vehiculesEnVente.size();
		
		if(vehiculesEnVente.isEmpty() == false) {
			min = 1;
		}
		numVehicule = new JSpinner();
		numVehicule.setModel(new SpinnerNumberModel(min, min, max, 1));
		numVehicule.setEditor(new JSpinner.DefaultEditor(numVehicule));
		numVehicule.setBounds(193, 37, 159, 23);
		numVehicule.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		panelPrincipal.add(numVehicule);
	}
	
	/*======================================  AFFICHAGE DES CHAMPS DU TEXT ====================================== */
	
	/****************************************************************************/
	/** AFFICHAGE DES CHAMPS DE TEXTE : POUR RECUPERER LES INFORMATIONS D'ACHAT**/
	/****************************************************************************/
	public void afficherTextField() {
		int y = 94;
		int i = 0;
		for(i=0; i<4; i++) {
			champText = new JTextField();
			champText.setBorder(new LineBorder(Color.LIGHT_GRAY));
			champText.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
			champText.setBounds(193, y, 159, 19);
			panelPrincipal.add(champText);
			champText.setColumns(10);
			
			listeChampsText.add(champText);
			
			y += 57;
		}
	}
	
	/*======================================  AFFICHAGE DES BOUTONS ====================================== */
	
	/**************************************************************************************************/
	/** AFFICHAGE DU BOUTON ACHETER, QUI PERMET AU CLIENT DE VALIDER SON ACHAT                       **/
	/** J'AI UTILISE LE POLYMORPHISME ("Même nom de la focntion") MAIS J'AI AFFICHER UN AUTRE BOUTON **/
	/**************************************************************************************************/
	public void afficherBoutonValider() {
		boutonAcheter = new JButton("Acheter");
		boutonAcheter.setBorder(new LineBorder(new Color(70,132,153), 1, true));
		boutonAcheter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acheterVehicule();
				afficherMessageConfirmation();
				dispose();
			}
			
		});
		boutonAcheter.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		boutonAcheter.setForeground(new Color(0, 0, 0));
		boutonAcheter.setBackground(new Color(224,238,238));
		boutonAcheter.setBounds(228, 368, 92, 32);
		panelPrincipal.add(boutonAcheter);
	}
	
	
	/*======================================  MESSAGE D'INFORMATION ====================================== */
	
	/**********************************************************/
	/** AFFICHER UN MESSAGE QUI CONFIRME L'ACHAT DU VEHICULE **/
	/**********************************************************/
	public void afficherMessageConfirmation() {
		/** AFFICHAGE DANS LA CONSOLE **/
		System.out.println("Achat Validé.");
		
		JOptionPane.showMessageDialog(panelPrincipal, "Achat Validé", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	/*======================================  ACHAT D'UN VEHICULE ====================================== */
	
	/************************************************/
	/** FONCTION POUR VALIDER L'ACHAT D'UN VEHICULE**/
	/************************************************/
	public void acheterVehicule() {
		sauvegarderAchat();
		removeVehiculeEnVente();
		removeVehiculeCollection();
	}
	
	/**********************************************************/
	/** ENLEVER LE VEHICULE ACHETE DU FICHIER COLLECTION.TXT **/
	/**********************************************************/
	public void removeVehiculeCollection() {
		vehicules.initialiserVehiculeList(vehicules, "files/collection.txt");
		
		for(Vehicule vehicule :vehicules) {
			if (vehicule == vehiculesVendues.get(0)) {
				vehicules.remove(vehicule);
			}
		}
		
		try {
			FileWriter fw = new FileWriter("files/collection.txt", false);

			for(Vehicule vehicule: vehicules) {
				fw.write(vehicule+ System.lineSeparator());
			}
			fw.close();
				
		}catch (IOException e) {
			System.out.println("An error occurred."); 
		}
	}

	/***************************************************************/
	/** ENLEVER LE VEHICULE ACHETE DU FICHIER collection_en_vente **/
	/***************************************************************/
	public void removeVehiculeEnVente() {
		vehiculesEnVente.remove((Integer)numVehicule.getValue() - 1);
		try {
			FileWriter fw = new FileWriter("files/collection_en_vente.txt", false);

			for(Vehicule vehicule: vehiculesEnVente) {
				fw.write(vehicule+ System.lineSeparator());
			}
			fw.close();
				
		}catch (IOException e) {
			System.out.println("An error occurred."); 
		}	
	}
	
	/*********************************************************************/
	/** AJOUTER LE VEHICULE VENDU DANS LE FICHIER vehicules_vendues.txt **/
	/*********************************************************************/
	public void sauvegarderAchat() {
		try {
			vehiculesVendues.add(vehiculesEnVente.get((Integer)numVehicule.getValue() - 1));
			FileWriter fw = new FileWriter("files/vehicules_vendues.txt", true);
			
			for(Vehicule vehicule: vehiculesVendues) {
				fw.write(vehicule+ System.lineSeparator());
			}
			
			fw.close();
				
		}catch (IOException e) {
			System.out.println("An error occurred.");	
		}
	}
}
