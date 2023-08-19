package minwoo.basic01.bruteforce.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 부분수열의합_1182_bitmasking {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] input1 = br.readLine().split(" ");
        int N = Integer.parseInt(input1[0]);
        int S = Integer.parseInt(input1[1]);
        int[] arr = new int[21];

        // 수열 저장
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input2[i]);
        }

        int cnt = 0; // 수열의 합이 S와 같은 경우의 수를 카운트


        // 1 ~ 32 까지 반복, (2^0 ~ 2^5)
        for (int i = 1; i < (1 << N); i++) {
            int sum = 0;
            // i의 2진수를 0 ~ (N-1) 자리 까지 반복하여 1이 있는지 없는지 탐색
            // 1이 있으면 그 자리에 있는 값을 sum에 누적 합 시킴.
            // ex) i = 3 = 00011
            // -> sum = (arr[5-1-0=4] + arr[5-1-1=3])
            // = arr[4] + arr[3]
            for (int index = 0; index < N; index++) {
                if ((i & (1 << index)) != 0 ) {
                    sum+=arr[(N-1)-index];
                }
            }
            // 수열의 합이 S와 같은 경우 카운트!
            if (sum == S) {
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}
