package minwoo.bruteforce.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ABCDE_13023 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int N,M;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i,0);
        }
        System.out.println(0);
    }

    public static void dfs(int v,int depth) {
        if (depth == 5) {
            System.out.println(1);
            System.exit(0);
        }

        for (int neighbor : graph[v]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfs(neighbor, depth+1);
                visited[neighbor] = false;
            }
        }

        Arrays.stream(new int[]{1, 2, 3, 4});
    }
}
