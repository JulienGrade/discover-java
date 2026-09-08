# Exercice 3 — Analyser une session de jeu

## 1. Contexte

Une plateforme de jeu souhaite proposer un bilan à la fin de chaque session. Le joueur indique combien de parties il a disputées, puis renseigne le score obtenu à chacune d'elles.

Le programme doit analyser toute la session et afficher le score total, le score moyen, le meilleur score et le nombre de victoires. Une partie est considérée comme gagnée lorsque son score atteint au moins 1 000 points.

Cet exercice introduit la répétition d'instructions : plutôt que de recopier le même code pour chaque partie, vous utiliserez une boucle.

## 2. Objectifs

Après cet exercice, vous saurez :

- répéter un bloc d'instructions avec une boucle `for` ;
- utiliser une variable de boucle ;
- construire un compteur et un accumulateur ;
- rechercher progressivement une valeur maximale ;
- calculer une moyenne décimale ;
- combiner une boucle et une condition ;
- contrôler un nombre de parties avant de lancer l'analyse.

## 3. Résultat attendu

### Session contenant plusieurs parties

```text
=== Analyse de votre session ===

Quel est votre pseudo ? Nova
Combien de parties avez-vous jouées ? 4

Score de la partie 1 : 850
Score de la partie 2 : 1200
Score de la partie 3 : 1000
Score de la partie 4 : 650

=== Bilan de Nova ===
Nombre de parties : 4
Score total : 3700 points
Score moyen : 925.0 points
Meilleur score : 1200 points
Victoires : 2
```

### Session contenant une seule partie

```text
=== Analyse de votre session ===

Quel est votre pseudo ? Pixel
Combien de parties avez-vous jouées ? 1

Score de la partie 1 : 450

=== Bilan de Pixel ===
Nombre de parties : 1
Score total : 450 points
Score moyen : 450.0 points
Meilleur score : 450 points
Victoires : 0
```

### Nombre de parties invalide

```text
=== Analyse de votre session ===

Quel est votre pseudo ? Kira
Combien de parties avez-vous jouées ? 0

Erreur : le nombre de parties doit être compris entre 1 et 10.
```

Lorsque le nombre de parties est invalide, aucun score ne doit être demandé.

## 4. Notions nécessaires

### Lire des informations avec `Scanner`

`Scanner` permet de récupérer le pseudo, le nombre de parties et les différents scores.

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
```

Utilisez `nextLine()` pour le pseudo et `nextInt()` pour les nombres entiers :

```java
System.out.print("Quel est le nom du participant ? ");
String participant = scanner.nextLine();

System.out.print("Combien de manches ont été jouées ? ");
int nombreManches = scanner.nextInt();
```

Dans cet exercice, toutes les saisies qui suivent le pseudo sont des nombres entiers. Le problème du retour à la ligne entre `nextInt()` et `nextLine()` ne se présente donc pas.

Fermez le scanner une fois toutes les saisies terminées :

```java
scanner.close();
```

Vous pouvez considérer que l'utilisateur saisit bien des nombres entiers lorsqu'un nombre est attendu. La gestion d'une saisie comme `abc` n'est pas demandée.

### Pourquoi utiliser une boucle ?

Sans boucle, demander trois valeurs obligerait à écrire trois fois des instructions très proches :

```java
System.out.print("Valeur 1 : ");
int valeur1 = scanner.nextInt();

System.out.print("Valeur 2 : ");
int valeur2 = scanner.nextInt();

System.out.print("Valeur 3 : ");
int valeur3 = scanner.nextInt();
```

Cette solution ne convient plus lorsque le nombre de valeurs dépend d'une saisie. Une boucle permet de répéter le même traitement autant de fois que nécessaire.

### Répéter avec une boucle `for`

La boucle `for` est adaptée lorsque le nombre de répétitions est connu avant son démarrage.

Syntaxe générale :

```java
for (initialisation; condition; évolution) {
    instructions à répéter
}
```

Exemple indépendant de l'exercice :

```java
for (int numeroJour = 1; numeroJour <= 5; numeroJour++) {
    System.out.println("Jour " + numeroJour);
}
```

Cette boucle contient trois parties :

| Partie | Code | Rôle |
|---|---|---|
| Initialisation | `int numeroJour = 1` | crée la variable avant la première répétition |
| Condition | `numeroJour <= 5` | autorise la répétition tant que la condition est vraie |
| Évolution | `numeroJour++` | ajoute 1 après chaque répétition |

Le résultat est :

```text
Jour 1
Jour 2
Jour 3
Jour 4
Jour 5
```

La variable `numeroJour` est appelée variable de boucle. Elle permet également de connaître le numéro de la répétition en cours.

### Comprendre l'incrémentation

L'instruction suivante augmente une variable de 1 :

```java
compteur++;
```

Elle est équivalente à :

```java
compteur = compteur + 1;
```

Erreur fréquente : oublier l'incrémentation dans une boucle ou utiliser `--` à la place de `++`. La condition peut alors ne jamais devenir fausse.

### Éviter les erreurs de limite

La condition détermine le nombre de répétitions.

```java
for (int numero = 1; numero <= 3; numero++) {
    System.out.println(numero);
}
```

Cette boucle affiche `1`, `2` et `3` : elle effectue trois répétitions.

Avec `numero < 3`, elle n'afficherait que `1` et `2`. Cette erreur est souvent appelée erreur de décalage ou erreur « off-by-one ».

Dans cet exercice, la première partie doit porter le numéro 1 et la dernière le numéro choisi par l'utilisateur.

### Utiliser un accumulateur

Un accumulateur conserve un total qui évolue à chaque passage dans la boucle. Il doit être déclaré et initialisé avant la boucle.

```java
int totalDuree = 0;

for (int numeroEpisode = 1; numeroEpisode <= 3; numeroEpisode++) {
    System.out.print("Durée de l'épisode " + numeroEpisode + " : ");
    int duree = scanner.nextInt();
    totalDuree = totalDuree + duree;
}
```

L'écriture suivante est une forme raccourcie équivalente :

```java
totalDuree += duree;
```

L'accumulateur commence généralement à `0`, car aucune valeur n'a encore été ajoutée avant la boucle.

Erreur fréquente : déclarer ou remettre l'accumulateur à zéro à l'intérieur de la boucle. Le total précédent serait alors perdu à chaque répétition.

### Utiliser un compteur

Un compteur mémorise combien de fois un événement s'est produit.

```java
int nombreNotesPositives = 0;

if (note >= 10) {
    nombreNotesPositives++;
}
```

Placée dans une boucle, cette condition permet de compter toutes les valeurs qui respectent une règle. Dans l'exercice, vous utiliserez ce principe pour compter les victoires.

Un compteur n'est pas la même chose qu'un accumulateur :

| Variable | Ce qu'elle mémorise | Mise à jour habituelle |
|---|---|---|
| Compteur | un nombre d'événements | `compteur++` |
| Accumulateur | la somme de plusieurs valeurs | `total += valeur` |

### Rechercher le maximum

Pour trouver la plus grande valeur sans tableau, conservez le meilleur résultat rencontré jusque-là.

Si les valeurs sont obligatoirement positives ou nulles, vous pouvez initialiser le maximum à `0` :

```java
int distanceMaximale = 0;
```

À chaque nouvelle valeur, comparez-la au maximum actuel :

```java
if (distance > distanceMaximale) {
    distanceMaximale = distance;
}
```

Le maximum n'est remplacé que lorsqu'une valeur plus élevée est rencontrée.

Exemple avec les valeurs `8`, `12` et `10` :

| Valeur examinée | Maximum avant | Maximum après |
|---:|---:|---:|
| 8 | 0 | 8 |
| 12 | 8 | 12 |
| 10 | 12 | 12 |

Erreur fréquente : affecter systématiquement la nouvelle valeur au maximum sans effectuer de comparaison. Le programme mémoriserait alors la dernière valeur, pas la plus grande.

### Calculer une moyenne décimale

Une moyenne se calcule en divisant le total par le nombre de valeurs :

```text
moyenne = total / nombre de valeurs
```

En Java, la division de deux `int` produit un résultat entier : la partie décimale est supprimée.

```java
int total = 7;
int quantite = 2;
double moyenneIncorrecte = total / quantite; // donne 3.0, pas 3.5
```

Pour obtenir une division décimale, convertissez l'une des deux valeurs en `double` :

```java
double moyenne = (double) total / quantite;
```

`(double)` réalise ici une conversion de type, aussi appelée cast. La division est alors effectuée avec des nombres décimaux.

Erreur fréquente : écrire `(double) (total / quantite)`. La conversion aurait lieu après la division entière et la partie décimale serait déjà perdue.

### Combiner boucle et condition

Une condition peut être placée à l'intérieur d'une boucle. Elle est alors évaluée à chaque répétition.

```java
int nombreProduitsEnRupture = 0;

for (int numeroProduit = 1; numeroProduit <= 4; numeroProduit++) {
    System.out.print("Stock du produit " + numeroProduit + " : ");
    int stock = scanner.nextInt();

    if (stock == 0) {
        nombreProduitsEnRupture++;
    }
}
```

Après quatre saisies, la variable contient le nombre de produits dont le stock est égal à zéro.

### Valider avant de démarrer la boucle

Le programme ne doit analyser une session que si le nombre de parties est compris entre les limites autorisées.

La structure générale peut être organisée ainsi :

```java
if (valeur trop petite ou valeur trop grande) {
    afficher une erreur
} else {
    effectuer les répétitions et les calculs
}
```

Cette structure empêche la boucle de démarrer lorsque la saisie initiale est invalide.

## 5. Consignes fonctionnelles

Votre programme doit respecter les règles suivantes :

1. une session contient entre 1 et 10 parties incluses ;
2. un score est un entier positif ou nul ;
3. une partie est gagnée si son score est supérieur ou égal à 1 000 points ;
4. le score total est la somme des scores de toutes les parties ;
5. le score moyen est affiché sous la forme d'un nombre décimal ;
6. le meilleur score correspond à la plus grande valeur saisie.

Le programme doit :

1. afficher le titre `=== Analyse de votre session ===` ;
2. demander le pseudo du joueur ;
3. demander le nombre de parties jouées ;
4. refuser un nombre de parties inférieur à 1 ou supérieur à 10 ;
5. demander le score de chaque partie à l'aide d'une boucle ;
6. afficher le numéro de la partie dans chaque question ;
7. mettre à jour le total, le meilleur score et le compteur de victoires après chaque saisie ;
8. calculer la moyenne après la boucle ;
9. afficher le bilan complet de la session.

Pour maintenir l'exercice centré sur les boucles, vous pouvez considérer que chaque score saisi est un entier positif ou nul. Le contrôle individuel des scores n'est pas demandé.

## 6. Contraintes techniques

- Créez `AnalyseSession.java` dans le dossier `exercice-3/src`.
- La classe publique doit se nommer `AnalyseSession`.
- N'ajoutez aucune instruction `package`.
- Utilisez un objet `Scanner` pour toutes les saisies.
- Utilisez une boucle `for` pour demander les scores.
- La boucle doit s'adapter au nombre de parties saisi.
- Déclarez le nombre minimal de parties, le nombre maximal de parties et le score nécessaire pour gagner sous forme de constantes `final int`.
- Utilisez un accumulateur pour le score total.
- Utilisez un compteur pour le nombre de victoires.
- Utilisez une condition pour mettre à jour le meilleur score.
- Calculez la moyenne dans une variable de type `double`.
- Utilisez une conversion en `double` pour éviter la division entière.
- Fermez le scanner après la dernière saisie.
- N'utilisez pas de tableau, de collection, de méthode personnalisée, de classe supplémentaire, de boucle `while` ou de boucle `do-while`.
- Ne recopiez pas plusieurs fois l'instruction de saisie d'un score.

Depuis le dossier `exercice-3`, compilez avec :

```bash
javac -d out src/AnalyseSession.java
```

Puis exécutez avec :

```bash
java -cp out AnalyseSession
```

Sous Windows :

```powershell
javac -d out src\AnalyseSession.java
java -cp out AnalyseSession
```

## 7. Étapes de réalisation

### Étape 1 — Préparer le programme

Créez la classe, la méthode `main` et le scanner. Affichez le titre du programme.

Vérification : le fichier doit compiler et afficher le titre.

### Étape 2 — Récupérer les informations générales

Demandez le pseudo et le nombre de parties. Déclarez les constantes nécessaires.

Vérification : affichez temporairement les deux valeurs saisies.

### Étape 3 — Contrôler le nombre de parties

Ajoutez une condition qui refuse une valeur inférieure à 1 ou supérieure à 10. Placez la suite du traitement dans le cas valide.

Vérification : avec `0` ou `11`, aucun score ne doit être demandé.

### Étape 4 — Créer la boucle

Ajoutez une boucle `for` qui effectue exactement autant de répétitions que de parties annoncées. À chaque passage, demandez et lisez un score.

Vérification : pour trois parties, le programme doit poser exactement trois questions numérotées de 1 à 3.

### Étape 5 — Calculer le total

Déclarez l'accumulateur avant la boucle, initialisez-le correctement, puis ajoutez chaque score.

Vérification : avec les scores `100`, `200` et `300`, le total doit être `600`.

### Étape 6 — Rechercher le meilleur score

Déclarez une variable avant la boucle. À chaque saisie, remplacez sa valeur uniquement si le nouveau score est supérieur au meilleur score actuel.

Vérification : avec `400`, `900` et `600`, le meilleur score doit rester `900`.

### Étape 7 — Compter les victoires

Déclarez un compteur initialisé à zéro. Dans la boucle, testez chaque score et incrémentez ce compteur lorsqu'il atteint le seuil de victoire.

Vérification : `999` n'est pas une victoire, tandis que `1 000` en est une.

### Étape 8 — Calculer la moyenne

Après la boucle, divisez le total par le nombre de parties. Forcez une division décimale grâce à une conversion en `double`.

Vérification : un total de `7` pour deux parties doit produire `3.5`, et non `3.0`.

### Étape 9 — Afficher le bilan

Construisez l'affichage final en reprenant tous les résultats calculés. Supprimez les éventuels affichages temporaires.

Vérification : comparez la présentation avec les exemples de résultat attendu.

### Étape 10 — Tester les cas limites

Exécutez tous les jeux d'essai. Contrôlez particulièrement les nombres de parties `0`, `1`, `10` et `11`, ainsi que les scores `999` et `1 000`.

## 8. Jeux d'essai

### Validation du nombre de parties

| Nombre de parties | Comportement attendu |
|---:|---|
| 0 | message d'erreur, aucun score demandé |
| 1 | une seule saisie de score |
| 10 | dix saisies de score |
| 11 | message d'erreur, aucun score demandé |

### Calculs sur une session

| Scores | Total | Moyenne | Meilleur score | Victoires |
|---|---:|---:|---:|---:|
| `450` | 450 | 450.0 | 450 | 0 |
| `100`, `200`, `300` | 600 | 200.0 | 300 | 0 |
| `850`, `1200`, `1000`, `650` | 3 700 | 925.0 | 1 200 | 2 |
| `1000`, `999`, `1001` | 3 000 | 1000.0 | 1 001 | 2 |
| `0`, `0` | 0 | 0.0 | 0 | 0 |

Les espaces utilisés dans le tableau pour rendre les grands nombres lisibles ne doivent pas être saisis dans la console. Saisissez par exemple `1200`, et non `1 200`.

## 9. Critères de validation

- [ ] Le fichier et la classe se nomment `AnalyseSession`.
- [ ] Le programme compile et s'exécute avec les commandes fournies.
- [ ] Le nombre de parties est limité à l'intervalle de 1 à 10.
- [ ] Aucun score n'est demandé lorsque le nombre de parties est invalide.
- [ ] Une boucle `for` réalise toutes les saisies de scores.
- [ ] Les questions sont correctement numérotées à partir de 1.
- [ ] Le nombre de répétitions correspond exactement au nombre de parties.
- [ ] Le score total est calculé avec un accumulateur.
- [ ] Le meilleur score est correctement mis à jour.
- [ ] Une victoire est comptée à partir de 1 000 points inclus.
- [ ] Le score moyen conserve sa partie décimale.
- [ ] Les calculs sont effectués à partir des saisies et ne sont pas écrits directement dans les affichages.
- [ ] Tous les résultats demandés apparaissent dans le bilan.
- [ ] Tous les jeux d'essai produisent les résultats attendus.
- [ ] Le scanner est fermé après les saisies.
- [ ] Aucun tableau, aucune collection et aucune autre forme de boucle ne sont utilisés.
- [ ] Les compteurs et accumulateurs sont déclarés avant la boucle.
- [ ] Le code est lisible, correctement indenté et utilise des noms explicites.

## 10. Erreurs fréquentes

### La boucle pose une question de trop ou de moins

Vérifiez la valeur initiale de la variable de boucle et l'opérateur utilisé dans sa condition. Si la numérotation commence à 1, déterminez précisément la dernière valeur qu'elle doit pouvoir atteindre.

### Toutes les questions affichent le même numéro

Le numéro affiché doit provenir de la variable de boucle, sans être placé entre guillemets.

### Le total correspond uniquement au dernier score

Vérifiez que vous ajoutez le nouveau score au total existant. Une simple affectation remplace le total précédent.

### Le total revient à zéro à chaque répétition

L'accumulateur doit être déclaré et initialisé avant la boucle. S'il est initialisé dans la boucle, les résultats précédents sont effacés.

### Le meilleur score correspond au dernier score

Ne remplacez le meilleur score que lorsque la nouvelle valeur lui est supérieure.

### Le score moyen perd sa partie décimale

Une division entre deux `int` est une division entière. Convertissez l'une des valeurs en `double` avant que la division soit effectuée.

### Une partie à exactement 1 000 points n'est pas comptée comme victoire

Le règlement dit « au moins 1 000 points ». Vérifiez si votre opérateur de comparaison inclut la valeur limite.

### Les résultats sont affichés après chaque partie

Le bilan général doit être affiché après la fin de la boucle. Seule la demande du score doit être répétée.

### Le programme divise par zéro

La moyenne ne doit être calculée que dans le cas où le nombre de parties est valide. La valeur minimale autorisée est 1.

## 11. Défis supplémentaires

### Défi 1 — Compter les parties parfaites

Une partie est parfaite lorsqu'elle atteint au moins 1 500 points. Ajoutez une constante, un compteur et une ligne dans le bilan.

### Défi 2 — Afficher le plus petit score

Recherchez également le moins bon score. Attention : une initialisation à zéro ne convient pas si tous les scores sont positifs. Vous pouvez initialiser le minimum avec le score de la première partie grâce à une condition portant sur le numéro de la partie.

### Défi 3 — Évaluer la session

Ajoutez un message final selon la moyenne :

- moins de 500 points : `Session difficile` ;
- de 500 à 999,99 points : `Bonne session` ;
- au moins 1 000 points : `Excellente session`.

## 12. Ce qu'il faut retenir

Une boucle `for` est adaptée lorsque le nombre de répétitions est connu. Les variables déclarées avant la boucle peuvent conserver et faire évoluer des informations pendant toutes les répétitions : un accumulateur construit une somme, un compteur dénombre des événements et une variable de maximum mémorise la meilleure valeur rencontrée. Une condition placée dans la boucle permet d'analyser séparément chaque saisie.


