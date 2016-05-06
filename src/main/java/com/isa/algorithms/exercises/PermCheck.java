package com.isa.algorithms.exercises;

import java.util.HashSet;
import java.util.Set;

/**
 * A non-empty zero-indexed array A consisting of N integers is given.
 * 
 * A permutation is a sequence containing each element from 1 to N once, and only once.
 * 
 * For example, array A such that:
 * 
 * A[0] = 4 A[1] = 1 A[2] = 3 A[3] = 2 is a permutation, but array A such that:
 * 
 * A[0] = 4 A[1] = 1 A[2] = 3 is not a permutation, because value 2 is missing.
 * 
 * The goal is to check whether array A is a permutation.
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.
 * 
 * For example, given array A such that:
 * 
 * A[0] = 4 A[1] = 1 A[2] = 3 A[3] = 2 the function should return 1.
 * 
 * Given array A such that:
 * 
 * A[0] = 4 A[1] = 1 A[2] = 3 the function should return 0.
 * 
 * Assume that:
 * 
 * N is an integer within the range [1..100,000]; each element of array A is an integer within the range [1..1,000,000,000]. Complexity:
 * 
 * expected worst-case time complexity is O(N); expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments). Elements
 * of input arrays can be modified.
 * 
 * @author isaolmez
 * 
 * */

public class PermCheck {
	public int solution(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}

		int length = A.length;
		Set<Integer> missing = new HashSet<>();
		missing.add(1);
		for (int i = 0; i < length; i++) {
			if (missing.contains(A[i])) {
				missing.remove(A[i]);
			}

			missing.add(A[i] + 1);
		}

		for (int i = 0; i < length; i++) {
			if (missing.contains(A[i])) {
				missing.remove(A[i]);
			}
		}

		System.out.println(missing);
		if (missing.size() == 1 && missing.iterator().next() > length) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		int[] test = { 1, 2,3,5 };
		PermCheck p = new PermCheck();
		System.out.println(p.solution(test));
	}
}
