package rpg.personnages;

import rpg.interfaces.PouvoirSpecial;

import java.util.Random;

public class Hero extends Personnage implements PouvoirSpecial {
    protected int nombreDePotions;
    protected int quantitePvPotion;
    protected int compteurVictoire;

    public Hero(String nom, int pv, int attaque, int defense) {
        super(nom,pv, attaque, defense);
        this.nombreDePotions = 1;
        this.quantitePvPotion = 75;
        this.compteurVictoire = 0;
    }

    // --- Getters et Setters ---
    public int getNombreDePotions() {
        return nombreDePotions;
    }

    public int getQuantitePvPotion() {
        return quantitePvPotion;
    }


    public int getCompteurVictoire() {
        return compteurVictoire;
    }

    // --- Méthodes utilisées pour tous les héros ---

    /**
     * Méthode pour utiliser une potion de soin.
     * Logique commune à tous les héros.
     */
    public void utiliserPotion() {
        if (nombreDePotions > 0) {
            if (this.pv >= 1 && this.pv <= 120) {
                int pvAvantPotion = this.pv;
                this.pv = Math.min(this.pv + quantitePvPotion, this.pvMax);
                nombreDePotions--;
                System.out.println("Vous avez utilisé votre potion ! Vous avez restauré " + (this.pv - pvAvantPotion) + " PV.");
            } else {
                System.out.println("Vous ne pouvez pas utiliser la potion maintenant. Vos PV sont trop élevés " + "("+ this.pv + "PV.)");
            }
        } else {
            System.out.println("Plus de potion dans ta besace !");
        }
    }

    /**
     * Incrémente le compteur de victoires du héros.
     * Logique commune à tous les héros.
     */
    public int incrementerCompteurVictoire(){
        return compteurVictoire++;
    }

    /**
     * Cette méthode définit l'attaque de base d'un héros.
     * Elle est surchargée ici pour donner une logique générique à tous les héros
     * avant d'être potentiellement surchargée à nouveau dans les sous-classes.
     * @return Les dégâts "bruts" infligés par l'attaque de base du héros.
     */
    @Override
    protected int getDegatsInfliges() {
        Random random = new Random();
        return this.attaque + random.nextInt(5) - 2;
    }

    @Override
    public void utiliserPouvoir(Personnage cible) {
        int degatsBruts = this.getDegatsInfliges() +10;
        int degatsInfliges = degatsBruts - cible.getDefense();
        if(degatsInfliges <10){
            degatsInfliges = 10;
        }
        cible.prendreDegat(degatsInfliges);
        System.out.println(this.nom + "utilise attaque spéciale sur " + cible.getNom() + " et inflige " + degatsInfliges + " de dégâts !");
    }

    @Override
    public String toString() {
        return super.toString() +  "Potions=" + nombreDePotions + ", Victoires=" + compteurVictoire;
    }
}
