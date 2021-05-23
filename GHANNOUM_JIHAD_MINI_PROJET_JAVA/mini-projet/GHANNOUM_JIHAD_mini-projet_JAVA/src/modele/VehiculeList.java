package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings({ "serial", "hiding" })
public class VehiculeList<Vehicule> extends ArrayList<Vehicule> {
	public VehiculeList() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void initialiserVehiculeList(VehiculeList<Vehicule> vehicules, String fichier) {
		String [] att = null;
		vehicules.removeAll(vehicules);
    	try {
    		File file = new File(fichier);
    		Scanner sc = new Scanner(file);	
    		
    		/** LECTURE D'UN FICHIER **/
    		while (sc.hasNextLine()) {
    	        String parts = sc.nextLine();
    	        att = parts.split(",");
    	        ArrayList<String> attributVehicule = new ArrayList<String>();
    	        
    	        /** RECUPERATION DES DONNEES **/
    	        /** AJOUTER DES ELEMENTS DANS UN ARRAYLIST "ATTRIBUTS DE VEHICULE" **/
    	        for(int i=0; i<att.length;i++) {
    	        	attributVehicule.add((String)att[i]);
    	        }
    	        
    	        /** CREATION DES OBJETS SELON LE TYPE **/
    	        /** INITIALISATION DU VEHICULELIST vehicules **/
    	        switch(attributVehicule.get(0)) {
				case "Auto" :
					Auto auto = new Auto(attributVehicule.get(1), attributVehicule.get(2), attributVehicule.get(3), attributVehicule.get(4));
					vehicules.add((Vehicule) auto);
					break;
	
				case "Moto":
					Moto moto = new Moto(attributVehicule.get(1), attributVehicule.get(2), attributVehicule.get(3), attributVehicule.get(4));
					vehicules.add((Vehicule) moto);
					break;
						
				case "Camion":
					Camion camion = new Camion(attributVehicule.get(1), attributVehicule.get(2), attributVehicule.get(3), attributVehicule.get(4));
					vehicules.add((Vehicule) camion);					
					break;
				}
    		}
    	        
      		sc.close();  		
    	}catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    	
	}
}
