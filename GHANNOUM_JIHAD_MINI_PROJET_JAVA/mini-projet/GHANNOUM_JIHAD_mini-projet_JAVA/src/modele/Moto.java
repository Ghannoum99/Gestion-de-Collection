package modele;
/**
*
* @author Jihad
*/
public class Moto extends Vehicule {
	
   public Moto(String marque, String modele, String couleur, String prix) {
	super(marque, modele, couleur, prix);	
   }
   
   @Override
   public String toString() {	
       return "Moto" + "," + marque + "," + modele + "," + couleur + "," + prix;
   }
}
