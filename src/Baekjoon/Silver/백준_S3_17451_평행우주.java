package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_S3_17451_평행우주 {
    static int  N;
    static long result;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new long[N];
        for(int i=0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 풀이
        /* 1.  현재 속도 < 그 다음 행성 속도  : 최종속도 그 다음행성으로 업데이트
        *  2.  현재 속도 > 그 다음 행성 속도
        *  2-1. 현재 속도 % 다음 행성 속도 == 0 : 그대로 진행
        *  2-2. 아닌 경우 현재속도보다 빠르면서 다음 행성속도 * N 인 수 찾아서 업데이트
        * */

        result = arr[N-1]; // 첫번째 행성은 무조건 속도 업데이트해야하므로 처리

        for(int i=N-2; i>=0; i--){
            if(result < arr[i]){
                result = arr[i];
            }else{
                if(result % arr[i] != 0){
                    result = ((result/arr[i])+1) * arr[i]; // ceil(b/a)*a = b이상이면서 a의 배수인 수를 찾는 공식
                }
            }
        }
        System.out.println(result);
    }

}
