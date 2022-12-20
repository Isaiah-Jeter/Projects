//Matthew Paul and Isiah Jeter
public class ArrayUtils {
    
    /* countOdds
     * This method should return the number of odd integers in the array.
     * Odd integers are not divisible by 2.
     * 0 is an even integer.
     */
    public static int countOdds(int[] nums) 
    {
        int count = 0;
        for (int a=0; a < nums.length; a++)
        {
            if (nums[a] % 2 != 0)
                count++;
        }
        return count;
    }
    /* onDiagonal
     * This method takes a 2D array of integers, and a single integer parameter.
     * It returns true if and only if the value of the parameter lies on a diagonal of the
     * 2D array.
     * 
     * Diagonals are illustrated here (X is on diagonal, . is not):
     * 
     * X.X  X..X  X...X
     * .X.  .XX.  .X.X.  etc.
     * X.X  .XX.  ..X..
     *      X..X  .X.X.
     *            X...X
     *            
     * 2D arrays that are not square do not have diagonals, and should always return false.
     */
    public static boolean onDiagonal(int[][] matrix, int test) 
    {
        int row = matrix.length;
        int col = matrix[0].length;
        int count = 0;
        if (row != col)
            return false;
        for (int a=0; a < row; a++)
        {
            if (matrix[a][count] == test)
                return true;
            count++;
        }
        count = 0;
        for (int b = row-1; b > -1; b--)
        {
            if (matrix[b][count] == test)
                return true;
            count++;
        }
        return false;
    }
    /* addElements
     * This method takes two 1D integer arrays: list and increment.
     * The list array is mutated such that the ith element of list will be incremented by the ith
     * element of increment.
     * 
     * e.g.             list -> (0, 0, 1, 1,  2,  2)
     *             increment -> (4, 5, 6, 7,  8,  9)
     * after returning, list -> (4, 5, 7, 8, 10, 11)
     * 
     * If increment is not as long as list, treat unspecified values as 0.
     * 
     * e.g.             list -> (0, 0, 1, 1, 2, 2)
     *             increment -> (4, 5, 6, 7)
     * after returning, list -> (4, 5, 7, 8, 2, 2)
     * 
     * If increment has more elements than list, ignore the extra elements.
     */
    public static void addElements(int[] list, int[] increment) 
    {
        for (int a = 0; a < list.length; a++)
        {
            if (a < increment.length)
            	list[a] += increment[a];
        }
    }
    
    /* embiggen
     * Merges two 1D integer arrays into a single 2D integer array.
     * The [i][j] element of the resulting array is whichever value is larger: array1[i] or array2[j].
     */
    public static int[][] embiggen(int[] array1, int[] array2) 
    {
        int [][] rep = new int [array1.length][array2.length];
        for (int a = 0; a < array1.length; a++)
        {
        	for (int b = 0; b< array2.length; b++)
        	{
        		if (array1[a] > array2[b])
        			rep[a][b] = array1[a];
        		else
        			rep[a][b] = array2[b];
        	}
        }
        return rep;
    }
}