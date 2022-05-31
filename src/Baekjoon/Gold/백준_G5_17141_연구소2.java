package Baekjoon.Gold;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_G5_17141_연구소2 {
    static int N, M;
    static int[][] map;
    static ArrayList<Point> virus;
    static int[] dx = new int[] { 0, 0, 1, -1 }, dy = new int[] { 1, -1, 0, 0 };
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");

        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        map = new int[N][N];
        virus = new ArrayList<Point>();

        int blink = 0; // 빈공간 개수

        // 바이러스가 없고 벽만 촌재하는 초기화된 연구소 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 2) {
                    // 바이러스를 놓을 수 있는 칸 수집
                    // 지도에는 확산될 수 있는 빈칸으로 바꿈
                    virus.add(new Point(i, j));
                    map[i][j] = 0;
                    blink++;
                } else if (map[i][j] == 1) {
                    // 벽 : -1로 초기화 (시간 계산하기 위해)
                    map[i][j] = -1;
                } else {
                    // 빈 공간이 있는지 확인
                    blink++;
                }
            }
        }

        // 빈공간 없음 = 바이러스가 이미 다 차있는 상태
        if (blink == 0) {
            System.out.println(0);
        } else {
            // 바이러스를 놓을 M개의 칸 뽑음 : 조합(순서에 신경쓰지 않고 뽑음)
            pickVirus(new boolean[virus.size()], 0, M);
            System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        }

    }

    public static void pickVirus(boolean[] visited, int start, int r) {
        if (r == 0) {
            Point[] picked = new Point[M];
            int idx = 0;

            // 바이러스 놓을 칸 다 뽑았을 때
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    picked[idx++] = virus.get(i);
                }
            }
            // 이를 적용하여 바이러스를 퍼트린 지도 가져옴
            int[][] copyMap = spreadVirus(picked);

            // 바이러스가 퍼진 시간을 담고있는 지도에서 최대 시간을 받아옴
            // 받아진 최대 시간 중 최소를 고름
            result = Math.min(result, checkVirus(copyMap));

            return;
        }

        for (int i = start; i < virus.size(); i++) {
            visited[i] = true;
            pickVirus(visited, i + 1, r - 1);
            visited[i] = false;
        }
    }

    public static int[][] spreadVirus(Point[] picked) {
        Queue<Point> queue = new LinkedList<Point>();
        int[][] copyMap = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < picked.length; i++) {
            Point pick = picked[i];
            // 뽑힌 바이러스에 대해 지도에 표시 + 작업 큐에 넣음
            copyMap[pick.x][pick.y] = -2;
            visited[pick.x][pick.y] = true;
            queue.add(pick);
        }

        while (!queue.isEmpty()) {
            Point curV = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curV.x + dx[i];
                int ny = curV.y + dy[i];
                // 복제된 칸에서 퍼지는게 아닌
                // 바이러스를 놓은 칸에서 복제하는 거면 1초로 셋팅
                int newValue = copyMap[curV.x][curV.y] == -2 ? 1 : copyMap[curV.x][curV.y] + 1;

                // 바이러스가 퍼질 수 있는 공간이면 바이러스 복제
                if (isIn(nx, ny) && !visited[nx][ny]) {
                    copyMap[nx][ny] = newValue;
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        return copyMap;
    }

    public static int checkVirus(int[][] copyMap) {
        int sec = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copyMap[i][j] == 0) {
                    // 만약 빈 공간이 하나라도 있으면 틀리게끔
                    return Integer.MAX_VALUE;
                }
                sec = Math.max(sec, copyMap[i][j]);
            }
        }

        return sec;
    }

    public static boolean isIn(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < N && map[x][y] != -1) {
            return true;
        }
        return false;
    }
}