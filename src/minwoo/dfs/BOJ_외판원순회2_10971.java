package minwoo.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_외판원순회2_10971 {
    static int N;
    static boolean[] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] W;
    static int[] cities;
    static int minCost=9999999;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        cities = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            W[i] = Arrays.stream(temp).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0);
        System.out.println(minCost);
    }

    private static int calculateCost() {
        int sum = 0;
        for (int i = 0; i < N-1; i++) {
            if (W[cities[i]][cities[i + 1]] == 0) {
                return minCost;
            }
            sum += W[cities[i]][cities[i + 1]];
        }
        if (W[cities[N-1]][cities[0]] == 0) {
            return minCost;
        }
        sum+=W[cities[N-1]][cities[0]];
        return sum;
    }
    private static void dfs(int depth) {
        if (depth == N) {
            minCost = Math.min(minCost, calculateCost());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                cities[depth] = i;
                visited[i] = true;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
