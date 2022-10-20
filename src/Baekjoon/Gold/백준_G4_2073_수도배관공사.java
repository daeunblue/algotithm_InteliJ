package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_G4_2073_수도배관공사 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] dp = new int[D+1];
        dp[0] = Integer.MAX_VALUE;

        for(int i=0; i<P; i++){
            st = new StringTokenizer(br.readLine());
            // dp[x] : 수도관의 길이가 x일때의 최대 수도관의 용량
            // dp[x] = Math.max(해당 수도관을 사용하는것, 해당 수도관을 사용하지 않는 것)
            // dp[x] = Math.max(Math.min(현재 수도관 용량, dp[x-해당 수도관의 길이]), dp[x])

            int len = Integer.parseInt(st.nextToken());
            int vol = Integer.parseInt(st.nextToken());

            for(int x=D; x>=len; x--){
                dp[x] = Math.max(Math.min(vol, dp[x-len]), dp[x]);
            }
        }

        System.out.println(dp[D]);


    }
}
