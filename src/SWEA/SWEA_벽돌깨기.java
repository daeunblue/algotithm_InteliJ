package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_벽돌깨기 {
    static int T, N, W, H; // test_case, 구슬 개수, 너비, 높이
    static int[][] map = new int [13][16];  // 벽돌 저장할 map
    static Map<Integer,Integer> hashMap = new HashMap<>();
    static int remain; // 남은 벽돌 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
