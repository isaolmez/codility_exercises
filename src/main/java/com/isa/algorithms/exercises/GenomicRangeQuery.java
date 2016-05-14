package com.isa.algorithms.exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. Each nucleotide
 * has an impact factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer several queries of the
 * form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?
 * 
 * The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. There are M queries, which are given in non-empty arrays P and Q, each
 * consisting of M integers. The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K]
 * (inclusive).
 * 
 * For example, consider string S = CAGCCTA and arrays P, Q such that:
 * 
 * P[0] = 2 Q[0] = 4 P[1] = 5 Q[1] = 5 P[2] = 0 Q[2] = 6 The answers to these M = 3 queries are as follows:
 * 
 * The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2. The part between positions
 * 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4. The part between positions 0 and 6 (the whole string) contains all nucleotides, in
 * particular nucleotide A whose impact factor is 1, so the answer is 1. Assume that the following declarations are given:
 * 
 * struct Results { int * A; int M; };
 * 
 * Write a function:
 * 
 * struct Results solution(char *S, int P[], int Q[], int M);
 * 
 * that, given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M
 * integers specifying the consecutive answers to all queries.
 * 
 * The sequence should be returned as:
 * 
 * a Results structure (in C), or a vector of integers (in C++), or a Results record (in Pascal), or an array of integers (in any other programming language). For example, given
 * the string S = CAGCCTA and arrays P, Q such that:
 * 
 * P[0] = 2 Q[0] = 4 P[1] = 5 Q[1] = 5 P[2] = 0 Q[2] = 6 the function should return the values [2, 4, 1], as explained above.
 * 
 * Assume that:
 * 
 * N is an integer within the range [1..100,000]; M is an integer within the range [1..50,000]; each element of arrays P, Q is an integer within the range [0..N − 1]; P[K] ≤ Q[K],
 * where 0 ≤ K < M; string S consists only of upper-case English letters A, C, G, T. Complexity:
 * 
 * expected worst-case time complexity is O(N+M); expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * 
 * @author isaolmez
 * 
 */

/**
 * NOT RUNNING/COMPLETE!
 * */
public class GenomicRangeQuery {
	private Map<Integer, Integer> impactMap;
	{
		impactMap = new HashMap<>();
		impactMap.put((int) 'A', 1);
		impactMap.put((int) 'C', 2);
		impactMap.put((int) 'G', 3);
		impactMap.put((int) 'T', 4);
	}

	private char[][] priorityTable;

	public int[] solution(String S, int[] P, int[] Q) {

		int levelCount = (int) Math.ceil(Math.log(S.length()) / Math.log(2));
		char[] chars = S.toCharArray();
		priorityTable = new char[levelCount + 1][];
		priorityTable[0] = chars;
		for (int subLength = 2, levelIndex = 1; subLength <= S.length(); subLength *= 2, levelIndex++) {
			priorityTable[levelIndex] = new char[(int) Math.ceil((double) S.length() / subLength)];
			for (int traverser = 0, subIndex = 0; traverser < priorityTable[levelIndex - 1].length; traverser += 2, subIndex++) {

				int last = (traverser + 1) < priorityTable[levelIndex - 1].length ? (traverser + 1) : traverser;
				priorityTable[levelIndex][subIndex] = (char) Math.min(priorityTable[levelIndex - 1][traverser], priorityTable[levelIndex - 1][last]);
			}
		}

		int[] result = new int[P.length];
		for (int i = 0; i < P.length; i++) {
			int start = P[i];
			int end = Q[i];
			result[i] = impactMap.get(findmMin(start, end, levelCount));
		}

		return result;
	}

	public int findmMin(int start, int end, int level) {
		// Find the appropriate slices
		int min = Integer.MAX_VALUE;
		if (level == 0) {
//			for (int i = start; i <= end; i++) {
//				min = Math.min(priorityTable[level][i], min);
//			}
			
			return Math.min(priorityTable[level][start], priorityTable[level][end]);
		}

		int step = (int) Math.pow(2, level);
		if (start % step == 0) {
			while (start + step <= end) {
				min = Math.min(min, priorityTable[level][start / step]);
				start += step;
			}

			int subMinRight = findmMin(start, end, level - 1);
			min = Math.min(min, subMinRight);
		} else {
			int temp = start;
			start = start + (step - (start % step));
			if (start <= end) {
				int subMinLeft = findmMin(temp, start-1, level - 1);
				while (start + step <= end) {
					min = Math.min(min, priorityTable[level][start / step]);
					start += step;
				}

				int subMinRight = findmMin(start, end, level - 1);
				min = Math.min(min, Math.min(subMinLeft, subMinRight));
			} else {
				min = findmMin(temp, end, level - 1);
			}
		}

		return min;
	}

	public static void main(String[] args) {
		int[] p = { 2, 5, 0 };
		int[] q = { 4, 5, 6 };

		GenomicRangeQuery g = new GenomicRangeQuery();
		int[] result = g.solution("CAGCCTA", p, q);
		for (int i : result) {
			System.out.println(i);
		}
	}
}
