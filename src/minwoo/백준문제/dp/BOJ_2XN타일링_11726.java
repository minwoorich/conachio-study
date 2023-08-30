package minwoo.백준문제.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_2XN타일링_11726 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Integer> dp = new ArrayList<>(Arrays.asList(0,1,2));

        for (int i = 3; i < 1001; i++) {
            dp.add((dp.get(i-1) + dp.get(i-2))%10007);
        }

        System.out.println(dp.get(N));
    }
}
