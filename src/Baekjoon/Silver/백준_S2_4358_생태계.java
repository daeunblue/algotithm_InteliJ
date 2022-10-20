package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_S2_4358_생태계 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String,Double> map = new HashMap<>();

        double count = 0;

        String str = "";

        while((str = br.readLine())!=null){
            map.put(str, map.getOrDefault(str,0.0)+1.0);  // getOrDefault() : 찾는 키가 존재하면 키값 반환, 없으면 기본값 반환
            count++;
        } // input


        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        for(String key : keys){
            System.out.print(key+" ");
            System.out.println(String.format("%.4f",map.get(key)/count*100));
        }
    }

}
