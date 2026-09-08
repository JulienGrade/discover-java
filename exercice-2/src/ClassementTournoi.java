import java.util.Scanner;

public class ClassementTournoi {
    public static void main(String[] args) {
        final int SCORE_MINIMUM = 0;
        final int SCORE_MAXIMUM = 1_000;
        final int SEUIL_ARGENT = 200;
        final int SEUIL_OR = 400;
        final int SEUIL_PLATINE = 700;
        final int SEUIL_CHAMPION = 900;

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Classement du tournoi ===");
        System.out.println();

        System.out.print("Quel est votre pseudo ? ");
        String pseudo = scanner.nextLine();

        System.out.print("Quel est votre score ? ");
        int score = scanner.nextInt();

        scanner.close();

        System.out.println();

        if (score < SCORE_MINIMUM || score > SCORE_MAXIMUM) {
            System.out.println("Erreur : le score doit être compris entre 0 et 1000.");
        } else {
            System.out.println("=== Résultat ===");
            System.out.println("Joueur : " + pseudo);
            System.out.println("Score : " + score + " points");

            if (score >= SEUIL_CHAMPION) {
                System.out.println("Ligue : Champion");
                System.out.println("Vous avez atteint la ligue la plus élevée.");
            } else if (score >= SEUIL_PLATINE) {
                int pointsManquants = SEUIL_CHAMPION - score;
                System.out.println("Ligue : Platine");
                System.out.println(
                        "Il vous manque " + pointsManquants
                                + " points pour atteindre la ligue Champion."
                );
            } else if (score >= SEUIL_OR) {
                int pointsManquants = SEUIL_PLATINE - score;
                System.out.println("Ligue : Or");
                System.out.println(
                        "Il vous manque " + pointsManquants
                                + " points pour atteindre la ligue Platine."
                );
            } else if (score >= SEUIL_ARGENT) {
                int pointsManquants = SEUIL_OR - score;
                System.out.println("Ligue : Argent");
                System.out.println(
                        "Il vous manque " + pointsManquants
                                + " points pour atteindre la ligue Or."
                );
            } else {
                int pointsManquants = SEUIL_ARGENT - score;
                System.out.println("Ligue : Bronze");
                System.out.println(
                        "Il vous manque " + pointsManquants
                                + " points pour atteindre la ligue Argent."
                );
            }
        }
    }
}
