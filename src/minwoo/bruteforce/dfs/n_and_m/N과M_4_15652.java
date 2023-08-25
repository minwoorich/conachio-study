package minwoo.bruteforce.dfs.n_and_m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N과M_4_15652 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int result[];
    static StringBuilder output = new StringBuilder();
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        result = new int[M];
        dfs(0,0);
        System.out.println(output);
    }

    public static void dfs(int depth, int last) {
        if (depth == M) {
            // append안하고 바로 sysout으로 출력하면 시간초과 발생함
            for (int num : result) {
                output.append(num+" ");
            }
            output.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            // 현재 값(i)이 이전 값(last)보다 크거나 같아야함(비내림차순)
            if (i >= last) {
                result[depth] = i;
                dfs(depth+1,i);
            }

        }
    }
}