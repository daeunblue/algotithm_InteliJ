package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_S2_회의실배정 {
    static int N;
    static PriorityQueue<Conference> pq = new PriorityQueue<>();

    static class Conference implements Comparable<Conference>{
        int start;
        int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Conference o) {
           // 종료시간이 같다면 -> 좀더 빨리 시작하는 사람이 앞쪽에
           // A 종료시간 < B 종료시간 -> A가 앞쪽에
           // A 종료시간 > B 종료시간 -> B가 앞쪽에
           if(this.end < o.end){
               return -1;
           }else if(this.end == o.end){
               if(this.start < o.start){
                   return -1;
               }else{
                   return 1;
               }
           }else{
               return 1;
           }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int minEnd = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Conference(start,end));
        }

        int result = 1; // 회의 수
        Conference con = pq.poll();
        int start = con.start;
        int end = con.end;
    
        while(!pq.isEmpty()){
            Conference curCon = pq.poll();
            int cStart = curCon.start;
            int cEnd =  curCon.end;
            
            if(cStart < end) continue; // 이전 회의 종료시간보다 해당 회의 시작시간이 빠른 경우
            start = curCon.start;
            end = curCon.end;
            result++;
        }

        System.out.println(result);

    }
}
