package Baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_B1_11050_이항계수1 {
    static int N, K, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        result = 0;
        boolean[] isVisited = new boolean[N];
        combination(isVisited,0, N, K);
        System.out.println(result);

    }

    static void combination(boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            result++;
            return;
        }

        for(int i=start; i<n; i++) {
            visited[i] = true;
            combination(visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
}
