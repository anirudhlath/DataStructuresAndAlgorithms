package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Stack;

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
            Node currentNode = root;
            Node previousNode = null;
            while (currentNode != null) {
                previousNode = currentNode;
                if (currentNode.data.compareTo(item) < 0) {
                    currentNode = currentNode.right;
                } else {
                    if (currentNode.data.compareTo(item) == 0) {
                        return false;
                    }
                    currentNode = currentNode.left;
                }
            }

            Node newNode = new Node(item);
            if (this.root == null) { // If the list is empty add the item as root.
                this.root = newNode;
            } else if (item.compareTo(previousNode.data) < 0) { // Add the left child if the item is lower than the parent node.
                previousNode.left = newNode;
            } else { // Add the right child if the item is greater than the parent node.
                previousNode.right = newNode;
            }
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
            if (t == null) {
                throw new NullPointerException("One of the items passed in is null value.");
            }
            else if (add(t)) {
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
        if (item != null) {
            Node currentNode;
            currentNode = root;
            while (currentNode != null) {
                if (currentNode.data.compareTo(item) > 0) {
                    currentNode = currentNode.left;
                } else if (currentNode.data.compareTo(item) < 0) {
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
            if (t != null) {
                if (contains(t)) {
                    count++;
                }
            } else {
                throw new NullPointerException("One of the items in the input is null.");
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
        /*if (item != null) {
            Node currentNode; // Do we need a previous node too?
            currentNode = root;
            while (currentNode != null) {
                if (currentNode.data.compareTo(item) < 0) {
                    currentNode = currentNode.left;
                } else if (currentNode.data.compareTo(item) > 0) {
                    currentNode = currentNode.right;
                } else {
                    if (currentNode.data.compareTo(item) == 0) {
                        currentNode = currentNode.right;
                    }
                    currentNode = currentNode.right;
                    size--;
                    return true;
                }
            }
        } else {
            throw new NullPointerException("The item that you passed in is null.");
        }
        return false;*/
        if (item != null) {
            Node currentNode = root;
            while (currentNode != null) {
                if (currentNode.data.compareTo(item) < 0) {
                    currentNode = currentNode.right;
                } else {
                    if (currentNode.data.compareTo(item) == 0) {
                        if (size == 0) {
                            System.out.println("The tree is empty.");
                        } else if (size == 1) {
                            clear();
                        } else if (currentNode.left == null && currentNode.right == null) {
                            currentNode.data = null;
                        } else if (currentNode.left == null) {
                            currentNode.data = currentNode.right.data;
                            currentNode.left = currentNode.right.left;
                            currentNode.right = currentNode.right.right;
                        } else if (currentNode.right == null) {
                            currentNode.data = currentNode.left.data;
                            currentNode.right = currentNode.left.right;
                            currentNode.left = currentNode.left.left;
                        } else                                                            {
                            // Find minimum in right subtree, delete that node and copy it over to current node.
                            T data = findDeleteMin(currentNode.right);
                            currentNode.data = data;
                        }
                        size--;
                        return true;
                    }
                    currentNode = currentNode.left;
                }
            }
        } else {
            throw new NullPointerException("The item that you passed in is null.");
        }
        return false;
    }

    private T findDeleteMin(Node node) {
        Node temp = node;
        Node parent = node;
        while(temp.left != null) {
            parent = temp;
            temp = temp.left;
        }
        // temp should now be the minimum value in the subtree without a left child.

        T data = temp.data;
        if (temp.right != null) {
            temp.data = temp.right.data;
            temp.left = temp.right.left;
            temp.right = temp.right.right;
        } else {
            if (temp.data.compareTo(parent.data) < 0) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }


        return data;
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
            if (t == null) {
                throw new NullPointerException("One of the items passed in is null value.");
            }
            else if (remove(t)) {
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

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<T> toArrayList() {
        Node currentNode = root;
        ArrayList<T> arr = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        if (size > 0) {
            while (currentNode != null || !s.isEmpty()) {
                while (currentNode != null) {
                    s.push(currentNode);
                    currentNode = currentNode.left;
                }
                currentNode = s.pop();
                arr.add(currentNode.data);
                currentNode = currentNode.right;
            }
        } else {
            System.out.println("The tree is empty.");
        }
        return arr;
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
