package com.isa.algorithms.exercises;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a function:
 * 
 * int solution(int A[], int N);
 * 
 * that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer (greater than 0) that does not occur in A.
 * 
 * For example, given:
 * 
 * A[0] = 1 A[1] = 3 A[2] = 6 A[3] = 4 A[4] = 1 A[5] = 2 the function should return 5.
 * 
 * Assume that:
 * 
 * N is an integer within the range [1..100,000]; each element of array A is an integer within the range [−2,147,483,648..2,147,483,647]. Complexity:
 * 
 * expected worst-case time complexity is O(N); expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments). Elements
 * of input arrays can be modified.
 * */
public class MissingInteger {
	public int solution(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}

		Set<Integer> missing = new HashSet<>();
		missing.add(1);
		for (int i = 0; i < A.length; i++) {
			if (A[i] >= 0) {

				if (missing.contains(A[i])) {
					missing.remove(A[i]);
				}

				missing.add(A[i] + 1);
			}
		}

		for (int i = 0; i < A.length; i++) {
			if (missing.contains(A[i])) {
				missing.remove(A[i]);
			}
		}

		int smallest = Integer.MAX_VALUE;
		for (int i : missing) {
			if (i < smallest) {
				smallest = i;
			}
		}

		return smallest;
	}

	public static void main(String[] args) {
		int[] test = { 1, 2, 100, 45, 1, 4, 3 };
		int[] test2 = { -100 };
		MissingInteger m = new MissingInteger();
		System.out.println(m.solution(test));
		System.out.println(m.solution(test2));
	}
}
