package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_S3_2579_계단오르기 {
    static int result, N,T;
    static int[][] arr = new int[302][3];
    static int[] stairs = new int[302];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int i=1; i<=T; i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        arr[1][1] = stairs[1];
        arr[1][2] = 0;
        arr[2][1] = stairs[2];
        arr[2][2] = stairs[1]+stairs[2];

        for(int n=3; n<=T; n++){
            arr[n][1] = Math.max(arr[n-2][1],arr[n-2][2])+stairs[n];
            arr[n][2] = arr[n-1][1]+stairs[n];
        }

        System.out.println(Math.max(arr[T][1],arr[T][2]));
    }

}
