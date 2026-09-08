# Exercice 7 — Constituer une équipe e-sport

## 1. Contexte

Une structure e-sport prépare une équipe pour un prochain tournoi. Elle doit enregistrer plusieurs joueurs, afficher son effectif, calculer son niveau moyen et identifier son meilleur joueur.

Dans l'exercice précédent, un seul objet représentait un jeu vidéo. Cette fois, une classe `EquipeEsport` devra gérer plusieurs objets `Joueur`. Les joueurs seront conservés dans une `ArrayList`, une collection dont la taille peut évoluer lorsque de nouveaux membres rejoignent l'équipe.

L'application sera répartie entre trois classes : une classe représentant un joueur, une classe représentant l'équipe et une classe contenant le programme principal.

## 2. Objectifs

Après cet exercice, vous saurez :

- créer plusieurs objets d'une même classe ;
- utiliser des getters pour lire des attributs privés ;
- créer et manipuler une `ArrayList` typée ;
- ajouter des objets dans une collection ;
- parcourir une collection avec une boucle `for-each` ;
- mettre en place une relation de composition entre deux classes ;
- faire retourner un objet par une méthode ;
- utiliser `null` pour représenter l'absence de résultat ;
- répartir les responsabilités entre plusieurs classes métier.

## 3. Résultat attendu

### Équipe de trois joueurs

```text
=== Création d'une équipe e-sport ===

Nom de l'équipe : Northern Stars
Combien de joueurs souhaitez-vous ajouter ? 3

Joueur 1
Pseudo : Nova
Rôle : Attaquant
Niveau : 72

Joueur 2
Pseudo : Kira
Rôle : Support
Niveau : 85

Joueur 3
Pseudo : Pixel
Rôle : Stratège
Niveau : 78

=== Équipe Northern Stars ===
1. Nova - Attaquant - niveau 72
2. Kira - Support - niveau 85
3. Pixel - Stratège - niveau 78

Nombre de joueurs : 3
Niveau moyen : 78.33333333333333
Meilleur joueur : Kira, niveau 85
```

Le nombre exact de décimales de la moyenne peut varier selon la manière dont vous choisissez de l'afficher. Le calcul doit néanmoins conserver sa partie décimale.

### Équipe contenant un seul joueur

```text
=== Création d'une équipe e-sport ===

Nom de l'équipe : Solo Squad
Combien de joueurs souhaitez-vous ajouter ? 1

Joueur 1
Pseudo : Atlas
Rôle : Polyvalent
Niveau : 60

=== Équipe Solo Squad ===
1. Atlas - Polyvalent - niveau 60

Nombre de joueurs : 1
Niveau moyen : 60.0
Meilleur joueur : Atlas, niveau 60
```

### Taille d'équipe invalide

```text
=== Création d'une équipe e-sport ===

Nom de l'équipe : Test
Combien de joueurs souhaitez-vous ajouter ? 6

Erreur : une équipe doit contenir entre 1 et 5 joueurs.
```

Aucun joueur ne doit être demandé si la taille annoncée est invalide.

## 4. Notions nécessaires

### Organiser plusieurs classes métier

Une application orientée objet contient souvent plusieurs classes qui collaborent.

```java
public class Auteur {
    private String nom;

    public Auteur(String nom) {
        this.nom = nom;
    }
}
```

```java
public class Bibliotheque {
    // La bibliothèque pourra conserver plusieurs auteurs.
}
```

Chaque classe doit avoir une responsabilité identifiable. Une classe représentant une équipe ne doit pas stocker séparément le pseudo, le rôle et le niveau de chaque membre. Elle doit conserver des objets qui regroupent déjà ces informations.

### Lire un attribut privé avec un getter

Un attribut `private` n'est pas accessible directement depuis une autre classe. Un getter est une méthode publique qui retourne sa valeur.

```java
public class Livre {
    private String titre;

    public Livre(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }
}
```

Le getter est appelé sur un objet :

```java
Livre livre = new Livre("Dune");
System.out.println(livre.getTitre());
```

Par convention, le nom d'un getter commence par `get`, suivi du nom de l'attribut avec une majuscule.

Un getter ne doit pas modifier l'objet. Il fournit uniquement une information.

### Pourquoi ne pas rendre les attributs publics ?

Rendre un attribut `public` supprimerait la protection apportée par l'encapsulation :

```java
joueur.niveau = -500;
```

Avec un attribut privé et un getter, le code extérieur peut consulter la valeur sans pouvoir la remplacer directement. Si une modification devient nécessaire, elle pourra être confiée à une méthode appliquant les règles métier.

Cet exercice ne demande aucun setter. Les informations d'un joueur sont définies à sa création et seulement consultées ensuite.

### Comprendre les limites d'un tableau

Un tableau possède une taille fixe :

```java
Joueur[] joueurs = new Joueur[3];
```

Une fois les trois cases utilisées, il faut créer un autre tableau pour augmenter sa capacité. Dans une application où des membres peuvent être ajoutés progressivement, une collection dynamique est plus adaptée.

### Découvrir `ArrayList`

`ArrayList` est une classe de la bibliothèque standard Java. Elle représente une liste dont la taille évolue.

Elle doit être importée en haut du fichier qui l'utilise :

```java
import java.util.ArrayList;
```

Déclaration et création d'une liste de textes :

```java
ArrayList<String> titres = new ArrayList<>();
```

Les éléments importants sont :

| Élément | Signification |
|---|---|
| `ArrayList` | type de collection utilisé |
| `<String>` | type des éléments autorisés dans la liste |
| `titres` | nom de la variable |
| `new ArrayList<>()` | création de la liste vide |

La partie placée entre `<` et `>` s'appelle un type générique. Elle garantit ici que la liste accepte uniquement des objets `String`.

Dans l'exercice, la collection acceptera uniquement des objets `Joueur`.

### Ajouter un élément avec `add`

La méthode `add` ajoute un élément à la fin de la liste :

```java
titres.add("Minecraft");
titres.add("Hades");
```

La liste contient alors deux éléments, dans l'ordre de leur ajout.

Avec des objets :

```java
Livre livre = new Livre("Dune");
livres.add(livre);
```

La liste conserve une référence vers l'objet. Elle ne crée pas automatiquement une copie.

### Connaître le nombre d'éléments avec `size()`

Une `ArrayList` utilise la méthode `size()` :

```java
int nombreTitres = titres.size();
```

Attention à la différence avec un tableau :

| Structure | Taille |
|---|---|
| tableau | `tableau.length` |
| `ArrayList` | `liste.size()` |

`size()` contient des parenthèses, car il s'agit d'une méthode.

### Récupérer un élément avec `get`

La méthode `get` retourne l'élément situé à un indice :

```java
String premierTitre = titres.get(0);
```

Comme pour un tableau, les indices commencent à `0`. Le dernier indice valide est `size() - 1`.

```java
titres.get(titres.size()); // indice invalide
```

Une position inexistante provoque une `IndexOutOfBoundsException`.

### Parcourir une collection avec une boucle `for-each`

La boucle `for-each`, aussi appelée boucle améliorée, parcourt directement tous les éléments d'une collection.

```java
for (String titre : titres) {
    System.out.println(titre);
}
```

Cette syntaxe se lit : « pour chaque `titre` de type `String` présent dans `titres` ».

Avec une liste d'objets :

```java
for (Livre livre : livres) {
    System.out.println(livre.getTitre());
}
```

La boucle `for-each` est adaptée lorsque vous devez examiner tous les éléments sans avoir besoin de modifier leur position.

Si un numéro doit être affiché, utilisez un compteur séparé :

```java
int numero = 1;

for (String titre : titres) {
    System.out.println(numero + ". " + titre);
    numero++;
}
```

### Créer une composition

Une composition existe lorsqu'un objet contient et gère d'autres objets.

```java
public class Bibliotheque {
    private ArrayList<Livre> livres;

    public Bibliotheque() {
        this.livres = new ArrayList<>();
    }
}
```

La bibliothèque est composée de livres. Elle est responsable de la collection qui les contient.

Dans l'exercice, `EquipeEsport` sera composée de plusieurs objets `Joueur`.

### Initialiser la collection dans le constructeur

La collection doit être créée avant le premier appel à `add`.

```java
public Bibliotheque() {
    this.livres = new ArrayList<>();
}
```

Si l'attribut reste à `null`, l'appel suivant provoque une `NullPointerException` :

```java
livres.add(livre);
```

Le constructeur garantit que chaque nouvel objet possède immédiatement une collection vide utilisable.

### Utiliser `final` avec une collection

La référence vers la collection peut être déclarée `final` :

```java
private final ArrayList<Livre> livres;
```

Il devient impossible de remplacer la liste par une autre :

```java
livres = new ArrayList<>(); // interdit après l'initialisation
```

Le contenu peut cependant continuer à évoluer :

```java
livres.add(livre); // autorisé
```

`final` protège donc ici la référence, mais ne rend pas la collection immuable.

### Ajouter un objet grâce à une méthode métier

La classe propriétaire de la collection doit contrôler les ajouts :

```java
public void ajouterLivre(Livre livre) {
    livres.add(livre);
}
```

Le programme principal ne doit pas récupérer directement la liste pour la modifier. Il demande à l'objet responsable d'effectuer l'opération.

Cette organisation respecte l'encapsulation et permet d'ajouter plus tard des règles comme une capacité maximale ou la détection des doublons.

### Calculer une moyenne à partir d'objets

Une boucle peut appeler un getter sur chaque objet :

```java
int totalPages = 0;

for (Livre livre : livres) {
    totalPages += livre.getNombrePages();
}

double moyenne = (double) totalPages / livres.size();
```

Avant une division, vérifiez que la collection n'est pas vide. Dans cet exercice, la saisie garantit au moins un joueur, mais la méthode doit malgré tout être capable de traiter une équipe vide proprement.

### Faire retourner un objet par une méthode

Le type de retour d'une méthode peut être une classe créée dans le projet :

```java
public Livre trouverPremierLivre() {
    if (livres.size() == 0) {
        return null;
    }

    return livres.get(0);
}
```

La méthode retourne une référence vers un objet `Livre` ou `null` si aucun résultat n'existe.

Le code appelant doit vérifier le résultat avant d'appeler une méthode :

```java
Livre resultat = bibliotheque.trouverPremierLivre();

if (resultat != null) {
    System.out.println(resultat.getTitre());
}
```

Appeler une méthode sur `null` provoquerait une `NullPointerException`.

### Rechercher le meilleur objet

Une recherche peut mémoriser l'objet qui représente le meilleur résultat actuel :

```java
Livre plusLong = null;

for (Livre livre : livres) {
    if (plusLong == null || livre.getNombrePages() > plusLong.getNombrePages()) {
        plusLong = livre;
    }
}
```

Au premier passage, `plusLong` vaut `null` : le premier objet devient donc la référence initiale. Les passages suivants remplacent cette référence uniquement lorsqu'une meilleure valeur est rencontrée.

Avec une comparaison strictement supérieure, la première occurrence est conservée en cas d'égalité.

### Respecter les responsabilités

Les trois classes n'ont pas le même rôle :

| Classe | Responsabilité |
|---|---|
| `Joueur` | représenter un joueur et fournir ses informations |
| `EquipeEsport` | gérer la collection et calculer les indicateurs de l'équipe |
| `GestionEquipe` | dialoguer avec l'utilisateur et coordonner les objets |

`GestionEquipe` ne doit pas recalculer elle-même le niveau moyen ni rechercher le meilleur joueur. Ces traitements appartiennent à l'équipe, car ils concernent son effectif.

## 5. Consignes fonctionnelles

Une équipe possède un nom et contient au maximum cinq joueurs.

Chaque joueur possède :

- un pseudo ;
- un rôle ;
- un niveau entier compris entre 1 et 100.

Pour maintenir l'exercice centré sur les objets et les collections, vous pouvez considérer que les niveaux saisis respectent cet intervalle.

Le programme doit :

1. demander le nom de l'équipe ;
2. demander un nombre de joueurs compris entre 1 et 5 ;
3. refuser une valeur située hors de cet intervalle ;
4. créer un objet `EquipeEsport` ;
5. répéter la saisie du pseudo, du rôle et du niveau ;
6. créer un objet `Joueur` pour chaque série de réponses ;
7. demander à l'équipe d'ajouter chaque joueur ;
8. afficher l'effectif dans l'ordre des ajouts ;
9. afficher le nombre de joueurs ;
10. calculer et afficher le niveau moyen ;
11. rechercher et afficher le premier joueur possédant le meilleur niveau.

## 6. Contraintes techniques

Créez exactement trois fichiers :

```text
exercice-7/
└── src/
    ├── Joueur.java
    ├── EquipeEsport.java
    └── GestionEquipe.java
```

N'ajoutez aucune instruction `package`.

### Classe `Joueur`

Déclarez les attributs suivants en `private` :

```java
private String pseudo;
private String role;
private int niveau;
```

Créez le constructeur :

```java
public Joueur(String pseudo, String role, int niveau)
```

Créez uniquement les getters suivants :

```java
public String getPseudo()

public String getRole()

public int getNiveau()
```

N'ajoutez aucun setter.

### Classe `EquipeEsport`

Déclarez les attributs suivants :

```java
private String nom;
private final ArrayList<Joueur> joueurs;
```

Importez `java.util.ArrayList` et initialisez la collection vide dans le constructeur :

```java
public EquipeEsport(String nom)
```

Créez les méthodes suivantes :

```java
public String getNom()

public void ajouterJoueur(Joueur joueur)

public int getNombreJoueurs()

public double calculerNiveauMoyen()

public Joueur trouverMeilleurJoueur()

public void afficherEffectif()
```

Règles imposées :

- `ajouterJoueur` ajoute l'objet reçu dans la collection ;
- `getNombreJoueurs` retourne la taille actuelle de la collection ;
- `calculerNiveauMoyen` retourne `0.0` si la collection est vide ;
- sinon, la moyenne utilise une division décimale ;
- `trouverMeilleurJoueur` retourne `null` si l'équipe est vide ;
- sinon, elle retourne le premier joueur ayant le niveau maximal ;
- `afficherEffectif` utilise une boucle `for-each` et un compteur pour numéroter les joueurs ;
- la collection ne doit jamais être retournée par un getter.

### Classe `GestionEquipe`

- Elle contient uniquement la méthode `main`.
- Elle crée le scanner et récupère les saisies.
- Elle contrôle que le nombre de joueurs est compris entre 1 et 5.
- Elle crée l'équipe puis les joueurs avec `new`.
- Elle utilise une boucle `for` pour répéter les saisies.
- Elle gère les retours à la ligne laissés après `nextInt()`.
- Elle appelle les méthodes de `EquipeEsport` pour obtenir les résultats.
- Elle vérifie que le meilleur joueur n'est pas `null` avant d'utiliser ses getters.
- Elle ne possède aucune `ArrayList` et n'accède à aucun attribut privé.
- Elle ferme le scanner après la dernière saisie.

N'utilisez pas de tableau, de `HashMap`, d'héritage, de méthode `static` en dehors de `main` ou de classe supplémentaire.

Depuis `exercice-7`, compilez les trois fichiers :

```bash
javac -d out src/Joueur.java src/EquipeEsport.java src/GestionEquipe.java
```

Exécutez la classe contenant `main` :

```bash
java -cp out GestionEquipe
```

Sous Windows :

```powershell
javac -d out src\Joueur.java src\EquipeEsport.java src\GestionEquipe.java
java -cp out GestionEquipe
```

## 7. Étapes de réalisation

### Étape 1 — Créer `Joueur`

Déclarez ses trois attributs privés, son constructeur et ses getters.

Vérification : chaque getter doit uniquement retourner l'attribut correspondant.

### Étape 2 — Créer l'équipe et sa collection

Déclarez `EquipeEsport`, importez `ArrayList`, puis initialisez le nom et la collection dans le constructeur.

Vérification : un nouvel objet équipe doit posséder une liste vide, jamais `null`.

### Étape 3 — Ajouter et compter les joueurs

Créez `ajouterJoueur` et `getNombreJoueurs`. Ajoutez provisoirement deux joueurs depuis `main` pour vérifier que la taille évolue.

Vérification : une équipe vide retourne 0, puis 2 après deux ajouts.

### Étape 4 — Saisir l'équipe

Dans `GestionEquipe`, demandez le nom et le nombre de joueurs. Refusez les valeurs inférieures à 1 ou supérieures à 5.

Vérification : aucun joueur ne doit être demandé dans le cas invalide.

### Étape 5 — Créer les joueurs dans une boucle

Pour chaque membre, demandez les trois informations, créez un objet puis ajoutez-le à l'équipe.

Vérification : attention au retour à la ligne après chaque niveau lu avec `nextInt()` avant la saisie textuelle du joueur suivant.

### Étape 6 — Afficher l'effectif

Créez `afficherEffectif` avec une boucle `for-each` et un compteur commençant à 1.

Vérification : les joueurs doivent apparaître dans l'ordre de leur ajout.

### Étape 7 — Calculer le niveau moyen

Parcourez les joueurs, additionnez leurs niveaux grâce au getter, puis divisez par la taille de la collection.

Vérification : traitez le cas vide avant la division.

### Étape 8 — Rechercher le meilleur joueur

Parcourez la collection en conservant une référence vers le meilleur objet rencontré. Retournez `null` si aucun joueur n'existe.

Vérification : en cas d'égalité, la première occurrence doit être conservée.

### Étape 9 — Construire le bilan

Depuis `main`, demandez à l'équipe d'afficher son effectif et ses indicateurs. Utilisez les getters du meilleur joueur après avoir vérifié qu'il n'est pas `null`.

### Étape 10 — Tester l'application

Compilez les trois fichiers ensemble et exécutez tous les jeux d'essai.

## 8. Jeux d'essai

### Nombre de joueurs

| Valeur | Comportement attendu |
|---:|---|
| 0 | erreur, aucune saisie de joueur |
| 1 | création d'un joueur |
| 5 | création de cinq joueurs |
| 6 | erreur, aucune saisie de joueur |

### Indicateurs

| Joueurs et niveaux | Moyenne | Meilleur joueur |
|---|---:|---|
| `Atlas: 60` | 60.0 | Atlas |
| `Nova: 72`, `Kira: 85`, `Pixel: 78` | environ 78.33 | Kira |
| `Nova: 90`, `Kira: 90`, `Pixel: 70` | environ 83.33 | Nova |
| `A: 1`, `B: 100` | 50.5 | B |

Le troisième cas vérifie que la première occurrence du niveau maximal est conservée.

Même si l'application principale refuse une équipe vide, vérifiez séparément que `calculerNiveauMoyen` retourne `0.0` et que `trouverMeilleurJoueur` retourne `null` lorsque la collection est vide.

## 9. Critères de validation

- [ ] Les trois fichiers et les trois classes portent les noms imposés.
- [ ] Seule `GestionEquipe` contient `main`.
- [ ] Tous les attributs sont privés.
- [ ] La collection est une `ArrayList<Joueur>`.
- [ ] La collection est initialisée dans le constructeur de l'équipe.
- [ ] Sa référence est déclarée `final`.
- [ ] Les joueurs sont créés avec leur constructeur.
- [ ] Seuls des getters permettent de consulter leurs attributs.
- [ ] Aucun setter n'a été ajouté.
- [ ] Les objets sont ajoutés avec la méthode de l'équipe.
- [ ] La taille est obtenue avec `size()`.
- [ ] `afficherEffectif` utilise une boucle `for-each`.
- [ ] Le niveau moyen conserve sa partie décimale.
- [ ] Une équipe vide ne provoque aucune division par zéro.
- [ ] La méthode de recherche retourne un objet `Joueur` ou `null`.
- [ ] La première occurrence du maximum est conservée.
- [ ] Le résultat est vérifié avant tout appel de getter.
- [ ] La classe principale ne manipule pas directement la collection.
- [ ] Le retour à la ligne après chaque niveau est correctement géré.
- [ ] Les trois fichiers compilent avec la commande fournie.
- [ ] Tous les jeux d'essai produisent les résultats attendus.

## 10. Erreurs fréquentes

### `ArrayList cannot be resolved to a type`

Vérifiez que `import java.util.ArrayList;` est présent en haut de `EquipeEsport.java`.

### La collection provoque une `NullPointerException`

L'attribut a probablement été déclaré sans être initialisé. Créez la liste avec `new ArrayList<>()` dans le constructeur.

### `length` ne fonctionne pas

Une `ArrayList` utilise `size()`, contrairement à un tableau qui utilise `length`.

### `getNiveau` ou `getPseudo` est introuvable

Vérifiez le nom, le type de retour, les parenthèses et la visibilité `public` du getter dans `Joueur`.

### Les saisies textuelles sont ignorées

Après `nextInt()`, consommez le retour à la ligne restant avant le prochain `nextLine()`.

### La moyenne est arrondie à l'entier inférieur

Le total et la taille sont des entiers. Convertissez l'une de ces valeurs en `double` avant la division.

### La recherche provoque une erreur sur une équipe vide

Ne récupérez pas directement l'élément d'indice 0 sans vérifier la présence d'un joueur. Une recherche initialisée à `null` peut fonctionner même avec une collection vide.

### Le meilleur joueur est toujours le dernier

Ne remplacez la référence que lorsque le niveau courant est strictement supérieur au meilleur niveau déjà rencontré.

### Une `NullPointerException` apparaît dans `main`

La méthode de recherche peut retourner `null`. Vérifiez le résultat avec `!= null` avant d'appeler un getter.

### `final` empêche l'ajout d'un joueur

`final` interdit de remplacer la référence vers la liste. Il n'interdit pas d'appeler `add` pour modifier son contenu.

## 11. Défis supplémentaires

### Défi 1 — Empêcher de dépasser cinq joueurs

Faites appliquer la capacité maximale directement par `ajouterJoueur`. Faites retourner un `boolean` indiquant si l'ajout a réussi.

### Défi 2 — Rechercher un joueur par son pseudo

Ajoutez une méthode qui reçoit un pseudo et retourne le joueur correspondant, ou `null`. Utilisez `equalsIgnoreCase` pour la comparaison.

### Défi 3 — Faire progresser un joueur

Ajoutez dans `Joueur` une méthode qui augmente son niveau sans jamais dépasser 100. L'équipe doit pouvoir retrouver le joueur concerné puis lui demander de progresser.

## 12. Ce qu'il faut retenir

Une collection `ArrayList` conserve un nombre évolutif d'objets d'un type précis. `add` ajoute un élément, `size()` indique la taille et une boucle `for-each` permet de parcourir tous les éléments.

La composition permet à un objet de gérer d'autres objets : l'équipe possède son effectif et porte les traitements qui concernent l'ensemble des joueurs. Les getters donnent un accès contrôlé aux attributs privés, tandis qu'une méthode peut retourner directement l'objet correspondant à une recherche.
