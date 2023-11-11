package minwoo.삼성sw기출.BOJ_마법사상어와파이어볼_20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class FireBall{
    int r;
    int c;
    int m;
    int s;
    int d;

    public FireBall(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//                      0,   1,   2,   3,   4,    5,   6,   7
    //               북북, 북동, 동동, 남동, 남남, 남서, 서서, 북서
    static int[] dr = {-1,  -1,   0,   1,   1,    1,   0,  -1};
    static int[] dc = { 0,   1,   1,   1,   0,   -1,  -1,  -1};
    static List<FireBall> fireBalls = new ArrayList<>();
    static Map<String, List<FireBall>> fireballsMap = new HashMap<>();
    static int N,M,K;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        map = new int[N][N];
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0])-1;
            int c = Integer.parseInt(input[1])-1;
            int m = Integer.parseInt(input[2]);
            int s = Integer.parseInt(input[3]);
            int d = Integer.parseInt(input[4]);

            map[r][c] = 1;
            fireBalls.add(new FireBall(r, c, m, s, d));
        }

        callFireball(K);
        printResult();
    }

    public static void callFireball(int count) {
        while (count > 0) {
            moveFireballs();
            createNewFireballs();
            count--;
        }
    }

    public static void moveFireballs() {
//          0,   1,   2,   3,   4,    5,   6,   7
//        북북, 북동, 동동, 남동, 남남, 남서, 서서, 북서
//        {-1,  -1,   0,   1,   1,    1,   0,  -1};
//        { 0,   1,   1,   1,   0,   -1,  -1,  -1};
        for(FireBall f : fireBalls) {
            map[f.r][f.c]--;
            f.r = (N + (f.r + dr[f.d] * f.s) % N) % N;
            f.c = (N + (f.c + dc[f.d] * f.s) % N) % N;
            map[f.r][f.c]++;
        }
    }

    public static void createNewFireballs() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2) {
                    // 파이어볼이 2개이상 모일 경우 합치고 분할 해줘야함
                    mergeAndSeparate(i, j, map[i][j]);
                }
            }
        }
    }

    public static void mergeAndSeparate(int r, int c, int count) {
        int totalM = 0;
        int totalS = 0;
        int evenCnt = 0;
        int oddCnt = 0;
        int size = 0;

        // 후방탐색으로 해야 remove() 할 때 오류 안 남
        for (int i = fireBalls.size()-1; i >= 0; i--) {
            FireBall f = fireBalls.get(i);
            if(f.r == r && f.c == c){
                totalM += f.m;
                totalS += f.s;
                if (f.d % 2 == 0) {
                    evenCnt++;
                } else {
                    oddCnt++;
                }
                size++;
                fireBalls.remove(f);
            }
        }

        if (totalM / 5 == 0) {
            map[r][c] = 0;
            return;
        }

        // 전부 홀 또는 짝 -> start=0  , 홀/짝 섞여 있음 -> start = 1
        int start = (oddCnt == size || evenCnt == size) ? 0:1;

        // 새롭게 분할된 4개의 파이어볼 추가
        for (int i = start; i < 8; i += 2) {
            fireBalls.add(new FireBall(r, c, totalM / 5, totalS / count, i));
        }
        map[r][c] = 4;
    }

    public static void printResult() {
        int sum = 0;
        for (FireBall f : fireBalls) {
            sum+=f.m;
        }
        System.out.println(sum);
    }
}
