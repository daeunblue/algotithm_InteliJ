package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_D3_규영이와인영이의카드게임 {
	static int[] guCards;	// 규영 카드
	static int[] inCards;	// 인영 카드
	static int[] output;
	static boolean[] visited;
	static boolean[] isUsed; // 규영의 카드인지 확인
	static int win,lose,input; // 승리 , 패배 횟수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			// 모든 변수, 배열 초기화
			guCards = new int[9]; 
			inCards = new int[9]; 
			output = new int[9];
			visited = new boolean[9]; 
			isUsed = new boolean[19]; // 1 ~ 18까지의 숫자중 어떤것이 남았는지 (true : 사용됨 false: 아직 사용x)
			win = 0 ;
			lose = 0;
		
			st = new StringTokenizer(br.readLine());
	
			for(int i=0; i<guCards.length; i++) {
				input = Integer.parseInt(st.nextToken());
				guCards[i] = input;
				isUsed[input] = true;
			}
			
			int index = 0;
			for(int i=1; i<isUsed.length; i++) {
				if(isUsed[i] == false) { // 규영이가 내지 않은 카드
					inCards[index++] = i;
				}
			}
						
			perm(inCards, output, visited, 0, 9 ,9);
			sb.append("#"+test_case+" "+win+" "+lose+"\n");
//			System.out.println("win: "+ win +"," +"lose: "+lose);
		}
		System.out.println(sb.toString());
	}
	
	
	
	// 순서를 지키면서 n 개중에서 r 개를 뽑는 경우
	// 예시: perm(arr, output, visited, 0, n, 3);
	static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
	    if (depth == r) {
	    	compare(guCards,output);
	        return;
	    }
	 
	    for (int i=0; i<n; i++) {
	        if (visited[i] != true) {
	            visited[i] = true;
	            output[depth] = arr[i];
	            perm(arr, output, visited, depth + 1, n, r);       
	            visited[i] = false;;
	        }
	    }
	}

	static void compare(int[] c1, int[] c2) {	// c1, c2 인덱스별로 비교
		int c1Sum = 0;
		int c2Sum = 0;
		
		for(int i=0; i<9; i++) {
			if(c1[i] > c2[i]) {
				c1Sum += c1[i] + c2[i];
			}else if(c1[i] < c2[i]) {
				c2Sum += c1[i] + c2[i];
			}
		}
		
		if(c1Sum < c2Sum) {
			 lose++;
		}else if(c1Sum > c2Sum) {
			win++;
		} 
		

	}
	
}