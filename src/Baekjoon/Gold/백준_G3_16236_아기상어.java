package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_G3_16236_아기상어 {
	static int[][] map;
	static int N;
	static int[] dr = { 1, 0, 0, -1 }; // 상 , 좌, 우 , 하
	static int[] dc = { 0, -1, 1, 0 };
	static int size, eat, move;
	static int[] cur = null;
	static boolean isEat;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {	// 상어 위치 저장
					cur = new int[] { i, j }; // que에 저장 
					map[i][j] = 0;
				}
			}
		}

		size = 2;
		eat = 0; // 먹은 물고기 수
		move = 0; // 움직인 총 거리
		
		BFS();
		System.out.println(move);

	}

	public static void BFS() {
		while (true) {
			/*	정렬 기준 :
			 *  1) 가까운 곳부터 찾기 o1,o2[2] : 거리
			 *  2) 거리가 같다면, o1,o2[0] : row 비교하기 (위쪽이 더 우선순위 up)
			 *  3) row도 같다면, o1,o2[1] : col 비교하기 (왼쪽이 더 우선순위 up)
			 *  */
			PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> 
				o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))); // 상, 좌,  우, 하 분류
			boolean[][] visit = new boolean[N][N];

			que.add(new int[] { cur[0], cur[1], 0 }); // y좌표, x좌표, 이동한 거리
			visit[cur[0]][cur[1]] = true;

			isEat = false; // 상어가 먹이를 먹었는지 체크할 변수

			while (!que.isEmpty()) {
				cur = que.poll();

				if (map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size) { // 먹을 수 있는 상황
					map[cur[0]][cur[1]] = 0;	// 물고기 먹기 
					eat++;
					move += cur[2]; // 움직인 거리를 총 움직인 거리에 추가
					isEat = true; // 
					break;
				}

				for (int k = 0; k < 4; k++) {
					int nr = cur[0] + dr[k];
					int nc = cur[1] + dc[k];

					if (nr < 0 || nc < 0 || nc >= N || nr >= N || visit[nr][nc] || map[nr][nc] > size)	// 해당 칸의 값이 범위 밖이거나 , 물고기가 먹지 못하는 크기 (size보다 클때)
						continue;

					que.add(new int[] { nr, nc, cur[2] + 1 });
					visit[nr][nc] = true;
				}
			}

			if (!isEat) // 큐가 비워질 때까지 먹이를 먹은적이 없다면 break;
				break;

			if (size == eat) { // 상어 크기 == 먹은횟수 , 상어의 크기 +1
				size++;
				eat = 0;
			}
		}
	}
}
