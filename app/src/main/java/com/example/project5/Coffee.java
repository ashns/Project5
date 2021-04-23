package com.example.project5;

import java.io.Serializable;

/**
 * This class defines the menu item of type coffee to hold all the
 * coffee specific information.
 * Provides default constructor for creating instances
 * Provides client methods: toString, equals, add, remove, itemprice,
 * addonprice
 * @author Ashley Stankovits, Matthew Walker
 */
public class Coffee extends MenuItem implements Customizable, Serializable {

    final int SIZE_SHORT = 1;
    final int SIZE_TALL = 2;
    final int SIZE_GRANDE = 3;
    final int SIZE_VENTI = 4;
    final double SHORT_PRICE = 1.99;
    final double TALL_PRICE = 2.49;
    final double GRANDE_PRICE = 2.99;
    final double VENTI_PRICE = 3.49;
    final double ADDON_COST = .20;
    boolean hasAddOns = false;
    final int CREAM = 0;
    final int SYRUP = 1;
    final int MILK = 2;
    final int CARAMEL = 3;
    final int WHIPPED_CREME = 4;

    int size;
    String[] addOns = new String[5];

    /**
     * This method is the constructor for a coffee instance of menuitem
     * @param size which is the size of the coffee
     * @param quantity which is the number of this exact type of coffee
     */
    public Coffee(int size, int quantity) {
        super(quantity);
        this.size = size;
    }

    /**
     * This method adds a coffee addon to a specific coffee instance
     * @param obj which is the addon being adding
     * @return a boolean which returns true of the addon was successfully added
     * and false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof String){
            String temp = (String)obj;
            if(temp.equals("cream")){
                addOns[CREAM] = "cream";
                return true;
            }
            else if(temp.equals("syrup")){
                addOns[SYRUP] = "syrup";
                return true;
            }
            else if(temp.equals("milk")){
                addOns[MILK] = "milk";
                return true;
            }
            else if(temp.equals("caramel")){
                addOns[CARAMEL] = "caramel";
                return true;
            }
            else{
                addOns[WHIPPED_CREME] = "whipped creme";
                return true;
            }
        }
        return false;
    }

    /**
     * This method removes an addon from coffee.
     * @param obj which is the addon being removed
     * @return a boolean which returns true if the addon was successfully
     * removed and false if otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof String){
            String temp = (String)obj;
            for(int i = 0; i < addOns.length; i++){
                if(addOns[i].equals(temp)){
                    addOns[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method converts a coffee instance into a string.
     * @return a string which contains the coffee quantity, size
     * and addons
     */
    @Override
    public String toString(){
        String message = "(" + quantity + ") " + " " ;
        switch (size){
            case SIZE_SHORT:
                message += "short coffee";
                break;
            case SIZE_TALL:
                message += "tall coffee";
                break;
            case SIZE_GRANDE:
                message += "grande coffee";
                break;
            case SIZE_VENTI:
                message += "venti coffee";
                break;
            default:
                message += "bagel";
        }

        if(quantity > 1 || quantity <= 0) message += "s";
        for(int i = 0; i < 5; i++) {
            String tempMessage = "";
            if ((addOns[i] == null)) {
                continue;
            } else {
                if (hasAddOns == false) {
                    tempMessage += " with " + addOns[i];
                    hasAddOns = true;
                } else {
                    tempMessage += ", " + addOns[i];
                }
            }
           message += tempMessage;
        }
        return message + "\n";
    }

    /**
     * This method calculates the cost of a specific coffee
     * based on the size and addons.
     * @return a double that is the price of this specific
     * instance of coffee.
     */
    @Override
    public double itemPrice() {
        double price = 0;
        switch(size){
            case SIZE_SHORT:
                price = SHORT_PRICE + addOnPrice();
                break;
            case SIZE_TALL:
                price = TALL_PRICE + addOnPrice();
                break;
            case SIZE_GRANDE:
                price =  GRANDE_PRICE + addOnPrice();
                break;
            case SIZE_VENTI:
                price = VENTI_PRICE + addOnPrice();
                break;
            default: price = 0;
        }
        return price * quantity;
    }

    /**
     * This method calculated the cost for addons of a coffee instance.
     * It is called by the itemPrice method to add this cost to the base cost.
     * @return a double which is the cost for just the addons
     */
    public double addOnPrice() {
        int j = 0;
        for (int i = 0; i < addOns.length; i++) {
            if (addOns[i] != null) {
                j++;
            }
        }
        return j * ADDON_COST;
    }

    /**
     * This method checks if 2 instances of coffee are the same.
     * @param obj which is the object being compared.
     * @return true if both objects are the same false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && !(obj instanceof Donut)){
            Coffee compare = (Coffee) obj;
            if(super.equals(compare)){
                if(size == compare.size){
                    for(int i = 0; i <= WHIPPED_CREME; i++){
                        if (addOns[i] != null && compare.addOns[i] != null) {
                            if (!addOns[i].equals(compare.addOns[i])) {
                                return false;
                            }
                        }
                        else return false;
                    }
                    return true;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }
}

