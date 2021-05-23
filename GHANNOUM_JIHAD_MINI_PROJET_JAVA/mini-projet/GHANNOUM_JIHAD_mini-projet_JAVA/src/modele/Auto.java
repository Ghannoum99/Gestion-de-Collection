package modele;
/**
*
* @author Jihad
*/
public class Auto extends Vehicule {
	
   public Auto(String marque, String modele, String couleur, String prix) {
	super(marque, modele, couleur, prix);
   }
   
   @Override
   public String toString() {	
       return "Auto" + "," + marque + "," + modele + "," + couleur + "," + prix;
   }
}