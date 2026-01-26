import java.util.Scanner;

class Hero {
    private String name;
    private String job;
    private double health;
    private double damage;
    private double speed;
    private double exp;
    //For the cooldown system
    private int special1NextTurn = 0;
    private int special2NextTurn = 0;
    private int ultimateNextTurn = 0;

    // Track the hero's max / leveled-up stats for resetting after battles
    private double maxHealth;
    private double baseDamage;
    private double baseSpeed;

    //Constructor
    public Hero(String name, String job, double health, double damage, double speed, double exp) {
        this.name = name;
        this.job = job;
        this.health = health;
        this.maxHealth = health;   // store max health for resets
        this.damage = damage;
        this.baseDamage = damage;  // store base damage for resets
        this.speed = speed;
        this.baseSpeed = speed;    // store base speed for resets
        this.exp = exp;
    }

    //Setters
    public void setName(String name) { this.name = name; }
    public void setJob(String job) { this.job = job; }
    public void setHealth(double health) { this.health = health; }
    public void setDamage (double damage) {this.damage = damage; }
    public void setSpeed(double speed) { this.speed = speed; }
    public void setExp(double exp) { this.exp = exp; }

    //Getters
    public String getName() { return name; }
    public String getJob() { return job; }
    public double getHealth() { return health; }
    public double getDamage() { return damage; }
    public double getSpeed() { return speed; }
    public double getExp() { return exp; }

    //Hero-Gaining Exp feature with SETTER
    public void gainExp(double amount, Scanner s) {
        exp += amount;
        System.out.println("\nYou gained " + amount + " EXP! Total EXP: " + exp);

        while (exp >= 100) { // handle multiple level-ups at once
            exp -= 100;
            System.out.println("\n=== LEVEL UP! ===");
            health += 10;
            maxHealth += 10; // update max health on level up
            damage += 5;
            baseDamage += 5; // update base damage on level up
            speed += 5;
            baseSpeed += 5;  // update base speed on level up
            System.out.println("Your stats increased! Health +10, Damage +5, Speed +5");

            // Letting user choose their fav stat :D
            int choice = 0;
            boolean validChoice = false;

            while (!validChoice) {
                System.out.println("\nChoose one stat to boost further:");
                System.out.println("1 - Health");
                System.out.println("2 - Damage");
                System.out.println("3 - Speed");
                System.out.print("Enter choice (1-3): ");

                if (s.hasNextInt()) {
                    choice = s.nextInt();
                    s.nextLine(); // consume newline
                    if (choice >= 1 && choice <= 3) validChoice = true;
                    else System.out.println("Invalid choice. Try again!");
                } else {
                    s.nextLine(); // clear invalid input
                    System.out.println("Invalid choice. Try again!");
                }
            }

            //Me using SETTERS for the updating of stats from the level up !!
            switch (choice) {
                case 1 -> {
                    setHealth(getHealth() + 10);
                    maxHealth += 10;  // update max health permanently
                    System.out.println("Health boosted by +10!");
                }
                case 2 -> {
                    setDamage(getDamage() + 5);
                    baseDamage += 5;  // update base damage permanently
                    System.out.println("Damage boosted by +5!");
                }
                case 3 -> {
                    setSpeed(getSpeed() + 5);
                    baseSpeed += 5;   // update base speed permanently
                    System.out.println("Speed boosted by +5!");
                }
            }

            System.out.println("Current Stats -> Health: " + health + ", Damage: " + damage + ", Speed: " + speed);
        }
    }

    // Reset hero stats for each battle so it doesn't stack
    public void resetForBattle() {
        this.health = maxHealth;  // restore health
        this.damage = baseDamage; // restore damage
        this.speed = baseSpeed;   // restore speed
        this.special1NextTurn = 0;
        this.special2NextTurn = 0;
        this.ultimateNextTurn = 0;
    }

    //Hero taking damage
    public boolean takeDamage(double damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            System.out.println("\n" + name + " Was defeated !!");
            return true;
        }
        System.out.println("\n" + name + " now has " + health + " hp remaining !!");
        return false;
    }

    //Methods of Attacking !!

                                                                                                    //Iric Notes on damage balance :D
                                                                                                   //archer base dmg = 20, 22, 23, 30 = 95
                                                                                                  //warrior base dmg = 5, 20, 30, 45 = 100
                                                                                                 //mage base dmg = 10, 25, 35, 50 = 130
                                                                                                //assassin base dmg = 12, 25, 30, 40 = 107

public void basicAttack(Enemy target) {
    System.out.println("\n==============================");

    double finalDamage = damage;

    if (job.equalsIgnoreCase("archer")) {
        System.out.println("** " + name + " uses ARROW SHOT !! **\n");
        System.out.println("Arrow Shot hits and deals " + finalDamage + " physical damage !! \n");

    } else if (job.equalsIgnoreCase("warrior")) { // ← FIXED
        System.out.println("** " + name + " uses HAMMER SWING !!");
        System.out.println("The Swing hits and deals " + finalDamage + " physical damage !!\n");

    } else if (job.equalsIgnoreCase("mage")) {
        System.out.println("** " + name + " uses WEIRD FINGERS :0 **\n");
        System.out.println("Weird Fingers lands and deals " + finalDamage + " magic damage !!\n");

    } else if (job.equalsIgnoreCase("assassin")) {
        System.out.println("** " + name + " uses BACK STAB...\n");
        System.out.println("It deals " + finalDamage + " emotional damage !! **\n");
    }

    target.takeDamage(finalDamage);
    System.out.println("==============================\n");
}

public void specialAttack1(Enemy target, int turnCount) {
    System.out.println("\n==============================");
            if (turnCount < special1NextTurn) {
                System.out.println("Special Attack 2 is on cooldown !! (" + (special1NextTurn - turnCount) + " turn(s) left !!");
                return;
        }
    double finalDamage = damage;

    if (job.equalsIgnoreCase("archer")) {
        finalDamage = damage + 2;
        System.out.println("** " + name + " uses PELLET GUN !! **\n");
        System.out.println("Pellet Gun deals " + finalDamage + " physical damage !!\n");

    } else if (job.equalsIgnoreCase("warrior")) {
        finalDamage = damage + 15;
        System.out.println("** " + name + " uses POK POK !! **\n");
        System.out.println("Pok Pok hits twice and deals " + finalDamage + " physical damage !!\n");

    } else if (job.equalsIgnoreCase("mage")) {
        finalDamage = damage + 20;
        System.out.println("** " + name + " uses TELEKENISIS **\n");
        System.out.println("Telekenisis lands and throws the enemy up and down dealing " + finalDamage + " magic damage :0\n");

    } else if (job.equalsIgnoreCase("assassin")) {
        finalDamage = damage + 13;
        System.out.println("** " + name + " uses LETHAL AMBUSH... **\n");
        System.out.println("The enemy doesn't suspect it and it deals " + finalDamage + " TRUE damage\n");
    }

    target.takeDamage(finalDamage);
    special1NextTurn = turnCount + 2;
    System.out.println("==============================\n");
}

    public void specialAttack2(Enemy target, int turnCount) {
        System.out.println("\n==============================");

        if (turnCount < special2NextTurn) {
            System.out.println("Special Attack 2 is on cooldown !! (" + (special2NextTurn - turnCount) + " turn(s) left !!");
            return;
        }
        double finalDamage = damage;

        if (job.equalsIgnoreCase("archer")) {
            finalDamage = damage + 3;
            System.out.println("** " + name + " uses BOW SMACK !! **\n");
            System.out.println("The smack deals " + (damage + 3) + " physical damage !!\n");
        } else if (job.equalsIgnoreCase("warrior")) {
            finalDamage = damage + 25;
            System.out.println("** " + name + " mimicks Thor's Mjölnir Throw !! **\n");
            System.out.println("The hammer does not inherit Mjölnir's lightning powers but does deal " + (damage + 25) + " physical damage !!\n");
        } else if (job.equalsIgnoreCase("mage")) {
            finalDamage = damage + 30;
            System.out.println("** " + name + " uses ARCANE BLAST !! **\n");
            System.out.println("The shot hits and deals " + (damage + 30) + " magic damage :0\n");
        } else if (job.equalsIgnoreCase("assassin")) {
            finalDamage = damage + 18;
            System.out.println("** " + name + " uses the ELEMENT OF SURPRISE :0 **\n");
            System.out.println("The enemy is shocked and it deals " + (damage + 18) + " emotional damage and inflicts schizophrenia !!\n");
        }

        target.takeDamage(finalDamage);
        special2NextTurn = turnCount + 2;
        System.out.println("==============================\n");
    }

    public void ultimateAbility(Enemy target, int turnCount) {
        System.out.println("\n==============================");

        double finalDamage = damage;

            if (turnCount < ultimateNextTurn) {
                System.out.println("ULTIMATE is on cooldown! (" + (ultimateNextTurn - turnCount) + " turn(s) left)");
            return;
    }
        if (job.equalsIgnoreCase("archer")) {
            finalDamage = damage + 10;
            System.out.println("** " + name + " summons Michiko and uses DEATH FROM ABOVE !! **\n");
            System.out.println("100 arrows are shot but only 30 hit and dealt " + (damage + 10) + " physical damage !!\n");
        } else if (job.equalsIgnoreCase("warrior")) {
            finalDamage = damage + 40;
            System.out.println("** " + name + " summons BOB THE BUILDER !! **\n");
            System.out.println("Bob spawns creating a machine gun, shoots the enemy, and deals " + (damage + 40) + " true damage !!\n");
        } else if (job.equalsIgnoreCase("mage")) {
            finalDamage = damage + 40;
            System.out.println("** " + name + " uses FIRE TORNADO !! **\n");
            System.out.println("The tornado hits the enemy several times dealing " + (damage + 40) + " magic damage :0\n");
        } else if (job.equalsIgnoreCase("assassin")) {
            finalDamage = damage + 28;
            System.out.println("** " + name + " gets paper and uses A THOUSAND SLASHES **\n");
            System.out.println("Thousands of papercut inflict the enemy and deals " + (damage + 28) + " TRUE damage\n");
        }
        target.takeDamage(finalDamage);
        ultimateNextTurn = turnCount + 4;
        System.out.println("==============================\n");
    }

    //Showing the mooooves :D
    public void showAttackMoves() {
        System.out.println("\n---------Attack Moves---------\n");
        if (job.equalsIgnoreCase("archer")) {
            System.out.println("(1) Arrow Shot ; " + (damage));
            System.out.println("(2) Pellet Gun ; " + (damage + 2));
            System.out.println("(3) Bow Smack ; " + (damage + 3));
            System.out.println("(4) Death From Above ; " + (damage + 10));
        } else if (job.equalsIgnoreCase("warrior")) {
            System.out.println("(1) Hammer Swing ; " + damage);
            System.out.println("(2) Pok Pok ; " + (damage + 15));
            System.out.println("(3) Thor's Mjölnir Throw ; " + (damage + 25));
            System.out.println("(4) Bob The Builder ; " + (damage + 40));
        } else if (job.equalsIgnoreCase("mage")) {
            System.out.println("(1) Weird Fingers ; " + (damage));
            System.out.println("(2) Telekenisis ; " + (damage + 20));
            System.out.println("(3) Arcane Blast ; " + (damage + 30));
            System.out.println("(4) Fire Tornado ; " + (damage + 40));
        } else if (job.equalsIgnoreCase("assassin")) {
            System.out.println("(1) Back Stab ; " + (damage));
            System.out.println("(2) Lethal Ambush ; " + (damage + 13));
            System.out.println("(3) Element of Surprise  ; " + (damage + 18));
            System.out.println("(4) A Thousand Slashes ; " + (damage + 28));
        }
    }
}

// Enemy class
class Enemy {
    private String name;
    private double health;
    private double damage;
    private double speed;
    private double expReward;

    //Consturction !! #Engineer
    public Enemy(String name, double health, double damage, double speed, double expReward) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.expReward = expReward;
    }

    //Getters for wot I wanna get ONLY
    public double getExpReward() { return expReward; }
    public double getHealth() { return health; }
    public double getSpeed() { return speed; }
    public String getName() { return name; }

    //Enemy Attacked
    public boolean takeDamage(double amount) {
        health -= amount;
        if (health <= 0) {
            health = 0;
            System.out.println("\n" + name + " Was defeated !!");
            return true;
        }
        System.out.println("\n" + name + " now has " + health + " hp remaining !!");
        return false;
    }

    //Enemy Attacks
    public void enemyBasicAttack(Hero target) {
        System.out.println("\n==============================");
        System.out.println("\n** " + name + " attacks !! **");
        System.out.println("It deals " + damage + " damage !!\n");
        target.takeDamage(damage);
        System.out.println("==============================\n");
    }

    public void enemySpecialAttack(Hero target) {
        double finalDamage = damage;
        System.out.println("\n==============================");
        finalDamage = damage + 10;
        System.out.println("\n** " + name + " uses a SPECIAL ATTACK !! **");
        System.out.println("\nIt deals " + (damage + 10) + " damage !!\n");
        target.takeDamage(finalDamage);
        System.out.println("==============================\n");
    }
}

        //=====================================================================FRONT-END PROPER=======================================================================================

    public class Roleplay {

    //Helper method for player and enemy turn
    static void playerTurn(Hero player, Enemy enemy, Scanner s, int turnCount) {
        int move;
        while (true) {
            player.showAttackMoves();
            System.out.print("\nWhat's your move (1-4): ");
            move = s.nextInt();
            s.nextLine();

        if (move >= 1 && move <= 4) {
            break;
        }
            System.out.println("Invalid move! Try again.");
        }
        // Execute the attack
        switch (move) {
            case 1 -> player.basicAttack(enemy);
            case 2 -> player.specialAttack1(enemy, turnCount);
            case 3 -> player.specialAttack2(enemy, turnCount);
            case 4 -> player.ultimateAbility(enemy, turnCount);
        }
    }

        static void enemyTurn(Enemy enemy, Hero player, int turnCount){
            System.out.println("Enemy's turn");

            if (turnCount % 3 == 0){
                enemy.enemySpecialAttack(player);
            } else {
                enemy.enemyBasicAttack(player);
            }
        }
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {

            System.out.print("\n\nPrint: \"Hello World\"\n\n(click enter to continue)   ->");
            String fill1 = s.nextLine();

            System.out.print("\nWelcome to the Bread Games !! Created by none other than Mr Tim/Thomas/Eric/Eerik/Amber's Foot Stool...   ->");
            String fill2 = s.nextLine();

            System.out.println();
            System.out.print("To Get Started type \"play\": ");
            String playButton = s.nextLine();

            System.out.println();
            System.out.print("Well, no matter what you input, YOU'RE PLAYING >:D   ->");
            String fill3 = s.nextLine();

            System.out.println();
            System.out.print("Now before we start... NAME YOUR CHARACTER OI OI OI (choose wisely): ");
            String characterName = s.nextLine();

            System.out.println("\n=======================================================================================================================");
            System.out.println();
            System.out.print("Greetings " + characterName + ", you are about to enter a realm of weird things made by me and it's important that you pick a character   ->");
            String fill4 = s.nextLine();
            System.out.print("\nThere are 4 roles namely, archer, warrior, mage, and assassin   ->");
            String fill5 = s.nextLine();
            System.out.print("\nPlease choose your role and remember, pick wisely !!  ->");
            String fill6 = s.nextLine();

            //OBJECTS
            Hero archer = new Hero(characterName, "Archer", 110, 20, 75, 0);
            Hero warrior = new Hero(characterName, "Warrior", 170, 5, 60, 0);
            Hero mage = new Hero(characterName, "Mage", 80, 10, 50, 0);
            Hero assassin = new Hero(characterName, "Assassin", 125, 12, 90, 0);

            System.out.println("\nBelow are the 4 classes of characters :D \n");

            System.out.println("=======ARCHER=======");
            System.out.println("Archer Stats: ");
            System.out.println("Health: " + archer.getHealth());
            System.out.println("Base Damage: " + archer.getDamage());
            System.out.println("Speed: " + archer.getSpeed());
            System.out.println("====================\n");

            System.out.println("=======WARRIOR======");
            System.out.println("Health: " + warrior.getHealth());
            System.out.println("Damage: " + warrior.getDamage());
            System.out.println("Speed: " + warrior.getSpeed());
            System.out.println("====================\n");

            System.out.println("========MAGE========");
            System.out.println("Health: " + mage.getHealth());
            System.out.println("Damage: " + mage.getDamage());
            System.out.println("Speed: " + mage.getSpeed());
            System.out.println("====================\n");

            System.out.println("======Assassin======");
            System.out.println("Health: " + assassin.getHealth());
            System.out.println("Damage: " + assassin.getDamage());
            System.out.println("Speed: " + assassin.getSpeed());
            System.out.println("====================\n");

            //Character Selection
            String chosenCharacter;
            while (true) {
                System.out.print("Please choose your character by typing \"archer,\" \"warrior,\" \"mage,\" or \"assassin\": ");
                chosenCharacter = s.nextLine().toLowerCase();
            if (chosenCharacter.equals("archer") ||
                chosenCharacter.equals("warrior") ||
                chosenCharacter.equals("mage") ||
                chosenCharacter.equals("assassin")) {
                break;
            } else {
                System.out.println("\nWrong input. Try again bruh.\n");
            }
        }

            Hero player = null; //This is where I create the player varaible (not an object)
            if (chosenCharacter.equals("archer")) player = archer;
            else if (chosenCharacter.equals("warrior")) player = warrior;
            else if (chosenCharacter.equals("mage")) player = mage;
            else if (chosenCharacter.equals("assassin")) player = assassin;

            System.out.println();
            System.out.println();
            System.out.print("Yippee !! You have chosen the " + chosenCharacter + " class !!   ->");
            String fill7 = s.nextLine();

            System.out.println();
            System.out.println("=======================================STORY LINE !!=======================================");

            System.out.println();
            System.out.print("It is the 27th of January.   ->");
            String fill8 = s.nextLine();
            System.out.print("\nYou wake up and realize it's your bithday :0  ->");
            String fill9 = s.nextLine();
            System.out.print("\nYou get out of bed after doom scrolling for 2 hours and realize that there is a box by the door.  ->");
            String fill10 = s.nextLine();
            System.out.print("\nYou pick up the box and see a note on it that reads, \"Happy Birthday " + characterName + " !! You are now 7 and I must leave you now because it is a family tradition.  ->\"");
            String fill11 = s.nextLine();

            if (chosenCharacter.equals("archer")) {
                System.out.print("\nI know, I know, it sounds like you're cooked rn but not to fret because in the box is our family's ancient bow that was passed down from generation to generation.  ->");
                String fill12 = s.nextLine();
                System.out.print("\nYou, are an archer " + characterName + " it's time you start your journey :D");
            } else if (chosenCharacter.equals("warrior")) {
                System.out.print("\nI know, I know, it sounds like you're cooked rn but not to fret because in the box is our family's ancient log that was passed down from generation to generation.  ->");
                String fill13 = s.nextLine();
                System.out.print("\nYou, are a warrior " + characterName + " it's time you start your journey :D");
            } else if (chosenCharacter.equals("mage")) {
                System.out.print("\nI know, I know, it sounds like you're cooked rn but not to fret because in the box is our family's ancient wand that was passed down from generation to generation.  ->");
                String fill12 = s.nextLine();
                System.out.print("\nYou, are a mage " + characterName + " it's time you start your journey :D");
            } else if (chosenCharacter.equals("assassin")) {
                System.out.print("\nI know, I know, it sounds like you're cooked rn but not to fret because in the box is our family's ancient shuriken that was passed down from generation to generation.  ->");
                String fill12 = s.nextLine();
                System.out.print("\nYou, are an assassin " + characterName + " it's time you start your journey :D");
            }

            System.out.print("\n\nAnd so you set forth in your journey as an aspiring " + chosenCharacter + " !!");
            String fill13 = s.nextLine();

            System.out.println("\n=======================================IN THE WOODS=======================================\n");
            System.out.print("You leave your humble abode and start wandering off in the woods with all the luscious trees and whatnot.  ->");
            String fill14 = s.nextLine();
            System.out.print("\nNight approaches quickly and you find a place to settle in to.  ->");
            String fill15 = s.nextLine();
            System.out.print("\nYou find a cozy tree and take shelter. And after settling in...  ->");
            String fill16 = s.nextLine();
            System.out.print("\nYou encounter a monster and go into a pokemon style battle scene :0  ->");
            String fill17String = s.nextLine();

            System.out.println("\n\n.*.*.*.*.*.*.* Pokemon entrance-like graphics .*.*.*.*.*.*.*");
            System.out.print("\nThe shadow silhouette fades and reveals the character and it's...  -> ");
            String fill18 = s.nextLine();
            System.out.print("\nSPONGEBOB ?!?!  -> ");
            String fill19 = s.nextLine();
            System.out.print("\nAncestor Spirit: Oh hey, it's your first battle.  -> ");
            String fill20 = s.nextLine();
            System.out.print("\nAncestor Spirit: Don't worry, I'll guide you on how to kill this sponge !!  -> ");
            String fill21 = s.nextLine();
            System.out.print("\nDisplayed shortly are your attack moves. It'll show the name and damage, all you have to do is type the corresponding number and it should be pretty straightforward from here on out.  ->");
            String fill22 = s.nextLine();
            System.out.print("\nBut first, we have to see who's faster.  ->");
            String fill23 = s.nextLine();

        // FIRST BATTLE
            Enemy enemy1 = new Enemy("Spongebob", 50, 10, 40, 105);
            int turnCount = 0;
            System.out.println();

            if (player.getSpeed() > enemy1.getSpeed()) {
                System.out.println(characterName + " is faster and will attack first.");
            } else {
                System.out.println(enemy1.getName() + " is faster and will attack first.");
            }

        System.out.println("\n~~~~~~~~~~~~~~~~~THE FIRST BATTLE BEGINS... SUGOI~~~~~~~~~~~~~~~~~~~~~");

        while (player.getHealth() > 0 && enemy1.getHealth() > 0) {
            System.out.println("\n------------Turn " + turnCount + "------------");

        if (player.getSpeed() > enemy1.getSpeed()) {
            playerTurn(player, enemy1, s, turnCount);
        if (enemy1.getHealth() <= 0) {
            System.out.println("Ancestor Spirit: SUGOI... You just defeated " + enemy1.getName() + " !!");
            break;
        }
            enemyTurn(enemy1, player, turnCount);
        } else {
            enemyTurn(enemy1, player, turnCount);
                if (player.getHealth() <= 0) break;

                    playerTurn(player, enemy1, s, turnCount);
                if (enemy1.getHealth() <= 0) {
                    System.out.println("Ancestor Spirit: SUGOI... You just defeated " + enemy1.getName() + " !!");
                break;
                }
            }
            turnCount++;
        }

        // POST-PURSE BATTLE
        if (player.getHealth() > 0) {
            System.out.print("\nAncestor Spirit: CONGRATULATIONS, you have just killed Spongebob !!  ->");
            String fill24 = s.nextLine();
            player.gainExp(enemy1.getExpReward(), s); //add s because user will choose the ano, the fav stat yes !!

        //SOTRY AGAUIN HUHU
        System.out.print("\nYou get the satisfaction of killing your first enemy.  ->");
        String fill25 = s.nextLine();
        System.out.print("\nBut then you smell the scent of Krabby Patties.  ->");
        String fill26 = s.nextLine();
        System.out.print("\nAnd what do you know, low and behold, another enemy spawns...  ->");
        String fill27 = s.nextLine();
    }

        // SECOND BATTLE
        Enemy enemy2 = new Enemy("Doc Krabby Pati", 100, 15, 65, 150);
        System.out.print("\nIt's " + enemy2.getName() + " !!\n");

        turnCount = 0;
        player.resetForBattle();

        System.out.println();
        if (player.getSpeed() > enemy2.getSpeed()) {
            System.out.println(characterName + " is faster and will attack first.");
        } else {
            System.out.println(enemy2.getName() + " is faster and will attack first.");
        }

        System.out.println("\n~~~~~~~~~~~~~~~~~THE SECOND BATTLE COMMENCES... SUGOI~~~~~~~~~~~~~~~~~~~~~");

        while (player.getHealth() > 0 && enemy2.getHealth() > 0) {
            System.out.println("\n------------Turn " + turnCount + "------------");

            if (player.getSpeed() > enemy2.getSpeed()) {
                playerTurn(player, enemy2, s, turnCount);
            if (enemy2.getHealth() <= 0) {
                System.out.println("Ancestor Spirit: SUGOI... You just defeated " + enemy2.getName() + " !!");
                player.gainExp(enemy2.getExpReward(), s);
                break;
                }
            enemyTurn(enemy2, player, turnCount);
        } else {
            enemyTurn(enemy2, player, turnCount);
        if (player.getHealth() <= 0) break;

        playerTurn(player, enemy2, s, turnCount);

        if (enemy2.getHealth() <= 0) {
                System.out.println("Ancestor Spirit: SUGOI... You just defeated " + enemy2.getName() + " !!");
                player.gainExp(enemy2.getExpReward(), s);
                break;
            }
        }
        turnCount++;
    }

        // POST-SIKUN BATTLE
        if (player.getHealth() > 0) {
            s.nextLine();
            System.out.print("\nAncestor Spirit: CONGRATULATIONS, you have just killed " + enemy2.getName() + " !!  ->");
            String fill28 = s.nextLine();

        //STORY LINE AGAIN HUHU
        System.out.print("\nYou survived looking busy and continue on your adventure  ->");
        String fill29 = s.nextLine();
        System.out.print("\nYou leave the tree as morning rises and start farmin enemies ->");
        String fill30 = s.nextLine();
        System.out.print("\nAnd what do you know, the first monster you encounter is...  ->");
        String fill31 = s.nextLine();
    }

        // THIRD BATTLE
        Enemy enemy3 = new Enemy("The IT department D:", 120, 15, 70, 50);
        System.out.print("\n" + enemy3.getName() + " !!\n");

        turnCount = 0;
        player.resetForBattle();

        System.out.println();
        if (player.getSpeed() > enemy3.getSpeed()) {
            System.out.println(characterName + " is faster and will attack first.");
        } else {
            System.out.println(enemy3.getName() + " is faster and will attack first.");
        }

        System.out.println("\n~~~~~~~~~~~~~~~~~THE TURD BATTLE COMMENCES... SUGOI~~~~~~~~~~~~~~~~~~~~~");

        while (player.getHealth() > 0 && enemy3.getHealth() > 0) {
            System.out.println("\n------------Turn " + turnCount + "------------");

        if (player.getSpeed() > enemy3.getSpeed()) {
            playerTurn(player, enemy3, s, turnCount);
        if (enemy3.getHealth() <= 0) {
            System.out.println("Ancestor Spirit: SUGOI... You just defeated " + enemy3.getName() + " !!");
            player.gainExp(enemy3.getExpReward(), s);
            break;
        }
            enemyTurn(enemy3, player, turnCount);
        } else {
            enemyTurn(enemy3, player, turnCount);
        if (player.getHealth() <= 0) break;

            playerTurn(player, enemy3, s, turnCount);
            if (enemy3.getHealth() <= 0) {
                System.out.println("Ancestor Spirit: SUGOI... You just defeated " + enemy3.getName() + " !!");
                player.gainExp(enemy3.getExpReward(), s);
                break;
            }
        }
        turnCount++;
    }

        // POST-THUD BATTLE
        if (player.getHealth() > 0) {
            s.nextLine();
            System.out.print("\nAncestor Spirit: CONGRATULATIONS, you have just killed " + enemy3.getName() + " !!  ->");
            String fill32 = s.nextLine();

        //MA'AM JAN JAN'S STORY LINE
            System.out.print("\nYou forgor you had school and attended your CC3 class.  ->");
            String fill33 = s.nextLine();
            System.out.print("\nYou arrive and no one is there. Desks empty as if abandoned... ->");
            String fill34 = s.nextLine();
            System.out.print("\nAnd what do you know, the professor comes in...  ->");
            String fill35 = s.nextLine();
        }

        // FOURTH BATTLE
        Enemy enemy4 = new Enemy("Ma'am JanJan's Aura", 150, 20, 100, 200);
        System.out.print("\nIt's the presence of a professor... IT'S " + enemy4.getName() + " !!\n");

        turnCount = 0;
        player.resetForBattle();
        System.out.println();
        
        if (player.getSpeed() > enemy4.getSpeed()) {
            System.out.println(characterName + " is faster and will attack first.");
        } else {
            System.out.println(enemy4.getName() + " is faster and will attack first.");
        }

        System.out.println("\n~~~~~~~~~~~~~~~~~THE FORT BATTLE COMMENCES... SUGOI~~~~~~~~~~~~~~~~~~~~~");

        while (player.getHealth() > 0 && enemy4.getHealth() > 0) {
            System.out.println("\n------------Turn " + turnCount + "------------");

            if (player.getSpeed() > enemy4.getSpeed()) {
                playerTurn(player, enemy4, s, turnCount);
            if (enemy4.getHealth() <= 0) {
                    System.out.println("Ancestor Spirit: SUGOI... You just defeated " + enemy4.getName() + " !!");
                player.gainExp(enemy4.getExpReward(), s);
                break;
            }
            enemyTurn(enemy4, player, turnCount);
        } else {
            enemyTurn(enemy4, player, turnCount);
            if (player.getHealth() <= 0) break;

            playerTurn(player, enemy4, s, turnCount);
            if (enemy4.getHealth() <= 0) {
                System.out.println("Ancestor Spirit: SUGOI... You just defeated " + enemy4.getName() + " !!");
                player.gainExp(enemy4.getExpReward(), s);
                break;
            }
        }
        turnCount++;
    }

        // POST-PORT BATTLE
        if (player.getHealth() > 0) {
            s.nextLine();
            System.out.print("\nAncestor Spirit: CONGRATULATIONS, you have just killed " + enemy4.getName() + " !!  ->");
            String fill37 = s.nextLine();
            System.out.print("\nYou head home after a long day of school.  ->");
            String fill38 = s.nextLine();
            System.out.print("\nYou come home and instantly head to the bathroom for a long-deserved shower... ->");
            String fill39 = s.nextLine();
            System.out.print("\nBut then you realize you're a COMPSCI STUDENT...  ->");
            String fill40 = s.nextLine();
        }

        // FINAL BOSS BATTLE
        Enemy boss = new Enemy("Showering", 250, 10, 67, 500);
        System.out.print("\nTHE FINAL BOSS OF ALL COMPSCI STUDENTS... " + boss.getName() + " !!\n");

        turnCount = 0;
        player.resetForBattle();

        System.out.println();
        if (player.getSpeed() > boss.getSpeed()) {
            System.out.println(characterName + " is faster and will attack first.");
        } else {
            System.out.println(boss.getName() + " is faster and will attack first.");
        }

        System.out.println("\n~~~~~~~~~~~~~~~~~THE FINAL BATTLE COMMENCES... SUGOI~~~~~~~~~~~~~~~~~~~~~");

        while (player.getHealth() > 0 && boss.getHealth() > 0) {
            System.out.println("\n------------Turn " + turnCount + "------------");

            if (player.getSpeed() > boss.getSpeed()) {
                playerTurn(player, boss, s, turnCount);
                if (boss.getHealth() <= 0) {
                    System.out.println("Ancestor Spirit: SUGOI... You just defeated " + boss.getName() + " !!");
                    player.gainExp(boss.getExpReward(), s);
                    break;
                }
                enemyTurn(boss, player, turnCount);
            } else {
                enemyTurn(boss, player, turnCount);
                if (player.getHealth() <= 0) break;

                playerTurn(player, boss, s, turnCount);
                if (boss.getHealth() <= 0) {
                    System.out.println("Ancestor Spirit: SUGOI... You just defeated " + boss.getName() + " !!");
                    player.gainExp(boss.getExpReward(), s);
                    break;
            }
        }
        turnCount++;
    }

        // POST-BATTLE AND CREDITS (?)
        if (player.getHealth() > 0) {
            s.nextLine();
            System.out.print("\nAncestor Spirit: CONGRATULATIONS, you have avoided " + boss.getName() + " !!  ->");
            String fill41 = s.nextLine();
            System.out.print("\nIn fact, you've beaten the game... Well Done !!  ->");
            String fill42 = s.nextLine();
            System.out.print("\nIf you wanna play it again and try the other roles...  ->");
            String fill43 = s.nextLine();

            System.out.println("1 - Restart Game");
            System.out.println("2 - Exit Game D:");
            System.out.print("\nEnter your choice: ");

            int choiceRestart = s.nextInt();
            s.nextLine();
            if (choiceRestart == 1) playAgain = true;
                else {
                    playAgain = false;
                    System.out.println("\nExiting game, thanks for playing :D");
                }
        } else {
            System.out.println("Awwwww, you dead");
            System.out.println("Do you want to restart the game?");
            System.out.println("1 - Restart Game");
            System.out.println("2 - Exit Game D:");
            System.out.print("\nEnter your choice: ");
        }
        int choice = s.nextInt();
        s.nextLine();
            if (choice == 1) playAgain = true;
                else {
                    playAgain = false;
                    System.out.println("\nExiting game, thanks for playing :D");
                }
            }
        }
    }
