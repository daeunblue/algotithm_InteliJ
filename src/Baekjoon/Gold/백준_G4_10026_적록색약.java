package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_G4_10026_적록색약 {
	static char[][] map;
	static boolean[][] isVisited;
	static int[] dr = {1,-1,0,0}; // 상 하 좌 우
	static int[] dc = {0,0,-1,1};
	static Queue<int[]> que = new LinkedList<int[]>();
	static int N, invisibleArea, visibleArea; // map 길이, 적록색약이 보는 구역(R == G), 아닌 사람이 보는 구역  
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		isVisited = new boolean[N][N];
		  
		invisibleArea = 0;
		visibleArea = 0;
		
		for(int i=0; i<N; i++) {
			str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		char cRGB;
		
		// 적녹색약 아닌 경우
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(isVisited[i][j]) continue; // 이미 들른곳은 체크 X
				isVisited[i][j] = true; // 시작점 방문 체크
				cRGB = map[i][j]; // 시작점의 문자가 뭔지 체크하기
				que.add(new int[] {i,j});
				
				visibleArea++;
				while(!que.isEmpty()) {	// bfs
					int[] cur = que.poll();
					for(int dir = 0; dir < 4; dir++) {
						int nr = cur[0] + dr[dir];
						int nc = cur[1] + dc[dir];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >=N) continue; // 범위 밖
						if(isVisited[nr][nc] || map[nr][nc] !=cRGB) continue; // 방문 했거나, 시작점의 문자와 다른경우 pass
						
						isVisited[nr][nc] = true; // 방문 체크
						que.add(new int[] {nr,nc});
					}
				}// end while
			}
		}// end for
	
		for(boolean[] b : isVisited) {
			Arrays.fill(b, false);	
		}
		// 적녹색약인 경우
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(isVisited[i][j]) continue; // 이미 들른곳은 체크 X
				isVisited[i][j] = true; // 시작점 방문 체크
				cRGB = map[i][j]; // 시작점의 문자가 뭔지 체크하기
				que.add(new int[] {i,j});
				
				invisibleArea++;
				while(!que.isEmpty()) {	// bfs
					int[] cur = que.poll();
					for(int dir = 0; dir < 4; dir++) {
						int nr = cur[0] + dr[dir];
						int nc = cur[1] + dc[dir];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >=N) continue; // 범위 밖
						if(cRGB == 'B') {
							if(isVisited[nr][nc] || map[nr][nc] !=cRGB) continue; // 방문 했거나, 시작점의 문자와 다른경우 pass
						}else {
							if(isVisited[nr][nc] || map[nr][nc] =='B') continue; // 적녹색약의 경우 (R == G) != B이기 때문
						}
							
						isVisited[nr][nc] = true; // 방문 체크
						que.add(new int[] {nr,nc});
					}
				}// end while
			}
		}// end for
		System.out.println(visibleArea+ " "+ invisibleArea);
	}

}
