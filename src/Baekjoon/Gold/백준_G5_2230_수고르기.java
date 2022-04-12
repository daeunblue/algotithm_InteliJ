package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 백준_G5_2230_수고르기 {
    static int N, M, minSub;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list); // 오름차순 정렬

        minSub = Integer.MAX_VALUE;
        int end = 0;
        for(int start=0; start<N; start++){
            while(end < N && list.get(end) -list.get(start) < M ){
                end++;
            }
            if(end == N) break;
            minSub = Math.min(minSub, list.get(end) -list.get(start));
        }

        System.out.println(minSub);
    }

}
