package rpg.personnages;

import java.util.Random;

public class Gobelin extends Ennemi {
    public Gobelin() {
        super("Gobelin", 50 , 0, 5);
    }

    /**
     * Surcharge de la méthode pour générer la valeur d'attaque du Gobelin.
     * L'attaque sera 5, 10 ou 15.
     * @return La valeur d'attaque aléatoire (5, 10 ou 15).
     */
    @Override
    protected int getDegatsInfliges() {
        Random random = new Random();
        int choixMultiplicateur = random.nextInt(3);

        return (choixMultiplicateur * 5) + 5;
    }

    @Override
    public void attaqueEnnemie(Personnage cible) {
        System.out.println("Le " + this.getNom() + " irrité, déclenche son attaque !");
        super.attaqueEnnemie(cible);
    }
}
