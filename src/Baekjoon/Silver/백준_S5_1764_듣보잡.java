package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_S5_1764_듣보잡 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<String> set = new HashSet<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            set.add(br.readLine());
        }

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if(set.contains(name)) list.add(name);
        }
        Collections.sort(list);
        System.out.println(list.size());
        for (String s : list) {
            System.out.println(s);
        }
    }
}
