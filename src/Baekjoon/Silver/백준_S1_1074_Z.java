package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_S1_1074_Z {
	static int N, R, C;
	static int result = 0;
	static int size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		size = (int) Math.pow(2, N); // size = 2^N
		divide(size,R,C);
		System.out.println(result);
	}

	public static void divide(int size, int r, int c) {
		if (size == 1) {
			return;
		}
		// 사분면 체크
		if (r < size / 2 && c < size / 2) { // 1사분면
			divide(size / 2, r, c);
		} else if (r < size / 2 && c >= size / 2) { // 2사분면
			result += (size / 2) * (size / 2);
			divide(size / 2, r, c - size / 2);
		} else if (r >= size / 2 && c < size / 2) { // 3사분면
			result += (size / 2) * (size / 2) * 2;
			divide(size / 2, r - size / 2, c);
		} else if (r >= size / 2 && c >= size / 2) { // 4사분면
			result += (size / 2) * (size / 2) * 3;
			divide(size / 2, r - size / 2, c - size / 2);
		}
	}

}
