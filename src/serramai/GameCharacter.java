/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * A class modeling a generic GameCharacter in Maizy's Game. A GameCharacter has
 * a name, money, attack power, defense power, hit points, speed, inventory of
 * item objects, and phrases. A GameCharacter also faces a direction and is
 * either controllable or not controllable.
 *
 * @author mserrao
 */
public abstract class GameCharacter {

    /**
     * Instance variables of a GameCharacter object. name is a String that
     * represents the name of a GameCharacter; controllable is a boolean that
     * represents whether a GameCharacter can be manipulated by the user; money
     * is a double amount representing the wealth a GameCharacter object has;
     * directionFacing is the Direction which a GameCharacter is looking at;
     * phrases is an ArrayList of String which represents what a GameCharacter
     * object can say; inventory is an ArrayList of Items which represents all
     * the Item objects a GameCharacter owns; attackPower is an integer
     * representing a GameCharacter object's ability to do damage; defensePower
     * is an integer representing a GameCharacter object's ability to retain
     * damage; speed is an integer representing a GameCharacter object's ability
     * to attack first and hitPoints is an integer representing a GameCharacter
     * object's health.
     */
    private String name = "dude";
    private Boolean controllable = false;
    private double money = 0;
    private Direction directionFacing = Direction.FORWARD;
    private ArrayList<String> phrases = new ArrayList<String>();
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int attackPower = 1;
    private int defensePower = 1;
    private int hitPoints = 100;
    private int speed = 1;

    /**
     * Validator object used for validation of inputs in methods below.
     */
    Validator isValid = new Validator();

    /**
     * The default constructor for a GameCharacter object. name is set to
     * "dude"; controllable is set to false; money is set to 0; directionFacing
     * is set to FORWARD; phrases is set to a new ArrayList of String; inventory
     * is set to a new ArrayList of Item objects; attackPower is set to 1;
     * defensePower is set to 1; hitPoints is set to 100; and speed is set to 1.
     */
    public GameCharacter() {
    }

    /**
     * A method returning the name of a GameCharacter object.
     *
     * @return String representing the name of the GameCharacter object.
     */
    public String getName() {
        return name;
    }

    /**
     * A method that sets the name of a GameCharacter object to a new name.
     *
     * @param name String representing the new name of the GameCharacter object.
     * @throws IllegalArgumentException if name is an empty String or null
     * object.
     */
    public void setName(String name) throws IllegalArgumentException {
        if (!isValid.isValidString(name)) {
            throw new IllegalArgumentException("Name cannot be an empty string"
                    + " or null object!");
        }
        this.name = name;
    }

    /**
     * A method that returns whether a GameCharacter object can be manipulated
     * by the user or not.
     *
     * @return true if the GameCharacter is controllable and false otherwise.
     */
    public Boolean getControllable() {
        return controllable;
    }

    /**
     * A method that sets the controllability of a GameCharacter object.
     *
     * @param controllable boolean value that represents whether a GameCharacter
     * is controllable.
     */
    public void setControllable(Boolean controllable) {
        this.controllable = controllable;
    }

    /**
     * A method that returns the amount of wealth a GameCharacter has.
     *
     * @return a double value representing the amount of money of the
     * GameCharacter
     */
    public double getMoney() {
        return money;
    }

    /**
     * A method that sets the amount of wealth a GameCharacter has.
     *
     * @param money a double value that represents the amount of money a
     * GameCharacter will own.
     * @throws IllegalArgumentException if money is a negative number.
     * @throws IllegalArgumentException if money is greater than 1000.
     */
    public void setMoney(double money) throws IllegalArgumentException,
            IllegalArgumentException {
        if ((!isValid.isZeroAndGreaterDouble(money))) {
            throw new IllegalArgumentException("Please enter a non-negative "
                    + "integer!");
        } else if (money > 1000) {
            throw new IllegalArgumentException("There isn't enough money in "
                    + "this world for you to have that amount! There is a max "
                    + "of $1000.");
        }
        this.money = money;
    }

    /**
     * A method returning the Direction a GameCharacter is pointing in.
     *
     * @return Direction that the GameCharacter is facing.
     */
    public Direction getDirectionFacing() {
        return directionFacing;
    }

    /**
     * A method that sets the Direction a GameCharacter is pointing towards.
     *
     * @param directionFacing Direction that the GameCharacter is will face.
     */
    public void setDirectionFacing(Direction newDirection) {
        this.directionFacing = newDirection;
    }

    /**
     * A method that returns all the String sentences a GameCharacter can use.
     *
     * @return an ArrayList of String containing all the sentences a
     * GameCharacter can say.
     */
    public ArrayList<String> getPhrases() {
        return phrases;
    }

    /**
     * A method that sets the phrases of a GameCharacter to an ArrayList of
     * String containing new String sentences.
     *
     * @param phrases an ArrayList of String representing the new sentences that
     * a GameCharacter can say.
     */
    public void setPhrases(ArrayList<String> phrases) {
        this.phrases = phrases; 
    }

    /**
     * A method that returns the ArrayList of Item objects that a GameCharacter
     * owns.
     *
     * @return an ArrayList of Item objects that constitute a GameCharacter's
     * inventory.
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * A method that sets a GameCharacter object's inventory to a new ArrayList
     * of Item objects.
     *
     * @param inventory an ArrayList of Item objects that represent the new
     * Items a GameCharacter owns.
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * A method returning a GameCharacter object's ability to damage.
     *
     * @return an integer value representing the GameCharacter object's
     * attackPower.
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * A method that sets a GameCharacter object's ability to do damage.
     *
     * @param attackPower an integer value representing the GameCharacter
     * object's new attackPower.
     * @throws IllegalArgumentException if the new attackPower is negative or is
     * greater than 100.
     */
    public void setAttackPower(int attackPower) throws
            IllegalArgumentException {
        if (!isValid.isZeroAndGreaterInt(attackPower) || (attackPower > 100)) {
            throw new IllegalArgumentException("Please enter an integer between"
                    + " 0 and 100!");
        }
        this.attackPower = attackPower;
    }

    /**
     * A method returning a GameCharacter object's ability to retain.
     *
     * @return an integer value representing the GameCharacter object's
     * defensePower.
     */
    public int getDefensePower() {
        return defensePower;
    }

    /**
     * A method that sets a GameCharacter object's ability to retain damage.
     *
     * @param defensePower an integer value representing the GameCharacter
     * object's new defensePower.
     * @throws IllegalArgumentException if the new defensePower is negative or
     * is greater than 100.
     */
    public void setDefensePower(int defensePower) throws
            IllegalArgumentException {
        if (!isValid.isZeroAndGreaterInt(defensePower)
                || (defensePower > 100)) {
            throw new IllegalArgumentException("Please enter an integer between"
                    + " 0 and 100!");
        }
        this.defensePower = defensePower;
    }

    /**
     * A method returning a GameCharacter object's health.
     *
     * @return an integer value representing the GameCharacter object's
     * hitPoints.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * A method that sets a GameCharacter object's health.
     *
     * @param hitPoints an integer value representing the GameCharacter object's
     * new hitPoints.
     * @throws IllegalArgumentException if the new hitPoints is negative or is
     * greater than 100.
     */
    public void setHitPoints(int hitPoints) throws IllegalArgumentException {
        if (!isValid.isZeroAndGreaterInt(hitPoints) || (hitPoints > 100)) {
            throw new IllegalArgumentException("Please enter an integer between"
                    + " 0 and 100!");
        }
        this.hitPoints = hitPoints;
    }

    /**
     * A method returning a GameCharacter object's ability to attack first.
     *
     * @return an integer value representing the GameCharacter object's speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * A method that sets a GameCharacter object's ability to attack first.
     *
     * @param speed an integer value representing the GameCharacter object's new
     * speed.
     * @throws IllegalArgumentException if the new speed is negative or is
     * greater than 100.
     */
    public void setSpeed(int speed) throws IllegalArgumentException {
        if (!isValid.isZeroAndGreaterInt(speed) || (speed > 100)) {
            throw new IllegalArgumentException("Please enter an integer between"
                    + " 0 and 100!");
        }
        this.speed = speed;
    }

    /**
     * A method that changes the directionFacing of a GameCharacter object to
     * face left relative to their starting direction.
     *
     * @return Direction representing the new directionFacing of the
     * GameCharacter object.
     */
    public Direction turnLeft() {
        int dirNow = this.getDirectionFacing().getValue();
        Direction newDir = Direction.LEFT;
        switch (dirNow) {
            case 1:
                newDir = Direction.LEFT;
                break;
            case 2:
                newDir = Direction.FORWARD;
                break;
            case 3:
                newDir = Direction.RIGHT;
                break;
            case 4:
                newDir = Direction.BACK;
                break;
            default:
                break;
        }
        this.setDirectionFacing(newDir);
        return newDir;
    }

    /**
     * A method that changes the directionFacing of a GameCharacter object to
     * face right relative to their starting direction.
     *
     * @return Direction representing the new directionFacing of the
     * GameCharacter object.
     */
    public Direction turnRight() {
        int dirNow = this.getDirectionFacing().getValue();
        Direction newDir = Direction.LEFT;
        switch (dirNow) {
            case 1:
                newDir = Direction.RIGHT;
                break;
            case 2:
                newDir = Direction.BACK;
                break;
            case 3:
                newDir = Direction.LEFT;
                break;
            case 4:
                newDir = Direction.FORWARD;
                break;
            default:
                break;
        }
        this.setDirectionFacing(newDir);
        return newDir;
    }

    /**
     * A method that takes a double value as input and increases a GameCharacter
     * object's money by that amount. Sets the GameCharacter object's money to
     * 100 if the input plus the GameCharacter's current money is greater than
     * 1000.
     *
     * @param m double value representing the amouunt to increase a
     * GameCharacter object's money by
     * @return double value representing a GameCharacter object's new money
     * @throws IllegalArgumentException if m is not a positive value
     */
    public double gainMoney(double m) throws IllegalArgumentException {
        if (!isValid.isPositiveDouble(m)) {
            throw new IllegalArgumentException("Please enter a positive "
                    + "number!");
        }
        if ((getMoney() + m) > 1000) {
            setMoney(1000);
        } else {
            setMoney(getMoney() + m);
        }
        return this.getMoney();
    }

    /**
     * A method that takes a double value as input and reduces a GameCharacter
     * object's money by that amount.
     *
     * @param m double value representing the amount by which a GameCharacter
     * object's money is reduced.
     * @return double value representing a GameCharacter object's new money.
     * @throws IllegalArgumentException if m is not positive.
     * @throws IllegalArgumentException if m is greater than the GameCharacter
     * object's current money.
     */
    public double loseMoney(double m) throws IllegalArgumentException,
            IllegalArgumentException {
        if (!isValid.isPositiveDouble(m)) {
            throw new IllegalArgumentException("Please enter a positive "
                    + "number!");
        }
        if (m > this.getMoney()) {
            throw new IllegalArgumentException("You can't lose more money than "
                    + "you have!");
        }
        setMoney(getMoney() - m);
        return this.getMoney();
    }

    /**
     * A method that takes an Item object as input and adds it to a
     * GameCharacter object's inventory if the GameCharacter's inventory does
     * not already contain 5 elements.
     *
     * @param i Item object to be added to a GameCharacter's inventory
     * @throws IllegalArgumentException if a GameCharacter's inventory already
     * contains 5 elements.
     */
    public void gainItem(Item i) throws IllegalArgumentException {
        if (this.inventory.size() > 4) {
            throw new IllegalArgumentException("Sorry, your backpack is full!");
        }
        this.inventory.add(i);
    }

    /**
     * A method that takes an Item object as input and removes it from the
     * inventory of a GameCharacter object if it exists in that inventory.
     *
     * @param i Item object to be removed to a GameCharacter's inventory.
     * @throws IllegalArgumentException if a GameCharacter's inventory does not
     * contain any elements.
     * @throws IllegalArgumentException if the Item object does not exist in the
     * GameCharacter's inventory.
     */
    public void loseItem(Item i) throws IllegalArgumentException,
            IllegalArgumentException {
        if (!this.getInventory().isEmpty()) {
            int index = inventory.indexOf(i);
            if (index >= 0) {
                this.inventory.remove(index);
            } else {
                throw new IllegalArgumentException("Please enter an item that "
                        + "exists!");
            }
        } else {
            throw new IllegalArgumentException("You have no more items!");
        }
    }

    /**
     *
     * A method that takes an Item object as input and adds it to the inventory
     * of a GameCharacter object if the GameCharacter has less money than the
     * Item object's value and if the GameCharacter's inventory does not already
     * contain 5 Item objects. Sets the GameCharacter's money to the current
     * money minus the value of the Item object added to the inventory if the
     * addition was successful.
     *
     * @param i Item object to be added to a GameCharacter's inventory.
     * @throws IllegalArgumentException if GameCharacter's inventory already
     * contains 5 elements.
     * @throws IllegalArgumentException if GameCharacter's money is less than
     * the value of the Item object.
     */
    public void buyItem(Item i) throws IllegalArgumentException,
            IllegalArgumentException {
        double cost = i.getValue();
        if (this.getMoney() < cost) {
            throw new IllegalArgumentException("You are too poor. You can't "
                    + "afford that!");
        }
        gainItem(i);
        this.setMoney(getMoney() - cost);
    }

    /**
     * A method that takes an Item object as input and removes it from the
     * inventory of the GameCharacter object if the Item object exists within
     * that inventory. Sets the money of the GameCharacter to the current money
     * plus the value of the Item object. If the sum of the current money and
     * the Item object exceeds 1000, the money is set to 1000.
     *
     * @param i Item object to be removed from a GameCharacter's inventory.
     */
    public void sellItem(Item i) {
        double cost = i.getValue();
        if ((this.getMoney() + cost) > 1000) {
            setMoney(1000);
        } else {
            setMoney(this.getMoney() + cost);
        }
        loseItem(i);
    }

    /**
     * A method to check if an object is the same GameCharacter object. Returns
     * true if the object is not null and points to the same reference in
     * memory; or if controllable, name, inventory size, money, attackPower,
     * defensePower, speed, hitPoints, and phrases size are all the same.
     *
     * @param o Object that needs to be checked as the same GameCharacter
     * object.
     * @return true if object is not null and points to the same reference in
     * memory; or if controllable, name, inventory size, money, attackPower,
     * defensePower, speed, hitPoints, and phrases size are all the equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (o instanceof GameCharacter) {
            GameCharacter g = (GameCharacter) o;
            return ((this.controllable == g.getControllable())
                    && (this.name.equals(g.getName()))
                    && (this.inventory.size() == g.getInventory().size())
                    && (this.money == g.getMoney())
                    && (this.attackPower == g.getAttackPower())
                    && (this.defensePower == g.getDefensePower())
                    && (this.speed == g.getSpeed())
                    && (this.hitPoints == g.getHitPoints())
                    && (this.phrases.size() == g.getPhrases().size()));
        } else {
            return false;
        }
    }

    /**
     * An abstract method that displays information about the GameCharacter
     * object child classes.
     */
    @Override
    public abstract String toString();

    /**
     * A method that searches through a GameCharacter objects phrases and
     * returns a random phrase as a String.
     *
     * @return String that represents a random phrase from a GameCharacter
     * object.
     */
    public String speak() {
        ArrayList<String> phrases = this.getPhrases();
        int randomPhrase = (int) Math.floor((Math.random()) * phrases.size());

        return phrases.get(randomPhrase);
    }
}
