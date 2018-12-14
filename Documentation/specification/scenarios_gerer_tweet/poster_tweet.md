# L'utilisateur doit pouvoir poster un tweet

## Envoi tweet 
- adresse : http://localhost:8080/gipsy-kings-tweet/tweet
- method POST
- json
{
	"senderId":"to be defined",
	"text":"Min 1 , Max 240",
	"urlMedia":"to be defined"
}

## Reponses 
- tweet ok :
	- code 200
- tweet hs :
	- problème de validation : 
		- code 406 
		- json ? {"propriété x":"message erreur x","propriété y":"message erreur y",... }
	- tweetid existe déjà => ne devrait jamais arriver :
		- code 400
		- json ? {"tweetid", "tweetid existe déjà"}
	- Autres erreurs :
		- code 400
		- json ? {"erreur", "message d'erreur"}