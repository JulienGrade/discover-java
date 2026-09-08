class Joueur {
    String pseudo;
    int score;

    Joueur(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
    }

    void afficherProfil() {
        System.out.println(pseudo + " possède " + score + " points.");
    }
}

public class PremiereClasse {

    public static void main(String[] args) {
        Joueur premierJoueur = new Joueur("Nova", 750);
        Joueur deuxiemeJoueur = new Joueur("Pixel", 500);

        premierJoueur.afficherProfil();
        deuxiemeJoueur.afficherProfil();
    }
}
