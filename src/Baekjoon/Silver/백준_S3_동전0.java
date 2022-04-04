package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_S3_동전0 {
    static int N, K, cnt;
    static int[] coin = new int[11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        int i = N-1;

        while(i >= 0){
            if(K == 0)
                break;

            if(coin[i] > K) {
                i--;
                continue;
            }else {
                int c = K / coin[i]; // 몫 = 동전 수
                if(c != 0){
                    cnt += c;
                    K = K % coin[i]; // 나머지 = 남은 금액
                }
               i--;
            }
        }
        System.out.println(cnt);
    }
}
