package assignment06;

/**
 * The type Mediocre hash functor.
 *
 * @author Anirudh Lath
 * @project CS6012
 * @created 12 /06/2021 - 8:22 PM
 */
public class MediocreHashFunctor implements HashFunctor {
    @Override
    public int hash(String item) {
        int hash = 0;
        for (int i = 0; i < item.length(); i++) {
            hash += item.charAt(i); // Add ascii values of each character
        }
        return hash;
    }
}
