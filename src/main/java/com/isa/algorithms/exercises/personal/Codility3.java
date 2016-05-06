package com.isa.algorithms.exercises.personal;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Codility3 {
	private class Node {
		int x;
		int y;
		int level;

		public Node(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}

	public int solution(int A, int B) {
		Queue<Node> queue = new LinkedBlockingQueue<>();
		queue.add(new Node(0, 0, 0));
		while (queue.size() != 0) {
			Node current = queue.remove();
			if (current.x == A && current.y == B) {
				return current.level;
			} else {
				queue.add(new Node(current.x + 2, current.y + 1, current.level+1));
				queue.add(new Node(current.x + 2, current.y - 1, current.level+1));
				queue.add(new Node(current.x - 2, current.y + 1, current.level+1));
				queue.add(new Node(current.x - 2, current.y - 1, current.level+1));
				queue.add(new Node(current.x + 1, current.y + 2, current.level+1));
				queue.add(new Node(current.x + 1, current.y - 2, current.level+1));
				queue.add(new Node(current.x - 1, current.y + 2, current.level+1));
				queue.add(new Node(current.x - 1, current.y - 2, current.level+1));
			}
			
			if(current.level>100000){
				break;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		Codility3 c = new Codility3();
		System.out.println(c.solution(4, 5));
		System.out.println(c.solution(0, 0));
	}
}
