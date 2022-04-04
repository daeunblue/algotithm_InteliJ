package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_S2_123더하기3 {
    static int N,T;
    static long[] arr = new long[10000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());

            arr[1] = 1;
            arr[2] = 2;
            arr[3] = 4;
            arr[4] = 7;

            // 규칙 : arr[n] = ( arr[n-1] + arr[n-2] + arr[n-3] ) %  1000000009
            if(N>4){
                for(int i=4; i<=N; i++){
                    arr[i] = (arr[i-1] + arr[i-2] + arr[i-3]) %  1000000009 ;
                }
            }
            sb.append(arr[N]+"\n");
        }
        System.out.println(sb.toString());
    }
}
