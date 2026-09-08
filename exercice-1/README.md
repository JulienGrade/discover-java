# Exercice 1 — Créer une fiche de profil de joueur

## 1. Contexte

Un studio prépare un jeu d'aventure en ligne. Avant de commencer une partie, chaque joueur doit créer son profil.

Vous devez développer un programme en console qui demande quelques informations au joueur, calcule une statistique simple, puis affiche une fiche récapitulative claire. Ce premier programme permettra de manipuler des données saisies par un utilisateur au lieu d'utiliser uniquement des valeurs écrites directement dans le code.

## 2. Objectifs

Après cet exercice, vous saurez :

- déclarer et initialiser des variables de plusieurs types ;
- récupérer du texte et des nombres saisis au clavier ;
- effectuer un calcul simple ;
- construire un affichage à partir de plusieurs variables ;
- compiler et exécuter un programme Java depuis un terminal.

## 3. Résultat attendu

Voici un exemple d'exécution. Les valeurs saisies par l'utilisateur sont indiquées après chaque question.

```text
=== Création de votre profil ===

Quel est votre pseudo ? Nova
Quel âge avez-vous ? 19
Quel est votre jeu préféré ? Minecraft
Combien d'heures jouez-vous par semaine ? 8

=== Profil du joueur ===
Pseudo : Nova
Âge : 19 ans
Jeu préféré : Minecraft
Temps de jeu hebdomadaire : 8 heures
Temps de jeu annuel estimé : 416 heures

Profil créé avec succès.
```

Le temps de jeu annuel est une estimation obtenue en multipliant le nombre d'heures jouées chaque semaine par `52`.

## 4. Notions nécessaires

### Déclarer une classe et une méthode `main`

En Java, le code du programme est placé dans une classe. Le nom de la classe doit correspondre exactement au nom du fichier.

```java
public class Exemple {
    public static void main(String[] args) {
        System.out.println("Le programme démarre.");
    }
}
```

Dans cet exemple :

- la classe `Exemple` doit être enregistrée dans un fichier nommé `Exemple.java` ;
- la méthode `main` est le point de départ du programme ;
- les instructions à exécuter sont placées entre les accolades de `main` ;
- chaque instruction se termine généralement par un point-virgule.

Erreur fréquente : écrire un nom de classe différent du nom du fichier. Java ne pourra alors pas compiler la classe publique.

### Déclarer des variables

Une variable permet de mémoriser une valeur pour la réutiliser plus tard. Sa déclaration indique son type, son nom et éventuellement sa valeur initiale.

Syntaxe générale :

```java
Type nomVariable = valeur;
```

Exemples indépendants de l'exercice :

```java
String artiste = "Daft Punk";
int nombreTitres = 12;
double dureeAlbum = 74.5;
```

- `String` contient du texte. Sa valeur est placée entre guillemets doubles ;
- `int` contient un nombre entier ;
- `double` contient un nombre pouvant avoir une partie décimale.

Choisissez des noms explicites et utilisez la convention `camelCase` : le premier mot commence par une minuscule et les mots suivants par une majuscule, par exemple `nombreDeParties` ou `tempsDeJeu`.

Erreurs fréquentes :

- oublier les guillemets autour d'un texte ;
- utiliser une virgule à la place du point dans un nombre décimal ;
- essayer de stocker du texte dans une variable de type `int`.

### Afficher du texte dans la console

La méthode `System.out.println` affiche un contenu puis passe à la ligne suivante.

```java
System.out.println("Bienvenue dans l'application");
```

La méthode `System.out.print` affiche un contenu sans passer automatiquement à la ligne.

```java
System.out.print("Saisissez votre ville : ");
```

Vous pouvez assembler du texte et une variable avec l'opérateur `+`.

```java
String ville = "Lille";
System.out.println("Ville choisie : " + ville);
```

Dans ce cas, `+` réalise une concaténation : il assemble plusieurs éléments pour former un seul texte.

Erreur fréquente : placer le nom de la variable entre guillemets. L'instruction suivante affiche le mot `ville`, et non la valeur de la variable :

```java
System.out.println("ville");
```

### Lire une saisie avec `Scanner`

La classe `Scanner` permet de lire les informations saisies au clavier.

Vous devez d'abord l'importer avant la déclaration de votre classe :

```java
import java.util.Scanner;
```

Créez ensuite un scanner au début de la méthode `main` :

```java
Scanner scanner = new Scanner(System.in);
```

`System.in` représente l'entrée standard du programme, c'est-à-dire ici le clavier.

Les principales méthodes de lecture sont :

```java
String titre = scanner.nextLine();
int annee = scanner.nextInt();
double note = scanner.nextDouble();
```

- `nextLine()` lit toute une ligne de texte, espaces compris ;
- `nextInt()` lit un nombre entier ;
- `nextDouble()` lit un nombre décimal.

Avant chaque saisie, affichez une question afin que l'utilisateur sache ce qu'il doit renseigner :

```java
System.out.print("Quel album écoutez-vous ? ");
String album = scanner.nextLine();
```

#### Le retour à la ligne après `nextInt()` ou `nextDouble()`

Lorsque l'utilisateur valide un nombre avec la touche Entrée, `nextInt()` lit le nombre mais laisse le retour à la ligne dans le scanner. Si vous appelez immédiatement `nextLine()`, cette méthode peut lire ce retour à la ligne et donner l'impression que la saisie suivante a été ignorée.

```java
int quantite = scanner.nextInt();
scanner.nextLine(); // consomme le retour à la ligne restant
String produit = scanner.nextLine();
```

Dans l'exercice, une saisie textuelle est demandée après un entier. Vous devrez donc appliquer cette correction au bon endroit.

#### Fermer le scanner

Quand toutes les saisies sont terminées, fermez le scanner :

```java
scanner.close();
```

Ne le fermez pas avant la dernière saisie, sinon le programme ne pourra plus lire les réponses suivantes.

### Effectuer un calcul

Java utilise les opérateurs arithmétiques habituels :

| Opérateur | Opération | Exemple |
|---|---|---|
| `+` | addition | `score + bonus` |
| `-` | soustraction | `vies - 1` |
| `*` | multiplication | `prix * quantite` |
| `/` | division | `total / nombre` |

Le résultat d'un calcul peut être enregistré dans une nouvelle variable :

```java
int nombreEpisodes = 10;
int dureeEpisode = 45;
int dureeTotale = nombreEpisodes * dureeEpisode;
```

Pour cet exercice, une année sera estimée à `52` semaines. Cette valeur ne change pas pendant l'exécution : vous pouvez donc la déclarer comme constante avec `final`.

Exemple indépendant :

```java
final int MOIS_DANS_UNE_ANNEE = 12;
```

Par convention, le nom d'une constante est écrit en majuscules et les mots sont séparés par des tirets bas.

Erreur fréquente : effectuer un calcul avant que les variables nécessaires aient reçu leur valeur.

## 5. Consignes fonctionnelles

Votre programme doit :

1. afficher le titre `=== Création de votre profil ===` ;
2. demander le pseudo du joueur ;
3. demander son âge en années entières ;
4. demander son jeu préféré ;
5. demander son nombre entier d'heures de jeu par semaine ;
6. calculer son temps de jeu annuel estimé sur la base de 52 semaines ;
7. afficher une fiche contenant toutes les informations saisies et le résultat du calcul ;
8. terminer par le message `Profil créé avec succès.`.

Pour ce premier exercice, vous pouvez considérer que l'utilisateur saisit toujours des valeurs valides. Il n'est pas encore nécessaire de vérifier l'âge ou le nombre d'heures.

## 6. Contraintes techniques

- Créez un fichier nommé `ProfilJoueur.java` dans le dossier `exercice-1/src`.
- La classe publique doit se nommer `ProfilJoueur`.
- Placez le programme dans la méthode `main`.
- Utilisez un objet `Scanner` pour récupérer les quatre réponses.
- Utilisez des variables de type `String` pour le pseudo et le jeu préféré.
- Utilisez des variables de type `int` pour l'âge et les temps de jeu.
- Déclarez le nombre de semaines dans une année sous la forme d'une constante `final int`.
- Calculez le temps annuel à partir du temps hebdomadaire : ne saisissez pas directement le résultat attendu dans le code.
- Fermez le scanner après la dernière saisie.
- N'utilisez pas de condition, de boucle, de tableau, de collection, de méthode personnalisée ou de classe supplémentaire.
- N'ajoutez aucune instruction `package`.

Depuis le dossier `exercice-1`, compilez le programme avec :

```bash
javac -d out src/ProfilJoueur.java
```

Exécutez-le ensuite avec :

```bash
java -cp out ProfilJoueur
```

Le dossier `out` contiendra le fichier compilé. Il ne doit pas être confondu avec le dossier `src`, qui contient votre code source.

## 7. Étapes de réalisation

### Étape 1 — Préparer le fichier

Créez le dossier `src`, puis le fichier `ProfilJoueur.java`. Déclarez la classe et la méthode `main`.

Vérification : faites afficher un message provisoire, compilez le programme et exécutez-le.

### Étape 2 — Préparer la saisie

Importez `Scanner`, puis créez un scanner dans la méthode `main`.

Vérification : le programme doit toujours compiler.

### Étape 3 — Demander le pseudo et l'âge

Affichez les deux premières questions, lisez les réponses et conservez-les dans des variables adaptées.

Vérification : affichez temporairement les deux valeurs pour confirmer qu'elles ont été correctement mémorisées.

### Étape 4 — Demander le jeu préféré

Ajoutez la saisie du jeu préféré. Soyez attentif au retour à la ligne encore présent après la lecture de l'âge.

Vérification : essayez un titre composé de plusieurs mots, comme `The Legend of Zelda`.

### Étape 5 — Demander le temps de jeu et effectuer le calcul

Ajoutez la dernière question. Déclarez la constante représentant le nombre de semaines, puis calculez le temps annuel estimé.

Vérification : pour 10 heures par semaine, le résultat doit être de 520 heures par an.

### Étape 6 — Construire la fiche finale

Remplacez les affichages provisoires par la fiche demandée. Utilisez les libellés montrés dans le résultat attendu.

Vérification : chaque information doit apparaître sur une ligne distincte.

### Étape 7 — Finaliser le programme

Fermez le scanner après la dernière saisie. Compilez et exécutez une dernière fois le programme avec plusieurs jeux d'essai.

## 8. Jeux d'essai

| Pseudo | Âge | Jeu préféré | Heures par semaine | Temps annuel attendu |
|---|---:|---|---:|---:|
| Nova | 19 | Minecraft | 8 | 416 heures |
| Kira59 | 22 | The Legend of Zelda | 10 | 520 heures |
| Pixel | 18 | Rocket League | 0 | 0 heure |
| Atlas | 35 | EA Sports FC | 168 | 8 736 heures |

Le dernier cas sert uniquement à vérifier le calcul avec une valeur élevée. Le programme n'a pas encore à déterminer si cette valeur est réaliste.

Vérifiez également qu'un nom de jeu contenant plusieurs mots est lu et affiché entièrement.

## 9. Critères de validation

- [ ] Le fichier se nomme `ProfilJoueur.java` et la classe se nomme `ProfilJoueur`.
- [ ] Le programme compile avec la commande fournie.
- [ ] Le programme s'exécute avec la commande fournie.
- [ ] Les quatre questions sont affichées dans le bon ordre.
- [ ] Le pseudo et le jeu préféré peuvent contenir des espaces.
- [ ] L'âge et le nombre d'heures sont stockés sous forme d'entiers.
- [ ] Le nombre de semaines est déclaré comme une constante.
- [ ] Le temps annuel est réellement calculé.
- [ ] La fiche contient toutes les informations demandées.
- [ ] Les quatre jeux d'essai produisent les résultats attendus.
- [ ] Le scanner est fermé après la dernière saisie.
- [ ] Aucun `package`, aucune condition et aucune boucle n'ont été ajoutés.
- [ ] Les noms des variables permettent de comprendre leur rôle.
- [ ] Le code est correctement indenté et ne contient plus d'affichage provisoire.

## 10. Erreurs fréquentes

### `cannot find symbol: class Scanner`

Vérifiez que l'import de `Scanner` est présent tout en haut du fichier et qu'il est écrit exactement ainsi :

```java
import java.util.Scanner;
```

### La saisie du jeu préféré est ignorée

Une lecture avec `nextInt()` a probablement laissé un retour à la ligne dans le scanner. Relisez la partie consacrée à ce problème et consommez ce retour à la ligne avant de lire le jeu avec `nextLine()`.

### Le titre d'un jeu est coupé au premier espace

Pour lire une ligne complète de texte, utilisez `nextLine()` et non `next()`.

### `InputMismatchException` apparaît

Le programme attend probablement un entier avec `nextInt()`, mais une autre valeur a été saisie. Relancez le programme et saisissez uniquement des chiffres pour l'âge et le temps de jeu.

### La classe ne peut pas être exécutée

Vérifiez que :

- la compilation n'a produit aucune erreur ;
- vous exécutez la commande depuis le dossier `exercice-1` ;
- le nom `ProfilJoueur` respecte exactement les majuscules et les minuscules ;
- le fichier `ProfilJoueur.class` se trouve dans le dossier `out`.

### Le programme affiche un texte à la place d'une valeur

Vérifiez que le nom de la variable n'a pas été placé entre guillemets dans l'instruction d'affichage.

## 11. Défis supplémentaires

Ces défis sont facultatifs et n'utilisent que les notions présentées dans cet exercice.

### Défi 1 — Ajouter une plateforme

Demandez au joueur sa plateforme principale, par exemple `PC`, `Xbox`, `PlayStation` ou `Nintendo Switch`, puis ajoutez-la à la fiche.

### Défi 2 — Estimer le temps mensuel

Calculez une estimation mensuelle en considérant qu'un mois contient quatre semaines. Affichez cette nouvelle information dans la fiche.

### Défi 3 — Ajouter un slogan

Demandez au joueur une courte phrase de présentation et affichez-la entre guillemets sous son pseudo.

## 12. Ce qu'il faut retenir

Un programme interactif peut récupérer des valeurs avec `Scanner`, les conserver dans des variables, les utiliser dans des calculs et construire un affichage personnalisé. Le choix du type de chaque variable est important : `String` convient au texte et `int` aux nombres entiers. Après `nextInt()`, le retour à la ligne restant doit être traité avant un nouvel appel à `nextLine()`.


