package minwoo.백준문제.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_스티커_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int [][]dp = new int[2][N+1];
            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= N; k++) {
                    dp[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            for (int j = 2; j <= N; j++) {
                dp[0][j] += Math.max(dp[1][j-1], dp[1][j-2]);
                dp[1][j] += Math.max(dp[0][j-1], dp[0][j-2]);
            }
            System.out.println(Math.max(dp[0][N], dp[1][N]));
        }
    }
}
