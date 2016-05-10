package com.isa.algorithms.exercises;

/***
 * 
 * We draw N discs on a plane. The discs are numbered from 0 to N − 1. A zero-indexed array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th
 * disc is drawn with its center at (J, 0) and radius A[J].
 * 
 * We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).
 * 
 * The figure below shows discs drawn for N = 6 and A as follows:
 * 
 * A[0] = 1 A[1] = 5 A[2] = 2 A[3] = 1 A[4] = 4 A[5] = 0
 * 
 * 
 * There are eleven (unordered) pairs of discs that intersect, namely:
 * 
 * discs 1 and 4 intersect, and both intersect with all the other discs; disc 2 also intersects with discs 0 and 3. Write a function:
 * 
 * int solution(int A[], int N);
 * 
 * that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return −1 if the number of
 * intersecting pairs exceeds 10,000,000.
 * 
 * Given array A shown above, the function should return 11, as explained above.
 * 
 * Assume that:
 * 
 * N is an integer within the range [0..100,000]; each element of array A is an integer within the range [0..2,147,483,647]. Complexity:
 * 
 * expected worst-case time complexity is O(N*log(N)); expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * 
 * @author isa
 *
 */
public class NumberOfDiscIntersections {
	public int solution(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}

		int base = 0;
		int[] contrastArray = new int[A.length * 2];
		for (int i = 0; i < A.length; i++) {
			long total = A[i];
			if (total >= Math.max(A.length - 1 - i, i)) {
				base++;
				continue;
			}

			int scaledIndex = 2 * i;
			contrastArray[scaledIndex]++;
			for (int k = 1; k <= 2 * total; k++) {
				if (scaledIndex + k < contrastArray.length) {
					contrastArray[scaledIndex + k]++;
				}

				if (scaledIndex - k >= 0) {
					contrastArray[scaledIndex - k]++;
				}
			}
		}

		int numberOfDiscIntersections = 0;
		if (contrastArray[0] + base > 1) {
			numberOfDiscIntersections = combination(contrastArray[0] + base);
		}

		// System.out.printf("%d, ", contrastArray[0]);
		for (int i = 1; i < contrastArray.length; i++) {
			// System.out.printf("%d, ", contrastArray[i]);
			if (numberOfDiscIntersections > 10000000) {
				return -1;
			}

			if (contrastArray[i] > contrastArray[i - 1]) {
				numberOfDiscIntersections += combinationDifference(contrastArray[i] + base, contrastArray[i - 1] + base);
			}
		}

		// System.out.println("base:" + base);
		return numberOfDiscIntersections;
	}

	private int combination(int m) {
		return (m * (m - 1)) >> 1;
	}

	private int combinationDifference(int m, int n) {
		return ((m - n) * (m + n - 1)) >> 1;
	}

	public int solutionFirst(int[] A) {
		int leftMargin = 0;
		int rightMargin = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] - i > leftMargin) {
				leftMargin = A[i] - i;
			}

			if (A[i] + i - A.length + 1 > rightMargin) {
				rightMargin = A[i] + i - A.length + 1;
			}
		}

		int[] contrastArray = new int[(A.length + leftMargin + rightMargin) * 2];
		for (int i = 0; i < A.length; i++) {
			int scaledIndex = 2 * (i + leftMargin);
			int total = A[i];
			for (int k = 0; k <= 2 * total; k++) {
				if (k == 0) {
					contrastArray[scaledIndex]++;
				} else {
					contrastArray[scaledIndex + k]++;
					contrastArray[scaledIndex - k]++;
				}
			}
		}

		int numberOfDiscIntersections = 0;
		if (contrastArray[0] > 1) {
			numberOfDiscIntersections = combination(contrastArray[0]);
		}

		// System.out.printf("%d, ", contrastArray[0]);
		for (int i = 1; i < contrastArray.length; i++) {
			// System.out.printf("%d, ", contrastArray[i]);
			if (contrastArray[i] > contrastArray[i - 1] && contrastArray[i] > 1) {
				numberOfDiscIntersections += combination(contrastArray[i]) - combination(contrastArray[i - 1]);
			}
		}

		// System.out.println();
		return numberOfDiscIntersections;
	}

	public static void main(String[] args) {
		int[] test1 = { 1, 5, 2, 1, 4, 0 };
		int[] test2 = { 1, 2, 3 };
		int[] test3 = { 1, 3, 6 };
		NumberOfDiscIntersections n = new NumberOfDiscIntersections();
		System.out.println(n.solution(test1));
		System.out.println(n.solution(test2));
		System.out.println(n.solution(test3));
	}
}
