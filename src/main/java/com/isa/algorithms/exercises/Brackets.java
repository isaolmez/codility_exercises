package com.isa.algorithms.exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * 
 * A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
 * 
 * S is empty; S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string; S has the form "VW" where V and W are properly nested strings. For example, the string
 * "{[()()]}" is properly nested but "([)()]" is not.
 * 
 * Write a function:
 * 
 * class Solution { public int solution(String S); }
 * 
 * that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
 * 
 * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
 * 
 * Assume that:
 * 
 * N is an integer within the range [0..200,000]; string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")". Complexity:
 * 
 * expected worst-case time complexity is O(N); expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
 * 
 * @author isa
 *
 */
public class Brackets {
	public int solution(String S) {
		Map<String, String> map = new HashMap<>();
		map.put(")", "(");
		map.put("]", "[");
		map.put("}", "{");
		
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < S.length(); i++) {
			stack.push(S.substring(i, i + 1));
			while (!stack.isEmpty()) {
				String current = stack.pop();
				if (current.equals(")") || current.equals("]") || current.equals("}")) {
					if (!stack.isEmpty()) {
						String previous = stack.pop();
						if (!previous.equals(map.get(current))) {
							return 0;
						}
					} else {
						return 0;
					}
				}else{
					stack.push(current);
					break;
				}
			}
		}

		return stack.size() == 0 ? 1 : 0;
	}

	public static void main(String[] args) {
		String S = "{[()()]}";
		Brackets b = new Brackets();
		System.out.println(b.solution(S));
	}
}
