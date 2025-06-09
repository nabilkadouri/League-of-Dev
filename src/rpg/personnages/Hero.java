package rpg.personnages;

import rpg.interfaces.PouvoirSpecial;

import java.util.Random;

public class Hero extends Personnage implements PouvoirSpecial {
    private int mana;
    private int nombreDePotions = 1;
    private int quantitePvPotion = 75;
    private int compteurVictoire = 0;

    public Hero(String nom) {
        super(nom, 150, 0, 10);
        this.mana = 30;
        this.nombreDePotions = 1;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getNombreDePotions() {
        return nombreDePotions;
    }

    public int getQuantitePvPotion() {
        return quantitePvPotion;
    }

    public int getCompteurVictoire() {
        return compteurVictoire;
    }

    @Override
    public void utiliserPouvoir(Personnage cible) {
        int coutMana = 10;
        if(this.mana >= coutMana) {
            int degatsSpeciaux = (getDegatsInfliges() * 2) - cible.getDefense();
            cible.prendreDegat(degatsSpeciaux);
            this.mana -= coutMana;
            System.out.println("Attaque spéciale déclenchée sur " + cible.getNom() + " qui subit " + degatsSpeciaux + " de dégâts.");
        }else {
            System.out.println(this.nom +" n'a pas assez de mana pour l'attaque spéciale !");
        }
    }

    public void utiliserPotion() {
        // Condition 1: Le héros a-t-il une potion ?
        if (nombreDePotions > 0) {
            // Condition 2: Les PV actuels sont-ils entre 5 et 170 (inclus) ?
            // On utilise également getPVMax() pour gérer le cas où la restauration dépasserait le max.
            if (this.pv >= 5 && this.pv <= 170) {
                // Restaure les PV, mais ne dépasse pas les PV max
                int pvAvantPotion = this.pv;
                this.pv = Math.min(this.pv + quantitePvPotion, this.pvMax);
                nombreDePotions--; // Consomme la potion
                System.out.println("Vous avez utilisé votre potion ! Vous avez restauré " + (this.pv - pvAvantPotion) + " PV.");
            } else {
                System.out.println("Vous ne pouvez pas utiliser la potion maintenant. Vos PV sont trop bas (<5) ou trop élevés (>=175).");
            }
        } else {
            System.out.println("Plus de potion dans ta besace !");
        }
    }

    public void restaureMana(){
        if(mana == 30) {
            System.out.println("Réserve de mana pleine, bonus de victoire perdu !");
        }else{
            this.setMana(this.mana + 10);
            System.out.println("Bonus de victoire : 10 de mana restauré.");
        }
    }

    public int compteurVictoire(){
        return compteurVictoire++;
    }

    /**
     * Surcharge de la methode pour générer la valeur d'attaque du Héros.
     * L'attaque sera 15,20,25,30 ou 35.
     * @return La valeur d'attaque aléatoire (15,20,25,30 ou 35).
     */
    @Override
    protected int getDegatsInfliges() {
        Random random = new Random();
        int choixMultiplicateur = random.nextInt(6);
        return (choixMultiplicateur * 5) + 15;
    }

    @Override
    public String toString() {
        return super.toString() + "mana=" + mana;
    }
}
