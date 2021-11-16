package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 *
 * @param <Type> the type parameter
 */
public class LibraryGeneric<Type> {

  private ArrayList<LibraryBookGeneric<Type>> library;

  /**
   * Instantiates a new Library generic.
   */
  public LibraryGeneric() {
    library = new ArrayList<>();
  }

  /**
   * Add the specified book to the library, assume no duplicates.
   *
   * @param isbn   -- ISBN of the book to be added
   * @param author -- author of the book to be added
   * @param title  -- title of the book to be added
   */
  public void add(long isbn, String author, String title) {
    library.add(new LibraryBookGeneric<>(isbn, author, title));
  }

  /**
   * Add the list of library books to the library, assume no duplicates.
   *
   * @param list -- list of library books to be added
   */
  public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
    library.addAll(list);
  }

  /**
   * Add books specified by the input file. One book per line with ISBN, author,
   * and title separated by tabs.
   * <p>
   * If file does not exist or format is violated, do nothing.
   *
   * @param filename the filename
   */
  public void addAll(String filename) {
    ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<>();

    try (Scanner fileIn = new Scanner(new File(filename))) {

      int lineNum = 1;

      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();

        try (Scanner lineIn = new Scanner(line)) {
          lineIn.useDelimiter("\\t");

          if (!lineIn.hasNextLong()) {
            throw new ParseException("ISBN", lineNum);
          }
          long isbn = lineIn.nextLong();

          if (!lineIn.hasNext()) {
            throw new ParseException("Author", lineNum);
          }
          String author = lineIn.next();

          if (!lineIn.hasNext()) {
            throw new ParseException("Title", lineNum);
          }
          String title = lineIn.next();
          toBeAdded.add(new LibraryBookGeneric<>(isbn, author, title));
        }
        lineNum++;
      }
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage() + " Nothing added to the library.");
      return;
    } catch (ParseException e) {
      System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
          + ". Nothing added to the library.");
      return;
    }

    library.addAll(toBeAdded);
  }

  /**
   * Returns the holder of the library book with the specified ISBN.
   * <p>
   * If no book with the specified ISBN is in the library, returns null.
   *
   * @param isbn -- ISBN of the book to be looked up
   * @return the type
   */
  public Type lookup(long isbn) {
    for (LibraryBookGeneric<Type> book:
         library) {
      if (isbn == book.getIsbn()) {
        return book.getHolder();
      }
    }
    return null;
  }

  /**
   * Returns the list of library books checked out to the specified holder.
   * <p>
   * If the specified holder has no books checked out, returns an empty list.
   *
   * @param holder -- holder whose checked out books are returned
   * @return the array list
   */
  public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {
    ArrayList<LibraryBookGeneric<Type>> booksOfHolder = new ArrayList<>();
    for (LibraryBookGeneric<Type> book :
            library) {
      if (book.getHolder() != null && book.getHolder().equals(holder)) {
        booksOfHolder.add(book);
      }
    }
    return booksOfHolder;
  }

  /**
   * Sets the holder and due date of the library book with the specified ISBN.
   * <p>
   * If no book with the specified ISBN is in the library, returns false.
   * <p>
   * If the book with the specified ISBN is already checked out, returns false.
   * <p>
   * Otherwise, returns true.
   *
   * @param isbn   -- ISBN of the library book to be checked out
   * @param holder -- new holder of the library book
   * @param month  -- month of the new due date of the library book
   * @param day    -- day of the new due date of the library book
   * @param year   -- year of the new due date of the library book
   * @return the boolean
   */
  public boolean checkout(long isbn, Type holder, int month, int day, int year) {
    for (LibraryBookGeneric<Type> book :
            library) {
      if (isbn == book.getIsbn() && book.getHolder() == null) {
        GregorianCalendar date = new GregorianCalendar(year, month, day);
        book.bookCheckOut(holder, date);
        return true;
      }
    }
    return false;
  }

  /**
   * Unsets the holder and due date of the library book.
   * <p>
   * If no book with the specified ISBN is in the library, returns false.
   * <p>
   * If the book with the specified ISBN is already checked in, returns false.
   * <p>
   * Otherwise, returns true.
   *
   * @param isbn -- ISBN of the library book to be checked in
   * @return the boolean
   */
  public boolean checkin(long isbn) {
    for (LibraryBookGeneric<Type> book :
            library) {
      if (isbn == book.getIsbn() && book.getHolder() != null) {
        book.bookCheckIn();
        return true;
      }
    }
    return false;
  }

  /**
   * Unsets the holder and due date for all library books checked out be the
   * specified holder.
   * <p>
   * If no books with the specified holder are in the library, returns false;
   * <p>
   * Otherwise, returns true.
   *
   * @param holder -- holder of the library books to be checked in
   * @return the boolean
   */
  public boolean checkin(Type holder) {
    boolean result = false;
    for (LibraryBookGeneric<Type> book :
            library) {
      if (book.getHolder() != null && book.getHolder().equals(holder)) {
        book.bookCheckIn();
        result = true;
      }
    }
    return result;
  }

  /**
   * Returns the list of library books, sorted by ISBN (smallest ISBN first).
   *
   * @return the inventory list
   */
  public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
    ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
    libraryCopy.addAll(library);

    OrderByIsbn comparator = new OrderByIsbn();

    sort(libraryCopy, comparator);

    return libraryCopy;
  }

  /**
   * Returns the list of library books, sorted by author
   *
   * @return the ordered by author
   */
  public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {
    ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
    libraryCopy.addAll(library);

    OrderByAuthor comparator = new OrderByAuthor();

    sort(libraryCopy, comparator);

    return libraryCopy;
  }

  /**
   * Returns the list of library books whose due date is older than the input
   * date. The list is sorted by date (oldest first).
   * <p>
   * If no library books are overdue, returns an empty list.
   *
   * @param month the month
   * @param day   the day
   * @param year  the year
   * @return the overdue list
   */
  public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day,
                                                            int year) {

    ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
    GregorianCalendar dueDate = new GregorianCalendar(year, month, day);

    for (LibraryBookGeneric<Type> book:
         library) {

      if (book.getDueDate() != null && book.getDueDate().compareTo(dueDate) < 0) {
        //System.out.println(book.getDueDate().getTime() + " " + book.getAuthor());
        libraryCopy.add(book);
      }

      OrderByDueDate comparator = new OrderByDueDate();
      sort(libraryCopy, comparator);

    }

    return libraryCopy;
  }



  /**
   * Performs a SELECTION SORT on the input ArrayList.
   *    1. Find the smallest item in the list.
   *    2. Swap the smallest item with the first item in the list.
   *    3. Now let the list be the remaining unsorted portion
   *       (second item to Nth item) and repeat steps 1, 2, and 3.
   */
  private static <ListType> void sort(ArrayList<ListType> list,
                                      Comparator<ListType> c) {
    for (int i = 0; i < list.size() - 1; i++) {
      int j, minIndex;
      for (j = i + 1, minIndex = i; j < list.size(); j++)
        if (c.compare(list.get(j), list.get(minIndex)) < 0)
          minIndex = j;
      ListType temp = list.get(i);
      list.set(i, list.get(minIndex));
      list.set(minIndex, temp);
    }
  }

  /**
   * Comparator that defines an ordering among library books using the ISBN.
   */
  protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

    /**
     * Returns a negative value if lhs is smaller than rhs. Returns a positive
     * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
     */
    public int compare(LibraryBookGeneric<Type> lhs,
                       LibraryBookGeneric<Type> rhs) {
      return (int) (lhs.getIsbn() - rhs.getIsbn());
    }
  }

  /**
   * Comparator that defines an ordering among library books using the author,  and book title as a tie-breaker.
   */
  protected class OrderByAuthor implements
          Comparator<LibraryBookGeneric<Type>> {

    @Override
    public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
      String str1 = lhs.getAuthor();
      String str2 = rhs.getAuthor();
      if (str1.compareTo(str2) == 0) {
        return lhs.getTitle().compareTo(rhs.getTitle());
      }
      return str1.compareTo(str2);
    }
  }

  /**
   * Comparator that defines an ordering among library books using the due date.
   */
  protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {
    @Override
    public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
      return (lhs.getDueDate().compareTo(rhs.getDueDate()));
    }
  }

}
