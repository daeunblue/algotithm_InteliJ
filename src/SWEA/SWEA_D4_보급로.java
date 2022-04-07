package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * 가중치가 있는 그래프 탐색 -> 다익스트라 알고리즘 or BFS로 계속 업데이트
 *
 * */
public class SWEA_D4_보급로 {
    static int[][] map;
    static int[][] road;
    static boolean[][] isVisited;
    static Queue<Point> que = new LinkedList<>();
    static int[] dr = {1, -1, 0, 0};    // 상 하 좌 우
    static int[] dc = {0, 0, -1, 1};

    static int T, N, result;

    static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            int sum = Integer.MAX_VALUE;

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            road = new int[N][N];
            isVisited = new boolean[N][N];

            for(int i=0; i<N; i++){
                String str = br.readLine();
                for(int j=0; j<N; j++){
                    map[i][j] = str.charAt(j) - '0';
                }
            }
            
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    road[i][j] = Integer.MAX_VALUE; // 작은 값으로 갱신해 줘야하기 때문에 MAX_VALUE로 채워두기
                }
            }
            road[0][0] = 0;
            que.add(new Point(0,0));
            
            while(!que.isEmpty()){
                Point curP = que.poll();
                System.out.println(curP.toString());
                if(curP.row == N-1 && curP.col == N-1){ // 도착 -> 작은값으로 갱신해주기
                    sum = Math.min(road[N-1][N-1], sum);
                }

                if(road[curP.row][curP.col] >= sum) continue; // 이미 최단경로보다 오래 걸리는 경우 pass (가지치기)

                for(int dir=0; dir < 4; dir++){
                    int nr = curP.row + dr[dir];
                    int nc = curP.col + dc[dir];

                    if(nr >= N || nc >= N || nr < 0 || nc < 0) continue; // 배열 범위 초과

                    if(!isVisited[nr][nc] || map[nr][nc] + road[curP.row][curP.col] < road[nr][nc]){ // 방문하지 않았거나 + 방문 했더라도 현재 경로가 더 빨리 도착한 경우 -> Update 해줘야 함
                        isVisited[nr][nc] = true; // 방문 처리
                        que.offer(new Point(nr,nc)); // 이동 지점 추가
                        road[nr][nc] = map[nr][nc] + road[curP.row][curP.col];
                    }
                }

            }
            for(int[] r : road){
                System.out.println(Arrays.toString(r));
            }
            sb.append("#"+test_case+" "+road[N-1][N-1]+"\n");
        }
        System.out.println(sb.toString());
    }

}
