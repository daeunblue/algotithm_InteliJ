package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_7465_창용마을무리의개수 {
	static int T, N, M; // 테케수, 사람 수, 관계 수 
	static int[] arr;
	static int result = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = MakeSet(N); // 그룹 생성
			result = N;
			for(int i=0; i<M; i++) {	// 그룹 합치기
				st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));				
			}
			sb.append("#"+test_case+" "+result +"\n");
		}
		
		System.out.println(sb.toString());
	}

// makeSet
	private static int[] MakeSet(int size) {
		int[] arr = new int[size + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		return arr;
	}

	// Union
	private static void union(int a, int b) {

		a = find(a);
		b = find(b);

		//더 작은 값으로 다른 집합이 편입
		if (a > b) {
			arr[a] = b;
		} else {
			arr[b] = a;
		}
		if(a == b)
			return;
		else
			result--;
	}

	// Find
	private static int find(int x) {
		if (arr[x] == x) // 찾는 인덱스가 부모일때 
			return x;
		else	// 재귀로 구현 (부모를 찾을 때 까지 반복) 
			return arr[x] = find(arr[x]);
	}

}
