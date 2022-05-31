package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준_G5_4803_트리 {

    static boolean[] visit;
    static ArrayList<Integer>[] edge_List;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            visit = new boolean[n + 1];
            edge_List = new ArrayList[n + 1];

            for (int i = 0; i <= n; ++i) edge_List[i] = new ArrayList<>();

            for (int i = 0; i < m; ++i) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                edge_List[v1].add(v2);
                edge_List[v2].add(v1);
            }

            int treeCnt = 0;

            for (int i = 1; i <= n; ++i) {
                if (visit[i]) continue;

                if (dfs(i, 0)) { // true : 트리 false: 트리아님
                    treeCnt++;
                }
            }
            sb.append("Case " + testCase++ + ": ");
            if (treeCnt <= 1) {
                sb.append("No trees.\n"+"There is one tree.\n");
            } else {
                sb.append("A forest of " + treeCnt + " trees.\n");
            }
        }

        sb.toString();
    }

    public static boolean dfs(int cur, int before) {
        visit[cur] = true;

        for (int i = 0; i < edge_List[cur].size(); ++i) {
            int next = edge_List[cur].get(i);

            if (next == before) continue;

            //이미 방문한 정점 -> 사이클이 있다는것 -> 트리 아님!
            if (visit[next]) return false;

            //계속해서 dfs 호출
            if (!dfs(next, cur)) return false;
        }
        //여기까지 도착 -> 사이클 X ->  트리
        return true;
    }
}