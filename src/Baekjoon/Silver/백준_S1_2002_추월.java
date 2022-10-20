package Baekjoon.Silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 백준_S1_2002_추월 {
    static int N, answer;
    static int[] arr;
    static Map<String,Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = 0;

        for (int i = 0; i < N; i++) {
            map.put(br.readLine(), i);
        }

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = map.get(br.readLine());
        }

        for(int i = 0; i< N-1; i++){
            for(int j = i+1; j< N; j++){
                if(arr[i] > arr[j]){
                    answer += 1;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}
