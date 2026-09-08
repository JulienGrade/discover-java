# Exercice 9 — Diffuser une alerte sur plusieurs canaux

## 1. Contexte

Une plateforme communautaire doit prévenir ses utilisateurs lorsqu'un événement important se produit : début d'un tournoi, changement d'horaire ou indisponibilité temporaire. Une même alerte peut être diffusée par e-mail, SMS et notification push.

Chaque canal possède son propre format d'envoi, mais le service de diffusion doit pouvoir les utiliser de la même manière. Vous allez définir une interface commune, créer trois implémentations, puis construire un service qui diffuse un message sans connaître les détails techniques de chaque canal.

L'application simulera les envois dans la console. Aucun véritable e-mail ou SMS ne sera envoyé.

## 2. Objectifs

Après cet exercice, vous saurez :

- déclarer une interface Java ;
- définir un contrat composé de méthodes ;
- implémenter une interface avec `implements` ;
- redéfinir les méthodes imposées avec `@Override` ;
- distinguer une interface d'une classe abstraite ;
- utiliser une interface comme type de variable et de collection ;
- exploiter le polymorphisme à travers une interface ;
- injecter une dépendance dans un objet ;
- concevoir un service ouvert à de nouvelles implémentations.

## 3. Résultat attendu

### Diffusion sur les trois canaux

```text
=== Centre de notifications ===

Destinataire : Nova
Message : Le tournoi commence à 20 h.

Activer les notifications par e-mail ? oui
Activer les notifications par SMS ? oui
Activer les notifications push ? oui

=== Diffusion de l'alerte ===
[E-MAIL] À Nova : Le tournoi commence à 20 h.
[SMS] À Nova : Le tournoi commence à 20 h.
[PUSH] À Nova : Le tournoi commence à 20 h.

3 notification(s) envoyée(s).
```

### Diffusion sur un seul canal

```text
=== Centre de notifications ===

Destinataire : Kira
Message : Votre équipe a remporté le match.

Activer les notifications par e-mail ? non
Activer les notifications par SMS ? non
Activer les notifications push ? oui

=== Diffusion de l'alerte ===
[PUSH] À Kira : Votre équipe a remporté le match.

1 notification(s) envoyée(s).
```

### Aucun canal activé

```text
=== Centre de notifications ===

Destinataire : Pixel
Message : Une nouvelle récompense est disponible.

Activer les notifications par e-mail ? non
Activer les notifications par SMS ? non
Activer les notifications push ? non

Aucun canal de notification n'est activé.
```

Les réponses `oui`, `OUI` et `Oui` doivent être considérées comme identiques.

## 4. Notions nécessaires

### Comprendre la notion de contrat

Une interface décrit ce qu'un type doit savoir faire, sans imposer la manière de le faire.

```java
public interface LecteurAudio {
    void lire(String titre);
    void mettreEnPause();
}
```

Toute classe qui implémente cette interface s'engage à fournir les deux méthodes. Le reste du programme peut utiliser un `LecteurAudio` sans connaître la classe concrète.

### Déclarer une interface

Une interface publique est placée dans un fichier portant le même nom :

```java
public interface Exporteur {
    String getNom();
    void exporter(String contenu);
}
```

Les méthodes abstraites d'une interface sont implicitement `public` et `abstract`. Il est donc possible d'écrire simplement leur type de retour, leur nom et leurs paramètres.

Elles ne possèdent pas de bloc d'instructions et se terminent par un point-virgule.

### Implémenter une interface

Une classe utilise `implements` pour respecter le contrat :

```java
public class ExporteurTexte implements Exporteur {
    @Override
    public String getNom() {
        return "Texte";
    }

    @Override
    public void exporter(String contenu) {
        System.out.println("Export texte : " + contenu);
    }
}
```

Les méthodes implémentées doivent être `public`. Réduire leur visibilité provoquerait une erreur de compilation.

L'annotation `@Override` permet au compilateur de vérifier que la signature correspond bien au contrat.

### Une interface ne se construit pas directement

Comme une classe abstraite, une interface ne peut pas être instanciée :

```java
Exporteur exporteur = new Exporteur(); // interdit
```

Vous devez créer une classe qui l'implémente :

```java
Exporteur exporteur = new ExporteurTexte();
```

La variable utilise le type de l'interface, tandis que l'objet réel utilise la classe concrète.

### Interface ou classe abstraite ?

Les deux mécanismes peuvent définir des méthodes communes, mais ils ne répondent pas exactement au même besoin.

| Classe abstraite | Interface |
|---|---|
| représente généralement une famille d'objets | représente une capacité ou un contrat |
| peut posséder des attributs d'instance | ne sert pas à conserver l'état métier des objets |
| possède un constructeur | ne possède pas de constructeur d'objet |
| est étendue avec `extends` | est implémentée avec `implements` |
| une classe ne peut en étendre qu'une | une classe peut implémenter plusieurs interfaces |

Dans l'exercice 8, un film et une série étaient des contenus : une classe abstraite représentait cette famille.

Dans cet exercice, e-mail, SMS et push offrent tous la capacité d'envoyer une notification : une interface décrit ce contrat.

### Implémenter plusieurs interfaces

Une classe Java ne peut étendre qu'une classe, mais elle peut implémenter plusieurs interfaces :

```java
public class FichierAudio implements Lisible, Telechargeable {
    // Implémentation des deux contrats
}
```

Les interfaces sont séparées par des virgules. Cette possibilité ne sera pas nécessaire dans la version principale de l'exercice, mais elle explique pourquoi les interfaces sont adaptées aux capacités.

### Utiliser le polymorphisme avec une interface

Plusieurs objets différents peuvent être manipulés à travers le même type :

```java
Exporteur premier = new ExporteurTexte();
Exporteur second = new ExporteurConsole();

premier.exporter("Bilan");
second.exporter("Bilan");
```

Java appelle automatiquement l'implémentation correspondant à l'objet réel.

Le code utilisateur n'a pas besoin d'écrire un `if` pour choisir le comportement après la création de l'objet.

### Créer une collection d'interfaces

Une `ArrayList` peut utiliser une interface comme type générique :

```java
ArrayList<Exporteur> exporteurs = new ArrayList<>();

exporteurs.add(new ExporteurTexte());
exporteurs.add(new ExporteurConsole());
```

La collection accepte tout objet dont la classe implémente `Exporteur`.

```java
for (Exporteur exporteur : exporteurs) {
    exporteur.exporter("Rapport terminé");
}
```

Cette boucle est polymorphe : la même instruction déclenche plusieurs comportements concrets.

### Passer une dépendance au constructeur

Un objet peut recevoir un autre objet dont il dépend :

```java
public class GenerateurRapport {
    private final Exporteur exporteur;

    public GenerateurRapport(Exporteur exporteur) {
        this.exporteur = exporteur;
    }
}
```

Cette technique s'appelle l'injection de dépendance par constructeur. La classe dépend du contrat `Exporteur`, pas d'une implémentation précise.

Dans cet exercice, le service recevra une collection déjà créée. Il ne construira pas lui-même les canaux concrets.

### Injecter une collection

Une collection peut elle aussi être transmise au constructeur :

```java
public class ServiceExport {
    private final ArrayList<Exporteur> exporteurs;

    public ServiceExport(ArrayList<Exporteur> exporteurs) {
        this.exporteurs = exporteurs;
    }
}
```

Le programme principal choisit les implémentations à ajouter, puis confie la collection au service.

Le service peut ensuite parcourir le contrat commun sans connaître les classes concrètes.

### Retourner une information après une action

Une méthode peut effectuer une action puis retourner un résultat utile :

```java
public int exporterPartout(String contenu) {
    int compteur = 0;

    for (Exporteur exporteur : exporteurs) {
        exporteur.exporter(contenu);
        compteur++;
    }

    return compteur;
}
```

Le compteur permet au code appelant de confirmer combien d'actions ont été réalisées.

### Comparer les réponses textuelles

Utilisez `equalsIgnoreCase` pour accepter plusieurs casses :

```java
if (reponse.equalsIgnoreCase("oui")) {
    // Ajouter l'implémentation choisie
}
```

N'utilisez pas `==` pour comparer le contenu de deux objets `String`.

### Ne pas tester les classes concrètes dans le service

Le service ne doit pas contenir :

```java
if (canal instanceof NotificationEmail) {
    // traitement spécial
}
```

Chaque classe concrète connaît déjà son propre format. Le service appelle simplement la méthode définie par l'interface.

Cette conception permet d'ajouter plus tard une notification Discord sans modifier la boucle de diffusion.

### Comprendre l'ouverture aux extensions

Une conception est extensible lorsqu'un nouveau comportement peut être ajouté avec peu de modifications.

Pour ajouter un nouveau canal :

1. créez une classe qui implémente l'interface ;
2. fournissez les méthodes du contrat ;
3. ajoutez un objet de cette classe à la collection.

Le service de diffusion reste inchangé. Il travaille uniquement avec `CanalNotification`.

### Répartition des responsabilités

| Type | Responsabilité |
|---|---|
| `CanalNotification` | définir le contrat commun |
| `NotificationEmail` | simuler le format e-mail |
| `NotificationSms` | simuler le format SMS |
| `NotificationPush` | simuler le format push |
| `ServiceNotification` | diffuser sur tous les canaux reçus |
| `CentreNotifications` | dialoguer avec l'utilisateur et configurer les canaux |

## 5. Consignes fonctionnelles

Le programme doit :

1. demander le nom ou identifiant du destinataire ;
2. demander le message à diffuser ;
3. demander séparément si l'e-mail, le SMS et le push sont activés ;
4. créer une collection de type `ArrayList<CanalNotification>` ;
5. ajouter uniquement les implémentations sélectionnées ;
6. créer le service en lui transmettant la collection ;
7. diffuser le message sur chaque canal avec une seule méthode du service ;
8. afficher le nombre de notifications envoyées ;
9. afficher un message particulier si aucun canal n'est activé.

Chaque implémentation doit afficher exactement son préfixe :

| Classe | Préfixe |
|---|---|
| `NotificationEmail` | `[E-MAIL]` |
| `NotificationSms` | `[SMS]` |
| `NotificationPush` | `[PUSH]` |

Le format commun est :

```text
[CANAL] À destinataire : message
```

## 6. Contraintes techniques

Créez exactement six fichiers :

```text
exercice-9/
└── src/
    ├── CanalNotification.java
    ├── NotificationEmail.java
    ├── NotificationSms.java
    ├── NotificationPush.java
    ├── ServiceNotification.java
    └── CentreNotifications.java
```

N'ajoutez aucune instruction `package`.

### Interface `CanalNotification`

Déclarez exactement la méthode :

```java
void envoyer(String destinataire, String message);
```

### Classes concrètes

Les trois classes doivent :

- utiliser `implements CanalNotification` ;
- redéfinir `envoyer` avec `@Override` ;
- déclarer la méthode `public` ;
- produire le format correspondant à leur canal ;
- ne contenir aucun attribut, constructeur personnalisé ou condition.

### Classe `ServiceNotification`

Déclarez :

```java
private final ArrayList<CanalNotification> canaux;
```

Créez le constructeur :

```java
public ServiceNotification(ArrayList<CanalNotification> canaux)
```

Créez la méthode :

```java
public int diffuser(String destinataire, String message)
```

Elle doit parcourir la collection avec une boucle `for-each`, appeler `envoyer` sur chaque canal, puis retourner le nombre d'envois réalisés.

Elle ne doit pas créer de canal concret, tester leur classe ou contenir les préfixes d'affichage.

### Classe `CentreNotifications`

- Elle contient uniquement `main`.
- Elle utilise `Scanner` pour toutes les saisies.
- Elle crée l'`ArrayList<CanalNotification>`.
- Elle compare les réponses avec `equalsIgnoreCase`.
- Elle crée et ajoute les implémentations sélectionnées.
- Elle construit `ServiceNotification` avec la collection.
- Elle ne réalise elle-même aucun envoi.
- Elle affiche le cas vide sans appeler inutilement la diffusion.
- Elle ferme le scanner après la dernière saisie.

N'utilisez pas de classe abstraite, d'héritage entre classes, de `instanceof`, de cast, de `switch`, de tableau ou de classe supplémentaire.

Depuis `exercice-9`, compilez les six fichiers :

```bash
javac -d out src/CanalNotification.java src/NotificationEmail.java src/NotificationSms.java src/NotificationPush.java src/ServiceNotification.java src/CentreNotifications.java
```

Puis exécutez :

```bash
java -cp out CentreNotifications
```

Sous Windows :

```powershell
javac -d out src\CanalNotification.java src\NotificationEmail.java src\NotificationSms.java src\NotificationPush.java src\ServiceNotification.java src\CentreNotifications.java
java -cp out CentreNotifications
```

## 7. Étapes de réalisation

### Étape 1 — Définir le contrat

Créez l'interface et déclarez la méthode `envoyer` sans bloc d'instructions.

Vérification : il doit être impossible de créer directement un `CanalNotification`.

### Étape 2 — Créer les implémentations

Créez les trois classes, ajoutez `implements`, puis fournissez leur version de `envoyer`.

Vérification : chaque classe utilise son propre préfixe et respecte le même format général.

### Étape 3 — Tester le polymorphisme

Créez temporairement une variable de type `CanalNotification` et affectez-lui successivement différentes implémentations.

Vérification : le même appel à `envoyer` doit produire le format de l'objet réel.

### Étape 4 — Créer le service

Déclarez la collection comme attribut privé final et recevez-la dans le constructeur.

Vérification : le service dépend uniquement de l'interface.

### Étape 5 — Diffuser le message

Parcourez les canaux, appelez la méthode du contrat et comptez les envois.

Vérification : aucun nom de classe concrète ne doit apparaître dans `ServiceNotification`.

### Étape 6 — Construire la configuration

Dans `main`, demandez les informations générales et les trois réponses. Ajoutez les objets correspondants à la collection.

Vérification : avec trois réponses `non`, la collection doit rester vide.

### Étape 7 — Relier le service

Construisez le service avec la collection, gérez le cas vide ou diffusez l'alerte, puis affichez le compteur retourné.

### Étape 8 — Tester toutes les combinaisons

Testez aucun canal, un seul canal, deux canaux et les trois canaux. Vérifiez aussi différentes casses pour `oui`.

## 8. Jeux d'essai

| E-mail | SMS | Push | Nombre d'envois | Ordre attendu |
|---|---|---|---:|---|
| non | non | non | 0 | aucun affichage d'envoi |
| oui | non | non | 1 | E-MAIL |
| non | oui | oui | 2 | SMS puis PUSH |
| OUI | Oui | oui | 3 | E-MAIL, SMS, PUSH |

Avec le destinataire `Nova` et le message `Match à 20 h`, une notification e-mail doit afficher :

```text
[E-MAIL] À Nova : Match à 20 h
```

Vérifiez qu'un message contenant des espaces est lu entièrement grâce à `nextLine()`.

## 9. Critères de validation

- [ ] Les six fichiers portent les noms imposés.
- [ ] `CanalNotification` est une interface.
- [ ] Elle déclare exactement la méthode demandée.
- [ ] Les trois classes utilisent `implements`.
- [ ] Chaque implémentation utilise `@Override` et une méthode publique.
- [ ] Chaque canal produit son propre préfixe.
- [ ] La collection est une `ArrayList<CanalNotification>`.
- [ ] Elle peut contenir les trois types d'objets.
- [ ] Le service reçoit la collection dans son constructeur.
- [ ] Son attribut est privé et final.
- [ ] La diffusion utilise une boucle `for-each` polymorphe.
- [ ] Le service ne connaît aucune classe concrète.
- [ ] La méthode retourne le nombre exact d'envois.
- [ ] Seuls les canaux activés sont ajoutés.
- [ ] Les réponses sont comparées avec `equalsIgnoreCase`.
- [ ] Le cas sans canal est traité clairement.
- [ ] Aucun `instanceof`, cast ou test de classe n'est utilisé.
- [ ] Tous les fichiers compilent avec la commande fournie.
- [ ] Tous les jeux d'essai donnent les résultats attendus.

## 10. Erreurs fréquentes

### `interface abstract methods cannot have body`

La méthode du contrat ne doit pas posséder de bloc dans cet exercice. Terminez sa déclaration par un point-virgule.

### Une classe ne compile pas après `implements`

Elle doit fournir toutes les méthodes de l'interface avec une signature identique et une visibilité `public`.

### `attempting to assign weaker access privileges`

La méthode implémentée n'est probablement pas publique. Ajoutez `public` devant sa déclaration.

### La collection refuse une notification

Vérifiez que la classe concrète implémente bien `CanalNotification` et que la collection utilise ce type générique.

### Les réponses `OUI` ne fonctionnent pas

Utilisez `equalsIgnoreCase` plutôt que `equals` ou `==`.

### Le service contient plusieurs conditions selon le canal

Ce choix appartient aux implémentations. Le service doit uniquement appeler `canal.envoyer(...)`.

### Le compteur est toujours égal à zéro

Incrémentez-le à chaque appel réalisé et retournez-le après la boucle, pas à l'intérieur.

### Tous les canaux sont activés malgré les réponses

Chaque création et chaque ajout doivent être placés dans la condition correspondant à la réponse de l'utilisateur.

## 11. Défis supplémentaires

### Défi 1 — Ajouter Discord

Créez `NotificationDiscord` avec le préfixe `[DISCORD]`. Le service ne doit subir aucune modification.

### Défi 2 — Ajouter une priorité

Créez une seconde interface `NotificationPrioritaire` avec une méthode dédiée. Faites-la implémenter par le SMS et le push.

### Défi 3 — Refuser les messages vides

Avant de construire le service, vérifiez le message avec `isBlank()`. Un message vide ou composé uniquement d'espaces ne doit pas être diffusé.

## 12. Ce qu'il faut retenir

Une interface définit un contrat commun. Les classes qui l'implémentent s'engagent à fournir les méthodes demandées, tout en restant libres de choisir leur comportement interne.

Utiliser l'interface comme type de variable, de paramètre ou de collection permet d'obtenir du polymorphisme. Le service dépend ainsi d'une abstraction et peut utiliser de nouvelles implémentations sans modifier son algorithme de diffusion.
