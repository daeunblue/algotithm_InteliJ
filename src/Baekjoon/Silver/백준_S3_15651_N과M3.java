package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_S3_15651_N과M3 {
    static int N,M;
    static boolean[] isVisited = new boolean[10];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        permutation(new int[M],N, 0, M, 0);
        System.out.println(sb.toString());
    }
    public static void permutation(int[] output, int n, int depth, int r, int cnt){
        if(depth == r){
            print(output, r);
            return;
        }

        for(int i=1; i<=n; i++){
            output[depth] = i;
            permutation(output, n, depth+1, r, cnt+1);
        }

    }
    public static void print(int[] output, int r)
    {
        for(int out: output){
            sb.append(out+" ");
        }
        sb.append("\n");
    }
}
