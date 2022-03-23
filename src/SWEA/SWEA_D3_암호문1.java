package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA_D3_암호문1 {
	static LinkedList list = new LinkedList<Integer>();
	static int index;	// 삽입 시작 인덱스
	static int N, CNT; //원본 암호문 길이 , 명령 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String str;
		int T = 1;
		while((str = br.readLine())!=null) {
			list.clear();
			N = Integer.parseInt(str);
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		
			// 명령어
			int cnt = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<cnt; i++) {
				String token = st.nextToken();
				if(token.equals("I")) {
					// 명령 시작
					index = Integer.parseInt(st.nextToken());
					CNT = Integer.parseInt(st.nextToken());
					
					for(int k=index; k<index+CNT; k++) {
						list.add(k, Integer.parseInt(st.nextToken()));
						
						// 아니면 값 바꾸기
						
					}
				}
			}
			
			sb.append("#").append(T).append(" ");
			for(int i=0; i<10; i++) {
				sb.append(list.get(i)).append(" ");
			}
			T++;
			System.out.println(sb.toString());
			sb.setLength(0);
		}
		
	}
}

