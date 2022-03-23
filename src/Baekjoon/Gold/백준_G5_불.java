package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 해당 문제 틀렸던 이유 
 * 1) 불은 하나가 아니다 
 * 2) 불과 지훈이의 que , dist를 분리해야한다 (같게하면 뭔가 꼬임)
 * */

public class 백준_G5_불 {
	static char[][] map;
	static int[][] dist1, dist2; // 불의 전파 시간, 지훈이의 도착 시간
	static int[] Jihun, fire;
	static boolean[][] isVisited;
	static Queue<int[]> que1 = new LinkedList<int[]>(); // 불
	static Queue<int[]> que2 = new LinkedList<int[]>(); // 지훈
	static int R, C, result, cnt;
	static int[] dr = {1,-1,0,0}; // 상 하 좌 우
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		String str;

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		dist1 = new int[R][C]; // 몇 초뒤에 불타는지 저장
		dist2 = new int[R][C]; // 몇 초뒤에 도착하는지 저장
		isVisited = new boolean[R][C];
		result = -1;
		cnt = 0;
		
		Jihun = new int[2]; // 지훈 시작점 위치
		fire = new int[2];	// 불 시작점 위치
		
		for(int[] b : dist1) { // isBurned 에 들어가는 값 == -1이라면 시작 위치로부터의 거리를 넣자
			Arrays.fill(b, -1); // 불이 닿지 않은 곳 = -1
		}
		for(int[] b : dist2) {
			Arrays.fill(b, -1); 
		}
		
		
		for(int i=0; i<R; i++) {
			str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'J') {
					Jihun[0] = i;
					Jihun[1] = j;
				}else if(map[i][j] == 'F') {
					que1.offer(new int[] {i,j});
					dist1[i][j] = 0;
				}
			}
		}
	
		
		while(!que1.isEmpty()) {
			int[] cur = que1.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue; // 범위 밖이거나, 벽을 만나면 stop
				if(dist1[nr][nc] >= 0 || map[nr][nc] == '#') continue;	// 이미 불이 지나간 경우 , 더 빠르게 지나갔으면 pass
				dist1[nr][nc] = dist1[cur[0]][cur[1]] + 1; // 전 위치로부터 +1 시간
				que1.offer(new int[] {nr,nc});			
			}
		}
			
		
		
//		for(int[] c : dist1) {
//			System.out.println(Arrays.toString(c));
//		}

		// 미로 탈출 기준 : 각 가장자리까지 무사히 도착한다면 탈출 가능 
		boolean escape = false;
		que2.add(Jihun);
		dist2[Jihun[0]][Jihun[1]] = 0; // 시작 점 0
		
		while(!que2.isEmpty()) {
			int[] cur = que2.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C ) {	// 탈출 성공
					System.out.println(dist2[cur[0]][cur[1]]+1); // 결과 출력
					return;
				} 
				if(map[nr][nc] == '#' || dist2[nr][nc] >= 0) continue;  // 벽을 만나거나, 불이 붙은 곳이거나, 방문했던 곳이라면 stop	
				
				if(dist1[nr][nc] != -1 && dist1[nr][nc] <= dist2[cur[0]][cur[1]]+1) continue; // 불이 붙어있고, 현재 시간보다 빨리 붙이 붙었다면
				
				dist2[nr][nc] = dist2[cur[0]][cur[1]]+1; // 시간 입력
//				System.out.println(dist2[cur[0]][cur[1]]+"초 "+nr+", "+nc +" map[r][c] : "+map[nr][nc]+" dist1[r][c] : "+dist1[nr][nc]+" dist2[r][c] : "+dist2[nr][nc]);				
				que2.add(new int[] {nr,nc});
			}

		}
		System.out.println("IMPOSSIBLE");
	}

}
