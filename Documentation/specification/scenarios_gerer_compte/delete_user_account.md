Use case : Gérer compte utilisateur

Scenario : l'utilisateur doit pouvoir supprimer son compte.

Pré-conditions :
- L'utilisateur est authentifié ;
- L'utilisateur a les droits pour modifier ses paramètres de compte.

Scénarios nominal :

 1. L'utilisateur se rend dans ces paramètres de compte ;
 2. L'utilisateur clique sur l'onglet pour modifier ses informations personnelles ;
 3. L'utilisateur choisi l'option pour supprimer sont compte ; 
 4. Le programme envoi une demande de confirmation indiquant à l'utilisateur de saisir son mot de passe ;
 5. L'utilisateur renseigne son mot de passe ;
 6. Le programme supprime l'utilisateur avec ses informations de sa base de données.

Point d’extension (Cas alternatifs) :

 5a. Si le mot de passe saisi par l'utilisateur n'est pas bon. Il faut retourner à l’étape 2 dans l'onglet de modification des informations personnelles.
 6a. Une fois le compte supprimé, le jeton d'authenfication est détruit côté serveur.	