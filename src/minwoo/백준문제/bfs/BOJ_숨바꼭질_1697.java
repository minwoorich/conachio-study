package minwoo.백준문제.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_숨바꼭질_1697 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,K;
    static int[] visited;
    static Queue<Integer> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new int[100001];
        bfs(N);
    }

    public static void bfs(int start) {
        queue.add(start);
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            if (v == K) {
                System.out.println(visited[v]-1);
                break;
            }

            if (v-1 >= 0 && visited[v - 1] == 0) {
                visited[v - 1] = visited[v]+1;
                queue.add(v - 1);
            }

            if (v+1<=100000 && visited[v + 1] == 0) {
                visited[v + 1] = visited[v]+1;
                queue.add(v + 1);
            }

            if (2*v<=100000 && visited[2 * v] == 0) {
                visited[2*v] = visited[v]+1;
                queue.add(2*v);
            }

        }
    }


}
