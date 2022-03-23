package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_S3_1463_1로만들기 {
    static int result, N;
    static int[] arr = new int[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr[1] = 0;
        for(int i=2; i<=N; i++){
            arr[i] = arr[i-1]+1; // 1을 뺀다
            if(i%2==0) arr[i] = Math.min(arr[i/2]+1,arr[i]);
            if(i%3==0) arr[i] = Math.min(arr[i/3]+1,arr[i]);
        }
        System.out.println(arr[N]);
    }

}
