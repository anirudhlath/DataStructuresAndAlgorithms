package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

    Node root;
    int size;


    public BinarySearchTree() {
        root = null;
        size = 0;
    }


    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean add(T item) {
        if (item != null) {
            Node currentNode;
            currentNode = root;
            while (currentNode != null) {
                if (currentNode.data.compareTo(item) < 0) {
                    currentNode = currentNode.left;
                } else if (currentNode.data.compareTo(item) > 0) {
                    currentNode = currentNode.right;
                } else {
                    return false;
                }
            }
            currentNode = new Node(item);
            size++;
        } else {
            throw new NullPointerException("The item that you passed in is null.");
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
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean addAll(Collection<? extends T> items) {
        boolean hasChanged = false;
        for (T t: items) {
            if (add(t)) {
                hasChanged = true;
                size++;
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
        root = null;
        root.left = null;
        root.right = null;
        size = 0;

    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean contains(T item) {
        if (item != null && size > 0) {
            Node currentNode;
            currentNode = root;
            while (currentNode.data != null) {
                if (currentNode.data.compareTo(item) < 0) {
                    currentNode = currentNode.left;
                } else if (currentNode.data.compareTo(item) > 0) {
                    currentNode = currentNode.right;
                } else {
                    return true;
                }
            }
        } else {
            throw new NullPointerException("The item you passed in is null.");
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
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean containsAll(Collection<? extends T> items) {
        int count = 0;
        for (T t: items) {
            if (contains(t)) {
                count++;
            }
        }

        if (count == items.size()) {
            return true;
        }

        return false;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T first() throws NoSuchElementException {
        Node currentNode = root;
        if (size != 0) {
            while(currentNode.left != null) {
                currentNode = currentNode.left;
            }
        } else {
            throw new NoSuchElementException("The set is empty!");
        }
        return currentNode.data;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T last() throws NoSuchElementException {
        Node currentNode = root;
        if (size != 0) {
            while(currentNode.right != null) {
                currentNode = currentNode.right;
            }
        } else {
            throw new NoSuchElementException("The set is empty!");
        }
        return currentNode.data;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean remove(T item) {
        if (item != null) {
            Node currentNode; // Do we need a previous node too?
            currentNode = root;
            while (currentNode != null) {
                if (currentNode.data.compareTo(item) < 0) {
                    currentNode = currentNode.left;
                } else if (currentNode.data.compareTo(item) > 0) {
                    currentNode = currentNode.right;
                } else {
                    currentNode = currentNode.right;
                    size--;
                    return true;
                }
            }
        } else {
            throw new NullPointerException("The item that you passed in is null.");
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
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean removeAll(Collection<? extends T> items) {
        boolean hasChanged = false;
        for (T t: items) {
            if (remove(t)) {
                hasChanged = true;
                size--;
            }
        }
        return hasChanged;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<T> toArrayList() {
        Node currentNode = root;
        Node previousNode;
        T[] arr = (T[]) new Object[size];
        while (currentNode != null) {

            currentNode = currentNode.left;
        }

    }

    private class Node {
        T data;
        Node left;
        Node right;


        public Node(T data) {
            this.data = data;
            left = right = null;
        }
    }
}
