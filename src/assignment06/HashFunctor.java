package assignment06;

/**
 * Serves as a guide for how to define a functor that contains a hashing
 * function for String items (i.e., the hash method).
 */
public interface HashFunctor {

    /**
     * Hash int.
     *
     * @param item the item
     * @return the int
     */
    public int hash(String item);

}