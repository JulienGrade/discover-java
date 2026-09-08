# Exercice 5 — Analyser les performances de publications

## 1. Contexte

Un créateur de contenu souhaite analyser les performances de ses dernières publications. Il connaît le nombre de vues obtenu par chacune d'elles, mais il veut rapidement identifier sa meilleure publication et savoir combien de publications dépassent sa moyenne.

Vous devez développer un programme qui enregistre le nombre de vues de plusieurs publications dans un tableau. Une fois toutes les données saisies, le programme les parcourt à nouveau pour calculer les indicateurs et afficher un bilan.

Le tableau est indispensable ici : la moyenne ne peut être calculée qu'après avoir obtenu le total, mais il faut ensuite revenir sur chaque publication pour déterminer si elle dépasse cette moyenne.

## 2. Objectifs

Après cet exercice, vous saurez :

- déclarer et créer un tableau en Java ;
- accéder à une case grâce à son indice ;
- utiliser la propriété `length` ;
- remplir et parcourir un tableau avec une boucle ;
- transmettre un tableau à une méthode ;
- calculer des statistiques à partir des valeurs conservées ;
- retrouver l'indice de la plus grande valeur ;
- effectuer plusieurs parcours d'un même tableau.

## 3. Résultat attendu

### Analyse de quatre publications

```text
=== Analyse des publications ===

Quel est le nom du compte ? NovaGaming
Combien de publications souhaitez-vous analyser ? 4

Nombre de vues de la publication 1 : 1200
Nombre de vues de la publication 2 : 3400
Nombre de vues de la publication 3 : 2800
Nombre de vues de la publication 4 : 4600

=== Bilan de NovaGaming ===
Publication 1 : 1200 vues
Publication 2 : 3400 vues
Publication 3 : 2800 vues
Publication 4 : 4600 vues

Total des vues : 12000
Moyenne des vues : 3000.0
Meilleure publication : publication 4 avec 4600 vues
Publications au-dessus de la moyenne : 2
```

### Une seule publication

```text
=== Analyse des publications ===

Quel est le nom du compte ? StudioPixel
Combien de publications souhaitez-vous analyser ? 1

Nombre de vues de la publication 1 : 750

=== Bilan de StudioPixel ===
Publication 1 : 750 vues

Total des vues : 750
Moyenne des vues : 750.0
Meilleure publication : publication 1 avec 750 vues
Publications au-dessus de la moyenne : 0
```

Une publication exactement égale à la moyenne n'est pas comptée comme étant au-dessus de la moyenne.

### Nombre de publications invalide

```text
=== Analyse des publications ===

Quel est le nom du compte ? Test
Combien de publications souhaitez-vous analyser ? 11

Erreur : le nombre de publications doit être compris entre 1 et 10.
```

Dans ce cas, aucun tableau de vues ne doit être rempli.

## 4. Notions nécessaires

### Pourquoi utiliser un tableau ?

Une variable simple ne conserve qu'une seule valeur :

```java
int nombreVues = 850;
```

Pour enregistrer cinq valeurs sans tableau, il faudrait créer cinq variables différentes. Cette solution devient difficile à parcourir et ne s'adapte pas à un nombre choisi par l'utilisateur.

Un tableau regroupe plusieurs valeurs de même type sous un seul nom :

```java
int[] temperatures = {18, 21, 19, 23};
```

Ici, `temperatures` contient quatre valeurs de type `int`.

Un tableau possède une taille fixe. Une fois créé, il est impossible d'ajouter ou de supprimer une case. Il est toutefois possible de remplacer la valeur contenue dans une case.

### Déclarer une variable de type tableau

La présence de `[]` indique qu'une variable référence un tableau.

```java
int[] scores;
String[] pseudos;
double[] moyennes;
```

Ces instructions déclarent les variables, mais ne créent pas encore les tableaux.

La forme suivante est également acceptée par Java :

```java
int scores[];
```

Préférez néanmoins `int[] scores`, car les crochets placés avec le type rendent plus clairement visible la nature de la variable.

### Créer un tableau avec une taille déterminée

Le mot-clé `new` permet de créer le tableau :

```java
int nombreJours = 7;
int[] pasParJour = new int[nombreJours];
```

Le tableau possède ici sept cases, toutes capables de contenir un `int`.

La taille peut provenir d'une variable, à condition qu'elle contienne un entier positif ou nul au moment de la création.

Après sa création, la taille ne peut plus être modifiée :

```java
pasParJour = new int[10];
```

Cette instruction ne redimensionne pas le premier tableau. Elle crée un nouveau tableau et remplace la référence conservée dans la variable. Les anciennes valeurs sont alors perdues si aucune autre variable ne les référence.

### Connaître les valeurs par défaut

Lorsqu'un tableau est créé avec `new`, Java initialise automatiquement ses cases.

| Type des cases | Valeur par défaut |
|---|---|
| `int` | `0` |
| `double` | `0.0` |
| `boolean` | `false` |
| `String` | `null` |

```java
int[] quantites = new int[3];
System.out.println(quantites[0]); // affiche 0
```

Dans cet exercice, toutes les cases seront remplacées par les valeurs saisies avant d'être analysées.

### Comprendre les indices

Chaque case possède une position appelée indice. Le premier indice est toujours `0`.

Pour un tableau de quatre éléments :

| Élément | Premier | Deuxième | Troisième | Quatrième |
|---|---:|---:|---:|---:|
| Indice Java | 0 | 1 | 2 | 3 |

Exemple :

```java
String[] plateformes = {"PC", "Xbox", "PlayStation"};

System.out.println(plateformes[0]); // PC
System.out.println(plateformes[2]); // PlayStation
```

Le dernier indice est toujours égal à la taille du tableau moins un.

Erreur fréquente : utiliser l'indice `1` pour accéder au premier élément.

### Lire et modifier une case

Utilisez l'indice entre crochets pour accéder à une valeur :

```java
int[] stocks = new int[3];

stocks[0] = 12;
stocks[1] = 8;
stocks[2] = 15;

int premierStock = stocks[0];
```

L'affectation remplace la valeur précédente de la case.

Un indice invalide provoque une erreur pendant l'exécution :

```java
stocks[3] = 20; // erreur : les indices valides sont 0, 1 et 2
```

Java signale alors une `ArrayIndexOutOfBoundsException`.

### Obtenir la taille avec `length`

La propriété `length` indique le nombre de cases du tableau :

```java
int[] scores = new int[5];
System.out.println(scores.length); // 5
```

Pour un tableau, `length` s'utilise sans parenthèses. Ce n'est pas une méthode.

```java
scores.length   // correct
scores.length() // incorrect pour un tableau
```

Utiliser `length` évite de recopier la taille dans les boucles.

### Parcourir un tableau avec ses indices

Une boucle `for` permet de visiter toutes les cases :

```java
int[] distances = {5, 8, 3};

for (int indice = 0; indice < distances.length; indice++) {
    System.out.println(distances[indice]);
}
```

La boucle commence à `0` et continue tant que l'indice est strictement inférieur à la taille. Pour un tableau de trois cases, elle utilise les indices `0`, `1` et `2`.

La condition suivante est incorrecte :

```java
indice <= distances.length
```

Elle essaierait d'accéder à une case située après la fin du tableau.

### Faire correspondre indice informatique et numéro affiché

L'utilisateur s'attend à voir une numérotation commençant à 1, tandis que le tableau commence à l'indice 0.

```java
for (int indice = 0; indice < notes.length; indice++) {
    int numeroAffiche = indice + 1;
    System.out.println("Note " + numeroAffiche + " : " + notes[indice]);
}
```

L'indice sert à accéder au tableau. `indice + 1` sert uniquement à produire une numérotation naturelle pour l'utilisateur.

### Remplir un tableau avec des saisies

Le tableau doit être créé avant la boucle. Chaque saisie est ensuite affectée à la case correspondant à l'indice courant.

```java
int[] durees = new int[nombreActivites];

for (int indice = 0; indice < durees.length; indice++) {
    System.out.print("Durée de l'activité " + (indice + 1) + " : ");
    durees[indice] = scanner.nextInt();
}
```

Les parenthèses autour de `indice + 1` sont importantes dans une concaténation. Sans elles, Java pourrait assembler les éléments comme du texte au lieu d'effectuer d'abord l'addition.

### Parcourir plusieurs fois les mêmes données

Une fois rempli, un tableau conserve toutes ses valeurs. Vous pouvez donc créer plusieurs boucles successives :

```java
for (int indice = 0; indice < valeurs.length; indice++) {
    // Premier traitement
}

for (int indice = 0; indice < valeurs.length; indice++) {
    // Deuxième traitement
}
```

Dans cet exercice :

1. un premier parcours remplit le tableau ;
2. une méthode parcourt le tableau pour calculer le total ;
3. une autre recherche la meilleure publication ;
4. un dernier parcours compte les publications supérieures à la moyenne.

Ces parcours ne modifient pas nécessairement les valeurs. Ils peuvent simplement les lire.

### Transmettre un tableau à une méthode

Un tableau peut être reçu comme paramètre :

```java
public static int calculerTotal(int[] valeurs) {
    int total = 0;

    for (int indice = 0; indice < valeurs.length; indice++) {
        total += valeurs[indice];
    }

    return total;
}
```

L'appel s'effectue avec la variable qui référence le tableau :

```java
int total = calculerTotal(distances);
```

Il ne faut pas ajouter `[]` lors de l'appel.

```java
calculerTotal(distances);   // correct
calculerTotal(distances[]); // incorrect
```

### Un tableau transmis peut être modifié

Lorsqu'une méthode reçoit un tableau, elle accède au même tableau que le code appelant. Une modification d'une case reste donc visible après l'appel.

```java
public static void doublerPremiereValeur(int[] valeurs) {
    valeurs[0] = valeurs[0] * 2;
}
```

Dans cet exercice, les méthodes d'analyse doivent uniquement lire les valeurs. Seule la boucle de saisie remplit le tableau.

### Rechercher l'indice du maximum

Il est souvent plus utile de conserver la position de la meilleure valeur que la valeur seule. L'indice permet ensuite de retrouver les deux informations.

```java
int indiceMaximum = 0;

for (int indice = 1; indice < valeurs.length; indice++) {
    if (valeurs[indice] > valeurs[indiceMaximum]) {
        indiceMaximum = indice;
    }
}
```

La recherche commence à l'indice `1`, car la première case, d'indice `0`, sert de meilleur résultat initial.

Après la boucle :

- `indiceMaximum` contient la position de la meilleure valeur ;
- `valeurs[indiceMaximum]` contient la meilleure valeur elle-même ;
- `indiceMaximum + 1` donne le numéro à afficher à l'utilisateur.

En cas d'égalité, l'opérateur `>` conserve la première occurrence du maximum. Avec `>=`, la dernière occurrence remplacerait la première.

### Calculer et utiliser une moyenne

Le total peut être divisé par le nombre de cases :

```java
double moyenne = (double) total / valeurs.length;
```

La conversion en `double` évite une division entière.

Une fois la moyenne connue, un nouveau parcours peut comparer toutes les valeurs :

```java
int nombreAuDessus = 0;

for (int indice = 0; indice < valeurs.length; indice++) {
    if (valeurs[indice] > moyenne) {
        nombreAuDessus++;
    }
}
```

Utilisez `>` et non `>=` si une valeur égale à la moyenne ne doit pas être comptée.

## 5. Consignes fonctionnelles

Le nombre de publications doit être compris entre 1 et 10 inclus. Chaque nombre de vues doit être un entier positif ou nul.

Pour maintenir l'exercice centré sur les tableaux, vous pouvez considérer que les nombres de vues saisis sont valides. Seul le nombre de publications doit être contrôlé.

Le programme doit :

1. demander le nom du compte ;
2. demander le nombre de publications à analyser ;
3. afficher une erreur si ce nombre est hors limites ;
4. créer un tableau dont la taille correspond au nombre choisi ;
5. remplir chaque case avec une saisie ;
6. afficher toutes les publications et leur nombre de vues ;
7. calculer le total des vues ;
8. calculer la moyenne sous forme décimale ;
9. retrouver la première publication ayant obtenu le maximum de vues ;
10. compter les publications strictement supérieures à la moyenne ;
11. afficher le bilan complet.

## 6. Contraintes techniques

- Créez `AnalysePublications.java` dans `exercice-5/src`.
- La classe publique doit se nommer `AnalysePublications`.
- N'ajoutez aucune instruction `package`.
- Utilisez un unique tableau de type `int[]` pour conserver les vues.
- La taille du tableau doit provenir de la saisie du nombre de publications.
- Utilisez des indices commençant à `0` dans le code et des numéros commençant à `1` dans les affichages.
- Utilisez `length` pour définir les limites des parcours.
- Déclarez obligatoirement les méthodes suivantes :

```java
public static int calculerTotal(int[] vues)

public static int trouverIndiceMeilleurePublication(int[] vues)

public static int compterPublicationsAuDessus(
        int[] vues,
        double moyenne
)

public static void afficherPublications(int[] vues)
```

- `calculerTotal` doit retourner la somme de toutes les cases.
- `trouverIndiceMeilleurePublication` doit retourner un indice, pas directement le maximum.
- En cas d'égalité pour le maximum, conservez la première publication concernée.
- `compterPublicationsAuDessus` doit utiliser la moyenne reçue en paramètre.
- `afficherPublications` doit afficher le numéro et le nombre de vues de chaque publication.
- Les méthodes d'analyse ne doivent pas modifier le tableau.
- Calculez la moyenne dans `main` à partir du total retourné et de `vues.length`.
- N'utilisez pas une variable différente pour chaque publication.
- N'utilisez pas de collection, de tableau à plusieurs dimensions, de tri ou de classe supplémentaire.
- Fermez le scanner après la dernière saisie.

Depuis `exercice-5`, compilez avec :

```bash
javac -d out src/AnalysePublications.java
```

Puis exécutez avec :

```bash
java -cp out AnalysePublications
```

Sous Windows :

```powershell
javac -d out src\AnalysePublications.java
java -cp out AnalysePublications
```

## 7. Étapes de réalisation

### Étape 1 — Préparer les saisies générales

Créez la classe, `main` et le scanner. Demandez le nom du compte et le nombre de publications. Contrôlez l'intervalle de 1 à 10.

Vérification : avec `0` ou `11`, le programme doit afficher l'erreur sans demander de vues.

### Étape 2 — Créer le tableau

Dans le cas valide, créez un tableau de `int` dont la taille correspond au nombre de publications.

Vérification : sa propriété `length` doit être égale au nombre saisi.

### Étape 3 — Remplir le tableau

Utilisez une boucle fondée sur les indices. Demandez une valeur pour chaque publication et affectez-la à la bonne case.

Vérification : la première question affiche `publication 1`, mais la réponse est enregistrée à l'indice `0`.

### Étape 4 — Afficher le contenu

Créez `afficherPublications`. Parcourez le tableau et affichez chaque numéro et chaque valeur au format demandé.

Vérification : toutes les cases doivent être affichées dans leur ordre de saisie.

### Étape 5 — Calculer le total

Créez `calculerTotal`, initialisez un accumulateur et parcourez toutes les cases avant de retourner le résultat.

Vérification : pour `{100, 250, 150}`, la méthode retourne `500`.

### Étape 6 — Calculer la moyenne

Dans `main`, divisez le total par la taille du tableau en forçant une division décimale.

Vérification : un total de `7` pour deux publications doit donner `3.5`.

### Étape 7 — Rechercher la meilleure publication

Créez `trouverIndiceMeilleurePublication`. Utilisez la première case comme référence initiale, puis comparez les cases suivantes.

Vérification : avec `{800, 1500, 1200}`, retournez l'indice `1`, qui correspond à la publication numéro 2.

### Étape 8 — Compter les publications au-dessus de la moyenne

Créez `compterPublicationsAuDessus`. Parcourez le tableau, comparez chaque valeur à la moyenne reçue et incrémentez un compteur.

Vérification : avec `{100, 200, 300}` et une moyenne de `200.0`, seule une publication est strictement au-dessus.

### Étape 9 — Construire le bilan

Appelez les méthodes depuis `main`, puis affichez le total, la moyenne, le numéro et les vues de la meilleure publication ainsi que le compteur calculé.

Vérification : pour retrouver les vues maximales, utilisez le tableau avec l'indice retourné.

### Étape 10 — Tester les limites

Testez les tailles minimale et maximale, les égalités pour le maximum et les valeurs égales à la moyenne.

## 8. Jeux d'essai

### Tailles du tableau

| Nombre de publications | Comportement attendu |
|---:|---|
| 0 | erreur, aucune vue demandée |
| 1 | tableau d'une case |
| 10 | tableau de dix cases |
| 11 | erreur, aucune vue demandée |

### Calculs

| Vues saisies | Total | Moyenne | Meilleure publication | Au-dessus de la moyenne |
|---|---:|---:|---|---:|
| `{750}` | 750 | 750.0 | n° 1, 750 vues | 0 |
| `{100, 200, 300}` | 600 | 200.0 | n° 3, 300 vues | 1 |
| `{1200, 3400, 2800, 4600}` | 12 000 | 3000.0 | n° 4, 4 600 vues | 2 |
| `{500, 500, 500}` | 1 500 | 500.0 | n° 1, 500 vues | 0 |
| `{900, 1500, 1500, 600}` | 4 500 | 1125.0 | n° 2, 1 500 vues | 2 |
| `{0, 0}` | 0 | 0.0 | n° 1, 0 vue | 0 |

Le tableau `{900, 1500, 1500, 600}` vérifie que la première occurrence du maximum est conservée.

## 9. Critères de validation

- [ ] Le fichier et la classe se nomment `AnalysePublications`.
- [ ] Le programme compile et s'exécute avec les commandes fournies.
- [ ] Le nombre de publications est limité à l'intervalle de 1 à 10.
- [ ] Un unique tableau `int[]` conserve toutes les vues.
- [ ] La taille du tableau correspond exactement à la saisie.
- [ ] Toutes les cases sont remplies grâce à une boucle.
- [ ] Les indices du tableau commencent à 0.
- [ ] Les numéros affichés commencent à 1.
- [ ] Tous les parcours utilisent la propriété `length`.
- [ ] Les quatre méthodes imposées existent avec les bonnes signatures.
- [ ] Le total est calculé par `calculerTotal`.
- [ ] La moyenne conserve sa partie décimale.
- [ ] La recherche retourne l'indice de la première valeur maximale.
- [ ] Les vues maximales sont retrouvées grâce à cet indice.
- [ ] Seules les valeurs strictement supérieures à la moyenne sont comptées.
- [ ] Les méthodes d'analyse ne modifient aucune case.
- [ ] Le programme fonctionne avec une seule publication.
- [ ] Tous les jeux d'essai produisent les résultats attendus.
- [ ] Le scanner est fermé après les saisies.
- [ ] Le code ne contient ni collection, ni tri, ni tableau supplémentaire.
- [ ] Les méthodes, variables et paramètres portent des noms explicites.

## 10. Erreurs fréquentes

### `ArrayIndexOutOfBoundsException`

Le programme essaie d'accéder à un indice inexistant. Vérifiez que les boucles commencent à `0` et utilisent `indice < tableau.length`.

### La dernière publication n'est pas analysée

Vérifiez la condition d'arrêt de la boucle. Le dernier indice valide est `length - 1`, mais la boucle doit pouvoir l'atteindre.

### Une publication numéro 0 est affichée

L'indice informatique commence à 0, mais le numéro présenté à l'utilisateur doit commencer à 1. Ajoutez 1 uniquement lors de l'affichage.

### Toutes les cases restent à zéro

Vérifiez que chaque saisie est affectée à `tableau[indice]` et pas seulement à une variable temporaire.

### Le programme affiche l'adresse apparente du tableau

Afficher directement une variable tableau avec `System.out.println(vues)` ne montre pas son contenu. Parcourez ses cases dans `afficherPublications`.

### La moyenne est arrondie à l'entier inférieur

Une division entre deux `int` supprime la partie décimale. Convertissez le total en `double` avant la division.

### Le numéro de la meilleure publication est décalé

La méthode retourne un indice commençant à 0. Ajoutez 1 pour produire le numéro visible, mais utilisez l'indice original pour accéder au tableau.

### La dernière occurrence du maximum est conservée

Si deux publications possèdent le même maximum, vérifiez l'opérateur de comparaison. Le règlement demande de remplacer l'indice uniquement lorsqu'une valeur est strictement supérieure.

### Les publications égales à la moyenne sont comptées

La consigne exige une comparaison strictement supérieure. Vérifiez l'opérateur utilisé.

### La méthode ne voit pas le tableau créé dans `main`

Une variable locale n'est accessible que dans sa méthode. Passez le tableau en argument lors de l'appel de la méthode.

## 11. Défis supplémentaires

### Défi 1 — Afficher un taux de performance

Calculez le pourcentage de publications situées strictement au-dessus de la moyenne. Utilisez un `double` pour conserver les décimales.

### Défi 2 — Rechercher la moins bonne publication

Ajoutez une méthode qui retourne l'indice de la première publication possédant le plus petit nombre de vues.

### Défi 3 — Afficher un indicateur pour chaque publication

Lors de l'affichage du tableau, ajoutez `Au-dessus de la moyenne`, `Dans la moyenne` ou `Sous la moyenne`. Pour cela, transmettez la moyenne à la méthode d'affichage.

## 12. Ce qu'il faut retenir

Un tableau conserve plusieurs valeurs de même type dans un ordre précis. Ses cases sont accessibles par des indices commençant à zéro et sa taille est disponible avec `length`. Une boucle utilisant `indice < tableau.length` permet de parcourir toutes les cases sans dépasser ses limites.

Conserver les données rend possibles plusieurs analyses successives. Un indice peut également être plus utile qu'une valeur seule : il permet de connaître la position d'un résultat et de retrouver ensuite la valeur correspondante dans le tableau.

