package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_S1_2583_영역구하기 {
    static int width, height, K;
    static int[][] map;
    // 상하좌우
    static int[] xdir = {-1,1,0,0};
    static int[] ydir = {0,0,-1,1};
    public static class Pos {
        int x;
        int y;

        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        height = Integer.parseInt(temp[0]);
        width = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);

        map = new int[height][width];
        for(int i=0; i<K; i++){
            temp = br.readLine().split(" ");
            int y1 = Integer.parseInt(temp[0]);
            int x1 = Integer.parseInt(temp[1]);
            int y2 = Integer.parseInt(temp[2]);
            int x2 = Integer.parseInt(temp[3]);
            for(int j=x1; j<x2; j++){
                for(int k=y1; k<y2; k++){
                    map[j][k] = 1;
                }
            }
        }

        int area=0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(map[i][j] != 1){
                    list.add(bfs(i,j));
                    area++;
                }
            }
        }

        Collections.sort(list);
        System.out.println(area);
        for(int a : list){
            System.out.print(a + " ");
        }
        System.out.println();
        br.close();
    }

    public static int bfs(int a, int b){
        Queue<Pos> q = new LinkedList<>();
        int sum = 0;
        q.add(new Pos(a,b));
        map[a][b] = 1;

        while(!q.isEmpty()){
            Pos p =q.poll();
            int x = p.x;
            int y = p.y;

            for(int i=0; i<4; i++){
                int dx = x + xdir[i];
                int dy = y + ydir[i];

                if(isVaild(dx, dy) && map[dx][dy] != 1){
                    q.add(new Pos(dx, dy));
                    map[dx][dy] = 1;
                    sum++;
                }
            }
        }
        return sum+1;
    }

    private static boolean isVaild(int x, int y){
        if( x < 0 || x >= height || y < 0 || y >= width) return false;
        return true;
    }


}
