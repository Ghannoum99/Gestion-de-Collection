# gestion_du_collection

## USER :
 - **Collectionnaire :** username: Dupont, password : 1234
 - **Client :** username: Martin, password : 1234

## Description:
- Le but du projet est de permettre à l'agence de gérer la liste des véhicules qu'il possède. 
- 3 types de véhicules possibles : Automobile, Camion et Moto

- Le projet est décomposé en 4 packages : Modele, Vue, Controleur, fichiers(db)

## Modele : Composé de 7 classes
        - Vehicule : a 4 attributs (Marque, Modèle, Couleur, Prix)
        - Auto : hérite de la classe Vehicule
        - Moto : hérite de la classe Vehicule
        - Camion : hérite de la classe Vehicule
        - VehiculeList : hérite de la classe ArrayList
        - Utilisateur : a 2 attributs (username, password);
        - UtilisateurAdmin : hérite de la classe Utilisateur (En gros c'est le collectionnaire).

## Vue : Composé de 6 classes (interfaces)
        - MenuLogin : permet l'utilisateur de se connecter. (si l'utilisateur est un Admin : Le MenuCollectionnaire s'affichera, sinon: c'est le MenuClient qui va s'afficher)
        - MenuCollectionnaire : 
                  - interface qui permet le collectionnaire de gérer la liste des véhicules qu’il possède.
                  - Dans cette classe on a  6 Panels :
                      -  panelHaut : contient le titre, le labeldeconnexion
                      -  panelDroite: contient les boutons de navigations (Accueil : permet d'afficher une salutation et contient et le bouton "boutonAjouter" qui permet le                              collectionnaire d'ajouter un véhicule à sa liste, Collection : afficher la collection des véhicules , En Vente: afficher les véhicules qui sont vendre,                          Vendu : permet d'afficher les véhicules vendues.)
                      -  panelAccueil : L'affichage du label Bienvenue et du boutonAjouter sont faites dans le panelAcceuil. ce panel contient le bouton Ajouter/Acheter des                              véhicules.
                      -  panelCollection : L'affichage de la collection de véhicules se fait dans le panelCollection
                      -  panelEnVente : L'affichage des véhicules en vente se fait dans le paneAccueil.
                      -  panelVendu : L'affichage des véhicules vendues se fait dans ce panel.
                 
                 - Contient 4 Boutons de Navigations: Accueil, Collection, EnVente, Vendu.
                  
                      
        - MenuClient : herite de la classe MenuCollectionnaire, en modifiant les droits d'accés du client (un client ne peut pas ajouter des véhicules, il ne peut accéder que
	  aux véhicules en vente, il peut acheter des véhicules)
        - MenuAjouterAcheter : classe mère
        - MenuAjouterVehicule : hérite de la classe MenuAjouterAcheter, permet au collection d'insérer les données du véhicule qu'il veut ajouter.
        - MenuAcheterVehicule : hérite de la classe MenuAjouterAcheter, permet aux clients de choisir le numréro du véhicule qu'il souhaite acheter, puis leurs données perso.

- **Remarque :**
  Les 2 panels (Collection et En Vente) contient un barre de recherche, après l'affichage du résultat de recherche, il faut appuyer soit sur le
  boutonCollection soit sur le boutonEnVente pour recharger les données.



## Controleur : Composé d'une seule classe
        - Application : consiste à relier le Modele et la Vue



## Fichiers : Composé de 3 classes
        - Collection : consiste à créer un fichier collection.txt (qui sert à sauvegarder la collection de véhicules)
        - VehiculeEnVente : consiste à créer un fichier collection_en_vente.txt ( qui regroupe les véhicules en vente)
        - UserInfo : consiste à créer un fichier users_details.txt (contient les différents utilisateurs) et stocker les données dedans.
