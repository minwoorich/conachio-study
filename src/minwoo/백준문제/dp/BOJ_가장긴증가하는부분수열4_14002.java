package minwoo.백준문제.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ_가장긴증가하는부분수열4_14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;

        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] <= dp[j]) {
                    dp[i] = dp[j]+1;
                }
            }
        }

        int length = Arrays.stream(dp).max().getAsInt();
        System.out.println(length);


        // 부분수열의 최대값의 인덱스 구하기
        int index = 0;
        for (int i = 0; i <N; i++) {
            if (dp[i] == length) {
                index = i;
            }
        }

        // 부분수열의 최대값부터 내림차순으로 탐색
        // length = 최대값의 dp값
        List<Integer> output = new ArrayList<>();
        int value = 0;
        for (int i = index; i >= 0; i--) {
            if (length == dp[i]) {
                value = arr[i];
                length--;
                output.add(value);
            }
        }

        // 내림차순으로 저장되는 list를 다시 오름차순으로 sort한뒤 출력
        Collections.sort(output);
        for (int num : output) {
            System.out.print(num+" ");
        }
    }
}
