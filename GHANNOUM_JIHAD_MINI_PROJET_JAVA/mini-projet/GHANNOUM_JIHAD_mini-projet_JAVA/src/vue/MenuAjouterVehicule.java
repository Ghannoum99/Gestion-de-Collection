package vue;


import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

import modele.*;

@SuppressWarnings("serial")
public class MenuAjouterVehicule extends MenuAjouterAcheter {
	private JLabel labelInfo;
	private JTextField champText;
	private JComboBox<String> typeVehicule;
	private JRadioButton boutonEnVente;
	private JButton boutonValider;
	private VehiculeList<Vehicule> vehicules =  new VehiculeList<Vehicule>();
	private ArrayList<JTextField> listeChampsText = new ArrayList<JTextField>();

	public MenuAjouterVehicule() {
		super();
		
		afficherLabel();
		afficherComboTypeVehicule();
		afficherTextField();
		afficherBoutonEnVente();
		afficherBoutonValider();
	}
	
	
	/**************************/
	/** AFFICHAGE DES LABELS **/
	/**************************/
	public void afficherLabel() {
		int y = 35;
		String[] label = {"Type de Véhicule :", "Marque :", "Modèle :", "Couleur :", "Prix :", "En Vente :"};
		
		for(int i=0; i<6; i++) {
			labelInfo = new JLabel(label[i]);
			labelInfo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
			labelInfo.setBounds(49, y, 126, 22);
			panelPrincipal.add(labelInfo);
			y+=57;
		}
		
	}
	
	/************************************************************/
	/** AFFICHAGE UN COMBOBOX POUR CHOISIR LE TYPE DU VEHICULE **/
	/************************************************************/
	public void afficherComboTypeVehicule() {
		typeVehicule = new JComboBox<String>();
		typeVehicule.setBounds(193, 37, 159, 21);
		typeVehicule.setModel(new DefaultComboBoxModel<String>(new String[] {"Auto", "Moto", "Camion"}));
		typeVehicule.setSelectedItem(null);
		panelPrincipal.add(typeVehicule);
	}
	
	/***********************************/
	/** AFFICHAGE DES CHAMPS DE TEXTE **/
	/***********************************/
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
	
	/****************************************/
	/** AFFICHAGE DU RADIO BUTTON EN VENTE **/
	/****************************************/
	public void afficherBoutonEnVente() {
		boutonEnVente = new JRadioButton("");
		boutonEnVente.setBackground(new Color(253,245,230));
		boutonEnVente.setBounds(193, 322, 103, 21);
		panelPrincipal.add(boutonEnVente);
	}
	
	/**************************************************************************************/
	/** AFFICHAGE DU BOUTON VALIDER, QUI PERMET LE COLLECTIONNAIRE D'AJOUTER LE VEHICULE **/
	/**************************************************************************************/
	public void afficherBoutonValider() {
		boutonValider = new JButton("Valider");
		boutonValider.setBorder(new LineBorder(new Color(70,132,153), 1, true));
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeVehicule.getSelectedIndex() > -1 | typeVehicule.getSelectedIndex() > -1 ) {
					/** ADDING Vehicles **/
					ajouterVehiculeList();
					sauvegarderVehicule();
					
					if (boutonEnVente.isSelected()) {
						mettreVehiculeEnVente();
						/** AFFICHAGE DANS LA CONSOLE **/
						System.out.println("Le véhicule est en Vente.");
					}
					
					/** AFFICHAGE DANS LA CONSOLE **/
					afficherVehicule();
					
					dispose();
					afficherMessageConfirmation();
				}
				else {
					afficherWarning();
				}
				

			}
		});
		boutonValider.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		boutonValider.setForeground(new Color(0, 0, 0));
		boutonValider.setBackground(new Color(224,238,238));
		boutonValider.setBounds(228, 368, 92, 32);
		panelPrincipal.add(boutonValider);
	}
	
	/*****************************************************/
	/** AFFICHER LA LISTE DES VEHICULES DANS LA CONSOLE **/
	/*****************************************************/
	public void afficherVehicule() {
		for(Vehicule vehicule: vehicules) {
			System.out.println(vehicule);
		}
	}
	
	
	
	/*****************************************************************/
	/** AJOUTER UN VEHICULE A LA LISTE DES VEHICULES "VehiculeList" **/
	/*****************************************************************/
	public void ajouterVehiculeList() {
		ArrayList<String> attributVehicule = new ArrayList<String>();
		String typeValue;
		
		for(int i=0; i<listeChampsText.size(); i++) {
			attributVehicule.add((String)listeChampsText.get(i).getText());
		}
		
		typeValue = (String) typeVehicule.getSelectedItem();
		switch(typeValue) {
		case "Auto" :
			Auto auto = new Auto(attributVehicule.get(0), attributVehicule.get(1), attributVehicule.get(2), attributVehicule.get(3));
			vehicules.add(auto);
			break;

		case "Moto":
			Moto moto = new Moto(attributVehicule.get(0), attributVehicule.get(1), attributVehicule.get(2), attributVehicule.get(3));
			vehicules.add(moto);
			break;
				
		case "Camion":
			Camion camion = new Camion(attributVehicule.get(0), attributVehicule.get(1), attributVehicule.get(2), attributVehicule.get(3));
			vehicules.add(camion);					
			break;
		}
		
	}
	
	/*****************************************************************/
	/** AFFICHER UN MESSAGE QUI CONFIRME QUE LE VEHICULE EST AJOUTE **/
	/*****************************************************************/
	public void afficherMessageConfirmation() {
		/** AFFICHAGE DANS LA CONSOLE **/
		System.out.println("Le Véhicule est ajouté.");
		
		JOptionPane.showMessageDialog(panelPrincipal, "Le Véhicule est ajouté!", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/*************************************************************************************************************/
	/** AFFICHER UN MESSAGE "WARNING" SI LE COLLECTIONNAIRE N'A PAS CHOSI LE TYPE DE VEHICULE QU'IL VEUT AJOUTER**/
	/*************************************************************************************************************/
	public void afficherWarning() {
		JOptionPane.showMessageDialog(panelPrincipal, "Veuillez sélectionner le type de véhicule", "Attention", JOptionPane.WARNING_MESSAGE);	
			 
		/** AFFICHAGE DANS LA CONSOLE **/
		System.out.println("Warning : Veuillez sélectionner le type de véhicule!\n");
	}
	
	/*****************************************************/
	/** AJOUTER VEHICULE DANS LE FICHIER COLLECTION.TXT **/
	/*****************************************************/
	
	public void sauvegarderVehicule() {
		try {
			FileWriter fw = new FileWriter("files/collection.txt", true);
			for(Vehicule vehicule: vehicules) {
				fw.write(vehicule+ System.lineSeparator());
			}
			fw.close();
				
		}catch (IOException e) {
			System.out.println("An error occurred.");	
		}	
	}

	/**********************************/
	/** METTANT LE VEHICULE EN VENTE **/
	/**********************************/

	public void mettreVehiculeEnVente() {
		try {
			FileWriter fw = new FileWriter("files/collection_en_vente.txt", true);

			for(Vehicule vehicule: vehicules) {
				fw.write(vehicule+ System.lineSeparator());
			}
			fw.close();
				
		}catch (IOException e) {
			System.out.println("An error occurred."); 
		}	
	}

}
