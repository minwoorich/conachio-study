package minwoo.백준문제.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class BOJ_제곱수의합_1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = IntStream.rangeClosed(0,N).toArray();

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j <= i; j++) {
                if (i < j * j) {
                    break;
                }
                if (dp[i] > dp[i - j*j] + 1) {
                    dp[i] = dp[i - j*j] + 1;
                }
            }
        }
        System.out.println(dp[N]);
    }
}
