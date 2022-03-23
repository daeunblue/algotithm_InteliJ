package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SWEA_D3_햄버거다이어트 {
 
    static int N, L, maxL;
    static int[][] hamBInfo;
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());    
            L = Integer.parseInt(st.nextToken());    
 
            hamBInfo = new int[N][2];
 
            // 재료 정보
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                hamBInfo[i][0] = Integer.parseInt(st.nextToken());
                hamBInfo[i][1] = Integer.parseInt(st.nextToken()); 
            }
 
            maxL = 0;
            selectMaterial(0, 0, 0);
 
            System.out.println("#" + test_case + " " + maxL);
        }
    }
 
    public static void selectMaterial(int idx, int scr, int cal) {
        // 칼로리 초과
        if (cal > L) return;
        // 주어진 제한 칼로리 이하의 조합
        if (cal <= L) maxL = Math.max(maxL, scr);
        // 모두 조합을 확인
        if (idx == N) return;
        

        selectMaterial(idx + 1, scr + hamBInfo[idx][0], cal + hamBInfo[idx][1]);

        selectMaterial(idx + 1, scr, cal);
    }
 
}

