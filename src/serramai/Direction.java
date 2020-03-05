/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

/**
 * An enumeration class containing the Directions FORWARD, RIGHT, BACK and LEFT
 * as well as their associated values. These represent the direction that a
 * GameCharacter may face or turn to.
 *
 * @author mserrao
 */
public enum Direction {

    /**
     * Direction objects that represent the direction a GameCharacter can face
     * and their associated value for specific methods to manipulate these
     * Direction objects.
     */
    FORWARD(1),
    RIGHT(2),
    BACK(3),
    LEFT(4);

    /**
     * Instance variable of a Direction object. Represents the integer value
     * associated with a Direction object so that methods can manipulate the
     * Direction object.
     */
    private final int value;

    /**
     * A constructor that creates a Direction object.
     *
     * @param i the integer value associated with a Direction object.
     */
    Direction(int i) {
        this.value = i;
    }

    /**
     * A method that returns the integer associated with a Direction object.
     *
     * @return an integer value that is associated with a Direction object.
     */
    public int getValue() {
        return value;
    }
}
