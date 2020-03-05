/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.ArrayList;

/**
 * A class that represents a TownCharacter object which is a non-controllable
 * GameCharacter object that does not implement battle. TownCharacter is a child
 * class of NonPlayableGameCharacter. A TownCharacter has a name, money,
 * attackPower, defensePower, hitPoints, speed, inventory of Item objects and
 * phrases as instance variables, however, only name, phrases and inventory are
 * manipulated by the program.
 *
 * @author mserrao
 */
public class TownCharacter extends NonPlayableGameCharacter {

    /**
     * A default constructor that creates a TownCharacter object. All instance
     * variables were initialized in the parent GameCharacter class to name
     * "dude"; controllable false; money 0; direction facing FORWARD; attack
     * power and defense power 1; hit points 100; speed 1; phrases to a new
     * ArrayList of String and inventory to a new ArrayList of Items.
     */
    public TownCharacter() {
        super();
    }

    /**
     * A constructor of a TownCharacter object. A String, ArrayList of String
     * and an ArrayList of Item objects are taken as parameters to represent the
     * name, phrases and inventory of the TownCharacter object.
     *
     * @param name String representing the name of the TownCharacter object.
     * @param phrases ArrayList of String representing the phrases of a
     * TownCharacter object.
     * @param items ArrayList of Item objects representing the inventory of a
     * TownCharacter object.
     */
    public TownCharacter(String name, ArrayList<String> phrases,
            ArrayList<Item> items) {
        super();
        setName(name);
        setPhrases(phrases);
        setInventory(items);
    }

    /**
     * A method that returns a String containing the name of a TownCharacter.
     *
     * @return String containing the name of a TownCharacter object.
     */
    @Override
    public String toString() {
        return "Name: " + this.getName();
    }
}
