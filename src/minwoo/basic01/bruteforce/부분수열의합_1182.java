package minwoo.basic01.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 부분수열의합_1182 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int S;
    static int count = 0;
    static int[] arr;
    static List<Integer> output = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String[] input1 = br.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        S = Integer.parseInt(input1[1]);
        arr = new int[N];

        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input2[i]);
        }

        for (int i = 1; i <= N; i++) {
            dfs(0,i);
        }
        System.out.println(count);
    }

    public static void dfs(int start, int size) {
        if (output.size() == size) {
            int sum = getSum();
            if (sum == S) {
                count++;
            }
            return;
        }

        for (int i = start; i < N; i++) {
            if (!output.contains(i)) {
                output.add(i);
                dfs(i + 1, size);
                output.remove(output.size() - 1);
            }
        }
    }

    private static int getSum() {
        List<Integer> temp = new ArrayList<>();
        for (int num : output) {
            temp.add(arr[num]);
        }
        return temp.stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
