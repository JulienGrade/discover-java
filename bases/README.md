# Premiers pas avec Java

Ce dossier est un laboratoire de découverte. Il permet de comprendre comment un programme Java est organisé, compilé et exécuté avant de commencer les exercices.

Vous allez manipuler :

- une classe et la méthode `main` ;
- les variables et les principaux types ;
- les affichages dans la console ;
- les conditions ;
- les boucles ;
- les méthodes ;
- une première classe permettant de créer des objets.

Les fichiers du dossier `src` sont des exemples exécutables. Vous êtes encouragé à les modifier, à les compiler de nouveau et à observer le résultat.

## 1. Comprendre l'organisation du dossier

```text
bases/
├── README.md
└── src/
    ├── Main.java
    ├── Variables.java
    ├── Conditions.java
    ├── Boucles.java
    ├── Methodes.java
    └── PremiereClasse.java
```

Le fichier `README.md` contient les explications et les manipulations à réaliser.

Le dossier `src`, abréviation de *source*, contient le code source Java. Ce code est écrit par un développeur et peut être lu par un humain. Il devra être compilé avant d'être exécuté par la machine.

Dans ces premiers exemples, une classe publique est placée dans un fichier portant exactement le même nom :

```java
public class Main {
}
```

La classe `Main` doit donc être enregistrée dans un fichier nommé `Main.java`. Java distingue les majuscules des minuscules : `Main`, `main` et `MAIN` sont trois noms différents.

## 2. Du code source au programme exécuté

Le JDK, ou *Java Development Kit*, contient les outils nécessaires pour développer en Java.

La commande `javac` compile un fichier `.java`. Elle produit un fichier `.class` contenant du bytecode. Ce bytecode est ensuite exécuté par la JVM, la machine virtuelle Java, avec la commande `java`.

Depuis le dossier `bases`, compilez le premier programme :

```bash
javac src/Main.java
```

Un fichier `Main.class` apparaît dans `src`. Lancez-le :

```bash
java -cp src Main
```

L'option `-cp src` indique à Java que les classes compilées se trouvent dans le dossier `src`.

Pour compiler tous les exemples :

```bash
javac src/*.java
```

Sous PowerShell, si cette commande ne fonctionne pas, utilisez :

```powershell
javac (Get-ChildItem src\*.java)
```

## 3. Le point d'entrée d'un programme

Ouvrez `src/Main.java`.

```java
public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenue dans la découverte de Java !");
    }
}
```

Une classe sert ici de conteneur au programme. La ligne suivante déclare la méthode que Java recherche au démarrage :

```java
public static void main(String[] args)
```

Pour le moment, retenez simplement que les instructions placées entre les accolades de `main` sont exécutées dans l'ordre.

`System.out.println` affiche une information dans la console puis revient à la ligne. Une instruction Java se termine généralement par un point-virgule.

### Manipulation

1. Remplacez le message par votre prénom.
2. Ajoutez un second affichage indiquant votre langage de programmation préféré.
3. Compilez et exécutez de nouveau le programme.
4. Retirez volontairement un point-virgule, compilez et observez l'erreur.
5. Corrigez le programme.

## 4. Les commentaires

Les commentaires sont ignorés pendant l'exécution. Ils servent à expliquer une intention lorsque le code ne suffit pas à la rendre évidente.

```java
// Commentaire sur une seule ligne

/*
 * Commentaire sur plusieurs lignes
 */
```

Évitez les commentaires qui répètent simplement l'instruction :

```java
// Affiche Bonjour
System.out.println("Bonjour");
```

## 5. Les variables et les types

Une variable associe un nom, un type et une valeur. Ouvrez `src/Variables.java`.

```java
String pseudo = "Nova";
int niveau = 12;
double tempsDeJeu = 4.5;
boolean connecte = true;
char rang = 'A';
```

La forme générale d'une déclaration est :

```text
type nomDeLaVariable = valeur;
```

| Type | Utilisation | Exemple |
| --- | --- | --- |
| `String` | texte | `"Nova"` |
| `int` | nombre entier | `12` |
| `double` | nombre décimal | `4.5` |
| `boolean` | vrai ou faux | `true` |
| `char` | caractère unique | `'A'` |

Une chaîne de caractères utilise des guillemets doubles. Un caractère unique utilise des apostrophes.

La valeur d'une variable peut changer si son type reste compatible :

```java
int score = 100;
score = 150;
score = score + 20;
```

Une constante est une valeur qui ne doit plus changer :

```java
final int SCORE_MAXIMUM = 1_000;
```

Par convention, le nom d'une constante est écrit en majuscules.

### Manipulation

Dans `Variables.java` :

1. Remplacez les valeurs du profil par les vôtres.
2. Ajoutez une variable contenant le nombre de victoires.
3. Ajoutez 100 points au score.
4. Affichez une phrase complète utilisant toutes les variables.
5. Créez une constante `NIVEAU_MAXIMUM` ayant pour valeur `100`.

## 6. Les opérateurs

Java permet d'effectuer des calculs :

```java
int total = 10 + 5;
int difference = 10 - 5;
int produit = 10 * 5;
int quotient = 10 / 5;
int reste = 10 % 3;
```

Les opérateurs permettent également de comparer des valeurs :

```java
score >= 100
score < 100
score == 100
score != 100
```

`=` affecte une valeur alors que `==` compare deux valeurs.

Plusieurs comparaisons peuvent être combinées :

```java
niveau >= 10 && score >= 500 // ET
niveau >= 10 || score >= 500 // OU
!connecte                     // NON
```

## 7. Les conditions

Une condition permet d'exécuter une partie du programme uniquement lorsqu'une expression est vraie. Ouvrez `src/Conditions.java`.

```java
if (score >= 1_000) {
    System.out.println("Nouveau rang débloqué");
} else {
    System.out.println("Continuez à jouer");
}
```

Plusieurs situations peuvent être distinguées avec `else if` :

```java
if (score >= 1_000) {
    System.out.println("Rang Or");
} else if (score >= 500) {
    System.out.println("Rang Argent");
} else {
    System.out.println("Rang Bronze");
}
```

### Manipulation

1. Modifiez le score et observez le rang affiché.
2. Ajoutez un rang Platine à partir de 2 000 points.
3. Affichez `Accès au tournoi autorisé` si le niveau est au moins égal à 10 et si le joueur est connecté.
4. Testez une valeur située exactement à chaque limite.

## 8. Les boucles

Une boucle répète des instructions. Ouvrez `src/Boucles.java`.

La boucle `for` est adaptée lorsque le nombre de répétitions est connu :

```java
for (int tour = 1; tour <= 3; tour++) {
    System.out.println("Tour " + tour);
}
```

La boucle `while` continue tant que sa condition est vraie :

```java
int energie = 3;

while (energie > 0) {
    System.out.println("Action effectuée");
    energie--;
}
```

`energie--` retire 1 à la variable. Sans cette modification, la condition resterait vraie et la boucle pourrait ne jamais s'arrêter.

### Manipulation

1. Affichez les manches 1 à 5 avec une boucle `for`.
2. Affichez uniquement les nombres pairs entre 2 et 10.
3. Faites démarrer une énergie à 5 et diminuez-la jusqu'à 0 avec `while`.
4. Affichez `Partie terminée` après la boucle.

## 9. Les méthodes

Une méthode regroupe des instructions qui réalisent une tâche précise. Elle évite de répéter du code et donne un nom à une action. Ouvrez `src/Methodes.java`.

```java
public static void afficherBienvenue(String pseudo) {
    System.out.println("Bienvenue " + pseudo);
}
```

`String pseudo` est un paramètre. La méthode reçoit une information lors de son appel :

```java
afficherBienvenue("Nova");
```

Une méthode peut renvoyer un résultat :

```java
public static int calculerScore(int points, int bonus) {
    return points + bonus;
}
```

Le type placé avant le nom de la méthode indique le type du résultat. `void` signifie que la méthode ne renvoie aucun résultat.

### Manipulation

1. Appelez `afficherBienvenue` avec votre pseudo.
2. Appelez plusieurs fois `calculerScore` avec des valeurs différentes.
3. Créez une méthode `estAdmissible` recevant un niveau et renvoyant un `boolean`.
4. Considérez qu'un joueur est admissible à partir du niveau 10.
5. Affichez le résultat renvoyé par la méthode.

## 10. Une première classe et un premier objet

Jusqu'ici, les informations concernant un joueur sont stockées dans plusieurs variables indépendantes. Une classe permet de définir un nouveau type regroupant des données et des comportements cohérents.

Ouvrez `src/PremiereClasse.java`. L'exemple contient une classe `Joueur` :

```java
class Joueur {
    String pseudo;
    int score;

    Joueur(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
    }

    void afficherProfil() {
        System.out.println(pseudo + " possède " + score + " points.");
    }
}
```

Cette classe sert de modèle. Elle indique que chaque joueur possède un pseudo et un score.

Un objet est une instance concrète créée à partir de cette classe :

```java
Joueur joueur = new Joueur("Nova", 750);
joueur.afficherProfil();
```

`new Joueur(...)` crée l'objet. La méthode `Joueur(...)`, appelée constructeur, lui donne ses valeurs initiales.

Cette première découverte reste volontairement simple. Les prochains exercices permettront de comprendre progressivement l'encapsulation, les responsabilités, les relations entre objets, l'héritage et le polymorphisme.

### Manipulation

1. Modifiez le pseudo et le score du premier joueur.
2. Créez un deuxième joueur.
3. Appelez `afficherProfil` sur les deux objets.
4. Ajoutez un attribut `niveau` à la classe.
5. Adaptez le constructeur et l'affichage pour prendre en compte le niveau.

## 11. Lire une erreur de compilation

Une erreur n'est pas un échec : elle indique que le compilateur ne peut pas comprendre ou accepter le programme.

Prenez l'habitude de lire :

1. le nom du fichier concerné ;
2. le numéro de la ligne ;
3. le message d'erreur ;
4. l'extrait de code désigné par le symbole `^`.

Erreurs fréquentes :

| Message ou situation | Cause probable |
| --- | --- |
| `';' expected` | point-virgule manquant |
| `cannot find symbol` | nom mal écrit ou variable inexistante |
| `incompatible types` | valeur incompatible avec le type de la variable |
| classe introuvable au lancement | mauvais nom de classe ou mauvais classpath |
| accolade signalée en erreur | accolade manquante un peu plus haut |

## 12. Validation

Avant de commencer l'exercice 1, vérifiez que vous êtes capable de :

- expliquer le rôle du dossier `src` ;
- compiler un fichier avec `javac` ;
- exécuter une classe avec `java` ;
- identifier la méthode `main` ;
- déclarer et modifier une variable ;
- choisir entre `String`, `int`, `double` et `boolean` ;
- écrire une condition simple ;
- utiliser une boucle ;
- déclarer et appeler une méthode ;
- expliquer simplement la différence entre une classe et un objet ;
- retrouver la ligne concernée par une erreur de compilation.

Vous n'avez pas besoin de tout mémoriser. Vous devez surtout savoir retrouver un exemple, l'adapter et comprendre le rôle de chaque élément utilisé.
