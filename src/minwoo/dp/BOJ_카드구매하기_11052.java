package minwoo.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_카드구매하기_11052 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] dp = new int[1001];
        int[] P = new int[10001];

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int index = 1;
        while (st.hasMoreTokens()) {
            P[index] = Integer.parseInt(st.nextToken());
            index++;
        }

        dp[1] = P[1];

        int max = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = i; j > i/2; j--) {
                max = Math.max(dp[j-1]+dp[i-j+1], max);
            }
            dp[i] = Math.max(max, P[i]);
        }

        System.out.println(dp[N]);
    }
}
