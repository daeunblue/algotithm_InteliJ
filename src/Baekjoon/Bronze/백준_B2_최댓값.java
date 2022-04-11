package Baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_B2_최댓값 {
    static int MAX, index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        MAX = Integer.MIN_VALUE;

        for(int i=0; i<9; i++){
            int num = Integer.parseInt(br.readLine());
            if(num > MAX){
                MAX = num;
                index = i;
            }
        }
        index++;
        System.out.println(MAX + "\n" + index);
    }
}
