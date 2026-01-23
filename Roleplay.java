import java.util.Scanner;

class Hero{
    private String name;
    private String job;
    private double health;
    private double damage;
    private double speed;
    private double exp;

    //Constructor !!
    public Hero(String name, String job, double health, double damage, double speed, double exp){
        this.name = name;
        this.job = job;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.exp = exp;
        }

    //Setters
    public void setName(){
        this.name = name;
    }
    public void setJob(){
        this.job = job;
    }
    public void setHealth(double health){
        this.health = health;
    }
    public void setSpeed(){
        this.speed = speed;
    }
    public void setExp(){
        this.exp = exp;
    }

    //Getters !!
    public String getName(){
        return name;
    }
    public String getJob(){
        return job;
    }
    public double getHealth(){
        return health;
    }
    public double getDamage(){
        return damage;
    }
    public double getSpeed(){
        return speed;
    }
    public double getExp(){
        return exp;
    }

    //Hero being attacked
    public void takeDamage (double damage){
        health -= damage;

        if (health < 0){
            health = 0;
        }
            System.out.println(name + " now has " + health + " hp remaining !!");
    }                                                                                               //Iric Notes on damage balance :D
                                                                                                   //archer base dmg = 20, 22, 23, 30 = 95
    //Methods of Attacking !!                                                                     //warrior base dmg = 5, 20, 30, 45 = 100
                                                                                                 //mage base dmg = 10, 25, 35, 50 = 130
    public void basicAttack (Enemy target){                                                     //assassin base dmg = 12, 25, 30, 40 = 107
        if (job.equalsIgnoreCase("archer")){
            System.out.println(name + "uses Arrow Shot !! ");
            System.out.println("Arrow Shot hits and deals " + damage + " physical damage !!");
            target.takeDamage(damage);                                                              //target isn't actually initialized but a placeholder/parameter for when I actually call an enemy object to take its place
        } else if (job.equalsIgnoreCase("warrior")){
            System.out.println(name + "uses Hammer Swing !!");
            System.out.println("Hammer Swing hits and deals " + damage + " physical damage !!");
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("mage")){
            System.out.println(name + "uses Weird Fingers :0");
            System.out.println("Weird Fingers lands and deals " + damage + " magic damage !!");
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("assassin")){
            System.out.println(name + "uses Lethal Ambush...");
            System.out.println("It deals " + damage + " physical damage !!");
            target.takeDamage(damage);
        }
    }

    public void specialAttack1(Enemy target){
        if (job.equalsIgnoreCase ("archer")){
            System.out.println(name + "");
            System.out.println("" + (damage + 2) + " physical damage !!");      //dmg = 22
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("warrior")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 15) + " physical damage !!");     //dmg = 20
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("mage")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 20) + " magic damage :0");        //dmg = 30
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("assassin")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 13) + " TRUE damage");            //dmg = 25
            target.takeDamage(damage);
        }
}

    public void specialAttack2(Enemy target){
        if (job.equalsIgnoreCase ("archer")){
            System.out.println(name + "");
            System.out.println("" + (damage + 3) + " physical damage !!");      //dmg = 23
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("warrior")) {
            System.out.println(name + "");
                System.out.println("" + (damage + 25) + " physical damage !!");     //dmg = 30
                target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("mage")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 30) + " magic damage :0");        //dmg = 40
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("assassin")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 18) + " TRUE damage");            //dmg = 30
            target.takeDamage(damage);
        }
}

    public void ultimateAbility(Enemy target){
        if (job.equalsIgnoreCase ("archer")){
            System.out.println(name + "");
            System.out.println("" + (damage + 10) + " physical damage !!");     //dmg = 30
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("warrior")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 40) + " physical damage !!");     //dmg = 45
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("mage")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 40) + " magic damage :0");        //dmg = 50
            target.takeDamage(damage);
        } else if (job.equalsIgnoreCase("assassin")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 28) + " TRUE damage");            //dmg = 40
            target.takeDamage(damage);
        }
    }
}

class Enemy{
    private String name;
    private double health;
    private double damage;
    private double speed;

    //Construction !!
    public Enemy(String name, double health, double damage, double speed){
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
    }
    //Enemy Attack and Attacked !!
    public void takeDamage(double amount){
        health -= damage;

        if (health < 0){
            health = 0;
        }
            System.out.println(name + " now has " + health + " hp reamining !!");
    }
    public void enemyBasicAttack (Hero target){
        if (name.equalsIgnoreCase("Spongebob")) {
            System.out.println( name + " uses Spatula Strike !!");
            System.out.println("It deals " + damage + " damage !!");
            target.takeDamage(damage);
        } else if (name.equalsIgnoreCase("Doc Krabby Pati")) {
            System.out.println(name + " uses The 4 Milestones !!"); //the ult is LOOK BUSY
            System.out.println("It deals " + damage + " points of damage !!");
            target.takeDamage(damage);
        } else if (name.equalsIgnoreCase("The IT Department D:")) {
            System.out.println(name + " uses Capstone !!");
            System.out.println("It deals " + damage + " points of damage !!");
            target.takeDamage(damage);
        } else if (name.equalsIgnoreCase("Ma'am JanJan's Aura")) {
            System.out.println(name + " uses her Seventeen lightstick");
            System.out.println("It deals " + damage + " damage !!");
            target.takeDamage(damage);
        } else if (name.equalsIgnoreCase("Showering")){
            System.out.println(name + " uses Soap and water !!");
            System.out.println("It is HIGHLY effective and deals " + damage + " points of TRUE damage !!");
            target.takeDamage(damage);
        }
    }
}

public class Roleplay {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        //Character Selection Screen With Stats and Description Displayed
        System.out.print("\n\nPrint: \"Hello World\"" + "\n\n(click enter to continue)   ->");
        String fill1 = s.nextLine();
        System.out.print("\nWelcome to the Bread Games !! Created by none other than Mr Tim/Thomas/Eric/Eerik/Amber's Foot Stool...   ->");
        String fill2 = s.nextLine();
        System.out.println("");
        System.out.print("To Get Started type \"play\": ");
        String playButton = s.nextLine();   //Gotta use an if/while statement so that if play or PLAY is typed, it progresses the text

        System.out.println("");
        System.out.print("Well, no matter what you input, YOU'RE PLAYING >:D   ->");
        String fill3 = s.nextLine();
        System.out.println("");
        System.out.print("Now before we start... NAME YOUR CHARACTER OI OI OI (choose wisely): ");
        String characterName = s.nextLine();
        System.out.println("");

System.out.println("=======================================================================================================================");
        System.out.println("");
        System.out.print("Greetings " + characterName + ", you are about to enter a realm of weird things made by me and it's important that you pick a character   ->");
        String fill4 = s.nextLine();
        System.out.print("\nThere are 4 roles namely, archer, warrior, mage, and assassin   ->");
        String fill5 = s.nextLine();
        System.out.print("\nPlease choose your role and remember, pick wisely !!  ->");
        String fill6 = s.nextLine();

        //Objects of the Different Hero Roles !!
        Hero archer = new Hero (characterName, "Archer", 110, 20, 75, 0);
        Hero warrior = new Hero (characterName, "Warrior", 170, 5, 60, 0);
        Hero mage = new Hero (characterName, "Mage", 80, 10, 50, 0);
        Hero assassin = new Hero (characterName, "Assassin", 125, 12, 90, 0);

        //Displaying Character Stats after "hitting" play
        System.out.println("\nBelow are the 4 classes of characters :D \n");
        System.out.println("=======ARCHER=======");
        System.out.println("Archer Stats: ");
        System.out.println("Health: " + archer.getHealth());
        System.out.println("Base Damage: " + archer.getDamage());
        System.out.println("Speed: " + archer.getSpeed() );
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
        System.out.print("Please choose your character by typing \"archer,\" \"warrior,\" \"mage,\" or \"assassin\": ");
        String chosenCharacter = s.nextLine().toLowerCase();
        System.out.println("");
        System.out.println("");
        System.out.print("Yippee !! You have chosen the " + chosenCharacter + " class !!   ->");
        String fill7 = s.nextLine();
        System.out.println("");
        System.out.println("=======================================STORY LINE !!=======================================");

        //Story Line Proper
        System.out.println("");
        System.out.print("It is the 27th of January.   ->");
        String fill8 = s.nextLine();
        System.out.print("\nYou wake up and realize it's your bithday :0  ->");
        String fill9 = s.nextLine();
        System.out.print("\nYou get out of bed after doom scrolling for 2 hours and realize that there is a box by the door.  ->");
        String fill10 = s.nextLine();
        System.out.print("\nYou pick up the box and see a note on it that reads, \"Happy Birthday " + characterName + " !! You are now 7 and I must leave you now because it is a family tradition.  ->\"");
        String fill11 = s.nextLine();
        
        if (chosenCharacter.equals ("archer")){
            System.out.print("\nI know, I know, it sounds like you're cooked rn but not to fret because in the box is our family's ancient bow that was passed down from generation to generation.  ->");
            String fill12 = s.nextLine();
            System.out.print("\nYou, are an archer " + characterName + " it's time you start your journey :D");
        } else if (chosenCharacter.equals ("warrior")){
            System.out.print("\nI know, I know, it sounds like you're cooked rn but not to fret because in the box is our family's ancient log that was passed down from generation to generation.  ->");
            String fill13 = s.nextLine();
            System.out.print("\nYou, are a warrior " + characterName + " it's time you start your journey :D");
        } else if (chosenCharacter.equals ("mage")){
            System.out.print("\nI know, I know, it sounds like you're cooked rn but not to fret because in the box is our family's ancient wand that was passed down from generation to generation.  ->");
            String fill12 = s.nextLine();
            System.out.print("\nYou, are a mage " + characterName + " it's time you start your journey :D");
        } else if (chosenCharacter.equals ("assassin")){
            System.out.print("\nI know, I know, it sounds like you're cooked rn but not to fret because in the box is our family's ancient shuriken that was passed down from generation to generation.  ->");
            String fill12 = s.nextLine();
            System.out.print("\nYou, are an assassin " + characterName + " it's time you start your journey :D");
        }
        
        System.out.print("And so you set forth in your journey as an aspiring" + chosenCharacter + " !!");
        String fill13 = s.nextLine();
        System.out.println("\n=======================================IN THE WOODS=======================================");
        System.out.print("You leave your humble abode and start wandering off in the woods with all the luscious trees and whatnot.  ->");
        String fill14 = s.nextLine();
        System.out.print("\nNight approaches quickly and you find a place to settle in to.  ->");
        String fill15 = s.nextLine();
        System.out.print("You find a cozy tree and take shelter. And after settling in...  ->");
        String fill16 = s.nextLine();
        System.out.print("\nYou encounter a monster and go into a pokemon style battle scene :0  ->");
        String fill17String = s.nextLine();

        //enemy Objects !!
        Enemy enemy1 = new Enemy ("Spongebob", 50, 10, 40);
        Enemy enemy2 = new Enemy ("Doc Krabby Pati", 100, 15, 65);
        Enemy enemy3 = new Enemy ("The IT department D:", 120, 15, 70);
        Enemy enemy4 = new Enemy ("Ma'am JanJan's Aura", 150, 20, 100);
        Enemy boss = new Enemy ("Showering", 250, 10, 67);

        //First Enemy  Battle
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");


        String fill = s.nextLine();

        
        //Story Line Choo Choo before first boss encounter



        System.out.println("");



    

    }
    
}
