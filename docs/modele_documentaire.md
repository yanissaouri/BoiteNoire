# Modèle documentaire


|  Champs   |   Type   |                Role                |
|:---------:|:--------:|:----------------------------------:|
|    _id    | objectId |   identifiant généré par MongoDB   |
|  userid   | objectId | référence de la collections users  |
|   type    |  string  |          type d'evenement          |
| timestamp |   date   | date heure et année de l'evenement |
|  details  |  object  |    contenu du type d'evenement     |


## L'embedding et le refferencing

### Embedding du bloc details
Le bloc details est embarqué dans le document de l'événement. On a presque toujours besoin
de l'IP, du montant ou du endpoint en même temps que l'événement, donc les stocker dans le même document évite une seconde 
lecture. Critère de croissance : ce bloc est de taille petite, il est écrit une fois à la création de l'événement
et ne grossit jamais ensuite, quel que soit le nombre d'événements de l'utilisateur.

### Referencing de l'utilisateur
Le champ userId référence la collection users au lieu d'y copier les données de l'utilisateur.Un gros utilisateur produit
des milliers d'événements sur l'année, donc embarquer ses événements dans son document le ferait grossir sans limite,
avec le risque d'approcher la limite de 16 Mo. Les informations de l'utilisateur (nom, email, région) sont partagées
par tous ses événements, donc on les stocke une seule fois et on les met à jour en un seul endroit.

## Les types d'événements

### Users Collection

```
{
"_id": " ",
"name": " ",
"email": " ",
"region": " ",
"createdAt": " "
}
```

### Login

```
{
"_id": " ",
"userId": " ",
"type": " ",
"timestamp": " ",
"details": {
"ip": " ",
"device": " ",
"success": true
}
}
```

### Subscription

```
{
  "_id": " ",
  "userId": " ",
  "type": " ",
  "timestamp": " ",
  "details": {
    "price": " ",
    "currency": " ",
    "plan": " ",
    "status": " "
  }
}
```
### Notification

```
{
{
  "_id": " ",
  "userId": " ",
  "type": " ",
  "timestamp": " ",
  "details": {
    "channel": " ",
    "read": true
  }
}
```

### Error

```
{
  "_id": " ",
  "userId": " ",
  "type": " ",
  "timestamp": " ",
  "details": {
    "errorType": " ",
    "service": " ",
    "message": " ",
  }
}
```

### API

```
{
"_id": " ",
"userId": " ",
"type": " ",
"timestamp": " ",
"details": {
    "endpoint": " ",
    "method": " ",
    "durationMs":  ,
    "HTTPCode": " " 
}
}
```
