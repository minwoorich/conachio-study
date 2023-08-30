package minwoo.백준문제.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_123더하기_9095 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        List<Integer> dp = null;
        for (int i = 0; i < T; i++) {
            dp = new ArrayList<>(Arrays.asList(0,1,2,4));
            int N = Integer.parseInt(br.readLine());
            for (int j = 4; j <= N; j++) {
                dp.add(dp.get(j - 1) + dp.get(j - 2) + dp.get(j - 3));
            }
            System.out.println(dp.get(N));
        }
    }
}
