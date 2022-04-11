package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 백준_S4_1920_수찾기 {
    static int N, M;
    static ArrayList<Integer> input = new ArrayList<>();
    static ArrayList<Integer> result = new ArrayList<>();
    static ArrayList<Integer> check = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            input.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        // 이분탐색으로 범위 나눠서 탐색
        // 1. Array 오름차순 정렬
        Collections.sort(input);

        for(int i=0; i<M; i++){
            sb.append(binarySearch(0, input.size()-1, Integer.parseInt(st.nextToken()))+"\n");
        }
        System.out.println(sb.toString());
    }

    private static int binarySearch(int start, int end, int target){
        while(start <= end){
            int mid = (start + end)/2;

            if(target < input.get(mid)) {
                end = mid - 1;
            }
            else if(target > input.get(mid)) {
                start = mid + 1;
            }
            else {
                return 1;
            }
        }
        return 0; // target이 arr에 존재하지 않음 (탐색 실패)
    }
}
