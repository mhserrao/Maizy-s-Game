/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.ArrayList;

/**
 * A class that represents an EnemyCharacter object. EnemyCharacter is a child
 * class of NonPlayableCharacter and implements the Battle interface. An
 * EnemyCharacter has a name, money, attackPower, defensePower, hitPoints,
 * speed, inventory of Item objects and phrases. The EnemyCharacter also faces a
 * Direction. EnemyCharacters are not controllable.
 *
 * @author mserrao
 */
public class EnemyCharacter extends NonPlayableGameCharacter implements Battle {

    /**
     * A default constructor that creates an EnemyCharacter object. All instance
     * variables were initialized in the parent GameCharacter class to name
     * "dude"; controllable false; money 0; direction facing FORWARD; attack
     * power and defense power 1; hit points 100; speed 1; phrases to a new
     * ArrayList of String and inventory to a new ArrayList of Items.
     */
    public EnemyCharacter() {
        super();
    }

    /**
     * A constructor that creates an EnemyCharacter object with parameters for
     * name, money, inventory, phrases, attackPower, defensePower, and speed.
     *
     * @param name String that represents the name of the EnemyCharacter.
     * @param mon double value that represents the money of the EnemyCharacter.
     * @param items ArrayList of Items that represent the EnemyCharacter's
     * inventory.
     * @param phrases ArrayList of String that represent the EnemyCharacter's
     * phrases.
     * @param att integer value that represents the EnemyCharacter's attack
     * power.
     * @param def integer value that represents the EnemyCharacter's defense
     * power.
     * @param speed integer value that represents the EnemyCharacter's speed.
     */
    public EnemyCharacter(String name, double mon, ArrayList<Item> items,
            ArrayList<String> phrases, int att, int def, int speed) {
        super();
        setName(name);
        setMoney(mon);
        setInventory(items);
        setPhrases(phrases);
        setAttackPower(att);
        setDefensePower(def);
        setSpeed(speed);
    }

    /**
     * An overridden abstract method from the interface Battle that reduces
     * (does damage) to the hitPoints of another GameCharacter. The damage
     * inflicted on the other GameCharacter depends on the attackPower of the
     * EnemyCharacter and the defensePower of the other GameCharacter.
     *
     * @param g GameCharacter that the EnemyCharacter does damage to by reducing
     * the GameCharacter's hitPoints.
     */
    @Override
    public void attack(GameCharacter g) {
        int playerAttack = g.getAttackPower();
        int playerDefense = g.getDefensePower();
        int playerHP = g.getHitPoints();
        int enemyAttack = this.getAttackPower();
        int damage = enemyAttack - playerDefense;
        if (damage >= 0) {
            if (damage > g.getHitPoints()) {
                g.setHitPoints(0);
            } else {
                g.setHitPoints(g.getHitPoints() - damage);
            }
        } else if (damage < 0) {
            if (enemyAttack == 0 || damage == 0) {
                System.out.println("\n" + this.getName() + " took one "
                        + "hit point by instilling fear and acting like a "
                        + "crazy person!");
            }
            g.setHitPoints(g.getHitPoints() - 1);
        }
    }

    /**
     * A method that returns the name, money, attackPower, defensePower,
     * hitPoints and speed of an EnemyCharacter object as a String.
     *
     * @return String literal containing the name, money, attackPower,
     * defensePower, hitPoints and speed of an EnemyCharacter object.
     */
    @Override
    public String toString() {
        return String.format("Name: '%s', Money: '%.2f', Attack Power: '%d', "
                + "Defense Power: '%d', Hit Points '%d', Speed: '%d'",
                this.getName(), this.getMoney(), this.getAttackPower(),
                this.getDefensePower(), this.getHitPoints(), this.getSpeed());
    }
}
