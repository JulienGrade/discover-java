# Exercice 2 — Déterminer la ligue d'un joueur

## 1. Contexte

Une plateforme organise un tournoi de jeu vidéo. Chaque joueur possède un score compris entre 0 et 1 000 points. Selon ce score, il appartient à une ligue : Bronze, Argent, Or, Platine ou Champion.

Vous devez créer un programme en console qui demande le pseudo et le score d'un joueur, vérifie que le score est valide, puis affiche sa ligue. Si le joueur n'est pas encore Champion, le programme indique également le nombre de points qu'il lui manque pour atteindre la ligue suivante.

## 2. Objectifs

Après cet exercice, vous saurez :

- comparer une valeur numérique à plusieurs seuils ;
- écrire une structure conditionnelle avec `if`, `else if` et `else` ;
- utiliser les opérateurs de comparaison et les opérateurs logiques ;
- traiter séparément une saisie valide et une saisie invalide ;
- calculer une différence en fonction d'une situation ;
- tester précisément les valeurs situées aux limites de plusieurs intervalles.

## 3. Résultat attendu

### Exemple avec un score valide

```text
=== Classement du tournoi ===

Quel est votre pseudo ? Nova
Quel est votre score ? 560

=== Résultat ===
Joueur : Nova
Score : 560 points
Ligue : Or
Il vous manque 140 points pour atteindre la ligue Platine.
```

### Exemple avec un joueur Champion

```text
=== Classement du tournoi ===

Quel est votre pseudo ? Kira
Quel est votre score ? 950

=== Résultat ===
Joueur : Kira
Score : 950 points
Ligue : Champion
Vous avez atteint la ligue la plus élevée.
```

### Exemple avec un score invalide

```text
=== Classement du tournoi ===

Quel est votre pseudo ? Pixel
Quel est votre score ? 1200

Erreur : le score doit être compris entre 0 et 1000.
```

Le programme n'a pas à redemander le score lorsqu'il est invalide. Il affiche le message d'erreur puis se termine.

## 4. Notions nécessaires

### Récupérer les informations saisies

Comme dans l'exercice précédent, `Scanner` permet de lire les saisies effectuées au clavier.

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
```

Utilisez `nextLine()` pour lire un texte et `nextInt()` pour lire un nombre entier :

```java
System.out.print("Quel est le nom de l'équipe ? ");
String equipe = scanner.nextLine();

System.out.print("Combien de matchs a-t-elle gagnés ? ");
int victoires = scanner.nextInt();
```

Dans cet exercice, le texte est saisi avant le nombre. Il n'est donc pas nécessaire de consommer un retour à la ligne entre ces deux lectures. Fermez le scanner après la dernière saisie :

```java
scanner.close();
```

Pour ce deuxième exercice, vous pouvez considérer que le score saisi est bien un nombre entier. Le programme doit contrôler sa valeur, mais pas gérer la saisie de lettres à la place d'un nombre.

### Comparer des valeurs

Une condition est une expression dont le résultat est soit `true`, soit `false`. Ces deux valeurs appartiennent au type `boolean`.

```java
int placesRestantes = 4;
boolean inscriptionPossible = placesRestantes > 0;
```

Ici, `inscriptionPossible` vaut `true`, car `4` est supérieur à `0`.

Les principaux opérateurs de comparaison sont :

| Opérateur | Signification | Exemple |
|---|---|---|
| `==` | égal à | `niveau == 10` |
| `!=` | différent de | `vies != 0` |
| `<` | strictement inférieur à | `age < 18` |
| `<=` | inférieur ou égal à | `note <= 20` |
| `>` | strictement supérieur à | `score > 500` |
| `>=` | supérieur ou égal à | `stock >= 1` |

Attention à ne pas confondre :

- `=` affecte une valeur à une variable ;
- `==` compare deux valeurs.

```java
int niveau = 5;        // affectation
boolean test = niveau == 5; // comparaison
```

### Exécuter une instruction avec `if`

La structure `if` exécute un bloc seulement si sa condition est vraie.

```java
int batterie = 15;

if (batterie < 20) {
    System.out.println("Batterie faible");
}
```

La condition est placée entre parenthèses. Les instructions concernées sont placées entre accolades.

Erreur fréquente : ajouter un point-virgule juste après la condition.

```java
if (batterie < 20); { // incorrect
    System.out.println("Ce bloc sera toujours exécuté");
}
```

### Choisir entre deux possibilités avec `if` et `else`

`else` permet d'exécuter un autre bloc lorsque la condition du `if` est fausse.

```java
int stock = 3;

if (stock > 0) {
    System.out.println("Produit disponible");
} else {
    System.out.println("Produit indisponible");
}
```

Un seul des deux blocs est exécuté.

### Tester plusieurs possibilités avec `else if`

Lorsque plusieurs situations sont possibles, utilisez une chaîne de conditions :

```java
int temperature = 18;

if (temperature < 10) {
    System.out.println("Il fait froid");
} else if (temperature < 20) {
    System.out.println("Il fait frais");
} else {
    System.out.println("Il fait chaud");
}
```

Java teste les conditions de haut en bas et s'arrête dès qu'une condition est vraie. Avec `18`, la première condition est fausse et la deuxième est vraie : le programme affiche `Il fait frais`.

L'ordre des conditions est donc important. Si vous testez des seuils croissants, commencez généralement par le seuil le plus bas.

### Comprendre les intervalles créés par une chaîne de conditions

Considérez cet exemple :

```java
if (distance < 5) {
    System.out.println("Trajet court");
} else if (distance < 20) {
    System.out.println("Trajet moyen");
} else {
    System.out.println("Trajet long");
}
```

Le deuxième bloc signifie en réalité que la distance est supérieure ou égale à 5 **et** inférieure à 20. Il n'est pas nécessaire de répéter la première comparaison, car Java n'arrive au `else if` que si `distance < 5` est faux.

| Valeur de `distance` | Résultat |
|---:|---|
| de 0 à 4 | Trajet court |
| de 5 à 19 | Trajet moyen |
| 20 ou plus | Trajet long |

### Combiner des conditions

Les opérateurs logiques permettent de combiner plusieurs comparaisons.

| Opérateur | Signification | Résultat vrai lorsque... |
|---|---|---|
| `&&` | ET | les deux conditions sont vraies |
| `||` | OU | au moins une condition est vraie |
| `!` | NON | la condition qui suit est fausse |

Exemple avec `&&` :

```java
int age = 16;

if (age >= 13 && age <= 17) {
    System.out.println("Catégorie junior");
}
```

L'âge doit respecter simultanément les deux limites.

Exemple avec `||` :

```java
int quantite = -2;

if (quantite < 0 || quantite > 100) {
    System.out.println("Quantité invalide");
}
```

La quantité est invalide si elle est inférieure à 0 **ou** supérieure à 100. Cette logique sera utile pour contrôler le score du tournoi.

Erreur fréquente : écrire `&` ou `|` au lieu de `&&` ou `||`. Pour combiner des conditions booléennes dans cet exercice, utilisez les opérateurs doubles.

### Calculer les points manquants

Pour connaître la distance entre une valeur actuelle et un objectif, soustrayez la valeur actuelle à la valeur cible :

```java
int abonnésActuels = 750;
int objectif = 1_000;
int abonnesManquants = objectif - abonnésActuels;
```

Si l'objectif vaut `1_000` et que la valeur actuelle vaut `750`, il manque `250`.

Le caractère `_` placé dans `1_000` améliore seulement la lisibilité du nombre. Java interprète cette valeur exactement comme `1000`.

### Utiliser des constantes

Les limites fixées par le règlement du tournoi ne changent pas pendant l'exécution. Déclarez-les donc avec `final` :

```java
final int LIMITE_EXEMPLE = 500;
```

Une variable déclarée avec `final` peut recevoir une valeur une seule fois. Une nouvelle affectation provoquerait une erreur de compilation.

Par convention, le nom d'une constante est écrit en majuscules, avec des tirets bas entre les mots.

## 5. Consignes fonctionnelles

Le classement utilise les règles suivantes :

| Score | Ligue | Prochain objectif |
|---:|---|---:|
| de 0 à 199 | Bronze | 200 points |
| de 200 à 399 | Argent | 400 points |
| de 400 à 699 | Or | 700 points |
| de 700 à 899 | Platine | 900 points |
| de 900 à 1 000 | Champion | aucune ligue supérieure |

Votre programme doit :

1. afficher le titre `=== Classement du tournoi ===` ;
2. demander le pseudo du joueur ;
3. demander son score ;
4. vérifier que le score est compris entre 0 et 1 000 inclus ;
5. afficher un message d'erreur si le score est invalide ;
6. déterminer la ligue correspondant au score lorsqu'il est valide ;
7. afficher le pseudo, le score et la ligue ;
8. calculer et afficher les points manquants jusqu'à la ligue suivante ;
9. afficher `Vous avez atteint la ligue la plus élevée.` lorsque le joueur est Champion.

Pour chaque joueur, un seul classement doit être affiché.

## 6. Contraintes techniques

- Créez un fichier `ClassementTournoi.java` dans `exercice-2/src`.
- La classe publique doit se nommer `ClassementTournoi`.
- N'ajoutez aucune instruction `package`.
- Utilisez un objet `Scanner` pour lire le pseudo et le score.
- Stockez le pseudo dans une variable de type `String` et le score dans une variable de type `int`.
- Déclarez les limites du score et les seuils des ligues sous forme de constantes `final int`.
- Utilisez au moins une structure `if / else if / else`.
- Utilisez l'opérateur logique `||` pour détecter un score inférieur au minimum ou supérieur au maximum.
- Calculez les points manquants avec une soustraction.
- Ne recopiez pas directement dans l'affichage les résultats propres aux jeux d'essai.
- Fermez le scanner après la dernière saisie.
- N'utilisez pas de boucle, de tableau, de collection, de `switch`, de méthode personnalisée ou de classe supplémentaire.

Depuis le dossier `exercice-2`, compilez avec :

```bash
javac -d out src/ClassementTournoi.java
```

Puis exécutez avec :

```bash
java -cp out ClassementTournoi
```

Sous Windows, vous pouvez remplacer `/` par `\` dans le chemin du fichier source :

```powershell
javac -d out src\ClassementTournoi.java
java -cp out ClassementTournoi
```

## 7. Étapes de réalisation

### Étape 1 — Préparer le programme

Créez le dossier `src` et le fichier demandé. Ajoutez l'import, la classe, la méthode `main` et le scanner.

Vérification : affichez temporairement un message, puis compilez et exécutez le programme.

### Étape 2 — Récupérer le profil

Demandez le pseudo puis le score. Stockez chaque réponse dans une variable du type adapté.

Vérification : affichez temporairement les valeurs saisies.

### Étape 3 — Déclarer les seuils

Créez les constantes représentant le score minimal, le score maximal et le premier score de chaque nouvelle ligue.

Vérification : assurez-vous que les valeurs correspondent exactement au tableau de classement.

### Étape 4 — Contrôler la validité du score

Écrivez une condition qui détecte une valeur située en dehors de l'intervalle autorisé. Utilisez `||` pour réunir les deux cas invalides.

Vérification : testez `-1`, `0`, `1 000` et `1 001`.

### Étape 5 — Déterminer la ligue

Dans le cas où le score est valide, utilisez une chaîne `if / else if / else` pour déterminer sa ligue. Organisez les comparaisons dans un ordre cohérent afin qu'un seul bloc soit exécuté.

Vérification : testez une valeur située au milieu de chaque intervalle.

### Étape 6 — Calculer la progression

Pour chaque ligue sauf Champion, déterminez le prochain seuil et calculez la différence entre ce seuil et le score actuel.

Vérification : avec un score de `560`, le prochain seuil est `700` et il manque `140` points.

### Étape 7 — Construire l'affichage final

Affichez le résultat avec les libellés présentés dans les exemples. Remplacez tous les affichages temporaires.

Vérification : un score invalide ne doit afficher ni ligue ni points manquants.

### Étape 8 — Tester les limites

Exécutez tous les jeux d'essai. Vérifiez particulièrement la valeur située juste avant et celle située exactement sur chaque seuil.

## 8. Jeux d'essai

| Pseudo | Score | Comportement attendu |
|---|---:|---|
| Nova | -1 | erreur : score invalide |
| Nova | 0 | Bronze, 200 points manquants |
| Nova | 199 | Bronze, 1 point manquant |
| Nova | 200 | Argent, 200 points manquants |
| Nova | 399 | Argent, 1 point manquant |
| Nova | 400 | Or, 300 points manquants |
| Nova | 699 | Or, 1 point manquant |
| Nova | 700 | Platine, 200 points manquants |
| Nova | 899 | Platine, 1 point manquant |
| Nova | 900 | Champion, ligue la plus élevée |
| Nova | 1 000 | Champion, ligue la plus élevée |
| Nova | 1 001 | erreur : score invalide |

Les seuils sont les cas les plus importants. Une erreur sur `<` ou `<=` peut classer un joueur dans la mauvaise ligue.

## 9. Critères de validation

- [ ] Le fichier et la classe se nomment `ClassementTournoi`.
- [ ] Le programme compile et s'exécute avec les commandes indiquées.
- [ ] Le pseudo et le score sont demandés dans le bon ordre.
- [ ] Les seuils sont déclarés sous forme de constantes.
- [ ] Les scores négatifs et supérieurs à 1 000 sont refusés.
- [ ] L'opérateur `||` est utilisé pour détecter un score invalide.
- [ ] Chaque score valide appartient à une seule ligue.
- [ ] Les cinq intervalles correspondent au règlement.
- [ ] Les points manquants sont calculés et non écrits directement.
- [ ] Aucun nombre de points manquants négatif n'est affiché.
- [ ] Un joueur Champion reçoit le message spécifique attendu.
- [ ] Un score invalide n'affiche aucun classement.
- [ ] Tous les jeux d'essai, notamment les seuils, produisent le résultat attendu.
- [ ] Le scanner est fermé.
- [ ] Le code ne contient ni boucle, ni tableau, ni `switch`.
- [ ] Les noms des variables et constantes sont explicites.
- [ ] L'indentation permet de distinguer clairement les blocs conditionnels.

## 10. Erreurs fréquentes

### Une valeur placée exactement sur un seuil est mal classée

Vérifiez les opérateurs `<`, `<=`, `>` et `>=`. Par exemple, `score < 200` inclut `199`, mais pas `200`.

### Plusieurs ligues sont affichées

Plusieurs `if` indépendants peuvent tous être évalués. Utilisez une même chaîne `if / else if / else` afin que Java s'arrête après le premier cas correspondant.

### Tous les joueurs appartiennent à la même ligue

Vérifiez l'ordre des conditions. Java les teste de haut en bas. Une condition trop générale placée en premier peut empêcher les suivantes d'être atteintes.

### Les scores invalides reçoivent malgré tout une ligue

Le classement doit être exécuté uniquement lorsque le contrôle de validité a réussi. Examinez l'organisation de vos blocs et de leurs accolades.

### Le contrôle des scores invalides ne fonctionne pas

Pour détecter une valeur située hors de l'intervalle, demandez-vous si elle est trop petite **ou** trop grande. Vérifiez que vous avez utilisé `||` et non `&&`.

### Le nombre de points manquants est incorrect

La formule doit soustraire le score actuel au prochain seuil. L'ordre des deux valeurs dans la soustraction est important.

### `else without if` apparaît à la compilation

Vérifiez les accolades et les points-virgules autour de vos conditions. Un `else` doit être directement associé à un `if`.

## 11. Défis supplémentaires

### Défi 1 — Afficher une division

Ajoutez une division à l'intérieur de chaque ligue :

- division 3 pour le premier tiers de l'intervalle ;
- division 2 pour le deuxième tiers ;
- division 1 pour le dernier tiers.

Définissez précisément vos seuils avant d'écrire les conditions.

### Défi 2 — Ajouter un bonus de victoire

Demandez le nombre de points gagnés lors du dernier match. Calculez le nouveau score sans dépasser 1 000, puis affichez la nouvelle ligue.

### Défi 3 — Afficher un message de progression

Ajoutez un message différent selon le nombre de points manquants : `Vous y êtes presque !` s'il manque 50 points ou moins, sinon `Continuez à jouer pour progresser.`.

Ces défis peuvent être réalisés uniquement avec les variables, les calculs et les conditions présentés dans le README.

## 12. Ce qu'il faut retenir

Une structure conditionnelle permet au programme de choisir un comportement selon les données reçues. `if` teste une première situation, `else if` ajoute d'autres possibilités et `else` traite le dernier cas. L'ordre des conditions et le choix des opérateurs de comparaison sont essentiels, particulièrement aux limites des intervalles. Les jeux d'essai portant sur les seuils permettent de détecter rapidement les erreurs.

