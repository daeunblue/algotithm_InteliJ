package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_최장증가부분순열 {
    static int N, T, result;
    static int[] arr = new int[1005];
    static int[] lis = new int[1005];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            lis[0] = arr[0]; // 초기값 설정
            int j = 0;
            int i = 1;
            while(i<N){
                if(lis[j] < arr[i]){
                    lis[j+1] = arr[i];
                    j++;
                }else{
                    int position = binarySearch(0, j, arr[i]);
                    lis[position] = arr[i];
                }
                i++;
            }
            sb.append("#"+test_case+" "+(j+1)+"\n");
        }
        System.out.println(sb.toString());
    }

    public static int binarySearch(int left, int right, int target){
        int mid ;

        while(left < right){
            mid = (left+right)/2;
            if(lis[mid] < target){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return right;
    }
}
