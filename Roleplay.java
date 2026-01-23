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
    public void setHealth(){
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

    //Methods of Attacking !!

    public void basicAttack (){
        if (job.equalsIgnoreCase("archer")){                                                    //Iric Notes on damage balance :D
        System.out.println(name + "uses Arrow Shot !! ");                                                    //archer base dmg = 20, 22, 23, 30 = 95
        System.out.println("Arrow Shot hits and deals " + damage + " physical damage !!");                  //warrior base dmg = 5, 20, 30, 45 = 100
        } else if (job.equalsIgnoreCase("warrior")) {                                       //mage base dmg = 10, 25, 35, 50 = 130
            System.out.println(name + "uses Hammer Swing !!");                                           //assassin base dmg = 12, 25, 30, 40 = 107
            System.out.println("Hammer Swing hits and deals " + damage + " physical damage !!");
        } else if (job.equalsIgnoreCase("mage")){
            System.out.println(name + "uses Weird Fingers :0");
            System.out.println("Weird Fingers lands and deals " + damage + " magic damage !!");
        } else if (job.equalsIgnoreCase("assassin")){
            System.out.println(name + "uses Lethal Ambush...");
            System.out.println("It deals " + damage + " physical damage !!");
        }
    }

    public void specialAttack1(){
        if (job.equalsIgnoreCase ("archer")){
            System.out.println(name + "");
            System.out.println("" + (damage + 2) + " physical damage !!");      //dmg = 22
        } else if (job.equalsIgnoreCase("warrior")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 15) + " physical damage !!");     //dmg = 20
        } else if (job.equalsIgnoreCase("mage")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 20) + " magic damage :0");        //dmg = 30
        } else if (job.equalsIgnoreCase("assassin")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 13) + " TRUE damage");            //dmg = 25
        }
}

    public void specialAttack2(){
        if (job.equalsIgnoreCase ("archer")){
            System.out.println(name + "");
            System.out.println("" + (damage + 3) + " physical damage !!");      //dmg = 23
        } else if (job.equalsIgnoreCase("warrior")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 25) + " physical damage !!");     //dmg = 30
        } else if (job.equalsIgnoreCase("mage")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 30) + " magic damage :0");        //dmg = 40
        } else if (job.equalsIgnoreCase("assassin")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 18) + " TRUE damage");            //dmg = 30
        }
}

    public void ultimateAbility(){
        if (job.equalsIgnoreCase ("archer")){
            System.out.println(name + "");
            System.out.println("" + (damage + 10) + " physical damage !!");     //dmg = 30
        } else if (job.equalsIgnoreCase("warrior")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 40) + " physical damage !!");     //dmg = 45
        } else if (job.equalsIgnoreCase("mage")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 40) + " magic damage :0");        //dmg = 50
        } else if (job.equalsIgnoreCase("assassin")) {
            System.out.println(name + "");
            System.out.println("" + (damage + 28) + " TRUE damage");            //dmg = 40
        }
    }
}

public class Roleplay {
    public static void main(String[] args) {

        //Character Selection Screen With Stats and Description Displayed
        System.out.println("Print: /"Hello World"/ ");
        System.out.println("Welcome to the Bread Games !! Created by none other than Mr Tim/Thomas/Eric/Eerik/Amber's Foot Stool... ");
        System.out.println("");
        System.out.println("");
        System.out.print("To Get Started, type: \"play\": ");
        String playButton = s.nextLine();   //Gotta use an if/while statement so that if play or PLAY is typed, it progresses the text

        System.out.println("Please Choose your character: ");
        System.out.println("");
        System.out.println("Archer Stats: ");
        System.out.println("Health: " + archer.getHealth());
        System.out.println("Base Damage: " + archer.getDamage());
        System.out.println("Speed: " + archer.getSpeed()\n\n);
        
        System.out.println("Warrior Stats: ");
        System.out.println("Health: " + warrior.getHealth());
        System.out.println("Damage: " + warrior.getDamage());
        System.out.println("Speed: " + warrior.getSpeed()\n\n);


        System.out.println("Mage Stats: ");
        System.out.println("Health: " + mage.getHealth());
        System.out.println("Damage: " + mage.getDamage());
        System.out.println("Speed: " + mage.getSpeed()\n\n);


        System.out.println("Assassin Stats: ");
        System.out.println("Health: " + assassin.getHealth());
        System.out.println("Damage: " + assassin.getDamage());
        System.out.println("Speed: " + assassin.getSpeed()\n\n);

        System.out.print("Please choose your character (Archer, Warrior, Mage, or Assassin): ");
        String chosenCharacter = s.nextLine();
        System.out.println("");
        System.out.println("");
        System.out.println("Yippee !! You have chosen the " + chosenCharacter + " class !! ");
        System.out.println("");
        System.out.println("");
        System.out.print("Now... NAME YOUR CHARACTER OI OI OI: ");
        String characterName = s.nextLine();
        System.out.println("");
        System.out.println("");



        
        //Story Line Choo Choo before first boss encounter



        System.out.println("");

        //Objects of the Different Hero Roles !!
        Hero archer = new Hero ("characterName", "Archer", 110, 20, 75, 0);
        Hero warrior = new Hero ("characterName", "Warrior", 170, 5, 60, 0);
        Hero mage = new Hero ("characterName", "Mage", 80, 10, 50, 0);
        Hero assassin = new Hero ("characterName", "Assassin", 125, 12, 90, 0);



    

    }
    
}
