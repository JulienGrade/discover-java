public class Methodes {

    public static void main(String[] args) {
        afficherBienvenue("Nova");

        int scoreFinal = calculerScore(500, 250);
        System.out.println("Score final : " + scoreFinal);
    }

    public static void afficherBienvenue(String pseudo) {
        System.out.println("Bienvenue " + pseudo);
    }

    public static int calculerScore(int points, int bonus) {
        return points + bonus;
    }
}
