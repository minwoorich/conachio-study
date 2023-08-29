package minwoo.bruteforce.bfs;

import java.io.BufferedReader;
import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_미로탐색_2178 {
    static Queue<Point> queue = new ArrayDeque<>();
    static int N,M;
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};
    static int[][] map;
    static boolean[][] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }

        map = new int[N+1][M+1];
        visited = new boolean [N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(temp[j-1]);
            }
        }

        bfs(1, 1);
        System.out.println(map[N][M]);
    }

    public static void bfs(int startX, int startY) {
        queue.add(new Point(startX, startY));
        map[startY][startX] = 1;
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            Point currentPoint = queue.remove();
            int currentX = currentPoint.getLocation().x;
            int currentY = currentPoint.getLocation().y;


            for (int i = 0; i < 4; i++) {
                int neighborX = currentX + dx[i];
                int neighborY = currentY + dy[i];

                if (!(neighborX >= 0 && neighborY >= 0 && neighborX < M+1 && neighborY < N+1)) {
                    continue;
                }

                if (visited[neighborY][neighborX] || map[neighborY][neighborX] == 0) {
                    continue;
                }
                visited[neighborY][neighborX] = true;
                map[neighborY][neighborX] = map[currentY][currentX] + 1;
                queue.add(new Point(neighborX, neighborY));
            }
        }
    }
}
