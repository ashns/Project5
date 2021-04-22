package com.example.project5;

import java.io.Serializable;

/**
 * This class is responsible for the array which holds the store orders.
 * Provides client methods: grow, search, add, remove, priny
 * Provides accessor methods: getOrders, getNumOrders
 * @author Ashley Stankovits, Matthew Walker
 */
public class StoreOrders implements Customizable, Serializable {

    private Order orders[] = new Order[4];
    private int orderCount = 0;
    final int NOT_FOUND = -1;

    /**
     * This method grows the orders array when it hits capacity.
     */
    private void grow(){
        Order newOrders[] = new Order[orders.length+4];
        for(int i = 0; i < orderCount; i++){
            newOrders[i] = orders[i];
        }
        orders = newOrders;
    }

    /**
     * This method searches through the orders array based on reference to
     * an order object.
     * @param obj which is the object being searched for.
     * @return an int which contains the index of the place in the array
     * where the object is or -1 if it is not found.
     */
    private int search(Object obj){
        for(int i = 0; i < orderCount; i++){
            if(orders[i].equals(obj)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method adds an order to the
     * orders array.
     * @param obj which is the object being added
     * @return true if the object was added successfully false
     * if otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if(orders.length == orderCount+1){
            grow();
        }
        if(obj instanceof Order) {
            orders[orderCount] = (Order) obj;
            orderCount++;
            return true;
        }
        return false;
    }

    /**
     * This method removes an instance of an order from
     * the orders array based on a reference to an object.
     * @param obj which is the object being removed
     * @return true if the object was successfully removed
     * false if otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        int index = search(obj);
        if(index != NOT_FOUND){
            for(int i = index; i < orderCount; i++){
                orders[i] = orders[i+1];
            }
            orders[orderCount] = null;
            orderCount--;
            return true;
        }
        else
            return false;
    }

    /**
     * This method removes an object from the orders array based
     * on the index it is located at rather than searching for the
     * equivalent object.
     * @param index which is where in the array the object is located.
     * @return true if the object was successfully removed false if
     * otherwise.
     */
    public boolean remove(int index){
        if(index < orderCount){
            for(int i = index; i < orderCount; i++){
                orders[i] = orders[i+1];
            }
            orders[orderCount] = null;
            orderCount--;
            return true;
        }
        else
            return false;
    }

    /**
     * This method converts the items in the array to strings for
     * use in the UI.
     * @return a string which contains all the information from the
     * orders array.
     */
    public String print(){
        String order = "";
        for(int i = 0; i < orderCount; i++){
            order += "Order #" + orders[i].getNumber() + "\n" + orders[i].print() + "\n\n";
        }
        return order;
    }

    /**
     * This method returns the array that contains all the orders from
     * the store.
     * @return an instance of Order array.
     */
    public Order[] getOrders(){
        return orders;
    }

    /**
     * This method is a getter for the total number of orders in
     * store order.
     * @return an integer which contains the number of orders
     * in store order.
     */
    public int getNumOrders(){
        return orderCount;
    }
}
