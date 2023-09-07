package minwoo.백준문제.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_TwoDots_16929 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static String[][] graph;
    static boolean[][] visited;
    static StringTokenizer st;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new String[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().split("");
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(0,j,i, j,i);
            }
        }

        System.out.println("No");
    }

    public static void dfs(int depth, int currentX, int currentY, int startPointX, int startPointY) {
        if (depth>=4 && currentX == startPointX && currentY == startPointY) {
            System.out.println("Yes");
            System.exit(0);
        }

        for (int i = 0; i < 4; i++) {
            int neighborX = currentX + dx[i];
            int neighborY = currentY + dy[i];

            if (!(neighborX >= 0 && neighborY >= 0 && neighborX < M && neighborY < N)) {
                continue;
            }

            if (visited[neighborY][neighborX] || !(graph[startPointY][startPointX].equals(graph[neighborY][neighborX]))) {
                continue;
            }
            visited[neighborY][neighborX] = true;
            dfs(depth+1, neighborX, neighborY, startPointX, startPointY);
            visited[neighborY][neighborX] = false;
        }
    }
}
