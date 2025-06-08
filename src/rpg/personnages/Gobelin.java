package rpg.personnages;

public class Gobelin extends Ennemi {
    public Gobelin() {
        super("Gobelin", 50 , 10, 5);
    }

    @Override
    public void attaqueEnnemie(Personnage cible) {
        System.out.println("Le " + this.getNom() + "irrité, déclenche son attaque !");
        super.attaqueEnnemie(cible);
    }
}
