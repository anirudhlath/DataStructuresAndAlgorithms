package assignment01;

public class Matrix {
  int numRows;
  int numColumns;
  int data[][];

  // Constructor with data for new matrix (automatically determines dimensions)
  public Matrix(int data[][]) {
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
    /*
     * TODO: replace the below return statement with the correct code, This function
     * must check if the two matrices are compatible for multiplication, if not,
     * return null. If they are compatible, determine the dimensions of the
     * resulting matrix, and fill it in with the correct values for matrix
     * multiplication
     */

    if (this.numColumns != matrix.numRows) { // If the no. of columns of the LHS matrix 'this' does not match the no. of rows of the RHS matrix 'matrix', return null.
      return null;
    }

    int data[][] = new int[this.numRows][matrix.numColumns];

    for (int i = 0; i < this.numRows; i++) {
      int value = 0;
      for (int j = 0; j < this.numColumns; j++) {
        value += this.data[i][j] * matrix.data[j][i];
      }


    }


    return null; // placeholder
  }

  public Matrix plus(Matrix matrix) {
    /*
     * TODO: replace the below return statement with the correct code, This function
     * must check if the two matrices are compatible for addition, if not, return
     * null. If they are compatible, create a new matrix and fill it in with the
     * correct values for matrix addition
     */
    return null; // placeholder
  }
}
