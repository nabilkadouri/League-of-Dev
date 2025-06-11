package rpg.personnages;

import java.util.Random;

public class Archer extends Hero {
    private int fleches;
    public final int FLECHES_MAX = 50;


    public Archer(String nom) {
        super(nom, 120, 20, 8);
        this.fleches = FLECHES_MAX;
    }


    public int getFleches() {
        return fleches;
    }

    public void setFleches(int fleches) {
        this.fleches = fleches;
        if(this.fleches > FLECHES_MAX) {
            this.fleches = FLECHES_MAX;
        }

        if (this.fleches < 0) {
            this.fleches = 0;
        }
    }

    public void restaureFleche() {
        if(this.fleches == FLECHES_MAX) {
            System.out.println(this.getNom() + " a déja toutes ses flèches !");
        } else {
            int flechesRestaure = 10;
            this.setFleches(this.fleches + flechesRestaure);
            System.out.println("Bonus de victoire : " + flechesRestaure +" flèches restauré !Flèches actuelles : " + this.fleches + "/" + FLECHES_MAX  );
        }
    }

    @Override
    public void utiliserPouvoir(Personnage cible) {
        int coutFleches = 10;
        if(this.fleches >= coutFleches){
            int degatsRapides = (this.attaque * 3) - cible.getDefense();
            if(degatsRapides < 15 ) {
                degatsRapides = 15;
            }
            cible.prendreDegat(degatsRapides);
            this.setFleches(this.fleches - coutFleches);
            System.out.println(this.getNom() + " décoche un Tir Rapide sur " + cible.getNom() + " infligeant " + degatsRapides + " de dégâts ! Flèches restantes: " + this.fleches);
        }else {
            System.out.println(this.getNom() + " n'a pas assez de flèches pour un Tir Rapide !");
        }
    }

    @Override
    protected int getDegatsInfliges() {
        Random random = new Random();
        if (this.fleches > 0) {
            this.setFleches(this.fleches - 1);
            return this.attaque + random.nextInt(8) + 1;
        } else {
            System.out.println(this.getNom() + " n'a plus de flèches et utilise son couteau !");
            return random.nextInt(5) + 2;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Flèches=" + fleches + "/" + FLECHES_MAX;
    }
}
