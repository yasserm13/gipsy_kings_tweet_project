L'utilisateur doit pouvoir se déconnecter.

Pré-conditions :

- Etre connecté
- Jeton d'authentification existant

Scénario principal:

    L'utilisateur accède au menu déroulant en déplaçant le curseur sur son avatar/username (en haut a gauche ou droite) => ou en cliquant ?
    L'utilisateur voit apparaître le bouton "se deconnecter" dans le menu déroulant et clique dessus.
    Le serveur supprime le jeton d'authentification de l'utilisateur => Status 200 returned

Extensions :

    La session de l'utilisateur a expiré (plus connecté), le serveur ne trouve pas de jeton d'authentification => HTTP 401 - Similar to 403 Forbidden, but specifically for use when authentication is possible but has failed or not yet been provided.