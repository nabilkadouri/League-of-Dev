package rpg.personnages;

public class Troll extends Ennemi{

    public Troll() {
        super("Troll", 150, 25, 15);
    }

    @Override
    public void attaqueEnnemie(Personnage cible) {
        System.out.println("Le " + this.getNom() + " furieux, déclenche son attaque !");
        super.attaqueEnnemie(cible);
    }
}
