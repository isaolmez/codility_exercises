package com.isa.algorithms.exercises;

import java.util.ArrayList;
import java.util.List;

public class PrimeAndCompositeNumbers {
	public List<Integer> divisors(int n) {
		List<Integer> result = new ArrayList<>();
		int limit = (int) Math.sqrt(n);
		for (int i = 1; i < limit; i++) {
			if (n % i == 0) {
				result.add(i);
				result.add(n / i);
			}
		}

		if (n % limit == 0) {
			result.add(limit);
		}

		return result;
	}

	public boolean primality(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		PrimeAndCompositeNumbers p = new PrimeAndCompositeNumbers();
		System.out.println(p.divisors(16));
		System.out.println(p.primality(5));
	}
}
