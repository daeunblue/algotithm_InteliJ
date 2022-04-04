package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_S1_11507_오르막수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][10];

        for (int i = 0; i < 10; i++) {  // 초기값 설정 (모든 1자리수에서 1로 끝나는 경우는 1개씩만있음)
            dp[1][i] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {  //뒤에 붙일 수 있는 수는  j(0~9)부터 9까지 이므로
                    dp[i + 1][k] = (dp[i + 1][k] +dp[i][j])% 10007; // 현재까지 만들 수 있는 자리수를 i+1자리의 뒷자리가 k인 경우에 더해주기
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (dp[N][i] % 10007);
        }
        System.out.println(sum % 10007);
    }
}

