package com.example.project5;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * This class is responsible for the array which holds the user order.
 * Provides client methods: grow, search, add, remove, print,
 * Provides accessor methods: getNumber, getItems, orderPrice
 * @author Ashley Stankovits, Matthew Walker
 */
public class Order implements Customizable, Serializable {

    final int NOT_FOUND = -1;
    final double SALES_TAX = 0.06625;
    private MenuItem items[] = new MenuItem[4];
    private int itemCount = 0;
    transient DecimalFormat usd = new DecimalFormat("0.00");
    private static int number = 0;
    private int thisNumber = number+1;
    private final double NJ_TAX = 1.06625;


    /**
     * This method grows the items array when it hits capacity.
     */
    private void grow(){
        MenuItem newItem[] = new MenuItem[items.length+4];
        for(int i = 0; i < itemCount; i++){
            newItem[i] = items[i];
        }
        items = newItem;
    }


    /**
     * This method returns the current number of items.
     * @return int with the current number of items.
     */
    public int getItemCount(){
        return itemCount;
    }


    /**
     * This method returns the order number.
     * @return int which contains the order number.
     */
    public int getNumber()
    {
        return thisNumber;
    }

    /**
     * This method searches through the items array based on reference to
     * an object.
     * @param obj which is the object being searched for.
     * @return an int which contains the index of the place in the array
     * where the object is or -1 if it is not found.
     */
    private int search(Object obj){
        for(int i = 0; i < itemCount; i++){
            if(items[i].equals(obj)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method adds an instance of coffee or donut to the
     * items array.
     * @param obj which is the object being added
     * @return true if the object was added successfully false
     * if otherwise.
     */
    @Override
    public boolean add(Object obj){
        if(items.length == itemCount+1){
            grow();
        }
        if(obj instanceof Donut || obj instanceof Coffee){
            if(obj instanceof Donut) {
                items[itemCount] = (Donut) obj;
            }
            else if(obj instanceof Coffee) {
                items[itemCount] = (Coffee) obj;
            }
            itemCount++;

            return true;
        }
        return false;
    }

    /**
     * This method updates the order number
     * to increment appropriately.
     */
    public void updateNumber(){
        number++;
    }

    /**
     * This method removes an instance of coffee or donut from
     * the items array based on a reference to an object.
     * @param obj which is the object being removed
     * @return true if the object was successfully removed
     * false if otherwise.
     */
    @Override
    public boolean remove(Object obj){
        int index = search(obj);
        if(index != NOT_FOUND){
            for(int i = index; i < itemCount; i++){
                items[i] = items[i+1];
            }
            items[itemCount] = null;
            itemCount--;
            return true;
        }
        else
        return false;
    }

    /**
     * This method removes an object from the items array based
     * on the index it is located at rather than searching for the
     * equivalent object.
     * @param index which is where in the array the object is located.
     * @return true if the object was successfully removed false if
     * otherwise.
     */
    public boolean remove(int index){
        if(index < itemCount){
            for(int i = index; i < itemCount; i++){
                items[i] = items[i+1];
            }
            items[itemCount] = null;
            itemCount--;
            return true;
        }
        else
            return false;
    }

    /**
     * This method converts the items in the array to strings for
     * use in the UI.
     * @return a string which contains all the information from the
     * items array.
     */
    public String print(){
        String order = "";
        for(int i = 0; i < itemCount; i++){
            order += items[i].toString();
        }
        order += "\n---------------\nTotal Price: $" + usd.format(orderPrice() * NJ_TAX) + "\n---------------------------------";
        return order;
    }

    /**
     * This method returns the array that contains all the items from
     * a specified order.
     * @return an instance of MenuItems array.
     */
    public MenuItem[] getItems(){
        return items;
    }

    /**
     * This method calculates the total price for all items in the order.
     * @return a double which contains the total price.
     */
    public double orderPrice(){
        double price = 0;
        for(int i = 0; i < itemCount; i++){
            price += items[i].itemPrice();
        }
        return price;
    }

    /**
     * Calculates the amount of money added via sales tax
     * @return the total taxed amount
     */
    public double calculateSalesTax(){
        return orderPrice() * SALES_TAX;
    }
}
