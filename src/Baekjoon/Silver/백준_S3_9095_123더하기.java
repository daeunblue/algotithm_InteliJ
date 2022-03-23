package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_S3_9095_123더하기 {
    static int result, N,T;
    static int[] arr = new int[11];
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

            if(N>4){
                for(int i=5; i<=N; i++){
                    arr[i] = arr[i-1] + arr[i-2] + arr[i-3] ;
                }
            }
            sb.append(arr[N]+"\n");
        }
        System.out.println(sb.toString());
    }

}
