/**
 * Glass Falling
 * Nelson Zeas
 */
public class GlassFalling {

  
  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {

    /*
    If there is only one floor, the at most we need only one trial.
    If there are 0 floors, then we need zero trials
    */
    if( floors == 1 || floors == 0 )
      return floors;

    /*
    If there is only one glass sheet, the worst case scenario is that we need to test-drop from each floor.
    Starting from the first floor all the way to the last floor, if the glass sheet never breaks
    */
    if( sheets == 1 )
      return floors;

    /*
    If the glass sheet doesn't break at kth floor, then we have to cheack all floors above it. floors-i

    If the glass sheet breaks at the k-th floor, then we have to check the floors below, which are i-1.
    There would also be a sheet of glass less (sheets -1)
    */
    int min    = floors;
    int result = 0;

    for ( int i = 1; i < floors; i++ ) {
      result = Math.max( glassFallingRecur(i-1,sheets-1), glassFallingRecur(floors-i, sheets) );
      
      if( result < min )
        min = result;

    }

    return min+1;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets, int [][] glassTable) {

    if( glassTable[floors-1][sheets-1] != 0 ){
      return glassTable[floors-1][sheets-1];
    }

    if( floors <= 1 || sheets == 1 ){
      return floors;
    }


    glassTable[floors-1][sheets-1] = floors;
    int result = floors;
    for( int i = 1; i < floors; i++){

      int num1 = glassFallingMemoized(i, sheets-1, glassTable);
      int num2 = glassFallingMemoized(floors-i, sheets, glassTable) + 1;

      result   = Integer.max(num1, num2); 

      if(result < glassTable[floors-1][sheets-1]){
        glassTable[floors-1][sheets-1] = result;
      }

    }

    return glassTable[floors-1][sheets-1]+1;
  }






  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
    
    int [][] glassTable = new int[floors][sheets];

    //If there are no floors, then 0 tests are needed
    //If there is only one floor, then only one test is need
    for( int i = 0; i < sheets; i++){
      glassTable[0][i] = 0;
      glassTable[1][i] = 1;
    }

    //If there is only one sheet of glass, then the number of tests is equal
    //to the number of floors.
    for( int i = 0; i < floors; i++){
      glassTable[i][0] = i;
    }

    int dropsCount = 0;

    for( int row = 1; row < floors; row++){
      for(int col = 1; col < sheets; col++){
        glassTable[row][col] = Integer.MAX_VALUE;

        for( int res = 1; res <= row; res++){
           dropsCount = 1 + Math.max( glassTable[res-1][col-1], glassTable[row-res][col]);
           if( dropsCount < glassTable[row][col] ){
            glassTable[row][col] = dropsCount;
           }
        }


      }
    }
    return glassTable[floors-1][sheets-1];
  }



  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur  = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);


      //int minTrials2Recur  = gf.glassFallingRecur(100, 3);  //kills laptop

      int minTrials2Memo = gf.glassFallingMemoized(100, 3, new int [100][3]);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);


      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo  + " " + minTrials2Bottom);
  }
}
