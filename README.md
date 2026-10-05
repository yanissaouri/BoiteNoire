# BoiteNoire

projet construit avec maven. 

si sa ne marche pas il faut build le projet  `mvn package`
puis verifier que le fichier .jar existe `dir target\*.jar`
puis pour lancer le projet utiliser la commande `java -jar target\demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=generator` ou `mvn spring-boot:run "-Dspring-boot.run.profiles=generator"`
