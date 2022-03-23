package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_G4_15686_치킨배달 {
	static int N, M, result; // map크기 , 선택해야 할 치킨집 수 ,모든 치킨거리의 합의 최솟값,  모든 치킨거리의 합, 치킨거리 , 치킨거리 계산용 변수
	static int[][] map;
	static int[] isVisited;
	static ArrayList<int[]> chicken = new ArrayList<int[]>();
	static ArrayList<int[]> house = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = Integer.MAX_VALUE;

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][j] = a;
				if (a == 0)
					continue;
				else if (a == 1) {
					int[] arr = { i, j };
					house.add(arr);
				} else if (a == 2) {
					int[] arr = { i, j };
					chicken.add(arr);
				}
			}
		}
		isVisited = new int[chicken.size()];
		comb(0,0);
		System.out.println(result);
	}

    private static void comb(int cur, int cnt) {
        if (cnt == M) {
            result = Math.min(result, checkLength());
            return;
        }
 
        for (int i = cur; i < chicken.size(); i++) {
            isVisited[cnt] = i;
            comb(i + 1, cnt + 1);
        }
    }

    private static int checkLength() {
        int sum = 0;
 
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 1) {
                    int min = Integer.MAX_VALUE;
                    for (int k = 0; k < M; k++) {
                        int[] store = chicken.get(isVisited[k]);
                        int d = Math.abs(r - store[0]) + Math.abs(c - store[1]);
                        min = Math.min(min, d);
                    }
                    sum += min;
                }
            }
        }
 
        return sum;
    }

}
