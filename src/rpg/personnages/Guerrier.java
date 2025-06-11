package rpg.personnages;

import java.util.Random;

public class Guerrier extends Hero {
    private int rage;
    public final int RAGE_MAX = 100;

    public Guerrier(String nom) {
        super(nom, 200, 30, 15);
        this.rage = 0;
    }


    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
        if(this.rage > RAGE_MAX) {
            this.rage = RAGE_MAX;
        }
        if(this.rage < 0) {
            this.rage = 0;
        }
    }

    public void restaureRage() {
        if(this.rage == RAGE_MAX) {
            System.out.println(this.getNom() + " a déja sa rage au maximum !");
        } else {
            int rageRestaure = 25;
            this.setRage(this.rage + rageRestaure);
            System.out.println("Bonus de victoire : " + rageRestaure +" de rage restauré !");
        }
    }

    @Override
    public void utiliserPouvoir(Personnage cible) {
        int coutRage = 30;
        if (this.rage >= coutRage) {
            int degatsHeroiques = (this.attaque * 2) - cible.getDefense();
            if (degatsHeroiques < 25) {
                degatsHeroiques = 25;
            }
            cible.prendreDegat(degatsHeroiques);
            this.setRage(this.rage - coutRage);
            System.out.println(this.getNom() + " exécute une Frappe Héroïque sur " + cible.getNom() + " infligeant " + degatsHeroiques + " dégâts massifs ! Rage restante: " + this.rage);
        } else {
            System.out.println(this.getNom() + " n'a pas assez de rage pour une Frappe Héroïque !");
        }
    }

    @Override
    protected int getDegatsInfliges() {
        Random random = new Random();
        int degats = this.attaque + random.nextInt(10) + 5;
        this.setRage(this.rage + 5);
        return degats;
    }

    @Override
    public String toString() {
        return super.toString() + ", Rage=" + rage + "/" + RAGE_MAX;
    }
}
