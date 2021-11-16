package assignment02;

import java.util.GregorianCalendar;

/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 11/14/2021 - 6:31 PM
 */
public class LibraryBookGeneric<Type> extends Book{

    private Type holder;
    private GregorianCalendar dueDate;

    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);
        holder = null;
        dueDate = null;
    }

    public Type getHolder() {
        return holder;
    }

    public GregorianCalendar getDueDate() {
        return dueDate;
    }

    public void bookCheckIn() {
        this.holder = null;
        this.dueDate = null;
    }

    public void bookCheckOut(Type holder, GregorianCalendar dueDate) {
        this.holder = holder;
        this.dueDate = dueDate;
    }

}
