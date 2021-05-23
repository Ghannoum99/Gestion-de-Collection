package modele;

/**
*
* @author Jihad
*/
public class Camion extends Vehicule {
	
   public Camion(String marque, String modele, String couleur, String prix) {
	super(marque, modele, couleur, prix);
   }
   
   @Override
   public String toString() {	
       return "Camion" + "," + marque + "," + modele + "," + couleur + "," + prix;
   }
   
}
