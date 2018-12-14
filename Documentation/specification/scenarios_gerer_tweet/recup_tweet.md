# L'utilisateur doit pouvoir lire un tweet

## Recup du tweet
- adresse : http://localhost:8080/gipsy-kings-tweet/tweet/X
- méthode get
- X : le numéro du twwet

## Reponses
- tweet ok :
	- code 200
	- {
		"tweetId":X,
		"senderId":numero,
		"text":"texte du tweet",
		"date":timestamp epochs (numero),
		"urlMedia":"to be defined"
		}
		
- tweet non trouvé :
	- code 404