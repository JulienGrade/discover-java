# Exercice 4 — Analyser la durée d'une playlist

## 1. Contexte

Une application musicale souhaite aider ses utilisateurs à préparer des playlists adaptées à différents moments de la journée. Pour chaque playlist, l'utilisateur indique son nom, le nombre de morceaux et la durée de chaque morceau en minutes et en secondes.

Le programme doit additionner toutes ces durées, convertir le résultat dans un format lisible, puis déterminer si la playlist est courte, standard ou longue.

Pour éviter de placer tous les calculs dans la méthode `main`, vous allez découper le programme en plusieurs méthodes. Chaque méthode réalisera une tâche précise et pourra être testée séparément.

## 2. Objectifs

Après cet exercice, vous saurez :

- déclarer et appeler une méthode `static` ;
- transmettre des informations grâce aux paramètres ;
- retourner un résultat avec `return` ;
- distinguer une méthode qui retourne une valeur d'une méthode `void` ;
- choisir un type de retour adapté ;
- comprendre la portée d'une variable locale ;
- décomposer un programme en responsabilités simples ;
- utiliser la division entière et l'opérateur modulo `%`.

## 3. Résultat attendu

### Playlist standard

```text
=== Analyseur de playlist ===

Quel est le nom de la playlist ? Session gaming
Combien contient-elle de morceaux ? 3

Morceau 1
Minutes : 12
Secondes : 30

Morceau 2
Minutes : 18
Secondes : 45

Morceau 3
Minutes : 15
Secondes : 15

=== Bilan de la playlist ===
Nom : Session gaming
Nombre de morceaux : 3
Durée totale : 46 min 0 s
Catégorie : Playlist standard
```

### Playlist dépassant une heure

```text
=== Analyseur de playlist ===

Quel est le nom de la playlist ? Long trajet
Combien contient-elle de morceaux ? 2

Morceau 1
Minutes : 35
Secondes : 20

Morceau 2
Minutes : 42
Secondes : 50

=== Bilan de la playlist ===
Nom : Long trajet
Nombre de morceaux : 2
Durée totale : 1 h 18 min 10 s
Catégorie : Playlist longue
```

### Nombre de morceaux invalide

```text
=== Analyseur de playlist ===

Quel est le nom de la playlist ? Test
Combien contient-elle de morceaux ? 0

Erreur : une playlist doit contenir entre 1 et 20 morceaux.
```

Dans ce dernier cas, le programme se termine sans demander la durée d'un morceau.

## 4. Notions nécessaires

### Pourquoi créer des méthodes ?

Une méthode regroupe des instructions qui réalisent une action précise. Elle permet de donner un nom à cette action, d'éviter les répétitions et de rendre le programme plus facile à comprendre.

Au lieu de placer tous les traitements dans `main`, vous pouvez séparer les responsabilités :

- une méthode effectue une conversion ;
- une méthode choisit une catégorie ;
- une méthode prépare un texte lisible ;
- une méthode affiche un résultat.

La méthode `main` conserve alors le rôle de chef d'orchestre : elle récupère les saisies et appelle les autres méthodes dans le bon ordre.

### Déclarer une méthode

Une méthode est déclarée dans la classe, mais en dehors de la méthode `main`.

Syntaxe générale :

```java
public static TypeRetour nomMethode(TypeParametre nomParametre) {
    instructions
    return valeur;
}
```

Exemple indépendant de l'exercice :

```java
public static int calculerPrixTotal(int prixUnitaire, int quantite) {
    int total = prixUnitaire * quantite;
    return total;
}
```

Les éléments importants sont :

| Élément | Rôle |
|---|---|
| `public` | rend la méthode accessible depuis les autres classes |
| `static` | permet de l'appeler directement depuis la méthode `main` |
| `int` | indique le type du résultat retourné |
| `calculerPrixTotal` | donne un nom à la méthode |
| `int prixUnitaire, int quantite` | décrit les informations reçues |
| `return total` | renvoie le résultat à l'endroit de l'appel |

Dans cet exercice, toutes les méthodes demandées seront `public static`. La notion de méthode liée à un objet sera étudiée plus tard avec la programmation orientée objet.

### Respecter l'emplacement des méthodes

Une méthode ne peut pas être déclarée à l'intérieur d'une autre méthode.

Structure correcte :

```java
public class Exemple {
    public static void main(String[] args) {
        // Instructions de main
    }

    public static int doubler(int nombre) {
        return nombre * 2;
    }
}
```

La méthode `doubler` se trouve après `main`, mais toujours entre les accolades de la classe `Exemple`.

Erreur fréquente : écrire une nouvelle méthode avant l'accolade fermante de `main`. Java signale alors plusieurs erreurs de compilation.

### Définir des paramètres

Les paramètres sont les informations dont une méthode a besoin pour travailler.

```java
public static int calculerSurface(int largeur, int hauteur) {
    return largeur * hauteur;
}
```

La méthode reçoit ici deux paramètres : `largeur` et `hauteur`. Ils se comportent comme des variables locales disponibles uniquement dans la méthode.

Une méthode peut recevoir plusieurs paramètres séparés par des virgules, ou n'en recevoir aucun :

```java
public static void afficherSeparateur() {
    System.out.println("--------------------");
}
```

### Appeler une méthode

Pour exécuter une méthode, utilisez son nom et fournissez les valeurs attendues entre parenthèses.

```java
int surface = calculerSurface(5, 3);
```

Lors de cet appel :

- `5` est transmis au paramètre `largeur` ;
- `3` est transmis au paramètre `hauteur` ;
- la méthode calcule `15` ;
- le résultat retourné est stocké dans `surface`.

Vous pouvez transmettre des variables à la place de valeurs écrites directement :

```java
int largeurTerrain = 8;
int longueurTerrain = 12;
int surfaceTerrain = calculerSurface(largeurTerrain, longueurTerrain);
```

L'ordre et le type des arguments doivent correspondre aux paramètres de la méthode.

### Retourner une valeur avec `return`

Le mot-clé `return` renvoie une valeur et termine immédiatement l'exécution de la méthode.

```java
public static String determinerEtat(int batterie) {
    if (batterie < 20) {
        return "Faible";
    } else {
        return "Suffisante";
    }
}
```

Le type de la valeur retournée doit correspondre au type annoncé dans la déclaration. Ici, la méthode annonce `String` et retourne donc du texte.

Erreur fréquente : annoncer un retour `int` puis retourner une chaîne de caractères.

```java
public static int obtenirNiveau() {
    return "Débutant"; // incorrect
}
```

Chaque chemin possible dans une méthode qui retourne une valeur doit aboutir à un `return`.

### Créer une méthode `void`

Une méthode qui réalise une action sans produire de résultat utilise le type `void`.

```java
public static void afficherBienvenue(String pseudo) {
    System.out.println("Bienvenue " + pseudo);
}
```

Elle s'appelle simplement ainsi :

```java
afficherBienvenue("Nova");
```

Il n'est pas possible de stocker le résultat d'une méthode `void`, puisqu'elle ne retourne aucune valeur.

Dans cet exercice, l'affichage du bilan sera confié à une méthode `void`.

### Comprendre la portée des variables

Une variable locale existe uniquement dans le bloc où elle est déclarée.

```java
public static int calculerBonus(int score) {
    int bonus = score / 10;
    return bonus;
}
```

Les variables `score` et `bonus` ne sont pas accessibles depuis `main` ou depuis une autre méthode. Seule la valeur renvoyée avec `return` peut être récupérée par le code appelant.

Deux méthodes peuvent utiliser des variables locales portant le même nom : elles restent indépendantes.

Erreur fréquente : essayer d'utiliser dans `main` une variable déclarée uniquement à l'intérieur d'une autre méthode.

### Convertir une durée en secondes

Pour additionner facilement des durées, ramenez-les à une unité commune. Une minute contient 60 secondes.

```text
durée en secondes = minutes × 60 + secondes
```

Exemple :

```text
3 min 25 s = 3 × 60 + 25 = 205 secondes
```

Une méthode pourra recevoir le nombre de minutes et de secondes, puis retourner la durée totale en secondes.

### Utiliser la division entière

Lorsque deux nombres de type `int` sont divisés, Java conserve uniquement la partie entière du résultat.

```java
int groupesComplets = 17 / 5;
```

La variable vaut `3`, car il est possible de former trois groupes complets de cinq éléments.

Cette propriété est utile pour déterminer combien d'heures ou de minutes complètes sont contenues dans une durée.

### Obtenir le reste avec l'opérateur modulo

L'opérateur `%` retourne le reste d'une division entière.

```java
int reste = 17 % 5;
```

La variable `reste` vaut `2`, car :

```text
17 = 3 × 5 + 2
```

Division entière et modulo sont souvent utilisés ensemble :

```java
int totalMinutes = 135;
int heures = totalMinutes / 60;  // 2
int minutesRestantes = totalMinutes % 60; // 15
```

Pour une durée exprimée en secondes, réfléchissez à l'ordre des conversions : heures complètes, minutes restantes, puis secondes restantes.

### Construire et retourner un texte

Une méthode peut construire une chaîne de caractères et la retourner :

```java
public static String formaterDistance(int metres) {
    int kilometres = metres / 1_000;
    int metresRestants = metres % 1_000;
    return kilometres + " km " + metresRestants + " m";
}
```

Le code appelant récupère le texte prêt à être affiché :

```java
String distanceLisible = formaterDistance(2_350);
System.out.println(distanceLisible);
```

Les underscores dans `1_000` et `2_350` améliorent uniquement la lisibilité. Ils ne changent pas la valeur du nombre.

### Faire appeler une méthode par une autre

Une méthode peut appeler une autre méthode de la même classe.

```java
public static void afficherCommande(int prix, int quantite) {
    int total = calculerPrixTotal(prix, quantite);
    System.out.println("Total : " + total + " euros");
}
```

Cette organisation évite de recopier un calcul déjà disponible ailleurs.

Dans l'exercice, la méthode chargée d'afficher le bilan pourra appeler les méthodes qui formatent la durée et déterminent la catégorie.

## 5. Consignes fonctionnelles

Une playlist contient entre 1 et 20 morceaux inclus.

Pour chaque morceau :

- le nombre de minutes est un entier positif ou nul ;
- le nombre de secondes est compris entre 0 et 59 inclus.

Pour maintenir l'exercice centré sur les méthodes, vous pouvez considérer que les durées saisies respectent ces règles. Seul le nombre de morceaux doit être contrôlé.

La catégorie dépend de la durée totale :

| Durée totale | Catégorie attendue |
|---|---|
| moins de 30 minutes | Playlist courte |
| de 30 minutes incluses à moins de 60 minutes | Playlist standard |
| 60 minutes ou plus | Playlist longue |

Votre programme doit :

1. demander le nom de la playlist ;
2. demander le nombre de morceaux ;
3. afficher une erreur si ce nombre n'est pas compris entre 1 et 20 ;
4. demander les minutes et les secondes de chaque morceau dans une boucle `for` ;
5. convertir la durée de chaque morceau en secondes en appelant une méthode ;
6. additionner les durées converties ;
7. convertir la durée totale en un texte lisible grâce à une méthode ;
8. déterminer la catégorie grâce à une méthode ;
9. afficher le bilan complet grâce à une méthode dédiée.

Le format de la durée dépend du total :

| Durée | Format attendu | Exemple |
|---|---|---|
| moins d'une heure | `X min Y s` | `46 min 0 s` |
| une heure ou plus | `X h Y min Z s` | `1 h 18 min 10 s` |

## 6. Contraintes techniques

- Créez `AnalysePlaylist.java` dans `exercice-4/src`.
- La classe publique doit se nommer `AnalysePlaylist`.
- N'ajoutez aucune instruction `package`.
- Conservez les saisies et la boucle principale dans la méthode `main`.
- Utilisez des constantes `final int` pour les limites et les conversions utiles.
- Utilisez obligatoirement les quatre méthodes suivantes avec exactement ces signatures :

```java
public static int convertirEnSecondes(int minutes, int secondes)

public static String formaterDuree(int totalSecondes)

public static String determinerCategorie(int totalSecondes)

public static void afficherBilan(
        String nomPlaylist,
        int nombreMorceaux,
        int totalSecondes
)
```

- `convertirEnSecondes` doit effectuer et retourner la conversion d'une durée.
- `formaterDuree` doit utiliser la division entière et l'opérateur `%`.
- `determinerCategorie` doit retourner exactement `Playlist courte`, `Playlist standard` ou `Playlist longue`.
- `afficherBilan` doit afficher le nom, le nombre de morceaux, la durée formatée et la catégorie.
- `afficherBilan` doit appeler `formaterDuree` et `determinerCategorie` au lieu de reproduire leurs traitements.
- Appelez `convertirEnSecondes` dans la boucle pour chaque morceau.
- N'utilisez pas de variable globale ou d'attribut de classe pour transmettre les résultats.
- N'utilisez pas de tableau, de collection, de surcharge de méthode ou de classe supplémentaire.
- Fermez le scanner après la dernière saisie.

Depuis `exercice-4`, compilez avec :

```bash
javac -d out src/AnalysePlaylist.java
```

Puis exécutez avec :

```bash
java -cp out AnalysePlaylist
```

Sous Windows :

```powershell
javac -d out src\AnalysePlaylist.java
java -cp out AnalysePlaylist
```

## 7. Étapes de réalisation

### Étape 1 — Préparer le programme principal

Créez la classe, la méthode `main` et le scanner. Demandez le nom de la playlist et le nombre de morceaux.

Vérification : les deux réponses doivent être correctement mémorisées.

### Étape 2 — Valider le nombre de morceaux

Déclarez les limites sous forme de constantes et refusez toute valeur située en dehors de l'intervalle de 1 à 20.

Vérification : avec `0` ou `21`, aucune durée ne doit être demandée.

### Étape 3 — Créer `convertirEnSecondes`

Déclarez la méthode en dehors de `main`. Utilisez ses deux paramètres pour calculer et retourner une durée en secondes.

Vérification : vérifiez mentalement que `3 min 25 s` produit `205` secondes. Vous pouvez appeler temporairement la méthode depuis `main` pour afficher son résultat.

### Étape 4 — Saisir et additionner les morceaux

Dans le cas valide, créez une boucle `for`. Demandez les minutes et les secondes de chaque morceau, appelez `convertirEnSecondes`, puis ajoutez le résultat au total.

Vérification : deux morceaux de `2 min 30 s` doivent produire un total de `300` secondes.

### Étape 5 — Créer `formaterDuree`

À partir du paramètre `totalSecondes`, calculez les heures complètes, les minutes restantes et les secondes restantes. Retournez le texte attendu selon que la durée atteint ou non une heure.

Vérification :

| Secondes reçues | Texte retourné |
|---:|---|
| 45 | `0 min 45 s` |
| 180 | `3 min 0 s` |
| 3 599 | `59 min 59 s` |
| 3 600 | `1 h 0 min 0 s` |
| 4 690 | `1 h 18 min 10 s` |

### Étape 6 — Créer `determinerCategorie`

Utilisez des conditions et les seuils du tableau pour retourner la bonne chaîne de caractères.

Vérification : testez les durées situées juste avant et exactement sur 30 et 60 minutes.

### Étape 7 — Créer `afficherBilan`

Utilisez les trois paramètres reçus pour afficher le bilan. Appelez les deux méthodes précédentes afin d'obtenir la durée formatée et la catégorie.

Vérification : cette méthode ne doit contenir ni calcul de conversion, ni règle de classement recopiée.

### Étape 8 — Relier les méthodes

Une fois la boucle terminée, appelez `afficherBilan` depuis `main` avec le nom, le nombre de morceaux et le total en secondes.

Vérification : `main` organise les saisies et les appels, tandis que chaque méthode réalise une seule tâche identifiable.

### Étape 9 — Finaliser et tester

Fermez le scanner, supprimez les affichages temporaires, compilez puis exécutez tous les jeux d'essai.

## 8. Jeux d'essai

### Conversion d'une durée

| Minutes | Secondes | Résultat attendu |
|---:|---:|---:|
| 0 | 45 | 45 secondes |
| 3 | 25 | 205 secondes |
| 10 | 0 | 600 secondes |
| 60 | 0 | 3 600 secondes |

### Formatage et catégorie de la playlist

| Durées des morceaux | Durée totale formatée | Catégorie |
|---|---|---|
| `4:30`, `5:15` | `9 min 45 s` | Playlist courte |
| `15:00`, `15:00` | `30 min 0 s` | Playlist standard |
| `29:59`, `29:59` | `59 min 58 s` | Playlist standard |
| `30:00`, `30:00` | `1 h 0 min 0 s` | Playlist longue |
| `35:20`, `42:50` | `1 h 18 min 10 s` | Playlist longue |

Dans ce tableau, `4:30` signifie 4 minutes et 30 secondes. Les deux valeurs doivent être saisies séparément dans le programme.

### Valeurs limites du nombre de morceaux

| Nombre | Comportement attendu |
|---:|---|
| 0 | message d'erreur, aucune durée demandée |
| 1 | une durée demandée |
| 20 | vingt durées demandées |
| 21 | message d'erreur, aucune durée demandée |

## 9. Critères de validation

- [ ] Le fichier et la classe se nomment `AnalysePlaylist`.
- [ ] Le programme compile et s'exécute avec les commandes indiquées.
- [ ] Le nombre de morceaux est limité à l'intervalle de 1 à 20.
- [ ] La boucle demande exactement la durée de chaque morceau.
- [ ] Les quatre méthodes imposées existent avec les signatures demandées.
- [ ] Chaque méthode est déclarée dans la classe, mais en dehors de `main`.
- [ ] Chaque durée est convertie en secondes par `convertirEnSecondes`.
- [ ] Le total est construit à partir des résultats retournés par la méthode.
- [ ] `formaterDuree` utilise `/` et `%`.
- [ ] Le format n'affiche les heures que lorsque la playlist atteint une heure.
- [ ] Les seuils de 30 et 60 minutes sont correctement traités.
- [ ] `determinerCategorie` retourne exactement l'un des trois textes attendus.
- [ ] `afficherBilan` est une méthode `void`.
- [ ] `afficherBilan` appelle les méthodes de formatage et de classement.
- [ ] `main` ne contient pas directement les traitements confiés aux autres méthodes.
- [ ] Aucune variable globale n'est utilisée pour partager les résultats.
- [ ] Tous les jeux d'essai produisent les résultats attendus.
- [ ] Le scanner est fermé après les saisies.
- [ ] Les noms de méthodes et de variables décrivent clairement leur rôle.
- [ ] Le code est correctement indenté.

## 10. Erreurs fréquentes

### `illegal start of expression`

Une méthode a peut-être été déclarée à l'intérieur de `main`. Vérifiez les accolades : chaque méthode doit être directement placée dans la classe.

### `non-static method cannot be referenced from a static context`

Une méthode appelée depuis `main` n'a probablement pas été déclarée avec `static`. Vérifiez sa signature.

### `missing return statement`

Une méthode annonce un type de retour, mais l'un de ses chemins ne retourne aucune valeur. Vérifiez toutes les branches de vos conditions.

### `incompatible types`

Le type de la valeur retournée ne correspond peut-être pas au type annoncé dans la signature. Une méthode `String` doit retourner du texte et une méthode `int` un entier.

### Le total contient uniquement le dernier morceau

La durée convertie doit être ajoutée à l'accumulateur. Une affectation simple remplace le total précédent.

### Les minutes dépassent 59 dans le bilan

Après avoir calculé les heures complètes, conservez uniquement les minutes restantes. L'opérateur `%` permet d'obtenir un reste de division.

### Les secondes sont toujours égales au total

Les secondes affichées doivent correspondre au reste après constitution des minutes complètes.

### Une playlist de 30 minutes est classée comme courte

Vérifiez si l'opérateur utilisé inclut ou exclut la valeur 30. Le tableau des catégories précise que 30 minutes appartiennent à la catégorie standard.

### La méthode affiche un résultat au lieu de le retourner

Une méthode de calcul doit transmettre son résultat avec `return`. Afficher une valeur dans la méthode ne permet pas à `main` de la récupérer et de la réutiliser.

### Une variable est introuvable dans une autre méthode

Une variable locale n'existe que dans sa méthode. Transmettez l'information nécessaire avec un paramètre ou récupérez-la grâce à une valeur de retour.

## 11. Défis supplémentaires

### Défi 1 — Estimer le nombre de morceaux moyen

Créez une méthode qui reçoit la durée totale et le nombre de morceaux, puis retourne la durée moyenne d'un morceau sous la forme d'un `double` exprimé en secondes.

### Défi 2 — Améliorer le texte au singulier

Adaptez `formaterDuree` pour afficher `1 heure`, `1 minute` ou `1 seconde` lorsque la valeur correspondante vaut exactement 1.

### Défi 3 — Conseiller un usage

Créez une méthode qui retourne un conseil selon la catégorie : pause rapide, séance de travail ou long trajet. Affichez ce conseil dans le bilan.

## 12. Ce qu'il faut retenir

Une méthode isole une responsabilité et peut recevoir des paramètres pour travailler avec les données du programme. Une méthode de calcul retourne une valeur avec `return`, tandis qu'une méthode `void` réalise une action sans fournir de résultat. Les variables locales restent limitées à leur méthode : les paramètres et les valeurs de retour permettent donc de faire circuler les informations proprement.

La division entière permet d'extraire un nombre d'unités complètes, tandis que l'opérateur `%` fournit le reste. Ensemble, ils permettent notamment de convertir une durée totale en heures, minutes et secondes.

