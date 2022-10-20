package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_G4_9082_지뢰찾기 {
    static int T, N;
    static int[] num;
    static char[] arr;
    static int result = Integer.MIN_VALUE;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            num = new int[N];
            arr = new char[N];
            boolean[] isVisited = new boolean[N];
            String str = br.readLine();
            int maxCnt = Integer.MIN_VALUE;
            for(int i=0; i<N; i++){
                num[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
                if (num[i] > maxCnt) maxCnt = num[i];
            }
            str = br.readLine();
            arr = str.toCharArray();

            int depth = 0;
            for(int i=0; i<N; i++){
                if(arr[i] == '*'){
                    depth++;
                    isVisited[i] = true;
                }
            }
            for(int i=N; i>=maxCnt; i--){
                if(!makeArr(isVisited, N, i, depth,0))break;
            }
            sb.append(result+"\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean makeArr(boolean[] isVisited, int n, int r, int depth, int start){
        if(depth == r){
            // 윗줄과 비교하기
            /*System.out.println(Arrays.toString(isVisited));
            System.out.println("------------------");*/
            if(check(r,isVisited)) return true;
            return  false;

        }
        for(int i=start; i<n; i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                makeArr(isVisited, n, r, depth+1, start+1);
                isVisited[i] = false;
            }
        }
        return false;
    }

    public static boolean check(int r, boolean[] isVisited){
        int[] checkArr = Arrays.copyOf(num, num.length);
        for(int i=0; i<checkArr.length; i++){
            if(isVisited[i]) {
                if(i>0) checkArr[i-1]--;
                checkArr[i]--;
                if(i<N-1) checkArr[i+1]--;
            }
        }

        boolean isCorrect = true;
        for(int i: checkArr){
            if(i != 0){
                isCorrect= false;
                break;
            }
        }
        if(isCorrect){
            result = Math.max(result, r);
            return true;
        }
        return false;

//        System.out.println(Arrays.toString(isVisited));
//        System.out.println(Arrays.toString(checkArr));
//        System.out.println("현재까지의 지뢰의 최댓값: "+result);
//        System.out.println("=====================");
    }
}
