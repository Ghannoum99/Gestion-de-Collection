package vue;


import javax.swing.JFrame;
import modele.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MenuCollectionnaire extends JFrame {

	protected JPanel contentPane;
	protected JPanel panelPrincipal;
	protected JPanel panelHaut;
	protected JPanel panelGauche;
	protected JLabel labelTitre;
	protected JTextField searchField;
	protected JSeparator searchSeparator;
	protected JLabel searchLabel;
	protected JComboBox<String> searchComboBox;
	protected JLabel labelDeconnexion;
	protected JButton boutonAjouter;
	protected JPanel panelCollection;
	protected JPanel panelAccueil;
	protected JPanel panelVendu;
	protected JButton boutonCollection;
	protected JButton boutonAccueil;
	protected JButton boutonEnVente;
	protected JButton boutonVendu;
	protected JPanel panelEnVente;
	protected JPanel panelSearch;
	protected JLabel labelBienvenue;
	protected JLabel valeurNumVehicule;
	protected JLabel vehiculeInfo;
	protected JLabel labelInfo;
	protected JLabel labelAccueil;
	protected JLabel labelCollection;
	protected JLabel labelEnVente;
	private VehiculeList<Vehicule> vehicules = new VehiculeList<Vehicule>();
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public MenuCollectionnaire() {
		setResizable(false);
		setVisible(true);
		setTitle(" Gestion du Collection - IATIC3 - Jihad Ghannoum");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1147, 789);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/** PANEL PRINCIPAL, CONTIENT TOUS LES PANELS **/
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPrincipal.setBounds(0, 0, 1143, 760);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		/** PANEL HAUT, CONTIENT LE TITRE, BOUTON DECONNEXION, CHAMPS DE RECHERCHE **/
		panelHaut = new JPanel();
		panelHaut.setBackground(new Color(37,134,163));
		panelHaut.setBounds(0, 0, 1142, 104);
		panelPrincipal.add(panelHaut);
		panelHaut.setLayout(null);
		
		/** Titre du Menu **/
		labelTitre = new JLabel("Gestion de Collection");
		labelTitre.setForeground(Color.WHITE);
		labelTitre.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
		labelTitre.setBounds(537, 10, 205, 89);
		panelHaut.add(labelTitre);
		
		/** Label qui permet le collectionnaire à deconnecter **/
		labelDeconnexion = new JLabel("");
		labelDeconnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuLogin menuLogin = new MenuLogin();
				menuLogin.show();
				System.out.println("deconnecté");
		        dispose();
			}
		});
		labelDeconnexion.setIcon(new ImageIcon("images/icons8-male-user-35.png"));
		labelDeconnexion.setBounds(1063, 47, 35, 32);
		panelHaut.add(labelDeconnexion);
	
		/** PANEL GAUCHE, CONTIENT LES DIFFERENTS BOUTON QUI NOUS PERMETTENT DE BASCULER D'UN PANEL VER L'AUTRE **/
		panelGauche = new JPanel();
		panelGauche.setBackground(new Color(37,134,163));
		panelGauche.setBounds(0, 102, 219, 661);
		panelPrincipal.add(panelGauche);
		panelGauche.setLayout(null);
		
		/** AFFICHAGE DANS LA CONSOLE **/
		System.out.println("\nBienvenue Chez Nous\n");
		
		afficherDashboard();
		afficherPanelCollection();
		afficherPanelEnVente();
		afficherPanelVendu();
		afficherPanelAccueil();
		
	}
	
	
	/*====================================== MENU DE NAVIGATION ====================================== */
	
	/***************************************************************************************/
	/** AFFICHAGE D'UN DASHBOARD QUI CONTIENT LES BOUTONS (ACCUEIL, COLLECTION, EN VENTE) **/
	/***************************************************************************************/
		public void afficherDashboard() {
			afficherBoutonAccueil();
			afficherBoutonCollection();
			afficherBoutonEnVente();
			afficherBoutonVendues();
		}
		
		
	/*====================================== AFFICHAGE DES PANELS ====================================== */
	
	/********************************/
	/** AFFICHAGE DU PANEL ACCUEIL **/
	/********************************/	
	public void afficherPanelAccueil() {
			SwingUtilities.updateComponentTreeUI(panelPrincipal);
			panelPrincipal.remove(panelCollection);
			panelPrincipal.remove(panelEnVente);
			panelPrincipal.remove(panelVendu);	
			
			panelAccueil = new JPanel();
			panelAccueil.setBackground(new Color(214, 229, 235));
			panelAccueil.setBorder(null);
			panelAccueil.setBounds(218, 105, 924, 658);
			panelPrincipal.add(panelAccueil);
			panelAccueil.setLayout(null);
			panelAccueil.setVisible(true);
			
			afficherBoutonAjouter();
			afficherLabelBienvenue();
	}
		
	/**********************************************************************************/
	/** AFFICHAGE DU PANEL COLLECTION QUI REGROUPE TOUTE LA COLLECTION DES VEHICULES **/
	/**********************************************************************************/	
	public void afficherPanelCollection() {
		panelCollection = new JPanel();
		panelCollection.setBackground(new Color(214, 229, 235));
		panelCollection.setBorder(null);
		panelCollection.setBounds(218, 105, 924, 655);
		panelPrincipal.add(panelCollection);
		panelCollection.setLayout(null);
			
	}
	
	/******************************************************/
	/** AFFICHAGE D'UN PANEL POUR LES VEHICULES EN VENTE **/
	/******************************************************/	
	public void afficherPanelEnVente() {
		panelEnVente = new JPanel();
		panelEnVente.setBackground(new Color(214, 229, 235));
		panelEnVente.setBorder(null);
		panelEnVente.setBounds(218, 105, 924, 658);
		panelPrincipal.add(panelEnVente);
		panelEnVente.setLayout(null);
		panelEnVente.setVisible(true);
	}
	
	/******************************************************/
	/** AFFICHAGE D'UN PANEL POUR LES VEHICULES VENDUES **/
	/******************************************************/	
	public void afficherPanelVendu() {
		panelVendu = new JPanel();
		panelVendu.setBackground(new Color(214, 229, 235));
		panelVendu.setBorder(null);
		panelVendu.setBounds(218, 105, 924, 658);
		panelPrincipal.add(panelVendu);
		panelVendu.setLayout(null);
		panelVendu.setVisible(true);
	}

	
	/*======================================  AFFICHAGE DES LABELS ====================================== */
	
	/*************************************************************************/
	/** AFFICHAGE DE PLUSIEURS LABEL, CHAQUE LABEL CORRESPOND A UNE COLONNE **/
	/*************************************************************************/	
	public void afficherLabelColonne(JPanel panel) {
		int x = 90;
		String[] titreInfo = {"Num", "Type", "Marque", "Modèle", "Couleur", "Prix"};
	
		for (int i=0;i<6;i++) {
			labelInfo = new JLabel(titreInfo[i]);
			labelInfo.setBounds(x, 115, 94, 27);
			labelInfo.setVisible(true);
			labelInfo.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
			panel.add(labelInfo);
			x += 138;	
		}
	}
	
	/*****************************************************/
	/** AFFICHAGE DES INFORMATIONS POUR CHAQUE VEHICULE **/
	/*****************************************************/	
	public void afficherVehiculeInfo(JPanel panel, int y, ArrayList<String> attributs, int nombreVehicule) {
		int x = 228;
		int i = 0;
		
		for(i=0; i<attributs.size(); i++) {
			vehiculeInfo = new JLabel(attributs.get(i));
			vehiculeInfo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
			vehiculeInfo.setBounds(x, y, 80, 27);
			panel.add(vehiculeInfo);
			x += 138;
		}
		
		String numVehicule = String.valueOf(nombreVehicule);
		valeurNumVehicule = new JLabel(numVehicule);
		valeurNumVehicule.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		valeurNumVehicule.setBounds(98, y, 50, 27);
		panel.add(valeurNumVehicule);
	}
	
	/*****************************/
	/** AFFICHER UNE SALUTATION **/
	/*****************************/	
	public void afficherLabelBienvenue() {
		labelBienvenue = new JLabel("");
		labelBienvenue.setIcon(new ImageIcon("images/logo-programme-741dc0-3cd7bb-0@1x.png"));
		labelBienvenue.setBorder(null);
		labelBienvenue.setBounds(278, 220, 333, 171);
		panelAccueil.add(labelBienvenue);
	}
	
	
	/*======================================  AFFICHAGE DES BOUTONS ====================================== */
	
	/*********************************************************************************************/
	/** AFFICHAGE DU BOUTON COLLECTION : QUI CONSISTE A AFFICHER TOUTE LA COLLECTION DU VEHICULE**/
	/*********************************************************************************************/	
	public void afficherBoutonCollection() {
		boutonCollection = new JButton("Collection");
		boutonCollection.setIconTextGap(25);
		boutonCollection.setForeground(Color.WHITE);
		boutonCollection.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		boutonCollection.setBorder(new LineBorder(new Color(37,134,163)));
		boutonCollection.setIcon(new ImageIcon("images/icons8-car-37.png"));
		boutonCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(panelPrincipal);
				panelPrincipal.remove(panelAccueil);
				panelPrincipal.remove(panelVendu);	
				panelPrincipal.remove(panelCollection);
				panelPrincipal.remove(panelEnVente);
				afficherPanelCollection();
				afficherOutilsRecherche(panelCollection, "files/collection.txt");
				afficherLabelColonne(panelCollection);
				System.out.println("\n***** Collection *****");
				memoriserVehicule(panelCollection, "files/collection.txt");
			}
		});
		boutonCollection.setBounds(27, 103, 182, 90);
		panelGauche.add(boutonCollection);
		boutonCollection.setBackground(new Color(37,134,163));
	}
	
	/******************************************************************************************/
	/** AFFICHAGE DU BOUTON EN VENTE QUI PERMET D'AFFICHER L'ENSEMBLE DES VEHICULES EN VENTE **/
	/******************************************************************************************/	
	public void afficherBoutonEnVente() {
		boutonEnVente = new JButton("En Vente");
		boutonEnVente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(panelPrincipal);
				panelPrincipal.remove(panelAccueil);
				panelPrincipal.remove(panelVendu);
				panelPrincipal.remove(panelEnVente);
				panelPrincipal.remove(panelCollection);
				afficherPanelEnVente();
				afficherLabelColonne(panelEnVente);
				afficherOutilsRecherche(panelEnVente, "files/collection_en_vente.txt");
				System.out.println("\n***** Véhicules En Vente *****");
				memoriserVehicule(panelEnVente, "files/collection_en_vente.txt");
			}
		});
		boutonEnVente.setIcon(new ImageIcon("images/icons8-buying-37.png"));
		boutonEnVente.setIconTextGap(25);
		boutonEnVente.setForeground(Color.WHITE);
		boutonEnVente.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		boutonEnVente.setBorder(new LineBorder(new Color(37,134,163)));
		boutonEnVente.setBackground(new Color(37,134,163));
		boutonEnVente.setBounds(27, 196, 182, 90);
		panelGauche.add(boutonEnVente);	
	}
	
	/**************************************************************/
	/** AFFICHAGE DU BOUTON ACCUEIL : QUI AFFICHE UNE SALUTATION **/
	/**************************************************************/	
	public void afficherBoutonAccueil() {
		boutonAccueil = new JButton("Accueil"); 
		boutonAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficherPanelAccueil();
			}
		});
		boutonAccueil.setIcon(new ImageIcon("images/icons8-home-37.png"));
		boutonAccueil.setIconTextGap(25);
		boutonAccueil.setForeground(Color.WHITE);
		boutonAccueil.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		boutonAccueil.setBorder(new LineBorder(new Color(37,134,163)));
		boutonAccueil.setBackground(new Color(37,134,163));
		boutonAccueil.setBounds(27, 10, 182, 90);
		panelGauche.add(boutonAccueil);
	}
	
	/*******************************************************************/
	/** AFFICHAGE DU BOUTON VENDU : QUI AFFICHE L'HISTORIQUE D'ACHATS **/
	/*******************************************************************/	
	public void afficherBoutonVendues() {
		boutonVendu = new JButton("Vendu"); 
		boutonVendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(panelPrincipal);
				panelPrincipal.remove(panelAccueil);
				panelPrincipal.remove(panelVendu);
				panelPrincipal.remove(panelEnVente);
				panelPrincipal.remove(panelCollection);
				afficherPanelVendu();
				afficherOutilsRecherche(panelVendu, "files/vehicules_vendues.txt");
				afficherLabelColonne(panelVendu);
				System.out.println("\n***** Historique D'Achats *****");
				memoriserVehicule(panelVendu, "files/vehicules_vendues.txt");
			}
		});
		boutonVendu.setIcon(new ImageIcon("images/icons8-best-sales-37.png"));
		boutonVendu.setIconTextGap(25);
		boutonVendu.setForeground(Color.WHITE);
		boutonVendu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		boutonVendu.setBorder(new LineBorder(new Color(37,134,163)));
		boutonVendu.setBackground(new Color(37,134,163));
		boutonVendu.setBounds(22, 289, 182, 90);
		panelGauche.add(boutonVendu);
	}
	
	
	/***************************************************************************************/
	/** AFFICHAGE DU BOUTON AJOUTER QUI PERMET LE COLLECTIONNAIRE D'AJOUTER DES VEHICULES **/
	/***************************************************************************************/	
	public void afficherBoutonAjouter() {
		boutonAjouter = new JButton("");
		boutonAjouter.setBounds(10, 20, 28, 31);
		panelAccueil.add(boutonAjouter);
		boutonAjouter.addActionListener(new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			MenuAjouterVehicule menuAjouterVehicule = new MenuAjouterVehicule();
			menuAjouterVehicule.show();
			}
		});
		boutonAjouter.setBorder(UIManager.getBorder("Button.border"));
		boutonAjouter.setIcon(new ImageIcon("images/icons8-add-new-37.png"));	
		}
	
	
	
	/*======================================  OUTILS DE RECHERCHE ====================================== */
	
	/******************************************************************************************************************/
	/** AFFICHAGE LES OUTILS DE RECHERCHE ( CHAMPS DU TEXTE, COMBOBOX: POUR FILTRER LE RECHERCHE, ICON DU RECHERCHE) **/
	/******************************************************************************************************************/	
	public void afficherOutilsRecherche(JPanel panel, String fichier) {
		afficherChampRecherche(panel);
		afficherComboBoxTypeRecherche(panel);
		afficherIconRecherche(panel, fichier);
	}
	
	/*************************************/
	/** AFFICHAGE DU CHAMP DE RECHERCHE **/
	/*************************************/	
	public void afficherChampRecherche(JPanel panel) {
		searchField = new JTextField();
		searchField.setForeground(new Color(132, 94, 194));
		searchField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		searchField.setText("Chercher");
		searchField.setBackground(new Color(214, 229, 235));
		searchField.setBorder(null);
		searchField.setBounds(640, 14, 140, 27);
		panel.add(searchField);
		searchField.setColumns(10);
		
		/** SEPARATEUR : LIGNE AU DESSOUS DU CHAMP DU TEXTE **/
		searchSeparator = new JSeparator();
		searchSeparator.setBackground(Color.WHITE);
		searchSeparator.setForeground(new Color(132, 94, 194));
		searchSeparator.setBounds(640, 44, 140, 2);
		panel.add(searchSeparator);
	}
	
	/************************************************/	
	/** COMBOBOX POUR CHOISIR LE TYPE DE RECHERCHE **/
	/************************************************/	
	public void afficherComboBoxTypeRecherche(JPanel panel) {
		searchComboBox = new JComboBox<String>();
		searchComboBox.setSelectedItem(null);
		searchComboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		searchComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Marque", "Modèle", "Couleur"}));
		searchComboBox.setBounds(490, 19, 120, 28);
		searchComboBox.setSelectedItem(null);
		panel.add(searchComboBox);
	}
	
	/**************************************/	
	/** AFFICHAGE D'UN ICON DE RECHERCHE **/
	/**************************************/
	public void afficherIconRecherche(JPanel panel, String fichier) {
		searchLabel = new JLabel("");
		searchLabel.setBounds(810, 13, 32, 37);
		searchLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chercherVehicule(panel, fichier);
			}
		});
		panel.add(searchLabel);
		searchLabel.setIcon(new ImageIcon("images/icons8-search-32.png"));
		searchLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
	}
	
	
	/*======================================  RECHERCHE D'UN VEHICULE ====================================== */
	
	/**********************************************************************************************************/
	/** RECHERCHER DES VEHICULES : QUI REGROUPE LES TROIS TYPES DE RECHERCHE: 1) MARQUE 2) MODELE 3) COULEUR **/
	/**********************************************************************************************************/
	public void chercherVehicule(JPanel panel, String fichier) {	
		/** PERMET D'EVITER LES ERREURS DANS LE CAS OU LE COLLECTIONNAIRE N'A PAS CHOISI UN TYPE DE RECHERCHE **/
		if (searchComboBox.getSelectedIndex() > -1 | searchComboBox.getSelectedIndex() > -1)
		{
			System.out.println("\n**** Résultat de recherche ****");
			switch((String) searchComboBox.getSelectedItem()) {
			case "Marque":
				chercherMarque(panel, fichier);
				break;
				
			case "Modèle":
				chercherModele(panel, fichier);
				break;
				
			case "Couleur":
				chercherCouleur(panel, fichier);
				break;
			}
		}
		else
		{
			afficherWarning();
		}
		
	}
	
	/********************************/
	/** RECHERCHER SELON LA MARQUE **/
	/********************************/
	public void chercherMarque(JPanel panel, String fichier) {
		chercherMarqueList();
		chercherFichier(fichier, panel, 1);
	}
	
	/***********************************************************/
	/** RECHERCHER SELON LA MARQUE DANS LA LISTE DE VEHICULES **/
	/***********************************************************/
	public void chercherMarqueList() {
		/** AFFICHAGE DANS LA CONSOLE **/
		for(Vehicule vehicule : vehicules) {
			if(vehicule.getMarque().equals(searchField.getText())) {
				System.out.println(vehicule);
			}
		}
	}
	
	/*****************************************************************************************************************/
	/* RECHERCHER  DANS UN FICHIER : LE NOM DU FICHIER EST PASSE EN PARAMETRE AVEC L'INDICE                          */
	/* PAR EXEMPLE LA MARQUE EST STOCKEE DANS LA CASE 1, DONC SI L'INDEX = 1 ALORS C'EST RECHERCHE SELON LA MARQUE   */
	/* AttributVehicule.get(1) : Marque,  AttributVehicule.get(2) : Modèle, AttributVehicule.get(3) : Couleur        */
	/*****************************************************************************************************************/
	public void chercherFichier(String fichier, JPanel panel, int index) {
		String [] att = null;
		int y = 147;
		int cmpt = 0; //pour afficher le bon numéro du véhicule
		ArrayList<String> attributVehicule = new ArrayList<String>();
		
    	try {
    		File file = new File(fichier);
    		Scanner sc = new Scanner(file);	
    		
    		/** LECTURE D'UN FICHIER **/
    		while (sc.hasNextLine()) {
    			String parts = sc.nextLine();
    	    	att = parts.split(",");
    	    	
    	    	/** REMOVE ALL ELEMENTS FROM THE ARRAYLIST **/
    	    	attributVehicule.removeAll(attributVehicule);
    	
    	    	/** RECUPERATION DES DONNEES **/
    	    	/** AJOUTER DES ELEMENTS DANS UN ARRAYLIST "ATTRIBUTS DE VEHICULE" **/
    	    	for(int i=0; i<att.length;i++) {
    	    		attributVehicule.add((String)att[i]);
    	    	}
    	    	
    	    	if (attributVehicule.get(index).equals(searchField.getText())) {
    	    		SwingUtilities.updateComponentTreeUI(panelPrincipal);
    	    		
    	    		/** VERIFIER LE TYPE DE PANEL **/
        	    	if(panel == (JPanel) panelCollection) {
        	    		panelPrincipal.remove(panelCollection);
        	    		afficherPanelCollection();
        	    		afficherOutilsRecherche(panelCollection, "files/collection.txt");
        				afficherLabelColonne(panelCollection);
        				/** AFFICHAGE DES VEHICULES DANS LE MENU **/
            	        afficherVehiculeInfo(panelCollection, y, attributVehicule, cmpt + 1);
        	    	}
        	    	else if(panel == (JPanel) panelVendu) {
        	    		panelPrincipal.remove(panelVendu);
        	    		afficherPanelVendu();
        	    		afficherOutilsRecherche(panelVendu, "files/vehicules_vendues.txt");
        				afficherLabelColonne(panelVendu);
        				/** AFFICHAGE DES VEHICULES DANS LE MENU **/
            	        afficherVehiculeInfo(panelVendu, y, attributVehicule, cmpt + 1);
        	    	}
        	    	else {
        	    		panelPrincipal.remove(panelEnVente);
            	    	afficherPanelEnVente();
            	    	afficherOutilsRecherche(panelEnVente, "files/collection_en_vente.txt");
        				afficherLabelColonne(panelEnVente);
        				/** AFFICHAGE DES VEHICULES DANS LE MENU **/
            	        afficherVehiculeInfo(panelEnVente, y, attributVehicule, cmpt + 1);
        	    	}
        	    	
        	        y += 33;
    	    	}  
    	    	
    	    	cmpt += 1;
    		}
      		sc.close();  		
    	}catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
	}
	/********************************/
	/** RECHERCHER SELON LE MODELE **/
	/********************************/
	public void chercherModele(JPanel panel, String fichier) {
		chercherModeleList();
		chercherFichier(fichier, panel, 2);
	}
	
	/***********************************************************/
	/** RECHERCHER SELON LE MODELE DANS LA LISTE DE VEHICULES **/
	/***********************************************************/
	public void chercherModeleList() {
		/** AFFICHAGE DANS LA CONSOLE **/
		for(Vehicule vehicule: vehicules) {
			if(vehicule.getModele().equals(searchField.getText())) {
				System.out.println(vehicule);
			}
		}
	}
	
	/********************************/
	/** RECHERCHER SELON LA COULEUR **/
	/********************************/
	public void chercherCouleur(JPanel panel, String fichier) {
		chercherCouleurList();
		chercherFichier(fichier, panel, 3);
	}
	
	/***********************************************************/
	/** RECHERCHER SELON LA COULEUR DANS LA LISTE DE VEHICULES **/
	/***********************************************************/
	public void chercherCouleurList() {
		/** AFFICHAGE DANS LA CONSOLE **/
		for(Vehicule vehicule: vehicules) {
			if(vehicule.getCouleur().equals(searchField.getText())) {
				System.out.println(vehicule);
			}
		}
	}
	
	
	/*======================================  MESSAGES WARNING ====================================== */
	
	/**********************************************************************************************************/
	/** AFFICHER UN MESSAGE "WARNING" SI LE COLLECTIONNAIRE N'A PAS CHOSI LE TYPE DE VEHICULE QU'IL RECHERCHE**/
	/**********************************************************************************************************/
	public void afficherWarning() {
		/** AFFICHAGE DANS LA CONSOLE **/
		System.out.println("Warning : Veuillez sélectionner le type de véhicule!\n");
		
		JOptionPane.showMessageDialog(panelAccueil, "Veuillez sélectionner le type de véhicule", "Attention", JOptionPane.WARNING_MESSAGE);	
	}
	
	/******************************************************************/
	/** AFFICHER UN MESSAGE SI LE VEHICULE SOUHAITE N'EST PAS TROUVE **/
	/******************************************************************/
	/*======================================  MESSAGES INFORMATION ====================================== */
	
	public void afficherMessage(JPanel panel) {
		/** AFFICHAGE DANS LA CONSOLE **/
		System.out.println("Information : le véhicule recherché n'est pas trouvé\n");
		
		JOptionPane.showMessageDialog(panel, "le véhicule recherché n'est pas trouvé", "Information", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	
	/*====================================== MEMORISATION DES VEHICULES ====================================== */	
	
	/********************************/
	/** MEMORISATION DES VEHICULES **/
	/********************************/	
	public void memoriserVehicule(JPanel panel,String fichier) {
		vehicules.removeAll(vehicules);
		int nombreVehicule = 0;
		String [] att = null;
		int y = 147;
		ArrayList<String> attributVehicule = new ArrayList<String>();
		vehicules.initialiserVehiculeList(vehicules, fichier);
		
    	try {
    		File file = new File(fichier);
    		Scanner sc = new Scanner(file);	
    		
    		/** LECTURE D'UN FICHIER **/
    		while (sc.hasNextLine()) {
    	        String parts = sc.nextLine();
    	        att = parts.split(",");
    	        
    	        attributVehicule.removeAll(attributVehicule);
    	        
    	        /** RECUPERATION DES DONNEES **/
    	        /** AJOUTER DES ELEMENTS DANS UN ARRAYLIST "ATTRIBUTS DE VEHICULE" **/
    	        for(int i=0; i<att.length;i++) {
    	        	attributVehicule.add((String)att[i]);
    	        }
    	        
    	        /** AFFICHAGE DES VEHICULES DANS LE MENU **/
        		afficherVehiculeInfo(panel, y, attributVehicule, nombreVehicule + 1);
        		
        		nombreVehicule += 1;
        		y += 33;
            }
    		
    		/** AFFICHAGE DES VEHICULES DANS LA CONSOLE **/
    		afficherVehiculeConsole();
    		
      		sc.close();  		
    	}catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
	}
	
	/*====================================== AFFICHAGE DANS LA CONSOLE ====================================== */
	/*****************************************************/
	/** AFFICHER LA LISTE DES VEHICULES DANS LA CONSOLE **/
	/*****************************************************/	
	public void afficherVehiculeConsole() {
		for(Vehicule v: vehicules) {
			System.out.println(v);
		}
	}
}
