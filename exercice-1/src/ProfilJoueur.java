import java.util.Scanner;

public class ProfilJoueur {
    public static void main(String[] args) {
        final int SEMAINES_DANS_UNE_ANNEE = 52;

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Création de votre profil ===");
        System.out.println();

        System.out.print("Quel est votre pseudo ? ");
        String pseudo = scanner.nextLine();

        System.out.print("Quel âge avez-vous ? ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Quel est votre jeu préféré ? ");
        String jeuPrefere = scanner.nextLine();

        System.out.print("Combien d'heures jouez-vous par semaine ? ");
        int tempsJeuHebdomadaire = scanner.nextInt();

        int tempsJeuAnnuel = tempsJeuHebdomadaire * SEMAINES_DANS_UNE_ANNEE;

        System.out.println();
        System.out.println("=== Profil du joueur ===");
        System.out.println("Pseudo : " + pseudo);
        System.out.println("Âge : " + age + " ans");
        System.out.println("Jeu préféré : " + jeuPrefere);
        System.out.println("Temps de jeu hebdomadaire : " + tempsJeuHebdomadaire + " heures");
        System.out.println("Temps de jeu annuel estimé : " + tempsJeuAnnuel + " heures");
        System.out.println();
        System.out.println("Profil créé avec succès.");

        scanner.close();
    }
}
