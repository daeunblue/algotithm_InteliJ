package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_S3_15649_N과M1 {
    static int N,M;
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];

        for(int i=0; i<N; i++){
            arr[i] = i+1;
        }

        int[] result = new int[N];
        permutation(arr, result, visited, N, 0,M);
        System.out.println(sb.toString());
    }

    static void permutation(int[] arr, int[] result, boolean[] isVisited, int n, int depth,int r){
        if(depth == r){
            print(result, r);
            return;
        }
        for(int i=0; i<n; i++){
            if(!isVisited[i]){
                isVisited[i] = true; // 방문처리
                result[depth] = arr[i];
                permutation(arr,result, isVisited, n, depth+1, r);
                isVisited[i] = false;
            }
        }
    }
    static void print(int[] arr, int r){
        for(int i=0; i<r; i++){
            sb.append(arr[i]+" ");
        }
        sb.append("\n");
    }
}
