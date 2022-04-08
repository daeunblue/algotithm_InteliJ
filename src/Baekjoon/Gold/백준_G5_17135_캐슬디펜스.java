package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_G5_17135_캐슬디펜스 {
   static int[][] map;
   static int[][] afterMap;
   static ArrayList<Archer> archerList = new ArrayList<>();
   static int[] dr = {0, -1, 0}; // 좌, 상 , 우
   static int[] dc = {-1, 0, 1};

   static int N, M, D, maxKill;

   static class Archer{
       int row;
       int col;

       public Archer(int row, int col) {
           this.row = row;
           this.col = col;
       }
   }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        afterMap = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<M; i++){
            archerList.add(new Archer(N, i));
        }
        maxKill = Integer.MIN_VALUE;
        combination(new boolean[M],0 , 0, 3);
        System.out.println(maxKill);
    }
    
    // 조합 -> 궁수 위치 뽑기
    private static void combination(boolean[] isChoosed, int start, int depth, int end){
       if(depth == end){
           int[] archer = new int[3];
           int idx = 0;

           // 궁수가 있는 col archer[] 배열에 저장해두기
           for (int i = 0; i < M; i++) {
               if (isChoosed[i])
                   archer[idx++] = i;
           }
           simulation(archer);
           return;
       }

        for (int i = start; i < M; i++) {
            isChoosed[i] = true;
            combination(isChoosed, i + 1, depth+1, end);
            isChoosed[i] = false;
        }
    }
    
    // 위치 뽑은 뒤 시뮬레이션
    private static void simulation(int[] archer) {
        boolean[][] isDead; // 적이 다음턴에 죽어야 하는지 확인할 배열
        int killSum = 0;
        int killCnt;

        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, afterMap[i], 0, M);
        }// 배열 복사

        for (int turn = 0; turn < N; turn++) {   // N (열) 만큼 턴 진행
            // 이 부분 조심해야함 !!!!!!!!
            // -> 문제에서 "같은 적이 여러 궁수에게 공격 당할 수 있다" 라는 부분을 놓치지말자
            // -> 즉 바로 kill 하는게 아니라 -> 모든 궁수의 행동이 끝난 후에 kill 처리 해주기
            isDead = new boolean[N][M];

            for (int col : archer) {    // 궁수 별로 탐색 시작(거리 1 인 경우만 탐색)
                if (afterMap[N - 1][col] == 1) {    // 적 처리 가능한 경우
                    isDead[N - 1][col] = true;
                } else {    // 탐색 범위 확대
                    search(new Archer(N - 1, col), isDead);
                }
            }

            killCnt = kill(isDead);

            killSum += killCnt;

            move();

        }

        // 5) 해당 궁수 위치에 대한 최대 죽일 수 있는 적의 수 갱신
        maxKill = Math.max(maxKill, killSum);
        return;

    }

    private static void search(Archer curA, boolean[][] isDead){
       Queue<Archer> archerQue = new LinkedList<Archer>();
       boolean[][] isVisited = new boolean[N][M];

       archerQue.offer(curA);
       isVisited[curA.row][curA.col] = true;

        for (int dist = 1; dist < D; dist++) {  // 남은 거리만큼 BFS 진행
            int size = archerQue.size();

            // 새로 추가된 좌표만 탐색
            for (int j = 0; j < size; j++) {
                Archer archer = archerQue.poll();
                int row = archer.row;
                int col = archer.col;

                // 거리가 같으면
                for (int dir = 0; dir < 3; dir++) {
                    int nr = row + dr[dir];
                    int nc = col + dc[dir];

                    if(nr >= N || nc >= M || nr < 0 || nc < 0) continue; // 배열 범위 초과
                    if(isVisited[nr][nc]) continue; // 중복 처리

                    if(afterMap[nr][nc] == 1){
                        isDead[nr][nc] = true; // 턴이 끝난 뒤 죽게끔 처리
                        return;
                    }
                    archerQue.offer(new Archer(nr, nc));
                    isVisited[nr][nc] = true;
                }
            }
        }
    }



    private static int kill(boolean[][] isDead){    // isDead[row][col] 값이 true 인 경우 kill 하기
       int cnt = 0;

       for(int i=0; i<N; i++){
           for(int j=0; j<M; j++){
               if(isDead[i][j]){    // kill
                   cnt++;
                   afterMap[i][j] = 0; // 여기서 빈칸으로 처리해야 함!
               }
           }
       }
       return cnt;
    }



    private static void move(){
        // 맨 밑부터 조회 + 적 한칸씩 down 시키기 (끌어내리기)
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if(afterMap[i][j] == 1) {
                    afterMap[i][j] = 0; // 현재 위치를 빈칸으로 만든 뒤

                    if(i >= N-1) continue; // 배열 범위 초과
                    afterMap[i + 1][j] = 1; // 배열의 맨 끝부분이 아니라면 끌어내리기

                 }
            }
        }
    }


}
