package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_G1_1194_달이차오른다가자 {

    static int n, m;
    static char map[][];
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());
        map = new char[n][m];
        int x = -1;
        int y = -1;
        for(int i = 0; i < n; i++) {
            String input = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j] == '0') {
                    x = i;
                    y = j;
                }
            }
        }
        escape(x, y);
    }

    public static void escape(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(-1, -1, 0));
        q.offer(new Point(x, y, 0));
        boolean visit[][][] = new boolean[n][m][1 << 6];
        visit[x][y][0] = true;

        int count = -1;

        while(!q.isEmpty()) {
            Point now = q.poll();

            if(now.x == -1) {
                count++;
                if(!q.isEmpty())
                    q.offer(now);
                continue;
            }

            if(map[now.x][now.y] == '1') {
                System.out.println(count);
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                int key = now.key;
                int copy = key;
                if(check(nx, ny) && !visit[nx][ny][key]) {
                    //열쇠
                    if(map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
                        key |= (1 << map[nx][ny] - 'a');
                        visit[nx][ny][key] = true;
                        q.offer(new Point(nx, ny, key));
                    }
                    //문
                    else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
                        if(!((key & (1 << map[nx][ny] - 'A')) == 0)) {
                            visit[nx][ny][key] = true;
                            q.offer(new Point(nx, ny, key));
                        }
                    }
                    //빈 칸
                    else {
                        q.offer(new Point(nx, ny, key));
                        visit[nx][ny][key] = true;
                    }
                }
                key = copy;
            }
        }
        System.out.println(-1);
    }

    public static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m && map[x][y] != '#';
    }

    static class Point {
        int x, y, key;

        Point(int x, int y, int key) {
            this.x = x;
            this.y = y;
            this.key = key;
        }
    }
}