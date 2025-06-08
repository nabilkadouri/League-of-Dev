package rpg.personnages;

public abstract class  Personnage {
    protected String nom;
    protected int pv;
    protected int attaque;
    protected int defense;

    public Personnage(String nom, int pv, int attaque, int defense) {
        this.nom = nom;
        this.pv = pv;
        this.attaque = attaque;
        this.defense = defense;
    }

    //Guetters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    //Méthodes

    /**
     * Méthode qui permet d'effectuer une attaque de la part d'un personnage
     * @param cible représente un hero ou un ennemi
     * Affiche les noms des personnages attaquant et recevant ainsi que les dégâts subit par l'attaque
     */
    public void attaquer(Personnage cible) {
        int degatInflige = this.attaque - this.defense;
        cible.prendreDegat(degatInflige);
        System.out.println(this.nom + " attaque " + cible.getNom() + ", dégâts infligé " + degatInflige);
    }

    /**
     * Méthode qui permet de prendre en compte les dégât subit lors d'une attaque
     * @param degats représente la puissance des dégâts
     * Vérifie que si les points de vie sont inférieurs à zéro, le compteur est remis à zéro
     */
    public void prendreDegat(int degats){
        this.pv -= degats;
        if (this.pv < 0) {
            this.pv = 0;
        }
    }

    /**
     * Boolean qui vérifie si le nombre de point de vie est supe
     * @return true
     */
    public Boolean estVivant() {
        if(pv <= 0) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Personnage{" +
                "nom='" + nom + '\'' +
                ", pv=" + pv +
                ", attaque=" + attaque +
                ", defense=" + defense +
                '}';
    }
}
