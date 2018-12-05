# L'utilisateur doit pouvoir poster un tweet

## Pré-conditions :

- [ ] L'utilisateur est authentifié

## Scénario principal:

1. L'utilisateur peut saisir un texte (max 244 caractères en UTF16 => java.lang.String)
2. L'utilisateur peut ajouter une image (format gérer par javax.imageio, taille 500x500 px max)
3. L'utilisateur valide le tweet sur le serveur
4. Le serveur envoi le code 200 pour signifier que tous c'est passé correctement
5. remise a zéro du compteur du jeton
6. Au bout de 5 minute le serveur dévalide le jeton

## Extensions :

- 1a. texte trop long => le serveur renvoi une erreur 415
- 2a. image ne respecte pas les standard (type,taille) => le serveur renvoi une erreur 415
- 3a. une erreur est elle encore possible => je vois pas