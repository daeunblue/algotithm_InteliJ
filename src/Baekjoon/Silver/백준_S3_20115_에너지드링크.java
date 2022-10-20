package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 백준_S3_20115_에너지드링크 {
    /* solution
    * 손실을 최소화 하려면? : 전부 남기는 드링크 = 제일 양 많은 것 , 절반 버리는 드링크 = 제일 양 적은 것*/
    static int  N;
    static double result;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);

        result = list.get(list.size()-1);
        for(int i=0; i<N-1; i++){
            result += list.get(i)/2;
            if(list.get(i)%2 == 1){
                result += 0.5;
            }
        }
        System.out.println(result);
    }
}
