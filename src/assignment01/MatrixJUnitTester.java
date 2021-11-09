/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/

/**
 * @author Anirudh Lath
 * @project ${PROJECT_NAME}
 * @created ${MONTH}/${DAY}/${YEAR} - ${TIME}
 */
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixJUnitTester {

  Matrix threeByTwo, twoByThree, twoByTwoResult;
  private Matrix threeByTwoDuplicate;
  private Matrix threeByTwoAdded;
  private Matrix twoByThreeZeros;
  /* Initialize some matrices we can play with for every test! */

  @Before
  public void setup() {
    threeByTwo = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
    threeByTwoDuplicate = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
    threeByTwoAdded = new Matrix(new int[][] { { 2, 4, 6 }, { 4, 10, 12 } });
    twoByThree = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
    twoByThreeZeros = new Matrix(new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 } });
    // this is the known correct result of multiplying M1 by M2
    twoByTwoResult = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });
  }

  @Test
  public void twoByTwoToString() { // Test toString()
    String resultString = "13 12 \n29 26 \n";
    Assert.assertEquals(resultString, twoByTwoResult.toString());
  }

  @Test
  public void equals() { // Test equals
    Assert.assertTrue(threeByTwo.equals(threeByTwoDuplicate));
  }

  @Test
  public void timesWithBalancedDimensions() { // Test multiplying compatible matrices
    Matrix matrixProduct = threeByTwo.times(twoByThree);
    Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
    Assert.assertEquals("0 0 \n0 0 \n", threeByTwo.times(twoByThreeZeros).toString());
  }

  @Test
  public void timesWithUnbalancedDimensions() { // Test multiplying incompatible matrices
    Assert.assertEquals(null, twoByThree.times(twoByThree));
  }

  @Test
  public void additionWithBalancedDimensions() {
    Assert.assertTrue(threeByTwoAdded.equals(threeByTwo.plus(threeByTwoDuplicate)));
    Assert.assertTrue(twoByThree.equals(twoByThree.plus(twoByThreeZeros)));

  }
  @Test
  public void additionWithUnbalancedDimensions() {
    Assert.assertEquals(null, threeByTwo.plus(twoByTwoResult));
  }


}


