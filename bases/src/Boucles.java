public class Boucles {

    public static void main(String[] args) {
        System.out.println("Début de la partie");

        for (int manche = 1; manche <= 3; manche++) {
            System.out.println("Manche " + manche);
        }

        int energie = 3;

        while (energie > 0) {
            System.out.println("Action effectuée, énergie restante : " + energie);
            energie--;
        }

        System.out.println("Partie terminée");
    }
}
