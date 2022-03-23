package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_D4_사칙연산유효성검사 {

	static char[] node = new char[222];
	static int n;
	static int result;
	public static void init() {
		for (int i = 0; i < n; i++)
			node[i] = 0;
	}

	

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; test_case++) {
			n = sc.nextInt();
			init();
			String s = sc.nextLine();
			
			for (int i = 1; i <= n; i++) {
				s = sc.nextLine();
				String[] arr = s.split(" ");
				node[i] = arr[1].charAt(0);
			}
			
			if(check(1) == true) {
				result = 1;
			}else {
				result = 0;
			}
			
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static boolean isIn(int x) {
		return (1 <= x && x <= n && node[x] == 0);
	}

	public static boolean check(int x) {
		
		if (x > n)
			return false;
		char c = node[x];
		
		if ('0' <= c && c <= '9') {	// �����϶� -> �ڽ� �� �ִ��� Ȯ�� 
			if ((isIn(x * 2) && isIn(x * 2 + 1)) || (x * 2 > n) && (x * 2 + 1 > n))
				return true;
			return false;
		} else
			return (check(x * 2) & check(x * 2 + 1));
	}
}