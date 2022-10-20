package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_S3_2606_바이러스 {
    static  int length = 0;
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int line = Integer.parseInt(br.readLine()); // 간선 수

        Node[] list = new Node[N+1];
        for(int i=0; i<line; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list[from] = new Node(to, list[from]);
            list[to] = new Node(from, list[to]);
        }

        bfs(list, 1,N);

        System.out.println(length);


    }
    public static void bfs(Node[] nList, int vertex, int N){
        Queue<Integer> que = new LinkedList<>();
        boolean[] isVisited = new boolean[N+1];

        que.offer(vertex);
        isVisited[vertex] = true;

        while(!que.isEmpty()){
            int size = que.size();
            for(int i=0; i<size; i++){
                int current = que.poll();
                //current 정점의 인접정점 처리(단, 방문하지 않은 인접정점만)
                for(Node temp = nList[current]; temp!=null; temp = temp.link) {
                    if(!isVisited[temp.vertex]) {
                        que.offer(temp.vertex);
                        isVisited[temp.vertex] = true;
                        length++;
                    }
                }
            }
//            length++;
        }
    }

    // Node 생성
    static class Node{
        int vertex;//정점번호
        Node link;

        public Node(int vertex, Node link) {
            this.vertex = vertex;
            this.link = link;
        }

        @Override
        public String toString() {
            return "Node [vertex=" + vertex + ", link=" + link + "]";
        }
    }


}
