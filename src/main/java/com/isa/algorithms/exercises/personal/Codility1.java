package com.isa.algorithms.exercises.personal;

public class Codility1 {
	public int solution(int X, int[] A) {
		int length = A.length;
		int leftIndex = -1;
		int rightIndex = length;
		int rightPrevious = rightIndex;
		while (true) {
			while (rightIndex > 0) {
				if (A[--rightIndex] != X) {
					break;
				}
			}

			System.out.println("right index:" + rightIndex);
			while (leftIndex < length - 1) {
				if (A[++leftIndex] == X) {
					break;
				}
			}

			System.out.println("left index:" + leftIndex);
			if (leftIndex >= rightIndex) {
				// right -1 left -1
				if (leftIndex > rightPrevious) {
					return rightPrevious;
				}
				
				// right -1 left 0
				else if (leftIndex < rightPrevious) {
					return leftIndex;
				}
			}

			rightPrevious = rightIndex;
		}
	}

	public static void main(String[] args) {
		int[] A = new int[7];
		A[0] = 5;
		A[1] = 5;
		A[2] = 1;
		A[3] = 2;
		A[4] = 3;
		A[5] = 5;
		A[6] = 1;
		Codility1 c = new Codility1();
		System.out.println(c.solution(5, A));
	}
}
