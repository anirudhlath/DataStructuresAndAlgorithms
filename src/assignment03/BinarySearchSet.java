package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class that can create, search and edits sets based on binary search algorithm.
 *
 * @param <E> the generic type parameter
 */
public class BinarySearchSet<E> implements Iterable<E>, SortedSet<E> {

    private int capacity;
    private int size = 0;
    private E[] data;
    private Comparator myComparator;
    private boolean hasNaturalComparator;

    /**
     * Instantiates a new Binary search set.
     */
    public BinarySearchSet() {
        // Initialise member variables
        capacity = 40;
        data = (E[]) new Object[capacity];
        myComparator = new NaturalComparator();
        hasNaturalComparator = true;
    }

    /**
     * Instantiates a new Binary search set.
     *
     * @param comparator the comparator
     */
    public BinarySearchSet(Comparator<? super E> comparator) {
        // Initialise member variables
        capacity = 40;
        data = (E[]) new Object[capacity];
        myComparator = comparator;
        hasNaturalComparator = false;
    }

    /**
     * Driver function that checks if the set elements can be compared and if they can be then call binarySearch()
     *
     * @param element the element
     * @return the index of the search element or the element that is lower than the element that is supposed to be added.
     * @throws ClassCastException if the elements cannot be compared naturally.
     */
    public int driver(E element) {
        if (hasNaturalComparator) {
            try {
                myComparator.compare(element, element); // Check if the elements can be compared using a natural comparator.
            } catch (ClassCastException e) {
                throw new ClassCastException("The object type is not comparable. Please assign a custom comparator.");
            }
        }
        return binarySearch(data, element, 0, this.size - 1);
    }

    /**
     * Binary search that returns an index.
     *
     * @param arr     the array of elements
     * @param element the element that is being searched for
     * @param low     the lower index bound
     * @param high    the higher index bound
     * @return the index
     */
    public int binarySearch(E[] arr, E element, int low, int high) {

        if (high >= low) {
            int mid = low + (high - low) / 2;

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
        if (size != 0 && myComparator.compare(element, arr[0]) < 0) { // Case 1
            return 0;
        } else if (size != 0 && myComparator.compare(element, arr[size - 1]) > 0) { // Case 2
            return size;
        } else if (size != 0) {
            if (myComparator.compare(element, arr[low]) > 0) {
                return low + 1; // Case 3
            } else {
                return low; // Case 4
            }
        }
        return 0;
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
            return data[size - 1];
        }
        throw new NoSuchElementException("The array is empty.");
    }

    /**
     * Adds the specified element to this set if it is not already present and
     * not set to null.
     *
     * @param element element to be added
     * @return true if this set did not already contain the specified element
     */
    @Override
    public boolean add(E element) {
        int index = this.driver(element); // Call the driver
        if (size >= capacity) { // Increase capacity if needed.
            increaseCapacity();
        }
        if (index == size) { // If the element is supposed to be added in the end, don't compare or shift anything, just add.
            data[index] = element; // Assign the element at the index
            size++; // Increase size
            return true;

        } else if (myComparator.compare(data[index], element) != 0) { // Check if the element at index already exists, if not continue.
            CustomIterator iterator = new CustomIterator();
            iterator.setCurrentIndex(index); // Set iterator index to current index to optimise on time.
            iterator.add(); // Shifts all the elements after the index.
            data[index] = element;
            size++;
            return true;
        }
        return false; // Return false if the element already exists
    }

    /**
     * Increases capacity
     */
    private void increaseCapacity() {
        capacity = capacity * 2;
        E[] temp = data;
        data = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            data[i] = temp[i];
        }
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
                count++; // Count the change if element was added to the set.
            }
        }
        if (count > 0) {
            return true; // If there was change, return true else false.
        }
        return false;
    }

    /**
     * Removes all of the elements from this set. The set will be empty after
     * this call returns.
     */
    @Override
    public void clear() {
        capacity = 40;
        size = 0;
        data = (E[]) new Object[capacity];
    }

    /**
     * @param element Element to be search for
     * @return true if this set contains the specified element
     */
    @Override
    public boolean contains(Object element) {
        int i = driver((E) element);
        if (i < size && data[i].equals(element)) {
            return true;
        }
        return false;
    }

    /**
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return an iterator over the elements in this set, where the elements are
     * returned in sorted (ascending) order
     */
    @Override
    public Iterator iterator() {
        return new CustomIterator();
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param element element to be removed
     * @return true if this set contained the specified element
     */
    @Override
    public boolean remove(Object element) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                iterator.remove();
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return an array containing all of the elements in this set, in sorted
     * (ascending) order.
     */
    @Override
    public Object[] toArray() {
        return data;
    }

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection.
     *
     * @param elements @return true if this set changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> elements) {
        int count = 0;
        for (Object e :
                elements) {
            if (remove(e))
                count++;

        }
        if (count > 0)
            return true;
        return false;
    }

    /**
     * @param elements@return true if this set contains all of the elements of the specified
     *                        collection
     */
    @Override
    public boolean containsAll(Collection<?> elements) {
        int count = 0;
        for (Object e :
                elements) {
            if (contains(e))
                count++;
        }

        if (count == elements.size())
            return true;
        return false;
    }

    /**
     * The type Custom iterator.
     */
    class CustomIterator implements Iterator<E> {

        /**
         * The Current index.
         */
        int currentIndex;

        /**
         * Instantiates a new Custom iterator.
         */
        public CustomIterator() {
            currentIndex = 0;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if (hasNext()) {
                E result = data[currentIndex];
                currentIndex++;
                return result;
            } else {
                throw new NoSuchElementException("Index is out of range.");
            }
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         * <p>
         * The behavior of an iterator is unspecified if this method is called
         * after a call to the {@link #forEachRemaining forEachRemaining} method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            for (int i = currentIndex - 1; i < size; i++) {
                data[i] = data[i + 1];
            }
        }

        /**
         * Add.
         */
        public void add() {
            for (int i = size - 1; i >= currentIndex; i--) {
                data[i + 1] = data[i];
            }
        }

        /**
         * Sets current index.
         *
         * @param index the index
         */
        public void setCurrentIndex(int index) {
            currentIndex = index;
        }
    }


    /**
     * The type Natural comparator.
     *
     * @param <T> the type parameter
     */
    class NaturalComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T a, T b) {
            return a.compareTo(b);
        }
    }
}
