package minwoo.백준문제.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_연산자끼워넣기_14888 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;
    static int[] operator = new int[4];
    static int minResult = 1000000001;
    static int maxResult = -1000000001;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        // N입력
        N = Integer.parseInt(br.readLine());

        // 수열 입력
        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 입력
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        // dfs 실행 (+,-,*,/)
        dfs(1, arr[0], operator[0], operator[1], operator[2], operator[3]);

        System.out.println(maxResult);
        System.out.println(minResult);

    }
    public static void dfs(int depth, int result, int add, int sub, int mult, int div) {
        if (depth == N) {
            minResult = Math.min(minResult, result);
            maxResult = Math.max(maxResult, result);
            return;
        }

        if (add>0) {
            dfs(depth + 1, result + arr[depth], add - 1, sub, mult, div);
        }
        if (sub>0) {
            dfs(depth + 1, result - arr[depth], add, sub - 1, mult, div);
        }
        if (mult>0) {
            dfs(depth + 1, result * arr[depth], add, sub, mult - 1, div);
        }
        if (div>0) {
            dfs(depth + 1, result / arr[depth], add, sub, mult, div - 1);
        }
    }
}
