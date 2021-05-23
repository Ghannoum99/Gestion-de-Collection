package vue;

import modele.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class MenuLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField usernameField;
	private JLabel warningLabel;
	private JPanel panelPrincipal;
	private JPanel panelGauche;
	private JPanel panelDroite;
	private JLabel labelBienvenue ;
	private JButton boutonSeConnecter;
	
	
	/**
	 * Create the frame.
	 */
	public MenuLogin() {
		setTitle("Authentification");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/** Panel Principal qui contient Panel Gauche et Panel Droite **/
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 745, 360);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		/** Panel Gauche, contient un logo **/
		panelGauche = new JPanel();
		panelGauche.setBackground(new Color(74,31,61));
		panelGauche.setBounds(0, 0, 351, 360);
		panelPrincipal.add(panelGauche);
		panelGauche.setLayout(null);
		
		/** LOGO BIENVENUE **/
		labelBienvenue = new JLabel("");
		labelBienvenue.setIcon(new ImageIcon("images/Bienvenue_chez_nous_2018.png"));
		labelBienvenue.setBounds(40, 104, 260, 155);
		panelGauche.add(labelBienvenue);
	
		/** Panel Droite, contient les champs de text, logo,  et le bouton Log in **/
		panelDroite = new JPanel();
		panelDroite.setBackground(new Color(186,79,84));
		panelDroite.setBounds(351, 0, 393, 360);
		panelPrincipal.add(panelDroite);
		panelDroite.setLayout(null);
		
		

		afficherchampsText();
		afficherBoutonSeConnecter();
		afficherIcon();
	}
	
	/***************************************************/
	/** AFFICHAGE DU CHAMP TEXT: USERNAME ET PASSWORD **/
	/***************************************************/	
	public void afficherchampsText()
	{
		usernameField = new JTextField();
		usernameField.setForeground(Color.LIGHT_GRAY);
		usernameField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		usernameField.setText("Username");
		usernameField.setBorder(null);
		usernameField.setBounds(64, 93, 264, 35);
		usernameField.setBackground(new Color(186,79,84));
		panelDroite.add(usernameField);
		usernameField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordField.setText("");
			}
		});
		passwordField.setText("Password");
		passwordField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		passwordField.setToolTipText("");
		passwordField.setBorder(null);
		passwordField.setBackground(new Color(186,79,84));
		passwordField.setForeground(Color.LIGHT_GRAY);
		passwordField.setBounds(64, 169, 264, 35);
		panelDroite.add(passwordField);
		
		afficherSeparateur();
		
	}
	
	/**************************************************************/
	/** AFFICHAGE D'UN ICON POUR CHACUN "USERNAME" ET "PASSWORD" **/
	/**************************************************************/
	public void afficherIcon() {
		int i = 0;
		int y = 93;
		String[] imagePath = {"images/icons8-male-user-35.png", "images/icons8-password-35.png"};
		for(i=0; i<2; i++) {
			JLabel icon = new JLabel("");
			icon.setIcon(new ImageIcon(imagePath[i]));
			icon.setBounds(19, y, 35, 35);
			panelDroite.add(icon);
			
			y += 76;
		}
	}
	
	/*******************************/
	/** AFFICHAGE DES SEPARATEURS **/
	/*******************************/	
	public void afficherSeparateur()
	{
		JSeparator usernameSeparateur = new JSeparator();
		usernameSeparateur.setBounds(64, 138, 264, 12);
		panelDroite.add(usernameSeparateur);
		
		JSeparator passwordSeparateur = new JSeparator();
		passwordSeparateur.setBounds(64, 214, 264, 12);
		panelDroite.add(passwordSeparateur);
		
	}
	
	/********************************/
	/** AFFICHAGE DU BOUTON LOG IN **/
	/********************************/	
	public void afficherBoutonSeConnecter()
	{
		boutonSeConnecter = new JButton("Log In");
		boutonSeConnecter.setBorder(new LineBorder(Color.LIGHT_GRAY));
		boutonSeConnecter.setBackground(new Color(186,79,84));
		boutonSeConnecter.setForeground(Color.LIGHT_GRAY);
		boutonSeConnecter.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		boutonSeConnecter.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				boolean connexion = seConnecter();
				boolean admin = estAdmin();
				
				/** Si l'utilisateur est un collectionnaire **/
				if (connexion && admin ) {
					@SuppressWarnings("unused")
					UtilisateurAdmin utilisateurAdmin = new UtilisateurAdmin(usernameField.getText(), passwordField.getText());
					System.out.println("Connecté avec succès!");
					System.out.println("Vous êtes un Administrateur");
					MenuCollectionnaire frame = new MenuCollectionnaire();
					frame.show();
					dispose();
				}
				/** Si l'utilisateur est un client **/
				else if (connexion) {
					@SuppressWarnings("unused")
					Utilisateur utilisateur = new Utilisateur(usernameField.getText(), passwordField.getText());
					System.out.println("Connecté avec succès!");
					System.out.println("Vous êtes un client");
					MenuClient frame = new MenuClient();
					frame.show();
					dispose();
				}
				else {
					afficherWarning();
					System.out.println("wrong username or password!");
				}
			}
		});
		boutonSeConnecter.setBounds(135, 268, 96, 35);
		panelDroite.add(boutonSeConnecter);
	}
	
	/*******************************************************/
	/** FONCTION QUI PERMET L'UTILISATEUR DE SE CONNECTER **/
	/*******************************************************/	
	@SuppressWarnings("deprecation")
	public boolean seConnecter() {
		String [] users_details = null;
	    boolean connexion = false;
	    String username = usernameField.getText();
		String password = passwordField.getText();
		ArrayList<String> attributs = new ArrayList<String>();

	    try {
	    	File file = new File("files/users_details.txt");
	    	Scanner sc = new Scanner(file);
	    	while (sc.hasNextLine()) {
	    		String data = sc.nextLine();
	    	    users_details = data.split(",");
	    	    attributs.removeAll(attributs);
	    	    
	    	    for(int i=0; i<users_details.length; i++) {
	    	    	attributs.add(users_details[i]);
	    	    }
	    			
	    	    if ((attributs.get(0).equals(username)) && (attributs.get(1).equals(password))) {
	    			connexion = true;
	                break;
	    		}	
	    	}
	    		
	    	sc.close();
	    		
	    }catch (IOException e) {
	    	System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	    	
	    return connexion;
	}
		
	/*********************************************************************************************/
	/** FOCNTION QUI NOUS PERMET DE SAVOIR SI L'UTILISATEUR EST UN CLIENT OU UN COLLECTIONNAIRE **/
	/*********************************************************************************************/	
	@SuppressWarnings("deprecation")
	public boolean estAdmin() {
		String [] users_details = null;
	    boolean admin = false;
	    ArrayList<String> attributs = new ArrayList<String>();
	    String username = usernameField.getText();
		String password = passwordField.getText();
	    	
	    try {
	    	File file = new File("files/users_details.txt");
	    	Scanner sc = new Scanner(file);
	    	while (sc.hasNextLine()) {
	    		String data = sc.nextLine();
	    	    users_details = data.split(",");
	    	    attributs.removeAll(attributs);
	    	    
	    	    for(int i=0; i<users_details.length; i++) {
	    	    	attributs.add(users_details[i]);
	    	    }
	    			
	    		if ((attributs.get(0).equals(username)) && (attributs.get(1).equals(password))) {
	    			if (attributs.get(2).equals("1")) {
	    				admin = true;
	    				break;
	                }    
	    		}	
	    	}
	    		
	    	sc.close();
	    		
	    }catch (IOException e) {
	    	System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	    	
	    return admin;
	}
	
	/********************************************************************************************************************************/
	/** AFFICHAGE D'UN WARNING SI LE NOM D'UTILISATEUR ET LE MOT DE PASSE NE SONT PAS ENREGISTRES DANS LE FICHIER users_details.txt**/
	/********************************************************************************************************************************/	
	public void afficherWarning() {
		warningLabel = new JLabel("wrong username or password");
		warningLabel.setOpaque(true);
		warningLabel.setForeground(new Color(74,31,61));
		warningLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		warningLabel.setBounds(95, 45, 199, 18);
		panelDroite.add(warningLabel);
		SwingUtilities.updateComponentTreeUI(panelDroite);
	}
}
