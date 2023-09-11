package minwoo.백준문제.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int x;
    int y;
    int bridge;

    public Node(int x, int y, int bridge) {
        this.x = x;
        this.y = y;
        this.bridge = bridge;
    }
}

public class BOJ_다리만들기_2146 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] map;
    static int[][] islandNumbers;
    static boolean[][] visited;
    static StringTokenizer st;
    static Queue<Node> queue = new ArrayDeque<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        islandNumbers = new int[N][N];

        // 섬 맵 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 섬들에 코드 부여
        int code = 100;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    visited = new boolean[N][N];
                    numberIslands(j, i, code);
                    code += 100;
                }
            }
        }

        // 섬간의 최단 거리 구하기
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited = new boolean[N][N];
                if (map[i][j] != 0) {
                    int result = getShortestBridge(j, i);
                    if (result != -1) {
                        shortest = Math.min(result, shortest);
                    }
                }
            }
        }
        System.out.println(shortest - 1);
    }

    // 각 섬들에 코드 부여
    public static void numberIslands(int startX, int startY, int code) {
        queue.add(new Node(startX, startY, 0));
        visited[startY][startX] = true;
        map[startY][startX] = code;

        while (!queue.isEmpty()) {
            Node currentLoc = queue.remove();
            int currentX = currentLoc.x;
            int currentY = currentLoc.y;

            for (int i = 0; i < 4; i++) {
                int neighborX = currentX + dx[i];
                int neighborY = currentY + dy[i];

                if (!(neighborX >= 0 && neighborY >= 0 && neighborY < N && neighborX < N)) {
                    continue;
                }

                if (visited[neighborY][neighborX] || map[neighborY][neighborX]==0) {
                    continue;
                }

                map[neighborY][neighborX] = code;
                visited[neighborY][neighborX] = true;
                queue.add(new Node(neighborX, neighborY, 0));
            }
        }
    }

    // 섬간의 최단 거리 구하기
    public static int getShortestBridge(int startX, int startY) {
        queue.add(new Node(startX, startY, 0));
        int startingIsland = map[startY][startX];
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            Node currentLocation = queue.remove();
            int currentX = currentLocation.x;
            int currentY = currentLocation.y;
            int bridgeLength = currentLocation.bridge;

            if (map[currentY][currentX] != 0 && map[currentY][currentX] != startingIsland) {
                queue.clear();
                return bridgeLength;
            }
            for (int i = 0; i < 4; i++) {
                int neighborX = currentX + dx[i];
                int neighborY = currentY + dy[i];

                if (!(neighborX >= 0 && neighborY >= 0 && neighborY < N && neighborX < N)) {
                    continue;
                }

                if (visited[neighborY][neighborX]) {
                    continue;
                }

                visited[neighborY][neighborX] = true;
                queue.add(new Node(neighborX, neighborY, bridgeLength + 1));
            }
        }
        return -1;
    }
}
