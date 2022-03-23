package Baekjoon.Gold;

import java.util.*;
import java.io.*;

public class 백준_G4_1987_알파벳 {
	static int R,C;
	static char[][] map;
	static int[] dr = {-1,0,1,0}; //상, 우 , 하 , 좌
	static int[] dc = {0,1,0,-1};
	static boolean[] isVisited = new boolean[26]; // 알파벳 :26개
	static int result = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str;
		
		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = (char) (str.charAt(j)- 'A'); // A의 아스키코드 값을 빼줘야 isVisited에서 관리 가능
			}
		}
		
		dfs(0, 0, 0);
		System.out.println(result);
	}
	
	public static void dfs(int r, int c, int cnt) {
		int curAlpha = map[r][c];
		if (isVisited[curAlpha]) { // 이미 방문한적 있는 알파벳
			result = Math.max(result, cnt); 
			return; 
		} else {
			isVisited[curAlpha] = true;
			for (int i = 0; i < 4; i++) {
				int cr = r + dr[i];
				int cy = c + dc[i];
				// 사분면 범위 체크 후 탐색
				if (cr >= 0 && cy >= 0 && cr < R && cy < C) {
					dfs(cr, cy, cnt + 1);
				}
			}
			isVisited[curAlpha] = false;
		}
	}
}
