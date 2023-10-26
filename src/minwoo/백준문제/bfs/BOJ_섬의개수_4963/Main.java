package minwoo.백준문제.bfs.BOJ_섬의개수_4963;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int W,H;
    static StringTokenizer st;
    static Queue<Point> queue = new ArrayDeque<>();
    static int[] dx = new int[]{1,-1,0,0, 1,1,-1,-1};
    static int[] dy = new int[]{0,0,1,-1, 1,-1,1,-1};
    public static void main(String[] args) throws IOException {
        while (true){
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) {
                break;
            }

            map = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == 1) {
                        bfs(j, i);// 여기는 x,y 로 보내야 하므로, j,i 순서로 전달해야함
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static void bfs(int startX, int startY) {
        queue.add(new Point(startX, startY));
        map[startY][startX] = 0; // [startY][startX] 순서 조심해야함.
                                // (행,열) 순서이기 때문에 Y,X 순으로 들어가야함

        while (!queue.isEmpty()) {
            Point currentPoint = queue.remove();
            int currentX = currentPoint.getLocation().x;
            int currentY = currentPoint.getLocation().y;

            for (int i = 0; i < dx.length; i++) {
                int neighborX = currentX + dx[i];
                int neighborY = currentY + dy[i];

                if (!(neighborX >= 0 && neighborY >= 0 && neighborX < W && neighborY < H)) {
                    continue;
                }
                if (map[neighborY][neighborX] == 0) {
                    continue;
                }

                queue.add(new Point(neighborX, neighborY));
                map[neighborY][neighborX] = 0;
            }
        }
    }
}
