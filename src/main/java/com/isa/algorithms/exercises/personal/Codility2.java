package com.isa.algorithms.exercises.personal;

import java.util.Arrays;

public class Codility2 {
	public int[] solution(int[] A) {
		int length = A.length;
		int lastIndex = length - 1;
		for (int k = 0; k < length - 1; k++) {
			if (A[k] == 1) {
				k++;
				A[k] = Math.abs(A[k] - 1);
				if (A[k] == 1) {
					lastIndex = k;
				} else {
					lastIndex = k - 1;
				}
			}
		}

		if (A[length - 1] == 1) {
			lastIndex = length;
			int[] result = Arrays.copyOf(A, lastIndex + 1);
			result[lastIndex] = 1;
			return result;
		} else {
			return Arrays.copyOf(A, lastIndex + 1);
		}
	}

	public static void main(String[] args) {
		int[] A = { 1, 0, 0, 1, 1 };
		Codility2 c = new Codility2();
		int[] solution = c.solution(A);
		for (int i = 0; i < solution.length; i++) {
			System.out.print(solution[i] + ",");
		}
	}
}
