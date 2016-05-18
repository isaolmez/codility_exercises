package com.isa.algorithms.exercises;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NailingPlanks {
	boolean found = false;

	public int solution(int[] A, int[] B, int[] C) {
		Arrays.sort(A);
		Arrays.sort(B);
		Set<Integer> backwardGuarantee = new HashSet<>();
		backwardGuarantee.add(0);
		Set<Integer> missed = new HashSet<>();
		boolean[] nailed = new boolean[A.length];
		int nailedCount = 0;
		int i = 0;
		for (; i < C.length; i++) {
			int nailPoint = C[i];
			found = false;
			int rankA = rankWithRightMost(nailPoint, A, 0, A.length - 1);
			if (found) {
				rankA++;
			}

			int rankB = rankWithLeftMost(nailPoint, B, 0, B.length - 1);
			System.out.printf("Nail position:, %d, %d, %d\n", nailPoint, rankA, rankB);
			for (int k = rankB; k < rankA; k++) {
				if (!nailed[k]) {
					nailedCount++;
					nailed[k] = true;
				}

				// if (backwardGuarantee.contains(k)) {
				// backwardGuarantee.remove(k);
				// } else {
				// missed.add(k);
				// }
				//
				// if (missed.contains(k + 1)) {
				// // missed.remove(k + 1);
				// } else {
				// if (k + 1 < A.length) {
				// backwardGuarantee.add(k + 1);
				// }
				// }
			}

			if (nailedCount == A.length) {
				return i + 1;
			}

			// if (backwardGuarantee.isEmpty()) {
			// return i + 1;
			// }
		}

		return -1;
	}

	private int rankWithLeftMost(int key, int[] B, int lo, int hi) {
		// System.out.println(lo + "-" + hi);
		if (lo > hi) {
			return lo;
		}

		int middle = (hi + lo) / 2;
		if (B[middle] == key) {
			int leftMost = rankWithLeftMost(key, B, lo, middle - 1);
			if (B[leftMost] != key) {
				return middle; // Inclusive
			} else {
				return leftMost; // Inclusive
			}
		} else if (key < B[middle]) {
			return rankWithLeftMost(key, B, lo, middle - 1);
		} else {
			return rankWithLeftMost(key, B, middle + 1, hi);
		}
	}

	private int rankWithRightMost(int key, int[] A, int lo, int hi) {
		// System.out.println(lo + "-" + hi);
		if (lo > hi) {
			return lo;
		}

		int middle = (hi + lo) / 2;
		if (A[middle] == key) {
			found = true;
			int rightMost = rankWithRightMost(key, A, middle + 1, hi);
			if (rightMost < A.length && A[rightMost] == key) {
				return rightMost;
			} else {
				return middle;
			}
		} else if (key < A[middle]) {
			return rankWithRightMost(key, A, lo, middle - 1);
		} else {
			return rankWithRightMost(key, A, middle + 1, hi);
		}
	}

	public static void main(String[] args) {
		int[] A = { 1, 5 };
		int[] B = { 3, 10 };
		int[] C = { 3, 5, 6, 1 };
		NailingPlanks n = new NailingPlanks();
		A = new int[]{1,1,1,1,1,1,1};
		// System.out.println(n.rankWithRightMost(3, A, 0, A.length - 1));
		System.out.println(n.solution(A, B, C));
	}
}
