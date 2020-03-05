/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

/**
 * A class that represents the Item. An item object has a name, a description
 * and a value (in dollars). A GameCharacter can own items.
 *
 * @author mserrao
 */
public class Item {

    /**
     * Instance variables of an item object. Name represents the title of an
     * item object. Description represents a String that contains a statement
     * about the Item object. Value represents a double value that is the Item's
     * worth in dollars.
     */
    private String name = "thing";
    private String description = " made of atoms";
    private double value = 0;

    //Validator object for methods below.
    Validator isValid = new Validator();

    /**
     * The default constructor of an Item object. Name is initialized to
     * "thing", description is initialized to "made of atoms", and value is
     * initialized to 0 dollars.
     */
    public Item() {
    }

    /**
     * A constructor for an Item object.
     *
     * @param n String representing the name of the Item object.
     * @param desc String representing the description of the Item object.
     * @param val double value representing the value of the Item object.
     */
    public Item(String n, String desc, double val) {
        setName(n);
        setDescription(desc);
        setValue(val);
    }

    /**
     * A method that returns the name of the Item object.
     *
     * @return String representing the name of the Item object.
     */
    public String getName() {
        return name;
    }

    /**
     * A method that sets the name of the Item object to a valid String.
     *
     * @param name String representing the new name of the Item object.
     * @throws IllegalArgumentException if name is an empty string or null
     * object.
     */
    public void setName(String name) throws IllegalArgumentException {
        if (!isValid.isValidString(name)) {
            throw new IllegalArgumentException("Name cannot be an empty string "
                    + "or null object!");
        }
        this.name = name;
    }

    /**
     * A method that returns the description of the Item object.
     *
     * @return String representing the description of the Item object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * A method that sets the description of the Item object to a valid String.
     *
     * @param description String representing the new description of the Item
     * object.
     * @throws IllegalArgumentException if description is an empty string or
     * null object.
     */
    public void setDescription(String description) throws
            IllegalArgumentException {
        if (!isValid.isValidString(description)) {
            throw new IllegalArgumentException("Description cannot be an empty "
                    + "string or null object!");
        }
        this.description = description;
    }

    /**
     * A method that returns the value of an Item object.
     *
     * @return double value representing the value of an Item object.
     */
    public double getValue() {
        return value;
    }

    /**
     * A method that sets the value of an Item object to a new double value.
     *
     * @param value double value representing the new value of the Item object.
     * @throws IllegalArgumentException if the value is not a positive integer
     * @throws IllegalArgumentException if the value is greater than 1000.
     */
    public void setValue(double value) throws IllegalArgumentException,
            IllegalArgumentException {
        if ((!isValid.isPositiveDouble(value))) {
            throw new IllegalArgumentException("Value must be a positive "
                    + "integer!");
        } else if (value > 1000) {
            throw new IllegalArgumentException("There is a max value of $1000."
                    + " Please enter a number less than 1000!");
        }
        this.value = value;
    }
}
