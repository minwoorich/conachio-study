package minwoo.백준문제.dfs.n_and_m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_N과M_3_15651 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int[] result;
    static StringBuilder output = new StringBuilder();
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        result = new int[M];
        dfs(0);

        // 출력
        System.out.println(output);

    }
    public static void dfs(int depth) {
        if (depth == M) {
            for (int num : result) {
                output.append(num+" ");
            }
            output.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            result[depth] = i;
            dfs(depth+1);
        }
    }
}
