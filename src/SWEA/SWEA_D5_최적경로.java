package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_D5_최적경로 {// SWEA
	static int T, N, comR, comC, homeR, homeC; // 테스트케이스 수, 고객 수, 회사좌표(r,c), 집 좌표(r,c)
	static int[][] user, out; // 고객 좌표 (N명)
	static int minRoute = Integer.MAX_VALUE; // 최단거리
	static boolean[] isVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			comR = Integer.parseInt(st.nextToken());
			comC = Integer.parseInt(st.nextToken());

			homeR = Integer.parseInt(st.nextToken());
			homeC = Integer.parseInt(st.nextToken());

			user = new int[N][2];
			out = new int[N][2];
			isVisited = new boolean[N];
			minRoute = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				user[i][0] = Integer.parseInt(st.nextToken());
				user[i][1] = Integer.parseInt(st.nextToken());
			}
			
			perm(user, out, isVisited, 0, N, N);
			sb.append(minRoute+"\n");
		}
		
		System.out.println(sb.toString());
	}

	static void perm(int[][] arr, int[][] output, boolean[] visited, int depth, int n, int r) {
		int route = 0;
		int curSum = 0;	
		
		// 가지치기 
		curSum = Math.abs(comR - output[0][0]) + Math.abs(comC - output[0][1]); // 회사에서 첫 고객한테
		for (int i = 1; i < depth; i++) {
			curSum += Math.abs(output[i][0] - output[i-1][0]) + Math.abs(output[i][1] - output[i-1][1]); // 현재 위치																// 이동거리
		}
		if(curSum > minRoute)	// 이미 최소값보다 큰 경우 (계산할 필요 X)
			return;
		
		if (depth == r) {
			// 종료 + 할일 적기 (재귀완료시점)
			route = Math.abs(comR - output[0][0]) + Math.abs(comC - output[0][1]); // 회사에서 첫 고객한테
			for (int i = 1; i < n; i++) {
				route += Math.abs(output[i][0] - output[i-1][0]) + Math.abs(output[i][1] - output[i-1][1]); // 현재 위치																// 이동거리
			}
			route += Math.abs(homeR - output[N - 1][0]) + Math.abs(homeC - output[N - 1][1]); // 마지막고객 ~ 집 까지의 경로

			if (route < minRoute) { // 현재 경로가 최단 경로일때!
				minRoute = route;
				return;
			} else
				return;
		}
		
		

		for (int i = 0; i < n; i++) {
			if (visited[i] != true) {
				visited[i] = true;
				output[depth][0] = arr[i][0]; // row 값
				output[depth][1] = arr[i][1]; // col 값
				perm(arr, output, visited, depth + 1, n, r);
				visited[i] = false;
			}
		}
	}
}