package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_D4_정사각형방 {
	static int[][] arr, visited;
	static int N, startP, maxRoom;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N =  Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new int[N][N];
			startP = 0;
			maxRoom = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] =  Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dfs(arr[i][j], 1,  i, j);
				}
			}
			
			System.out.println("#" + test_case + " " + startP + " " + maxRoom);
		}
	}
	
	public static void dfs(int start, int count, int r, int c) {
		visited[r][c] = 1;
		
		for(int i = 0; i < dr.length; i++) {	
			int nx = r + dr[i];
			int ny = c + dc[i];
			
			if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if(arr[nx][ny] == (arr[r][c] + 1) && visited[nx][ny] == 0)	// 1만큼 크면서, 방문한적 없으면 호출
					dfs(start, count + 1, nx, ny);
			}
		}
		if(count > maxRoom) {	// 최대값 등장
			maxRoom = count;
			startP = start;
		}
		if(count == maxRoom) {	// 동일값 -> 시작점이 작은 값을 저장
			startP = Math.min(startP, start);
		}
		visited[r][c] = 0;
	}
}