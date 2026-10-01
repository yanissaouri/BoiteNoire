# ADR-0001 : Utiliser MongoDB comme base de donnée

## Pourquoi ne pas utiliser du SQL

Le SQL est plus utile pour les systemes **ERP** (Entreprise Ressources Planning) et les **PGI** (Progiciel de Gestion Integres).
Ces cas-la servent plus particulierement dans la finance, les ressources-humaines, la supply-chain, E-T-C..., donc
pour des logicels internes d'entreprise. Ce qui ne sert a rien dans notre cas: on veut récuperer des informations
venant directement de notre application et d'en faire des statistiques.

## Le noSQL

La différence principale entre le SQL et le noSQL est son évolution. Le SQL évolue **verticalement** , signifiant qu'on 
a qu'un seul serveur ou on y ajoute des ressources matérielles supplementaires, comme de la **RAM**, du **stockage SSD**, E-T-C....

Alors que le noSQL evolue **horizontalement**; il ajoute des serveurs supplémentaire, permettant de répartir la charge de travail
entre eux et offre une *__extension plus fléxible__* mais aussi *__plus rentable__*. Le noSQL est aussi ideal pour gérer de grands volumes de 
donnés et des trafics élevés.

## MongoDB

**MongoDB** utilise le noSQL et son utilisation permet de stocker des documents JSON, ce qui est idéal pour les **CMS**.

Un **CMS**, Content Management System, est un logiciel permettant de créer, gérer et publier du contenu web sans coder.