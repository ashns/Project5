package com.example.project5;
import java.io.Serializable;
/**
 * This class defines menu item which is the parent type for
 * donut and coffee.
 * Provides default constructor for creating instances
 * Provides client methods: itemPrice, equals, toString
 * Provides accessor methods: getQuantity
 * @author Ashley Stankovits, Matthew Walker
 */
public class MenuItem implements Serializable{

    int quantity;

    /**
     * This is the default constructor for instances of menu item
     * @param number which is the quantity of an instance of
     *               menu item.
     */
    public MenuItem(int number){
        quantity = number;
    }

    public MenuItem(){quantity = 0;}

    /**
     * This method initializes the default price of a menu item
     * object.
     * @return a double which is the default item price.
     */
    public double itemPrice(){
        return 0;
    }

    /**
     * This method returns the quantity of an instance of menu item.
     * @return int which is the specified quantity.
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * This method checks if two instances of menuItem are equal.
     * @param obj which is the object being compared.
     * @return true if the items are the same false if otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        MenuItem compare = (MenuItem)obj;
        if(compare.getQuantity() == quantity)
            return true;
        else
            return false;
    }

    /**
     * This method returns a string version of the menu item information
     * corresponding with a specific instance.
     * @return a string which contains the information.
     */
    @Override
    public String toString() {
        return "(" + quantity + ") ";
    }
}
