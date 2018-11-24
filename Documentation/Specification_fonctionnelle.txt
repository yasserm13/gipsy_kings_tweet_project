DOCUMENTATION TWITTER: https://developer.twitter.com/en/docs
DOCUMENTATION CODE HTML: https://fr.wikipedia.org/wiki/Liste_des_codes_HTTP
DOCUMENTATION TOKEN JAVA: https://developer.okta.com/blog/2018/10/16/token-auth-for-java
DOCUMENTATION IMAGE DANS UN TWEET: https://business.twitter.com/fr/help/campaign-setup/advertiser-card-specifications.html
LIEN VERS LE GITHUB: https://github.com/yasserm13/gipsy_kings_tweet_project
LIEN VERS LE TRELLO: https://trello.com/b/0cFv88Ah/isen-jee

CECI EST UNE LEGENDE CESAR
- 	Fonctionnalité 
-- 	Composé de la fonctionnalité
*** 	Exigeance
^^^	Gestion des erreurs
(*)	A implémenter au minimum

Liste des fonctionnalités:

- L'utilisateur doit pouvoir créer un compte
- - Nom d'utilisateur (*)
- - Adresse Mail
- - Mot de passe (*)
*** Un seul compte peut être créé avec une même adresse mail. (*)
^^^ Un compte a déjà été créé avec une adresse mail existante => renvoi code 403 (Le serveur a compris la requête, mais refuse de l'exécuter.) (*)
*** Lorsque la création du compte se passe bien, le code 200 est renvoyé. (*)

- L'utilisateur doit se logguer avec un login ET un MDP
- - Login -> mail + mdp (cryptage MD5) (*)
*** L'association mail + mdp doit être correcte afin de réussir l'authentification. (*) le code 200 est renvoyé. (*)
Une fois réussie, le serveur renvoie un jeton d'authentification.
Ce jeton (JWT) est valide pour une durée de: 5 minutes sans activité.
*** Le serveur doit pouvoir sauvegarder les couples jetons/nom d'utilisateur délivrés ainsi que leur durée de validité.
^^^ Login incorrect => renvoi du code 401 (Une authentification est nécessaire pour accéder à la ressource. ) (*)
^^^ Mot de pass incorrect => renvoi du code 401 (Une authentification est nécessaire pour accéder à la ressource. ) (*)
*** Le message  d'erreur doit être identique dans les deux cas, pour éviter de donner une indication trop précise à d'éventuels pirates. (*)

- L'utilisateur doit pouvoir supprimer son compte.
*** L'utilisateur doit être loggué au moment de la suppression du compte.
*** Le jeton d'authenfication est détruit côté serveur lorsque le compte est supprimé.

( - L'utilisateur doit pouvoir modifier son adresse mail et/ou son mot de passe -- POUR UNE V2 -- )

- L'utilisateur doit pouvoir se déconnecter.
*** Le jeton d'authenfication est détruit.

- L'utilisateur doit pouvoir poster un tweet.
*** Doit être authentifié pour envoyer un tweet. (*)
^^^ N'est pas connecté => renvoi du code 511 (Le client doit s'authentifier pour accéder au réseau)
*** Jeton doit toujours être valide
*** Remise à 0 du compteur de destruction de jeton après action réalisée par l'utilisateur.

- L'utilisateur doit pouvoir modifier un tweet.
*** Doit être authentifié pour modifier un tweet. (*)
^^^ N'est pas connecté => renvoi du code 511 (Le client doit s'authentifier pour accéder au réseau)
*** Jeton doit toujours être valide
*** Remise à 0 du compteur de destruction de jeton après action réalisée par l'utilisateur.

- L'utilisateur doit pouvoir supprimer un tweet.
*** Doit être authentifié pour supprimer un tweet. (*)
^^^ N'est pas connecté => renvoi du code 511 (Le client doit s'authentifier pour accéder au réseau)
*** Jeton doit toujours être valide
*** Remise à 0 du compteur de destruction de jeton après action réalisée par l'utilisateur.

- L'utilisateur doit pouvoir lire un tweet (avec une image) (*)
*** Le tweet est accessible à tout le monde. (personnes non authentifiées compris).
^^^ N'est pas connecté => renvoi du code 511 (Le client doit s'authentifier pour accéder au réseau)
*** Jeton doit toujours être valide

------------------------------------------------------------------------------------------------
Définition d'un tweet:
- Un texte (Unicode UTF-8)
- Taille minimale du tweet: 240 caractères
- Format d'image: JPEG, PNG
- Taille de l'image: 500px * 500px
- Poids de l'image: 2 Mo max. 

