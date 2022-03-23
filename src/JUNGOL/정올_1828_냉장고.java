package JUNGOL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 정올_1828_냉장고 {
	static int N;
	static int[][] arr;
	static int Min, Max; // 최저온도, 최고온도
	static int count = 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());	// low
			arr[i][1] = Integer.parseInt(st.nextToken());	// high
		}
		
		// 최고온도 오름차순 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {	
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		Max = arr[0][1];
		for(int i=1; i<N; i++) {
			if(Max < arr[i][0]) {
				Max = arr[i][1];
				count++;
			}
		}
		System.out.println(count);
		
	}
}
