package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_S1_1149_RGB거리 {
    static int N;
    static int[] R,G,B;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        R = new int[1004];
        G = new int[1004];
        B = new int[1004];
        DP = new int[1004][4];

        N = Integer.parseInt(br.readLine());

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            R[i] = Integer.parseInt(st.nextToken());
            G[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        // DP[i][color] : i번째집을 color로 칠했을때의 최소 비용
        DP[1][1] = R[1];
        DP[1][2] = G[1];
        DP[1][3] = B[1];

        for(int k=2; k<=N; k++){
            DP[k][1] = Math.min(DP[k-1][2],DP[k-1][3])+R[k];
            DP[k][2] = Math.min(DP[k-1][1],DP[k-1][3])+G[k];
            DP[k][3] = Math.min(DP[k-1][2],DP[k-1][1])+B[k];
        }
        System.out.println(Math.min(DP[N][1],Math.min(DP[N][2],DP[N][3])));
    }
}
