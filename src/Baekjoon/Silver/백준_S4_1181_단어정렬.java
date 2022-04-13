package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_S4_1181_단어정렬 {
    static PriorityQueue<Word> pq = new PriorityQueue<>();
    static Set<String> set = new HashSet<>();
    static int N;

    static class Word implements Comparable<Word>{
        String value;

        public Word(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(Word o) {
            if(this.value == o.value){
                return 0;
            }
            // 길이 비교 먼저 -> 같다면 그때 사전순으로 정렬
            if(this.value.length() > o.value.length()){
                return 1;
            }else if (this.value.length() == o.value.length()){
                return this.value.compareTo(o.value);
            }else{
                return -1;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            set.add(br.readLine());
        }


        Iterator iter = set.iterator();	// Iterator
        while(iter.hasNext()) {//값이 있으면 true 없으면 false
            pq.offer(new Word((String)iter.next()));
        }

        for(int k=0; k<set.size(); k++){
            sb.append(pq.poll().value+"\n");
        }
        System.out.println(sb.toString());
    }
}
