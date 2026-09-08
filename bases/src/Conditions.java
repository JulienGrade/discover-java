public class Conditions {

    public static void main(String[] args) {
        int score = 750;
        int niveau = 12;
        boolean connecte = true;

        if (score >= 1_000) {
            System.out.println("Rang Or");
        } else if (score >= 500) {
            System.out.println("Rang Argent");
        } else {
            System.out.println("Rang Bronze");
        }

        if (niveau >= 10 && connecte) {
            System.out.println("Accès au tournoi autorisé");
        } else {
            System.out.println("Accès au tournoi refusé");
        }
    }
}
