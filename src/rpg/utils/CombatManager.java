package rpg.utils;

import rpg.personnages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CombatManager {
    private List<Personnage> ennemis;
    private int compteurCombat = 0;


    public CombatManager() {
        this.ennemis = new ArrayList<>();
        ennemis.add(new Gobelin());
        ennemis.add(new Troll());
        ennemis.add(new Dragon());
    }

    public Ennemi ennemisAleatoire() {
        compteurCombat++;
        Random r = new Random();
        int indexAleatoire = r.nextInt(ennemis.size());

        Personnage ennemiModele = ennemis.get(indexAleatoire);

        Personnage ennemiAleatoire;
        if (ennemiModele instanceof Gobelin) {
            ennemiAleatoire = new Gobelin();
        } else if (ennemiModele instanceof Troll) {
            ennemiAleatoire = new Troll();
        } else if (ennemiModele instanceof Dragon) {
            ennemiAleatoire = new Dragon();
        } else {
            return null;
        }

        System.out.println("Ennemi n° " + compteurCombat + " : " + ennemiAleatoire.getNom() + " sauvage apparaît !");
        return (Ennemi) ennemiAleatoire;
    }

    public Hero initialiserHero (Scanner scanner){
        System.out.println(" Lancement du jeu ...");
        System.out.print("Entrez le nom de votre héros : ");
        String nom = scanner.nextLine().trim();
        while (nom.isEmpty()) {
            System.out.println("Le nom de votre héro ne peut pas être vide. Veuillez réessayez.");
            System.out.print("Entrez le nom de votre héros : ");
            nom = scanner.nextLine().trim();
        }
        Hero hero = new Hero(nom);
        System.out.println("Nouveau héro enregistré :" + hero.getNom());
        return hero;
    }

    public void AfficherStatutHeroEtEnnemi(Hero hero, Ennemi ennemi) {
        System.out.println("\n" + hero.getNom() + " - " + "Pv: " + hero.getPv() + " | " + "Mana: " + hero.getMana() + " | " + "Potion: " + hero.getNombreDePotions());
        System.out.println(ennemi.getNom() + " - " + "Pv: " + ennemi.getPv());
    }

    public int lireChoixAction(Scanner scanner) throws InvalideActionChoisieException {
        System.out.println("\n Que voulez-vous faire ?");
        System.out.println("1. Attaquer");
        System.out.println("2. Utiliser pouvoir");
        System.out.println("3. Utiliser potion");

        int choix = -1;
        boolean saisieValide = false;

        while (!saisieValide) {
            System.out.print("Choix : ");
            String input = scanner.nextLine();
            try {
                choix = Integer.parseInt(input);
                if (choix >= 1 && choix <= 3) {
                    saisieValide = true;
                }
            } catch (NumberFormatException e) {
                // Lance notre exception personnalisée si la saisie n'est pas un nombre
                throw new InvalideActionChoisieException("La saisie '" + input + "' n'est pas un nombre entier valide.", e);
            }
        }
        return choix;
    }

    private void choisirActionHero(Hero hero, Ennemi ennemi, int choix) {
        switch (choix) {
            case 1:
                hero.attaquer(ennemi);
                break;
            case 2:
                hero.utiliserPouvoir(ennemi);
                break;
            case 3:
                hero.utiliserPotion();
                break;
        }
    }

    private boolean gererFinDeCombat(Hero hero, Ennemi ennemi) {
        if (!ennemi.estVivant()) {
            System.out.println("\n" + ennemi.getNom() + " a été vaincu ! Bravo " + hero.getNom() + " !");
            hero.restaureMana();
            hero.compteurVictoire();
            if (hero.getCompteurVictoire() >= 5) {
                System.out.println("\n--- VICTOIRE FINALE ! ---");
                System.out.println(hero.getNom() + " a vaincu " + hero.getCompteurVictoire() + " ennemis ! Vous avez accompli votre quête !");
                return true;
            }
        } else if (!hero.estVivant()) {
            System.out.println("\n" + hero.getNom() + " a été vaincu par " + ennemi.getNom() + " !");
            return true;
        }
        return false;
    }

    public void demarrerPartie() {
        Hero monHero = null;
        try (Scanner scanner = new Scanner(System.in)) {
            monHero = initialiserHero(scanner);

            while (monHero.estVivant() && monHero.getCompteurVictoire() < 5) {
                System.out.println("\n--- NOUVEAU COMBAT ---");
                Ennemi ennemiActuel = ennemisAleatoire();
                System.out.println("Vous affrontez : " + ennemiActuel.getNom() + " (" + ennemiActuel.getPv() + " PV)");

                while (monHero.estVivant() && ennemiActuel.estVivant()) {
                    AfficherStatutHeroEtEnnemi(monHero, ennemiActuel);
                    int choix = lireChoixAction(scanner);

                    choisirActionHero(monHero,ennemiActuel,choix);

                    if (!ennemiActuel.estVivant()) {
                        if (gererFinDeCombat(monHero, ennemiActuel)) {
                            break;
                        }
                    } else {
                        ennemiActuel.attaqueEnnemie(monHero);
                        if (!monHero.estVivant()) {
                            gererFinDeCombat(monHero, ennemiActuel);
                            break;
                        }
                    }
                }
                if (!monHero.estVivant() || monHero.getCompteurVictoire() >= 5) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e);
        } finally {
            if (monHero != null) {
                if (monHero.getCompteurVictoire() >= 5) {
                    System.out.println("\n Félicitations, " + monHero.getNom() + " ! Le monde est sauvé !");
                } else if (!monHero.estVivant()) {
                    System.out.println("\n Votre quête se termine ici, " + monHero.getNom() + ". Mieux vaut se préparer la prochaine fois !");
                }
            } else {
                System.out.println("\nLe jeu s'est terminé de manière inattendue avant la création du héros.");
            }
            System.out.println("\nFin du programme. Merci !");
        }
    }
}

