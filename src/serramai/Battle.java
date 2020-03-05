/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

/**
 * An interface that holds the abstract method attack. GameCharacters that can
 * participate in battles can implement this interface.
 *
 * @author mserrao
 */
public interface Battle {

    /**
     * A method in which a GameCharacter is attacked (it's health is reduced in
     * some capacity).
     *
     * @param g the GameCharacter that is being attacked.
     */
    void attack(GameCharacter g);
}
