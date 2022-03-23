package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_D3_Flatten {
	static int [] boxes;
	static int H;	// 높이
	static final int W = 100;	// 넓이 (100 고정)
	static int diff; // Max - Min
	static int dumpCnt; // 총 덤프 가능한 횟수
	static int totalCnt = 0;	// 현재까지 덤프한 횟수
	
	public static void main(String[] args) throws IOException {
		/* 평탄화 작업
		 * 순서는 중요하지 않다!
		 * 1. 오름차순으로 높이를 전부 정렬한 다음
		 * 2. 횟수가 남았다면, 맨 앞(Min) +1 and 맨 뒤(Max) -1 -> 두 값의 차이 저장 
		 * 3. 횟수가 끝나면 -> 두 값의 차이 출력
		 * 4. 횟수가 끝나기전에 차이가 0 이 된다면 0 출력
		 * */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			dumpCnt = Integer.parseInt(st.nextToken());	

			st = new StringTokenizer(br.readLine());
			boxes = new int[100];
	
			for(int i=0; i<100; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(boxes); // 오름차순 정렬
			
			for(int i=0; i<dumpCnt; i++) {
				boxes[99] -= 1;
				boxes[0] += 1;
				Arrays.sort(boxes);
				diff = boxes[99] - boxes[0];
			}
			sb.append("#").append(test_case).append(" ").append(diff);
			sb.append("\n");
			
		
		}
		System.out.println(sb);;
	}
	


}
