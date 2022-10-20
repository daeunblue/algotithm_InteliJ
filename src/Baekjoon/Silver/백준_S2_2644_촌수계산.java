package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_S2_2644_촌수계산 {
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        LinkedList<Integer>[] list = new LinkedList[N+1];
        isVisited = new boolean[N+1];

        for(int j=0; j<N+1; j++){
            list[j] = new LinkedList<>();
        }

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list[x].add(y);
            list[y].add(x);
        }

        int answer = dfs(first, list, isVisited, second, 0);
        System.out.println(answer);
    }
    static int dfs(int start, LinkedList<Integer>[] list, boolean[] isVisited, int goal, int cnt){
        int answer = -1;

        if(start == goal) {
            return cnt;
        }

        for(int i=0; i<list[start].size(); i++){
            int next = list[start].get(i);

            if(isVisited[next]) continue;
            else{
                isVisited[next] = true;
                answer = dfs(next, list, isVisited, goal, cnt+1);
                isVisited[next] = false;
            }
            if(answer != -1)  break;
        }
        return answer;
    }
}

