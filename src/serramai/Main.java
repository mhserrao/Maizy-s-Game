/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Main method of the serrama package representing Maizy's Game. In this
 * game, the user creates their own player by setting their name and battle
 * stats. Every player has an inventory of Items. There are also EnemyCharacters
 * with which the player can battle with and gain and lose money from. There are
 * also TownCharacters with whom the player can converse with. There are special
 * events that can happen if the player chooses to change direction.
 *
 * @author mserrao
 */
public class Main {

    /**
     * Method that contains all the logic of the Maizy's Game Program.
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<EnemyCharacter> enemies = makeEnemies();
        ArrayList<TownCharacter> townsPeople = makeTownsPeople();

        Scanner scan = new Scanner(System.in);
        Validator isValid = new Validator();

        //make your player
        PlayableGameCharacter me = makePlayableGameCharacter();
        System.out.println(me);

        //tell the user what items they have
        System.out.println(itemCheck(me));

        //give the user options as to what to do
        int whatToDo = 1;
        do {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1 - Change direction (something might "
                    + "happen...)");
            System.out.println("2 - Talk to a towns person");
            System.out.println("3 - Battle ");
            System.out.println("4 - Check Stats");
            System.out.println("5 - Check Items");
            System.out.println("6 - Quit");
            whatToDo = isValid.isOption(scan);
            switch (whatToDo) {
                case 1:
                    changeDirection(me);
                    System.out.println("\nYou are now facing "
                            + me.getDirectionFacing());
                    randomEvent(me);
                    break;
                case 2:
                    System.out.println("\nOkay, let's find someone!");
                    talkToTownsPeople(townsPeople, me);
                    break;
                case 3:
                    if (me.getHitPoints() != 0) {
                        implementBattle(me, chooseEnemyToBattle(enemies));
                        System.out.println("\nYour stats: ");
                        System.out.println(me);
                    } else {
                        System.out.println("\nYou have no health!");
                    }
                    break;
                case 4:
                    System.out.println(me);
                    break;
                case 5:
                    System.out.println(itemCheck(me));
                    break;
                case 6:
                    System.out.println("\nThanks for playing! Good luck in your"
                            + " future endeavors!");
                    System.exit(0);
                default:
                    break;
            }
        } while (whatToDo != 6);
    }

    /**
     * A method that takes a PlayableGameCharacter and EnemyCharacter as inputs,
     * checks which object's speed is higher, and implements attack one each
     * object based on that speed.
     *
     * @param g PlayableGameCharacter involved in the battle.
     * @param e EnemyGameCharacter involved in the battle.
     * @return String literal "This round is over."
     */
    public static String battle(PlayableGameCharacter g, EnemyCharacter e) {
        if (e.getHitPoints() > 0 && g.getHitPoints() > 0) {
            if (checkWhosFaster(g, e) == -1) { //enemy is faster
                System.out.println("\n" + e.getName() + " attacked first!");
                e.attack(g);
                if (g.getHitPoints() > 0) {
                    g.attack(e);
                } else {
                    System.out.println("You have no more health!");
                }
            } else { //player is faster
                System.out.println("\n" + g.getName() + " attacked first!");
                g.attack(e);
                if (e.getHitPoints() > 0) {
                    e.attack(g);
                } else {
                    System.out.println(e.getName() + " has no more health!");
                }
            }
            System.out.println("\nThe opponent is down to " + e.getHitPoints()
                    + " HP!");
            System.out.println("You are down to " + g.getHitPoints() + " HP!");
        } else {
            System.out.println("\nYou can't battle " + e.getName() + " right "
                    + "now!");
            if (g.getHitPoints() == 0) {
                System.out.println("You don't have enough hit points!");
            }
            if (e.getHitPoints() == 0) {
                System.out.println("\n" + e.getName() + " doesn't have enough "
                        + "hit points!");
            }
        }
        return "This round is over.";
    }

    /**
     * A method that takes a PlayableGameCharacter and EnemyCharacter as inputs,
     * and returns the object whose speed is faster. If both speeds are
     * equivalent, chooses PlayableCharacter or EnemyCharacter at random.
     *
     * @param g PlayableGameCharacter involved in the battle.
     * @param e EnemyGameCharacter involved in the battle.
     * @return -1 if the EnemyCharacter is faster and 1 if the
     * PlayableGameCharacter is faster.
     */
    public static int checkWhosFaster(PlayableGameCharacter g, 
            EnemyCharacter e) {
        int playerSpeed = g.getSpeed();
        int enemySpeed = e.getSpeed();
        if (playerSpeed < enemySpeed) { //enemy is faster
            return -1;
        } else if (playerSpeed > enemySpeed) { //player is faster
            return 1;
        } else {
            int randomNum = (int) Math.round(Math.random());
            if (randomNum == 0) { //enemy is faster
                return -1;
            } else {
                return 1;  //player is faster
            }
        }
    }

    /**
     * A method that either adds money to the GameCharacter if the other
     * GameCharacter's hitPoints are 0 or removes money from the GameCharacter
     * if the GameCharacter's hitPoints are 0.
     *
     * @param g GameCharacter that represents the PlayableGameCharacter.
     * @param e GameCharacter that represents the EnemyCharacter.
     * @param playerBetMoney double value representing the amount that the
     * PlayableGameCharacter will lose if it's hitPoints are 0.
     */
    public static void exchangeMoney(GameCharacter g, GameCharacter e, 
            double playerBetMoney) {

        if (e.getHitPoints() == 0) { //if enemy loses
            g.gainMoney(playerBetMoney);
            System.out.printf("You are gaining %.2f$!", playerBetMoney);
            System.out.printf("\nYour total money is now %.2f$.", g.getMoney());
        } else if (g.getHitPoints() == 0) { //if player loses
            g.loseMoney(playerBetMoney);
            System.out.printf("You are losing %.2f$!", playerBetMoney);
            System.out.printf("\nYour total money is now %.2f$.", g.getMoney());
        }
    }

    /**
     * A method that loops through an ArrayList of EnemyCharacter objects and
     * returns a random EnemyCharacter to battle.
     *
     * @param enemies an ArrayList of EnemyCharacter objects to loop through.
     * @return EnemyCharacter chosen from an ArrayList of EnemyCharacters at
     * random.
     */
    public static EnemyCharacter chooseEnemyToBattle(ArrayList<EnemyCharacter> 
            enemies) {
        int randomEnemy = (int) Math.floor((Math.random()) * enemies.size());
        EnemyCharacter newEnemy = enemies.get(randomEnemy);
        if (newEnemy.getHitPoints() == 0) {
            System.out.println("\n" + newEnemy.getName() + " was restored to "
                    + "health and is now ready to battle!");
            newEnemy.setHitPoints(100);
        }
        System.out.println("\n" + newEnemy.getName() + " is ready to battle!");
        System.out.println("\n" + newEnemy);
        return newEnemy;
    }

    /**
     * A method that takes a PlayableGameCharacter and an EnemyCharacter as
     * input, prompts the user to enter amount to bet and makes the
     * PlayableGameCharacter and EnemyCharacter continue to attack each other
     * until either object's hitPoints are 0 or the user decides to quit. At the
     * end of the battle, the winner exchangesMoney with the loser.
     *
     * @param g PlayableGameCharacter involved in the battle.
     * @param e EnemyCharacter involved in the battle.
     */
    public static void implementBattle(PlayableGameCharacter g,
            EnemyCharacter e) {
        Scanner scan = new Scanner(System.in);
        Validator isValid = new Validator();

        System.out.println("\nDo you want to continue with this battle? "
                + "(yes/no)");
        String letsBattle = isValid.checkYesOrNo(scan);
        if (letsBattle.equalsIgnoreCase("yes") && g.getMoney() != 0) {
            System.out.printf("\nHow much money do you want to bet? "
                    + "(You have %.2f$)", g.getMoney());
            double betMoney = isValid.checkBet(scan, g);
            boolean continueBattle = true;
            while (((g.getHitPoints() != 0) && (e.getHitPoints() != 0))
                    && (continueBattle)) {
                System.out.println(battle(g, e));
                if (e.getHitPoints() == 0 || g.getHitPoints() == 0) {
                    break;
                }
                System.out.println("\nWhat do you want to do? ");
                System.out.println("1 - Attack");
                System.out.println("2 - Quit battle (you lose all your money)");
                int answer = isValid.isOneOrTwo(scan);
                if (answer == 1) {
                    System.out.println("\nOkay, let's go!");
                } else if (answer == 2) {
                    quitBattle(g, betMoney);
                    continueBattle = false;
                    System.out.println("\n" + e.getName()
                            + " won this battle.");
                }
            }
            if (e.getHitPoints() == 0) {
                System.out.println("\nYou are the winner!");
                System.out.println("\n" + e.getName() + ":");
                System.out.println(e.speak() + "\n");
            } else if (g.getHitPoints() == 0) {
                System.out.println("\n" + e.getName() + " is the winner!");
                System.out.println("\n" + g.getName() + ":");
                System.out.println(g.speak() + "\n");
            }
            exchangeMoney(g, e, betMoney);
        } else if (letsBattle.equalsIgnoreCase("yes") && g.getMoney() == 0) {
            System.out.println("\nYou don't have enough money!");
        } else if (letsBattle.equalsIgnoreCase("no")) {
            System.out.println("\nOkay, let's save your energies for "
                    + "another day!");
        }
    }

    /**
     * A method that removes an amount of money from a PlayableGameCharacter
     * object's money.
     *
     * @param g PlayableGameCharacter that will lose it's money.
     * @param betMoney double value representing the amount of money to be lost.
     */
    public static void quitBattle(PlayableGameCharacter g, double betMoney) {
        g.loseMoney(betMoney);
        System.out.printf("\nYou have conceded this fight. You have "
                + "lost %.2f$.", betMoney);
    }

    /**
     * A method that creates an ArrayList of miscellaneous Item objects to be
     * used for any GameCharacter.
     *
     * @return an ArrayList of miscellaneous Item objects.
     */
    public static ArrayList<Item> makeItems() {
        Item playerItem0 = new Item("Milk", "from a cow", 50);
        Item playerItem1 = new Item("Black Tea", "best drink ever", 30);
        Item playerItem2 = new Item("Coffee", "energy galore", 20);
        Item playerItem3 = new Item("Green Tea", "for a longer life", 40);
        Item playerItem4 = new Item("Water", "....mmmmm tasty", 10);

        ArrayList<Item> playerItems = new ArrayList<Item>();
        playerItems.add(playerItem0);
        playerItems.add(playerItem1);
        playerItems.add(playerItem2);
        playerItems.add(playerItem3);
        playerItems.add(playerItem4);

        return playerItems;
    }

    /**
     * A method that creates an ArrayList of miscellaneous Strings to be used
     * for a PlayableGameCharacter.
     *
     * @return an ArrayList of miscellaneous Strings.
     */
    public static ArrayList<String> makePlayerPhrases() {
        String playerPhrase1 = "Why did the hipster drown? "
                + "\n...Because he skated on the ice before it was cool.";
        String playerPhrase2 = "Energizer Bunny arrested! "
                + "\n...Charged with battery.";
        String playerPhrase3 = "How does Moses make coffee? "
                + "\n...Hebrews it.";
        String playerPhrase4 = "What do you give a sick lemon? "
                + "\n...Lemonaid.";
        String playerPhrase5 = "It's hard to explain puns to kleptomaniacs... "
                + "\nBecause they take everything literally.";

        ArrayList<String> playerPhrases = new ArrayList<String>();
        playerPhrases.add(playerPhrase1);
        playerPhrases.add(playerPhrase2);
        playerPhrases.add(playerPhrase3);
        playerPhrases.add(playerPhrase4);
        playerPhrases.add(playerPhrase5);

        return playerPhrases;
    }

    /**
     * A method that creates an ArrayList of miscellaneous Strings to be used
     * for a TownCharacter.
     *
     * @return an ArrayList of miscellaneous Strings.
     */
    public static ArrayList<String> makeTownsPeoplePhrases() {
        String townsPeoplePhrase1 = "Why couldn't the lifeguard save the "
                + "hippie? \n...Because he was too far out man.";
        String townsPeoplePhrase2 = "You can't run through a camp site. "
                + "\n...You can only ran because it's past tents.";
        String townsPeoplePhrase3 = "I was just looking up at my ceiling. "
                + "Not sure if it's the best ceiling in the world "
                + "\n...But it's definitely up there.";
        String townsPeoplePhrase4 = "When you have a bladder infection "
                + "\n...Urine trouble.";
        String townsPeoplePhrase5 = "Have you heard of the band 1023MB? "
                + "\n...They haven't gotten a gig yet.";

        ArrayList<String> townsPeoplePhrases = new ArrayList<String>();
        townsPeoplePhrases.add(townsPeoplePhrase1);
        townsPeoplePhrases.add(townsPeoplePhrase2);
        townsPeoplePhrases.add(townsPeoplePhrase3);
        townsPeoplePhrases.add(townsPeoplePhrase4);
        townsPeoplePhrases.add(townsPeoplePhrase5);

        return townsPeoplePhrases;
    }

    /**
     * A method that creates an ArrayList of miscellaneous Strings to be used
     * for an EnemyCharacter.
     *
     * @return an ArrayList of miscellaneous Strings.
     */
    public static ArrayList<String> makeEnemyPhrases() {
        String enemyPhrase1 = "I used to hate facial hair, "
                + "\n...But then it grew on me.";
        String enemyPhrase2 = "R.I.P. boiled water, "
                + "\n...You will be mist.";
        String enemyPhrase3 = "When does a joke become a dad joke? "
                + "\n...When it becomes apparent.";
        String enemyPhrase4 = "Why does Waldo wear stripes? "
                + "\n...Because he doesn't want to be spotted.";
        String enemyPhrase5 = "What's the best thing about elevator jokes? "
                + "\n...They work on so many levels.";

        ArrayList<String> enemyPhrases = new ArrayList<String>();
        enemyPhrases.add(enemyPhrase1);
        enemyPhrases.add(enemyPhrase2);
        enemyPhrases.add(enemyPhrase3);
        enemyPhrases.add(enemyPhrase4);
        enemyPhrases.add(enemyPhrase5);

        return enemyPhrases;
    }

    /**
     * A method that creates an ArrayList of various EnemyCharacter objects.
     *
     * @return an ArrayList of various EnemyCharacter objects.
     */
    public static ArrayList<EnemyCharacter> makeEnemies() {
        ArrayList<EnemyCharacter> enemies = new ArrayList<EnemyCharacter>();
        ArrayList<String> enemyPhrases = makeEnemyPhrases();
        ArrayList<Item> enemyItems = makeItems();

        EnemyCharacter enemy0 = new EnemyCharacter("Toucan Sam", 100,
                enemyItems, enemyPhrases, 10, 20, 30);
        EnemyCharacter enemy1 = new EnemyCharacter("Kool-Aid Man", 200,
                enemyItems, enemyPhrases, 20, 30, 40);
        EnemyCharacter enemy2 = new EnemyCharacter("Wendy's Wendy", 300,
                enemyItems, enemyPhrases, 10, 10, 50);
        EnemyCharacter enemy3 = new EnemyCharacter("Aunt Jemima", 500,
                enemyItems, enemyPhrases, 40, 20, 100);
        EnemyCharacter enemy4 = new EnemyCharacter("Mr. Clean", 700,
                enemyItems, enemyPhrases, 60, 60, 5);

        enemies.add(enemy0);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        enemies.add(enemy4);

        return enemies;
    }

    /**
     * A method that creates an ArrayList of various TownCharacter objects.
     *
     * @return an ArrayList of various TownCharacter objects.
     */
    public static ArrayList<TownCharacter> makeTownsPeople() {
        ArrayList<TownCharacter> townsPeople = new ArrayList<TownCharacter>();
        ArrayList<String> townsPeoplePhrases = makeTownsPeoplePhrases();
        ArrayList<Item> townsPeopleItems = makeItems();

        TownCharacter townPerson0 = new TownCharacter("Peter",
                townsPeoplePhrases, townsPeopleItems);
        TownCharacter townPerson1 = new TownCharacter("Lois",
                townsPeoplePhrases, townsPeopleItems);
        TownCharacter townPerson2 = new TownCharacter("Stewie",
                townsPeoplePhrases, townsPeopleItems);
        TownCharacter townPerson3 = new TownCharacter("Chris",
                townsPeoplePhrases, townsPeopleItems);
        TownCharacter townPerson4 = new TownCharacter("Brian",
                townsPeoplePhrases, townsPeopleItems);

        townsPeople.add(townPerson0);
        townsPeople.add(townPerson1);
        townsPeople.add(townPerson2);
        townsPeople.add(townPerson3);
        townsPeople.add(townPerson4);

        return townsPeople;
    }

    /**
     * A method that creates a TownCharacter named Kevin O'Leary with special
     * phrases specific to the method implementBuyItem().
     *
     * @return a TownCharacter named Kevin O'Leary.
     */
    public static TownCharacter makeKevin() {
        ArrayList<String> kevinPhrases = new ArrayList<String>();
        ArrayList<Item> townsPeopleItems = makeItems();

        String kevinPhrase0 = "Do you want to buy something?";
        String kevinPhrase1 = "Okay, what do you want?";
        String kevinPhrase2 = "It's gonna cost you. Here you go.";
        String kevinPhrase3 = "You can't buy that! You don't have "
                + "enough money!";
        String kevinPhrase4 = "Pleasure doing business with you.";

        kevinPhrases.add(kevinPhrase0);
        kevinPhrases.add(kevinPhrase1);
        kevinPhrases.add(kevinPhrase2);
        kevinPhrases.add(kevinPhrase3);
        kevinPhrases.add(kevinPhrase4);

        TownCharacter kevin = new TownCharacter("Kevin O'Leary",
                kevinPhrases, townsPeopleItems);

        return kevin;
    }

    /**
     * A method that creates a TownCharacter named Keanu Reeves with special
     * phrases specific to the method implementSellItem().
     *
     * @return a TownCharacter named Keanu Reeves.
     */
    public static TownCharacter makeKeanu() {
        ArrayList<String> keanuPhrases = new ArrayList<String>();
        ArrayList<Item> townsPeopleItems = makeItems();

        String keanuPhrase0 = "Do you want to sell something?";
        String keanuPhrase1 = "Okay, what do you want to sell?";
        String keanuPhrase2 = "Here's your cash.";
        String keanuPhrase3 = "Pleasure doing business with you.";

        keanuPhrases.add(keanuPhrase0);
        keanuPhrases.add(keanuPhrase1);
        keanuPhrases.add(keanuPhrase2);
        keanuPhrases.add(keanuPhrase3);

        TownCharacter keanu = new TownCharacter("Keanu Reeves",
                keanuPhrases, townsPeopleItems);

        return keanu;
    }

    /**
     * A method prompts the user to enter a name, money, attackPower,
     * defensePower, speed of a PlayableGameCharacter that they will manipulate
     * in Maizy's Game.
     *
     * @return PlayableGameCharacter to be used in Maizy's Game Program.
     */
    public static PlayableGameCharacter makePlayableGameCharacter() {
        Validator isValid = new Validator();
        Scanner scan = new Scanner(System.in);

        Direction dir = Direction.LEFT;
        String name = "Fella";
        double monies = 0;
        PlayableGameCharacter me = new PlayableGameCharacter(name, dir, monies);

        //welcome and set name
        System.out.println("*************************************************");
        System.out.println("*            WELCOME TO MAIZY'S GAME!           *");
        System.out.println("*************************************************");
        System.out.println("\n");

        //set items
        ArrayList<Item> playerItems = makeItems();
        ArrayList<String> playerPhrases = makePlayerPhrases();

        me.setInventory(playerItems);
        me.setPhrases(playerPhrases);

        System.out.println("What is your name?");
        name = isValid.checkString(scan);
        me.setName(name);

        //set money
        boolean checkMonies = true;
        do {
            try {
                System.out.println("\nHow much money do you have in your "
                        + "wallet? (Max of $1000)");
                monies = isValid.checkPositiveDouble(scan);
                me.setMoney(monies);
                checkMonies = false;
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }
        } while (checkMonies);

        //set attack stats
        boolean checkAttack = true;
        int newAttack = 0;
        do {
            try {
                System.out.println("\nWhat is your attack power? (Max of 100)");
                newAttack = isValid.checkInt(scan);
                me.setAttackPower(newAttack);
                checkAttack = false;
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }
        } while (checkAttack);

        //set defense stats
        boolean checkDefense = true;
        int newDefense = 0;
        do {
            try {
                System.out.println("\nWhat is your defense power? ("
                        + "Max of 100)");
                newDefense = isValid.checkInt(scan);
                me.setDefensePower(newDefense);
                checkDefense = false;
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }
        } while (checkDefense);

        //set defense stats
        boolean checkSpeed = true;
        int newSpeed = 0;
        do {
            try {
                System.out.println("\nWhat is your speed? (Max of 100)");
                newSpeed = isValid.checkInt(scan);
                me.setSpeed(newSpeed);
                checkSpeed = false;
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }
        } while (checkSpeed);
        return me;
    }

    /**
     * A method that returns the Item objects contained within a GameCharacter
     * object's inventory.
     *
     * @param g GameCharacter object whose inventory needs to be checked.
     * @return String literal containing the names of the Item objects contained
     * in the GameCharacter's inventory.
     */
    public static String itemCheck(GameCharacter g) {
        ArrayList<Item> itemsOwned = g.getInventory();
        String itemString = "\nYou have: ";
        if (g.getInventory().isEmpty()) {
            itemString += "nothing! Your bag is empty.";
        }
        for (int i = 0; i < itemsOwned.size(); i++) {
            itemString += "\n" + itemsOwned.get(i).getName() + " - "
                    + itemsOwned.get(i).getDescription() + ".";
        }
        return itemString;
    }

    /**
     * A method that takes a GameCharacter as input and makes it change
     * directionFacing either to the left or to the right depending on the user
     * input.
     *
     * @param g GameCharacter whose directionFacing will be changed.
     */
    public static void changeDirection(GameCharacter g) {
        Validator isValid = new Validator();
        Scanner scan = new Scanner(System.in);
        int go = 0;

        System.out.println("\nYou are facing " + g.getDirectionFacing() + ".");
        System.out.println("\nWhat do you want to do?");
        System.out.println("1 - Turn LEFT");
        System.out.println("2 - Turn RIGHT");
        go = isValid.isOneOrTwo(scan);
        if (go == 1) {
            g.turnLeft();
        } else if (go == 2) {
            g.turnRight();
        }
    }

    /**
     * A method that takes an ArrayList of TownCharacters and a GameCharacter as
     * input. This method chooses a random TownCharacter in that ArrayList of
     * TownCharacter objects and chooses and displays a random phrase of that
     * TownCharacter's phrases. The user is then prompted if they would like to
     * respond during which a GameCharacter's phrases will also be chosen at
     * random.
     *
     * @param townsPeople ArrayList of TownCharacters to choose one
     * TownCharacter from.
     * @param g GameCharacter to whom the TownCharacter is speaking to.
     */
    public static void talkToTownsPeople(ArrayList<TownCharacter> townsPeople,
            GameCharacter g) {
        Validator isValid = new Validator();
        Scanner scan = new Scanner(System.in);

        int randomPerson = (int) Math.floor((Math.random())
                * townsPeople.size());
        TownCharacter person = townsPeople.get(randomPerson);

        System.out.println("\n" + person.getName() + " wants to talk to you:");
        System.out.println(person.speak());

        System.out.println("\nDo you want to say anything? (yes/no)");
        String talk = isValid.checkYesOrNo(scan);
        if (talk.equalsIgnoreCase("yes")) {
            System.out.println("\n" + g.getName() + ":");
            System.out.println(g.speak());
        }
    }

    /**
     * A method that takes a GameCharacter as input which is now subject to a
     * random event. The method creates a random integer from 0 to 9. If the
     * integer is 0, the method called is findItem(). If the integer is 1, the
     * method called is randomBattle(), If the integer is 2, the method called
     * is loseWallet(). If the integer is 3, the method called is findMoney().
     * If the integer is 4, the method called is stealItem(). If the integer is
     * 5, the method called is implementBuyItem(). If the integer is 6, the
     * method called is implementSellItem(). For integers 7, 8, and 9, nothing
     * happens.
     *
     * @param g GameCharacter which will be subject to a random event.
     */
    public static void randomEvent(PlayableGameCharacter g) {
        int randomEvent = (int) Math.floor((Math.random()) * 10);
        switch (randomEvent) {
            case 0:
                findItem(g);
                break;
            case 1:
                if (g.getHitPoints() > 0) {
                    randomBattle(g);
                } else {
                    System.out.println("\nYou found a magic potion! Let's "
                            + "drink it!\n\nGlug Glug Glug....");
                    System.out.println("\nYour health is restored to 100!");
                    g.setHitPoints(100);
                }
                break;
            case 2:
                if (g.getMoney() != 0) {
                    loseWallet(g);
                }
                break;
            case 3:
                if (g.getMoney() < 1000) {
                    findMoney(g);
                }
                break;
            case 4:
                if (!g.getInventory().isEmpty()) {
                    stealItem(g);
                }
                break;
            case 5:
                implementBuyItem(g);
                break;
            case 6:
                implementSellItem(g);
                break;
            default:
                break;
        }
    }

    /**
     * A method that takes a PlayableGameCharacter as input and adds a random
     * Item object to its inventory.
     *
     * @param g PlayableGameCharacter to whose inventory to add a random Item
     * object.
     */
    public static void findItem(PlayableGameCharacter g) {
        ArrayList<Item> randomItems = makeItems();
        int randomItemIndex = (int) Math.floor((Math.random())
                * randomItems.size());
        Item randomItem = randomItems.get(randomItemIndex);

        System.out.println("\nHey, you found " + randomItem.getName() + "!");

        try {
            g.gainItem(randomItem);
        } catch (IllegalArgumentException x) {
            System.out.println(x.getMessage());
        }
    }

    /**
     * A method that takes a PlayableGameCharacter as input and uses the method
     * implementBattle() on that PlayableGameCharacter.
     *
     * @param g PlayableGameCharacter which will participate in the
     * implementBattle() method.
     */
    public static void randomBattle(PlayableGameCharacter g) {
        System.out.println("\nRANDOM BATTLE!");
        implementBattle(g, chooseEnemyToBattle(makeEnemies()));
        System.out.println("\nYour stats: ");
        System.out.println(g);
    }

    /**
     * A method that takes a PlayableGameCharacter as input and sets that
     * PlayableGameCharacter's money to 0.
     *
     * @param g PlayableGameCharacter whose money will be set to 0.
     */
    public static void loseWallet(PlayableGameCharacter g) {
        System.out.println("\nOh no! Swiper swiped your wallet! You lost "
                + "all your money...");
        System.out.println("You should've said Swiper no swiping!");
        g.setMoney(0);
    }

    /**
     * A method that takes a PlayableGameCharacter as input and adds 100 to that
     * PlayableGameCharacter's money.
     *
     * @param g PlayableGameCharacter whose money will increase by 100.
     */
    public static void findMoney(PlayableGameCharacter g) {
        System.out.println("\nHey! You found a $100 bill!");
        g.gainMoney(100);
    }

    /**
     * A method that takes a PlayableGameCharacter as input from which a random
     * Item object from their inventory will be removed.
     *
     * @param g PlayableGameCharacter whose random Item object will be removed
     * from their inventory.
     */
    public static void stealItem(PlayableGameCharacter g) {
        ArrayList<Item> items = g.getInventory();
        if (items.size() > 0) {
            int randomItemIndex = (int) Math.floor((Math.random())
                    * items.size());
            Item randomItem = items.get(randomItemIndex);
            g.loseItem(randomItem);
            System.out.println("\nSwiper stole an item...");
            System.out.println("You're missing " + randomItem.getName());
        }
    }

    /**
     * A method in which a PlayableGameCharacter is taken as input where the
     * user can choose to purchase an Item object from Kevin O'Leary. If the
     * user enters yes, an exchange is made where the user obtains an Item but
     * removes the Item's value from their PlayableGameCharacter's money. If the
     * inventory of the PlayableGameCharacter already contains 5 elements, this
     * transaction is void.
     *
     * @param g PlayableGameCharacter which will gain an Item object and lose
     * money.
     */
    public static void implementBuyItem(PlayableGameCharacter g) {
        TownCharacter kevin = makeKevin();
        Validator isValid = new Validator();
        Scanner scan = new Scanner(System.in);

        System.out.println("\nKevin O'Leary wants to talk to you:");
        System.out.println("\nKevin: " + kevin.getPhrases().get(0)
                + " (yes/no)");

        String buy = isValid.checkYesOrNo(scan);
        if (buy.equalsIgnoreCase("yes")) {
            System.out.println("\nKevin: " + kevin.getPhrases().get(1));

            ArrayList<Item> kevinsItems = kevin.getInventory();
            String itemString = "\nItems: ";
            for (int i = 0; i < kevinsItems.size(); i++) {
                itemString += String.format("\n%d-%s(%.2f$)", (i + 1),
                        kevinsItems.get(i).getName(),
                        kevinsItems.get(i).getValue());
            }

            System.out.println(itemString);
            System.out.println("6-None of the above");
            boolean wantToBuy = true;

            do {
                try {
                    int iwant = isValid.isOption(scan) - 1;
                    if (iwant == 5) {
                        wantToBuy = true;
                    } else {
                        Item kevinSells = kevinsItems.get(iwant);
                        if ((iwant != 5)
                                && (g.getMoney() >= kevinSells.getValue())) {
                            g.buyItem(kevinSells);
                            wantToBuy = true;
                            System.out.println("\nKevin: "
                                    + kevin.getPhrases().get(2));
                        } else if (g.getMoney() < kevinSells.getValue()) {
                            System.out.println("\nKevin: "
                                    + kevin.getPhrases().get(3));
                            wantToBuy = false;
                        }
                    }
                } catch (Exception x) {
                    System.out.println(x.getMessage());
                }
            } while (!wantToBuy);
        }
        System.out.println("\nKevin: " + kevin.getPhrases().get(4));
    }

    /**
     * A method in which a PlayableGameCharacter is taken as input where the
     * user can choose to give an Item object to Keanu Reeves. If the user
     * enters yes, an exchange is made where the user gives an Item object and
     * adds the Item's value to their PlayableGameCharacter's money.
     *
     * @param g PlayableGameCharacter which will lose an Item object and gain
     * money.
     */
    public static void implementSellItem(PlayableGameCharacter g) {
        if (!g.getInventory().isEmpty()) {
            TownCharacter keanu = makeKeanu();
            Validator isValid = new Validator();
            Scanner scan = new Scanner(System.in);

            System.out.println("\nKeanu Reeves wants to talk to you:");
            System.out.println("\nKeanu: " + keanu.getPhrases().get(0)
                    + " (yes/no)");

            String buy = isValid.checkYesOrNo(scan);
            ArrayList<Item> playerItems = g.getInventory();

            if (buy.equalsIgnoreCase("yes") && playerItems.size() > 0) {
                System.out.println("\nKeanu: " + keanu.getPhrases().get(1));

                String itemString = "\nItems: ";
                for (int i = 0; i < playerItems.size(); i++) {
                    itemString += String.format("\n%d-%s(%.2f$)", (i + 1),
                            playerItems.get(i).getName(),
                            playerItems.get(i).getValue());
                }

                System.out.println(itemString);
                System.out.println((playerItems.size() + 1)
                        + "-None of the above");

                boolean yes = true;

                do {
                    int iwant = isValid.isNumberOption(scan,
                            playerItems.size() + 1) - 1;
                    try {
                        if (iwant == playerItems.size()) {
                            yes = false;
                        } else {
                            Item keanuBuys = playerItems.get(iwant);
                            if ((iwant >= 0) && (iwant < playerItems.size())) {
                                g.sellItem(keanuBuys);
                                System.out.println("\nKeanu: "
                                        + keanu.getPhrases().get(2));
                                yes = false;
                            }
                        }
                    } catch (Exception x) {
                        System.out.println(x.getMessage());
                    }
                } while (yes);

            } else if (buy.equalsIgnoreCase("yes") && playerItems.isEmpty()) {
                System.out.println("\nYou don't have any items!");
            }
            System.out.println("\nKeanu: " + keanu.getPhrases().get(3));
        }
    }
}
