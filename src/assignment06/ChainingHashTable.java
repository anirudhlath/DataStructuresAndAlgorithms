package assignment06;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 12/06/2021 - 8:00 PM
 */
public class ChainingHashTable implements Set<String>{

    private LinkedList<String>[] storage;
    private HashFunctor hasher;
    private int size;
    private int collisionCount;

    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor) {
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        hasher = functor;
        size = 0;
        collisionCount = 0;

    }

    public int getCollisionCount() {
        return collisionCount;
    }

    private int convertHash(int hash) {
        return Math.abs(hash % storage.length);
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(String item) {
        int arrIndex = convertHash(hasher.hash(item)); // Find the index based on the hash of the item.

        if(storage[arrIndex] != null) {
            if (storage[arrIndex].contains(item)) {
                return false;
            } else {
                storage[arrIndex].add(item);
                collisionCount++;
                size++;
            }
        } else {
            storage[arrIndex] = new LinkedList<>();
            storage[arrIndex].add(item);
            size++;
        }

        return true;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        boolean hasChanged = false;
        for (String s :
                items) {
            if (add(s)) {
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        storage = (LinkedList<String>[]) new LinkedList[storage.length];
        size = 0;
        collisionCount = 0;

    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(String item) {
        int arrIndex = convertHash(hasher.hash(item));
        if (storage[arrIndex].contains(item)) {
            return true;
        }
        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {
        int count = 0;
        for (String s :
                items) {
            if (contains(s)) {
                count++;
            }
        }
        return count == items.size();
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(String item) {
        int arrIndex = convertHash(hasher.hash(item));

        if (storage[arrIndex].contains(item)) {
            storage[arrIndex].remove(item);
            size--;
            return true;
        }
        return false;

    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends String> items) {
        boolean hasChanged = false;
        for (String s :
                items) {
            if (remove(s)) {
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }
}
