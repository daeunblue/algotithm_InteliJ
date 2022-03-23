package Baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BOJ_B2_2309_일곱난쟁이 {
	static int[] hats;
	static boolean[] visited;
	static int N = 9; // 총 9명의 난쟁이 입력
	static boolean isEnd = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		hats = new int[9];
		visited = new boolean[9];
		
		for(int i=0; i<N; i++) {
			hats[i] = Integer.parseInt(br.readLine());
		}
		
		comb(hats, visited, 0, 9, 7);

	}
	
	// 재귀 사용
	// 사용 예시 : comb(arr, visited, 0, n, r)
	static void comb(int[] arr, boolean[] visited, int depth, int n, int r) {
		if (r == 0) {
			print(arr, visited, n);
			// 재귀종료시점에 할일 구현
			return;
		}

		if (depth == n) {
			return;
		}

		visited[depth] = true;
		comb(arr, visited, depth + 1, n, r - 1);

		visited[depth] = false;
		comb(arr, visited, depth + 1, n, r);
	}

	// 배열 출력
	static void print(int[] arr, boolean[] visited, int n) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				sum += arr[i]; 
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		if(sum == 100) { // 일곱난쟁이 찾음
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					list.add(arr[i]);
				}
			}
		}
		if(!list.isEmpty() && isEnd == false) {
			Collections.sort(list);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
			isEnd = true;
		}
		
	}
}
