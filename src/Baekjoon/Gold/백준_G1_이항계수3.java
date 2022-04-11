package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_G1_이항계수3 {
    static long[] factorial;
    static int T, N, R;
    static int nLimit, p ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        nLimit = 4000000; // 자연수 N의 제한값
        p = 1000000007; // 나누는 수 P
        factorial = new long[nLimit+2];
        factorial[0] = 1;

        for(int i = 1; i <= nLimit; i++){
            factorial[i] = (i * factorial[i-1]) % p;
        }

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        long ans = (factorial[N] * pow( (factorial[R] * factorial[N-R]) % p, p-2)) % p;

        System.out.println(ans);

    }

    public static long pow(long a, long b){
        if(b == 0)
            return 1;
        long ans = pow(a,b / 2);
        long next = (ans * ans) % p;
        if(b % 2 == 0)
            return next;
        else
            return (next * a) % p;
    }


}
