package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 이번 문제를 틀린 이유 : 음식(신맛 + 쓴맛)은 한 묶음인데 , 신맛 따로 쓴맛 따로 라고 생각해서 각각의 부분집합으로 푸는 문제인줄
 * 알았다.. -> 실수 하지 말자 ㅠ
 * 
 * 분류 : 브루트포스 알고리즘, 비트마스킹(? -> 비트마스킹을 통한 )
 * 
 **/
public class BOJ_S1_2961_도영이가만든맛있는음식 {
	static int[] Sour; // 신맛
	static int[] Bitter; // 쓴맛

	static ArrayList<Integer> totalSour = new ArrayList<Integer>(); // 총 신맛
	static ArrayList<Integer> totalBitter = new ArrayList<Integer>(); // 총 쓴맛

	static int N, S, B; // 재료 수, 신맛 , 쓴맛
	static long minSub = -1; // 신맛-쓴맛의 최소값
	static boolean[] visited;
	private static int Sub;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		Sour = new int[N];
		Bitter = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Sour[i] = Integer.parseInt(st.nextToken());
			Bitter[i] = Integer.parseInt(st.nextToken());
		}

		combination(Sour, visited, N, 0);
		System.out.println(minSub);

	}

	static void combination(int[] arr, boolean[] visited, int n, int idx) {
		int visitT = 0;

		if (idx == n) {
			for (int i = 0; i < visited.length; i++) {
				if (visited[i] == true)
					visitT++;
			}

			if (visitT == 0)
				return;

			getSub(arr, visited, n);
			return;
		}
		// 선택 X
		visited[idx] = false;
		combination(arr, visited, n, idx + 1);
		// 선택 O
		visited[idx] = true;
		visitT++;
		combination(arr, visited, n, idx + 1);
	}

	static void getSub(int[] arr, boolean[] visited, int n) {
		int total = 0;
		S = 1;
		B = 0;

		for (int i = 0; i < n; i++) {
			if (visited[i] == true) {
				// 신맛 : 신 * 신 , 쓴 맛 : 쓴 + 쓴
				S *= Sour[i];
				B += Bitter[i];
			}
		}

		total = ((S - B) >= 0) ? S - B : -(S - B);

		if (total < minSub) {
			minSub = total;
		} else if (minSub == -1) {
			minSub = total;
		}

	}

}