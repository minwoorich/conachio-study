package minwoo.백준문제.bfs.BOJ_나이트의이동_7526;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = new int[]{1, 1,-1,-1, 2, 2,-2,-2};
    static int[] dy = new int[]{2, -2, 2,-2, 1,-1,1,-1};
    static int[][] map;
    static int size, targetX, targetY;
    static Queue<Point> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < T; i++) {
            size = Integer.parseInt(br.readLine());
            map = new int[size][size];

            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            targetX = Integer.parseInt(st.nextToken());
            targetY = Integer.parseInt(st.nextToken());

            bfs(startX, startY);
            System.out.println(map[targetY][targetX] - 1);
            map = new int[size][size];
        }
    }

    public static void bfs(int startX, int startY) {
        queue.add(new Point(startX, startY));
        map[startY][startX] = 1;

        while (!queue.isEmpty()) {
            Point currentPoint = queue.remove();
            int currentX = currentPoint.getLocation().x;
            int currentY = currentPoint.getLocation().y;

            for (int i = 0; i < 8; i++) {
                int neighborX = currentX + dx[i];
                int neighborY = currentY + dy[i];

                if (!(neighborX >= 0 && neighborY >= 0 && neighborX < size && neighborY < size)) {
                    continue;
                }

                if (map[neighborY][neighborX] != 0) {
                    continue;
                }

                map[neighborY][neighborX] = map[currentY][currentX] + 1;
                queue.add(new Point(neighborX, neighborY));
            }
        }
    }
}
