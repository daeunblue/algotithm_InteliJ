package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_S1_1926_그림 {
	static int[][] map;
	static boolean[][]	isVisited;
	static Queue<int[]> que = new LinkedList<int[]>();
	static int N, M, artCnt, MaxWide; // 가로, 세로, 그림 수, 가장 넓은 그림의 넓이
	static int[] dr = {1,-1,0,0}; // 상 하 좌 우
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isVisited = new boolean[N][M];
		artCnt = 0; 
		MaxWide = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1 && isVisited[i][j] == false) {// 방문한 적 없을때만 bfs
					int curWide = 0;
					isVisited[i][j] = true;
					que.add(new int[] {i,j}); // 시작점 저장하고 bfs시작
					artCnt++; // 새 그림 추가
					// BFS
					while(!que.isEmpty()) {
						int[] cur = que.poll(); 
						curWide++;
						for(int dir=0; dir<4; dir++) {
							int nr = cur[0] + dr[dir];
							int nc = cur[1] + dc[dir];
							// 범위 안에 있는지, 방문한 적 있는지 확인
							if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
							if(isVisited[nr][nc] == true || map[nr][nc] != 1) continue;
							// 방문 가능한 경우
							isVisited[nr][nc] = true; // 방문 처리
							que.offer(new int[]{nr, nc});
						}
					}
					MaxWide = Math.max(curWide, MaxWide);
				}
			}
			
		}
		
		System.out.println(artCnt +"\n" + MaxWide);
	}
	

}
