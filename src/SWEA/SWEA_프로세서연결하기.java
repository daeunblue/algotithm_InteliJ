package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_프로세서연결하기 {
    static int map[][];
    static ArrayList<Core> list = new ArrayList<>();
    static int[] dr = { 1, -1, 0, 0 };
    static int[] dc = { 0, 0, 1, -1 };
    static int N;
    static int maxCnt = Integer.MIN_VALUE;
    static int minLength = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();

    static class Core {
        int row;
        int col;

        Core(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        list.add(new Core(i, j));
                    }
                }
            }

            dfs(0, 0, 0);
            sb.append("#"+testCase+" "+minLength+"\n");
            list.clear();
            maxCnt = Integer.MIN_VALUE;
            minLength = Integer.MAX_VALUE;
        }
        System.out.println(sb.toString());

    }

    public static void dfs(int depth, int cnt, int vLength) {
        // 종료조건 -> Core 를 전부 다 확인했을 때
        // 확인해야할 부분 -> cnt가 maxCnt 보다 작으면 pass, cnt == maxCnt인 경우 -> length와 minLength 비교(작은 값 minLength에 저장)
        if (depth == list.size()) {
            if (cnt > maxCnt) {
                maxCnt = cnt;
                minLength = vLength;

            } else if (cnt == maxCnt) {
                if (minLength > vLength) {
                    minLength = vLength;
                }
            }

            return;
        }
        Core core = list.get(depth);

        // 사방탐색
        for (int i = 0; i < 4; i++) {
            if (core.row == 0 || core.row == N - 1 || core.col == 0 || core.col == N - 1) {
                dfs(depth + 1, cnt + 1, vLength);
                break;
            }

            int check = checkLine(i, core);
            int nr = core.row;
            int nc = core.col;
            
            if (check != -1) {
                dfs(depth + 1, cnt + 1, vLength + check);
                
                for (int j = 0; j < check; j++) {
                    nr += dr[i];
                    nc += dc[i];
                    map[nr][nc] = 0;

                }
            }

        }
        // 해당 코어는 활성화시키지 않는 경우
        dfs(depth + 1, cnt, vLength);
    }

    public static int checkLine(int i, Core c) {
        int line = 0;
        int nr = c.row + dr[i];
        int nc = c.col + dc[i];
        boolean check = false;
        while (true) {
            line++;
            if ((nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) && map[nr][nc] == 0) {
                check = true;
                break;
            }
            if (map[nr][nc] != 0) {
                check = false;
                break;
            }
            nr += dr[i];
            nc += dc[i];

        }
        nr = c.row;
        nc = c.col;
        //연결이 가능하다면 라인을 2로 표시해주면서 그어줌
        if (check) {
            for (int idx = 0; idx < line; idx++) {
                nr += dr[i];
                nc += dc[i];
                map[nr][nc] = 2;

            }
            return line;
        }
        return -1;
    }

}

