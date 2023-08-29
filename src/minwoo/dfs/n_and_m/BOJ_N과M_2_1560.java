package minwoo.dfs.n_and_m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BOJ_N과M_2_1560 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[] visit;
    static int N;
    static int M;
    static StringBuilder output;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        output = new StringBuilder("");
        visit = new boolean[N+1];

        // 처음에는 시작 값을 넘겨줌
        dfs(1);
    }

    public static void dfs(int last) {
        if (output.length() == M) {
            String result = Arrays.stream(output.toString().split(""))
                    .collect(Collectors.joining(" "));
            System.out.println(result);
            return;
        }

        for (int i = 1; i <= N; i++) {
            // 방문한 곳 금지(중복 회피) && 이전 값보다 크거나 같아야함(오름차순)
            if (!visit[i] && last <= i) {
                output.append(i);
                visit[i] = true;
                dfs(i);
                visit[i] = false;
                output.deleteCharAt(output.length()-1);
            }
        }
    }
}
