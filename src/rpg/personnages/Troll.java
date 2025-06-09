package rpg.personnages;

import java.util.Random;

public class Troll extends Ennemi{

    public Troll() {
        super("Troll", 120, 0, 10);
    }

    /**
     * Surcharge de la méthode pour générer la valeur d'attaque du Troll.
     * L'attaque sera 10, 15, 20 ou 25.
     * @return La valeur d'attaque aléatoire (10, 15, 20 ou 25).
     */
    @Override
    protected int getDegatsInfliges() {
        Random random = new Random();
        int choixMultiplicateur = random.nextInt(4);

        return (choixMultiplicateur * 5) + 10;
    }

    @Override
    public void attaqueEnnemie(Personnage cible) {
        System.out.println("Le " + this.getNom() + " furieux, déclenche son attaque !");
        super.attaqueEnnemie(cible);
    }
}
