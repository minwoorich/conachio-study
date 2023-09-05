package minwoo.백준문제.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_쉬운계단수_10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N+1][10];
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {

                if (j == 0) {
                    // 매 연산마다 모듈러 연산을 해주지 않으면
                    // 어느 시점부터 long타입을 벗어남
                    dp[i][j] = dp[i - 1][1] % 1000000000;
                    continue;
                }

                if (j == 9) {
                    // 매 연산마다 모듈러 연산을 해주지 않으면
                    // 어느 시점부터 long타입을 벗어남
                    dp[i][j] = dp[i - 1][8] % 1000000000;
                    continue;
                }

                // 매 연산마다 모듈러 연산을 해주지 않으면
                // 어느 시점부터 long타입을 벗어남
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1] % 1000000000;
            }
        }
        long sum = Arrays.stream(dp[N]).sum();
        System.out.println(sum%1000000000);
    }
}
