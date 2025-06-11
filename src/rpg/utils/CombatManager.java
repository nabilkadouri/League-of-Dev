package rpg.utils;

import rpg.personnages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CombatManager {
    // --- Icônes
    private static final String ICON_HERO = "✨";
    private static final String ICON_ENEMY = "👹";
    private static final String ICON_HP = "❤️";
    private static final String ICON_MANA = "💧";
    private static final String ICON_RAGE = "🔥";
    private static final String ICON_ARROW = "🏹";
    private static final String ICON_POTION = "🧪";
    private static final String ICON_VICTORY = "🏆";
    private static final String ICON_SKULL = "💀";
    private static final String ICON_ATTACK = "⚔️";
    private static final String ICON_POWER = "✨";
    private static final String ICON_HEAL = "➕";
    private static final String ICON_MAGE = "🧙‍♂️";
    private static final String ICON_GUERRIER = "🛡️";
    private static final String ICON_ARCHER = "🎯";
    private static final String ICON_GOBLIN = "🧟";
    private static final String ICON_TROLL = "🧌";
    private static final String ICON_DRAGON = "🐉";

    private List<Personnage> ennemis;
    private int compteurCombat = 0;


    public CombatManager() {
        this.ennemis = new ArrayList<>();
        ennemis.add(new Gobelin());
        ennemis.add(new Troll());
        ennemis.add(new Dragon());
    }

    private Ennemi ennemisAleatoire() {
        compteurCombat++;
        Random r = new Random();
        int indexAleatoire = r.nextInt(ennemis.size());

        Personnage ennemiModele = ennemis.get(indexAleatoire);

        Personnage ennemiAleatoire = null;
        if (ennemiModele instanceof Gobelin) {
            ennemiAleatoire = new Gobelin();
        } else if (ennemiModele instanceof Troll) {
            ennemiAleatoire = new Troll();
        } else if (ennemiModele instanceof Dragon) {
            ennemiAleatoire = new Dragon();
        } else {
            return new Ennemi("Ennemi Inconnu", 50, 10, 5);
        }

        String ennemiIcon = ICON_ENEMY;
        if (ennemiAleatoire instanceof Gobelin) {
            ennemiIcon = ICON_GOBLIN;
        } else if (ennemiAleatoire instanceof Troll) {
            ennemiIcon = ICON_TROLL;
        } else if (ennemiAleatoire instanceof Dragon) {
            ennemiIcon = ICON_DRAGON;
        }

        System.out.println("\n" + ennemiIcon + " Ennemi n° " + compteurCombat + " : " + ennemiAleatoire.getNom() + " sauvage apparaît !");
        return (Ennemi) ennemiAleatoire;
    }

    private String demanderNomJoueur(Scanner scanner) {
        System.out.println(" Lancement du jeu ...");
        System.out.print("Entrez votre nom de joueur: ");
        String nom = scanner.nextLine().trim();
        while (nom.isEmpty()) {
            System.out.println("Votre nom de joueur ne peut pas être vide. Veuillez réessayez.");
            System.out.print("Entrez votre nom de joueur: ");
            nom = scanner.nextLine().trim();
        }
        return nom;
    }

    private void afficherChoixClasseHeros () {
        System.out.println("\nChoisissez votre classe de héros :");
        System.out.println("1. " + ICON_MAGE + " Mage (Maîtrise la magie, régénère le mana)");
        System.out.println("2. " + ICON_GUERRIER + " Guerrier (Robuste au corps à corps, génère et utilise la rage)");
        System.out.println("3. " + ICON_ARCHER + " Archer (Attaques à distance, utilise et régénère des flèches)");
        System.out.print("Votre choix (1-3) : ");
    }

    private int lireChoixClasseHeros(Scanner scanner) {
        int choixClasse = -1;
        boolean choixValide = false;
        while (!choixValide) {
            try {
                String input = scanner.nextLine();
                choixClasse = Integer.parseInt(input);
                if (choixClasse >= 1 && choixClasse <= 3) {
                    choixValide = true;
                } else {
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 3.");
                    System.out.print("Votre choix : ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Saisie invalide. Veuillez entrer un nombre entier.");
                System.out.print("Votre choix : ");
            }
        }
        return choixClasse;
    }

    private Hero creerHeroSelonChoix(String nom, int choixClasse) {
        Hero hero;
        switch (choixClasse) {
            case 1:
                hero = new Mage(nom);
                break;
            case 2:
                hero = new Guerrier(nom);
                break;
            case 3:
                hero = new Archer(nom);
                break;
            default:
                System.out.println("Erreur de sélection de classe, Mage choisi par défaut.");
                hero = new Mage(nom);
                break;
        }
        return hero;
    }

    private String getHerosClasseIcone(Hero hero) {
        if (hero instanceof Mage) {
            return ICON_MAGE;
        } else if (hero instanceof Guerrier) {
            return ICON_GUERRIER;
        } else if (hero instanceof Archer) {
            return ICON_ARCHER;
        }
        return ICON_HERO;
    }

    private Hero initialiserHero(Scanner scanner) {
        String nom = demanderNomJoueur(scanner);
        afficherChoixClasseHeros();
        int choixClasse = lireChoixClasseHeros(scanner);
        Hero hero = creerHeroSelonChoix(nom,choixClasse);
        String herosIcone = getHerosClasseIcone(hero);
        System.out.println(herosIcone + " Nouveau héros enregistré : " + hero.getNom() + " " +    hero.getClass().getSimpleName() + " !");
        return hero;
    }

    private void AfficherStatutHeroEtEnnemi(Hero hero, Ennemi ennemi) {
        String heroIcon = ICON_HERO;
        if (hero instanceof Mage) {
            heroIcon = ICON_MAGE;
        } else if (hero instanceof Guerrier) {
            heroIcon = ICON_GUERRIER;
        } else if (hero instanceof Archer) {
            heroIcon = ICON_ARCHER;
        }

        String ennemiIcon = ICON_ENEMY;
        if (ennemi instanceof Gobelin) {
            ennemiIcon = ICON_GOBLIN;
        } else if (ennemi instanceof Troll) {
            ennemiIcon = ICON_TROLL;
        } else if (ennemi instanceof Dragon) {
            ennemiIcon = ICON_DRAGON;
        }


        System.out.print("\n" + heroIcon + " " + hero.getNom() + " (" + hero.getClass().getSimpleName() + ") - ");
        System.out.print(ICON_HP + "PV: " + hero.getPv() + "/" + hero.getPvMax() + " | ");

        if (hero instanceof Mage) {
            Mage mage = (Mage) hero;
            System.out.print(ICON_MANA + "Mana: " + mage.getMana() + "/" + mage.MANA_MAX + " | ");
        } else if (hero instanceof Guerrier) {
            Guerrier guerrier = (Guerrier) hero;
            System.out.print(ICON_RAGE + "Rage: " + guerrier.getRage() + "/" + guerrier.RAGE_MAX + " | ");
        } else if (hero instanceof Archer) {
            Archer archer = (Archer) hero;
            System.out.print(ICON_ARROW + "Flèches: " + archer.getFleches() + "/" + archer.FLECHES_MAX + " | ");
        }

        System.out.println(ICON_POTION + "Potion: " + hero.getNombreDePotions());
        System.out.println(ennemiIcon + " " + ennemi.getNom() + " - " + ICON_HP + "PV: " + ennemi.getPv() + "/" + ennemi.getPvMax());
    }

    private int lireChoixAction(Scanner scanner) throws InvalideActionChoisieException {
        System.out.println("\n Que voulez-vous faire ?");
        System.out.println("1. " + ICON_ATTACK + " Attaquer (Attaque de base)");
        System.out.println("2. " + ICON_POWER + " Utiliser pouvoir (Compétence spéciale)");
        System.out.println("3. " + ICON_HEAL + " Utiliser potion (Soins)");

        int choix = -1;
        boolean saisieValide = false;

        while (!saisieValide) {
            System.out.print("Choix : ");
            String input = scanner.nextLine();
            try {
                choix = Integer.parseInt(input);
                if (choix >= 1 && choix <= 3) {
                    saisieValide = true;
                } else {
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 3.");
                }
            } catch (NumberFormatException e) {
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
            System.out.println("\n" + ICON_VICTORY + " " + ennemi.getNom() + " a été vaincu ! Bravo " + hero.getNom() + " !");

            hero.incrementerCompteurVictoire();

            if (hero instanceof Mage) {
                ((Mage) hero).restaureMana();
            } else if (hero instanceof Guerrier) {
                ((Guerrier) hero).restaureRage();
            } else if (hero instanceof Archer) {
                ((Archer) hero).restaureFleche();
            }

            if (hero.getCompteurVictoire() >= 5) {
                System.out.println("\n--- " + ICON_VICTORY + " VICTOIRE FINALE ! " + ICON_VICTORY + " ---");
                System.out.println(hero.getNom() + " a vaincu " + hero.getCompteurVictoire() + " ennemis ! Vous avez accompli votre quête !");
                return true;
            }
        } else if (!hero.estVivant()) {
            System.out.println("\n" + ICON_SKULL + " " + hero.getNom() + " a été vaincu par " + ennemi.getNom() + " !");
            return true;
        }
        return false;
    }

    public void demarrerPartie() {
        Hero monHero = null;
        try (Scanner scanner = new Scanner(System.in)) {
            monHero = initialiserHero(scanner);

            while (monHero.estVivant() && monHero.getCompteurVictoire() < 5) {
                System.out.println("\n--- " + ICON_ATTACK + " NOUVEAU COMBAT " + ICON_ATTACK + " ---");
                Ennemi ennemiActuel = ennemisAleatoire();
                System.out.println("Vous affrontez : " + ennemiActuel.getNom() + " (" + ennemiActuel.getPv() + "/" + ennemiActuel.getPvMax() + " " + ICON_HP + ")");

                while (monHero.estVivant() && ennemiActuel.estVivant()) {
                    AfficherStatutHeroEtEnnemi(monHero, ennemiActuel);
                    int choix = -1;
                    try {
                        choix = lireChoixAction(scanner);
                    } catch (InvalideActionChoisieException e) {
                        System.err.println(e.getMessage());
                        continue;
                    }

                    choisirActionHero(monHero, ennemiActuel, choix);

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
                    System.out.println("\n" + ICON_VICTORY + " Félicitations, " + monHero.getNom() + " ! Le monde est sauvé !");
                } else if (!monHero.estVivant()) {
                    System.out.println("\n" + ICON_SKULL + " Votre quête se termine ici, " + monHero.getNom() + ". Mieux vaut se préparer la prochaine fois !");
                }
            } else {
                System.out.println("\nLe jeu s'est terminé de manière inattendue avant la création du héros.");
            }
            System.out.println("\nFin du programme. Merci d'avoir joué !");
        }
    }
}