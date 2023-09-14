package minwoo.백준문제.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_123더하기3_15988 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[1000001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int j = 4; j <= 1000000; j++) {
            dp[j] = (dp[j - 1]  + dp[j - 2] + dp[j - 3])% 1000000009;
        }
        for (int i = 0; i < T; i++) {
            System.out.println(dp[Integer.parseInt(br.readLine())]);
        }
    }

}
