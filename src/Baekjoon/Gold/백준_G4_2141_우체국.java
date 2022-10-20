package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 백준_G4_2141_우체국 {
    static int N, answer;
    static ArrayList<Town> list = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    static int minSum = Integer.MAX_VALUE;

    static class Town implements Comparable{
        int location;
        int population;

        public Town(int location, int population) {
            this.location = location;
            this.population = population;
        }

        @Override
        public int compareTo(Object o) {
            Town nTown = (Town) o;
            if(this.population >= nTown.population) return -1;
            else return 1;
        }

        @Override
        public String toString() {
            return "Town{" +
                    "location=" + location +
                    ", population=" + population +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            int num, cnt;

            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            cnt = Integer.parseInt(st.nextToken());
            list.add(new Town(num, cnt));
        }
        // 인구수대로 정렬 후 (내림차순으로 정렬)
        Collections.sort(list);


        for(int i=0; i<N; i++){
            calculate(list.get(i).location);
        }
        System.out.println(min);

    }

    public static void calculate(int location){
        int sum = 0; //각 사람들까지의 거리의 합
        for(Town t : list){
            int sub = Math.abs(location -t.location); // 거리는 절댓값으로 처리
            sum += sub * t.population;
            if(sum > minSum) return; // 거리의 합이 최소값을 넘어가면 더이상 계산할 필요 없음
        }
        if(sum == minSum){
            if(location < min) min = location; // 가능한경우 여러개 -> 가장 작은 위치
            return;
        }
        minSum = sum;
        min = location;
//        System.out.println(minSum);
        return;
    }
}
