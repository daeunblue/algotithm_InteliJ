package Baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_B1_2839_설탕배달 {
	/*
	 * 정확하게 N kg 배달 3kg 5 kg 총 두 봉지 최대한 적은 봉지로 N kg 배달
	 * 
	 * (출력) 배달하는 봉지의 최소 개수 출력 정확하게 N kg을 만드는것이 불가능하다면 -1 출력
	 */
	static int N;
	static int minCnt = -1; // 최솟값
	static int remain;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		remain = N;

		if (modFive(remain) == false) {
			modThree(remain);
		}
		System.out.println(minCnt);

	}

	// 5로 나누기
	public static boolean modFive(int n) {
		int share = 0; // 몫
		minCnt = 0;
		if ((share = n / 5) != 0) {
			n = n % 5;

			if (n == 0) {
				// 5로 전부 다 나눠질 떄 (항상 제일 작음 -> 비교할 필요 X)
				minCnt += share;
				return true;
			} else {

				minCnt += share;
				share = n / 3;

				if ((share != 0) && (n % 3) == 0) {
					// 남은 나머지가 3으로 나눠질 떄
					minCnt += share;
					return true;
				} else {
					// 나눠지지 않을 때 -> 5의 몫을 1개 쓰고 n+5를 해서 3으로 다시 나눠보기
					while (minCnt != 0) {
						minCnt--;
						n += 5;
						share = n / 3;
						if ((share != 0) && (n % 3) == 0) {
							// 남은 나머지가 3으로 나눠질 떄
							minCnt += share;
							return true;
						}
					}
					minCnt = -1;
					return false;
				} // end if
			} // end if
		} // end if
		minCnt = -1;
		return false;
	}

	// 3으로 나누기
	public static boolean modThree(int n) {
		int share = 0; // 몫
		int rem = 0; // 나머지

		share = n / 3;
		rem = n % 3;

		if (share > 0 && rem == 0) { // 성공
			minCnt = share;
			return true;
		}
		minCnt = -1;
		return false;
	}
}
