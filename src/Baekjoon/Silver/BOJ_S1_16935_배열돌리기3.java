package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_16935_배열돌리기3 {
    static int[][] map;
    static int[][] result;  // 결과 값
    static int N,M,R;
    public static void main(String[] args) throws IOException {
        /**
         * R - 1 : 상하반전 , 2: 좌우 반전, 3: 우측 90도 회전, 4: 좌측 40도 회전 ,
         * 5 : 블록 구역 나누고 우측블록으로 이동 6: 좌측 블록 이동
         * **/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        // map 입력받기
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        One();
//        Two();
//        Three();
//        Four();
//        Five();
//        Six();
        st = new StringTokenizer(br.readLine());
        int num;
        for(int n=0; n<R; n++) {
        	num = Integer.parseInt(st.nextToken());
        	switch(num) {
        	case 1 :
        		One();
        		break;
        	case 2 :
        		Two();
        		break;
        	case 3 :
        		Three();
        		break;
        	case 4 :
        		Four();
        		break;
        	case 5 :
        		Five();
        		break;
        	case 6 :
        		Six();
        		break;
        	}
        }
        
        for(int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
            	if(j == M-1) {
                	System.out.print(map[i][j]);
            	}else {
                	System.out.print(map[i][j]+" ");
            	}
            	
            }
            System.out.println();
        }

    }

    public static void One(){   // 상 하 반전
        int[] temp; // 옮길 값 저장할 배열
        temp = new int[map[0].length];

        for(int i=0; i<map.length/2; i++){
            temp = map[i];
            map[i] = map[N-1-i];
            map[N-1-i] = temp;
        }
        
    }
    public static void Two(){
        int temp; // 옮길 값 저장

        for(int i=0; i<N; i++){
            for(int j=0; j<M/2; j++){
//                System.out.println(j +" , "+(M-1-j));
                temp = map[i][j];
                map[i][j] = map[i][M-1-j];
                map[i][M-1-j] = temp;
            }
        }
//        for(int[] m : map){
//            System.out.println(Arrays.toString(m));
//        }

    }
    
 // 오른쪽 90도 회전
 	private static void Three() {
 		result = new int[M][N];

 		for (int i = 0; i < N; i++) {
 			for (int j = 0; j < M; j++) {
 				result[j][N - 1 - i] = map[i][j];
 			}
 		}

 		// 크기 변환
 		int tmp = N;
 		N = M;
 		M = tmp;

 		map = result;
 	}

 	// 왼쪽 90도 회전
 	private static void Four() {
 		result = new int[N][M];

 		// 오른쪽으로 90도 회전 3번
 		for (int i = 0; i < 3; i++) {
 			Three();
 		}
 	}
 	
    public static void Five(){
    	
    	int[][] temp = new int[N][M];
    	
//    	// 4 to 1
    	for(int i=0; i<N/2; i++) {
    		for(int j= 0; j < M/2; j++) {
    			temp[i][j] = map[N/2+i][j];
    		}
    	}
    	// 1 to 2
    	for(int i=0; i<N/2; i++) {
    		for(int j= M/2; j < M; j++) {
    			temp[i][j] = map[i][j-(M/2)];
    		}
    	}
//    	// 2 to 3
    	for(int i=N/2; i<N; i++) {
    		for(int j= M/2; j < M; j++) {
    			temp[i][j] = map[i-N/2][j];
    		}
    	}
    	// 3 to 4
    	for(int i=N/2; i<N; i++) {
    		for(int j= 0; j < M/2; j++) {
    			temp[i][j] = map[i][(M/2)+j];
    		}
    	}
    	
    	map = temp;
    	

    } 
    public static void Six(){
    	
    	result = new int[N][M];
    	
    	// 1 to 4
    	for(int i=N/2; i<N; i++) {
    		for(int j= 0 ; j < M/2; j++) {
    			result[i][j] = map[i-N/2][j];
    		}
    	}
    	
    	// 2 to 1
    	for(int i=0; i<N/2; i++) {
    		for(int j= 0; j < M/2; j++) {
    			result[i][j] = map[i][(M/2)+j];
    		}
    	}
    	// 3 to 2  
    	for(int i=0; i<N/2; i++) {
    		for(int j= M/2; j < M; j++) {
    			result[i][j] = map[N/2+i][j];
    		}
    	}
//    	// 3 to 4
    	for(int i=N/2; i<N; i++) {
    		for(int j= M/2; j < M; j++) {
    			result[i][j] = map[i][j-M/2];
    		}
    	}

    	map = result;
//    	for(int[] m : result) {
//		System.out.println(Arrays.toString(m));
//	}
    	
    }

}
