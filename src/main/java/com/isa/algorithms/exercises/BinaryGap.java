package com.isa.algorithms.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.
 * 
 * For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one
 * of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no
 * binary gaps.
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int N); }
 * 
 * that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.
 * 
 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5.
 * 
 * Assume that:
 * 
 * N is an integer within the range [1..2,147,483,647]. Complexity:
 * 
 * expected worst-case time complexity is O(log(N)); expected worst-case space complexity is O(1).
 * 
 * @author isaolmez
 **/
public class BinaryGap {
	public int solution(int N) {
		if (N <= 0) {
			return 0;
		}

		Integer[] binary = toBinary(N);
		boolean gapStarted = false;
		int largestGap = 0;
		int gapCount = 0;
		for (int i = 0; i < binary.length; i++) {
			if (binary[i] == 1) {
				if (gapCount > largestGap) {
					largestGap = gapCount;
				}

				gapStarted = true;
				gapCount = 0;
			} else {
				if (gapStarted) {
					gapCount++;
				}
			}
		}

		return largestGap;
	}

	private Integer[] toBinary(int number) {
		List<Integer> result = new ArrayList<>();
		while (number / 2 != 0) {
			result.add(number % 2);
			number = number / 2;
		}

		result.add(number);

		System.out.println(result);
		Integer[] arr = new Integer[result.size()];
		return (Integer[]) result.toArray(arr);
	}

	public static void main(String[] args) {
		BinaryGap binaryGap = new BinaryGap();
		System.out.println(binaryGap.solution(1041));
		System.out.println(binaryGap.solution(9));
	}
}
