package Baekjoon.Silver;

import java.util.Scanner;

/**
 * 시간 초과 주의하기 -> System.out.print 사용하면 시간초과뜸 StringBuilder로 해결하자
 * **/
public class 백준_S1_하노이탑이동순서 {
	static int N, cnt;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
	// 하노이탑 최소 이동 횟수 = 2^n -1회
		sb.append((int)Math.pow(2, N)-1+"\n");
		hanoi(1,3,N);
		System.out.println(sb.toString());
	}
	public static void hanoi(int start, int end, int n) { // 시작위치, 종료위치, 원판의 개수
		if(n==1) {
			sb.append(start+" "+end+"\n");
			return;
		}
		
		hanoi(start, 6-start-end, n-1);	 //왜 6인지 : 1번기둥 2번기둥 3번기둥 = 1+2+3 = 6 이기 때문 
		sb.append(start+" "+end+"\n");
		hanoi(6-start-end, end, n-1);
	}
}
