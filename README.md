# 
# RPG - League of Dev

---

Ce projet est un jeu de rôle (RPG) textuel simple développé en Java. Il offre une expérience de combat basique où un héros affronte différents types d'ennemis pour accomplir une quête. Le jeu met l'accent sur la gestion stratégique des ressources (mana et potion) et propose une progression de difficulté au fil des combats.

---

## Fonctionnalités

* **Création de Héro** : Le joueur peut nommer son héro au début de la partie.
* **Système de Combat au Tour par Tour** : Affrontez des ennemis comme des Gobelins, des Trolls et des Dragons dans un combat où le Héros et l'ennemi attaquent à tour de rôle.
* **Statistiques des Personnages Variées** : Chaque personnage (Héro, Gobelin, Troll, Dragon) possède des points de vie (PV), une défense, et une plage d'attaque spécifique pour équilibrer les combats.
    * **Héro** : PV et défense équilibrés, avec une attaque aléatoire (15-35 dégâts).
    * **Gobelin** : Faible en PV et défense, idéal pour les premiers combats.
    * **Troll** : Plus résistant, un défi de milieu de jeu.
    * **Dragon** : Le boss ultime, très robuste et infligeant de lourds dégâts.
* **Dégâts Minimum Garantis** : Toutes les attaques infligent au moins 5 dégâts, assurant que chaque coup compte.
* **Attaque Spéciale du Héro** : Une attaque puissante nécessitant du mana. Sa puissance est basée sur les dégâts aléatoires normaux du héro.
* **Gestion de la Potion** : Le héro dispose d'une potion qui restaure des PV, mais son utilisation est stratégique : elle ne peut être activée que si les PV du héro se situent entre 5 et 170.
* **Restauration de Mana** : Après chaque victoire, le héro récupère du mana, encourageant l'utilisation du pouvoir spécial.
* **Objectif de Victoire** : Le jeu se termine lorsque le héro a vaincu 5 ennemis, ou s'il est vaincu en combat.
* **Gestion Robuste des Saisies** : Une exception personnalisée (`InvalidActionChoiceException`) est utilisée pour gérer les entrées utilisateur incorrectes, offrant un feedback clair et permettant au joueur de retenter sa saisie.

---

## Structure du Projet

Le projet est organisé en plusieurs classes principales :

* **`Main.java`** : Point d'entrée du jeu. Il initialise le `CombatManager` et lance la partie.
* **`Personnage.java`** (Classe Abstraite) : Classe de base pour tous les participants aux combats. Elle contient les attributs communs (nom, PV, PV Max, défense) et les méthodes génériques (`attaquer`, `prendreDegat`, `estVivant`, `getDegatsInfliges` (abstraite)).
* **`Hero.java`** : Étend `Personnage` et représente le joueur. Il gère le mana, la potion et le compteur de victoires. Implémente l'interface `PouvoirSpecial`.
* **`Ennemi.java`** : Étend `Personnage` et sert de base pour tous les ennemis du jeu.
* **`Gobelin.java`**, **`Troll.java`**, **`Dragon.java`** : Étendent `Ennemi` et définissent les statistiques et les plages de dégâts spécifiques à chaque type d'ennemi.
* **`CombatManager.java`** : Gère la logique principale du jeu, y compris l'initialisation du héro, la génération des ennemis, le déroulement des combats tour par tour, la gestion des actions du joueur, et les conditions de victoire/défaite. La méthode `demarrerPartie()` a été refactorisée pour plus de clarté.
* **`PouvoirSpecial.java`** (Interface) : Définit le contrat pour l'utilisation d'un pouvoir spécial.
* **`InvalideActionChoisieException.java`** (Exception Personnalisée) : Gère les erreurs de saisie de l'utilisateur.

---

## Installation et Lancement

Pour installer et lancer ce projet, suivez ces étapes :

### Prérequis

* **Java Development Kit (JDK)** : Assurez-vous d'avoir le JDK installé sur votre machine (version 17 ou plus récente recommandée). Vous pouvez le télécharger depuis le site officiel d'Oracle ou d'OpenJDK.

### Étapes d'installation

1.  **Cloner le dépôt** :
    ```bash
    git clone <URL_DU_DEPOT>
    ```
    (Remplacez `<URL_DU_DEPOT>` par l'URL réelle de votre dépôt Git.)
2.  **Naviguer vers le répertoire du projet** :
    ```bash
    cd <NOM_DU_REPERTOIRE_DU_PROJET>
    ```
3.  **Compiler les fichiers Java** :
    ```bash
    javac -d out src/rpg/*.java src/rpg/personnages/*.java src/rpg/utils/*.java src/rpg/exceptions/*.java src/rpg/interfaces/*.java
    ```
    *(Cette commande suppose que vos fichiers source sont organisés dans un dossier `src` avec des sous-dossiers pour les packages (`rpg`, `rpg/personnages`, `rpg/utils`, `rpg/exceptions`, `rpg/interfaces`), et qu'un dossier `out` sera créé pour les fichiers compilés.)*
4.  **Exécuter le jeu** :
    ```bash
    java -cp out rpg.Main
    ```

---

## Comment Jouer

1.  **Suivez les instructions** : Entrez le nom de votre héro, puis choisissez vos actions (attaquer, utiliser un pouvoir, utiliser une potion) en tapant le numéro correspondant.
2.  **Gérez vos ressources** : Soyez stratégique avec votre mana pour les pouvoirs spéciaux et votre potion pour les soins.
3.  **Vainquez 5 ennemis** : Atteignez l'objectif de 5 victoires pour terminer le jeu.

---

## Développement

Ce projet a été développé en adoptant des principes de bonne pratique comme la **séparation des responsabilités**, la **factorisation de code** pour améliorer la lisibilité et la maintenabilité, et l'utilisation d'**exceptions personnalisées** pour une gestion d'erreurs plus robuste. Les statistiques des personnages ont été ajustées pour offrir une expérience de jeu équilibrée et engageante.