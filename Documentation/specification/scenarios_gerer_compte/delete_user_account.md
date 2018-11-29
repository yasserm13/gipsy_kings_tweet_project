Use case : G�rer compte utilisateur

Scenario : l'utilisateur doit pouvoir supprimer son compte.

Pr�-conditions :
- L'utilisateur est authentifi� ;
- L'utilisateur a les droits pour modifier ses param�tres de compte.

Sc�narios nominal :

 1. L'utilisateur se rend dans ces param�tres de compte ;
 2. L'utilisateur clique sur l'onglet pour modifier ses informations personnelles ;
 3. L'utilisateur choisi l'option pour supprimer sont compte ; 
 4. Le programme envoi une demande de confirmation indiquant � l'utilisateur de saisir son mot de passe ;
 5. L'utilisateur renseigne son mot de passe ;
 6. Le programme supprime l'utilisateur avec ses informations de sa base de donn�es.

Point d�extension (Cas alternatifs) :

 5a. Si le mot de passe saisi par l'utilisateur n'est pas bon. Il faut retourner � l��tape 2 dans l'onglet de modification des informations personnelles.
 6a. Une fois le compte supprim�, le jeton d'authenfication est d�truit c�t� serveur.	