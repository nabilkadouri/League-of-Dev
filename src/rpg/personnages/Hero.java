package rpg.personnages;

import rpg.interfaces.PouvoirSpecial;

public class Hero extends Personnage implements PouvoirSpecial {
    private int mana;
    private int potion = 30;

    public Hero(String nom) {
        super(nom, 110, 20, 10);
        this.mana = 30;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void utiliserPouvoir(Personnage cible) {
        int coutMana = 10;
        if(this.mana >= coutMana) {
            int degatsSpeciaux = this.attaque * 2;
            cible.prendreDegat(degatsSpeciaux);
            this.mana -= coutMana;
            System.out.println("Attaque spéciale déclenchée sur " + cible.getNom() + "qui subit " + degatsSpeciaux + "de dégâts.");
        }else {
            System.out.println(this.nom +" n'a pas assez de mana pour l'attaque spéciale !");
        }
    }

    public void utiliserPotion() {
        if(potion > 0) {
            pv += potion;
        }else{
            System.out.println("Plus de potion dans ta besace");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "mana=" + mana;
    }
}
