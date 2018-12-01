## Creation du scenario l'utilisateur doit se loger avec un login et un mot de passe ##

### Pré-condition: 
L'utilisateur n'est pas connecté

### Scénario principal:
1. L'utilisateur accède à la fenêtre de connexion
2. L'utilisateur entre son adresse mail et son mot de passe
3. L'utilisateur se connecte à partir des informations renseignées.
4. Le système analyse les informations de l'utilisateur

### Extensions:
2. a) L'utilisateur ne renseigne pas au moins l'une des deux informations (mot de passe et/ou login)
	- 1: L'utilisateur ne peut pas lancer la connexion
	
4. a) Le couple adresse mail/mot de passe n'existe pas en base
	- 1: Le système renvoie le code erreur XXXX

4. b) Le couple adresse mail/mot de passe existe
	- 1: Le systèeme renvoi le code 200
	- 2: Le système renvoie un token d'authentification
