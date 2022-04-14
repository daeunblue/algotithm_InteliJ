package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_구간합 {
    /*
    * 풀이법
    * 0 ~ 9
    * 10 ~ 99 : 1 + 0 ~ 9 / 2 + 0 ~ 9 / 3 + 0 ~ 9... 와 같이 끝에 한자리수만 남기고 앞자리를 더하면 된다
    *
    * 구간 A ~ B 의 입력 값이 10억정도기 떄문에 2중 for문으로 해결 불가능 -> N^2은 시간 초과
    * 즉 , logN이나 N으로 풀어야함
    * */

    static long[] total;
    static int[] sum;
    static int T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        sum = new int[10];
        sum[1] = 1;
        for(int i=2; i<10; i++) {
            sum[i] = i + sum[i - 1];
        }


        total = new long[16];
        total[1] = sum[9];
        for(int i=2; i<=15; i++){
            total[i] =  total[1] * (long)Math.pow(10, i-1) + total[i-1]*10;
        }

        for(int t=1;t<=T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            System.out.println("#"+t+" "+(solve(b)-solve(a-1)));
        }
    }

    public static long solve(long num) {
        if(num<0) return 0;
        if(num<10) return sum[(int)num];

        String str = String.valueOf(num);
        int z = str.length()-1;
        int x = str.charAt(0) - '0';
        long y = Long.parseLong(str.substring(1));

        return sum[x-1] * (long) Math.pow(10, z) + x* total[z] + x*(y+1) + solve(y);
    }

}
