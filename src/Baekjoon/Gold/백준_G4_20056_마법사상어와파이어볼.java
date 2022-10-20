package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준_G4_20056_마법사상어와파이어볼 {
    static class FireBall {
        int r, c, m, s, d;

        FireBall(int r, int c, int m, int s, int d){
            this.r=r;
            this.c=c;
            this.m=m;
            this.s=s;
            this.d=d;
        }
    }

    static int N, M, K;
    static int[][] map;
    static int[][] dir = {{-1,0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static ArrayList<FireBall> balls;
    static ArrayList<FireBall> temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        temp = new ArrayList();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            temp.add(new FireBall(R, C, M, S, D));
        }

        for(int i=0; i<K; i++) {
            map = new int[N+1][N+1];
            balls = new ArrayList(temp);
            temp.clear();

            // 1. 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동한다.
            for(int j=0; j<balls.size(); j++) {
                FireBall ball = balls.get(j);
                int nx = ball.r;
                int ny = ball.c;

                // 속력 s 만큼 이동
                for(int k=0; k<ball.s; k++) {
                    nx += dir[ball.d][0];
                    ny += dir[ball.d][1];

                    // 범위 밖을 벗어나면 (연결시켜주는 과정)
                    if(!isIn(nx, ny)) {
                        if(nx == 0) nx = N;
                        else if(nx == N+1) nx = 1;

                        if(ny == 0) ny = N;
                        else if(ny == N+1) ny = 1;
                    }
                }
                // 파이어볼 위치 갱신
                balls.set(j, new FireBall(nx, ny, ball.m, ball.s, ball.d));
                map[nx][ny] += 1;
            }
            // 이동 끝

            // map에 2개 이상의 파이어볼이 있는 칸 확인.
            for(int j=1; j<=N; j++) {
                for(int k=1; k<=N; k++) {

                    if(map[j][k] >= 2) {

                        int sumM=0; // 질량 합
                        int sumS=0; // 속력 합
                        int cnt=0; // 파이어볼 갯수
                        int add=0; // 홀수
                        int even=0; // 짝수

                        for(int o=0; o<balls.size(); o++) {
                            FireBall fb = balls.get(o);

                            if(fb.r == j && fb.c == k) {
                                sumM += fb.m;
                                sumS += fb.s;
                                cnt++;

                                // 파이어볼의 방향이 짝수이면
                                if(fb.d % 2 == 0) even++;
                                else add++;


                                balls.remove(o);
                                o--;
                            }
                        }

                        // 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다
                        int tempM = sumM/5;

                        // 질량이 0인 파이어볼은 소멸되어 없어진다.
                        if(tempM <= 0) continue;

                        // 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
                        int tempS = sumS / cnt;


                        // 2개이상의 파이어볼이 있는 칸은4개의 파이어볼로 나누어진다.
                        // 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
                        if(cnt == even || cnt == add) {
                            temp.add(new FireBall(j, k, tempM, tempS, 0));
                            temp.add(new FireBall(j, k, tempM, tempS, 2));
                            temp.add(new FireBall(j, k, tempM, tempS, 4));
                            temp.add(new FireBall(j, k, tempM, tempS, 6));
                        } else {
                            temp.add(new FireBall(j, k, tempM, tempS, 1));
                            temp.add(new FireBall(j, k, tempM, tempS, 3));
                            temp.add(new FireBall(j, k, tempM, tempS, 5));
                            temp.add(new FireBall(j, k, tempM, tempS, 7));
                        }
                    }
                }
            }

            // balls 에 남아있는거 temp에 추가
            for(FireBall fb : balls) {
                temp.add(fb);
            }
        }

        int answer = 0;

        for(FireBall fb : temp) {
            answer += fb.m;
        }

        System.out.println(answer);
    }

    public static boolean isIn(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= N;
    }
}
