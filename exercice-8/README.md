# Exercice 8 — Construire un catalogue de streaming

## 1. Contexte

Une plateforme de streaming souhaite gérer dans un même catalogue des films et des séries. Ces contenus possèdent des informations communes, comme un titre, un genre et une durée totale, mais chacun possède également ses propres caractéristiques : un film a un réalisateur, tandis qu'une série possède un nombre d'épisodes.

Vous devez concevoir une application orientée objet capable de manipuler tous ces contenus à travers un type commun. Une classe abstraite regroupera ce qui est partagé, deux classes spécialisées représenteront les films et les séries, et un catalogue conservera tous les objets dans une même collection.

## 2. Objectifs

Après cet exercice, vous saurez :

- identifier les caractéristiques communes à plusieurs classes ;
- créer une classe mère abstraite ;
- faire hériter une classe avec `extends` ;
- appeler le constructeur parent avec `super` ;
- déclarer et redéfinir une méthode abstraite ;
- utiliser l'annotation `@Override` ;
- comprendre le polymorphisme ;
- conserver différents types d'objets dans une même collection ;
- appeler une méthode redéfinie sans tester le type réel de l'objet.

## 3. Résultat attendu

### Catalogue contenant un film et une série

```text
=== Création du catalogue de streaming ===

Combien de contenus souhaitez-vous ajouter ? 2

Contenu 1
Type (film/serie) : film
Titre : Dune
Genre : Science-fiction
Durée en minutes : 155
Réalisateur : Denis Villeneuve

Contenu 2
Type (film/serie) : serie
Titre : Arcane
Genre : Animation
Nombre d'épisodes : 9
Durée d'un épisode en minutes : 40

=== Catalogue ===
1. Film : Dune
   Genre : Science-fiction
   Durée : 155 minutes
   Réalisateur : Denis Villeneuve

2. Série : Arcane
   Genre : Animation
   Épisodes : 9
   Durée par épisode : 40 minutes
   Durée totale : 360 minutes

Nombre de contenus : 2
Durée totale du catalogue : 515 minutes
```

### Type de contenu invalide

```text
Contenu 1
Type (film/serie) : podcast
Erreur : le type doit être film ou serie.
```

Si le type est invalide, aucun objet n'est créé pour cette saisie. Le programme continue avec le contenu suivant. Le nombre final de contenus peut donc être inférieur au nombre initialement annoncé.

### Nombre de contenus invalide

```text
=== Création du catalogue de streaming ===

Combien de contenus souhaitez-vous ajouter ? 6

Erreur : le nombre de contenus doit être compris entre 1 et 5.
```

## 4. Notions nécessaires

### Identifier une relation d'héritage

L'héritage est pertinent lorsque plusieurs classes représentent des variantes d'un même concept.

Un film **est un** contenu. Une série **est un** contenu. Ils peuvent donc partager une classe mère `Contenu`.

L'héritage ne doit pas être utilisé pour une simple relation de possession. Une équipe possède des joueurs, mais une équipe n'est pas un joueur : l'exercice précédent utilisait donc la composition.

| Relation | Modélisation adaptée |
|---|---|
| un film est un contenu | héritage |
| une équipe possède des joueurs | composition |

### Créer une classe mère

Une classe mère regroupe les attributs et comportements communs :

```java
public class Media {
    private String titre;
    private int dureeMinutes;

    public Media(String titre, int dureeMinutes) {
        this.titre = titre;
        this.dureeMinutes = dureeMinutes;
    }

    public String getTitre() {
        return titre;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }
}
```

Les classes enfants héritent des méthodes publiques de cette classe.

### Déclarer une classe abstraite

Une classe abstraite représente un concept général qui ne doit pas être instancié directement.

```java
public abstract class Media {
    // Attributs, constructeur et méthodes communes
}
```

L'instruction suivante devient interdite :

```java
Media media = new Media(...); // impossible si Media est abstraite
```

En revanche, une variable de type `Media` peut référencer un objet d'une classe enfant :

```java
Media media = new Documentaire(...);
```

Dans l'exercice, il n'existe pas de contenu sans type précis. `Contenu` sera donc abstraite.

### Faire hériter une classe avec `extends`

Le mot-clé `extends` indique qu'une classe hérite d'une autre :

```java
public class Documentaire extends Media {
    private String sujet;
}
```

`Documentaire` récupère les méthodes publiques de `Media` et peut ajouter ses propres attributs et méthodes.

Java autorise une classe à étendre une seule classe mère à la fois.

### Appeler le constructeur parent avec `super`

Les attributs privés de la classe mère doivent être initialisés par son propre constructeur. La classe enfant l'appelle avec `super` :

```java
public Documentaire(String titre, int dureeMinutes, String sujet) {
    super(titre, dureeMinutes);
    this.sujet = sujet;
}
```

L'appel à `super(...)` doit être la première instruction du constructeur enfant.

- `super(...)` appelle le constructeur de la classe mère ;
- `this.sujet` initialise l'attribut propre à la classe enfant.

Erreur fréquente : essayer d'affecter directement un attribut privé de la classe mère depuis la classe enfant.

### Déclarer une méthode abstraite

Une classe abstraite peut imposer un comportement sans en fournir l'implémentation :

```java
public abstract void afficherDetails();
```

Une méthode abstraite :

- utilise le mot-clé `abstract` ;
- ne possède pas de bloc d'instructions ;
- se termine par un point-virgule ;
- oblige chaque classe enfant concrète à fournir sa propre version.

La classe contenant une méthode abstraite doit elle-même être abstraite.

### Redéfinir une méthode avec `@Override`

La classe enfant fournit l'implémentation demandée :

```java
@Override
public void afficherDetails() {
    System.out.println("Documentaire : " + getTitre());
}
```

`@Override` indique au compilateur que la méthode doit redéfinir une méthode héritée. Si le nom ou les paramètres sont incorrects, Java signale l'erreur.

La signature doit correspondre à celle déclarée dans la classe mère.

### Accéder aux informations privées de la classe mère

Une classe enfant ne peut pas accéder directement aux attributs `private` de sa classe mère. Elle utilise les getters hérités :

```java
System.out.println(getTitre());
```

Il n'est pas nécessaire de rendre les attributs `protected`. Les conserver privés maintient une meilleure encapsulation.

### Comprendre le polymorphisme

Le polymorphisme permet de manipuler un objet enfant à travers le type de sa classe mère :

```java
Media premier = new Documentaire(...);
Media second = new Concert(...);
```

Les deux variables ont le même type déclaré, mais référencent des objets différents.

Si les deux classes redéfinissent `afficherDetails`, Java choisit automatiquement la bonne version pendant l'exécution :

```java
premier.afficherDetails(); // version de Documentaire
second.afficherDetails();  // version de Concert
```

Le choix dépend du type réel de l'objet créé avec `new`, pas uniquement du type de la variable.

### Créer une collection polymorphe

Une collection typée avec la classe mère peut contenir tous ses objets enfants :

```java
private final ArrayList<Media> medias;
```

```java
medias.add(new Documentaire(...));
medias.add(new Concert(...));
```

Lors du parcours, chaque élément est manipulé comme un `Media` :

```java
for (Media media : medias) {
    media.afficherDetails();
}
```

Aucun `if` n'est nécessaire pour choisir l'affichage. Le polymorphisme réalise ce choix.

### Utiliser les méthodes communes

Une méthode concrète définie dans la classe mère est disponible pour tous les objets enfants :

```java
public int getDureeMinutes() {
    return dureeMinutes;
}
```

Le catalogue peut donc additionner les durées sans connaître le type précis de chaque contenu :

```java
int total = 0;

for (Media media : medias) {
    total += media.getDureeMinutes();
}
```

Le code commun reste dans la classe mère, tandis que le comportement variable est redéfini dans les classes enfants.

### Calculer une valeur avant l'appel à `super`

Les arguments transmis à `super` peuvent contenir une expression :

```java
public Saison(String titre, int episodes, int dureeEpisode) {
    super(titre, episodes * dureeEpisode);
    this.episodes = episodes;
    this.dureeEpisode = dureeEpisode;
}
```

La multiplication est évaluée avant l'appel du constructeur parent. La durée totale peut ainsi être conservée dans la partie commune de l'objet.

### Choisir la classe à instancier selon une saisie

Le programme principal peut utiliser une condition :

```java
if (type.equalsIgnoreCase("documentaire")) {
    Media media = new Documentaire(...);
    catalogue.ajouter(media);
} else if (type.equalsIgnoreCase("concert")) {
    Media media = new Concert(...);
    catalogue.ajouter(media);
} else {
    System.out.println("Type inconnu");
}
```

La variable locale peut utiliser le type parent, car les deux objets sont des médias.

### Éviter les tests de type dans le catalogue

Le catalogue ne doit pas écrire :

```java
if (media instanceof Documentaire) {
    // affichage spécifique
}
```

Cette logique contredirait l'objectif du polymorphisme. Chaque objet connaît déjà la version de `afficherDetails` qu'il doit exécuter.

Ajouter plus tard un nouveau type de contenu ne devrait pas obliger à modifier la méthode d'affichage du catalogue.

### Répartition des responsabilités

| Classe | Responsabilité |
|---|---|
| `Contenu` | définir les informations et comportements communs |
| `Film` | représenter et afficher les particularités d'un film |
| `Serie` | représenter et afficher les particularités d'une série |
| `CatalogueStreaming` | conserver et analyser tous les contenus |
| `GestionCatalogue` | récupérer les saisies et créer les bons objets |

## 5. Consignes fonctionnelles

Chaque contenu possède un titre, un genre et une durée totale en minutes.

Un film possède également un réalisateur. Sa durée totale est saisie directement.

Une série possède un nombre d'épisodes et une durée par épisode. Sa durée totale est calculée ainsi :

```text
nombre d'épisodes × durée d'un épisode
```

Le programme doit :

1. demander un nombre de contenus compris entre 1 et 5 ;
2. créer un catalogue vide ;
3. demander le type de chaque contenu ;
4. demander les informations propres au type `film` ou `serie` ;
5. créer l'objet spécialisé correspondant ;
6. ajouter cet objet au catalogue à travers le type `Contenu` ;
7. refuser un type différent de `film` et `serie` sans interrompre le programme ;
8. afficher tous les contenus grâce au polymorphisme ;
9. afficher le nombre d'objets réellement ajoutés ;
10. calculer la durée totale de tous les contenus du catalogue.

Vous pouvez considérer que les durées et nombres d'épisodes saisis sont des entiers strictement positifs.

## 6. Contraintes techniques

Créez exactement cinq fichiers :

```text
exercice-8/
└── src/
    ├── Contenu.java
    ├── Film.java
    ├── Serie.java
    ├── CatalogueStreaming.java
    └── GestionCatalogue.java
```

N'ajoutez aucune instruction `package`.

### Classe abstraite `Contenu`

Déclarez :

```java
private String titre;
private String genre;
private int dureeTotaleMinutes;
```

Créez le constructeur :

```java
public Contenu(String titre, String genre, int dureeTotaleMinutes)
```

Ajoutez les getters `getTitre`, `getGenre` et `getDureeTotaleMinutes`, puis la méthode abstraite :

```java
public abstract void afficherDetails();
```

### Classe `Film`

- Elle étend `Contenu`.
- Elle possède l'attribut privé `realisateur`.
- Son constructeur reçoit le titre, le genre, la durée et le réalisateur.
- Il appelle `super` avant d'initialiser son attribut propre.
- Elle redéfinit `afficherDetails` avec `@Override`.
- Elle affiche toutes les informations présentées dans le résultat attendu.

### Classe `Serie`

- Elle étend `Contenu`.
- Elle possède les attributs privés `nombreEpisodes` et `dureeEpisodeMinutes`.
- Son constructeur reçoit le titre, le genre, le nombre d'épisodes et la durée d'un épisode.
- Il transmet à `super` la durée totale calculée.
- Elle redéfinit `afficherDetails` avec `@Override`.
- Elle affiche toutes les informations présentées dans le résultat attendu.

### Classe `CatalogueStreaming`

Déclarez une collection :

```java
private final ArrayList<Contenu> contenus;
```

Initialisez-la dans un constructeur sans paramètre et créez :

```java
public void ajouterContenu(Contenu contenu)

public int getNombreContenus()

public int calculerDureeTotale()

public void afficherCatalogue()
```

`afficherCatalogue` doit parcourir la collection et appeler uniquement `contenu.afficherDetails()` pour obtenir l'affichage spécialisé. N'utilisez ni `instanceof`, ni conversion de type.

### Classe `GestionCatalogue`

- Elle contient uniquement `main`.
- Elle utilise `Scanner` et une boucle `for`.
- Elle crée un `Film` ou une `Serie` selon le texte saisi.
- Elle compare les textes avec `equalsIgnoreCase`.
- Elle gère les retours à la ligne après chaque `nextInt()`.
- Elle n'ajoute rien pour un type invalide.
- Elle utilise les méthodes du catalogue pour le bilan final.
- Elle ferme le scanner après les saisies.

N'utilisez pas de package, d'interface, d'enum, de `instanceof`, de cast d'objet, de setter ou de classe supplémentaire.

Depuis `exercice-8`, compilez les cinq fichiers :

```bash
javac -d out src/Contenu.java src/Film.java src/Serie.java src/CatalogueStreaming.java src/GestionCatalogue.java
```

Puis exécutez :

```bash
java -cp out GestionCatalogue
```

Sous Windows :

```powershell
javac -d out src\Contenu.java src\Film.java src\Serie.java src\CatalogueStreaming.java src\GestionCatalogue.java
java -cp out GestionCatalogue
```

## 7. Étapes de réalisation

### Étape 1 — Créer la classe mère

Déclarez `Contenu` comme classe abstraite. Ajoutez ses attributs privés, son constructeur et ses getters.

Vérification : il doit être impossible d'écrire `new Contenu(...)`.

### Étape 2 — Imposer l'affichage spécialisé

Déclarez `afficherDetails` comme méthode abstraite sans bloc d'instructions.

Vérification : toute classe enfant concrète doit alors redéfinir cette méthode pour compiler.

### Étape 3 — Créer `Film`

Ajoutez l'héritage, l'attribut propre, le constructeur avec `super` et la redéfinition de la méthode.

Vérification : créez provisoirement un film et contrôlez son affichage.

### Étape 4 — Créer `Serie`

Transmettez au constructeur parent le produit du nombre d'épisodes par leur durée, puis redéfinissez l'affichage.

Vérification : neuf épisodes de 40 minutes représentent 360 minutes.

### Étape 5 — Construire le catalogue

Initialisez une `ArrayList<Contenu>` vide et créez la méthode d'ajout.

Vérification : la même méthode doit accepter un objet `Film` et un objet `Serie`.

### Étape 6 — Calculer les indicateurs

Créez les méthodes retournant le nombre de contenus et la somme de leurs durées.

Vérification : un catalogue vide doit retourner zéro pour les deux indicateurs.

### Étape 7 — Mettre en œuvre le polymorphisme

Dans `afficherCatalogue`, parcourez les contenus et appelez la méthode abstraite redéfinie.

Vérification : le code du catalogue ne doit jamais tester si l'objet est un film ou une série.

### Étape 8 — Construire la saisie

Dans `GestionCatalogue`, demandez le nombre puis les informations de chaque contenu. Créez la bonne classe selon le type.

Vérification : gérez soigneusement les passages entre `nextInt()` et `nextLine()`.

### Étape 9 — Afficher le bilan

Demandez au catalogue d'afficher ses contenus, son nombre d'éléments et sa durée totale.

### Étape 10 — Tester l'ensemble

Compilez tous les fichiers ensemble et exécutez les jeux d'essai.

## 8. Jeux d'essai

### Validité du nombre initial

| Nombre | Comportement attendu |
|---:|---|
| 0 | erreur, aucun contenu demandé |
| 1 | une saisie de contenu |
| 5 | cinq saisies de contenus |
| 6 | erreur, aucun contenu demandé |

### Création des objets

| Type et informations | Objet attendu | Durée totale |
|---|---|---:|
| film, 155 minutes | `Film` | 155 minutes |
| serie, 9 épisodes de 40 minutes | `Serie` | 360 minutes |
| SERIE, 3 épisodes de 50 minutes | `Serie` | 150 minutes |
| podcast | aucun objet | 0 minute ajoutée |

### Catalogue complet

Avec un film de 155 minutes, une série de 9 × 40 minutes et un film de 120 minutes :

- nombre de contenus : 3 ;
- durée totale : 635 minutes ;
- chaque objet utilise son propre format d'affichage.

## 9. Critères de validation

- [ ] Les cinq fichiers et classes portent les noms imposés.
- [ ] `Contenu` est déclarée `abstract`.
- [ ] Ses trois attributs sont privés.
- [ ] Les classes enfants utilisent `extends Contenu`.
- [ ] Chaque constructeur enfant appelle `super` en premier.
- [ ] La durée totale d'une série est calculée dans l'appel au parent.
- [ ] `afficherDetails` est abstraite dans la classe mère.
- [ ] Les deux classes enfants utilisent `@Override`.
- [ ] Les attributs privés du parent sont consultés grâce aux getters.
- [ ] Le catalogue utilise une `ArrayList<Contenu>`.
- [ ] La même collection contient des films et des séries.
- [ ] La durée totale est calculée par les méthodes communes du parent.
- [ ] L'affichage utilise le polymorphisme.
- [ ] Le catalogue ne contient ni `instanceof`, ni cast, ni test du type.
- [ ] Un type invalide n'ajoute aucun objet.
- [ ] Le nombre final correspond aux objets réellement ajoutés.
- [ ] Les retours à la ligne de `Scanner` sont correctement gérés.
- [ ] Tous les fichiers compilent avec la commande fournie.
- [ ] Tous les jeux d'essai produisent les résultats attendus.

## 10. Erreurs fréquentes

### `Contenu is abstract; cannot be instantiated`

La classe abstraite sert de type commun mais ne peut pas être créée directement. Instanciez `Film` ou `Serie`.

### Le constructeur enfant ne compile pas

Vérifiez que `super(...)` est sa première instruction et que ses arguments correspondent au constructeur de `Contenu`.

### Un attribut du parent est inaccessible

Les attributs restent privés. Utilisez les getters hérités au lieu de changer leur visibilité.

### `Film is not abstract and does not override abstract method`

La signature de `afficherDetails` ne correspond probablement pas exactement à celle du parent. Vérifiez le nom, le type de retour et les paramètres.

### L'annotation `@Override` provoque une erreur

La méthode ne redéfinit aucune signature connue. Comparez-la précisément avec la déclaration abstraite.

### La durée d'une série correspond à un seul épisode

La valeur transmise au parent doit représenter la durée de tous les épisodes.

### La collection refuse un objet enfant

Vérifiez que `Film` et `Serie` étendent bien `Contenu` et que la collection est typée `ArrayList<Contenu>`.

### Les saisies textuelles sont ignorées

Après chaque `nextInt()`, consommez le retour à la ligne avant le prochain `nextLine()`.

### Le catalogue contient des tests `instanceof`

Chaque classe enfant doit connaître son propre affichage. Le catalogue appelle simplement la méthode polymorphe.

## 11. Défis supplémentaires

### Défi 1 — Ajouter un documentaire

Créez une troisième classe enfant possédant un sujet et un intervenant principal. Le catalogue ne doit subir aucune modification pour l'afficher.

### Défi 2 — Ajouter une durée lisible

Ajoutez dans `Contenu` une méthode concrète qui transforme la durée totale en heures et minutes. Réutilisez la division entière et `%`.

### Défi 3 — Rechercher le contenu le plus long

Ajoutez au catalogue une méthode qui retourne le `Contenu` possédant la durée maximale, ou `null` si le catalogue est vide.

## 12. Ce qu'il faut retenir

L'héritage représente une relation « est un » et permet de centraliser les caractéristiques communes dans une classe mère. Une classe abstraite définit un modèle incomplet qui doit être spécialisé par ses classes enfants.

Le polymorphisme permet de manipuler des objets différents à travers un même type. Lorsqu'une méthode est redéfinie, Java sélectionne automatiquement l'implémentation correspondant à l'objet réel. Cette approche évite les tests de type et facilite l'ajout de nouvelles spécialisations.

