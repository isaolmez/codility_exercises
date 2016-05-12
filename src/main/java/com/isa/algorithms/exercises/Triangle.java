package com.isa.algorithms.exercises;

import java.util.Arrays;

/***
 * A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
 * 
 * A[P] + A[Q] > A[R], A[Q] + A[R] > A[P], A[R] + A[P] > A[Q]. For example, consider array A such that:
 * 
 * A[0] = 10 A[1] = 2 A[2] = 5 A[3] = 1 A[4] = 8 A[5] = 20 Triplet (0, 2, 4) is triangular.
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a zero-indexed array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.
 * 
 * For example, given array A such that:
 * 
 * A[0] = 10 A[1] = 2 A[2] = 5 A[3] = 1 A[4] = 8 A[5] = 20 the function should return 1, as explained above. Given array A such that:
 * 
 * A[0] = 10 A[1] = 50 A[2] = 5 A[3] = 1 the function should return 0.
 * 
 * Assume that:
 * 
 * N is an integer within the range [0..100,000]; each element of array A is an integer within the range [−2,147,483,648..2,147,483,647]. Complexity:
 * 
 * expected worst-case time complexity is O(N*log(N)); expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * 
 * @author isa
 *
 */
public class Triangle {

	public int solution(int[] A) {
		Arrays.sort(A);
		for (int i = 0; i < A.length - 2; i++) {
			if (isTriangle(A[i], A[i + 1], A[i + 2])) {
				return 1;
			}
		}

		return 0;
	}

	private void sort(int[] A, int start, int end) {
		if (start >= end) {
			return;
		}

		int partition = partition(A, start, end);
		sort(A, start, partition - 1);
		sort(A, partition + 1, end);
	}

	private int partition(int[] A, int start, int end) {
		int sentinel = A[start];
		int left = start;
		int right = end + 1;
		while (true) {
			while (A[++left] < sentinel) {
				if (left == end) {
					break;
				}
			}

			while (A[--right] > sentinel) {
			}

			if (left >= right) {
				break;
			}

			swap(A, left, right);
		}

		swap(A, start, right);
		return right;
	}

	private void swap(int[] A, int p, int q) {
		// System.out.printf("Swapping (%d, %d)\n", A[p], A[q]);
		int temp = A[p];
		A[p] = A[q];
		A[q] = temp;
	}

	private boolean isTriangle(int x, int y, int z) {
		// System.out.printf("Checking triplet(%d, %d, %d)\n", x, y, z);
		if ((long) x + y > z && (long) x + z > y && (long) y + z > x) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		Triangle t = new Triangle();
		int[] A = { 10, 2, 5, 1, 8, 20 };
		int[] A2 = { 10, 50, 5, 1 };
		System.out.println(t.solution(A2));
		for (int i : A2) {
			System.out.printf("%d, ", i);
		}
	}
}
