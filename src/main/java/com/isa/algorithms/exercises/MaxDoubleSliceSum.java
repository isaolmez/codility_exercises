package com.isa.algorithms.exercises;

/****
 * 
 * A non-empty zero-indexed array A consisting of N integers is given.
 * 
 * A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
 * 
 * The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
 * 
 * For example, array A such that:
 * 
 * A[0] = 3 A[1] = 2 A[2] = 6 A[3] = -1 A[4] = 4 A[5] = 5 A[6] = -1 A[7] = 2 contains the following example double slices:
 * 
 * double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17, double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16, double slice (3, 4, 5), sum is 0. The goal is to
 * find the maximal sum of any double slice.
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a non-empty zero-indexed array A consisting of N integers, returns the maximal sum of any double slice.
 * 
 * For example, given:
 * 
 * A[0] = 3 A[1] = 2 A[2] = 6 A[3] = -1 A[4] = 4 A[5] = 5 A[6] = -1 A[7] = 2 the function should return 17, because no double slice of array A has a sum of
 * greater than 17.
 * 
 * Assume that:
 * 
 * N is an integer within the range [3..100,000]; each element of array A is an integer within the range [−10,000..10,000]. Complexity:
 * 
 * expected worst-case time complexity is O(N); expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input
 * arguments). Elements of input arrays can be modified.
 * 
 * @author isa
 *
 */
public class MaxDoubleSliceSum {
	public int solution(int[] A) {
		if (A == null || A.length < 4) {
			return 0;
		}

		// Global values
		int globalMaxSum = 0;
		int globalStartIndex = -1;
		int globalMiddleIndex = -1;
		int globalEndIndex = -1;

		// Local values
		int runningSum = -Integer.MAX_VALUE;
		int start = 0;
		int runningMiddle = Integer.MAX_VALUE;
		int middleIndex = 0;

		for (int i = 1; i < A.length - 1; i++) {
			int change = (A[i]);
			if (change <= runningMiddle) {
				runningSum += runningMiddle;
				runningMiddle = change;
				middleIndex = i;
			} else {
				runningSum += change;
			}

			if (runningSum < 0) {
//				runningProfit = -Integer.MAX_VALUE;
//				start = i;
//				runningMiddle = Integer.MAX_VALUE;
//				middleIndex = 0;
				 runningSum = 0;
				 start = i - 1;
				 runningMiddle = A[i];
				 middleIndex = i;
			} else {
				if (runningSum > globalMaxSum) {
					globalMaxSum = runningSum;
					globalStartIndex = start;
					globalMiddleIndex = middleIndex;
					globalEndIndex = i + 1;
					
					/** Edge Case**/
					
					// System.out.printf("(running, global,x,y,z) (%d, %d, %d, %d, %d)\n", runningProfit, globalMaxProfit, globalStartIndex, globalMiddleIndex,
					// globalEndIndex);
				}
			}
		}

		return globalMaxSum;
	}

	public static void main(String[] args) {
		// int[] A = { 3, 2, 6, -1, 4, 5, -1, 2 };
		// int[] A = { 1, -1, 1, -1, -1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1, 1, -11, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1 };
		int[] A = { 1, -1, 1, -1, -1, 1, 1, 1, -1, 1, 1, 1, 1 };
		// There is a problem with starting index assignment. ;If there is a smaller value ahead
		MaxDoubleSliceSum m = new MaxDoubleSliceSum();
		System.out.println(m.solution(A));
	}
}
