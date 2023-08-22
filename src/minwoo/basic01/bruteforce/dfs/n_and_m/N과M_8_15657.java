package minwoo.basic01.bruteforce.dfs.n_and_m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class N과M_8_15657 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int[] nArr;
    static int result[];
    static boolean[] visit;
    static StringBuilder output = new StringBuilder();
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        nArr = new int[N];
        visit = new boolean[10001];
        result = new int[M];
        output = new StringBuilder();

        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(input2[i]);
        }
        Arrays.sort(nArr);

        dfs(0, nArr[0]);
        System.out.println(output);
    }

    public static void dfs(int depth, int last) {
        if (depth == M) {
            for (int num : result) {
                output.append(num + " ");
            }
            output.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (last <= nArr[i]) {
                result[depth] = nArr[i];
                dfs(depth + 1, nArr[i]);
            }
        }
    }
}
