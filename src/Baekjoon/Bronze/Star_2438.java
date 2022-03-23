package Baekjoon.Bronze;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Star_2438 {

	public static void main(String[] args) throws Exception, IOException {
		// 첫째 줄에는 별 1개, 둘째 줄에는 별 2개, N번째 줄에는 별 N개를 찍는 문제
		// input ) N 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<=i; j++) {
				System.out.print("*");	
			}
			System.out.println("");
		}
		
	}
}
