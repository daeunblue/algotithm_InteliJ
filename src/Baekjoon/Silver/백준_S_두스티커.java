package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_S_두스티커 {
    static int result;
    static int H, W, N;
    static int[][] stickers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine().trim());

        stickers = new int[N][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stickers[i][0] = Integer.parseInt(st.nextToken());
            stickers[i][1] = Integer.parseInt(st.nextToken());
            stickers[i][2] = stickers[i][0] * stickers[i][1];
        }

        result = 0;

        calcMaxArea();

        System.out.println(result);
    }

    private static void calcMaxArea() {
        for(int i = 0; i < stickers.length; i++) {
            for(int j = i + 1; j < stickers.length; j++) {
                if(i == j) continue;
                if(isPossible(stickers[i], stickers[j])) { // 붙일 수 있는지 확인
                    result = Math.max(result, stickers[i][2] + stickers[j][2]);
                }
            }
        }
    }

    private static boolean isPossible(int[] a, int[] b) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if((a[i] + b[j]) <= H && Math.max(a[(i+1)%2], b[(j+1)%2]) <= W) return true; // 스티커를 붙일 수 있는지 판단은 두 시티커를 수평으로 붙여보고
                if((a[i] + b[j]) <= W && Math.max(a[(i+1)%2], b[(j+1)%2]) <= H) return true; //  수직으로 붙여보기
            }
        }
        return false;
    }
}
