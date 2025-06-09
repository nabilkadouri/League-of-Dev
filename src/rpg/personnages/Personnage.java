package rpg.personnages;

public abstract class  Personnage {
    protected String nom;
    protected int pv;
    protected int pvMax;
    protected int attaque;
    protected int defense;

    public Personnage(String nom, int pv, int attaque, int defense) {
        this.nom = nom;
        this.pv = pv;
        this.pvMax = pv;
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

    public int getPvMax() {
        return pvMax;
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
     * Cette méthode est conçue pour être surchargée par les sous-classes
     * afin de définir leur propre logique de dégâts aléatoires.
     * Par défaut, elle retourne l'attribut d'attaque fixe du personnage.
     * @return Les dégâts "bruts" avant calcul de la défense.
     */
    protected int getDegatsInfliges() {
        return this.attaque;
    }

    /**
     * Méthode qui permet d'effectuer une attaque de la part d'un personnage
     * @param cible représente un hero ou un ennemi
     * Affiche les noms des personnages attaquants et les dégâts subit par cette attaque
     */
    public void attaquer(Personnage cible) {
        int degatsBruts = this.getDegatsInfliges();
        int degatInflige = degatsBruts - cible.getDefense();
        if (degatInflige < 5) {
            degatInflige = 5;
        }
        cible.prendreDegat(degatInflige);
        System.out.println(this.nom + " attaque " + cible.getNom() + ", et inflige " + degatInflige + " de dégâts !");
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
        return "Nom=" + nom + ", Pv=" + pv + "/" + pvMax + ", Défense=" + defense + " | ";
    }
}
