# L'utilisateur doit pouvoir créer un compte

## Pré-conditions :

- [ ]

## Scénario principal:

1. L'utilisateur se rend sur l'interface pour créer son compte ? *=> une adresse particulière ?*
2. L'utilisateur saisie son émail
3. L'utilisateur saisie son mot de passe ? *=> vérification sur mot de passe?*
4. Le serveur envoie un code 200 pour signifie que tout va bien et fournit un jeton d’identification ? * => *une première vérification avec l'envoi d'un émail*
5. Le serveur enregistre le nouveau compte avec le jeton associé

## Extensions :

4a. Cette adresse mail est déjà utilisée : le serveur renvoie une erreur code 403 (Le serveur a compris la requête, mais refuse de l'exécuter).