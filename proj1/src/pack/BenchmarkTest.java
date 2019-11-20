/**
* This class creates two identical arrays then sorts them in
* two different ways and outputs the time it takes to sort them.
* The task is done using 3 different array sizes 1,000, 10,000, and 100,000
* @author Anonymous
*/

package pack;

import java.util.Arrays;

public class BenchmarkTest
{
	// Constant maximum value to be assigned to array values
	public static final int MAX_VALUE = 800;
	
	public static void main(String[] args)
	{
		System.out.print("What is the array size?\nType an integer: ");
		int size = TextIO.getlnInt();
		
		// These two arrays are made identical and used
		// for the algorithm benchmarking purposes 
		int[] arrayOne = new int[size];
		int[] arrayTwo = new int[size];
		
		if(size == 0)
		{
			System.out.print("Zero size given. Exiting program.");
			System.exit(0);
		}
		
		for (int i = 0; i < size; i++)
		{
			int randomNumber = (int)(Integer.MAX_VALUE * Math.random());
			arrayOne[i] = randomNumber;
			arrayTwo[i] = randomNumber;
		}
		
		long startTime = System.currentTimeMillis();
		selectionSort(arrayOne);
		long runTime = System.currentTimeMillis() - startTime;
		System.out.print("The selection sort ran for: " + runTime + " milliseconds.\n");
		
		startTime = System.currentTimeMillis();
		Arrays.sort(arrayTwo);
		runTime = System.currentTimeMillis() - startTime;
		System.out.print("The Arrays.sort() ran for: " + runTime + " milliseconds.");
	}
	
	/**
	 * This method performs the Selection Sort algorithm on a given array
	 * The array given must be of type INT
	 * @param A an array of type int that is to be sorted high to low
	 */
	static void selectionSort(int[] A)
	{
		// Sort A into increasing order using selection sort
		for(int lastPlace = A.length-1; lastPlace > 0; lastPlace--)
		{
			// Find the largest item among A[0], A[1]...
			// A[lastPlace], and move it into position lastPlace
			// by swapping it with the number that is currently
			// in position lastPlace.
			int maxLoc = 0;
			
			for(int j = 1; j <= lastPlace; j++)
			{
				if(A[j] > A[maxLoc])
				{
					// Since A[j] is bigger than the maximum we've seen
					// so far, j is the new location of the maximum value
					// we've seen so far.
					maxLoc = j;
				}
			}
			
			int temp = A[maxLoc]; // Swap largest item with A[lastPlace].
			A[maxLoc] = A[lastPlace];
			A[lastPlace] = temp;
		}
	}
}