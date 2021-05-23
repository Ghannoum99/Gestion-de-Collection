package modele;

public class Vehicule {
   protected String marque;
   protected String modele;
   protected String couleur;
   protected String prix;
	
   public Vehicule(String marque, String modele, String couleur, String prix) {	
       this.marque = marque;
       this.modele = modele;
       this.couleur = couleur;	
       this.prix = prix;	
   }
   
   public String getMarque() {	
       return marque;
   }
	
   public String getModele() {	
       return modele;
   }
	
   public String getCouleur() {
       return couleur;
   }
	
   public String getPrix() {
       return prix;
   }
	
   public void setMarque(String marque) {
       this.marque = marque;
   }
   
   public void setModel(String modele) {
       this.modele = modele;
   }
   
   public void setCouleur(String couleur) {
      this.couleur = couleur;
   }
	
   public void setPrix(String prix) {	
       this.prix = prix;
   }
   
   @Override
   public String toString() {	
       return "Vehicule" + "," + marque + "," + modele + "," + couleur + "," + prix;
   }
   
}