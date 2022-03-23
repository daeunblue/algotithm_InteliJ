package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  date : 2020-02-10
 *  url : https://www.acmicpc.net/problem/1158
 * **/
public class BOJ_S4_1158_요세푸스문제 {
    static Queue<Integer> queue = new LinkedList<>();
    static int temp, N, K, answer;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++){
            queue.add(i);
        }

        sb.append("<");
        for(int k=0; k<N ;k++){
            Yose(0);

        }
        for(int j=0; j<N-1; j++){
            sb.append(result.get(j)+", ");
        }
        sb.append(result.get(N-1)+">");
        System.out.println(sb.toString());

    }

    public static void Yose(int cnt){
        if(cnt == K-1){
            result.add(queue.poll());
            return;
        }
        temp = queue.poll();
        queue.add(temp);
        Yose(cnt+1);

    }
}