package minwoo.삼성sw기출.BOJ_미세먼지안녕_17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Point {
    int r;
    int c;
    int[] darr;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
        this.darr = new int[4]; // 각 방향마다 확산 될 미세먼지량 저장하는 배열
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R,C,T;
    static List<Point> dustList = new ArrayList<>();
    static List<Point> circulator = new ArrayList<>();
    static Queue<Point> queue;
    static int[][] map;
    //                 북  동  남 서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (hasTouchedCirculator(i, j)) {
                    circulator.add(new Point(i, j));
                }
                if (map[i][j] > 0) {
                    dustList.add(new Point(i, j));
                }
            }
        }

        int sec = 0;
        while (sec < T) {
            spreadDust(); // 미세먼지 확산
            filterAir(); // 공기청정기 가동
            updateDustList(); // dustList 갱신 -> 미세먼지들이 이동했으니 위치를 갱신해주어야함
            sec++;
        }
        System.out.println(getTotalParticles());
    }

    public static void spreadDust() {
        spreadStep1(); // step1 - 사방으로 확산시킬 먼지량 각 방향마다 저장 (darr[] 에 저장)
        spreadStep2(); // step2 - 사방으로 먼지 확산시키기
    }


    // step1 - 사방으로 확산시킬 먼지량 각 방향마다 저장 (darr[] 에 저장)
    public static void spreadStep1() {
        for (Point p : dustList) {
            // 미세먼지가 5미만은 확산 안되므로 그냥 스킵
            if (map[p.r][p.c] < 5) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (hasTouchedWall(nr, nc)) {
                    continue;
                }

                if (hasTouchedCirculator(nr, nc)) {
                    continue;
                }

                p.darr[i] = map[p.r][p.c] / 5; // 각 방향마다 확산 될 미세먼지량 저장
            }
            calcDustEmission(p); // 확산되고 남은 먼지량 계산
        }
    }

    // 확산되고 남은 먼지량 계산
    public static void calcDustEmission(Point p) {
        for (int num : p.darr) {
            map[p.r][p.c] -= num;
        }
    }


    // step2 - 사방으로 먼지 확산시키기
    public static void spreadStep2() {
        for (Point p : dustList) {
            for (int i = 0; i < 4; i++) {
                if (p.darr[i] != 0) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    map[nr][nc] += p.darr[i]; // 사방으로 먼지 확산
                }
            }
        }
    }


    // 공기청정기 가동
    public static void filterAir() {
        // ([행], [열], [초기 방향], [회전방향], [공기청정기위치])
        circulate(circulator.get(0).r, circulator.get(0).c, 0, 1, 1);
        circulate(circulator.get(1).r, circulator.get(1).c, 2, -1, 0);
    }

    public static void circulate(int r, int c, int d, int turn, int i) {
        queue = new ArrayDeque<>();

        int startR = r + dr[d];
        int startC = c + dc[d];

        map[startR][startC] = 0;
        queue.add(new Point(startR, startC));

        while (true) {
            Point crnt = queue.poll();
            int nr = crnt.r + dr[d];
            int nc = crnt.c + dc[d];


            // 벽을 넘거나 or 상/하부 공기청정구역 경계를 넘거나.

            // 상부 순환 구역의 경계는 공기청정기 하부의 행(r) 까지고
            // 하부 순환 구역의 경계는 공기청정기 상부의 행(r) 까지다
            if (hasTouchedWall(nr, nc) || nr == circulator.get(i).r) {
                d = (4+d+turn) % 4;
                nr = crnt.r + dr[d];
                nc = crnt.c + dc[d];
            }

            if (hasTouchedCirculator(nr, nc)) {
                map[crnt.r][crnt.c] = 0; // 공기청정기 맨 앞은 무조건 0 임
                break;
            }

            // 미세먼지 이동
            map[crnt.r][crnt.c] = map[nr][nc];
            queue.add(new Point(nr, nc));
        }
    }

    // dustList 갱신 -> 미세먼지들이 이동했으니 위치를 갱신해주어야함
    public static void updateDustList() {
        dustList.clear();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    dustList.add(new Point(i, j));
                }
            }
        }
    }

    public static boolean hasTouchedWall(int r, int c) {
        return !(r >= 0 && c >= 0 && r < R && c < C);
    }
    private static boolean hasTouchedCirculator(int nr, int nc) {
        return map[nr][nc] == -1;
    }

    public static int getTotalParticles() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                }
            }
        }
        return sum;
    }
}
