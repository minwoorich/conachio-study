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

        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input2[i]);
        }

        int cnt = 0;

        for (int i = 1; i < (1 << N); i++) {
            int sum = 0;
            for (int index = 0; index < N; index++) {
                if ((i & (1 << index)) != 0 ) {
                    sum+=arr[(N-1)-index];
                }
            }
            if (sum == S) {
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}
