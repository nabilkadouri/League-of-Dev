package rpg.personnages;

public class Ennemi extends Personnage{


    public Ennemi(String nom, int pv, int attaque, int defense) {
        super(nom, pv, attaque, defense);
    }

    public void attaqueEnnemie(Personnage cible) {
       this.attaquer(cible);
    }
}
