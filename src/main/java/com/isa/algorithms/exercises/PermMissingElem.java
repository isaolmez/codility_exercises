package com.isa.algorithms.exercises;

import java.util.HashSet;
import java.util.Set;

/**
 * A zero-indexed array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 * 
 * Your goal is to find that missing element.
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a zero-indexed array A, returns the value of the missing element.
 * 
 * For example, given array A such that:
 * 
 * A[0] = 2 A[1] = 3 A[2] = 1 A[3] = 5 the function should return 4, as it is the missing element.
 * 
 * Assume that:
 * 
 * N is an integer within the range [0..100,000]; the elements of A are all distinct; each element of array A is an integer within the range [1..(N + 1)]. Complexity:
 * 
 * expected worst-case time complexity is O(N); expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments). Elements
 * of input arrays can be modified.
 * 
 * @author isaolmez
 * 
 * */
public class PermMissingElem {
	public int solution(int[] A) {
		if (A == null) {
			return -1;
		}

		int length = A.length;
		if (length == 0) {
			return 1;
		}

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
		for (int i : missing) {
			if (i <= length + 1) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] test = { 4, 5, 7, 1, 2, 3 };
		int[] test2 = { 1, 2, 3, 4, 5, 7 };
		int[] test3 = { 7, 5, 4, 3, 2, 1 };
		int[] test4 = { 6, 5, 4, 3, 2, 1 };
		int[] test5 = { 7, 6, 5, 4, 3, 2 };
		PermMissingElem p = new PermMissingElem();
		System.out.println(p.solution(test));
		System.out.println(p.solution(test2));
		System.out.println(p.solution(test3));
		System.out.println(p.solution(test4));
		System.out.println(p.solution(test5));
	}
}
