/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.ArrayList;

/**
 * A class that represents a child class of the GameCharacter class and that can
 * be manipulated by the user (controllable is set to true).
 *
 * @author mserrao
 */
public class PlayableGameCharacter extends GameCharacter implements Battle {

    /**
     * The default constructor of a PlayableGameCharacter object. Passes all
     * previous instance variables to the GameCharacter constructor while
     * setting controllable to true.
     */
    public PlayableGameCharacter() {
        super();
        setControllable(true);
    }

    /**
     * A constructor of a PlayableGameCharacter object. Takes a String,
     * Direction and double value as input to set the name, directionFacing and
     * money of the PlayableGameCharacter object. Also sets controllable to
     * true.
     *
     * @param name String representing the name of the PlayableGameCharacter
     * object.
     * @param dir Direction representing the directionFacing of the
     * PlayableGameCharacter object.
     * @param mon double value representing the money of the
     * .PlayableGameCharacter object
     */
    public PlayableGameCharacter(String name, Direction dir, double mon) {
        super();
        setName(name);
        setControllable(true);
        setDirectionFacing(dir);
        setMoney(mon);
    }

    /**
     * A method that takes another GameCharacter object as input and reduces the
     * GameCharacter object's hitPoints by the PlayableGameCharacter object's
     * attack minus the defense if that difference is greater than zero. If that
     * difference is not greater than zero, the GameCharacter object's hitPoints
     * are reduced by 1.
     *
     * @param g GameCharacter object whose hitPoints are to be reduced.
     */
    @Override
    public void attack(GameCharacter g) {
        int enemyAttack = g.getAttackPower();
        int enemyDefense = g.getDefensePower();
        int enemyHP = g.getHitPoints();
        int myAttack = this.getAttackPower();
        int damage = myAttack - enemyDefense;
        if (damage > 0) {
            if (damage > g.getHitPoints()) {
                g.setHitPoints(0);
            } else {
                g.setHitPoints(g.getHitPoints() - damage);
            }
        } else if (damage <= 0) {
            if (myAttack == 0 || damage == 0) {
                System.out.println("\nYou took one hit point by instilling"
                        + " fear and acting like a crazy person!");
            }
            g.setHitPoints(g.getHitPoints() - 1);
        }
    }

    /**
     * A method that returns a String that contains information about the
     * PlayableGameCharacter object. Information includes name, money,
     * attackPower, defensePower, hitPoints, and speed.
     *
     * @return String containing information about the PlayableGameCharacter
     * object. name, money, attackPower, defensePower, hitPoints, and speed
     * values are made into a String.
     */
    @Override
    public String toString() {
        return String.format("Name: '%s', Money: '%.2f', Attack Power: '%d', "
                + "Defense Power: '%d', Hit Points '%d', Speed: '%d'",
                this.getName(), this.getMoney(), this.getAttackPower(),
                this.getDefensePower(), this.getHitPoints(), this.getSpeed());
    }

}
