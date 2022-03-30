package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_G5_3190_뱀 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K, L;
    static int snakeHeadR, snakeHeadC, tailIdx, direct;
    static int[] snakeR, snakeC; // 뱀의 몸통 위치
    static int[] command;   // 명령 저장할 배열
    static int[][] map;

    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};    //  우, 상, 좌, 하 (반시계방향)

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        for (int i = 0; i < K; ++i) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1;
        }

        snakeR = new int[10010];
        snakeC = new int[10010];
        command = new int[10010];

        snakeHeadR = 1; snakeHeadC = 1;
        snakeR[0] = 1; snakeC[0] = 1;
        map[snakeHeadR][snakeHeadC] = 2;

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; ++i) {   // t번 뱀을 c방향으로 이동시킨다
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            if(c == 'D'){
                command[t] = 1;
            }else{
                command[t] = 3;
            }
        }

        simulation(1);  // 1초부터 시작
    }

    public static void simulation(int time) throws IOException {
        // 머리 한칸 이동
        snakeHeadR += dir[direct][0];
        snakeHeadC += dir[direct][1];

        //범위를 벗어나거나 자신을 만나면 종료
        if (snakeHeadR < 1 || snakeHeadC < 1 || snakeHeadR > N || snakeHeadC > N || map[snakeHeadR][snakeHeadC] == 2) {
            System.out.println(time);   // 결과 출력
            return;
        }

        snakeR[time] = snakeHeadR;
        snakeC[time] = snakeHeadC;

        // 이동 칸에 사과가 있는지 확인 -> 없으면? 꼬리 한칸 줄이기
        if (map[snakeHeadR][snakeHeadC] != 1) {
            map[snakeR[tailIdx]][snakeC[tailIdx]] = 0;
            tailIdx++;
        }

        // 지도에 뱀 표시
        map[snakeHeadR][snakeHeadC] = 2;

        // rotation
        if (command[time] != 0) {
            direct = (direct + command[time]) % 4;
        }

        simulation(time + 1);   // 1초 추가 + 다시 호출
    }
}