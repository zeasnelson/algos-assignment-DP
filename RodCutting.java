/**
 * Rod cutting problem described in Chapter 15 of textbook
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {

    //If the length of the rod is 0, then there can't be any revenue
    if( rodLength == 0 ) return 0;


    int maxRevenue = -1;

    for( int i = 0; i < rodLength; i++ ){
      maxRevenue = Math.max( maxRevenue, lengthPrices[i] + rodCuttingRecur(rodLength-i-1, lengthPrices) );
    }

    return maxRevenue;
  }

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {

    int [] results = new int[rodLength+1];

    //if the rod length is 0, then there is no revenue
    results[0] = 0;

    for( int i = 1; i <= rodLength; i++){
      int min =  Integer.MAX_VALUE*-1;
      for( int j = 1; j <= i; j++){
        min = Math.max( min, lengthPrices[j-1] + results[i-j] );
      }
      results[i] = min;
    }



    return results[rodLength];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};



      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
