package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_S3_15650_N과M2 {
    static int N,M;
    static boolean[] isVisited = new boolean[10];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        permutation(isVisited, new int[N], N, 0, M);
        System.out.println(sb.toString());
    }
    public static void permutation(boolean[] isVisited, int[] output,int n, int depth, int r){
        if(depth == r){
            print(output, r);
        }
        for(int i=0; i<n; i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                output[depth] = i+1;
                permutation(isVisited, output, n,depth+1, r);
                isVisited[i] = false;
            }
        }
    }

    public static void print(int[] output, int r){
        // 여기서 오름차순인지 체크해야하나? 해보자
        int before = output[0];
        for(int i=0; i<r; i++){
            if(before > output[i]) return;
            before = output[i];
        }
        for(int k=0; k<r; k++){
            sb.append(output[k]+" ");
        }
        sb.append("\n");
    }
}
