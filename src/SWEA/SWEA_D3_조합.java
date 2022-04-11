package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_조합 {
    static long[] factorial;
    static int T, N, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        factorial = new long[1000001];
        factorial[0] = 1;

        for(int i = 1; i <= 1000000; i++){
            factorial[i] = (i * factorial[i-1]) % 1234567891;
        }

        T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            long ans = (factorial[N] * pow( (factorial[R] * factorial[N-R]) % 1234567891, 1234567891-2)) % 1234567891;
            sb.append("#" + test_case + " " + ans + "\n");
        }
        System.out.println(sb);

    }

    // a^b
    public static long pow(long a, long b){
        if(b == 0)
            return 1;
        long ans = pow(a,b / 2);
        long next = (ans * ans) % 1234567891;
        if(b % 2 == 0)
            return next;
        else
            return (next * a) % 1234567891;
    }


}
