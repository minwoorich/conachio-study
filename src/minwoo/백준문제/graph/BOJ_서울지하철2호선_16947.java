package minwoo.백준문제.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_서울지하철2호선_16947 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer>[] graph;
    static int[] visited;
    static boolean[] isRing;
    static Queue<Integer> queue = new ArrayDeque<>();
    static int N;
    static StringTokenizer st;
    static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        isRing = new boolean[N+1];
        visited = new int[N + 1];

        // 그래프 생성
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        // 순환선 / 비순환선 역 구분하기
        for (int i = 1; i <= N; i++) {
            dfs(0, i, i);
        }

        // 순환선까지의 거리 구하기
        for (int i = 1; i <= N; i++) {
            if (isRing[i]) {
                result.append(0).append(" ");
                continue;
            }
            visited = new int[N + 1];
            bfs(i);
        }
        System.out.println(result);
    }

    public static void dfs(int depth, int start, int v) {
        if (depth >= 3 && v == start) {
            isRing[v] = true;
            return;
        }

        for (int node : graph[v]) {
            if (visited[node]!=1) {
                visited[node] = 1;
                dfs(depth+1, start, node);
                visited[node] = 0;
            }
        }
    }

    public static void bfs(int start) {
        queue.add(start);
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            if (isRing[v]) {
                result.append(visited[v]-1).append(" ");
                queue.clear();
                break;
            }

            for (int neighbor : graph[v]) {
                if (visited[neighbor] != 0) {
                    continue;
                }

                visited[neighbor] = visited[v] + 1;
                queue.add(neighbor);
            }
        }
    }
}
