package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_S1_곱셈 {
	static long A,B,C;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// A를 B번 곱한 수를 C로 나눈 나머지 출력
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		System.out.println(cal(A,B));

	}
	
	public static long cal(long a, long b) {
		if(b == 1)
			return a % C;
		
		long temp = cal(a, b/2);
		if(b %2 == 0)
			return temp * temp % C;
		else
			return(temp * temp % C) * a % C;
		
	}

}
