package minwoo.백준문제.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1로만들기_1463 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        // 연산의 횟수가 저장됨
        // ex) dp[4] --> 4에서 1까지 되는데 필요한 연산의 횟수가 저장됨.
        int[] dp = new int[1000001];


        for (int i = 2; i <= N; i++) {
            // +1 연산
            // +1 연산의 경우 반드시 바로 전 단계 [i-1]의 연산횟수보다 +1 만큼 증가함.
            dp[i] = dp[i-1] + 1;


            // /2 연산
            // /2 연산의 경우 [i/2] 단계 보다 +1 만큼 증가함
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

            // /3 연산
            // /3 연산의 경우 [i/3] 단계 보다 +1 만큼 증가함
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}
