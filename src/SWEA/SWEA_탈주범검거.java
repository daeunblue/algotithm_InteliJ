package SWEA;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_탈주범검거 {
    static int N, M, R, C, L;
    static int[][] map;
    static boolean[][] isVisited;
    static int answer;
    static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}}; 
    static int[][] pipe = {
            {},
            {0,1,2,3},
            {0,2},
            {1,3},
            {0,1},
            {1,2},
            {2,3},
            {0,3}
    };

    static class Point {
        public int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++) {
            StringTokenizer tk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(tk.nextToken());
            M = Integer.parseInt(tk.nextToken());
            R = Integer.parseInt(tk.nextToken());
            C = Integer.parseInt(tk.nextToken());
            L = Integer.parseInt(tk.nextToken());

            map = new int[N][M];
            isVisited = new boolean[N][M];

            for(int i = 0; i< N; i++) {
                tk = new StringTokenizer(br.readLine());
                for(int j = 0; j< M; j++) {
                    map[i][j] = Integer.parseInt(tk.nextToken());
                }
            }
            // 최소한 1곳 (벙커)애는 존재할 수 있기 때문
            answer=1;

            bfs();
            sb.append("#" + test_case+ " " + answer +'\n');
        }
        System.out.println(sb.toString());
    }


    static void bfs() {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(R, C));   // 맨홀 시작
        isVisited[R][C] = true;
        int time = 1;

        if(time == L) return;   // 시간 종료

        while(!que.isEmpty()) {
            int size = que.size();

            for(int s =0; s<size; s++) {
                Point cur = que.poll();
                int curTunnel = map[cur.row][cur.col];

                for(int i = 0; i< pipe[curTunnel].length; i++) {
                    int curDir = pipe[curTunnel][i];
                    int nr = cur.row + deltas[curDir][0];
                    int nc = cur.col + deltas[curDir][1];

                    if(nr<0 || nr>= N || nc<0 || nc>= M) continue;  // 범위 초과
                    if(map[nr][nc]==0) continue;    // 파이프 없는 경우 pass
                    if(!isConnected(nr,nc,(curDir+2)%4)) continue;  // 연결되어있지 않은 경우
                    if(isVisited[nr][nc]) continue; // 방문한 적 있는 경우 

                    isVisited[nr][nc] = true;   // 방문 처리
                    answer++;
                    que.add(new Point(nr,nc));
                }
            }
            if(++time== L) return;  // 시간 종료
        }
    }

    static boolean isConnected(int row, int col, int dir) {  // 파이프끼리 연결되었는지 확인
        int curPipe = map[row][col];
        for(int p : pipe[curPipe]) {
            if(p == dir) // 현재 가려는 방향과 연결되어 있는 경우 -> true 반환
                return true;
        }
        return false;
    }


}