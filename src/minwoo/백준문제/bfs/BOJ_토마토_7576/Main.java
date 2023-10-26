package minwoo.백준문제.bfs.BOJ_토마토_7576;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};
    static Queue<Point> queue = new ArrayDeque<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map;
    static int N,M, result=0;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    // 토마토를 발견하면 해당 좌표를 queue에 집어 넣음
                    queue.add(new Point(j, i));
                }
            }
        }

        bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    result = -1;
                    break;
                }
            }
        }
        if (result == 0 ) { // 이미 토마토가 다 익어있는 경우
            System.out.println(0);
        } else if(result == -1) {// 익지 않은 토마토가 1개라도 있을 경우
            System.out.println(-1);
        }else{ // 토마토가 모두 익은 경우
            System.out.println(result-1);
        }
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            Point currentPoint = queue.remove();
            int currentX = currentPoint.getLocation().x;
            int currentY = currentPoint.getLocation().y;

            for (int i = 0; i < 4; i++) {
                int neighborX = currentX + dx[i];
                int neighborY = currentY + dy[i];

                if (!(neighborX >= 0 && neighborY >= 0 && neighborX < M && neighborY < N)) {
                    continue;
                }

                if (map[neighborY][neighborX] != 0) {
                    continue;
                }

                queue.add(new Point(neighborX, neighborY));
                map[neighborY][neighborX] = map[currentY][currentX] + 1;
                result = Math.max(result, map[neighborY][neighborX]);
            }
        }
    }
}
