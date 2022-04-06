package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * {@auther} : daeun
 * @since : 2022-04-05
 * link : https://www.acmicpc.net/problem/15961
 * type : Sliding Window
 * */
public class 백준_G5_15961_회전초밥 {
    static int[] dishes = new int[3000003];
    static int[] sushi = new int[3003];
    static int N, D, K, C;
    static int maxCnt, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            dishes[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<K; i++){ // 첫번째 ~ K번째 스시까지 연속으로 먹음 처리하기
            if(sushi[dishes[i]] == 0)   sum++;   // sushi : 스시 종류별로 먹은 횟수를 카운트 하기위한 배열
            sushi[dishes[i]]++;
        }

        maxCnt = sum; // 0 ~ k-1번째 접시 까지의 스시 종류

        // 슬라이딩 윈도우 기법 활용 -> 한칸씩 밀면서, 겹치는 부분

        for(int i=0; i<N; i++){ // 한칸씩 밀면서 체크하기
            if(maxCnt <= sum){  // max값보다 더 많은 초밥을 먹을 수 있는 경우 -> 갱신하기
                if(sushi[C] == 0) {
                    sum++;  // 쿠폰으로 먹을 수 있는 초밥을 한번도 먹지 않은 경우 -> +1 해줘야함
                    sushi[C]++;
                }
                maxCnt = sum;
            }

            sushi[dishes[(i%N)]]--; // 범위 밖으로 벗어난 부분 --
            if(sushi[dishes[(i%N)]] == 0) sum--; // 해당 초밥을 한번만 먹었던 경우
            if(sushi[dishes[(K+i)%N]] == 0) sum++; // 새로 먹을 초밥을 먹은적이 없는 경우
            sushi[dishes[(K+i)%N]]++; // 새로 먹은 초밥 추가
        }

        System.out.println(maxCnt);
    }
}
