package assignment06;

/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 12/06/2021 - 8:22 PM
 */
public class GoodHashFunctor implements HashFunctor {
    @Override
    public int hash(String item) {
        int hash = 5381;
        for (int i = 0; i < item.length(); i++) {
            hash = ((hash << 5) + hash) + item.charAt(i); // Add ascii values of each character and multiply it by the magic number 33^i.
        }
        return hash;
    }
}
