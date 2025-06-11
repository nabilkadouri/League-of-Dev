package rpg.personnages;

import java.util.Random;

public class Mage extends Hero {
    private int mana;
    public final int MANA_MAX = 60;

    public Mage(String nom) {
        super(nom, 100, 15, 5);
        this.mana = MANA_MAX;
    }


    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
        if(this.mana > MANA_MAX) {
            this.mana = MANA_MAX;
        }

        if(this.mana < 0){
            this.mana = 0;
        }
    }

    public void restaureMana(){
        if(this.mana == MANA_MAX) {
            System.out.println(this.getNom() + " a déja sa réserve de mana pleine !");
        }else{
            int manaRestaure = 15;
            this.setMana(this.mana + manaRestaure);
            System.out.println("Bonus de victoire : " + manaRestaure +" de mana restauré !");
        }
    }

    @Override
    public void utiliserPouvoir(Personnage cible) {
        int coutMana = 15;
        if(this.mana >= coutMana){
            int degatsMagiques = (this.attaque * 4 ) +(this.mana / 5) - cible.getDefense();
            if(degatsMagiques <15){
                degatsMagiques = 15;
            }
            cible.prendreDegat(degatsMagiques);
            this.setMana(this.mana - coutMana);
            System.out.println(this.getNom() + " lance une boule de feu sur " + cible.getNom() + " infligeant " + degatsMagiques + " de dégâts magique ! Mana restant: " + this.mana);
        } else {
            System.out.println(this.getNom() + " n'a pas assez de mana pour lancer une attaque puissante!");
        }
    }

    @Override
    protected int getDegatsInfliges() {
        Random random = new Random();
        return random.nextInt(8) + 5;
    }

    @Override
    public String toString() {
        return super.toString() + ", Mana=" + mana + "/" + MANA_MAX;
    }
}
