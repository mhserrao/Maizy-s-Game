/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serramai;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class representing methods that check inputs according to specific criteria
 * required by the program. Methods include but are not limited to checking for
 * positive integers, valid strings, and positive double values.
 *
 * @author mserrao
 */
public class Validator {

    public Validator() {
    }

    /**
     * A method to check if an object is of class Integer.
     *
     * @param value the object that needs to be checked as an Integer object.
     * @return boolean true if value is of class Integer or false if not.
     */
    public boolean isInt(Object value) {
        boolean isInt = false;
        if (value instanceof Integer) {
            isInt = true;
        }
        return isInt;
    }

    /**
     * A method to check if an object is of class Integer and is greater than
     * zero.
     *
     * @param value the object that needs to be checked as an Integer object
     * greater than zero.
     * @return boolean true if value is of class Integer and is greater than
     * zero or false if not.
     */
    public boolean isPositiveInt(Object value) {
        boolean isPositiveInt = false;
        if (value instanceof Integer) {
            int newVal = (int) value;
            if (newVal > 0) {
                isPositiveInt = true;
            }
        }
        return isPositiveInt;
    }

    /**
     * A method to check if an object is of class Integer and is zero or greater
     * than zero.
     *
     * @param value the object that needs to be checked as an Integer object
     * that is zero or greater than zero.
     * @return boolean true if value is of class Integer and is zero or greater
     * than zero or false if not.
     */
    public boolean isZeroAndGreaterInt(Object value) {
        boolean isInt = false;
        if (value instanceof Integer) {
            int newVal = (int) value;
            if (newVal > -1) {
                isInt = true;
            }
        }
        return isInt;
    }

    /**
     * A method to check if an object is of class Double.
     *
     * @param value the object that needs to be checked as a Double object.
     * @return boolean true if value is of class Double or false if not.
     */
    public boolean isDouble(Object value) {
        boolean isDouble = false;
        if (value instanceof Double) {
            isDouble = true;
        }
        return isDouble;
    }

    /**
     * A method to check if an object is of class Double and is greater than
     * zero.
     *
     * @param value the object that needs to be checked as a positive Double
     * object.
     * @return boolean true if value is of class Double and is greater than zero
     * or false if not.
     */
    public boolean isPositiveDouble(Object value) {
        boolean isPositiveDouble = false;
        if (value instanceof Double) {
            double newVal = (double) value;
            if (newVal > 0) {
                isPositiveDouble = true;
            }
        }
        return isPositiveDouble;
    }

    /**
     * A method to check if an object is of class Double and is zero or greater
     * than zero.
     *
     * @param value the object that needs to be checked as a Double object and
     * is zero or greater.
     * @return boolean true if value is of class Double and is zero or greater
     * than zero, or false if not.
     */
    public boolean isZeroAndGreaterDouble(Object value) {
        boolean isPositiveDouble = false;
        if (value instanceof Double) {
            double newVal = (double) value;
            if (newVal >= 0) {
                isPositiveDouble = true;
            }
        }
        return isPositiveDouble;
    }

    /**
     * A method to check if an object is of class String which if it is, checks
     * if a String is null or empty.
     *
     * @param value the object that needs to be checked as a String object that
     * is neither null or empty.
     * @return boolean true if value is of class String and is not null or
     * empty. Else, returns false.
     */
    public boolean isValidString(Object value) {
        boolean isString = false;
        if (value instanceof String) {
            String newVal = (String) value;
            if (!newVal.trim().equals("")) {
                isString = true;
            }
        }
        return isString;
    }

    //methods below use Scanners
    /**
     * A method that takes a Scanner object as input, and checks if the input is
     * of integer type and is either 1 or 2. If the input is not 1 or 2, the
     * method continuously reprompts the user until the input is integer 1 or 2.
     *
     * @param scan Scanner object that takes user input.
     * @return integer 1 or 2.
     */
    public int isOneOrTwo(Scanner scan) {
        int value = 0;
        boolean isInt = false;
        do {
            try {
                value = scan.nextInt();
                if (value != 1 && value != 2) {
                    throw new IllegalArgumentException("Please enter 1 or 2!");
                }
                isInt = true;
            } catch (InputMismatchException x) {
                System.out.println("Please enter a number!");
                scan.nextLine();
            } catch (Exception x) {
                System.out.println(x.getMessage());
                scan.nextLine();
            }
        } while (!isInt);
        return value;
    }

    /**
     * A method that takes a Scanner object as input, and checks if the input is
     * an integer value between 1 inclusive and 6 inclusive. If the input is not
     * a value between 1 and 6 inclusive, the method continuously reprompts the
     * user until the input is of integer data type between 1 and 6 inclusive.
     *
     * @param scan Scanner object that takes user input.
     * @return integer value between 1 inclusive and 6 inclusive.
     */
    public int isOption(Scanner scan) {
        int value = 0;
        boolean isInt = false;
        do {
            try {
                value = scan.nextInt();
                if (value > 6 || value < 1) {
                    throw new IllegalArgumentException("Please enter a value "
                            + "between 1 and 6!");
                }
                isInt = true;
            } catch (InputMismatchException x) {
                System.out.println("Please enter a number!");
                scan.nextLine();
            } catch (Exception x) {
                System.out.println(x.getMessage());
                scan.nextLine();
            }
        } while (!isInt);
        return value;
    }

    /**
     * A method that takes a Scanner object as input, and checks if the input is
     * of integer data type. If the input is not of integer data type, the
     * method continuously reprompts the user until the input is of integer data
     * type.
     *
     * @param scan Scanner object that takes user input.
     * @return integer value.
     */
    public int checkInt(Scanner scan) {
        int value = 0;
        boolean isInt = false;
        do {
            try {
                value = scan.nextInt();
                isInt = true;
            } catch (InputMismatchException x) {
                System.out.println("Please enter an integer!");
                scan.nextLine();
            } catch (Exception x) {
                System.out.println("Please enter an integer!");
                scan.nextLine();
            }
        } while (!isInt);
        return value;
    }

    /**
     * A method that takes a Scanner object as input, and checks if the input is
     * of integer data type and is positive. If the input is not of integer data
     * type or the input is not positive, the method continuously reprompts the
     * user until the input is of integer data type and is a positive value.
     *
     * @param scan Scanner object that takes user input.
     * @return positive integer value.
     */
    public int checkPositiveInt(Scanner scan) {
        int value = 0;
        boolean isPositive = false;
        do {
            try {
                value = scan.nextInt();
                if (value <= 0) {
                    throw new IllegalArgumentException("Please enter a positive"
                            + " integer!");
                }
                isPositive = true;
            } catch (InputMismatchException x) {
                System.out.println("Please enter an integer!");
                scan.nextLine();
            } catch (Exception x) {
                System.out.println(x.getMessage());
                scan.nextLine();
            }
        } while (!isPositive);
        return value;
    }

    /**
     * A method that takes a Scanner object as input, and checks if the input is
     * of double data type and is positive. If the input is not of double data
     * type or the input is not positive, the method continuously reprompts the
     * user until the input is of double data type and is a positive value.
     *
     * @param scan Scanner object that takes user input.
     * @return positive double value.
     */
    public double checkPositiveDouble(Scanner scan) {
        double value = 0;
        boolean isPositive = false;
        do {
            try {
                value = scan.nextDouble();
                if (value <= 0) {
                    throw new IllegalArgumentException("Please enter a positive"
                            + " number!");
                }
                isPositive = true;
            } catch (InputMismatchException x) {
                System.out.println("Please enter a number!");
                scan.nextLine();
            } catch (Exception x) {
                System.out.println(x.getMessage());
                scan.nextLine();
            }
        } while (!isPositive);
        return value;
    }

    /**
     * A method that takes a Scanner object as input, and checks if the input is
     * of double data type. If the input is not of double data type, the method
     * continuously reprompts the user until the input is of double data type.
     *
     * @param scan Scanner object that takes user input.
     * @return double value.
     */
    public double checkDouble(Scanner scan) {
        double value = 0;
        boolean isDouble = false;
        do {
            try {
                value = scan.nextDouble();
                isDouble = true;
            } catch (InputMismatchException x) {
                System.out.println("Please enter a number!");
                scan.nextLine();
            } catch (Exception x) {
                System.out.println("Please enter a number!");
                scan.nextLine();
            }
        } while (!isDouble);
        return value;
    }

    /**
     * A method that takes a Scanner object as input and checks if the input is
     * a valid String. If the input is not a valid String (i.e. is null or
     * empty), the method continuously reprompts the user until the input is a
     * valid String.
     *
     * @param scan Scanner object that takes user input.
     * @return a valid String.
     */
    public String checkString(Scanner scan) {
        String value = "";
        boolean isString = false;
        do {
            try {
                value = scan.nextLine();
                if (value == null) {
                    throw new NullPointerException("The string can't be a null"
                            + " object!");
                } else if (value.trim().equals("")) {
                    throw new IllegalArgumentException("Your string can't be "
                            + "empty!");
                }
                isString = true;
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }
        } while (!isString);
        return value;
    }

    /**
     * A method that takes a Scanner object as input and checks if the input is
     * a String literal that is either "yes" or "no" (case is ignored). If not,
     * the method continuously reprompts the user until the input is either
     * "yes" or "no".
     *
     * @param scan Scanner object that takes user input.
     * @return the user input which is the String literal "yes" or String
     * literal "no" (ignore case).
     */
    public String checkYesOrNo(Scanner scan) {
        String value = "";
        boolean isString = false;
        do {
            try {
                value = scan.nextLine();
                isValidString(value);
                if (!value.equalsIgnoreCase("yes")
                        && !value.equalsIgnoreCase("no")) {
                    throw new IllegalArgumentException("Please enter yes "
                            + "or no!");
                }
                isString = true;
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }
        } while (!isString);
        return value;
    }

    /**
     * A method that takes a Scanner object and a GameCharacter as input and
     * checks if the input is an double value that is less than or equal to the
     * GameCharacter's money. If the input does not satisfy this criteria, the
     * method continuously reprompts the user until the input is an double value
     * that is less than or equal to the GameCharacter's money.
     *
     * @param scan Scanner object that takes user input.
     * @param me GameCharacter object whose money needs to be checked for
     * reference.
     * @return a double value that is less than or equal to the GameCharacter's
     * money.
     */
    public double checkBet(Scanner scan, GameCharacter me) {
        double value = 0;
        boolean isOkay = false;
        double myMoney = me.getMoney();
        do {
            try {
                value = scan.nextDouble();
                if (value <= 0) {
                    throw new IllegalArgumentException("Please enter a positive"
                            + " number!");
                } else if (value > myMoney) {
                    throw new IllegalArgumentException(String.format("You have "
                            + "%.2f$! You can't bet more than you have!",
                            myMoney));
                }
                isOkay = true;
            } catch (InputMismatchException x) {
                System.out.println("Please enter a number!");
                scan.nextLine();
            } catch (Exception x) {
                System.out.println(x.getMessage());
                scan.nextLine();
            }
        } while (!isOkay);
        return value;
    }

    /**
     * A method that takes a Scanner object as input and in integer as a maximum
     * number that the user can input (starting at 1).Checks if the input is an
     * integer value between 1 inclusive and the maximum number inclusive. If
     * the input is not a value between 1 and the maximum number inclusive, the
     * method continuously reprompts the user until the input is of integer data
     * type between 1 and the maximum number inclusive.
     *
     * @param scan Scanner object that takes user input.
     * @return integer value between 1 inclusive and the maximum number
     * inclusive.
     */
    public int isNumberOption(Scanner scan, int size) {
        int value = 0;
        boolean isInt = false;
        do {
            try {
                value = scan.nextInt();
                if (value > size || value < 1) {
                    throw new IllegalArgumentException("Please enter a value "
                            + "between 1 and " + size + "!");
                }
                isInt = true;
            } catch (InputMismatchException x) {
                System.out.println("Please enter a number!");
                scan.nextLine();
            } catch (Exception x) {
                System.out.println(x.getMessage());
                scan.nextLine();
            }
        } while (!isInt);
        return value;
    }
}
