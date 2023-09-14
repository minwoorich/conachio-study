package minwoo.백준문제.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_숨바꼭질4_13913 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,K;
    static Queue<Integer> queue = new ArrayDeque<>();
    static boolean[] visited = new boolean[100001];
    static int[][] route = new int[100001][2];
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N));
        List<Integer> output = route();
        for (int i=output.size()-1;i>=0;i--) {
            System.out.print(output.get(i)+" ");
        }
    }

    public static int bfs(int start) {
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.remove();
            if (current == K) {
                return route[current][0];
            }
            if (current-1 >= 0 && !visited[current-1]) {
                queue.add(current - 1);
                route[current-1][0] = route[current][0] + 1;
                route[current-1][1] = current;
                visited[current-1] = true;
            }
            if (current+1 <= 100000 && !visited[current+1]  ) {
                queue.add(current + 1);
                route[current+1][0] = route[current][0] + 1;
                route[current+1][1] = current;
                visited[current+1] = true;
            }
            if (current*2 >= 0 && current*2 <= 100000 && !visited[current*2]) {
                queue.add(current * 2);
                route[current*2][0] = route[current][0] + 1;
                route[current*2][1] = current;
                visited[current*2] = true;
            }
        }
        return -1;
    }

    public static List<Integer> route() {
        List<Integer> list = new ArrayList<>();
        int current = K;
        list.add(current);
        while (current != N) {
            current = route[current][1];
            list.add(current);
        }
        return list;
    }
}
