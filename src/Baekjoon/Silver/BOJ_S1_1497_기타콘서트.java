package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_S1_1497_기타콘서트 {
	static int N, M, minG; // minG :기타 최소 개수
	static int maxSong = -1;
	static String [][] gInfo;
	static boolean isSelectedGuitar[];
	static boolean isPlay[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		gInfo = new String[N][2];
		
		isSelectedGuitar = new boolean[N];
		isSelectedGuitar = new boolean[N];
		
		for(int i=0; i<N; i++) {	// 총 N개의 기타
			st = new StringTokenizer(br.readLine());
//			hmap.put(st.nextToken(), st.nextToken());
		}
//		System.out.println(hmap.toString());
		
		// 1곡 조차 연주 불가능하다면 -1 출력
		if(maxSong == -1) {
			System.out.print("-1");
		}
	}
	
	private static void getGuitarCnt(int count) { // count: 현재까지 고려한 원소 수
		if(count==N) {
			for(int i=0; i<N; i++) {
				if(isSelectedGuitar[i]) {	// 선택된 기타의 연주 가능 곡 추가하기
					for(int k=0; i<M; k++) {
					}
				}	
			}
			return;
		}
		isSelectedGuitar[count] = true;
		getGuitarCnt(count+1);
		isSelectedGuitar[count] = false;
		getGuitarCnt(count+1);
	}
	
}
