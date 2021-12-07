package assignment06;

/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 12/06/2021 - 8:22 PM
 */
public class BadHashFunctor implements HashFunctor {
    @Override
    public int hash(String item) {
        return item.length(); // Return the length of the string as the hash.
    }
}
