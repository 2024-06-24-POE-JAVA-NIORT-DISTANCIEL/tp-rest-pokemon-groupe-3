# TP Pokémon REST

Ce TP de la formation M2I Programmation Java Fullstack a pour but de simuler une gestion et des combats de Pokémons.
Les langages utilisés sont **Java** et **SQL**. Ceci est construit à l'aide du framework **Spring Boot** et démontrera le fonctionnement de **WebServices** conformes à l'architecture **REST**.

## Fichiers additionnels
TP Pokemon.drawio.xml : diagramme entité-association de l'application
Ouvrir le fichier sur [app.diagrams.net](https://app.diagrams.net)

reset-pokemon-joute.sql : fichier de remise à zéro de la base de données pokemon-joute
Voici les étapes pour repartir d'une base propre (sans données) :
- attention : si la base pokemon-joute existe déjà, elle est effacée puis recréée à neuf -
- Fermer pgAdmin (ou équivalent) s'il est lancé -
1. faire un git pull pour récupérer la dernière version du projet, y compris ce fichier
2. ouvrir un terminal dans le répertoire où se trouve le projet sur votre machine
3. entrer la commande : 
   `pg_restore -U postgres -h localhost -d postgres --clean --create reset-pokemon-joute.sql` et faire Entrée
   -U : utilisateur de PostgreSQL, (ici:postgres ou autre si vous l'avez modifié)
4. normalement vous allez devoir entrer votre mot de passe PostgreSQL (rien, postgres, au autre si vous l'avez modifié)
5. si vous n'avez pas encore la base, vous aurez le message :
   `pg_restore: erreur : could not executy query: ERREUR: la base de données "pokemon-joute" n'existe pas .....`
   et c'est normal
6. allez dans pgAdmin4, clic-droit dans l'arborescence à gauche sur Servers et cliquer sur Refresh, si tout s'est bien passé, sous 
   Servers > PostgreSQL 16 > Databases > .. vous devez trouver pokemon-joute et les tables sont dans pokemon-joute > Schemas > public > Tables
   
