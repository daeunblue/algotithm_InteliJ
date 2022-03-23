package Baekjoon.Bronze;

import java.io.*;
import java.util.StringTokenizer;

public class 백준_B5_AXB {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		System.out.println(Integer.parseInt(st.nextToken())*Integer.parseInt(st.nextToken()));
	}

}
