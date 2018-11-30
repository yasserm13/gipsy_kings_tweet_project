## Scenario: l'utilisateur doit pouvoir modifier un tweet ##

### Pré-condition: 
Il existe des tweets en base

### Scénario principal:
1. L'utilisateur se connecte
2. L'utilisateur accède à l'un de ses tweets
3. L'utilisateur tente d'entrer en mode édition
4. Le système vérifie les autorisations de l'utilisateur
5. L'utilisateur saisie sa modification
6. L'utilisateur quitte le mode edition
7. Le système enregistre les modifications du tweet

### Extensions:
4a. L'utilisateur n'est pas connecté et tente d'éditer un tweet
	- 1: L'utilisateur ne peut pas éditer son tweet
	
4b. L'utilisateur tente d'éditer un tweet qui ne lui appartiente pas
	- 1: Le système n'autorise pas l'éditions => renvoie le code XXX
	
4c. L'utilisateur est connecté et tente d'éditer un tweet 
	- 1: Le système renvoie le code 200
	
7a. L'utilisateur est connecté et a quittté le mode édition
	- 1: Le système enregistre les modifications du tweet en base
	- 2: Le système renvoie le code XXX
	
7b. L'utilisateur n'est plus connecté (déconnexion ou expiration du token d'authentification) et a quittté le mode édition
	- 1: Le système n'enregistre pas les modifications du tweet
	- 2: Le système renvoie le code d'erreur XXX

4b. Le couple adresse mail/mot de passe existe
	- 1: Le systèeme renvoi le code 200
	- 2: Le système renvoie un token d'authentification