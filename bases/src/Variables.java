public class Variables {

    public static void main(String[] args) {
        String pseudo = "Nova";
        int niveau = 12;
        int score = 750;
        double tempsDeJeu = 4.5;
        boolean connecte = true;
        char rang = 'A';

        final int SCORE_MAXIMUM = 1_000;

        score = score + 100;

        System.out.println("Pseudo : " + pseudo);
        System.out.println("Niveau : " + niveau);
        System.out.println("Score : " + score + " / " + SCORE_MAXIMUM);
        System.out.println("Temps de jeu : " + tempsDeJeu + " heures");
        System.out.println("Connecté : " + connecte);
        System.out.println("Rang : " + rang);
    }
}
