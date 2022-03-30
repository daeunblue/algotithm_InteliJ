package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * @author daeun
 * 
 * 문제 해결법 -> isVisited에 현재까지의 이동 횟수를 추가해서 풀면 메모리 초과가 나지 않는다
 * -> Monkey 라는 객체를 만들어서 Queue에 담자 => 헷갈릴수도 있기 때문에 객체로 만들어버리자
 * **/

public class 백준_G5_1600_말이되고싶은원숭이  {
	static int R, C,K;
	static int[][] map;
	static int result = Integer.MAX_VALUE;    // 최종 이동 횟수

	static Queue<Monkey> que = new LinkedList<>();
	static boolean[][][] isVisited;
	static int[] dr = {-1, 1, 0, 0};    // 원숭이 처럼 이동
	static int[] dc = {0, 0, -1, 1};
	static int[] hdr = {-2, -2, -1, -1, +1, +1, +2, +2};    // 말처럼 이동
	static int[] hdc = {-1, +1, -2, +2, -2, +2, -1, +1};

	static class Monkey {
		public int r, c;
		public int K;		// 말 처럼 이동 가능한 횟수 
		public int total;		// 총 이동 횟수

		public Monkey(int r, int c, int K, int total) {
			this.c = c;
			this.r = r;
			this.K = K;
			this.total = total;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		isVisited = new boolean[R][C][K + 1];
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		isVisited[0][0][0] = true;
		que.add(new Monkey(0, 0, 0, 0)); // 초기 위치 설정
		BFS();

		if (result != Integer.MAX_VALUE)
			System.out.println(result);
		else
			System.out.println(-1);
	}



	public static void BFS() {
		while (!que.isEmpty()) {
			Monkey cur = que.remove();
			int curK = cur.K;
			int cnt = cur.total;

			if (cur.r == R - 1 && cur.c == C - 1) {
				result = cur.total;
				return;
			}

			// 원숭이
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C)    // 다음 지점이 범위 밖인 경우
					continue;

				// 인접한 지점이 평지이고, 아직 방문 안한 경우
				if (map[nr][nc] == 0 && !isVisited[nr][nc][curK]) {
					isVisited[nr][nc][curK] = true;
					que.add(new Monkey(nr, nc, curK, cnt + 1));
				}
			}

			// 말
			if (curK < K) {
				for (int i = 0; i < 8; i++) {
					int nr = cur.r + hdr[i];
					int nc = cur.c + hdc[i];

					if (nr < 0 || nr >= R || nc < 0 || nc >= C)    // 범위 체크
						continue;
					if (map[nr][nc] == 0 && !isVisited[nr][nc][curK + 1]) {
						isVisited[nr][nc][curK + 1] = true;
						que.add(new Monkey(nr, nc, curK + 1, cnt + 1));
					}
				}
			}
		}
	}






}
