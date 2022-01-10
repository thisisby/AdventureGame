package adventure;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // objects
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        // Game variables
        String[] enemies = {"Skeleton", "Zombie", "Org", "Warrior", "Assassin"};
        int maxEnemyHealth = 100;
        int enemyAttackDamage = 25;

        // Player variables
        int health = 120;
        int attackDamage = 50;
        int healthPots = 3;
        int healthPotsAmount = 30;
        int healthDropChance = 50;

        boolean running = true;

        System.out.println("Welcome, to the Dungeon");

        GAME:
        while (running) {
            System.out.println("--------------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length) - 1];
            System.out.println("\t# Your enemy is " + enemy + ". #\n");

            label:
            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1 Attack");
                System.out.println("\t2 Drink health portion");
                System.out.println("\t3 Run");

                String userInput = scan.nextLine();
                switch (userInput) {
                    case "1":
                        int damageDealt = rand.nextInt(attackDamage);
                        int damageTaken = rand.nextInt(enemyAttackDamage);

                        enemyHealth -= damageDealt;
                        health -= damageTaken;

                        System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage");
                        System.out.println("\t> You got " + damageTaken + " damage from " + enemy);

                        if (health < 1) {
                            System.out.println("\t> You are too weak to continue");
                            break label;
                        }
                        break;
                    case "2":
                        if (healthPots > 0) {
                            health += healthPotsAmount;
                            healthPots--;
                            System.out.println("\t> You drink health portion, healing yourself for " + healthPotsAmount + " HP. \n\t> Now you have " + health + " HP. \n\t> You have " + healthPots + " heal portions");
                        } else {
                            System.out.println("\t> You have no healing portions. Defeat enemy to get it");
                        }
                        break;
                    case "3":
                        System.out.println("\tYou run away from enemy " + enemy + "!");
                        continue GAME;
                    default:
                        System.out.println("\tInvalid input!");
                        break;
                }
            }
            if (health < 1) {
                System.out.println("You lose!");
                break;
            }
            System.out.println("--------------------------------------------------");
            System.out.println(" # " + enemy + " was defeated #");
            System.out.println(" # You have " + health + " HP left. #");
            if(rand.nextInt(100) < healthDropChance) {
                healthPots++;
                System.out.println("# The " + enemy + " dropped health portion!");
                System.out.println("# You have " + healthPots + " health portion");
            }

            System.out.println("--------------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("#1. Continue fighting");
            System.out.println("#2. Exit dungeon");

            String userInput = scan.nextLine();

            while(!userInput.equals("1") && !userInput.equals("2")) {
                System.out.println("Invalid input");
                userInput = scan.nextLine();
            }
            if(userInput.equals("1")) {
                System.out.println("You continue adventure");
            }
            else if(userInput.equals("2")) {
                System.out.println("You exit dungeon successfully");
                break;
            }
        }

        System.out.println("###############");
        System.out.println("Thanks for playing");
        System.out.println("###############");
    }
}
