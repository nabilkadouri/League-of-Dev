package rpg.personnages;

public class Dragon extends Ennemi{
    public Dragon() {
        super("Dragon", 200, 50, 30);
    }

    @Override
    public void attaqueEnnemie(Personnage cible) {
        System.out.println("Le " + this.getNom() + " courroucé déclenche son attaque !");
        super.attaqueEnnemie(cible);
    }
}
