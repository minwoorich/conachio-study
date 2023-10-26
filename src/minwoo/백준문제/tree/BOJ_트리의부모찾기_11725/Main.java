package minwoo.백준문제.tree.BOJ_트리의부모찾기_11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Queue<Integer> queue = new ArrayDeque<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static ArrayList<Integer>[] tree;
    static int[] parents ;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        parents = new int[N + 1];
        visited = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            tree[x].add(y);
            tree[y].add(x);
        }
        bfs(1);

        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }
    }

    public static void bfs(int start) {
        queue.add(start);

        while (!queue.isEmpty()) {
            int parent = queue.remove();
            for (int child : tree[parent]) {
                if (!visited[child]) {
                    parents[child] = parent;
                    visited[child] = true;
                    queue.add(child);
                }
            }

        }
    }
}
