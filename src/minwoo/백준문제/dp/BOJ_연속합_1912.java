package minwoo.백준문제.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ_연속합_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N + 1];
        dp[1] = arr[1];
        for (int i = 2; i < N + 1; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = arr[i];
                continue;
            }
            dp[i] = dp[i - 1] + arr[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
