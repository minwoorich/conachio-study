package minwoo.bruteforce.dfs.n_and_m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_N과M_5_15654 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int[] nArr;
    static int result[];
    static boolean[] visit;
    static StringBuilder output = new StringBuilder();
    public static void main(String[] args) throws IOException {
        String input1[] = br.readLine().split(" ");
        String input2[] = br.readLine().split(" ");

        N = Integer.parseInt(input1[0]);
        M = Integer.parseInt(input1[1]);
        visit = new boolean[10001];
        nArr = new int[N];
        result = new int[M];

        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(input2[i]);
        }
        Arrays.sort(nArr);

        dfs(0);
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

        for (int i = 0; i < N; i++) {
            if (!visit[nArr[i]]) {
                 result[depth] = nArr[i];
                 visit[nArr[i]] = true;
                 dfs(depth+1);
                 visit[nArr[i]] = false;
            }
        }
    }
}
