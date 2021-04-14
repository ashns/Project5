package com.example.project5;

/**
 * This is the given interface Customizable which contained the
 * framework for add and remove methods which were required
 * in certain other classes.
 * Provides client methods: add and remove
 * @author Ashley Stankovits, Matthew Walker
 */
public interface Customizable {
    /**
     * This is the framework for the add method.
     * @param obj which is the object being added
     * @return boolean which is true if the add was successful
     * and false if otherwise.
     */
    boolean add(Object obj);

    /**
     * This is the framework for the remove method.
     * @param obj which is the object being removed
     * @return boolean which is true if the remove was
     * successful and false if otherwise.
     */
    boolean remove(Object obj);
}
