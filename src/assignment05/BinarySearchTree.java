package assignment05;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * The type Binary search tree.
 *
 * @param <T> the type parameter
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

    private Node root;
    private int size;


    /**
     * Instantiates a new Binary search tree.
     */
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
                    currentNode = currentNode.right; // Iterate to right subtree if the item is greater than the current node.
                } else {
                    if (currentNode.data.compareTo(item) == 0) {
                        return false; // Return false because the item already exists in the BST.
                    }
                    currentNode = currentNode.left; // Iterate to left subtree if the item is less than the current node.
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
                hasChanged = true; // If add returns true, that means the BST has changed and therefore return hasChanged when the time comes.
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
                if (currentNode.data.compareTo(item) > 0) { // Iterate to right subtree if the item is greater than the current node.
                    currentNode = currentNode.left;
                } else if (currentNode.data.compareTo(item) < 0) { // Iterate to left subtree if the item is less than the current node.
                    currentNode = currentNode.right;
                } else {
                    return true; // Return true if the item is found.
                }
            }
        } else {
            throw new NullPointerException("The item you passed in is null.");
        }
        return false; // Return false is the item is not found.
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
                if (contains(t)) { // Keep track of the number of items found and check each word in the collection.
                    count++;
                }
            } else {
                throw new NullPointerException("One of the items in the input is null.");
            }
        }

        if (count == items.size()) {
            return true; // If all the items in the collection were found, only then return true, else false.
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
        return size == 0 && root == null;
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

        if (size == 0) { // If the tree is empty.
            System.out.println("The tree is empty.");
        } else

        if (item != null) {
            Node currentNode = root;
            Node parentNode = root;
            while (currentNode != null) {
                if (currentNode.data.compareTo(item) != 0) {
                    parentNode = currentNode; // Keep track of the parent node
                }
                if (currentNode.data.compareTo(item) < 0) {
                    currentNode = currentNode.right;
                } else {
                    if (currentNode.data.compareTo(item) == 0) {
                        if (size == 1) { // If the tree only has root node.
                            clear();
                        } else if (currentNode.left == null && currentNode.right == null) { // If currentNode is a leaf node.
                            handleParent(currentNode, parentNode); // Deassociate parent with the currentNode.
                        } else if (currentNode.left == null)                              { // If the node only has right child.
                            currentNode.data = currentNode.right.data;
                            currentNode.left = currentNode.right.left;
                            currentNode.right = currentNode.right.right;
                        } else if (currentNode.right == null)                             { // If the node only has left child.
                            currentNode.data = currentNode.left.data;
                            currentNode.right = currentNode.left.right;
                            currentNode.left = currentNode.left.left;
                        } else                                                            { // If the node has two children.
                            // Find minimum in right subtree, delete that node and copy it over to current node.
                            T data = findDeleteMin(currentNode.right);
                            currentNode.data = data;
                        }
                        size--; // Update size
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

    private T findDeleteMin(Node node) { // Finds the minimum node, or the next appropriate successor of the current node.
        Node currentNode = node;
        Node parent = node;
        while(currentNode.left != null) {
            parent = currentNode;
            currentNode = currentNode.left;
        }
        // currentNode should now be the minimum value in the subtree without a left child.

        T data = currentNode.data;
        if (currentNode.right != null) { // If the successor has right child, having a left child is not possible because this is the minimum value in the subtree.
            currentNode.data = currentNode.right.data;
            currentNode.left = currentNode.right.left;
            currentNode.right = currentNode.right.right;
        } else {
            handleParent(currentNode, parent); // If the successor is a leaf node.
        }


        return data;
    }

    private void handleParent(Node temp, Node parent) {
        if (temp.data.compareTo(parent.data) < 0) {
            parent.left = null;
        } else {
            parent.right = null;
        }
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
     * Returns an ArrayList containing all the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<T> toArrayList() {
        writeDot("dictionary.dot");
        Node currentNode = root;
        ArrayList<T> arr = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        if (size > 0) {
            while (currentNode != null || !s.isEmpty()) { // Inorder traversal
                while (currentNode != null) { // Add all the minimum values to stack
                    s.push(currentNode);
                    currentNode = currentNode.left;
                }
                currentNode = s.pop();
                arr.add(currentNode.data); // Add data to array in a sorted fashion
                currentNode = currentNode.right; // Traverse to right subtree.
            }
        } else {
            System.out.println("The tree is empty.");
        }
        return arr;
    }

    /**
     * Write dot.
     *
     * @param filename the filename
     */
    public void writeDot(String filename) {
        try {
            PrintWriter output = new PrintWriter(new FileWriter(filename));
            output.println("graph g {");
            if (root != null)
                output.println(root.hashCode() + "[label=\"" + root.data + "\"]");
            writeDotRecursive(root, output);
            output.println("}");
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recursively traverse the tree, outputting each node to the .dot file
    private void writeDotRecursive(Node n, PrintWriter output) throws Exception {
        if (n == null)
            return;
        if (n.left != null) {
            output.println(n.left.hashCode() + "[label=\"" + n.left.data + "\"]");
            output.println(n.hashCode() + " -- " + n.left.hashCode());
        }
        if (n.right != null) {
            output.println(n.right.hashCode() + "[label=\"" + n.right.data + "\"]");
            output.println(n.hashCode() + " -- " + n.right.hashCode());
        }

        writeDotRecursive(n.left, output);
        writeDotRecursive(n.right, output);
    }


    private class Node {
        /**
         * The Data.
         */
        T data;
        /**
         * The Left.
         */
        Node left;
        /**
         * The Right.
         */
        Node right;


        /**
         * Instantiates a new Node.
         *
         * @param data the data
         */
        public Node(T data) {
            this.data = data;
            left = right = null;
        }
    }
}
