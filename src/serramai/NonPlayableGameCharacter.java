/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.ArrayList;

/**
 * An abstract class representing a GameCharacter which cannot be controlled
 * (i.e. controllable instance variable is set to false). This class is a child
 * class of the GameCharacter class and is a parent class to the EnemyCharacter
 * and TownCharacter classes.
 *
 * @author mserrao
 */
public abstract class NonPlayableGameCharacter extends GameCharacter {

    /**
     * A default constructor for a NonPlayableGameCharacter object. All instance
     * variables were initialized in the parent GameCharacter class to name
     * "dude"; controllable false; money 0; direction facing FORWARD; attack
     * power and defense power 1; hit points 100; speed 1; phrases to a new
     * ArrayList of String and inventory to a new ArrayList of Items.
     */
    public NonPlayableGameCharacter() {
        super();
    }
}
