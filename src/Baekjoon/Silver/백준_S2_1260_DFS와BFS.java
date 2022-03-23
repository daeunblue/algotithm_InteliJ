package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_S2_1260_DFS와BFS {
	static int N, M, V; // 정점의 개수, 간선의 개수, 탐색 시작 정점 번호
	static boolean[][] arr; // 행렬
	static boolean[] isVisited;
	static Queue<Integer> que = new LinkedList<>(); // BFS를 위한 큐
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		arr = new boolean[N + 1][N + 1]; // 1부터 index 사용
		isVisited = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 양방향 그래프이므로 둘 다 true로 바꿔주기
			arr[a][b] = true;
			arr[b][a] = true;
		}
//		for(boolean[] a : arr) {
//			System.out.println(Arrays.toString(a));
//		}
		DFS(V);
		sb.append("\n");
		Arrays.fill(isVisited, false); // BFS에서도 사용해야하기 때문에 초기화
		BFS(V);
		System.out.println(sb.toString());
	}

	public static void DFS(int v) {
		if(isVisited[v] == true) return; 
		
		isVisited[v] = true;
		sb.append(v+" ");
		
		for(int i=1; i<arr.length; i++) {
			if(arr[v][i] == true) { 	// 방문 X + 인접 노드인 경우
				DFS(i);
			}
		}
	}

	
	public static void BFS(int v) { // queue를 이용한 bfs구현
		que.offer(v);	// 시작 노드
		isVisited[v] = true;	
		
		while(!que.isEmpty()) {	// 큐가 빌때까지 반복
			v = que.poll();
			sb.append(v+" ");
			for(int i =1;i<arr.length;i++) { 		
				if(isVisited[i] != true && arr[v][i] == true ) {	// 방문 X + 이웃 노드
					que.offer(i); // 큐 삽입
					isVisited[i] = true; // visited 수정
				}
			}
		}
	}

}
