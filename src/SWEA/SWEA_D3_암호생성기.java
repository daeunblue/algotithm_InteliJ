package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA_D3_암호생성기 {
	static Queue<Integer> que = new LinkedList<>();
	static int temp;	//	값 저장용
	static boolean isEnd;
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String T;
        int last = 0;
        
        while((T = br.readLine())!=null) {
        	// 더이상 입력이 없을때까지 반복
        	// que 입력
        	st = new StringTokenizer(br.readLine());
        	que.clear();
        	
        	while(st.hasMoreTokens()) {
        		last = Integer.parseInt(st.nextToken()); //마지막값	
        		que.add(last);
        	}

        	while(isEnd == false) {
        		for(int i=1; i<6; i++) {	// 한 사이클 : 1 ~ 5 감소
        			temp = que.peek(); // 0번째 요소 저장해두기
            		que.remove(); 
        			if(temp - i <= 0) {
            			que.add(0); // 0 저장 후 종료
            			isEnd = true;
            			break;
            		}
        			que.add(temp-i);
        		}
        	}

        	isEnd = false;
        	// 결과
        	sb.append("#").append(T).append(" ");
        	
        	for(int i=0; i<8; i++) {
            	sb.append(que.poll()).append(" ");
        	}
        	// 왜 sb.toString을 밖으로 빼면 오류가날까
            
        	System.out.println(sb.toString());
            sb.setLength(0);
            
        }   
    }
}

