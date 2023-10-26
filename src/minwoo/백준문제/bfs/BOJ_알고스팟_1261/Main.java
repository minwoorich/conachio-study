package minwoo.백준문제.bfs.BOJ_알고스팟_1261;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static Deque<Point> queue = new ArrayDeque<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N,M;
    static int min=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M + 1][N + 1];
        visited = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                visited[i][j] = -1;
            }
        }
        for (int i = 1; i <= M; i++) {
            String[] input = br.readLine().split("");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(input[j-1]);
            }
        }
        bfs(1,1);
        System.out.println(visited[M][N]);
    }

    public static void bfs(int startX, int startY) {
        queue.add(new Point(startX, startY));
        visited[startY][startX] = 0;

        while (!queue.isEmpty()) {
            Point currentLoc = queue.remove();
            int currentX = currentLoc.getLocation().x;
            int currentY = currentLoc.getLocation().y;

            for (int i = 0; i <4; i++) {
                int neighborX = currentX + dx[i];
                int neighborY = currentY + dy[i];

                if (!(neighborX >= 1 && neighborY >= 1 && neighborX <= N && neighborY <= M)) {
                    continue;
                }
                if (visited[neighborY][neighborX]!=-1) {
                    continue;
                }

                if (map[neighborY][neighborX] == 1) {
                    queue.addLast(new Point(neighborX, neighborY));
                    visited[neighborY][neighborX] = visited[currentY][currentX] + 1;
                } else {
                    queue.addFirst(new Point(neighborX, neighborY));
                    visited[neighborY][neighborX] = visited[currentY][currentX];
                }
            }
        }

    }
}
