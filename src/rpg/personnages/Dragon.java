package rpg.personnages;

import java.util.Random;

public class Dragon extends Ennemi{
    public Dragon() {
        super("Dragon", 130, 0, 15);
    }

    /**
     * Surcharge de la méthode pour générer la valeur d'attaque du Dragon.
     * L'attaque sera 25, 30, 35, 40, 45, 50.
     * @return La valeur d'attaque aléatoire (25, 30, 35, 40, 45, 50).
     */
    @Override
    protected int getDegatsInfliges() {
        Random random = new Random();
        int choixMultiplicateur = random.nextInt(6);

        return (choixMultiplicateur * 5) + 25;
    }

    @Override
    public void attaqueEnnemie(Personnage cible) {
        System.out.println("Le " + this.getNom() + " courroucé déclenche son attaque !");
        super.attaqueEnnemie(cible);
    }
}
