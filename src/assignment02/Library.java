package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 */
public class Library {

  private ArrayList<LibraryBook> library;

  /**
   * Instantiates a new Library.
   */
  public Library() {
    library = new ArrayList<LibraryBook>();
  }

  /**
   * Add the specified book to the library, assume no duplicates.
   *
   * @param isbn   -- ISBN of the book to be added
   * @param author -- author of the book to be added
   * @param title  -- title of the book to be added
   */
  public void add(long isbn, String author, String title) {
    library.add(new LibraryBook(isbn, author, title));
    assert true;
  }

  /**
   * Add the list of library books to the library, assume no duplicates.
   *
   * @param list -- list of library books to be added
   */
  public void addAll(ArrayList<LibraryBook> list) {
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
    ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

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
          toBeAdded.add(new LibraryBook(isbn, author, title));
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
   * @return the string
   */
  public String lookup(long isbn) {
    for (LibraryBook book:
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
  public ArrayList<LibraryBook> lookup(String holder) {
    ArrayList<LibraryBook> booksOfHolder = new ArrayList<>();
    for (LibraryBook book :
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
  public boolean checkout(long isbn, String holder, int month, int day, int year) {
    for (LibraryBook book :
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
    for (LibraryBook book :
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
  public boolean checkin(String holder) {
    boolean result = false;
    for (LibraryBook book :
            library) {
      if (book.getHolder() != null && book.getHolder().equals(holder)) {
        book.bookCheckIn();
        result = true;
      }
    }
    return result;
  }
}
