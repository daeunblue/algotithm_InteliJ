package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_G4_1520_내리막길 {

    public class Algo2_서울_11반_김다은 {
        static int R, C, possible; // test_case, 높이, 너비, 가능한 경로의 수
        static int[][] map;	// 맵
        static boolean[][] isVisited; // 방문 여부 체크
        static int[] dr = {-1, 1, 0, 0}; // 상 , 하 , 좌, 우로 이동할 방향 배열
        static int[] dc = {0, 0, -1, 1}; // 상 , 하 , 좌, 우로 이동할 방향 배열

        public static void main(String[] args) throws NumberFormatException, IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();


            // input
            // 높이, 너비  입력
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            // map, isVisited 선언
            map = new int[R][C];
            isVisited = new boolean[R][C];

            for(int i=0; i<R; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            possible = 0; // 가능한 경우의 수 초기화
            bfs(0,0);


            // output
            System.out.println(possible);
        }

        public static void bfs(int row, int col) {
            if(row == R-1 && col == C-1) { // 맵의 맨 오른쪽 끝에 도달한 경우 (가능한 경로)
                possible++;	// 가능한 경우의 수 증가
                for(int i=0; i<R; i++) {	// 해당 경로 이후 초기화 한번 해줘야 다른 최단경로도 검색할 수 있기 때문에 초기화 한번 시켜주기
                    for(int j=0; j<C; j++) {
                        isVisited[i][j] = false;
                    }
                }
                return;
            }

            for(int dir=0; dir<4; dir++) {	// 사방탐색
                // 다음 지점 확인하기 위한 nr, nc
                int nr = row + dr[dir];
                int nc = col + dc[dir];

                if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue; // 배열 범위 초과 -> continue
                if(isVisited[nr][nc]) continue; // 이미 방문한 곳인 경우 pass

                boolean isLow = (map[row][col] > map[nr][nc])? true :false; // 내리막인지 확인
                if(!isLow) continue; // 같거나 오르막인 경우 pass
                isVisited[nr][nc] =  true; // 방문 처리
                bfs(nr,nc); // 다음 bfs 진행
            }

        }

    }

}
