package controleur;

import java.awt.EventQueue;

import vue.MenuLogin;

public class Application {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuLogin menuLogin = new MenuLogin();
					menuLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
