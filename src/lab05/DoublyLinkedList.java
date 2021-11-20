package lab05;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

    LinkedNode<E> head;
    LinkedNode<E> tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Inserts the specified element at the beginning of the list. O(1) for a
     * doubly-linked list.
     *
     * @param element
     */
    @Override
    public void addFirst(E element) {

        if (size == 0) {
            head = new LinkedNode<>(element);
            tail = head;
        } else {
            LinkedNode<E> newNode = new LinkedNode<>(element);
            newNode.next = head;
            head = newNode;
        }
        size++;

    }

    /**
     * Inserts the specified element at the end of the list. O(1) for a
     * doubly-linked list.
     *
     * @param o
     */
    @Override
    public void addLast(E o) {
        if (size == 0) {
            head = new LinkedNode<>(o);
            tail = head;
        } else {
            LinkedNode<E> newNode = new LinkedNode<>(o);
            newNode.previous = tail;
            tail = newNode;
        }
        size++;

    }

    /**
     * Inserts the specified element at the specified position in the list. Throws
     * IndexOutOfBoundsException if index is out of range (index < 0 || index >
     * size()) O(N) for a doubly-linked list.
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        LinkedNode<E> currentNode = head;
        if (index < size) {
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    LinkedNode<E> newNode = new LinkedNode<>(element);
                    newNode.previous = currentNode.previous;
                    newNode.next = currentNode;
                    newNode.previous.next = newNode;
                    newNode.next.previous = newNode;
                    return;
                }
                currentNode = currentNode.next;
            }
        } else {
            throw new IndexOutOfBoundsException("The index is out of range.");
        }

    }

    /**
     * Returns the first element in the list. Throws NoSuchElementException if the
     * list is empty. O(1) for a doubly-linked list.
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty.");
        }
        return head.data;
    }

    /**
     * Returns the last element in the list. Throws NoSuchElementException if the
     * list is empty. O(1) for a doubly-linked list.
     */
    @Override
    public E getLast() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty.");
        }
        return tail.data;
    }

    /**
     * Returns the element at the specified position in the list. Throws
     * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
     * size()) O(N) for a doubly-linked list.
     *
     * @param index
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        LinkedNode<E> currentNode = head;

        if (index < size) {
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    break;
                }
                currentNode = currentNode.next;
            }
        } else {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }
        return currentNode.data;
    }

    /**
     * Removes and returns the first element from the list. Throws
     * NoSuchElementException if the list is empty. O(1) for a doubly-linked list.
     */
    @Override
    public E removeFirst() throws NoSuchElementException {
        LinkedNode<E> tempNode = head;
        if (size != 0) {
            head = head.next;
            head.previous = null;
            size--;
        } else {
            throw new NoSuchElementException("The list is empty.");
        }
        return tempNode.data;
    }

    /**
     * Removes and returns the last element from the list. Throws
     * NoSuchElementException if the list is empty. O(1) for a doubly-linked list.
     */
    @Override
    public E removeLast() throws NoSuchElementException {
        LinkedNode<E> tempNode = tail;
        if (size != 0) {
            tail = tail.previous;
            tail.next = null;
            size--;
        } else {
            throw new NoSuchElementException("The list is empty.");
        }
        return tempNode.data;
    }

    /**
     * Removes and returns the element at the specified position in the list. Throws
     * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
     * size()) O(N) for a doubly-linked list.
     *
     * @param index
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        LinkedNode<E> currentNode = head;

        if (index < size) {
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    currentNode.previous.next = currentNode.next;
                    currentNode.next.previous = currentNode.previous;
                    currentNode.data = null;
                    currentNode.next = null;
                    currentNode.previous = null;
                    size--;
                    break;
                }
                currentNode = currentNode.next;
            }
        } else {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }
        return currentNode.data;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the
     * list, or -1 if this list does not contain the element. O(N) for a
     * doubly-linked list.
     *
     * @param element
     */
    @Override
    public int indexOf(E element) {
        LinkedNode<E> currentNode = head;
        for (int i = 0; i < size; i++) {
            if (currentNode.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this
     * list, or -1 if this list does not contain the element. O(N) for a
     * doubly-linked list.
     *
     * @param element
     */
    @Override
    public int lastIndexOf(E element) {
        LinkedNode<E> currentNode = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (currentNode.data.equals(element)) {
                index = i;
            }
        }
        return index;

    }

    /**
     * Returns the number of elements in this list. O(1) for a doubly-linked list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this collection contains no elements. O(1) for a
     * doubly-linked list.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all of the elements from this list. O(1) for a doubly-linked list.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element). O(N) for a doubly-linked list.
     */
    @Override
    public Object[] toArray() {
        LinkedNode<E> currentNode = head;
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return arr;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * The behavior of this method is unspecified if the action performs
     * side-effects that modify the underlying source of elements, unless an
     * overriding class has specified a concurrent modification policy.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    /**
     * Creates a {@link Spliterator} over the elements described by this
     * {@code Iterable}.
     *
     * @return a {@code Spliterator} over the elements described by this
     * {@code Iterable}.
     * @implSpec The default implementation creates an
     * <em><a href="../util/Spliterator.html#binding">early-binding</a></em>
     * spliterator from the iterable's {@code Iterator}.  The spliterator
     * inherits the <em>fail-fast</em> properties of the iterable's iterator.
     * @implNote The default implementation should usually be overridden.  The
     * spliterator returned by the default implementation has poor splitting
     * capabilities, is unsized, and does not report any spliterator
     * characteristics. Implementing classes can nearly always provide a
     * better implementation.
     * @since 1.8
     */
    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }

    class LinkedNode<E> {

        E data;
        DoublyLinkedList<E>.LinkedNode<E> previous;
        DoublyLinkedList<E>.LinkedNode<E> next;

        public LinkedNode(E data) {
            this.data = data;
            previous = null;
            next = null;
        }

    }
}
