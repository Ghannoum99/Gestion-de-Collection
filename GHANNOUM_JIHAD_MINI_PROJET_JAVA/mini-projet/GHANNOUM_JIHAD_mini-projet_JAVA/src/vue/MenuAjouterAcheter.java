package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**********************************************************************************************************/
/* CLASSE MERE (MenuAjouterAchetr) : CONTIENT LES OUTIS PRINCIPAL D'UN MENU (FRAME, PANEL)                */
/* CLASSE FILS : MenuAjouterVehicule, MenuAcheterVehicule                                                 */
/*  MOTIF DE L'HERITAGE: 1) LES 2 CLASSES FILS ONT DES CARACTERISTIQUES EN COMMUN                         */
/* 						 2) EVITER LA REDONDANCE                                                          */
/*                                                                                                        */
/**********************************************************************************************************/


@SuppressWarnings("serial")
public class MenuAjouterAcheter extends JFrame {
	
	protected JPanel contentPane;
	protected JPanel panelPrincipal;
	
	public MenuAjouterAcheter() {
		setResizable(false);
		setTitle("Ajouter/Acheter un V\u00E9hicule");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		setBounds(100, 100, 528, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(253,245,230));
		panelPrincipal.setBounds(0, 0, 524, 427);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
	}
}
