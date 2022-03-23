package Baekjoon.Bronze;

import java.io.*;
import java.util.StringTokenizer;

public class 백준_B5_ABAll {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		sb.append(A+B+"\n");
		sb.append(A-B+"\n");
		sb.append(A/B+"\n");
		sb.append(A%B+"\n");
		

		System.out.println(sb.toString());
	}
}
