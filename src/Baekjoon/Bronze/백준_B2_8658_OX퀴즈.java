package Baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_B2_8658_OX퀴즈 {
    static int N, correct, inCorrect;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            correct = 0;
            inCorrect = 0;

            int cnt = 0;
            int sum = 0;

            String str = br.readLine();

            for(int k=0; k<str.length(); k++){
                if(str.charAt(k) == 'X'){
                    sum += cnt;
                    cnt = 0;
                }
                else{
                    sum += cnt;
                    cnt++;
                }
            }

            sum += cnt;
            sb.append(sum+"\n");
        }
        System.out.println(sb.toString());
    }
}
