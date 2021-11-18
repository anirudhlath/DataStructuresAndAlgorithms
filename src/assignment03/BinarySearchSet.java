package assignment03;

import java.util.*;

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

    private int capacity;
    public int size = 0;
    public E[] data;
    private Comparator myComparator;
    boolean hasNaturalComparator;

    public BinarySearchSet() {
        // Initialise member variables
        capacity = 40;
        data = (E[]) new Object[capacity];
        myComparator = new NaturalComparator();
        hasNaturalComparator = true;
    }

    public BinarySearchSet(Comparator<? super E> comparator) {
        // Initialise member variables
        capacity = 40;
        data = (E[]) new Object[capacity];
        myComparator = comparator;
        hasNaturalComparator = false;
    }

    public int driver(E element) {
        if(hasNaturalComparator) {
            try {
                Comparator comparator = Comparator.naturalOrder();
                comparator.compare(element, element);
            } catch (ClassCastException e) {
                System.err.println(e.getMessage());
                System.out.println("The object type is not comparable! Please assign a comparable!");
                System.exit(-1);
            }
        }
        //System.out.println(size);
        return binarySearch(data, element, 0, this.size - 1);
    }

    public int getSize() {
        return size;
    }


    public int binarySearch(E[] arr, E element, int low, int high) {
//        System.out.println("Size: " + size);
//        System.out.println("Low: " + low + " | High: " + high);
        if (high >= low) {
            int mid = low + (high - low) / 2;
            //System.out.println(mid);

            if (myComparator.compare(arr[mid], element) == 0) {
                return mid;
            }

            if (myComparator.compare(arr[mid], element) > 0) {
                return binarySearch(arr, element, low, mid - 1);
            }

            if (myComparator.compare(arr[mid], element) < 0) {
                return binarySearch(arr, element, mid + 1, high);
            }
        }
        if (high < 0) {
//            System.out.println("Case 1");
            return 0;
        } else if(low == size) {
//            System.out.println("Case 2");
            return low;
        } else if(low >= high) {
//            System.out.println("Case 3");
            return high + 1;
        } else {
            System.exit(-1);
            return -1;
        }
    }


    /**
     * @return The comparator used to order the elements in this set, or null if
     * this set uses the natural ordering of its elements (i.e., uses
     * Comparable).
     */
    @Override
    public Comparator comparator() {
        if (!hasNaturalComparator) {
            return myComparator;
        }
        return null;
    }

    /**
     * @return the first (lowest, smallest) element currently in this set
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public E first() throws NoSuchElementException {
        if (size != 0) {
            return data[0];
        }
        throw new NoSuchElementException("The array is empty.");
    }

    /**
     * @return the last (highest, largest) element currently in this set
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public E last() throws NoSuchElementException {
        if (size != 0) {
            return data[data.length - 1];
        }
        throw new NoSuchElementException("The array is empty.");
    }

    /**
     * Adds the specified element to this set if it is not already present and
     * not set to null.
     *
     * @param element@return true if this set did not already contain the specified element
     */
    @Override
    public boolean add(E element) {
        int index = this.driver(element);
        if (index < size && index >= 0) {
            if (data[index] != element) {
                if (size < capacity - 5) {
                    this.size++;
                    for (int i = size + 1; i >= index; i--) {
                        data[i + 1] = data[i];
                    }
                    data[index] = element;
                    System.out.println("Added: " + Arrays.toString(data));
                    return true;
                } else {
                    this.size++;
                    capacity = capacity * 2;
                    E[] temp = data;
                    data = (E[]) new Object[capacity];
                    for (int i = 0; i < index; i++) {
                        data[i] = temp[i];
                    }
                    data[index] = element;
                    for (int i = index + 1; i < size; i++) {
                        data[i] = temp[i];
                    }
                    System.out.println("Added: " + Arrays.toString(data));
                    return true;
                }
            }
        } else if (index == size) {
            if (size < capacity - 5) {
                this.size++;
                data[index] = element;
                System.out.println("Added: " + Arrays.toString(data));
                return true;
            } else {
                this.size++;
                capacity = capacity * 2;
                E[] temp = data;
                data = (E[]) new Object[capacity];
                for (int i = 0; i < index; i++) {
                    data[i] = temp[i];
                }
                data[index] = element;
                System.out.println("Added: " + Arrays.toString(data));
                return true;
            }
        }
        System.out.println("Not Added: " + Arrays.toString(data));
        return false;
    }

    /**
     * Adds all of the elements in the specified collection to this set if they
     * are not already present and not set to null.
     *
     * @param elements @return true if this set changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> elements) {
        int count = 0;
        for (E e :
                elements) {
            if (add(e)) {
                count++;
            }
        }
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * Removes all of the elements from this set. The set will be empty after
     * this call returns.
     */
    @Override
    public void clear() {

    }

    /**
     * @param element@return true if this set contains the specified element
     */
    @Override
    public boolean contains(Object element) {

        return false;
    }

    /**
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * @return an iterator over the elements in this set, where the elements are
     * returned in sorted (ascending) order
     */
    @Override
    public Iterator iterator() {
        return null;
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param element@return true if this set contained the specified element
     */
    @Override
    public boolean remove(Object element) {
        return false;
    }

    /**
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * @return an array containing all of the elements in this set, in sorted
     * (ascending) order.
     */
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection.
     *
     * @param elements@return true if this set changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection elements) {
        return false;
    }

    /**
     * @param elements@return true if this set contains all of the elements of the specified
     *                        collection
     */
    @Override
    public boolean containsAll(Collection elements) {
        return false;
    }



    class NaturalComparator<T extends Comparable<T>>  implements Comparator<T> {
        @Override
        public int compare(T a, T b){
            return a.compareTo(b);
        }
    }
}
