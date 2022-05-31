package Baekjoon.Gold;

import java.util.*;
import java.io.*;

public class 백준_G3_15681_트리와쿼리 {

    public static ArrayList<Integer>[] tree;
    public static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int r = Integer.parseInt(st.nextToken()); // 루트 정점
        int q = Integer.parseInt(st.nextToken()); // 쿼리 개수

        dp = new int[n+1];

        tree = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        checkSubTree(r); // 정점부터 서브트리 노드 수 파악

        for(int i = 0; i < q; i++){
            int u = Integer.parseInt(br.readLine());
            sb.append(dp[u]+"\n");
        }

        sb.toString();

    }


    public static int checkSubTree(int node){
        if(dp[node] != 0){
            return 0;
        }

        dp[node] = 1;

        for(int next : tree[node]){
            dp[node] += checkSubTree(next);
        }

        return dp[node];
    }
}