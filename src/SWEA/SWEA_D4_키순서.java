package SWEA;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_D4_키순서 {
    static ArrayList<ArrayList<Integer>> shortList;
    static ArrayList<ArrayList<Integer>> tallList;
    static int N, M, result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine())+1;
            M = Integer.parseInt(br.readLine());
            shortList = new ArrayList<>();
            tallList = new ArrayList<>();
            result = 0;
            for (int i = 0; i < N; i++) {
                shortList.add(new ArrayList<>());
                tallList.add(new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                shortList.get(end).add(first);//back 정점의 진입차수로 front 정점 저장
                tallList.get(first).add(end);//front 정점의 진출차수로 back 정점 저장
            }
            for (int i = 1; i < N; i++) {
                // 각 정점마다 진입차수, 진출차수를 타고 완탐해서 나머지 정점을 모두 접근가능한지 확인
                BFS(i);
            }
            sb.append("#"+test_case+" "+ result+"\n");
        }
        System.out.println(sb.toString());
    }
    private static void BFS(int index) {//bfs
        Queue<Integer> que = new LinkedList<>();
        boolean[] isVisited = new boolean[N];
        int cnt = 0;
        int cur = 0;

        que.add(index);
        isVisited[index] = true;
        cnt++;

        while(!que.isEmpty()) {
            cur = que.poll();
            for (int i = 0; i < shortList.get(cur).size(); i++) {
                int t = shortList.get(cur).get(i);
                if(!isVisited[t]) {
                    que.add(t);
                    isVisited[t] = true;
                    cnt++;
                }
            }
        }

        que.add(index);
        while(!que.isEmpty()) {//진출차수로 위로 끝까지 올라가기
            cur = que.poll();
            for (int i = 0; i < tallList.get(cur).size(); i++) {
                int t = tallList.get(cur).get(i);
                if(!isVisited[t]) {
                    isVisited[t] = true;
                    que.add(t);
                    cnt++;
                }
            }
        }
        if(cnt == N -1) result++;
    }
}
