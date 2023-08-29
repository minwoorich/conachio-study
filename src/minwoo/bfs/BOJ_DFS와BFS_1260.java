package minwoo.bfs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_DFS와BFS_1260 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Queue<Integer> queue = new ArrayDeque<>();
    static boolean[][] graph;
    static boolean[] visited;
    static int N,M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드의 개수
        M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int v = Integer.parseInt(st.nextToken());

        graph = new boolean[N+1][N+1]; // 행과 열 모두 N+1 개의 칸이 존재해야함

        // 그래프 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 양방향이므로 x <-> y 모두 연결해줘야함
            graph[x][y] = true;
            graph[y][x] = true;
        }

        // DFS 파트
        visited = new boolean[N + 1];
        dfs(v); //dfs(시작노드)
        System.out.println(sb);// 출력
        sb.setLength(0); // sb 길이 0으로 초기화

        //BFS 파트
        visited = new boolean[N + 1]; // 방문자체크배열 초기화 해줘야함.
        bfs(v); //bfs(시작노드)
        System.out.println(sb);// 출력
    }

    public static void dfs(int start) {
        // 모든 노드를 방문하는게 목적이므로 depth 필요없음
        visited[start] = true;
        sb.append(start+" ");

        for (int i = 1; i <= N; i++) {
            if (graph[start][i] && !visited[i]) {
                dfs(i);
            }
        }
    }

    public static void bfs(int start) {
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            sb.append(v+" ");
            for (int i = 1; i <= N; i++) {
                if (graph[v][i] && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
