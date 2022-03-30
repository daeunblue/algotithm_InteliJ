package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * 문제 해결법 -> 치즈를 기준으로 하지말고 공기를 기준으로 탐색하자
 * -> 공기의 4면중 닿는 부분을 -1로 만들어버리고 (큐에 저장해 뒀다가 1초 지난후 이 지점부터 시작) 완탐으로 1초가 지날때마다 -1 -> 0으로 변환시켜주기
 * **/
public class 백준_G5_2636_치즈 {
	static int[][] map;
	static boolean[][] isVisited;
	static int R, C;
	static Queue<int[]> que = new LinkedList<>();
	static int[] dr = {1, -1, 0, 0};	// 상, 하, 좌, 우
	static int[] dc = {0, 0, -1, 1};
	static boolean flag = true;
	static int cheeseCnt, time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		isVisited = new boolean[R][C];
		
		for(int i=0; i<R; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		time = 0;
		// 구현
		que.offer(new int[] {0,0});
		while(flag){
			cheeseCnt = 0;
			while(!que.isEmpty()){
				int[] cur = que.poll();
				isVisited[cur[0]][cur[1]] = true;

				for(int dir=0; dir<4; dir++){
					int nr = cur[0] + dr[dir];
					int nc = cur[1] + dc[dir];

					if(nr >= R || nr < 0 || nc >= C || nc < 0 ) continue; // 범위초과
					if(isVisited[nr][nc]) continue; // 중복방문 or 방문할 필요없는 경우
					if(map[nr][nc]==0 && isVisited[nr][nc] == false){
						isVisited[nr][nc] = true;	// 방문처리를 꼭 해주자 ! <-- 이부분 때문에 메모리 초과가 났었다 why? 방문 처리를 안하게 되면 que에 중복값이 들어가기 때문!
						que.offer(new int[]{nr,nc});
					}
					if(isVisited[nr][nc] == false && map[nr][nc] == 1){	// 다음 턴에 산화될 치즈 -1로 설정하기
						map[nr][nc] = -1;
						isVisited[nr][nc] = true;
					}
				}
			}

			int cCnt = 0;	// 1초 후에도 산화되지 않을 치즈의 개수
			for(int i=0; i<R; i++){
				for(int j=0; j<C; j++){
					if(map[i][j] == -1){
						map[i][j] = 0; // 산화 시키기
						que.offer(new int[]{i,j});	//산화된 지점 -> 새로운 시작점
						cheeseCnt++;
					}
					if(map[i][j] == 1)
						cCnt ++;
				}
			}

			if(cCnt == 0) flag = false;
			time++; // 1초 초과

//			for(int[] m : map){
//				System.out.println(Arrays.toString(m));
//			}
//
//			System.out.println("---------------------");
		}
		System.out.println(time+"\n"+cheeseCnt);
	}

}
