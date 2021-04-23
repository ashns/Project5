package com.example.project5;

import java.io.Serializable;

/**
 * This class defines the menu item of type donut to hold all the
 * donut specific information.
 * Provides default constructor for creating instances
 * Provides client methods: itemPrice, toString, equals
 * Provides accessor methods: getFlavor, getDonutType
 * @author Ashley Stankovits, Matthew Walker
 */
public class Donut extends MenuItem implements Serializable{

    final int YEAST_DONUT = 1;
    final int CAKE_DONUT = 2;
    final int DONUT_HOLE = 3;
    final double YEAST_PRICE = 1.39;
    final double CAKE_PRICE = 1.59;
    final double HOLE_PRICE = 0.33;
    String flavor;
    int donutType;


    /**
     * This is the default constructor for instances of donut.
     * @param number which is the quantity of a specific donut type.
     * @param taste which is the flavor of the donut
     * @param type which is the type of donut (yeast, cake, etc)
     */
    public Donut(int number, String taste, int type){
        super(number);
        flavor = taste;
        donutType = type;
    }

    public Donut(){
        super();
    }

    /**
     * This method calculates the price for a specific donut instance.
     * @return a double which is the calculated price.
     */
    @Override
    public double itemPrice() {
        double price = 0;
        switch(donutType){
            case YEAST_DONUT:
                price = YEAST_PRICE;
                break;
            case CAKE_DONUT:
                price = CAKE_PRICE;
                break;
            case DONUT_HOLE:
                price =  HOLE_PRICE;
                break;
            default: price = 0;
        }
        return price * quantity;
    }

    /**
     * This method is a getter for the donut type of an instance
     * of donut.
     * @return an int which corresponds to a specific donut type.
     */
    public int getDonutType(){
        return donutType;
    }

    /**
     * This method is a getter for the donut flavor of an instance
     * of donut.
     * @return a String which contains the donut flavor.
     */
    public String getFlavor(){
        return flavor;
    }

    /**
     * This method converts an instance of donut to a string.
     * @return a string which contains all the information regarding
     * an instance of donut such as the flavor quantity and type.
     */
    @Override
    public String toString(){
        String message = super.toString() + flavor + " " ;
        switch (donutType){
            case YEAST_DONUT:
                message += "yeast donut";
                break;
            case CAKE_DONUT:
                message += "cake donut";
                break;
            case DONUT_HOLE:
                message += "donut hole";
                break;
            default:
                message += "bagel";
        }
        if(quantity > 1 || quantity <= 0) message += "s";
        return message + "\n";
    }

    /**
     * This method checks if two instances of donut are the same.
     * @param obj which is the donut object being compared.
     * @return boolean which is true if the two objects are equal
     * false if otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj != null && !(obj instanceof Coffee)) {
            Donut compare = (Donut) obj;
            if (super.equals(compare)) {
                if (compare.getDonutType() == donutType && compare.getFlavor().equals(flavor))
                    return true;
                else return false;
            } else
                return false;
        }
        else
            return false;
    }


}
