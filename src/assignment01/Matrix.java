/**
 * @author Anirudh Lath
 * @project ${PROJECT_NAME}
 * @created ${MONTH}/${DAY}/${YEAR} - ${TIME}
 */
package assignment01;

public class Matrix {
  int numRows;
  int numColumns;
  int[][] data;

  // Constructor with data for new matrix (automatically determines dimensions)
  public Matrix(int[][] data) {
    numRows = data.length; // d.length is the number of 1D arrays in the 2D array
    if (numRows == 0) {
      numColumns = 0;
    } else {
      numColumns = data[0].length; // d[0] is the first 1D array
    }
    this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
    // copy the data over
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        this.data[i][j] = data[i][j];
      }
    }
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public boolean equals(Object other) {
    if (!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
      return false;
    }
    Matrix matrix = (Matrix) other; // if the above was not true, we know it's safe to treat 'o' as a Matrix

    // Check if the dimensions are equals or not
    if (this.numColumns != matrix.numColumns && this.numRows != matrix.numRows) {
      return false; // Matrices are not equals
    }

    // Check if the values are the same or not
    for (int i = 0; i < matrix.numRows; i++) { // Iterate over all the rows
      for (int j = 0; j < matrix.numColumns; j++) { // Iterate over all the columns
        if (this.data[i][j] != matrix.data[i][j]) {
          return false; // Matrices are not equals
        }
      }
    }

    return true;
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public String toString() {

    String result = "";

    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.numColumns; j++) {
        result += this.data[i][j] + " "; // Add each element to the result string followed by a space.
      }
      result += "\n"; // Add a newline character to the result string at the end of each row.
    }

    return result; // return the result string.
  }

  public Matrix times(Matrix matrix) {

    if (this.numColumns != matrix.numRows) { // If the no. of columns of the LHS matrix 'this' does not match the no. of rows of the RHS matrix 'matrix', return null.
      return null;
    }

    int[][] data = new int[this.numRows][matrix.numColumns];
    int value = 0;

    for (int i = 0; i < this.numRows; i++) { // Iterate over the rows of 'this' --> Aids the construction of the new matrix and in calculation of new values.
      for (int k = 0; k < matrix.numColumns; k++) { // Iterate over the columns of 'matrix' --> Aids the construction of the new matrix and in calculation of new values.
        for (int j = 0; j < this.numColumns; j++) { // Iterate over the columns of 'this' --> Aids in calculation of new values.
          value += this.data[i][j] * matrix.data[j][k];
        }
        data[i][k] = value; // I couldn't figure out a way to do it with just two loops.
        value = 0; // Reset value to 0 before calculating another value.
      }
    }

    return new Matrix(data); // Return the new matrix
  }

  public Matrix plus(Matrix matrix) {

    if (this.numRows != matrix.numRows || this.numColumns != matrix.numColumns) {
      return null; // Return null if the dimension are not compatible.
    }

    int data[][] = new int[this.numRows][this.numColumns];

    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.numColumns; j++) {
        data[i][j] = this.data[i][j] + matrix.data[i][j];
      }
    }

    return new Matrix(data); // Return the sum of two matrices.
  }
}
