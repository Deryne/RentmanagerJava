Deryne Four
deryne.four@epfedu.fr

Projet Rentmanager
Il s'agit d'un projet permettant de gérer et visualiser des locations de voitures par des clients. Il permet sur une adresse locale de :
- Créer des clients, des voitures et des réservations
- Visualiser les clients, voitures et réservations existants
- Supprimer  les clients, voitures et réservations existants
- Modifier les clients, voitures et réservations existants

Le projet contient un backup.zip contenant la base de données initiale du projet.

Problèmes rencontrés :
- J'ai eu du mal initialement à comprendre comment les données des formulaires web devaient arriver à communiquer avec la classe Java.
	Une fois que j'ai compris le fonctionnement avec la fonction count(), tout est allé mieux.

- J'ai heurté pendant un moment sur un problème de visualisation des véhicules et je ne comprenais pas pourquoi mon findAll() affichait toujours la même voiture.
	Réponse : La classe vehicle (pour la voiture) était static... oui c'est douloureux.

- Afin de réaliser la fonction Update, je me suis inspirée de la fonction Create pour proposer à l'utilisateur de sélectionner le client/voiture/reservation 
	à changer puis à proposer de rentrer les renseignements. Je n'ai pas réussi à implémenter un bouton pour chaque client/voiture/reservation.

- Les tests sur les classes Service ont nécessité pas mal de concentration pour bien vérifier que les new throws étaient sur les Dao et les services.

- Par manque de temps, je n'ai pas réalisé les contraintes les plus complexes : 
	- une voiture ne peux pas être réservé 2 fois le même jour
	- une voiture ne peux pas être réservé plus de 7 jours de suite par le même utilisateur
	- une voiture ne peux pas être réservé 30 jours de suite sans pause


Axes d'amélioration :
- Amélioration de la fonction Update (Modifier) pour un côté plus User Friendly

- Implémentation des contraintes restantes


Pour lancer le projet :
- faire mvn tomcat7:run dans le Terminal après s'être dirigigé dans le dossier du projet (ici rentmanager)
