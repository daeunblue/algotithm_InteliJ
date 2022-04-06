package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * {@auther} : daeun
 * @since : 2022-04-06
 * link : https://www.acmicpc.net/problem/2239
 * type : 구현, 백트래킹
 * */

public class 백준_G5_2239_스도쿠 {
    static int[][] map = new int[9][9];
    static boolean flag; // 스도쿠가 완성되었는지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = str.charAt(j) - '0';

            }
        }
        // 사전식 출력 -> 제일 작은 값부터 채워넣자 (1 ~ 9)
        // 백트래킹 조건 -> 이미 스토쿠가 완성되었거나, 같은 수가 가로 , 세로 줄에 존재하거나, 3*3 안에 같은 수가 존재하는 경우
        dfs(0);

        for(int r=0; r<9; r++){
            for(int c=0; c<9; c++){
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int depth) {
        if (depth == 81) {    // 9 * 9 = 81칸 -> 0~80까지 돌면 81칸이므로 마지막 호출 depth : 81
            flag = true; // 스도쿠 완성
            return;
        }

        int row = depth / 9; // 행
        int col = depth % 9; // 열

        if(map[row][col] != 0){        // 채워진 곳인지 확인하기 -> 이미 채워진 곳이라면 dfs 탐색 진행
            dfs(depth+1);
        }else{  // 채워야 할 공간이라면
            for(int i=1; i<=9; i++){
                if(!isOk(row,col,i)) continue; // 불가능한 경우 pass
                map[row][col] = i;  // map에 i를 넣은 채로 dfs 진행해보기
                dfs(depth+1);
                if(flag) return; // 스도쿠가 완성되었다면 return(종료)
                map[row][col] = 0;   // 다시 진행해야 하므로 -> map을 이전 상태로 복구시켜놓기
            }

        }

    }

    public static boolean isOk(int row, int col, int n) {
        // 가로줄, 세로줄에 n 이 있는지
        for(int i=0; i<9; i++){
            if(map[row][i] == n) return false;
            if(map[i][col] == n) return false;
        }

        // 3*3 사각형에 n이 있는지
        int sR = row / 3;
        int sC = col / 3;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(map[i+3*sR][j+3*sC] == n) return false;
            }
        }
        return true; // 조건 전부 충족할 경우
    }
}

