package SWEA;

import java.util.*;
import java.io.*;
public class SSEA_D3_1873_상호의배틀필드 {

	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		char [][] map;
		char [] action;
		int tankH = 0;	// 전차 위치 (height)
		int tankW = 0;	// 전차 위치 (width)
		String tPos = null; // 바라보고 있는 방향
		int T;
		int H,W;
		T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++){
			// map 만들기
			int index = 0;
			String str;
			H = sc.nextInt();
			W = sc.nextInt();
			map = new char[H][W];
			
			for(int i=0; i<H; i++) {
				 str = sc.next();
				 for(int j=0; j<W; j++) {		
					map[i][j] = str.charAt(j);
					// 전차 위치 + 방향 저장
					if(map[i][j]=='<') {
						tPos = "L";
						tankH = i;
						tankW = j;
					}else if(map[i][j] == 'v') {
						tPos = "D";
						tankH = i;
						tankW = j;
					}else if(map[i][j] == '^') {
						tPos = "U";
						tankH = i;
						tankW = j;
					}else if(map[i][j] == '>') {
						tPos = "R";
						tankH = i;
						tankW = j;
					}
				}
				index = 0;
			}
//			System.out.println(tPos +" , "+ tankH +" , " +tankW);
			// 행동 입력받기
			int aCnt = sc.nextInt();
			action = new char[aCnt];
			
			str = sc.next();
			
			for(int k=0; k<str.length(); k++) {
				action[k] = str.charAt(k);
			}
			
			// 동 서 남 북 이동 구현 
			/* 구현시 생각해야 할 점
			 * 1. 해당 방향으로 이동할 때 경계 밖으로 가는지 확인하기 -> 경계 밖으로 간다면 이동 X
			 * 2. S : 바라보고 있는 방향에 있는 모든 *를 삭제하고 평지.로 수정 , 강철#이 경로에 있다면 그 앞까지만 삭제  
			 * 3. U D L R : 해당 방향으로 회전 후 -> 평지인지 확인하고 -> 평지라면 이동 
			 * */
			
			for(int i=0; i<aCnt; i++) {	 // 행동 수만큼 for문 돌리기
				switch(action[i]) {
				case 'S':	// shoot
//					System.out.println("현재 전차는 "+tPos+"를 가르키고 있습니다");
					if(tPos == "L") { 	// 전차가 왼쪽을 보고 있을 때
						if(tankW == 0) {// 이미 왼쪽 끝일 때
							break;
						}
						for(int w=tankW-1; w>=0; w--) {
							if(map[tankH][w] == '*') { 	// 벽돌 만나면 stop + 부수기
								map[tankH][w] = '.'; // 평지로 만들기
								break;
							}else if(map[tankH][w] == '#') {	// 강철 만나면 stop
								break;
							} 
						}
					}else if(tPos == "U") {
						if(tankH == 0) {// 이미 맨 위일 때
							break;
						}
						for(int h=tankH-1; h>=0; h--) {
							if(map[h][tankW] == '*') { 	// 벽돌 만나면 stop + 부수기
								map[h][tankW] = '.'; // 평지로 만들기
								break;
							}else if(map[h][tankW] == '#') {	// 강철 만나면 stop
								break;
							} 
						}
					}else if(tPos == "D") {
						if(tankH == H-1) {// 이미 맨 아래일 때
							break;
						}
						for(int h=tankH+1; h<H; h++) {
							if(map[h][tankW] == '*') { 	// 벽돌 만나면 stop + 부수기
								map[h][tankW] = '.'; // 평지로 만들기
								break;
							}else if(map[h][tankW] == '#') {	// 강철 만나면 stop
								break;
							} 
						}

					}else if(tPos == "R") {
						if(tankW == W-1) {// 이미 맨 오른쪽일 때
							break;
						}
						for(int w=tankW+1; w<W; w++) {
							if(map[tankH][w] == '*') { 	// 벽돌 만나면 stop + 부수기
								map[tankH][w] = '.'; // 평지로 만들기
								break;
							}else if(map[tankH][w] == '#') {	// 강철 만나면 stop
								break;
							} 
						}
					}
					break;
				case 'U':	// up
					tPos = "U";
					map[tankH][tankW] = '^';
					/*전차가 맨 위에 있을 때*/
					if(tankH == 0) {	// 이동 X
						break;
					}
					if(map[tankH-1][tankW] == '.') {	// 전차의 위가 평지라면
						map[tankH][tankW] = '.';
						map[tankH-1][tankW] = '^';
						tankH--;
					}else if(map[tankH-1][tankW] == '-') {	// 물 만났을 때
						break;
					}
					break;
				case 'D':	// down
					tPos = "D";
					map[tankH][tankW] = 'v';
					/*전차가 맨 아래에 있을 때*/
					if(tankH == H-1) {	// 이동 X
						break;
					}
					if(map[tankH+1][tankW] == '.') {	// 전차의 아래가 평지라면
						map[tankH][tankW] = '.';
						map[tankH+1][tankW] = 'v';
						tankH++;
					}else if(map[tankH+1][tankW] == '-') {	// 물 만났을 때
						break;
					}
					break;
				case 'L':	// left
					tPos = "L";
					map[tankH][tankW] = '<';
					/*전차가 맨 왼쪽에 있을 때*/
					if(tankW == 0) {	// 이동 X
						break;
					}
					if(map[tankH][tankW-1] == '.') {	// 전차의 왼쪽 평지라면
						map[tankH][tankW] = '.';
						map[tankH][tankW-1] = '<';
						tankW--;
					}else if(map[tankH][tankW-1] == '-') {	// 물 만났을 때
						break;
					}
					break;
				case 'R':	// right
					tPos = "R";
					map[tankH][tankW] = '>';
					/*전차가 맨 오른쪽에 있을 때*/
					if(tankW == W-1) {	// 이동 X
						break;
					}
					if(map[tankH][tankW+1] == '.') {	// 전차의 오른쪽이 평지라면
						map[tankH][tankW] = '.';
						map[tankH][tankW+1] = '>';
						tankW++;
					}else if(map[tankH][tankW+1] == '-') {	// 물 만났을 때
						break;
					}
					break;
				}
				
//				for(char[] maps :map) {
//					System.out.println(Arrays.toString(maps));
//				}
//				System.out.println(" ");
			}
			System.out.printf("#%d ",test_case);
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println("");
			}
		}
	}
	
}

