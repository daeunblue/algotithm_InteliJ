package Baekjoon.Silver;

import java.util.*;
import java.io.*;

public class BOJ_S5_카드놓기 {
	// hashset -> 중복값 허용 x 
	static HashSet<String> numbers = new HashSet<String>();
	static String [] input;
	static boolean isVisit[];
	static int N,K;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		input = new String[N];
		isVisit = new boolean[N];
		
		for(int i=0; i<N; i++) {
			input[i] = br.readLine();
		}
		
		card(0,"");
		System.out.println(numbers.size());
	}
	public static void card(int cnt, String str) {
		if(cnt == K) {
			numbers.add(str);
			return ;
		}
		for(int i=0; i<N; i++) {
			if(!isVisit[i]) {
				isVisit[i] = true;
				card(cnt+1, str+input[i]);
				isVisit[i] = false;
			}
		}
	}
}

