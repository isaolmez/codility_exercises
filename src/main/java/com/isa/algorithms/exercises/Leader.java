package com.isa.algorithms.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import com.isa.algorithms.utils.Stopwatch;

public class Leader {
	public int showLeaderBasic(int[] A) {
		for (int i = 0; i < A.length; i++) {
			int candidate = A[i];
			int count = 0;
			for (int k = 0; k < A.length; k++) {
				if (A[k] == candidate) {
					count++;
				}
			}

			if (count > A.length / 2) {
				return candidate;
			}
		}

		return -1;
	}

	public int showLeaderWithSorted(int[] A) {
		Arrays.sort(A);
		int candidate = A[(A.length - 1) / 2];
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate) {
				count++;
			}
		}

		if (count > A.length / 2) {
			return candidate;
		}

		return -1;
	}

	public int showLeaderWithMap(int[] A) {
		/** Statistics */
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < A.length; i++) {
			if (!map.containsKey(A[i])) {
				if (!map.isEmpty()) {
					Integer key = map.keySet().iterator().next();
					if (map.get(key) == 1) {
						map.remove(key);
					} else {
						map.put(key, map.get(key) - 1);
					}
				} else {
					map.put(A[i], 1);
				}
			} else {
				map.put(A[i], map.get(A[i]) + 1);
			}
		}

		if (!map.isEmpty()) {
			int candidate = map.keySet().iterator().next();
			if (isLeader(A, candidate)) {
				return candidate;
			}
		}

		return -1;
	}

	public int showLeaderWithStack(int[] A) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < A.length; i++) {
			if (!stack.isEmpty() && stack.peek() != A[i]) {
				stack.pop();
			} else {
				stack.push(A[i]);
			}
		}

		if (!stack.isEmpty()) {
			int candidate = stack.peek();
			if (isLeader(A, candidate)) {
				return candidate;
			}
		}

		return -1;
	}

	public int showLeaderWithStackBehaviour(int[] A) {
		int stackSize = 0;
		int top = 0;
		for (int i = 0; i < A.length; i++) {
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
		}

		if (stackSize > 0) {
			int candidate = top;
			if (isLeader(A, candidate)) {
				return candidate;
			}
		}

		return -1;
	}

	private boolean isLeader(int[] A, int candidate) {
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate) {
				count++;
			}
		}

		if (count > A.length / 2) {
			return true;
		}

		return false;
	}

	protected int[] randomLeaderArray(int size) {
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

	protected int[] randomArray(int size) {
		int[] A = new int[size];
		Random r = new Random();
		for (int m = 0; m < size; m++) {
			A[m] = r.nextInt();
		}

		return A;
	}

	public static void main(String[] args) {
		// int[] A = { 4, 2, 1, 1, 3, 3, 3, 3, 4, 3, 1, 2, 3, 3, 3 };
		int[] A = { 2, 1, 1, 3, 4 };
//		A = new int[] { 1, 1, 1, 2, 3, 4 };
		Leader l = new Leader();
		Stopwatch watch;
		A = l.randomArray(1000000);
		watch = new Stopwatch();
		// System.out.println(l.showLeaderBasic(A));
		// System.out.println("Elapsed time: " + watch.elapsedTime());

		watch = new Stopwatch();
		System.out.println(l.showLeaderWithStackBehaviour(A));
		System.out.println("showLeaderWithStackBehaviour => Elapsed time: " + watch.elapsedTime());

		watch = new Stopwatch();
		System.out.println(l.showLeaderWithStack(A));
		System.out.println("showLeaderWithStack => Elapsed time: " + watch.elapsedTime());

		watch = new Stopwatch();
		System.out.println(l.showLeaderWithMap(A));
		System.out.println("showLeaderWithMap => Elapsed time: " + watch.elapsedTime());

		watch = new Stopwatch();
		System.out.println(l.showLeaderWithSorted(A));
		System.out.println("showLeaderWithSorted => Elapsed time: " + watch.elapsedTime());

		System.out.println("*************");
		A = l.randomLeaderArray(1000000);
		watch = new Stopwatch();
		// System.out.println(l.showLeaderBasic(A));
		// System.out.println("Elapsed time: " + watch.elapsedTime());

		watch = new Stopwatch();
		System.out.println(l.showLeaderWithStackBehaviour(A));
		System.out.println("showLeaderWithStackBehaviour => Elapsed time: " + watch.elapsedTime());

		watch = new Stopwatch();
		System.out.println(l.showLeaderWithStack(A));
		System.out.println("showLeaderWithStack => Elapsed time: " + watch.elapsedTime());

		watch = new Stopwatch();
		System.out.println(l.showLeaderWithMap(A));
		System.out.println("showLeaderWithMap => Elapsed time: " + watch.elapsedTime());

		watch = new Stopwatch();
		System.out.println(l.showLeaderWithSorted(A));
		System.out.println("showLeaderWithSorted => Elapsed time: " + watch.elapsedTime());

	}
}
