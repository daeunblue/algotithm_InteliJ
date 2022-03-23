package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D4_서로소집합 {
	static int T, N, M; // 테케 수, 집합의 수 , 주어지는 연산의 수
	static int[] arr;

	// 0 a b -> a가 포함된 집합 + b 포함 집합 합치기
	// 1 a b -> 두 원소가 같은 집합에 있는지 확인 => 같은 집합 : 1 아니면 0 출력

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			arr = MakeSet(N); // N 개의 집합 만들기
			sb.append("#"+test_case+" ");
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());

				if (first == 0) { // union
					union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				} else { // find
					int a = find(Integer.parseInt(st.nextToken()));
					int b = find(Integer.parseInt(st.nextToken()));

					if (a == b)	// 둘이 같은 집합인지 확인 
						sb.append("1");
					else
						sb.append("0");
				}
			}
			sb.append("\n");
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
	}

	// Find
	private static int find(int x) {
		if (arr[x] == x) // 찾는 인덱스가 부모일때 
			return x;
		else	// 재귀로 구현 (부모를 찾을 때 까지 반복) 
			return arr[x] = find(arr[x]);
	}
}
