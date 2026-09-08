# Exercice 6 — Suivre l'avancement d'un jeu vidéo

## 1. Contexte

Une application permet aux joueurs de suivre les jeux présents dans leur collection. Pour chaque jeu, elle mémorise son titre, son genre, son prix d'achat, le nombre d'heures jouées et son statut.

Vous devez créer une première véritable application orientée objet. Une classe `JeuVideo` représentera le jeu et contiendra les traitements qui le concernent. Une classe `SuiviJeu` contiendra la méthode `main`, récupérera les saisies et utilisera l'objet.

Le programme créera un jeu, affichera sa fiche initiale, enregistrera une session de jeu, permettra de le marquer comme terminé, puis affichera sa fiche mise à jour et son coût par heure de jeu.

## 2. Objectifs

Après cet exercice, vous saurez :

- distinguer une classe d'un objet ;
- créer une classe métier dans un fichier séparé ;
- déclarer des attributs privés ;
- définir et utiliser un constructeur ;
- employer le mot-clé `this` ;
- créer un objet avec `new` ;
- appeler des méthodes sur un objet ;
- modifier l'état d'un objet grâce à ses méthodes ;
- répartir les responsabilités entre une classe métier et une classe d'exécution ;
- compiler et exécuter un programme composé de plusieurs classes.

## 3. Résultat attendu

### Jeu marqué comme terminé

```text
=== Suivi d'un jeu vidéo ===

Titre du jeu : Hollow Knight
Genre : Metroidvania
Prix d'achat en euros entiers : 30

=== Fiche initiale ===
Titre : Hollow Knight
Genre : Metroidvania
Prix d'achat : 30 euros
Temps de jeu : 0 heure
Statut : À commencer
Coût par heure : non disponible

Combien d'heures avez-vous joué pendant cette session ? 10
Avez-vous terminé le jeu ? oui

=== Fiche mise à jour ===
Titre : Hollow Knight
Genre : Metroidvania
Prix d'achat : 30 euros
Temps de jeu : 10 heures
Statut : Terminé
Coût par heure : 3.0 euros
```

### Jeu commencé mais non terminé

```text
=== Suivi d'un jeu vidéo ===

Titre du jeu : Minecraft
Genre : Survie
Prix d'achat en euros entiers : 24

=== Fiche initiale ===
Titre : Minecraft
Genre : Survie
Prix d'achat : 24 euros
Temps de jeu : 0 heure
Statut : À commencer
Coût par heure : non disponible

Combien d'heures avez-vous joué pendant cette session ? 8
Avez-vous terminé le jeu ? non

=== Fiche mise à jour ===
Titre : Minecraft
Genre : Survie
Prix d'achat : 24 euros
Temps de jeu : 8 heures
Statut : En cours
Coût par heure : 3.0 euros
```

Pour cet exercice, la réponse à la dernière question doit être `oui` ou `non`. Les majuscules et minuscules ne doivent pas avoir d'importance.

## 4. Notions nécessaires

### Passer d'un programme procédural à des objets

Dans les exercices précédents, les données étaient placées dans des variables de `main`, puis transmises à des méthodes. Cette organisation convient aux petits programmes, mais elle devient difficile à maintenir lorsque plusieurs données décrivent une même chose.

La programmation orientée objet regroupe :

- les données décrivant un élément ;
- les comportements capables d'utiliser ou de modifier ces données.

Par exemple, une classe représentant un compte musical peut regrouper son propriétaire, son nombre d'abonnés et les actions permettant d'ajouter un abonné ou d'afficher son profil.

### Distinguer une classe et un objet

Une classe est un modèle. Elle décrit les informations et les comportements que posséderont les objets créés à partir d'elle.

Un objet est une instance concrète de cette classe.

```java
public class Album {
    private String titre;
    private String artiste;
}
```

La classe `Album` définit un modèle. Chaque objet créé à partir de cette classe pourra représenter un album différent.

```java
Album premierAlbum = new Album();
Album secondAlbum = new Album();
```

`premierAlbum` et `secondAlbum` sont deux objets distincts. Modifier l'un ne modifie pas automatiquement l'autre.

### Créer une classe dans son propre fichier

Une classe publique doit être enregistrée dans un fichier portant exactement le même nom.

```text
src/
├── Album.java
└── Application.java
```

Dans `Album.java` :

```java
public class Album {
    // Contenu de la classe
}
```

Dans `Application.java` :

```java
public class Application {
    public static void main(String[] args) {
        // Démarrage du programme
    }
}
```

Les deux classes se trouvent ici dans le même dossier et n'utilisent pas de package. Elles peuvent donc se référencer directement.

### Déclarer des attributs

Un attribut est une variable appartenant à un objet. Il est déclaré directement dans la classe, en dehors des méthodes.

```java
public class ProfilSportif {
    private String nom;
    private int nombreSeances;
    private boolean actif;
}
```

Chaque objet `ProfilSportif` possède ses propres valeurs pour ces trois attributs.

Les attributs décrivent l'état de l'objet. Dans l'exercice, l'état du jeu évoluera lorsque du temps de jeu sera ajouté ou lorsque le jeu sera terminé.

### Protéger les attributs avec `private`

Le mot-clé `private` empêche les autres classes d'accéder directement à un attribut.

```java
private int nombreAbonnes;
```

Depuis une autre classe, l'instruction suivante sera interdite :

```java
compte.nombreAbonnes = -500; // accès interdit
```

L'objet contrôle plutôt son évolution grâce à ses propres méthodes :

```java
compte.ajouterAbonne();
```

Cette protection s'appelle l'encapsulation. Elle évite qu'une autre partie du programme place directement l'objet dans un état incohérent.

Dans cet exercice, tous les attributs de `JeuVideo` doivent être `private`.

### Définir un constructeur

Le constructeur est appelé au moment de la création d'un objet. Il initialise son état de départ.

```java
public class Film {
    private String titre;
    private int annee;

    public Film(String titre, int annee) {
        this.titre = titre;
        this.annee = annee;
    }
}
```

Un constructeur :

- porte exactement le même nom que la classe ;
- ne possède aucun type de retour, pas même `void` ;
- peut recevoir des paramètres ;
- est exécuté automatiquement avec `new`.

Erreur fréquente : écrire `public void Film(...)`. Il s'agirait alors d'une méthode ordinaire et non d'un constructeur.

### Comprendre le mot-clé `this`

Dans un constructeur, un paramètre et un attribut peuvent porter le même nom :

```java
public Film(String titre, int annee) {
    this.titre = titre;
    this.annee = annee;
}
```

- `this.titre` désigne l'attribut de l'objet en cours de création ;
- `titre` désigne le paramètre reçu ;
- l'affectation place donc la valeur du paramètre dans l'attribut.

Sans `this`, l'instruction suivante réaffecterait seulement le paramètre à lui-même :

```java
titre = titre;
```

L'attribut conserverait alors sa valeur par défaut.

### Initialiser certains attributs sans paramètre

Toutes les valeurs ne doivent pas nécessairement être demandées au constructeur. Un objet peut posséder un état initial fixé par la classe.

```java
public Compte(String proprietaire) {
    this.proprietaire = proprietaire;
    this.solde = 0;
    this.bloque = false;
}
```

Ici, le propriétaire est fourni lors de la création, tandis que le solde et le statut reçoivent des valeurs initiales cohérentes.

Dans l'exercice, un nouveau jeu n'a encore aucune heure jouée et n'est pas terminé.

### Créer un objet avec `new`

L'appel du constructeur s'effectue avec `new` :

```java
Film film = new Film("Dune", 2021);
```

Cette instruction :

1. déclare une variable de type `Film` ;
2. crée un nouvel objet en mémoire ;
3. appelle le constructeur avec les valeurs fournies ;
4. conserve une référence vers l'objet dans la variable `film`.

Le nombre, l'ordre et le type des arguments doivent correspondre aux paramètres du constructeur.

### Comprendre une référence d'objet

Une variable de type objet ne contient pas directement tous les attributs. Elle contient une référence permettant de retrouver l'objet en mémoire.

```java
Film filmPrefere = new Film("Dune", 2021);
Film autreReference = filmPrefere;
```

Les deux variables désignent ici le même objet. Une modification réalisée par l'une sera visible par l'autre.

La valeur spéciale `null` signifie qu'une variable ne référence aucun objet :

```java
Film film = null;
```

Appeler une méthode sur `null` provoquerait une `NullPointerException`. Dans l'exercice, l'objet doit être créé avant tout appel de méthode.

### Créer une méthode d'instance

Une méthode d'instance représente un comportement d'un objet. Contrairement aux méthodes des exercices précédents, elle ne porte pas le mot-clé `static`.

```java
public class Compte {
    private int solde;

    public void deposer(int montant) {
        solde += montant;
    }
}
```

La méthode agit directement sur l'attribut de l'objet concerné.

Elle est appelée à partir d'un objet avec l'opérateur `.` :

```java
Compte compte = new Compte();
compte.deposer(50);
```

Si deux comptes existent, chaque appel ne modifie que l'objet placé avant le point.

### Modifier l'état de l'objet

Une méthode peut faire évoluer un ou plusieurs attributs :

```java
public void enregistrerSeance(int duree) {
    nombreMinutes += duree;
}
```

Il ne faut pas créer une nouvelle variable locale portant le même nom que l'attribut :

```java
public void enregistrerSeance(int duree) {
    int nombreMinutes = duree; // ne modifie pas l'attribut
}
```

Lorsque le nom du paramètre est différent, `this` n'est pas obligatoire, mais peut être utilisé pour rendre l'accès à l'attribut explicite :

```java
this.nombreMinutes += duree;
```

### Retourner une information calculée par l'objet

Une méthode d'instance peut lire les attributs et retourner un résultat :

```java
public double calculerPrixMoyen() {
    if (quantite == 0) {
        return 0.0;
    }

    return (double) prixTotal / quantite;
}
```

Cette méthode ne reçoit aucun paramètre, car les informations nécessaires sont déjà stockées dans l'objet.

Le contrôle de la valeur zéro évite une division impossible ou un résultat incohérent.

### Retourner un état sous forme de texte

Un attribut `boolean` peut être transformé en texte lisible :

```java
public String obtenirStatut() {
    if (livre) {
        return "Livré";
    } else {
        return "En préparation";
    }
}
```

Dans une condition, il n'est pas nécessaire d'écrire `livre == true`. Écrire directement `if (livre)` est plus lisible.

### Comparer une saisie textuelle

Pour comparer le contenu de deux chaînes, utilisez `equals` ou `equalsIgnoreCase`, et non `==`.

```java
String reponse = "OUI";

if (reponse.equalsIgnoreCase("oui")) {
    System.out.println("Réponse positive");
}
```

`equalsIgnoreCase` ignore les différences de majuscules et de minuscules.

`==` compare les références des objets `String` et peut produire un résultat inattendu même si les textes semblent identiques.

### Gérer le retour à la ligne après `nextInt()`

Après une lecture avec `nextInt()`, le retour à la ligne validé par Entrée reste disponible. Avant de lire la réponse `oui` ou `non` avec `nextLine()`, consommez-le :

```java
int quantite = scanner.nextInt();
scanner.nextLine();

String confirmation = scanner.nextLine();
```

Sans cette ligne intermédiaire, la confirmation pourrait être lue comme une chaîne vide.

### Séparer les responsabilités

La classe métier représente le concept et protège son état. La classe contenant `main` gère l'interaction avec l'utilisateur.

| Classe | Responsabilités |
|---|---|
| Classe métier | conserver les attributs, appliquer les règles, calculer et afficher sa fiche |
| Classe d'exécution | créer le scanner, poser les questions, créer l'objet et appeler ses méthodes |

La classe `SuiviJeu` ne doit donc pas modifier directement les attributs. Elle demande à l'objet d'effectuer les actions.

## 5. Consignes fonctionnelles

La classe `JeuVideo` doit représenter :

- un titre ;
- un genre ;
- un prix d'achat en euros entiers ;
- un nombre d'heures jouées ;
- un état indiquant si le jeu est terminé.

Lors de sa création :

- le titre, le genre et le prix sont fournis au constructeur ;
- le nombre d'heures jouées vaut `0` ;
- le jeu n'est pas terminé.

Le statut affiché doit respecter les règles suivantes :

| État du jeu | Statut |
|---|---|
| 0 heure jouée et non terminé | À commencer |
| au moins 1 heure jouée et non terminé | En cours |
| jeu terminé | Terminé |

Le coût par heure correspond au prix d'achat divisé par le nombre d'heures jouées. Tant qu'aucune heure n'a été enregistrée, la fiche doit afficher `Coût par heure : non disponible`.

La classe `SuiviJeu` doit :

1. demander le titre, le genre et le prix ;
2. créer un objet `JeuVideo` ;
3. afficher sa fiche initiale ;
4. demander la durée d'une session en heures entières ;
5. ajouter cette durée au jeu grâce à une méthode ;
6. demander si le jeu est terminé ;
7. mettre à jour son état si la réponse est `oui` ;
8. afficher la fiche mise à jour.

Vous pouvez considérer que le prix est positif ou nul, que la durée de session est strictement positive et que la réponse finale est `oui` ou `non`.

## 6. Contraintes techniques

Créez exactement deux fichiers :

```text
exercice-6/
└── src/
    ├── JeuVideo.java
    └── SuiviJeu.java
```

### Classe `JeuVideo`

Déclarez exactement les cinq attributs privés suivants :

```java
private String titre;
private String genre;
private int prixAchat;
private int heuresJouees;
private boolean termine;
```

Créez un constructeur avec la signature suivante :

```java
public JeuVideo(String titre, String genre, int prixAchat)
```

Créez également les méthodes d'instance suivantes :

```java
public void ajouterTempsJeu(int heures)

public void terminerJeu()

public double calculerCoutParHeure()

public String obtenirStatut()

public void afficherFiche()
```

Règles imposées :

- `ajouterTempsJeu` ajoute les heures reçues à la valeur existante ;
- `terminerJeu` place l'attribut `termine` à `true` ;
- `calculerCoutParHeure` retourne `0.0` si aucune heure n'a encore été jouée ;
- sinon, `calculerCoutParHeure` effectue une division décimale ;
- `obtenirStatut` applique les trois règles du tableau des statuts ;
- `afficherFiche` appelle `obtenirStatut` et `calculerCoutParHeure` ;
- `afficherFiche` affiche `non disponible` lorsque le temps de jeu vaut zéro ;
- aucune de ces méthodes ne doit être `static`.

### Classe `SuiviJeu`

- Elle contient la méthode `main`.
- Elle utilise `Scanner` pour récupérer les saisies.
- Elle crée un seul objet `JeuVideo` avec `new`.
- Elle n'accède jamais directement aux attributs de l'objet.
- Elle appelle les méthodes publiques de l'objet avec l'opérateur `.`.
- Elle compare la réponse finale avec `equalsIgnoreCase`.
- Elle gère correctement le retour à la ligne après la saisie de la durée.
- Elle ferme le scanner après la dernière saisie.

N'ajoutez aucun package, héritage, getter, setter, tableau, collection ou classe supplémentaire.

Depuis `exercice-6`, compilez simultanément les deux classes :

```bash
javac -d out src/JeuVideo.java src/SuiviJeu.java
```

Exécutez ensuite la classe qui contient `main` :

```bash
java -cp out SuiviJeu
```

Sous Windows :

```powershell
javac -d out src\JeuVideo.java src\SuiviJeu.java
java -cp out SuiviJeu
```

Il ne faut pas exécuter `JeuVideo`, car cette classe ne contient pas de méthode `main`.

## 7. Étapes de réalisation

### Étape 1 — Créer les deux classes

Créez les deux fichiers et déclarez une classe publique dans chacun d'eux. Ajoutez `main` uniquement dans `SuiviJeu`.

Vérification : les noms des classes et des fichiers doivent correspondre exactement.

### Étape 2 — Déclarer l'état du jeu

Ajoutez les cinq attributs privés dans `JeuVideo`.

Vérification : aucun attribut ne doit être déclaré dans une méthode ni être accessible directement depuis `SuiviJeu`.

### Étape 3 — Construire l'objet

Créez le constructeur, utilisez `this` pour initialiser les trois informations reçues et donnez leurs valeurs initiales aux deux autres attributs.

Vérification : le constructeur ne possède aucun type de retour.

### Étape 4 — Créer et afficher un jeu

Dans `main`, récupérez les trois premières saisies et utilisez-les pour créer l'objet. Ajoutez provisoirement un appel à `afficherFiche` après avoir créé cette méthode.

Vérification : la fiche initiale doit afficher 0 heure et le statut `À commencer`.

### Étape 5 — Ajouter du temps de jeu

Créez `ajouterTempsJeu`, puis demandez la durée de session dans `main` et transmettez-la à l'objet.

Vérification : après l'ajout de 5 heures, la fiche doit afficher 5 heures, pas remplacer une valeur précédente de manière incorrecte.

### Étape 6 — Déterminer le statut

Créez `obtenirStatut` en appliquant les priorités correctes. Un jeu terminé doit toujours avoir le statut `Terminé`, quel que soit son temps de jeu.

Vérification : contrôlez les trois états possibles.

### Étape 7 — Calculer le coût par heure

Créez `calculerCoutParHeure`. Traitez d'abord le cas sans heure jouée, puis effectuez une division décimale dans les autres cas.

Vérification : un jeu acheté 30 euros et joué 10 heures doit retourner `3.0`.

### Étape 8 — Terminer le jeu

Créez `terminerJeu`. Dans `main`, lisez la réponse après avoir consommé le retour à la ligne, puis appelez cette méthode uniquement si la réponse vaut `oui` sans tenir compte de la casse.

Vérification : `oui`, `OUI` et `Oui` doivent tous terminer le jeu ; `non` doit le laisser en cours.

### Étape 9 — Finaliser la fiche

Complétez `afficherFiche` avec tous les attributs, le statut et le coût par heure. Gérez le cas particulier où le coût n'est pas encore disponible.

Vérification : la classe d'exécution doit simplement appeler `jeu.afficherFiche()` sans reconstruire elle-même les informations.

### Étape 10 — Compiler et tester

Compilez les deux fichiers ensemble et exécutez `SuiviJeu`. Réalisez tous les jeux d'essai.

## 8. Jeux d'essai

| Prix | Heures ajoutées | Réponse | Statut final | Coût par heure |
|---:|---:|---|---|---:|
| 30 | 10 | oui | Terminé | 3.0 euros |
| 24 | 8 | non | En cours | 3.0 euros |
| 60 | 12 | OUI | Terminé | 5.0 euros |
| 50 | 3 | non | En cours | environ 16.6667 euros |
| 0 | 5 | non | En cours | 0.0 euro |

Vérifiez également la fiche avant l'ajout des heures : elle doit toujours afficher `À commencer` et `Coût par heure : non disponible`.

Pour le prix de 50 euros et 3 heures, le nombre exact de décimales affichées peut dépendre de votre choix d'affichage. Le calcul ne doit cependant pas utiliser une division entière.

## 9. Critères de validation

- [ ] Les fichiers `JeuVideo.java` et `SuiviJeu.java` existent dans `src`.
- [ ] Chaque fichier contient la classe publique portant le même nom.
- [ ] Seule `SuiviJeu` contient `main`.
- [ ] Les cinq attributs imposés existent et sont privés.
- [ ] Le constructeur initialise correctement tout l'état de l'objet.
- [ ] `this` distingue les paramètres des attributs.
- [ ] L'objet est créé avec les valeurs saisies.
- [ ] Aucun attribut n'est lu ou modifié directement depuis `SuiviJeu`.
- [ ] Toutes les méthodes de `JeuVideo` sont des méthodes d'instance.
- [ ] L'ajout de temps augmente la valeur existante.
- [ ] Les trois statuts sont correctement déterminés.
- [ ] Le coût par heure utilise une division décimale.
- [ ] La division par zéro est évitée.
- [ ] La fiche initiale indique que le coût est indisponible.
- [ ] La réponse est comparée avec `equalsIgnoreCase`.
- [ ] Le retour à la ligne après `nextInt()` est correctement traité.
- [ ] La fiche est affichée par l'objet lui-même.
- [ ] Les deux classes compilent avec la commande fournie.
- [ ] Tous les jeux d'essai produisent les comportements attendus.
- [ ] Le scanner est fermé après la dernière saisie.
- [ ] Aucun package, getter, setter, héritage ou collection n'a été ajouté.

## 10. Erreurs fréquentes

### Le constructeur possède `void`

Un constructeur ne déclare aucun type de retour. S'il possède `void`, Java le considère comme une méthode ordinaire et ne l'appelle pas automatiquement avec `new`.

### Les attributs restent vides ou à zéro

Vérifiez les affectations du constructeur. Lorsque le paramètre et l'attribut portent le même nom, utilisez `this.attribut = parametre`.

### `non-static variable cannot be referenced from a static context`

`main` est statique, mais les attributs appartiennent à un objet. Créez d'abord un objet, puis appelez ses méthodes avec la variable qui le référence.

### `private access` apparaît à la compilation

La classe `SuiviJeu` essaie probablement d'accéder directement à un attribut. Utilisez les méthodes publiques prévues par `JeuVideo`.

### La méthode ne modifie pas les heures de l'objet

Vérifiez que vous mettez à jour l'attribut et que vous ne déclarez pas une nouvelle variable locale portant le même nom.

### Le statut reste `À commencer`

La méthode de statut doit consulter la valeur actuelle des attributs à chaque appel. Ne conservez pas le statut dans une variable créée une seule fois au démarrage.

### Le jeu terminé apparaît `En cours`

Vérifiez l'ordre des conditions. Le statut `Terminé` est prioritaire dès que l'attribut booléen vaut `true`.

### Une réponse `oui` est ignorée

Consommez le retour à la ligne laissé après `nextInt()`, puis comparez le contenu avec `equalsIgnoreCase`, pas avec `==`.

### Le coût par heure est arrondi à un entier

Le prix et les heures sont des `int`. Convertissez l'une des deux valeurs en `double` avant d'effectuer la division.

### Le programme ne trouve pas la classe `JeuVideo`

Vérifiez que les deux fichiers sont compilés ensemble, qu'ils se trouvent dans le même dossier et qu'aucun package n'a été ajouté.

### Java refuse d'exécuter `JeuVideo`

Cette classe ne contient volontairement pas de méthode `main`. Exécutez la classe `SuiviJeu`.

## 11. Défis supplémentaires

### Défi 1 — Ajouter une note

Ajoutez un attribut `note` initialisé à zéro et une méthode permettant d'attribuer une note comprise entre 1 et 5 une fois le jeu terminé.

### Défi 2 — Ajouter plusieurs sessions

Utilisez une boucle dans `SuiviJeu` pour demander plusieurs durées successives jusqu'à ce que l'utilisateur saisisse `0`. Chaque durée positive doit être transmise à `ajouterTempsJeu`.

### Défi 3 — Comparer deux jeux

Créez deux objets `JeuVideo` possédant des informations différentes. Ajoutez du temps à chacun et comparez leurs coûts par heure grâce à la valeur retournée par `calculerCoutParHeure`.

## 12. Ce qu'il faut retenir

Une classe définit un modèle regroupant un état et des comportements. Un objet est une instance créée à partir de ce modèle avec `new`. Son constructeur garantit son état initial, tandis que ses méthodes d'instance permettent de le consulter ou de le faire évoluer.

Les attributs privés protègent l'état interne. Le code extérieur n'effectue pas directement les modifications : il demande à l'objet de réaliser une action par l'intermédiaire de ses méthodes publiques. Cette encapsulation constitue l'un des principes fondamentaux de la programmation orientée objet.

