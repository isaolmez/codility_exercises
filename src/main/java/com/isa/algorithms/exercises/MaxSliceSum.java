package com.isa.algorithms.exercises;

import com.isa.algorithms.utils.InputGenerator;
import com.isa.algorithms.utils.Stopwatch;

/****
 * 
 * A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The sum
 * of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].
 * 
 * Write a function:
 * 
 * int solution(int A[], int N);
 * 
 * that, given an array A consisting of N integers, returns the maximum sum of any slice of A.
 * 
 * For example, given array A such that:
 * 
 * A[0] = 3 A[1] = 2 A[2] = -6 A[3] = 4 A[4] = 0 the function should return 5 because:
 * 
 * (3, 4) is a slice of A that has sum 4, (2, 2) is a slice of A that has sum −6, (0, 1) is a slice of A that has sum 5, no other slice of A has sum greater
 * than (0, 1). Assume that:
 * 
 * N is an integer within the range [1..1,000,000]; each element of array A is an integer within the range [−1,000,000..1,000,000]; the result will be an
 * integer within the range [−2,147,483,648..2,147,483,647]. Complexity:
 * 
 * expected worst-case time complexity is O(N); expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input
 * arguments). Elements of input arrays can be modified.
 * 
 * @author isa
 *
 */
public class MaxSliceSum {
	public int solution(int[] A) {
		if (A == null || A.length == 1) {
			return A[0];
		}

		int globalMaxSum = Integer.MIN_VALUE;
		long globalMinSum = 0;
		long runningSum = 0;

		for (int i = 0; i < A.length; i++) {
			runningSum += A[i];
			globalMaxSum = (int)Math.max(globalMaxSum, runningSum - globalMinSum);
			globalMinSum = Math.min(globalMinSum, runningSum);
		}

		return globalMaxSum;
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		MaxSliceSum m = new MaxSliceSum();
		int[] A = { 3, 2, -6, 4, 0 };
		System.out.println(m.solution(A));

		A = new int[] { -2, -2 };
		System.out.println(m.solution(A));

		A = InputGenerator.randomArray(1_000_0000, -1_000_000, 1_000_000);
		Stopwatch watch = new Stopwatch();
		System.out.println(m.solution(A));
		System.out.println("Elapsed: " + watch.elapsedTime());
	}
}
