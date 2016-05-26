package com.isa.algorithms.exercises;

import java.util.Random;

/******
 * A non-empty zero-indexed array A consisting of N integers is given.
 * 
 * The leader of this array is the value that occurs in more than half of the elements of A.
 * 
 * An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same
 * value.
 * 
 * For example, given array A such that:
 * 
 * A[0] = 4 A[1] = 3 A[2] = 4 A[3] = 4 A[4] = 4 A[5] = 2 we can find two equi leaders:
 * 
 * 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4. 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader,
 * whose value is 4. The goal is to count the number of equi leaders.
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.
 * 
 * For example, given:
 * 
 * A[0] = 4 A[1] = 3 A[2] = 4 A[3] = 4 A[4] = 4 A[5] = 2 the function should return 2, as explained above.
 * 
 * Assume that:
 * 
 * N is an integer within the range [1..100,000]; each element of array A is an integer within the range [−1,000,000,000..1,000,000,000]. Complexity:
 * 
 * expected worst-case time complexity is O(N); expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input
 * arguments). Elements of input arrays can be modified.
 * 
 * @author isa
 *
 */
public class EquiLeader {
	public int solution(int[] A) {
		if (A == null || A.length < 2) {
			return 0;
		}

		int[] eqLeft = new int[A.length - 1];
		int[] eqRight = new int[A.length - 1];
		int stackSize = 0;
		int top = 0;
		for (int i = 0; i < A.length - 1; i++) {
			// Manage stack
			if (stackSize == 0) {
				top = A[i];
				stackSize++;
			} else {
				if (top != A[i]) {
					stackSize--;
				} else {
					stackSize++;
				}
			}

			eqLeft[i] = stackSize > 0 ? top : Integer.MIN_VALUE;
		}

		stackSize = 0;
		top = 0;
		for (int i = A.length - 2; i >= 0; i--) {
			// Manage stack
			if (stackSize == 0) {
				top = A[i + 1];
				stackSize++;
			} else {
				if (top != A[i + 1]) {
					stackSize--;
				} else {
					stackSize++;
				}
			}

			eqRight[i] = stackSize > 0 ? top : Integer.MIN_VALUE;
		}

		int count = 0;
		for (int i = 0; i < eqLeft.length; i++) {
			if (eqLeft[i] != Integer.MIN_VALUE && eqLeft[i] == eqRight[i]) {
				System.out.printf("isLeader(index, candidate) (%d, %d)\n", i, eqLeft[i]);
				if (isLeader(A, 0, i, eqLeft[i]) && isLeader(A, i + 1, A.length - 1, eqLeft[i])) {
					count++;
				}
			}
		}

		return count;
	}

	private boolean isLeader(int[] A, int start, int end, int candidate) {
		int count = 0;
		for (int i = start; i <= end; i++) {
			if (A[i] == candidate) {
				count++;
			}
		}

		if (count > (end - start + 1) / 2) {
			return true;
		}

		return false;
	}

	protected int[] randomLeaderArray() {
		int size = 300;
		int[] A = new int[size];
		Random r = new Random();
		int leader = -1_000_000_000;
		System.out.println("Leader: " + leader);
		for (int i = 0; i < 50; i++) {
			A[i] = r.nextInt(100);
		}

		for (int m = 50; m < 250; m++) {
			A[m] = leader;
		}

		for (int k = 250; k < size; k++) {
			A[k] = r.nextInt(100);
		}

		return A;
	}

	public static void main(String[] args) {
		EquiLeader e = new EquiLeader();
		int[] A = { 4, 3, 4, 4, 4, 2 };
		// int[] A = { 2, 1, 1, 3, 4, 2, 1, 1, 3, 4 };
		A = e.randomLeaderArray();
		System.out.println(e.solution(A));
	}
}
