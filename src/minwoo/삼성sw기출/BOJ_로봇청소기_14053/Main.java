package minwoo.삼성sw기출.BOJ_로봇청소기_14053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int N, M, r, c,d;
    static int count=1;
    static int[][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];

        input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]); // y축
        c = Integer.parseInt(input[1]); // x축
        d = Integer.parseInt(input[2]); // 시작 방향

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        clean(c,r,d);
        System.out.println(count);
    }

    public static void clean(int x, int y, int direction) {
        // 현재 위치를 청소
        map[y][x] = -1;

        // 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색
        for (int i = 0; i < 4; i++) {
            direction = (direction+3) % 4;

            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                if (map[ny][nx] == 0) {
                    count++;
                    clean(nx,ny,direction);
                    return;
                }
            }
        }

        int rear = (direction + 2) % 4;
        int rx = x + dx[rear];
        int ry = y + dy[rear];

        if (rx >= 0 && ry >= 0 && rx < M && ry < N &&
                map[ry][rx] != 1) {
            clean(rx, ry, direction); //바라보는 방향은 유지
        }
    }
}
