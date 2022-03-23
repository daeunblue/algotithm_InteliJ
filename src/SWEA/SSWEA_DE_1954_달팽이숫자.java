package SWEA;

import java.util.Scanner;


/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/

class SSWEA_DE_1954_달팽이숫자 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int k = 1;
			int right = -1;
			int bottom = 0;
			int top = 1;
			
			int[][] snail = new int[N][N];
			for (int i = N; i > 0; i--) {

				for (int j = 0; j < N; j++) {
					right += top;
					snail[bottom][right] = k;
					k++;
				}

				N--;

				for (int j = 0; j < N; j++) {
					bottom += top;
					snail[bottom][right] = k;
					k++;
				}

				top = top * (-1);
			}

			// 출력
			System.out.printf("#%d\n",test_case);
			for (int i = 0; i < snail.length; i++) {
				for (int j = 0; j < snail[i].length; j++) {
					
					System.out.printf("%d ", snail[i][j]);
				}
				System.out.println();
			}
		}
	}
}
