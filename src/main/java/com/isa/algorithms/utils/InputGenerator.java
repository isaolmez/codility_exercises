package com.isa.algorithms.utils;

import java.util.Random;

public class InputGenerator {
	public static int[] randomArray(int size) {
		int[] A = new int[size];
		Random r = new Random();
		for (int m = 0; m < size; m++) {
			A[m] = r.nextInt();
		}

		return A;
	}

	public static int[] randomArray(int size, int negativeLimit, int positiveLimit) {
		int[] A = new int[size];
		Random r = new Random();
		for (int m = 0; m < size; m++) {
			A[m] = r.nextInt(positiveLimit - negativeLimit + 1) + negativeLimit;
		}

		return A;
	}
	
	public static int[] randomLeaderArray(int size) {
		int[] A = new int[size];
		Random r = new Random();
		int leader = r.nextInt();
		System.out.println("Leader: " + leader);
		for (int m = 0; m < (size - 1) / 2; m++) {
			A[m] = r.nextInt();
		}

		for (int k = (size - 1) / 2; k < size; k++) {
			A[k] = leader;
		}

		return A;
	}
}
