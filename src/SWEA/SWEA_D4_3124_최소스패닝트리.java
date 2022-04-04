package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D4_3124_최소스패닝트리 {

    static int V, E, roots[], T;
    static Edge[] edges;


    static class Edge implements Comparable<Edge> {
        int from, to, value;

        public Edge(int from, int to, int value) {
            super();
            this.from = from;
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.value, o.value);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for(int test_case=1; test_case<=T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());    // 정점
            E = Integer.parseInt(st.nextToken());    // 간선

            edges = new Edge[E];
            roots = new int[V + 1];

            // Edge 정보 입력
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                edges[i] = new Edge(a, b, c);
            }
            sb.append("#"+test_case+" "+kruskal()+"\n");
        }
        System.out.println(sb.toString());
    }

    private static long kruskal() {

        long res = 0, cnt = 0;

        // 간선 오름차순 정렬
        Arrays.sort(edges);
        make();

        // 주어진 간선을 이어보면서
        for (Edge edge : edges) {
            // 싸이클이 형성되지 않는다면
            if(union(edge.from, edge.to)) {
                // 해당 간선을 사용
                res += edge.value;
                // 정점 - 1 개의 간선이 이어졌다면 MST 완성!
                if(++cnt == V - 1) return res;
            }
        }

        return res;
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;    // 같은 부모인 경우 -> 합치기 불가능
        roots[rootA] = rootB;
        return true;
    }

    private static int find(int a) {
        if(a == roots[a]) return a;
        return roots[a] = find(roots[a]);
    }

    private static void make() {
        for (int i = 1; i <= V; i++) {
            roots[i] = i;
        }
    }

}

