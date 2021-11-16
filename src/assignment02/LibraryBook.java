package assignment02;

import java.util.GregorianCalendar;

/**
 * The type Library book.
 *
 * @author Anirudh Lath
 * @project CS6012
 * @created 11 /14/2021 - 6:31 PM
 */
public class LibraryBook extends Book{

    private String holder;
    private GregorianCalendar dueDate;

    /**
     * Instantiates a new Library book.
     *
     * @param isbn   the isbn
     * @param author the author
     * @param title  the title
     */
    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);
        holder = null;
        dueDate = null;
    }

    /**
     * Gets holder.
     *
     * @return the holder
     */
    public String getHolder() {
        return holder;
    }

    /**
     * Gets due date.
     *
     * @return the due date
     */
    public GregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Book check in.
     */
    public void bookCheckIn() {
        this.holder = null;
        this.dueDate = null;
    }

    /**
     * Book check out.
     *
     * @param holder  the holder
     * @param dueDate the due date
     */
    public void bookCheckOut(String holder, GregorianCalendar dueDate) {
        this.holder = holder;
        this.dueDate = dueDate;
    }

}
