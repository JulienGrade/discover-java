# Exercice 10 — Construire PlayTrack, votre gestionnaire de jeux

## 1. Contexte

Vous allez réaliser `PlayTrack`, une application en console permettant de gérer une collection personnelle de jeux vidéo. L'utilisateur pourra ajouter des jeux, consulter sa collection, enregistrer une session, terminer un jeu et afficher ses statistiques.

Ce dernier exercice rassemble les notions étudiées précédemment dans une application structurée en plusieurs packages. Il introduit également Maven pour construire le projet et JUnit pour vérifier automatiquement les règles métier.

L'objectif n'est plus seulement d'obtenir un programme qui fonctionne : vous devez organiser le code, protéger les données, traiter les erreurs prévisibles et prouver les principaux comportements avec des tests.

## 2. Objectifs

Après cet exercice, vous saurez :

- organiser une application Java avec Maven et des packages ;
- représenter une liste fermée de valeurs avec une `enum` ;
- créer et lever une exception métier ;
- intercepter une exception avec `try` et `catch` ;
- construire un menu interactif avec `while` et `switch` ;
- séparer le modèle, les règles métier et l'interface console ;
- rechercher et modifier des objets dans une collection ;
- écrire des tests automatisés avec JUnit 5 ;
- lancer la compilation et les tests avec Maven.

## 3. Résultat attendu

### Menu principal

```text
=== PlayTrack ===
1. Ajouter un jeu
2. Afficher la collection
3. Enregistrer une session
4. Terminer un jeu
5. Afficher les statistiques
0. Quitter
Votre choix :
```

Le menu réapparaît après chaque action jusqu'à ce que l'utilisateur choisisse `0`.

### Ajouter un jeu

```text
Votre choix : 1

Titre : Hollow Knight
Genre : Metroidvania
Jeu ajouté avec succès.
```

Un nouveau jeu possède automatiquement le statut `A_COMMENCER` et zéro heure jouée.

### Refuser un doublon

```text
Votre choix : 1

Titre : hollow knight
Genre : Action
Erreur : le jeu « hollow knight » existe déjà.
```

La comparaison des titres ignore les majuscules et les minuscules.

### Afficher la collection

```text
Votre choix : 2

=== Collection ===
1. Hollow Knight - Metroidvania - A_COMMENCER - 0 heure
2. Minecraft - Survie - EN_COURS - 12 heures
3. Celeste - Plateforme - TERMINE - 9 heures
```

Si la collection est vide :

```text
Votre collection est vide.
```

### Enregistrer une session

```text
Votre choix : 3

Titre du jeu : Hollow Knight
Durée de la session en heures : 4
Session enregistrée.
Nouveau temps de jeu : 4 heures
Statut : EN_COURS
```

Une première session fait automatiquement passer le statut de `A_COMMENCER` à `EN_COURS`.

### Jeu introuvable

```text
Votre choix : 3

Titre du jeu : Inconnu
Durée de la session en heures : 2
Erreur : aucun jeu trouvé pour le titre « Inconnu ».
```

### Terminer un jeu

```text
Votre choix : 4

Titre du jeu : Hollow Knight
Le jeu « Hollow Knight » est maintenant terminé.
```

### Afficher les statistiques

```text
Votre choix : 5

=== Statistiques ===
Nombre de jeux : 3
Jeux terminés : 1
Temps de jeu total : 25 heures
Jeu le plus joué : Minecraft avec 12 heures
```

Pour une collection vide :

```text
=== Statistiques ===
Nombre de jeux : 0
Jeux terminés : 0
Temps de jeu total : 0 heure
Jeu le plus joué : aucun
```

### Quitter

```text
Votre choix : 0
À bientôt sur PlayTrack.
```

Un choix ne correspondant pas au menu affiche :

```text
Choix invalide.
```

## 4. Notions nécessaires

### Pourquoi utiliser Maven maintenant ?

Les premiers exercices contenaient peu de fichiers et n'utilisaient aucune bibliothèque externe. `javac` était donc suffisant.

PlayTrack contient plusieurs packages et des tests JUnit. Maven apporte désormais une véritable utilité :

- il impose une structure de projet connue ;
- il compile toutes les classes ;
- il télécharge JUnit ;
- il compile et exécute automatiquement les tests ;
- il produit un fichier JAR dans le dossier `target`.

Maven est un outil de construction. Il ne remplace ni Java, ni le JDK.

### Comprendre la structure Maven

Le projet utilisera cette structure :

```text
exercice-10/
├── README.md
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── fr/
    │           └── discoverjava/
    │               └── playtrack/
    │                   ├── app/
    │                   │   └── Application.java
    │                   ├── exception/
    │                   │   ├── JeuDejaPresentException.java
    │                   │   └── JeuIntrouvableException.java
    │                   ├── model/
    │                   │   ├── Jeu.java
    │                   │   └── StatutJeu.java
    │                   └── service/
    │                       └── BibliothequeJeux.java
    └── test/
        └── java/
            └── fr/
                └── discoverjava/
                    └── playtrack/
                        └── service/
                            └── BibliothequeJeuxTest.java
```

Maven reconnaît automatiquement :

- `src/main/java` pour le code de l'application ;
- `src/test/java` pour les tests ;
- `target` pour les fichiers générés.

Le dossier `target` ne doit pas être ajouté au dépôt Git.

### Configurer le projet avec `pom.xml`

Le fichier `pom.xml` décrit le projet et ses dépendances.

Créez-le à la racine d'`exercice-10` avec le contenu suivant :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>fr.discoverjava</groupId>
    <artifactId>playtrack</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.release>21</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>5.11.4</junit.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.5.2</version>
            </plugin>
        </plugins>
    </build>
</project>
```

La dépendance JUnit possède le scope `test` : elle est disponible pour les tests, mais ne fait pas partie du code principal.

### Déclarer un package

Un package organise les classes et évite les conflits de noms.

La déclaration doit être la première instruction du fichier, avant les imports :

```java
package fr.discoverjava.playtrack.model;
```

Le chemin doit correspondre au package :

```text
src/main/java/fr/discoverjava/playtrack/model/Jeu.java
```

Les noms de packages sont écrits en minuscules.

### Importer une classe d'un autre package

Une classe située dans un autre package doit être importée :

```java
package fr.discoverjava.playtrack.service;

import fr.discoverjava.playtrack.model.Jeu;
```

Les classes de `java.lang`, comme `String` et `RuntimeException`, sont importées automatiquement.

### Représenter un ensemble fermé avec une `enum`

Une énumération définit une liste limitée de valeurs autorisées :

```java
public enum EtatCommande {
    EN_ATTENTE,
    EXPEDIEE,
    LIVREE
}
```

Une variable ne peut contenir que l'une de ces valeurs :

```java
private EtatCommande etat;
```

```java
this.etat = EtatCommande.EN_ATTENTE;
```

Par convention, les constantes d'une `enum` sont écrites en majuscules.

Une `enum` est préférable à une chaîne libre : une faute comme `"EN_COUR"` devient impossible.

### Comparer une valeur d'énumération

Les valeurs d'une `enum` sont des constantes uniques. Elles peuvent être comparées avec `==` :

```java
if (etat == EtatCommande.LIVREE) {
    // Traitement
}
```

Cette règle diffère des objets `String`, dont le contenu se compare avec `equals` ou `equalsIgnoreCase`.

### Faire évoluer l'état d'un objet

La classe métier contrôle elle-même ses changements d'état :

```java
public void ajouterDuree(int heures) {
    heuresUtilisees += heures;

    if (etat == EtatCommande.EN_ATTENTE) {
        etat = EtatCommande.EXPEDIEE;
    }
}
```

Le code extérieur ne doit pas appeler un setter générique pour imposer n'importe quel statut. Il demande une action métier à l'objet.

### Comprendre les exceptions

Une exception représente une situation qui empêche une opération de se dérouler normalement.

Exemples :

- rechercher un objet inexistant ;
- ajouter un doublon interdit ;
- fournir une durée invalide.

Une exception interrompt le déroulement normal jusqu'à ce qu'elle soit interceptée.

### Créer une exception métier

Une exception personnalisée donne un nom clair à une erreur du domaine :

```java
public class ProduitIntrouvableException extends RuntimeException {
    public ProduitIntrouvableException(String message) {
        super(message);
    }
}
```

`super(message)` transmet le message au constructeur de `RuntimeException`.

Une exception qui étend `RuntimeException` est dite non vérifiée. Java n'oblige pas à l'ajouter à la signature avec `throws`, mais le programme doit l'intercepter à l'endroit adapté.

### Lever une exception avec `throw`

Le mot-clé `throw` déclenche une exception :

```java
if (produitExiste) {
    throw new ProduitDejaPresentException("Le produit existe déjà.");
}
```

Le code placé après `throw` dans la même exécution n'est pas atteint.

`throw` déclenche un objet exception. Il ne faut pas le confondre avec `throws`, utilisé dans une signature pour annoncer certaines exceptions.

### Intercepter avec `try` et `catch`

Le code susceptible d'échouer est placé dans `try` :

```java
try {
    catalogue.ajouter(produit);
    System.out.println("Produit ajouté.");
} catch (ProduitDejaPresentException exception) {
    System.out.println("Erreur : " + exception.getMessage());
}
```

Si l'exception apparaît, Java abandonne la suite du bloc `try` et exécute le `catch` correspondant.

La classe métier lève l'exception ; l'interface console décide comment présenter le message. Cette séparation évite de mélanger les règles métier et l'affichage.

### Rechercher un objet dans une collection

Une méthode peut parcourir une liste et retourner le premier objet correspondant :

```java
for (Produit produit : produits) {
    if (produit.getNom().equalsIgnoreCase(nomRecherche)) {
        return produit;
    }
}

throw new ProduitIntrouvableException("Produit introuvable");
```

La méthode retourne immédiatement l'objet trouvé. Si la boucle se termine sans résultat, elle lève l'exception.

### Utiliser une boucle `while` pour un menu

Une boucle `while` répète un bloc tant qu'une condition est vraie :

```java
boolean continuer = true;

while (continuer) {
    afficherMenu();
    int choix = scanner.nextInt();

    if (choix == 0) {
        continuer = false;
    }
}
```

Le menu ne connaît pas à l'avance le nombre de répétitions. `while` est donc plus adaptée qu'une boucle `for`.

Attention : une boucle dont la condition ne devient jamais fausse est une boucle infinie.

### Sélectionner une action avec `switch`

`switch` évite une longue chaîne de conditions lorsque plusieurs actions dépendent d'une même valeur :

```java
switch (choix) {
    case 1 -> afficherProduits();
    case 2 -> ajouterProduit();
    case 0 -> continuer = false;
    default -> System.out.println("Choix invalide.");
}
```

Cette syntaxe moderne avec `->` est disponible en Java 21. Chaque branche n'a pas besoin de `break`.

Pour exécuter plusieurs instructions, utilisez un bloc :

```java
case 1 -> {
    System.out.println("Ajout");
    ajouterProduit();
}
```

### Séparer les couches de l'application

| Package | Responsabilité |
|---|---|
| `model` | représenter les données et comportements d'un jeu |
| `service` | gérer la collection et les règles portant sur plusieurs jeux |
| `exception` | nommer les erreurs métier |
| `app` | afficher le menu et lire les saisies |

La classe `Application` ne doit pas parcourir directement la collection pour calculer les statistiques. Elle appelle les méthodes du service.

### Découvrir les tests automatisés

Un test automatisé exécute une petite partie du programme et vérifie un résultat attendu.

Avec JUnit 5 :

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatriceTest {
    @Test
    void doitAdditionnerDeuxNombres() {
        Calculatrice calculatrice = new Calculatrice();

        int resultat = calculatrice.additionner(2, 3);

        assertEquals(5, resultat);
    }
}
```

- `@Test` signale une méthode de test ;
- le nom explique le comportement vérifié ;
- `assertEquals` compare la valeur attendue à la valeur obtenue.

### Organiser un test avec Arrange, Act, Assert

Un test lisible suit généralement trois temps :

1. Arrange : préparer les objets et les données ;
2. Act : exécuter l'action testée ;
3. Assert : vérifier le résultat.

```java
@Test
void doitAjouterUnProduit() {
    // Arrange
    Catalogue catalogue = new Catalogue();
    Produit produit = new Produit("Clavier");

    // Act
    catalogue.ajouter(produit);

    // Assert
    assertEquals(1, catalogue.getNombreProduits());
}
```

Un test doit vérifier un comportement précis et rester indépendant des autres tests.

### Vérifier une exception avec `assertThrows`

JUnit peut vérifier qu'une action déclenche l'exception attendue :

```java
assertThrows(
        ProduitDejaPresentException.class,
        () -> catalogue.ajouter(produit)
);
```

`() -> ...` est une expression lambda. Ici, elle permet de transmettre à JUnit l'action à exécuter. Vous n'avez pas besoin de maîtriser toutes les formes de lambda pour réaliser cet exercice.

Import nécessaire :

```java
import static org.junit.jupiter.api.Assertions.assertThrows;
```

### Exécuter les tests avec Maven

Depuis le dossier contenant `pom.xml` :

```bash
mvn test
```

Maven compile le code principal, compile les tests et les exécute. Un résultat réussi se termine par :

```text
BUILD SUCCESS
```

`mvn clean test` supprime d'abord les anciens fichiers générés :

```bash
mvn clean test
```

## 5. Consignes fonctionnelles

### Règles d'un jeu

Un jeu possède un titre, un genre, un statut et un nombre d'heures jouées.

- le statut initial est `A_COMMENCER` ;
- le temps initial est `0` ;
- ajouter une durée strictement positive augmente le temps ;
- la première session fait passer `A_COMMENCER` à `EN_COURS` ;
- une session supplémentaire ne change pas un jeu déjà `TERMINE` ;
- terminer un jeu place son statut à `TERMINE`.

### Règles de la bibliothèque

- deux titres identiques sans tenir compte de la casse sont interdits ;
- une recherche de titre ignore la casse ;
- l'ajout d'un doublon lève `JeuDejaPresentException` ;
- une recherche sans résultat lève `JeuIntrouvableException` ;
- le jeu le plus joué est le premier possédant le maximum ;
- une collection vide ne possède aucun jeu le plus joué.

### Actions du menu

| Choix | Action |
|---:|---|
| 1 | ajouter un jeu |
| 2 | afficher la collection |
| 3 | enregistrer une session |
| 4 | terminer un jeu |
| 5 | afficher les statistiques |
| 0 | quitter |

## 6. Contraintes techniques

### Structure imposée

Respectez exactement la structure Maven et les packages présentés dans la section précédente.

### `StatutJeu`

Déclarez exactement :

```java
public enum StatutJeu {
    A_COMMENCER,
    EN_COURS,
    TERMINE
}
```

### `Jeu`

Déclarez les attributs privés :

```java
private String titre;
private String genre;
private StatutJeu statut;
private int heuresJouees;
```

Créez :

```java
public Jeu(String titre, String genre)

public String getTitre()
public String getGenre()
public StatutJeu getStatut()
public int getHeuresJouees()

public void enregistrerSession(int heures)
public void terminer()
```

`enregistrerSession` doit refuser silencieusement toute durée inférieure ou égale à zéro : elle ne modifie alors aucun attribut.

N'ajoutez aucun setter.

### Exceptions

Créez `JeuDejaPresentException` et `JeuIntrouvableException`. Elles étendent `RuntimeException` et possèdent chacune un constructeur recevant un message.

### `BibliothequeJeux`

Déclarez :

```java
private final ArrayList<Jeu> jeux;
```

Initialisez la collection dans un constructeur sans paramètre, puis créez :

```java
public void ajouterJeu(Jeu jeu)
public Jeu rechercherParTitre(String titre)
public int getNombreJeux()
public int calculerTempsTotal()
public int compterJeuxTermines()
public Jeu trouverJeuLePlusJoue()
public void afficherCollection()
```

Règles imposées :

- `ajouterJeu` utilise `rechercherParTitre` ou un parcours équivalent pour détecter les doublons ;
- `rechercherParTitre` lève l'exception prévue si aucun jeu ne correspond ;
- `trouverJeuLePlusJoue` retourne `null` pour une collection vide ;
- `afficherCollection` affiche un message spécifique si la liste est vide ;
- aucun getter ne doit exposer directement l'`ArrayList`.

### `Application`

- Elle contient `main` et les méthodes privées statiques utiles à l'interface console.
- Elle crée une seule instance de `BibliothequeJeux` et un seul `Scanner`.
- Elle utilise une boucle `while` pour maintenir le menu.
- Elle utilise un `switch` avec la syntaxe `case ... ->`.
- Elle consomme correctement les retours à la ligne après `nextInt()`.
- Elle intercepte les deux exceptions métier et affiche leur message.
- Elle ne contient aucune règle métier déjà portée par `Jeu` ou `BibliothequeJeux`.
- Elle ferme le scanner lorsque l'utilisateur quitte.

### Tests obligatoires

Dans `BibliothequeJeuxTest`, écrivez au minimum les huit tests suivants :

1. ajouter un jeu augmente la taille de la collection ;
2. ajouter deux fois le même titre avec une casse différente lève `JeuDejaPresentException` ;
3. rechercher un titre existant sans respecter sa casse retourne le bon objet ;
4. rechercher un titre absent lève `JeuIntrouvableException` ;
5. le temps total additionne les heures de tous les jeux ;
6. seuls les jeux terminés sont comptés ;
7. le jeu possédant le plus d'heures est retourné ;
8. une bibliothèque vide retourne `null` pour le jeu le plus joué.

N'utilisez pas d'héritage métier, d'interface métier, de base de données, de fichier de sauvegarde, de framework applicatif ou de bibliothèque autre que JUnit.

Depuis le dossier `exercice-10`, compilez et exécutez les tests avec :

```bash
mvn clean test
```

Construisez ensuite le projet :

```bash
mvn package
```

Lancez l'application avec :

```bash
java -cp target/classes fr.discoverjava.playtrack.app.Application
```

Ces commandes sont identiques sous macOS, Linux, PowerShell et l'invite de commandes Windows.

## 7. Étapes de réalisation

### Étape 1 — Créer le projet Maven

Créez la structure de dossiers et le `pom.xml`. Ajoutez les déclarations de package dans chaque fichier.

Vérification : lancez `mvn test` sur le projet encore vide ou contenant une classe minimale. Maven doit terminer sans erreur de configuration.

### Étape 2 — Créer l'énumération

Ajoutez les trois statuts dans `StatutJeu`.

Vérification : utilisez temporairement une valeur de l'énumération dans une variable.

### Étape 3 — Créer la classe `Jeu`

Ajoutez ses attributs, son constructeur, ses getters et ses deux comportements métier.

Vérification : créez un jeu et contrôlez successivement ses trois statuts possibles.

### Étape 4 — Créer les exceptions

Ajoutez les deux classes dans le package `exception` et transmettez leur message à `RuntimeException`.

### Étape 5 — Construire la bibliothèque

Créez la collection, l'ajout et la recherche. Comparez les titres avec `equalsIgnoreCase`.

Vérification : un titre absent et un doublon doivent lever les exceptions prévues.

### Étape 6 — Calculer les statistiques

Ajoutez les méthodes de total, de comptage et de recherche du maximum.

Vérification : traitez explicitement la collection vide.

### Étape 7 — Afficher la collection

Parcourez les jeux avec une boucle `for-each` et un compteur visible commençant à 1.

### Étape 8 — Construire le menu

Créez la boucle `while`, affichez les choix et distribuez les actions avec `switch`.

Vérification : le menu doit revenir après chaque opération et s'arrêter uniquement avec `0`.

### Étape 9 — Relier les actions métier

Ajoutez les saisies nécessaires à chaque action. Recherchez le jeu avant d'enregistrer une session ou de le terminer.

### Étape 10 — Gérer les erreurs

Interceptez les exceptions dans la couche console. Le programme doit afficher l'erreur puis revenir au menu au lieu de s'arrêter brutalement.

### Étape 11 — Écrire les tests

Créez la classe de test, suivez la structure Arrange, Act, Assert et implémentez les huit cas obligatoires.

### Étape 12 — Vérifier le projet complet

Lancez :

```bash
mvn clean test
```

Testez ensuite manuellement tous les parcours du menu.

## 8. Jeux d'essai

### Évolution d'un jeu

| Situation initiale | Action | Résultat attendu |
|---|---|---|
| 0 heure, `A_COMMENCER` | session de 4 heures | 4 heures, `EN_COURS` |
| 4 heures, `EN_COURS` | session de 3 heures | 7 heures, `EN_COURS` |
| 7 heures, `EN_COURS` | terminer | 7 heures, `TERMINE` |
| 7 heures, `TERMINE` | session de 2 heures | 9 heures, `TERMINE` |
| 5 heures | session de 0 heure | aucun changement |
| 5 heures | session de -2 heures | aucun changement |

### Recherche et doublons

| Collection | Recherche ou ajout | Résultat attendu |
|---|---|---|
| `Hollow Knight` | rechercher `hollow knight` | objet trouvé |
| `Minecraft` | ajouter `MINECRAFT` | `JeuDejaPresentException` |
| collection vide | rechercher `Celeste` | `JeuIntrouvableException` |

### Statistiques

| Jeux | Total | Terminés | Plus joué |
|---|---:|---:|---|
| aucun | 0 | 0 | `null` |
| Minecraft : 12 h | 12 | 0 | Minecraft |
| Minecraft : 12 h, Celeste : 9 h terminé, Hades : 15 h terminé | 36 | 2 | Hades |
| A : 10 h, B : 10 h | 20 | 0 | A |

Le dernier cas vérifie que la première occurrence du maximum est conservée.

## 9. Critères de validation

- [ ] La structure Maven et les packages correspondent à l'énoncé.
- [ ] Le projet utilise Java 21 et JUnit 5.
- [ ] `mvn clean test` se termine par `BUILD SUCCESS`.
- [ ] Les trois statuts sont définis dans une `enum`.
- [ ] Les attributs de `Jeu` sont privés et aucun setter n'existe.
- [ ] Les changements de statut respectent toutes les règles.
- [ ] Les durées nulles ou négatives ne modifient pas le jeu.
- [ ] La collection reste encapsulée dans le service.
- [ ] Les titres sont comparés sans tenir compte de la casse.
- [ ] Les deux exceptions métier sont créées et utilisées.
- [ ] L'application intercepte les exceptions sans s'arrêter.
- [ ] Le menu utilise `while` et `switch`.
- [ ] Le choix `0` arrête réellement la boucle.
- [ ] Les statistiques sont calculées par le service.
- [ ] La collection vide est correctement traitée.
- [ ] La première occurrence du maximum est conservée.
- [ ] Les huit tests obligatoires sont présents et indépendants.
- [ ] Les tests vérifient les résultats et les exceptions.
- [ ] Aucun code de saisie n'est placé dans les classes métier.
- [ ] Aucun calcul métier n'est recopié dans `Application`.
- [ ] `target` n'est pas versionné.

## 10. Erreurs fréquentes

### Maven ne trouve pas le projet

Exécutez les commandes depuis le dossier contenant `pom.xml`.

### Une classe n'est pas trouvée

Vérifiez que son chemin correspond exactement à sa déclaration `package` et que l'import utilise son nom complet.

### Les tests ne sont pas exécutés

Le fichier doit se trouver sous `src/test/java`, utiliser `@Test` et porter un nom reconnu comme `BibliothequeJeuxTest`.

### Le statut est comparé avec du texte

Comparez directement les valeurs de `StatutJeu` avec `==`, sans transformer l'état en chaîne.

### Le doublon n'est pas détecté

Utilisez `equalsIgnoreCase` pour comparer le titre reçu avec chaque titre existant.

### Le programme s'arrête sur une recherche absente

L'exception métier doit être interceptée dans l'interface console avec un `catch` adapté.

### Le menu tourne sans pouvoir quitter

La branche `case 0` doit modifier la variable contrôlant la boucle `while`.

### Une saisie textuelle est ignorée

Après `nextInt()`, consommez le retour à la ligne avant d'appeler `nextLine()`.

### Le jeu le plus joué provoque une erreur sur une liste vide

Retournez `null` avant de chercher un premier élément, puis vérifiez ce résultat dans l'affichage.

### Un test dépend d'un autre test

Créez une nouvelle bibliothèque dans chaque méthode de test. JUnit ne garantit pas l'ordre d'exécution des tests.

### `assertThrows` ne détecte rien

Vérifiez que l'action susceptible de lever l'exception se trouve bien dans la lambda transmise à `assertThrows`.

## 11. Défis supplémentaires

### Défi 1 — Supprimer un jeu

Ajoutez une action au menu et une méthode métier qui supprime un jeu recherché par son titre. Ajoutez les tests correspondants.

### Défi 2 — Filtrer par statut

Permettez d'afficher uniquement les jeux `A_COMMENCER`, `EN_COURS` ou `TERMINE`. Convertissez proprement le choix utilisateur vers l'énumération.

### Défi 3 — Sauvegarder la collection

Enregistrez la collection dans un fichier texte à la fermeture et rechargez-la au démarrage. Ce défi introduit les entrées-sorties et doit gérer les erreurs de lecture ou d'écriture.

## 12. Ce qu'il faut retenir

Une application Java structurée sépare les données, les règles métier, les erreurs et l'interaction avec l'utilisateur. Les packages rendent cette organisation explicite. Une `enum` protège les valeurs autorisées et les exceptions métier signalent clairement les opérations impossibles.

Maven automatise la construction du projet et la gestion des dépendances. JUnit permet de vérifier les comportements importants à chaque modification. Les tests ne remplacent pas les essais manuels de l'interface console, mais ils sécurisent durablement les règles métier.

Ce projet mobilise l'ensemble du parcours : variables, conditions, boucles, méthodes, collections, objets, encapsulation, polymorphisme, exceptions et tests automatisés.

