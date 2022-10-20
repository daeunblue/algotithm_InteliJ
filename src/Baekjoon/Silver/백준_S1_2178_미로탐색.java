package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_S1_2178_미로탐색 {
    static int N, M, answer;
    static char[][] map;
    static boolean[][] isvisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        isvisited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
        }

        // 0,0에서 N-1, M-1 까지의 최단거리 게산
        bfs(0,0,N,M);

        System.out.println(answer+1);
    }

    // bfs - que
    public static void bfs(int r, int c , int N, int M){ // 시작 r,c , 도착 r,c
        Queue<int[]> queue = new LinkedList<>();
        isvisited[r][c] = true; // 방문처리
        int[] dx = {-1,0,1,0};
        int[] dy = {0,-1,0,1};

        queue.add(new int[]{r,c,0}); // {row, col, 원점으로부터의 거리}
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == N-1 && cur[1] == M-1){ // 종점 도착
                answer = cur[2];
                break;
            }

            for(int i=0; i<4; i++){
                int nr = cur[0] + dx[i];
                int nc = cur[1] + dy[i];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue; // 배열 범위 체크

                if(!isvisited[nr][nc] && map[nr][nc] == '1') { // 방문한 적 없고 이동 가능한 경우
                    isvisited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, cur[2] + 1});
                }
            }
        }
    }
}
